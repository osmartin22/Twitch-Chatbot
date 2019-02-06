package poke_models.common;

import com.google.gson.Gson;
import poke_models.encounters.EncounterConditionValue;
import poke_models.encounters.EncounterMethod;

import java.util.List;

public class Encounter {
    // The lowest level the Pokémon could be encountered at
    private int min_level;

    // The highest level the Pokémon could be encountered at
    private int max_level;

    // A list of condition values that must be in effect for this encounter to occur
    private List<EncounterConditionValue> condition_values;

    // percent chance that this encounter will occur
    private int chance;

    // The method by which this encounter happens
    private EncounterMethod method;

    public int getMinLevel() {
        return min_level;
    }

    public Encounter setMinLevel(int min_level) {
        this.min_level = min_level;
        return this;
    }

    public int getMaxLevel() {
        return max_level;
    }

    public Encounter setMaxLevel(int max_level) {
        this.max_level = max_level;
        return this;
    }

    public List<EncounterConditionValue> getConditionValues() {
        return condition_values;
    }

    public Encounter setConditionValues(List<EncounterConditionValue> condition_values) {
        this.condition_values = condition_values;
        return this;
    }

    public int getChance() {
        return chance;
    }

    public Encounter setChance(int chance) {
        this.chance = chance;
        return this;
    }

    public EncounterMethod getMethod() {
        return method;
    }

    public Encounter setMethod(EncounterMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}