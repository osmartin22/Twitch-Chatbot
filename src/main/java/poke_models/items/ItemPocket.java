package poke_models.items;
/*
{
    "id": 1,
    "name": "misc",
    "categories": [{
        "name": "collectibles",
        "url": "http://pokeapi.co/api/v2/item-category/9/"
    }],
    "names": [{
        "name": "Items",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class ItemPocket extends NamedAPIResource {
    // The identifier for this item pocket resource
    private int id;

    // A list of item categories that are relevant to this item pocket
    private List<ItemCategory> categories;

    // The name of this item pocket listed in different languages
    private List<Name> names;

    private static ItemPocket get(String url) {
        String json = Information.fromInternet(url);
        ItemPocket obj = new Gson().fromJson(json, ItemPocket.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static ItemPocket getById(int id) {
        return get("https://pokeapi.co/api/v2/item-pocket/" + id);
    }

    public static ItemPocket getByName(String name) {
        return get("https://pokeapi.co/api/v2/item-pocket/" + name);
    }

    public int getId() {
        return id;
    }

    public ItemPocket setId(int id) {
        this.id = id;
        return this;
    }

    public List<ItemCategory> getCategories() {
        return categories;
    }

    public ItemPocket setCategories(List<ItemCategory> categories) {
        this.categories = categories;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public ItemPocket setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}