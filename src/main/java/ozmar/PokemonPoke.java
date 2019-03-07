package ozmar;

import ozmar.enums.PokemonGender;

import java.util.Set;

public class PokemonPoke {

    private String pokemonSpecies;
    private String pokemonName;
    private String pokemonNickName;
    private boolean isShiny;
    private PokemonGender gender;
    private String nature;
    private Set<String> pokemonMoves;

    public PokemonPoke() {

    }

    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    public void setPokemonSpecies(String pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonNickName() {
        return pokemonNickName;
    }

    public void setPokemonNickName(String pokemonNickName) {
        this.pokemonNickName = pokemonNickName;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    public PokemonGender getGender() {
        return gender;
    }

    public void setGender(PokemonGender gender) {
        this.gender = gender;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Set<String> getPokemonMovesSet() {
        return pokemonMoves;
    }

    public String getPokemonMovesString() {
        StringBuilder moves = new StringBuilder();
        for (String pokemonMove : pokemonMoves) {
            moves.append(",");
            System.out.println(pokemonMove);
        }

        return moves.toString();
    }

    public void setPokemonMoves(Set<String> pokemonMoves) {
        this.pokemonMoves = pokemonMoves;
    }

    public String getPokeString() {
        StringBuilder sb = new StringBuilder();
        if (isShiny) {
            sb.append("shiny ");
        }

        sb.append(nature);
        sb.append(" ");
        sb.append(gender.getGenderString());
        if (gender.getGenderNum() != 0) {
            sb.append(" ");
        }
        sb.append(pokemonName);

        return sb.toString();
    }
}
