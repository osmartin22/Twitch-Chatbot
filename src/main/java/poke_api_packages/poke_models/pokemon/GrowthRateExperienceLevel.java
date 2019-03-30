package poke_api_packages.poke_models.pokemon;

import com.google.gson.Gson;

public class GrowthRateExperienceLevel {
    // The level gained
    private int level;

    // The amount of experience required to reach the referenced level
    private int experience;

    public int getLevel() {
        return level;
    }

    public GrowthRateExperienceLevel setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public GrowthRateExperienceLevel setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}