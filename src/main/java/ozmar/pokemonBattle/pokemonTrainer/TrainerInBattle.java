package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokePosition;
import ozmar.pokemonBattle.pokemonField.PokeSide;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TrainerInBattle {
    private final int trainerPosition;
    private final Trainer trainer;
    private final List<PokeInBattle> pokeInBattleList;
    private boolean hasMegaEvolved; // Only one mega evolution is allowed per trainer in a battle

    // THE TRAINER IN BATTLE MIGHT NOT NEED TO KNOW WHAT SIDE IT IS ON
    private final PokeSide side;

    public TrainerInBattle(@Nonnull PokeSide side, @Nonnull Trainer trainer, int trainerPosition, int pokesInBattle) {
        this.side = side;
        this.trainerPosition = trainerPosition;
        this.trainer = trainer;
        this.hasMegaEvolved = false;
        this.pokeInBattleList = new ArrayList<>(pokesInBattle);
        initialize(pokesInBattle);
    }

    private void initialize(int pokesInBattle) {
        for (int i = 0; i < pokesInBattle; i++) {
            pokeInBattleList.add(new PokeInBattle(trainer.getPokeList().get(0), side.getPosition(), trainerPosition, i));
        }
    }

    public PokeSide getSide() {
        return side;
    }

    public int getSidePosition() {
        return side.getPosition();
    }

    public int getTrainerPosition() {
        return trainerPosition;
    }

    @Nonnull
    public Trainer getTrainer() {
        return trainer;
    }

    @Nonnull
    public List<PokeInBattle> getPokeInBattleList() {
        return pokeInBattleList;
    }

    public boolean isHasMegaEvolved() {
        return hasMegaEvolved;
    }

    public void setHasMegaEvolved(boolean hasMegaEvolved) {
        this.hasMegaEvolved = hasMegaEvolved;
    }

    @Nonnull
    public PokeInBattle getPokeInBattle(int fieldPosition) {
        return pokeInBattleList.get(fieldPosition);
    }

    public boolean setPokeToSwitchIn(int fieldPosition, int pokePosition) {
        boolean canSwitchIn = false;
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        Poke currentPoke = pokeInBattle.getPoke();
        Poke pokeToSwitchIn = trainer.getPokeList().get(pokePosition);
        if (!pokeToSwitchIn.isFainted() && (currentPoke != pokeToSwitchIn)) {
            pokeInBattle.setPokeToSwitchIn(pokeToSwitchIn);
            canSwitchIn = true;
        }

        return canSwitchIn;
    }

    public void switchPoke(int fieldPosition) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        pokeInBattle.switchPoke();
    }

    public void setMoveToUse(int fieldPosition, int movePosition, @Nonnull PokePosition targetPosition) {
        PokeInBattle pokeInBattle = pokeInBattleList.get(fieldPosition);
        pokeInBattle.setMoveToUse(pokeInBattle.getPoke().getMoveList().get(movePosition), targetPosition);
    }
}
