package poke_models.pokemon;

import com.google.gson.Gson;
import poke_models.locations.PalParkArea;

public class PalParkEncounterArea {
    // The base score given to the player when the referenced Pokémon is caught during a pal park run
    private int base_score;

    // The base rate for encountering the referenced Pokémon in this pal park area
    private int rate;

    // The pal park area where this encounter happens
    private PalParkArea area;

    public int getBaseScore() {
        return base_score;
    }

    public PalParkEncounterArea setBaseScore(int base_score) {
        this.base_score = base_score;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public PalParkEncounterArea setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public PalParkArea getArea() {
        return area;
    }

    public PalParkEncounterArea setArea(PalParkArea area) {
        this.area = area;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}