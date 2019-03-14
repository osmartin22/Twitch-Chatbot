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
}
