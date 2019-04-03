package PokeBattleTests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.enums.PokemonGender;
import ozmar.pokemonBattle.PokeBattleView;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeController;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokeControllerTest {

    private static long red;
    private static long blue;

    private static List<PokemonPoke> redPoke;
    private static List<PokemonPoke> bluePoke;

    @Mock
    private static PokemonDaoInterface pokemonDaoInterface;

    @InjectMocks
    private static DatabaseHandlerInterface db;

    private static PokeController controller;

    @BeforeClass
    public static void initialize() {
        red = 1;
        blue = 2;
        pokemonDaoInterface = Mockito.mock(PokemonDaoInterface.class);
        db = Mockito.mock(DatabaseHandlerInterface.class);
        controller = new PokeController(db);
        controller.setView(new PokeBattleView());
        pokemonToGetFromDb();
        initializeController();
    }

    private static void pokemonToGetFromDb() {
        List<String> redPokeNames = new ArrayList<>(Arrays.asList("bulbasaur", "charmander", "squirtle",
                "chikorita", "cyndaquil", "totodile"));
        redPoke = new ArrayList<>();
        initializePokes(redPoke, redPokeNames);

        List<String> bluePokeNames = new ArrayList<>(Arrays.asList("treecko", "torchic", "mudkip",
                "turtwig", "chimchar", "piplup"));
        bluePoke = new ArrayList<>();
        initializePokes(bluePoke, bluePokeNames);
    }

    private static void initializePokes(@Nonnull List<PokemonPoke> pokeList, @Nonnull List<String> nameList) {
        for (String name : nameList) {
            pokeList.add(createPokemonPoke(name));
        }
    }

    private static PokemonPoke createPokemonPoke(@Nonnull String name) {
        PokemonPoke pokemonPoke = new PokemonPoke();
        pokemonPoke.setId(1);
        pokemonPoke.setPokemonSpecies(name);
        pokemonPoke.setPokemonName(name);
        pokemonPoke.setPokemonNickName(name);
        pokemonPoke.setShiny(true);
        pokemonPoke.setGender(PokemonGender.MALE);
        pokemonPoke.setNature("adamant");
        pokemonPoke.setPokemonMoves(new ArrayList<>());
        return pokemonPoke;
    }

    private static void initializeController() {
        when(db.getPokemonDao()).thenReturn(pokemonDaoInterface);
        when(pokemonDaoInterface.getPokemon(red)).thenReturn(redPoke);
        when(pokemonDaoInterface.getPokemon(blue)).thenReturn(bluePoke);
        controller.setUsers(red, "RED", blue, "BLUE");
        setMoves();
    }

    private static void setMoves() {
        List<String> moveNames = new ArrayList<>(Arrays.asList("Pound", "Quick Attack", "Swift", "Scratch"));
        for (int i = 0; i < 6; i++) {
            controller.setPokeMoves(red, i, moveNames);
        }

        for (int i = 0; i < 6; i++) {
            controller.setPokeMoves(blue, i, moveNames);
        }
    }

    @Test
    public void s() {
        controller.setMoveToUse(red, 0, 0);
        controller.setPokeToSwitchIn(red, 0, 2);
        controller.setMoveToUse(blue, 0, 0);
    }

}
