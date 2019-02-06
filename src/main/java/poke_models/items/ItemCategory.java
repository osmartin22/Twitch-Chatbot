package poke_models.items;
/*
{
    "id": 1,
    "name": "stat-boosts",
    "items": [{
        "name": "guard-spec",
        "url": "http://pokeapi.co/api/v2/item/55/"
    }],
    "names": [{
        "name": "Stat boosts",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "pocket": {
        "name": "battle",
        "url": "http://pokeapi.co/api/v2/item-pocket/7/"
    }
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class ItemCategory extends NamedAPIResource {
    // The identifier for this item category resource
    private int id;

    // A list of items that are a part of this category
    private List<Item> items;

    // The name of this item category listed in different languages
    private List<Name> names;

    // The pocket items in this category would be put in
    private ItemPocket pocket;

    private static ItemCategory get(String url) {
        String json = Information.fromInternet(url);
        ItemCategory obj = new Gson().fromJson(json, ItemCategory.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static ItemCategory getById(int id) {
        return get("https://pokeapi.co/api/v2/item-category/" + id);
    }

    public static ItemCategory getByName(String name) {
        return get("https://pokeapi.co/api/v2/item-category/" + name);
    }

    public int getId() {
        return id;
    }

    public ItemCategory setId(int id) {
        this.id = id;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemCategory setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public ItemCategory setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public ItemPocket getPocket() {
        return pocket;
    }

    public ItemCategory setPocket(ItemPocket pocket) {
        this.pocket = pocket;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}