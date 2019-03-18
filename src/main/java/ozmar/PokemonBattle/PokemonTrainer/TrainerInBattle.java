package ozmar.PokemonBattle.PokemonTrainer;

import ozmar.PokemonBattle.Poke;
import ozmar.PokemonBattle.PokeInBattle;
import ozmar.PokemonBattle.PokemonMoves.PokeMove;
import ozmar.PokemonBattle.TrainerChoice;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TrainerInBattle {
    private final Trainer trainer;
    private PokeInBattle pokeInBattle;
    private TrainerChoice trainerChoice;


    public TrainerInBattle(@Nonnull Trainer trainer, @Nonnull PokeInBattle pokeInBattle) {
        this.trainer = trainer;
        this.pokeInBattle = pokeInBattle;
        this.trainerChoice = TrainerChoice.CHOICE_WAITING;
    }

    // Should get the pokeList's info, like it's health, nature, stat stages, so the user can see their Pokemon
    public void getCurrentPokeInfo() {
        // TODO:
    }

    public List<PokeMove> getCurrPokeMoves() {
        // TODO:
        return new ArrayList<>();
    }

    // Should get the info of Pokemon not in the battle, along with the moves possibly
    public void getOtherPokeInfo() {
        // TODO:
    }

    // Switches the current Pokemon with the selected one
    public void switchPoke(Poke poke) {
        // TODO:
        trainerChoice = TrainerChoice.CHOICE_SWITCH;
    }

    // Makes the Pokemon do the selected move (check if the Pokemon knows the move first
    public void doMove() {
        // TODO:
        trainerChoice = TrainerChoice.CHOICE_MOVE;
    }
}
