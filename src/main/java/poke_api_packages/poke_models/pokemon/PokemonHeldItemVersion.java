package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.games.Version;

public class PokemonHeldItemVersion {
    // The version in which the item is held
    private Version version;

    // How often the item is held
    private int rarity;

    public Version getVersion() {
        return version;
    }

    public PokemonHeldItemVersion setVersion(Version version) {
        this.version = version;
        return this;
    }

    public int getRarity() {
        return rarity;
    }

    public PokemonHeldItemVersion setRarity(int rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}