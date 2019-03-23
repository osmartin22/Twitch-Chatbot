package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonTrainer.TrainerInBattle;
import reactor.util.annotation.NonNull;

public class PokeRules {

    public PokeRules() {

    }

    public boolean isAbleToDoMove(@NonNull PokeTrainerSide side, @NonNull PositionHelper positions) {
        TrainerInBattle trainerInBattle = side.getTrainerInBattle(positions);
        PokeInBattle pokeInBattle = trainerInBattle.getPokeInBattle(positions);
        PokeMove moveToUse = pokeInBattle.getPoke().getMoveList().get(positions.getMove());

        // Check for any effects that prevent the move being used

        return false;
    }

    public boolean isAbleToSwitch(@NonNull PokeTrainerSide side, @NonNull PositionHelper positions) {
        PokeInBattle pokeInBattle = side.getTrainerInBattle(positions).getPokeInBattle(positions);
        return false;
    }
}
