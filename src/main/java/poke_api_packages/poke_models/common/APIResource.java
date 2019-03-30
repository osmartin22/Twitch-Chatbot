package poke_api_packages.poke_models.common;

import com.google.gson.Gson;

public class APIResource {
    // The URL of the referenced resource
    private String url;

    public String getUrl() {
        return url;
    }

    public APIResource setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}