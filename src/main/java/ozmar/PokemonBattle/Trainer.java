package ozmar.PokemonBattle;

import java.util.List;

public class Trainer {
    private final String trainerName;
    private final List<Poke> pokemon;
    private Poke currentPoke;

    public Trainer(String trainerName, List<Poke> pokemon) {
        this.trainerName = trainerName;
        this.pokemon = pokemon;
        // TODO: MAKE USER CHOOSE THEIR STARTING POKEMON
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<Poke> getPokemon() {
        return pokemon;
    }

    public Poke getCurrentPoke() {
        return currentPoke;
    }

    public void setCurrentPoke(Poke currentPoke) {
        this.currentPoke = currentPoke;
    }

    // Should get the pokemon's info, like it's health, nature, stat stages, so the user can see their Pokemon
    public void getCurrentPokeInfo() {
        // TODO:
    }

    public void getCurrPokeMoves() {
        // TODO:
    }

    // Should get the info of Pokemon not in the battle, along with the moves possibly
    public void getOtherPokeInfo() {
        // TODO:
    }

    // Switches the current Pokemon with the selected one
    public void switchPoke() {
        // TODO:
    }

    // Makes the Pokemon do the selected move (check if the Pokemon knows the move first
    public void doMove() {
        // TODO:
    }
}
