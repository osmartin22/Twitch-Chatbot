package poke_models.berries;

import com.google.gson.Gson;

public class BerryFlavorMap {
    // How powerful the referenced flavor is for this berry
    private int potency;

    // The referenced berry flavor
    private BerryFlavor flavor;

    public int getPotency() {
        return potency;
    }

    public BerryFlavorMap setPotency(int potency) {
        this.potency = potency;
        return this;
    }

    public BerryFlavor getFlavor() {
        return flavor;
    }

    public BerryFlavorMap setFlavor(BerryFlavor flavor) {
        this.flavor = flavor;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}