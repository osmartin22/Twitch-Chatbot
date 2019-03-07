package ozmar.database.tables;

import ozmar.PokemonPoke;
import ozmar.database.tables.interfaces.PokemonTableInterface;
import ozmar.enums.PokemonGender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonTable extends Table implements PokemonTableInterface {

    private static final String POKEMON_TABLE = "pokemonTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_POKEMON_SPECIES = "pokemonSpecies";
    private static final String COLUMN_POKEMON_NAME = "pokemonName";
    private static final String COLUMN_POKEMON_NICKNAME = "pokemonNickName";
    private static final String COLUMN_IS_SHINY = "isShiny";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_NATURE = "nature";
    private static final String COLUMN_MOVES = "moves";

    /*
    USER_ID is unique for now while a user can only have one pokemon
     */
    private static final String CREATE_POKEMON_TABLE =
            "CREATE TABLE IF NOT EXISTS " + POKEMON_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USER_ID + " INTEGER UNIQUE, " +
                    COLUMN_POKEMON_SPECIES + " TEXT, " +
                    COLUMN_POKEMON_NAME + " TEXT, " +
                    COLUMN_POKEMON_NICKNAME + " TEXT, " +
                    COLUMN_IS_SHINY + " INTEGER, " +
                    COLUMN_GENDER + " INTEGER, " +
                    COLUMN_NATURE + " TEXT, " +
                    COLUMN_MOVES + " TEXT)";

    private static final String getPokemonSql =
            "SELECT * FROM " + POKEMON_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String insertPokemonSql =
            "INSERT OR IGNORE INTO " + POKEMON_TABLE + " (" +
                    COLUMN_USER_ID + ", " +
                    COLUMN_POKEMON_SPECIES + ", " +
                    COLUMN_POKEMON_NAME + ", " +
                    COLUMN_POKEMON_NICKNAME + ", " +
                    COLUMN_IS_SHINY + ", " +
                    COLUMN_GENDER + ", " +
                    COLUMN_NATURE + ", " +
                    COLUMN_MOVES + ") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String updatePokemonSql =
            "UPDATE " + POKEMON_TABLE +
                    " SET " +
                    COLUMN_POKEMON_SPECIES + " = ?, " +
                    COLUMN_POKEMON_NAME + " = ?, " +
                    COLUMN_POKEMON_NICKNAME + " = ?, " +
                    COLUMN_IS_SHINY + " = ?, " +
                    COLUMN_GENDER + " = ?, " +
                    COLUMN_NATURE + " = ?, " +
                    COLUMN_MOVES + " = ? " +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String getPokemonNameSql =
            "SELECT " + COLUMN_POKEMON_NAME + ", " + COLUMN_POKEMON_NICKNAME +
                    " FROM " + POKEMON_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";


    @Nullable
    @Override
    public PokemonPoke getPokemon(long userId) {
        PokemonPoke pokemonPoke = null;
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPokemonSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                pokemonPoke = new PokemonPoke();
                while (resultSet.next()) {
                    pokemonPoke.setPokemonSpecies(resultSet.getString(COLUMN_POKEMON_SPECIES));
                    pokemonPoke.setPokemonName(resultSet.getString(COLUMN_POKEMON_NAME));
                    pokemonPoke.setPokemonNickName(resultSet.getString(COLUMN_POKEMON_NICKNAME));
                    pokemonPoke.setShiny(resultSet.getInt(COLUMN_IS_SHINY) == 1);
                    pokemonPoke.setGender(PokemonGender.genders[resultSet.getInt(COLUMN_GENDER)]);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get the users Pokemon: " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return pokemonPoke;
    }

    @Override
    public void insertPokemon(long userId, @Nonnull PokemonPoke poke) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPokemonSql)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, poke.getPokemonSpecies());
            preparedStatement.setString(3, poke.getPokemonName());
            preparedStatement.setString(4, poke.getPokemonNickName());
            preparedStatement.setInt(4, poke.isShiny() ? 1 : 0);
            preparedStatement.setInt(6, poke.getGender().getGenderNum());
            preparedStatement.setString(7, poke.getNature());
            preparedStatement.setString(8, poke.getPokemonMovesString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to insert pokemon for " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public void updatePokemon(long userId, @Nonnull PokemonPoke poke) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePokemonSql)) {
            preparedStatement.setString(1, poke.getPokemonSpecies());
            preparedStatement.setString(2, poke.getPokemonName());
            preparedStatement.setString(3, poke.getPokemonNickName());
            preparedStatement.setInt(4, poke.isShiny() ? 1 : 0);
            preparedStatement.setInt(5, poke.getGender().getGenderNum());
            preparedStatement.setString(6, poke.getNature());
            preparedStatement.setString(7, poke.getPokemonMovesString());
            preparedStatement.setLong(8, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to update pokemon for " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
