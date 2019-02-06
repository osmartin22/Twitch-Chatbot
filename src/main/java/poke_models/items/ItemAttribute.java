package poke_models.items;
/*
{
    "id": 1,
    "name": "countable",
    "descriptions": [{
        "description": "Has a count in the bag",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }],
    "items": [{
        "name": "master-ball",
        "url": "http://pokeapi.co/api/v2/item/1/"
    }],
    "names": [{
        "name": "Countable",
        "language": {
            "name": "en",
            "url": "http://pokeapi.co/api/v2/language/9/"
        }
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.Description;
import poke_models.common.Name;
import poke_models.common.NamedAPIResource;
import poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class ItemAttribute extends NamedAPIResource {
    // The identifier for this item attribute resource
    private int id;

    // A list of items that have this attribute
    private List<Item> items;

    // The name of this item attribute listed in different languages
    private List<Name> names;

    // The description of this item attribute listed in different languages
    private List<Description> descriptions;

    private static ItemAttribute get(String url) {
        String json = Information.fromInternet(url);
        ItemAttribute obj = new Gson().fromJson(json, ItemAttribute.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static ItemAttribute getById(int id) {
        return get("https://pokeapi.co/api/v2/item-attribute/" + id);
    }

    public static ItemAttribute getByName(String name) {
        return get("https://pokeapi.co/api/v2/item-attribute/" + name);
    }

    public int getId() {
        return id;
    }

    public ItemAttribute setId(int id) {
        this.id = id;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemAttribute setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public List<Name> getNames() {
        return names;
    }

    public ItemAttribute setNames(List<Name> names) {
        this.names = names;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public ItemAttribute setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}