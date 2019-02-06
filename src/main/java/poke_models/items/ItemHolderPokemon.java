package poke_models.items;

import com.google.gson.Gson;

import java.util.List;

public class ItemHolderPokemon {
    // The Pokémon that holds this item
    private String pokemon;

    // The details for the version that this item is held in by the Pokémon
    private List<ItemHolderPokemonVersionDetail> version_details;

    public String getPokemon() {
        return pokemon;
    }

    public ItemHolderPokemon setPokemon(String pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    public List<ItemHolderPokemonVersionDetail> getVersionDetails() {
        return version_details;
    }

    public ItemHolderPokemon setVersionDetails(List<ItemHolderPokemonVersionDetail> version_details) {
        this.version_details = version_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}