package ozmar.pokemonBattle.pokemonTrainer;

import ozmar.pokemonBattle.pokemon.Poke;

import java.util.List;

public class Trainer {
    private final long id;
    private final String trainerName;
    private final List<Poke> pokeList;

    private int faintedPokeCount;

    public Trainer(long id, String trainerName, List<Poke> pokeList) {
        this.id = id;
        this.trainerName = trainerName;
        this.pokeList = pokeList;
        this.faintedPokeCount = 0;
    }

    public long getId() {
        return id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<Poke> getPokeList() {
        return pokeList;
    }

    public int getFaintedPokeCount() {
        return faintedPokeCount;
    }

    public void incrementFaintedPoke() {
        this.faintedPokeCount++;
    }
}
