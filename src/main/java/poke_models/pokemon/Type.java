package poke_models.pokemon;
/*
{
    "id": 5,
    "name": "ground",
    "damage_relations": {
        "no_damage_to": [{
            "name": "flying",
            "url": "http://pokeapi.co/api/v2/type/3/"
        }],
        "half_damage_to": [{
            "name": "bug",
            "url": "http://pokeapi.co/api/v2/type/7/"
        }],
        "double_damage_to": [{
            "name": "poison",
            "url": "http://pokeapi.co/api/v2/type/4/"
        }],
        "no_damage_from": [{
            "name": "electric",
            "url": "http://pokeapi.co/api/v2/type/13/"
        }],
        "half_damage_from": [{
            "name": "poison",
            "url": "http://pokeapi.co/api/v2/type/4/"
        }],
        "double_damage_from": [{
            "name": "water",
            "url": "http://pokeapi.co/api/v2/type/11/"
        }]
    },
    "game_indices": [{
        "game_index": 4,
        "generation": {
            "name": "generation-i",
            "url": "http://pokeapi.co/api/v2/generation/1/"
        }
    }],
    "generation": {
        "name": "generation-i",
        "url": "http://pokeapi.co/api/v2/generation/1/"
    },
    "move_damage_class": {
        "name": "physical",
        "url": "http://pokeapi.co/api/v2/move-damage-class/2/"
    },
    "names": [{
        "name": "ã˜ã‚ã‚“",
        "language": {
            "name": "ja",
            "url": "http://pokeapi.co/api/v2/language/1/"
        }
    }],
    "pokemon": [{
        "slot": 1,
        "pokemon": {
            "name": "sandshrew",
            "url": "http://pokeapi.co/api/v2/pokemon/27/"
        }
    }],
    "moves": [{
        "name": "sand-attack",
        "url": "http://pokeapi.co/api/v2/move/28/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.GenerationGameIndex;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.games.Generation;
import poke_models.moves.Move;
import poke_models.moves.MoveDamageClass;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Type extends NamedAPIResource {
    // The identifier for this type resource
    private int id;

    // A detail of how effective this type is toward others and vice versa
    private TypeRelations damage_relations;

    // A list of game indices relevent to this item by generation
    private List<GenerationGameIndex> game_indices;

    // The generation this type was introduced in
    private Generation generation;

    // The class of damage inflicted by this type
    private MoveDamageClass move_damage_class;

    // The name of this type listed in different languages
    private List<Name> names;

    // A list of details of Pokémon that have this type
    private List<TypePokemon> pokemon;

    // A list of moves that have this type
    private List<Move> moves;

    private static Type get(String url) {
        String json = Information.fromInternet(url);
        Type obj = new Gson().fromJson(json, Type.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Type getById(int id) {
        return get("https://pokeapi.co/api/v2/type/" + id);
    }

    public static Type getByName(String name) {
        return get("https://pokeapi.co/api/v2/type/" + name);
    }

    public int getId() {
        return id;
    }

    public Type setId(int id) {
        this.id = id;
        return this;
    }

    public TypeRelations getDamageRelations() {
        return damage_relations;
    }

    public Type setDamageRelations(TypeRelations damage_relations) {
        this.damage_relations = damage_relations;
        return this;
    }

    public List<GenerationGameIndex> getGameIndices() {
        return game_indices;
    }

    public Type setGameIndices(List<GenerationGameIndex> game_indices) {
        this.game_indices = game_indices;
        return this;
    }

    public Generation getGeneration() {
        return generation;
    }

    public Type setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    public MoveDamageClass getMoveDamageClass() {
        return move_damage_class;
    }

    public Type setMoveDamageClass(MoveDamageClass move_damage_class) {
        this.move_damage_class = move_damage_class;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public Type setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<TypePokemon> getPokemon() {
        return pokemon;
    }

    public Type setPokemon(List<TypePokemon> pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Type setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}