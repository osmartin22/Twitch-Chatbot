package poke_models.evolution;
/*
{
    "id": 7,
    "baby_trigger_item": null,
    "chain": {
        "is_baby": false,
        "species": {
            "name": "rattata",
            "url": "http://pokeapi.co/api/v2/pokemon-species/19/"
        },
        "evolution_details": null,
        "evolves_to": [{
            "is_baby": false,
            "species": {
                "name": "raticate",
                "url": "http://pokeapi.co/api/v2/pokemon-species/20/"
            },
            "evolution_details": {
                "item": null,
                "trigger": {
                    "name": "level-up",
                    "url": "http://pokeapi.co/api/v2/evolution-trigger/1/"
                },
                "gender": null,
                "held_item": null,
                "known_move": null,
                "known_move_type": null,
                "location": null,
                "min_level": 20,
                "min_happiness": null,
                "min_beauty": null,
                "min_affection": null,
                "needs_overworld_rain": false,
                "party_species": null,
                "party_type": null,
                "relative_physical_stats": null,
                "time_of_day": "",
                "trade_species": null,
                "turn_upside_down": false
            },
            "evolves_to": []
        }]
    }
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.APIResource;
import poke_models.items.Item;
import poke_models.resource.NamedAPIResourceList;

public class EvolutionChain extends APIResource {
    // The identifier for this evolution chain resource
    private int id;

    // The item that a Pokémon would be holding when mating that would trigger the egg hatching a baby Pokémon rather than a basic Pokémon
    private Item baby_trigger_item;

    // The base chain link object. Each link contains evolution details for a Pokémon in the chain. Each link references the next Pokémon in the natural evolution order.
    private ChainLink chain;

    private static EvolutionChain get(String url) {
        String json = Information.fromInternet(url);
        EvolutionChain obj = new Gson().fromJson(json, EvolutionChain.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static EvolutionChain getById(int id) {
        return get("https://pokeapi.co/poke_api/v2/evolution-chain/" + id);
    }

    public int getId() {
        return id;
    }

    public EvolutionChain setId(int id) {
        this.id = id;
        return this;
    }

    public Item getBabyTriggerItem() {
        return baby_trigger_item;
    }

    public EvolutionChain setBabyTriggerItem(Item baby_trigger_item) {
        this.baby_trigger_item = baby_trigger_item;
        return this;
    }

    public ChainLink getChain() {
        return chain;
    }

    public EvolutionChain setChain(ChainLink chain) {
        this.chain = chain;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}