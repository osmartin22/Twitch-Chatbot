package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeField;
import ozmar.pokemonBattle.pokemonField.PokeSide;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.util.*;


public class PokeBattle {

    private final PokeField field;
    private final PokeBattleCalculator calculator;
    // Maybe a last used move and last pokemon that attacked for the entire battle

    private final Map<Trainer, TrainerInBattle> trainerInBattleMap;
    private final Map<TrainerInBattle, PokeSide> pokeSideMap;
    // Maybe create a Map<PokeSide, List<TrainerInBattle> if getting a certain side is necessary

    public PokeBattle(@NonNull List<List<Trainer>> trainerList) {
        this.field = new PokeField();
        this.calculator = new PokeBattleCalculator();
        trainerInBattleMap = new HashMap<>();
        pokeSideMap = new HashMap<>();
        initialize(trainerList);
    }

    public void initialize(@NonNull List<List<Trainer>> lists) {
        for (List<Trainer> trainers : lists) {
            PokeSide pokeSide = new PokeSide();
            for (Trainer trainer : trainers) {
                TrainerInBattle trainerInBattle = new TrainerInBattle(trainer);
                trainerInBattleMap.put(trainer, trainerInBattle);
                pokeSideMap.put(trainerInBattle, pokeSide);
            }
        }
    }

    public boolean setMoveToUse(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        TrainerInBattle trainerInBattle = trainerInBattleMap.get(trainer);
        boolean isAbleToDoMove = canTrainersPokeUseMove(trainerInBattle, positions);
        if (isAbleToDoMove) {
            trainerInBattle.setMoveToUse(positions);
        }
        return isAbleToDoMove;
    }

    private boolean canTrainersPokeUseMove(@Nonnull TrainerInBattle trainerInBattle, @NonNull PositionHelper positions) {
        boolean isAbleToDoMove = false;
        if (trainerInBattle.getTrainerChoice(positions) == TrainerChoice.CHOICE_WAITING) {
            isAbleToDoMove = trainerInBattle.isAbleToDoMove(positions);
        }
        return isAbleToDoMove;
    }

    public boolean setPokeToSwitchIn(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        TrainerInBattle trainerInBattle = trainerInBattleMap.get(trainer);
        boolean isAbleToSwitch = canTrainerSwitchPoke(trainerInBattle, positions);
        if (isAbleToSwitch) {
            isAbleToSwitch = trainerInBattle.setPokeToSwitchIn(positions);
        }

        return isAbleToSwitch;
    }

    private boolean canTrainerSwitchPoke(@Nonnull TrainerInBattle trainerInBattle, @NonNull PositionHelper positions) {
        boolean isAbleToSwitch = false;
        if (trainerInBattle.getTrainerChoice(positions) == TrainerChoice.CHOICE_WAITING) {
            isAbleToSwitch = trainerInBattle.isAbleToSwitchPoke(positions);
        }

        return isAbleToSwitch;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        return trainerInBattleMap.get(trainer).getPokeInBattle(positions);
    }

    @Nonnull
    public TrainerChoice getTrainerStatus(@Nonnull Trainer trainer, @NonNull PositionHelper positions) {
        return trainerInBattleMap.get(trainer).getTrainerChoice(positions);
    }

    public boolean trainersReady() {
        for (TrainerInBattle trainerInBattle : trainerInBattleMap.values()) {
            List<PokeInBattle> pokeInBattleList = trainerInBattle.getPokeInBattleList();
            for (PokeInBattle pokeInBattle : pokeInBattleList) {
                if (pokeInBattle.getTrainerChoice() == TrainerChoice.CHOICE_WAITING) {
                    return false;
                }
            }
        }

        return true;
    }


