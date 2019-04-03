package ozmar;

import ozmar.enums.PokemonGender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PokemonPoke {

    private int id = -1;
    private String pokemonSpecies;
    private String pokemonName;
    private String pokemonNickName;
    private boolean isShiny;
    private PokemonGender gender;
    private String nature;
    private List<String> pokemonMoves;

    public PokemonPoke() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nonnull
    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    public void setPokemonSpecies(@Nonnull String pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }

    @Nonnull
    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(@Nonnull String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @Nonnull
    public String getPokemonNickName() {
        return pokemonNickName;
    }

    public void setPokemonNickName(@Nonnull String pokemonNickName) {
        this.pokemonNickName = pokemonNickName;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    @Nonnull
    public PokemonGender getGender() {
        return gender;
    }

    public void setGender(@Nonnull PokemonGender gender) {
        this.gender = gender;
    }

    @Nonnull
    public String getNature() {
        return nature;
    }

    public void setNature(@Nonnull String nature) {
        this.nature = nature;
    }

    @Nonnull
    public List<String> getPokemonMovesSet() {
        return pokemonMoves;
    }

    @Nonnull
    public String getPokemonMovesString() {
        StringBuilder moves = new StringBuilder();
        for (String pokemonMove : pokemonMoves) {
            moves.append(",");
            System.out.println(pokemonMove);
        }

        return moves.toString();
    }


    public void setPokemonMoves(@Nonnull List<String> pokemonMoves) {
        this.pokemonMoves = pokemonMoves;
    }

    public void setPokemonMoves(@Nonnull String moves) {
        List<String> pokemonMoves = new ArrayList<>(4);
        StringTokenizer st = new StringTokenizer(moves, ",");
        while (st.hasMoreTokens()) {
            pokemonMoves.add(st.nextToken());
        }
        this.pokemonMoves = pokemonMoves;
    }

    @Nonnull
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
