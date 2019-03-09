package ozmar.database.tables;

import ozmar.PokemonPoke;
import ozmar.database.tables.interfaces.PokemonTableInterface;
import ozmar.enums.PokemonGender;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonTable extends Table implements PokemonTableInterface {

    private static final String POKEMON_TABLE = "pokemonTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_POKEMON_SPECIES = "pokemonSpecies";
    private static final String COLUMN_POKEMON_NAME = "pokemonName";
    private static final String COLUMN_POKEMON_NICKNAME = "pokemonNickName";
    private static final String COLUMN_IS_SHINY = "isShiny";
    private static final String COLUMN_NATURE = "nature";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MOVES = "moves";

    private static final String CREATE_POKEMON_TABLE =
            "CREATE TABLE IF NOT EXISTS " + POKEMON_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_POKEMON_SPECIES + " TEXT, " +
                    COLUMN_POKEMON_NAME + " TEXT, " +
                    COLUMN_POKEMON_NICKNAME + " TEXT, " +
                    COLUMN_IS_SHINY + " INTEGER, " +
                    COLUMN_NATURE + " TEXT, " +
                    COLUMN_GENDER + " INTEGER, " +
                    COLUMN_MOVES + " TEXT)";

    private static final String getPokemonSql =
            "SELECT * FROM " + POKEMON_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    private static final String insertPokemonSql =
            "INSERT INTO " + POKEMON_TABLE + " (" +
                    COLUMN_USER_ID + ", " +
                    COLUMN_POKEMON_SPECIES + ", " +
                    COLUMN_POKEMON_NAME + ", " +
                    COLUMN_POKEMON_NICKNAME + ", " +
                    COLUMN_IS_SHINY + ", " +
                    COLUMN_NATURE + ", " +
                    COLUMN_GENDER + ", " +
                    COLUMN_MOVES + ") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String updatePokemonSql =
            "UPDATE " + POKEMON_TABLE +
                    " SET " +
                    COLUMN_POKEMON_SPECIES + " = ?, " +
                    COLUMN_POKEMON_NAME + " = ?, " +
                    COLUMN_POKEMON_NICKNAME + " = ?, " +
                    COLUMN_IS_SHINY + " = ?, " +
                    COLUMN_NATURE + " = ?, " +
                    COLUMN_GENDER + " = ?, " +
                    COLUMN_MOVES + " = ? " +
                    " WHERE " + COLUMN_ID + " = ?";

    private static final String getPokemonNameSql =
            "SELECT " + COLUMN_POKEMON_NAME + ", " + COLUMN_POKEMON_NICKNAME +
                    " FROM " + POKEMON_TABLE +
                    " WHERE " + COLUMN_USER_ID + " = ?";

    public PokemonTable() {
        createTable(CREATE_POKEMON_TABLE);
    }

    @Override
    public int getUsersPokemonCount(long userId) {
        Connection connection = openConnection();
        String sql = "SELECT count(*) FROM " + POKEMON_TABLE +
                " WHERE " + COLUMN_USER_ID + " = " + userId;
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Failed to check table for user " + userId + " " + e.getMessage());
        }
        return count;
    }

    @Nonnull
    @Override
    public List<PokemonPoke> getPokemon(long userId) {
        List<PokemonPoke> pokeList = new ArrayList<>();
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPokemonSql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    PokemonPoke poke = new PokemonPoke();
                    poke.setId(resultSet.getInt(COLUMN_ID));
                    poke.setPokemonSpecies(resultSet.getString(COLUMN_POKEMON_SPECIES));
                    poke.setPokemonName(resultSet.getString(COLUMN_POKEMON_NAME));
                    poke.setPokemonNickName(resultSet.getString(COLUMN_POKEMON_NICKNAME));
                    poke.setShiny(resultSet.getInt(COLUMN_IS_SHINY) == 1);
                    poke.setNature(resultSet.getString(COLUMN_NATURE));
                    poke.setGender(PokemonGender.genders[resultSet.getInt(COLUMN_GENDER)]);
                    poke.setPokemonMoves(resultSet.getString(COLUMN_MOVES));
                    pokeList.add(poke);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get the users Pokemon: " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return pokeList;
    }

    @Override
    public void insertPokemon(long userId, @Nonnull PokemonPoke poke) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPokemonSql)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, poke.getPokemonSpecies());
            preparedStatement.setString(3, poke.getPokemonName());
            preparedStatement.setString(4, poke.getPokemonNickName());
            preparedStatement.setInt(5, poke.isShiny() ? 1 : 0);
            preparedStatement.setString(6, poke.getNature());
            preparedStatement.setInt(7, poke.getGender().getGenderNum());
            preparedStatement.setString(8, poke.getPokemonMovesString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert pokemon for " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updatePokemon(long userId, @Nonnull PokemonPoke poke, int pokeToReplace) {
        Connection connection = openConnection();

        List<PokemonPoke> pokeList = getPokemon(userId);
        PokemonPoke oldPoke = pokeList.get(pokeToReplace - 1);
        poke.setId(oldPoke.getId());

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePokemonSql)) {
            preparedStatement.setString(1, poke.getPokemonSpecies());
            preparedStatement.setString(2, poke.getPokemonName());
            preparedStatement.setString(3, poke.getPokemonNickName());
            preparedStatement.setInt(4, poke.isShiny() ? 1 : 0);
            preparedStatement.setString(5, poke.getNature());
            preparedStatement.setInt(6, poke.getGender().getGenderNum());
            preparedStatement.setString(7, poke.getPokemonMovesString());
            preparedStatement.setLong(8, poke.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update pokemon for " + userId + ": " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
