package poke_models.pokemon;
/*
{
    "id": 1,
    "name": "ball",
    "awesome_names": [
        {
            "awesome_name": "Pomaceous",
            "language": {
                "name": "en",
                "url": "http://pokeapi.co/api/v2/language/9/"
            }
        }
    ],
    "names": [
        {
            "name": "Ball",
            "language": {
                "name": "en",
                "url": "http://pokeapi.co/api/v2/language/9/"
            }
        }
    ],
    "pokemon_species": [
        {
            "name": "shellder",
            "url": "http://pokeapi.co/api/v2/pokemon-species/90/"
        }
    ]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class PokemonShape extends NamedAPIResource {
    // The identifier for this Pokémon shape resource
    private int id;

    // The "scientific" name of this Pokémon shape listed in different languages
    private List<AwesomeName> awesome_names;

    // The name of this Pokémon shape listed in different languages
    private List<Name> names;

    // A list of the Pokémon species that have this shape
    private List<PokemonSpecies> pokemon_species;

    private static PokemonShape get(String url) {
        String json = Information.fromInternet(url);
        PokemonShape obj = new Gson().fromJson(json, PokemonShape.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static PokemonShape getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/pokemon-shape/" + id);
    }

    public static PokemonShape getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/pokemon-shape/" + name);
    }

    public int getId() {
        return id;
    }

    public PokemonShape setId(int id) {
        this.id = id;
        return this;
    }

    public List<AwesomeName> getAwesomeNames() {
        return awesome_names;
    }

    public PokemonShape setAwesomeNames(List<AwesomeName> awesome_names) {
        this.awesome_names = awesome_names;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public PokemonShape setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public PokemonShape setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}