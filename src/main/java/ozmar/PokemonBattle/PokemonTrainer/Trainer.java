package ozmar.PokemonBattle.PokemonTrainer;

import ozmar.PokemonBattle.Poke;

import java.util.List;

public class Trainer {
    private final String trainerName;
    private final List<Poke> pokeList;

    public Trainer(String trainerName, List<Poke> pokeList) {
        this.trainerName = trainerName;
        this.pokeList = pokeList;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<Poke> getPokeList() {
        return pokeList;
    }
}
