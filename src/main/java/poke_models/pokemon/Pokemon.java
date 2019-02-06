package poke_models.pokemon;
/*
{
    "id": 12,
    "name": "butterfree",
    "base_experience": 178,
    "height": 11,
    "is_default": true,
    "order": 16,
    "weight": 320,
    "abilities": [{
        "is_hidden": true,
        "slot": 3,
        "ability": {
            "name": "tinted-lens",
            "url": "http://pokeapi.co/api/v2/ability/110/"
        }
    }],
    "forms": [{
        "name": "butterfree",
        "url": "http://pokeapi.co/api/v2/pokemon-form/12/"
    }],
    "game_indices": [{
        "game_index": 12,
        "version": {
            "name": "white-2",
            "url": "http://pokeapi.co/api/v2/version/22/"
        }
    }],
    "held_items": [{
        "item": {
            "name": "silver-powder",
            "url": "http://pokeapi.co/api/v2/item/199/"
        },
        "version_details": [{
            "rarity": 5,
            "version": {
                "name": "y",
                "url": "http://pokeapi.co/api/v2/version/24/"
            }
        }]
    }],
    "location_area_encounters": [{
        "location_area": {
            "name": "kanto-route-2-south-towards-viridian-city",
            "url": "http://pokeapi.co/api/v2/location-area/296/"
        },
        "version_details": [{
            "max_chance": 10,
            "encounter_details": [{
                "min_level": 7,
                "max_level": 7,
                "condition_values": [{
                    "name": "time-morning",
                    "url": "http://pokeapi.co/api/v2/encounter-condition-value/3/"
                }],
                "chance": 5,
                "method": {
                    "name": "walk",
                    "url": "http://pokeapi.co/api/v2/encounter-method/1/"
                }
            }],
            "version": {
                "name": "heartgold",
                "url": "http://pokeapi.co/api/v2/version/15/"
            }
        }]
    }],
    "moves": [{
        "move": {
            "name": "flash",
            "url": "http://pokeapi.co/api/v2/move/148/"
        },
        "version_group_details": [{
            "level_learned_at": 0,
            "version_group": {
                "name": "x-y",
                "url": "http://pokeapi.co/api/v2/version-group/15/"
            },
            "move_learn_method": {
                "name": "machine",
                "url": "http://pokeapi.co/api/v2/move-learn-method/4/"
            }
        }]
    }],
    "species": {
        "name": "butterfree",
        "url": "http://pokeapi.co/api/v2/pokemon-species/12/"
    },
    "sprites": {
        "back_female": "http://pokeapi.co/media/sprites/pokemon/back/female/12.png",
        "back_shiny_female": "http://pokeapi.co/media/sprites/pokemon/back/shiny/female/12.png",
        "back_default": "http://pokeapi.co/media/sprites/pokemon/back/12.png",
        "front_female": "http://pokeapi.co/media/sprites/pokemon/female/12.png",
        "front_shiny_female": "http://pokeapi.co/media/sprites/pokemon/shiny/female/12.png",
        "back_shiny": "http://pokeapi.co/media/sprites/pokemon/back/shiny/12.png",
        "front_default": "http://pokeapi.co/media/sprites/pokemon/12.png",
        "front_shiny": "http://pokeapi.co/media/sprites/pokemon/shiny/12.png"
    },
    "stats": [{
        "base_stat": 70,
        "effort": 0,
        "stat": {
            "name": "speed",
            "url": "http://pokeapi.co/api/v2/stat/6/"
        }
    }],
    "types": [{
        "slot": 2,
        "type": {
            "name": "flying",
            "url": "http://pokeapi.co/api/v2/type/3/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.NamedAPIResource;
import poke_models.common.VersionGameIndex;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Pokemon extends NamedAPIResource {
    // The identifier for this Pokémon resource
    private int id;

    // The base experience gained for defeating this Pokémon
    private int base_experience;

    // The height of this Pokémon
    private int height;

    // Set for exactly one Pokémon used as the default for each species
    private boolean is_default;

    // Order for sorting. Almost national order, except families are grouped together.
    private int order;

    // The weight of this Pokémon
    private int weight;

    // A list of abilities this Pokémon could potentially have
    private List<PokemonAbility> abilities;

    // A list of forms this Pokémon can take on
    private List<PokemonForm> forms;

    // A list of game indices relevent to Pokémon item by generation
    private List<VersionGameIndex> game_indices;

    // A list of items this Pokémon may be holding when encountered
    private List<PokemonHeldItem> held_items;

    // A link to a list of location areas as well as encounter details pertaining to specific versions
    private String location_area_encounters;

    // A list of moves along with learn methods and level details pertaining to specific version groups
    private List<PokemonMove> moves;

    // A set of sprites used to depict this Pokémon in the game
    private PokemonSprites sprites;

    // The species this Pokémon belongs to
    private PokemonSpecies species;

    // A list of base stat values for this Pokémon
    private List<PokemonStat> stats;

    // A list of details showing types this Pokémon has
    private List<PokemonType> types;

    private static Pokemon get(String url) {
        String json = Information.fromInternet(url);
        Pokemon obj = new Gson().fromJson(json, Pokemon.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit="
                + Math.abs(limit) + "&offset=" + Math.abs(offset));

        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Pokemon getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/pokemon/" + id);
    }

    public static Pokemon getByName(String name) {
        return get("https://pokeapi.co/poke_api/v2/pokemon/" + name);
    }

    public int getId() {
        return id;
    }

    public Pokemon setId(int id) {
        this.id = id;
        return this;
    }

    public int getBaseExperience() {
        return base_experience;
    }

    public Pokemon setBaseExperience(int base_experience) {
        this.base_experience = base_experience;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Pokemon setHeight(int height) {
        this.height = height;
        return this;
    }

    public boolean isDefault() {
        return is_default;
    }

    public Pokemon setIsDefault(boolean is_default) {
        this.is_default = is_default;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public Pokemon setOrder(int order) {
        this.order = order;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Pokemon setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public Pokemon setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
        return this;
    }

    public List<PokemonForm> getForms() {
        return forms;
    }

    public Pokemon setForms(List<PokemonForm> forms) {
        this.forms = forms;
        return this;
    }

    public List<VersionGameIndex> getGameIndices() {
        return game_indices;
    }

    public Pokemon setGameIndices(List<VersionGameIndex> game_indices) {
        this.game_indices = game_indices;
        return this;
    }

    public List<PokemonHeldItem> getHeldItems() {
        return held_items;
    }

    public Pokemon setHeldItems(List<PokemonHeldItem> held_items) {
        this.held_items = held_items;
        return this;
    }

    public String getLocationAreaEncounters() {
        return location_area_encounters;
    }

    public Pokemon setLocationAreaEncounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
        return this;
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public Pokemon setMoves(List<PokemonMove> moves) {
        this.moves = moves;
        return this;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public Pokemon setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
        return this;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public Pokemon setSpecies(PokemonSpecies species) {
        this.species = species;
        return this;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public Pokemon setStats(List<PokemonStat> stats) {
        this.stats = stats;
        return this;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public Pokemon setTypes(List<PokemonType> types) {
        this.types = types;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}