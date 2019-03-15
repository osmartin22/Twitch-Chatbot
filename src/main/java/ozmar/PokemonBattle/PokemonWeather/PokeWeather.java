package ozmar.PokemonBattle.PokemonWeather;

public class PokeWeather {

    private PokeWeatherEnum weather;
    private int turnsRemaining; // TODO: Not sure if the turn the move is used is counted as a turn

    public PokeWeather(PokeWeatherEnum weather, int turnsRemaining) {
        this.weather = weather;
        this.turnsRemaining = turnsRemaining;
    }

    public PokeWeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(PokeWeatherEnum weather) {
        this.weather = weather;
    }

    public void setTurnsRemaining(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public void decrementTurn() {
        this.turnsRemaining--;
    }


    /*
    HARSH SUNLIGHT
        Fire type moves 50% power increase
        Water type moves 50% power decrease
        Solar Beam and Solar Blade are used instantly

        Makes Growth(Move) to raise Attack and Special Attack 2 stages each
        Activates the following Abilities: Chlorophyll, Dry Skin, Flower Gift, Forecast, Leaf Guard, Solar Power
        Changes Weather Ball to a Fire-type move and doubles its power
        Prevents Pokemon from being frozen
        Causes Moonlight, Synthesis, and Morning Sun to recover ⅔ of max HP
        Lowers accuracy of Thunder and Hurricane to 50%
     */

    /*
    RAIN
        Fire type moves 50% power decrease
        Water type moves 50% power increase
        Allows Thunder and Hurricane to bypass accuracy check
        Activates the following Abilities: Dry Skin, Forecast, Hydration, Rain Dish, Swift Swim
        Changes Weather Ball to a Water-type move and doubles its power
        Halves the power of Solar Beam and Solar Blade
        Causes Moonlight, Synthesis, and Morning Sun to recover ¼ of max HP
     */

    /*
    SANDSTORM
        At the end of each turn, damages each Pokemon for 1/16 of its max HP
        unless it is a Rock, Steel, or Ground Type or has one of the Abilities  Sand Force, Sand Rush, Sand Veil, Magic Guard, or Overcoat
        Activates the following Abilities: Sand Force, Sand Rush, Sand Veil
        Raises the Special Defense of all Rock-type Pokemon by 50%.
        Changes Weather Ball to a Rock-type move and doubles its power
        Halves the power of Solar Beam and Solar Blade
        Causes Shore Up to recover ⅔ of max HP instead of ½.
        Causes Moonlight, Synthesis, and Morning Sun to recover ¼ of max HP
     */

    /*
    HAIL
        At the end of each turn, damages each Pokemon for 1/16 of its max HP
        unless it is Ice Type or has one of the Abilities Forecast, Ice Body, Snow Cloak, and Slush Rush
        Changes Weather Ball to an Ice-type move and doubles its power
        Allows Blizzard to bypass accuracy check
        Halves the power of Solar Beam and Solar Blade
        Causes Moonlight, Synthesis, and Morning Sun to recover ¼ of max HP
        Allows Aurora Veil to be used, though the effect lingers even after Hail ends
     */

    /*
    FOG
        Multiplies accuracy of all Pokemon battling by 60%
        Doubles the power of Weather Ball (but does not change its type)
        Causes Solar Beam to have its power halved
        Causes Moonlight, Synthesis, and Morning Sun to only recover ¼ of max HP
     */


    // UNIQUE WEATHER TYPES BELOW ARE FROM GROUDON, KYOGRE, AND RAYQUAZA
    /*
    EXTREMELY HARSH SUNLIGHT
        Same as above, with some differences
        Water type damage moves always fail
        If a frozen Pokemon attempts to use Scald or Steam Eruption, it will still thaw itself before the move fails
        Causes the moves Sunny Day, Rain Dance, Sandstorm, and Hail to fail if used. This cannot be subverted even with Cloud Nine or Air Lock
        If a Pokemon affected by Powder uses Sunny Day, it will take damage from Powder
        Causes the Abilities Drought, Drizzle, Sand Stream, and Snow Warning to fail. This cannot be subverted even with Cloud Nine or Air Lock
     */

    /*
    HEAVY RAIN
        Same as above, with some differences
        Fire type damage moves always fail
        If a Pokemon affected by Powder uses a Fire-type move, it will take damage from Powder
        If a frozen Pokemon attempts to use a Fire type move that would thaw it out, it will still thaw itself before the move fails
        Causes the moves Sunny Day, Rain Dance, Sandstorm, and Hail to fail if used. This cannot be subverted even with Cloud Nine or Air Lock
        If a Pokemon affected by Powder uses Sunny Day, it will take damage from Powder
        Causes the Abilities Drought, Drizzle, Sand Stream, and Snow Warning to fail. This cannot be subverted even with Cloud Nine or Air Lock
     */

    /*
    MYSTERIOUS AIR CURRENT
        Moves super effective against flying types are now normally effective, Electric, Ice, and Rock type
        If the move is not effective against the Pokemon's other type, it no longer counts as super effective
        This has no effect on Stealth Rock and Anticipation
        Causes the moves Sunny Day, Rain Dance, Sandstorm, and Hail to fail if used. This cannot be subverted even with Cloud Nine or Air Lock
        Causes the Abilities Drought, Drizzle, Sand Stream, and Snow Warning to fail. This cannot be subverted even with Cloud Nine or Air Lock
        Weather Ball remains a Normal-type move
     */
}
