package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonField.PokemonTerrain.PokeTerrain;
import ozmar.pokemonBattle.pokemonField.PokemonWeather.PokeWeather;

public class PokeField {
    private final PokeTerrain terrain;
    private final PokeWeather weather;

    // Hold field effects

    public PokeField() {
        this.terrain = new PokeTerrain();
        this.weather = new PokeWeather();
    }

    public PokeTerrain getTerrain() {
        return terrain;
    }

    public PokeWeather getWeather() {
        return weather;
    }
}
