package poke_api_packages.poke_models.evolution;

import com.google.gson.Gson;
import poke_api_packages.poke_models.pokemon.PokemonSpecies;

import java.util.List;

public class ChainLink {
    // Whether or not this link is for a baby Pokémon. This would only ever be true on the base link.
    private boolean is_baby;

    // The Pokémon species at this point in the evolution chain
    private PokemonSpecies species;

    // All details regarding the specific details of the referenced Pokémon species evolution
    private List<EvolutionDetail> evolution_details;

    // A List of chain objects.
    private List<ChainLink> evolves_to;

    public boolean isBaby() {
        return is_baby;
    }

    public ChainLink setIsBaby(boolean is_baby) {
        this.is_baby = is_baby;
        return this;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public ChainLink setSpecies(PokemonSpecies species) {
        this.species = species;
        return this;
    }

    public List<EvolutionDetail> getEvolutionDetails() {
        return evolution_details;
    }

    public ChainLink setEvolutionDetails(List<EvolutionDetail> evolution_details) {
        this.evolution_details = evolution_details;
        return this;
    }

    public List<ChainLink> getEvolvesTo() {
        return evolves_to;
    }

    public ChainLink setEvolvesTo(List<ChainLink> evolves_to) {
        this.evolves_to = evolves_to;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}