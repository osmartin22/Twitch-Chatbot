package poke_models.pokemon;

import com.google.gson.Gson;

public class PokemonFormSprites {
    // The default depiction of this Pokémon form from the front in battle
    private String front_default;

    // The shiny depiction of this Pokémon form from the front in battle
    private String front_shiny;

    // The default depiction of this Pokémon form from the back in battle
    private String back_default;

    // The shiny depiction of this Pokémon form from the back in battle
    private String back_shiny;

    public String getFrontDefault() {
        return front_default;
    }

    public PokemonFormSprites setFrontDefault(String front_default) {
        this.front_default = front_default;
        return this;
    }

    public String getFrontShiny() {
        return front_shiny;
    }

    public PokemonFormSprites setFrontShiny(String front_shiny) {
        this.front_shiny = front_shiny;
        return this;
    }

    public String getBackDefault() {
        return back_default;
    }

    public PokemonFormSprites setBackDefault(String back_default) {
        this.back_default = back_default;
        return this;
    }

    public String getBackShiny() {
        return back_shiny;
    }

    public PokemonFormSprites setBackShiny(String back_shiny) {
        this.back_shiny = back_shiny;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}