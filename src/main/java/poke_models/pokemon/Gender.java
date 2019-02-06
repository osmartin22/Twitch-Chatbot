package poke_models.pokemon;
/*
{
    "id": 1,
    "name": "female",
    "pokemon_species_details": [{
        "rate": 1,
        "pokemon_species": {
            "name": "bulbasaur",
            "url": "http://pokeapi.co/api/v2/pokemon-species/1/"
        }
    }],
    "required_for_evolution": [{
        "name": "wormadam",
        "url": "http://pokeapi.co/api/v2/pokemon-species/413/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Gender {
    // The identifier for this gender resource
    private int id;

    // The name for this gender resource
    private String name;

    // A list of Pokémon species that can be this gender and how likely it is that they will be
    private List<PokemonSpeciesGender> pokemon_species_details;

    // A list of Pokémon species that required this gender in order for a Pokémon to evolve into them
    private List<PokemonSpecies> required_for_evolution;

    private static Gender get(String url) {
        String json = Information.fromInternet(url);
        Gender obj = new Gson().fromJson(json, Gender.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Gender getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/gender/" + id);
    }

    public static Gender getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/gender/" + name);
    }

    public int getId() {
        return id;
    }

    public Gender setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Gender setName(String name) {
        this.name = name;
        return this;
    }

    public List<PokemonSpeciesGender> getPokemonSpeciesDetails() {
        return pokemon_species_details;
    }

    public Gender setPokemonSpeciesDetails(List<PokemonSpeciesGender> pokemon_species_details) {
        this.pokemon_species_details = pokemon_species_details;
        return this;
    }

    public List<PokemonSpecies> getRequiredForEvolution() {
        return required_for_evolution;
    }

    public Gender setRequiredForEvolution(List<PokemonSpecies> required_for_evolution) {
        this.required_for_evolution = required_for_evolution;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}