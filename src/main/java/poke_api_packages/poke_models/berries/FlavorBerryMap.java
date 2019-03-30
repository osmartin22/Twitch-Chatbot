package poke_api_packages.poke_models.berries;

import com.google.gson.Gson;

public class FlavorBerryMap {
    // How powerful the referenced flavor is for this berry
    private int potency;

    // The berry with the referenced flavor
    private Berry berry;

    public int getPotency() {
        return potency;
    }

    public FlavorBerryMap setPotency(int potency) {
        this.potency = potency;
        return this;
    }

    public Berry getBerry() {
        return berry;
    }

    public FlavorBerryMap setBerry(Berry berry) {
        this.berry = berry;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}