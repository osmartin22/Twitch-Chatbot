package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;
import poke_api_packages.poke_models.common.VersionEncounterDetail;
import poke_api_packages.poke_models.locations.LocationArea;

import java.util.List;

public class LocationAreaEncounter {
    // The location area the referenced Pokémon can be encountered in
    private LocationArea location_area;

    // A list of versions and encounters with the referenced Pokémon that might happen
    private List<VersionEncounterDetail> version_details;

    public LocationArea getLocationArea() {
        return location_area;
    }

    public LocationAreaEncounter setLocationArea(LocationArea location_area) {
        this.location_area = location_area;
        return this;
    }

    public List<VersionEncounterDetail> getVersionDetails() {
        return version_details;
    }

    public LocationAreaEncounter setVersionDetails(List<VersionEncounterDetail> version_details) {
        this.version_details = version_details;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}