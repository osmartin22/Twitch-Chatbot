package poke_models.resource;
/*
{
    "count": 248,
    "next": "http://pokeapi.co/api/v2/ability/?limit=20&offset=20",
    "previous": null,
    "results": [{
        "name": "stench",
        "url": "http://pokeapi.co/api/v2/ability/1/"
    }]
}
*/

import com.google.gson.Gson;
import poke_api.Information;
import poke_models.common.NamedAPIResource;

import java.util.List;

public class NamedAPIResourceList {
    // The total number of resources available from this API
    private int count;

    // The URL for the next page in the list
    private String next;

    // The URL for the previous page in the list
    private boolean previous;

    // A list of named API resources
    private List<NamedAPIResource> results;

    private static NamedAPIResourceList get(String url) {
        String json = Information.fromInternet(url);
        NamedAPIResourceList obj = new Gson().fromJson(json, NamedAPIResourceList.class);
        return obj;
    }

    public static NamedAPIResourceList getList(int limit, int offset) {
        String json = Information.fromInternet("https://pokeapi.co/poke_api/v2/berry/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset));
        return (new Gson()).fromJson(json, NamedAPIResourceList.class);
    }

    public static NamedAPIResourceList getByEndpoint(String endpoint) {
        return get("https://pokeapi.co/poke_api/v2/" + endpoint);
    }

    public int getCount() {
        return count;
    }

    public NamedAPIResourceList setCount(int count) {
        this.count = count;
        return this;
    }

    public String getNext() {
        return next;
    }

    public NamedAPIResourceList setNext(String next) {
        this.next = next;
        return this;
    }

    public boolean previous() {
        return previous;
    }

    public NamedAPIResourceList setPrevious(boolean previous) {
        this.previous = previous;
        return this;
    }

    public List<NamedAPIResource> getResults() {
        return results;
    }

    public NamedAPIResourceList setResults(List<NamedAPIResource> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}