package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.pokemonBattle.*;
import ozmar.pokemonBattle.convertData.ConvertIntoPoke;
import ozmar.pokemonBattle.convertData.GetMovesData;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// TODO: Make sure every Pokemon has at least 1 move
public class PokeController {

    private ScheduledFuture<?> switchTimer;
    private ScheduledFuture<?> actionTimer;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final PokemonDaoInterface pokemonDao;
    private final ConvertIntoPoke convert;
    private final GetMovesData movesData;

    private final Map<Long, TrainerInfo> trainerMap;
    private final List<List<Trainer>> trainerList;
    private PokeBattleViewInterface view;
    private PokeBattle pokeBattle;

    private boolean battleOver;

    // REMOVE LATER ON
    private DefaultTrainer defaultTrainer;

    public PokeController(@Nonnull PokemonDaoInterface pokemonDao) {
        this.pokemonDao = pokemonDao;
        this.convert = new ConvertIntoPoke();
        this.movesData = new GetMovesData();
        this.trainerMap = new HashMap<>();
        this.trainerList = new ArrayList<>();
        this.battleOver = false;

        defaultTrainer = new DefaultTrainer();
    }

    public void setView(@Nonnull PokeBattleViewInterface view) {
        this.view = view;
    }

    // Used to create default trainers
    // Currently only used to test in Twitch Chat with others wile the rest
    // of the code for initializing correctly is incomplete
    public void setUsers(long userId1, long userId2) {
        Trainer red = defaultTrainer.redTrainer(userId1);
        this.trainerMap.put(userId1, new TrainerInfo(red, 0, 0));
        this.trainerList.add(new ArrayList<>(Collections.singleton(red)));

        Trainer blue = defaultTrainer.blueTrainer(userId2);
        this.trainerMap.put(userId2, new TrainerInfo(blue, 0, 0));
        this.trainerList.add(new ArrayList<>(Collections.singleton(blue)));

        this.pokeBattle = new PokeBattle(view, trainerList, 1);
        startActionTimer();
    }

    // Only set users once there are two users ready to battle
    public void setUsers(long userId1, @Nonnull String userName1, long userId2, @Nonnull String userName2) {
        Trainer trainer;
        trainer = createTrainer(userId1, userName1);
        this.trainerMap.put(userId1, new TrainerInfo(trainer, 0, 0));
        this.trainerList.add(new ArrayList<>(Collections.singleton(trainer)));

        trainer = createTrainer(userId2, userName2);
        this.trainerMap.put(userId2, new TrainerInfo(trainer, 1, 0));
        this.trainerList.add(new ArrayList<>(Collections.singleton(trainer)));

        this.pokeBattle = new PokeBattle(view, trainerList, 1);
        startActionTimer();
    }

    // Clear everything about the battle to have it ready for the next one
    private void clearAll() {

    }

    @Nonnull
    private Trainer createTrainer(long userId, @Nonnull String userName) {
        List<Poke> pokeList = convertIntoPoke(pokemonDao.getPokemon(userId));
        return new Trainer(userId, userName, pokeList);
    }

    @Nonnull
    private List<Poke> convertIntoPoke(@Nonnull List<PokemonPoke> list) {
        List<Poke> pokeList = new ArrayList<>();
        for (PokemonPoke pokemonPoke : list) {
            pokeList.add(convert.getPoke(pokemonPoke.getPokemonName(), pokemonPoke.getNature()));
        }

        return pokeList;
    }

    // Check if the pokemon can use the moves else send a message to the user saying what is wrong
    public void setPokeMoves(long userId, int pokePosition, @Nonnull List<String> moveList) {
        if (trainerMap.containsKey(userId)) {
            List<PokeMove> pokeMoves = movesData.convertNamesToMoves(moveList);
            TrainerInfo info = trainerMap.get(userId);
            List<PokeMove> currMoves = info.trainer.getPokeList().get(pokePosition).getMoveList();
            currMoves.clear();
            currMoves.addAll(pokeMoves);
        }
    }

    /**
     * Attempts to set a Poke to switch in for the current Poke on the field
     * Sends a message to the user of the resulting success
     *
     * @param trainerId     id of the trainer
     * @param fieldPosition position on the field of the Poke
     * @param pokePosition  position of the Poke in the trainer's Poke list
     */
    public void setPokeToSwitchIn(long trainerId, int fieldPosition, int pokePosition) {
        if (trainerMap.containsKey(trainerId)) {
            SwitchResult result = pokeBattle.setPokeToSwitchIn(trainerId, fieldPosition, pokePosition);

            if (result.canSwitch()) {
                view.sendUserMessage(trainerId, "Pokemon set to switch");
                doBattlePhase();
            } else {
                view.sendUserMessage(trainerId, "Pokemon unable to switch");
            }

            if (result.getPhase() == BattlePhase.WAITING) {
                stopSwitchTimer();
                startActionTimer();
            }
        }
    }

