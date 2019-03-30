package poke_api_packages.poke_models.items;

import com.google.gson.Gson;

public class ItemSprites {
    // The default depiction of this item
    @com.fasterxml.jackson.annotation.JsonProperty("default")
    private String default_val;

    public String getDefaultVal() {
        return default_val;
    }

    public ItemSprites setDefaultVal(String default_val) {
        this.default_val = default_val;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}