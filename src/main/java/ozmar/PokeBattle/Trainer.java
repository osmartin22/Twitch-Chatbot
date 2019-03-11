package ozmar.PokeBattle;

import java.util.List;

public class Trainer {
    private String trainerName;
    private List<Poke> pokemon;
    private Poke currentPoke;

    public Trainer(String trainerName, List<Poke> pokemon) {
        this.trainerName = trainerName;
        this.pokemon = pokemon;
        // TODO: MAKE USER CHOOSE THEIR STARTING POKEMON
    }
}