    /**
     * Attempts to set a move for the given Poke
     * Sends a message to the user of the resulting success
     *
     * @param trainerId     id of the trainer
     * @param fieldPosition position on the field of the Poke
     * @param movePosition  position of the move in the Poke move set
     */
    public void setMoveToUse(long trainerId, int fieldPosition, int movePosition) {
        if (trainerMap.containsKey(trainerId)) {
            boolean canUseMove = pokeBattle.setMoveToUse(trainerId, fieldPosition, movePosition);
            if (canUseMove) {
                view.sendUserMessage(trainerId, "Pokemon can use move");
                doBattlePhase();
            } else {
                view.sendUserMessage(trainerId, "Pokemon unable to use move");
            }
        }
    }

    /**
     * Does the battle phase and calls timers depending on the state of the resulting battle phase
     */
    private void doBattlePhase() {
        if (pokeBattle.trainersReady()) {
            stopActionTimer();
            BattlePhase phase = pokeBattle.doBattlePhase();
            if (phase == BattlePhase.WAITING) {
                startActionTimer();
            } else if (phase == BattlePhase.SWITCHING) {
                startSwitchTimer();
            } else if (phase == BattlePhase.FINISHED) {
                this.battleOver = true;
                System.out.println("BATTLE IS OVER, LET OBJECTS BE GARBAGE COLLECTED");
            }
        }
    }

    public boolean isBattleOver() {
        return battleOver;
    }

    public void getPokeMoves(long userId, int fieldPosition) {
        if (trainerMap.containsKey(userId)) {
            String moveNames = pokeBattle.getMoves(userId, fieldPosition);
            System.out.println(moveNames);
        }
    }

    /**
     * Contains info about the trainer and their position on the field
     */
    private class TrainerInfo {
        private Trainer trainer;
        private int sidePosition;
        private int trainerPosition;

        public TrainerInfo(Trainer trainer, int sidePosition, int trainerPosition) {
            this.trainer = trainer;
            this.sidePosition = sidePosition;
            this.trainerPosition = trainerPosition;
        }
    }

    /**
     * When the timer runs out, any Poke that have not been given an action are forced
     * to do one by the program
     *
     * @return ScheduledFuture
     */
    @Nonnull
    private ScheduledFuture<?> timerToDoAction() {
        final Runnable timerFinished = () -> {
            stopActionTimer();
            System.out.println("Time Ran Out For Choosing An Action");
            pokeBattle.forceActionOnPoke();
            // Timer only runs when a user has not done a choice yet (move or switch out)
            // A move should be chosen for them to do here
            doBattlePhase();
        };

        return scheduler.schedule(timerFinished, 10, TimeUnit.SECONDS);
//        return scheduler.schedule(timerFinished, 50, TimeUnit.MILLISECONDS);
    }

    /**
     * Start timer for a trainer to do an action
     * When this timer runs out, the program will choose an action for any undecided Trainers
     */
    public void startActionTimer() {
        if (actionTimer == null) {
            pokeBattle.tellTrainersToDoAction();
            System.out.println("Started Action Timer");
            actionTimer = timerToDoAction();
        }
    }

    /**
     * Stop the timer before the program chooses an action for the user
     * Sets the Scheduler to null and cancels it if it is still running
     */
    public void stopActionTimer() {
        if (actionTimer != null) {
            System.out.println("Stopping Action Timer");
            actionTimer.cancel(false);
            actionTimer = null;
        }
    }

    /**
     * When the timer runs out, any Poke that are still fainted are forced to switch out by the program
     *
     * @return ScheduledFuture
     */
    @Nonnull
    private ScheduledFuture<?> timerToSwitch() {
        final Runnable timerFinished = () -> {
            stopSwitchTimer();
            System.out.println("Time Ran Out For Switching");
            pokeBattle.forceSwitchFaintedPoke();
            startActionTimer();
        };

        return scheduler.schedule(timerFinished, 10, TimeUnit.SECONDS);
//        return scheduler.schedule(timerFinished, 50, TimeUnit.MILLISECONDS);
    }

    /**
     * Start the timer for a trainer to switch in a Poke
     * When the timer runs out, the program will choose a Poke to switch in for undecided trainers
     * This time should ONLY be used when switching out fainted Poke and not for choosing a move
     */
    public void startSwitchTimer() {
        if (switchTimer == null) {
            System.out.println("Started Switch Timer");
            switchTimer = timerToSwitch();
        }
    }

    /**
     * Stop the timer before the program chooses a Poke to switch in
     * Sets the Scheduler to null and cancels it if it is still running
     */
    public void stopSwitchTimer() {
        if (switchTimer != null) {
            System.out.println("Stopped Switch Timer");
            switchTimer.cancel(false);
            switchTimer = null;
        }
    }
}
