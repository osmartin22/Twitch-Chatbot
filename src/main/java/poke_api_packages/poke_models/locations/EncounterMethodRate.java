package poke_api_packages.poke_models.locations;

import com.google.gson.Gson;
import poke_api_packages.poke_models.encounters.EncounterMethod;

import java.util.List;

public class EncounterMethodRate {
    // The method in which Pokémon may be encountered in an area.
    private EncounterMethod encounter_method;

    // The chance of the encounter to occur on a version of the game.
    private List<EncounterVersionDetails> version_details;

    public EncounterMethod getEncounterMethod() {
        return encounter_method;
    }

    public EncounterMethodRate setEncounterMethod(EncounterMethod encounter_method) {
        this.encounter_method = encounter_method;
        return this;
    }

    public List<EncounterVersionDetails> getVersionDetails() {
        return version_details;
    }

    public EncounterMethodRate setVersionDetails(List<EncounterVersionDetails> version_details) {
        this.version_details = version_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}