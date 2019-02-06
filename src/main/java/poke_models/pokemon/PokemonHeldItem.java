package poke_models.pokemon;

import com.google.gson.Gson;
import poke_models.items.Item;

import java.util.List;

public class PokemonHeldItem {
    // The item the referenced Pokémon holds
    private Item item;

    // The details of the different versions in which the item is held
    private List<PokemonHeldItemVersion> version_details;

    public Item getItem() {
        return item;
    }

    public PokemonHeldItem setItem(Item item) {
        this.item = item;
        return this;
    }

    public List<PokemonHeldItemVersion> getVersionDetails() {
        return version_details;
    }

    public PokemonHeldItem setVersionDetails(List<PokemonHeldItemVersion> version_details) {
        this.version_details = version_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}