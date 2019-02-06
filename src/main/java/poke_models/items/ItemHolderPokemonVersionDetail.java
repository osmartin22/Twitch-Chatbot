package poke_models.items;

import com.google.gson.Gson;
import poke_models.games.Version;

public class ItemHolderPokemonVersionDetail {
    // How often this Pokémon holds this item in this version
    private String rarity;

    // The version that this item is held in by the Pokémon
    private Version version;

    public String getRarity() {
        return rarity;
    }

    public ItemHolderPokemonVersionDetail setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    public Version getVersion() {
        return version;
    }

    public ItemHolderPokemonVersionDetail setVersion(Version version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}