    // TODO: Check if one of the Pokemon will use pursuit before switching out
    //  Pursuit activates if the Poke manually switches out,
    //  or uses U-turn, Volt Switch, or Parting Shot and would attack second
    // NOTE* If both trainers switch out on the same turn, the faster pokemon switches out first
    public void doTrainerChoice() {

        // Below code only works for 1v1
        // Pursuit effect not coded yet
        List<PokeInBattle> pokesWithPursuit = new ArrayList<>();
        List<PokeInBattle> pokesSwitching = new ArrayList<>();
        List<PokeInBattle> pokesAttacking = new ArrayList<>();
        getChoicesInList(pokesWithPursuit, pokesSwitching, pokesAttacking);

        // TODO: Make pursuit attack first before any opponent pokemon are switching
        for (PokeInBattle p : pokesSwitching) {
            p.switchPoke();
            p.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        }


        // The below code only works for 1v1 battles
        PokeInBattle p1 = null;
        PokeInBattle p2 = null;
        if (pokesAttacking.size() > 0) {
            p1 = pokesAttacking.get(0);
            if (pokesAttacking.size() == 2) {
                p2 = pokesAttacking.get(1);
            }
        }

        int damageDone;
        if (p1 != null) {
            damageDone = doMove(p1, p2);
//            p2.getPoke().getPokeStats().updateCurrHp(damageDone);
            System.out.println(p1.getPoke().getName() + " DamageDone: " + damageDone);
        }

        if (p2 != null) {
            damageDone = doMove(p2, p1);
            System.out.println(p2.getPoke().getName() + " DamageDone : " + damageDone);
        }
    }

    public void tempDo() {
        List<TrainerInBattle> trainerInBattleList = new ArrayList<>(trainerInBattleMap.values());
        List<PokeInBattle> pokeInBattleList = new ArrayList<>();
        PositionHelper positionHelper = new PositionHelper();
        pokeInBattleList.add(trainerInBattleList.get(0).getPokeInBattle(positionHelper));
        pokeInBattleList.add(trainerInBattleList.get(1).getPokeInBattle(positionHelper));
        pokeInBattleList.sort(Comparator.comparingInt((PokeInBattle p)
                -> p.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD)).reversed());

        for (PokeInBattle pokeInBattle : pokeInBattleList) {
            if (pokeInBattle.getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {

            }
        }

    }

    private void getChoicesInList(@NonNull List<PokeInBattle> pokesWithPursuit, @NonNull List<PokeInBattle> pokesSwitching,
                                  @NonNull List<PokeInBattle> pokesAttacking) {
        for (TrainerInBattle trainerInBattle : trainerInBattleMap.values()) {
            List<PokeInBattle> pokeInBattleList = trainerInBattle.getPokeInBattleList();
            for (PokeInBattle pokeInBattle : pokeInBattleList) {

                if (pokeInBattle.getTrainerChoice() == TrainerChoice.CHOICE_SWITCH) {
                    pokesSwitching.add(pokeInBattle);
                } else if (pokeInBattle.getMoveToUse().getName().equals("Pursuit")) {
                    pokesWithPursuit.add(pokeInBattle);
                } else {
                    pokesAttacking.add(pokeInBattle);
                }
            }
        }

        pokesWithPursuit.sort(Comparator.comparingInt((PokeInBattle p)
                -> p.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD)).reversed());
        pokesSwitching.sort(Comparator.comparingInt((PokeInBattle p)
                -> p.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD)).reversed());
        pokesAttacking.sort(Comparator.comparingInt((PokeInBattle p)
                -> p.getPoke().getPokeStats().getPokeStatValue(PokeStat.SPD)).reversed());
    }

    private int doMove(@NonNull PokeInBattle attacker, @NonNull PokeInBattle target) {
        int damageDone = 0;
        if (attacker.getMoveToUse().getDamageClass() == PokeMoveDamageClass.STATUS) {
            damageDone = -1;
        } else {
            boolean willMoveHit = calculator.willMoveHit(attacker.getPokeStages().getStateStage(PokeStatStage.ACC_STAGE),
                    target.getPokeStages().getStateStage(PokeStatStage.EVA_STAGE), attacker.getMoveToUse());

            if (willMoveHit) {
                damageDone = calculator.calculateDamage(attacker, target, field.getWeather().getWeather());
            }
        }

        attacker.setTrainerChoice(TrainerChoice.CHOICE_WAITING);
        return damageDone;
    }

    private void doPostTurnEffects() {

    }
}
