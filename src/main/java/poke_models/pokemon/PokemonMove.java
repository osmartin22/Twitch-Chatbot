package poke_models.pokemon;

import com.google.gson.Gson;
import poke_models.moves.Move;

import java.util.List;

public class PokemonMove {
    // The move the Pokémon can learn
    private Move move;

    // The details of the version in which the Pokémon can learn the move
    private List<PokemonMoveVersion> version_group_details;

    public Move getMove() {
        return move;
    }

    public PokemonMove setMove(Move move) {
        this.move = move;
        return this;
    }

    public List<PokemonMoveVersion> getVersionGroupDetails() {
        return version_group_details;
    }

    public PokemonMove setVersionGroupDetails(List<PokemonMoveVersion> version_group_details) {
        this.version_group_details = version_group_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}