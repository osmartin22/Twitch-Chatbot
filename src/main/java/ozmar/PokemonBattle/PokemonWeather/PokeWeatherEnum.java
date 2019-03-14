package ozmar.PokemonBattle.PokemonWeather;

public enum PokeWeatherEnum {
    CLEAR_SKIES(0),     // This weather is considered the "default" weather with no effects
    HARSH_SUNLIGHT(1),
    RAIN(2),
    SANDSTORM(3),
    HAIL(4),
    EXTREMELY_HARSH_SUNLIGHT(5),
    HEAVY_RAIN(6),
    MYSTERIOUS_AIR_CURRENT(7);

    // 5-7 are unique weather from Groudon, Kyogre, and Rayquaza(might implement later)
    private int id;

    PokeWeatherEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
