package poke_api_packages.poke_models.locations;

import com.google.gson.Gson;
import poke_api_packages.poke_models.games.Version;

public class EncounterVersionDetails {
    // The chance of an encounter to occur.
    private int rate;

    // The version of the game in which the encounter can occur with the given chance.
    private Version version;

    public int getRate() {
        return rate;
    }

    public EncounterVersionDetails setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public Version getVersion() {
        return version;
    }

    public EncounterVersionDetails setVersion(Version version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}