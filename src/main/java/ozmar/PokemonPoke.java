package ozmar;

import ozmar.enums.PokemonGender;
import reactor.util.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class PokemonPoke {

    private int id = -1;
    private String pokemonSpecies;
    private String pokemonName;
    private String pokemonNickName;
    private boolean isShiny;
    private PokemonGender gender;
    private String nature;
    private Set<String> pokemonMoves;

    public PokemonPoke() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    public void setPokemonSpecies(@NonNull String pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }

    @NonNull
    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(@NonNull String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @NonNull
    public String getPokemonNickName() {
        return pokemonNickName;
    }

    public void setPokemonNickName(@NonNull String pokemonNickName) {
        this.pokemonNickName = pokemonNickName;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    @NonNull
    public PokemonGender getGender() {
        return gender;
    }

    public void setGender(@NonNull PokemonGender gender) {
        this.gender = gender;
    }

    @NonNull
    public String getNature() {
        return nature;
    }

    public void setNature(@NonNull String nature) {
        this.nature = nature;
    }

    @NonNull
    public Set<String> getPokemonMovesSet() {
        return pokemonMoves;
    }

    @NonNull
    public String getPokemonMovesString() {
        StringBuilder moves = new StringBuilder();
        for (String pokemonMove : pokemonMoves) {
            moves.append(",");
            System.out.println(pokemonMove);
        }

        return moves.toString();
    }

    public void setPokemonMoves(@NonNull Set<String> pokemonMoves) {
        this.pokemonMoves = pokemonMoves;
    }

    public void setPokemonMoves(@NonNull String moves) {
        Set<String> pokemonMoves = new HashSet<>(4);
        StringTokenizer st = new StringTokenizer(moves, ",");
        while (st.hasMoreTokens()) {
            pokemonMoves.add(st.nextToken());
        }
        this.pokemonMoves = pokemonMoves;
    }

    @NonNull
    public String getPokeString() {
        StringBuilder sb = new StringBuilder();
        if (isShiny) {
            sb.append("shiny ");
        }

        sb.append(nature);
        sb.append(" ");
        sb.append(pokemonName);
        if (gender.getGenderNum() != 0) {
            sb.append(" ");
            sb.append(gender.getGenderString());
        }

        return sb.toString();
    }
}
