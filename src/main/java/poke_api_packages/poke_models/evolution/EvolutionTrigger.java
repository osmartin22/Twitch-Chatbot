package poke_api_packages.poke_models.evolution;
/*
{
    "id": 1,
    "name": "level-up",
    "names": [{
        "name": "Level up",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "pokemon_species": [{
        "name": "ivysaur",
        "url": "http://pokeapi.co/api/v2/pokemon-species/2/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.Name;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.pokemon.PokemonSpecies;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class EvolutionTrigger extends NamedAPIResource {
    // The identifier for this evolution trigger resource
    private int id;

    // The name of this evolution trigger listed in different languages
    private List<Name> names;

    // A list of pokemon species that result from this evolution trigger
    private List<PokemonSpecies> pokemon_species;

    private static EvolutionTrigger get(String url) {
        String json = Information.fromInternet(url);
        EvolutionTrigger obj = new Gson().fromJson(json, EvolutionTrigger.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EvolutionTrigger getById(int id) {
        return get("https://pokeapi.co/api/v2/evolution-trigger/" + id);
    }

    public static EvolutionTrigger getByName(String name) {
        return get("https://pokeapi.co/api/v2/evolution-trigger/" + name);
    }

    public int getId() {
        return id;
    }

    public EvolutionTrigger setId(int id) {
        this.id = id;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public EvolutionTrigger setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemon_species;
    }

    public EvolutionTrigger setPokemonSpecies(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}