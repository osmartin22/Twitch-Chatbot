package poke_api_packages.poke_models.berries;
/*
{
    "id": 1,
    "name": "cheri",
    "growth_time": 3,
    "max_harvest": 5,
    "natural_gift_power": 60,
    "size": 20,
    "smoothness": 25,
    "soil_dryness": 15,
    "firmness": {
        "name": "soft",
        "url": "http://pokeapi.co/api/v2/berry-firmness/2/"
    },
    "flavors": [{
        "potency": 10,
        "flavor": {
            "name": "spicy",
            "url": "http://pokeapi.co/api/v2/berry-flavor/1/"
        }
    }],
    "item": {
        "name": "cheri-berry",
        "url": "http://pokeapi.co/api/v2/item/126/"
    },
    "natural_gift_type": {
        "name": "fire",
        "url": "http://pokeapi.co/api/v2/type/10/"
    }
}
*/

import com.google.gson.Gson;
import poke_api_packages.poke_api.Information;
import poke_api_packages.poke_models.common.NamedAPIResource;
import poke_api_packages.poke_models.items.Item;
import poke_api_packages.poke_models.pokemon.Type;
import poke_api_packages.poke_models.resource.NamedAPIResourceList;

import java.util.List;

public class Berry extends NamedAPIResource {
    // The identifier for this berry resource
    private int id;

    // Time it takes the tree to grow one stage, in hours. Berry trees go through four of these growth stages before they can be picked.
    private int growth_time;

    // The maximum number of these berries that can grow on one tree in Generation IV
    private int max_harvest;

    // The power of the move "Natural Gift" when used with this Berry
    private int natural_gift_power;

    // The size of this Berry, in millimeters
    private int size;

    // The smoothness of this Berry, used in making Pokéblocks or Poffins
    private int smoothness;

    // The speed at which this Berry dries out the soil as it grows. A higher rate means the soil dries more quickly.
    private int soil_dryness;

    // The firmness of this berry, used in making Pokéblocks or Poffins
    private BerryFirmness firmness;

    // A list of references to each flavor a berry can have and the potency of each of those flavors in regard to this berry
    private List<BerryFlavorMap> flavors;

    // Berries are actually items. This is a reference to the item specific data for this berry.
    private Item item;

    // The type inherited by "Natural Gift" when used with this Berry
    private Type natural_gift_type;

    private static Berry get(String url) {
        String json = Information.fromInternet(url);
        Berry obj = new Gson().fromJson(json, Berry.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static Berry getById(int id) {
        return get("https://pokeapi.co/api/v2/berry/" + id);
    }

    public static Berry getByName(String name) {
        return get("https://pokeapi.co/api/v2/berry/" + name);
    }

    public int getId() {
        return id;
    }

    public Berry setId(int id) {
        this.id = id;
        return this;
    }

    public int getGrowthTime() {
        return growth_time;
    }

    public Berry setGrowthTime(int growth_time) {
        this.growth_time = growth_time;
        return this;
    }

    public int getMaxHarvest() {
        return max_harvest;
    }

    public Berry setMaxHarvest(int max_harvest) {
        this.max_harvest = max_harvest;
        return this;
    }

    public int getNaturalGiftPower() {
        return natural_gift_power;
    }

    public Berry setNaturalGiftPower(int natural_gift_power) {
        this.natural_gift_power = natural_gift_power;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Berry setSize(int size) {
        this.size = size;
        return this;
    }

    public int getSmoothness() {
        return smoothness;
    }

    public Berry setSmoothness(int smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    public int getSoilDryness() {
        return soil_dryness;
    }

    public Berry setSoilDryness(int soil_dryness) {
        this.soil_dryness = soil_dryness;
        return this;
    }

    public BerryFirmness getFirmness() {
        return firmness;
    }

    public Berry setFirmness(BerryFirmness firmness) {
        this.firmness = firmness;
        return this;
    }

    public List<BerryFlavorMap> getFlavors() {
        return flavors;
    }

    public Berry setFlavors(List<BerryFlavorMap> flavors) {
        this.flavors = flavors;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Berry setItem(Item item) {
        this.item = item;
        return this;
    }

    public Type getNaturalGiftType() {
        return natural_gift_type;
    }

    public Berry setNaturalGiftType(Type natural_gift_type) {
        this.natural_gift_type = natural_gift_type;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}