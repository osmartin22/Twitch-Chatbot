package poke_api_packages.poke_models.common;

import com.google.gson.Gson;
import poke_api_packages.poke_models.games.Version;

import java.util.List;

public class VersionEncounterDetail {
    // The game version this encounter happens in
    private Version version;

    // The total percentage of all encounter potential
    private int max_chance;

    // A list of encounters and their specifics
    private List<Encounter> encounter_details;

    public Version getVersion() {
        return version;
    }

    public VersionEncounterDetail setVersion(Version version) {
        this.version = version;
        return this;
    }

    public int getMaxChance() {
        return max_chance;
    }

    public VersionEncounterDetail setMaxChance(int max_chance) {
        this.max_chance = max_chance;
        return this;
    }

    public List<Encounter> getEncounterDetails() {
        return encounter_details;
    }

    public VersionEncounterDetail setEncounterDetails(List<Encounter> encounter_details) {
        this.encounter_details = encounter_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}