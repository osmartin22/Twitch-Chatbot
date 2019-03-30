package poke_api_packages.poke_models.common;

import com.google.gson.Gson;
import poke_api_packages.poke_models.games.Generation;

public class GenerationGameIndex {
    // The internal id of an API resource within game data
    private int game_index;

    // The generation relevent to this game index
    private Generation generation;

    public int getGameIndex() {
        return game_index;
    }

    public GenerationGameIndex setGameIndex(int game_index) {
        this.game_index = game_index;
        return this;
    }

    public Generation getGeneration() {
        return generation;
    }

    public GenerationGameIndex setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}