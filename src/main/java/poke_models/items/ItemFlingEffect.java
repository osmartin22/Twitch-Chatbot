package poke_models.items;
/*
{
    "id": 1,
    "name": "badly-poison",
    "effect_entries": [{
        "effect": "Badly poisons the target.",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "items": [{
        "name": "toxic-orb",
        "url": "http://pokeapi.co/api/v2/item/249/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Effect;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class ItemFlingEffect extends NamedAPIResource {
    // The identifier for this fling effect resource
    private int id;

    // The result of this fling effect listed in different languages
    private List<Effect> effect_entries;

    // A list of items that have this fling effect
    private List<Item> items;

    private static ItemFlingEffect get(String url) {
        String json = Information.fromInternet(url);
        ItemFlingEffect obj = new Gson().fromJson(json, ItemFlingEffect.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static ItemFlingEffect getById(int id) {
        return get("https://pokeapi.co/api/v2/item-fling-effect/" + id);
    }

    public static ItemFlingEffect getByName(String name) {
        return get("https://pokeapi.co/api/v2/item-fling-effect/" + name);
    }

    public int getId() {
        return id;
    }

    public ItemFlingEffect setId(int id) {
        this.id = id;
        return this;
    }

    public List<Effect> getEffectEntries() {
        return effect_entries;
    }

    public ItemFlingEffect setEffectEntries(List<Effect> effect_entries) {
        this.effect_entries = effect_entries;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemFlingEffect setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}