package PokeBattleTests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ozmar.PokemonPoke;
import ozmar.database.dao.interfaces.PokemonDaoInterface;
import ozmar.enums.PokemonGender;
import ozmar.pokemonBattle.PokeBattleView;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeController;
import ozmar.utils.RandomHelper;
import twitch4j_packages.chat.TwitchChat;

import javax.annotation.Nonnull;
import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokeControllerTest {

    private static long red;
    private static long blue;

    private static List<PokemonPoke> redPoke;
    private static List<PokemonPoke> bluePoke;

    @Mock
    private static TwitchChat chat;

    @Mock
    private static PokemonDaoInterface pokemonDaoInterface;

    private static PokeController controller;

    @BeforeClass
    public static void initialize() {
        red = 1;
        blue = 2;
        chat = Mockito.mock(TwitchChat.class);
        pokemonDaoInterface = Mockito.mock(PokemonDaoInterface.class);
        controller = new PokeController(pokemonDaoInterface);
        Map<Long, String> userMap = new HashMap<>();
        userMap.put(1L, "RED");
        userMap.put(2L, "BLUE");
        controller.setView(new PokeBattleView(chat, "testChannel", userMap));
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

    // This method not a unit test, currently it's more of an implementation test
    @Test
    public void s() {
        while (!controller.isBattleOver()) {
            controller.setMoveToUse(blue, 0, RandomHelper.getRandNumInRange(0, 3));
            controller.setMoveToUse(red, 0, RandomHelper.getRandNumInRange(0, 3));
            controller.getPokeMoves(blue, 0);
            controller.getPokeMoves(red, 0);
            System.out.println();
        }
//        while (true) {
//            if (controller.isBattleOver()) {
//                break;
//            }
//        }
    }

    @Test
    public void switchingTest() {
        for (int i = 1; i < 6; i++) {
            controller.setPokeToSwitchIn(blue, 0, i);
            controller.setPokeToSwitchIn(red, 0, i);
            System.out.println();
        }
    }
}
