package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.pokemonBattle.BattlePhase;
import ozmar.pokemonBattle.PokeBattle;
import ozmar.pokemonBattle.PokeBattleViewInterface;
import ozmar.pokemonBattle.SwitchResult;
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

    // Objects to get information about the objects to be used in the battle
    private final PokemonDaoInterface pokemonDao;
    private final ConvertIntoPoke convert;
    private final GetMovesData movesData;

    private final Map<Long, TrainerInfo> map;
    private final List<List<Trainer>> trainerList;
    private PokeBattleViewInterface view;
    private PokeBattle pokeBattle;

    private boolean battleOver;

    public PokeController(@Nonnull PokemonDaoInterface pokemonDao) {
        this.pokemonDao = pokemonDao;
        this.convert = new ConvertIntoPoke();
        this.movesData = new GetMovesData();
        this.map = new HashMap<>();
        this.trainerList = new ArrayList<>();
        this.battleOver = false;
    }

    public void setView(@Nonnull PokeBattleViewInterface view) {
        this.view = view;
    }

    // Only set users once there are two users ready to battle
    public void setUsers(long userId1, @Nonnull String userName1, long userId2, @Nonnull String userName2) {
        Trainer trainer;
        trainer = createTrainer(userId1, userName1);
        this.map.put(userId1, new TrainerInfo(trainer, 0, 0));
        this.trainerList.add(new ArrayList<>(Collections.singleton(trainer)));

        trainer = createTrainer(userId2, userName2);
        this.map.put(userId2, new TrainerInfo(trainer, 1, 0));
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
        if (map.containsKey(userId)) {
            List<PokeMove> pokeMoves = movesData.convertNamesToMoves(moveList);
            TrainerInfo info = map.get(userId);
            List<PokeMove> currMoves = info.trainer.getPokeList().get(pokePosition).getMoveList();
            currMoves.clear();
            currMoves.addAll(pokeMoves);
        }
    }

    public void setPokeToSwitchIn(long userId, int fieldPosition, int pokePosition) {
        if (map.containsKey(userId)) {
            SwitchResult result = pokeBattle.setPokeToSwitchIn(userId, fieldPosition, pokePosition);

            if (result.canSwitch()) {
                view.sendUserMessage(userId, "Pokemon set to switch");
                doBattlePhase();
            } else {
                view.sendUserMessage(userId, "Pokemon unable to switch");
            }

            if (result.getPhase() == BattlePhase.WAITING) {
                stopSwitchTimer();
            }
        }
    }

    public void setMoveToUse(long userId, int fieldPosition, int movePosition) {
        if (map.containsKey(userId)) {
            boolean canUseMove = pokeBattle.setMoveToUse(userId, fieldPosition, movePosition);
            if (canUseMove) {
                view.sendUserMessage(userId, "Pokemon can use move");
                doBattlePhase();
            } else {
                view.sendUserMessage(userId, "Pokemon unable to use move");
            }
        }
    }

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

    @Nonnull
    private PokePosition getTargetPosition(@Nonnull TrainerInfo info) {
        PokePosition targetPosition;
        if (info.sidePosition == 0) {
            targetPosition = new PokePosition(1, 0, 0);
        } else {
            targetPosition = new PokePosition(0, 0, 0);
        }

        return targetPosition;
    }

    public String getPokeMoves(long userId, int fieldPosition) {
        if (map.containsKey(userId)) {
            return pokeBattle.getMoves(userId, fieldPosition);
        }

        return null;
    }

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


    @Nonnull
    private ScheduledFuture<?> timerToDoAction() {
        final Runnable timerFinished = () -> {
            System.out.println("Time Ran Out For Choosing An Action");
            pokeBattle.actionTimerRanOut();
            // Timer only runs when a user has not done a choice yet (move or switch out)
            // A move should be chosen for them to do here
            doBattlePhase();
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
//        return scheduler.schedule(timerFinished, 50, TimeUnit.MILLISECONDS);
    }

    public void startActionTimer() {
        if (actionTimer == null) {
            pokeBattle.tellTrainersToDoAction();
            System.out.println("Started Action Timer");
            this.actionTimer = timerToDoAction();
        }
    }

    public void stopActionTimer() {
        System.out.println("Stopping Action Timer");
        this.actionTimer.cancel(false);
        this.actionTimer = null;
    }

    /**
     * When the Switch Timer runs out, any Poke that are still fainted are forced to switch out
     *
     * @return ScheduledFuture
     */
    @Nonnull
    private ScheduledFuture<?> timerToSwitch() {
        final Runnable timerFinished = () -> {
            pokeBattle.switchTimerRanOut();
            startActionTimer();
        };

        return scheduler.schedule(timerFinished, 5, TimeUnit.SECONDS);
//        return scheduler.schedule(timerFinished, 50, TimeUnit.MILLISECONDS);
    }

    /**
     * Start the timer used for switching only Poke
     */
    public void startSwitchTimer() {
        if (actionTimer == null) {
            System.out.println("Started Switch Timer");
            switchTimer = timerToSwitch();
        }
    }

    /**
     * Stop the timer used for switching only Poke
     */
    public void stopSwitchTimer() {
        System.out.println("Stopped Switch Timer");
        this.switchTimer.cancel(false);
        this.switchTimer = null;
    }
}
