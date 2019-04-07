package ozmar.pokemonBattle.pokemonBattleHelpers;

import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.pokemonBattle.PokeBattle;
import ozmar.pokemonBattle.PokeBattleViewInterface;
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

// TODO: Make sure every Pokemon has at least 1 move
public class PokeController {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> fixedRateTimer;

    // Objects to get information about the objects to be used in the battle
    private final PokemonDaoInterface pokemonDao;
    private final ConvertIntoPoke convert;
    private final GetMovesData movesData;

    private final Map<Long, TrainerInfo> map;
    private final List<List<Trainer>> trainerList;
    private PokeBattleViewInterface view;
    private PokeBattle pokeBattle;

    public PokeController(@Nonnull PokemonDaoInterface pokemonDao) {
        this.pokemonDao = pokemonDao;
        this.convert = new ConvertIntoPoke();
        this.movesData = new GetMovesData();
        this.map = new HashMap<>();
        this.trainerList = new ArrayList<>();
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
        List<PokeMove> pokeMoves = movesData.convertNamesToMoves(moveList);
        TrainerInfo info = map.get(userId);
        List<PokeMove> currMoves = info.trainer.getPokeList().get(pokePosition).getMoveList();
        currMoves.clear();
        currMoves.addAll(pokeMoves);
    }

    public void setPokeToSwitchIn(long userId, int fieldPosition, int pokePosition) {
        boolean canSwitchPoke = pokeBattle.setPokeToSwitchIn(userId, fieldPosition, pokePosition);
        if (canSwitchPoke) {
            view.sendUserMessage(userId, "Pokemon set to switch");
            pokeBattle.doTrainerChoices();
        } else {
            view.sendUserMessage(userId, "Pokemon unable to switch");
        }
    }

    public void setMoveToUse(long userId, int fieldPosition, int movePosition) {
        boolean canUseMove = pokeBattle.setMoveToUse(userId, fieldPosition, movePosition);
        if (canUseMove) {
            view.sendUserMessage(userId, "Pokemon can use move");
            pokeBattle.doTrainerChoices();
        } else {
            view.sendUserMessage(userId, "Pokemon unable to use move");
        }
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
        return pokeBattle.getMoves(userId, fieldPosition);
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
}
