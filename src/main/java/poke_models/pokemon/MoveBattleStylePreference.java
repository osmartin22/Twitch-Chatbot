package poke_models.pokemon;

import com.google.gson.Gson;
import poke_models.moves.MoveBattleStyle;

public class MoveBattleStylePreference {
    // Chance of using the move, in percent, if HP is under one half
    private int low_hp_preference;

    // Chance of using the move, in percent, if HP is over one half
    private int high_hp_preference;

    // The move battle style
    private MoveBattleStyle move_battle_style;

    public int getLowHpPreference() {
        return low_hp_preference;
    }

    public MoveBattleStylePreference setLowHpPreference(int low_hp_preference) {
        this.low_hp_preference = low_hp_preference;
        return this;
    }

    public int getHighHpPreference() {
        return high_hp_preference;
    }

    public MoveBattleStylePreference setHighHpPreference(int high_hp_preference) {
        this.high_hp_preference = high_hp_preference;
        return this;
    }

    public MoveBattleStyle getMoveBattleStyle() {
        return move_battle_style;
    }

    public MoveBattleStylePreference setMoveBattleStyle(MoveBattleStyle move_battle_style) {
        this.move_battle_style = move_battle_style;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}