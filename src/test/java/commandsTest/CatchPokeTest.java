package commandsTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.commands.CatchPoke;
import ozmar.commands.interfaces.CatchPokeInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatchPokeTest {

    // DO NOT CALL API MORE THAN 100 TIMES IN A MINUTE (100/min is the rate limit)

    private static CatchPokeInterface catchPoke;
    private static List<String> pokemonNames;

    @BeforeClass
    public static void setup() {
        catchPoke = new CatchPoke();
        pokemonNames = new ArrayList<>(Arrays.asList("bulbasaur", "charmander", "squirtle", "pikachu", "meowth"));
    }

    @Test
    public void checkExistingPokemonById() {
        for (int i = 1; i < 5; i++) {
            checkStringNotEmpty(catchPoke.initialize(i));
        }
    }

    @Test
    public void checkExistingPokemonByName() {
        for (String pokemonName : pokemonNames) {
            checkStringNotEmpty(catchPoke.initialize(pokemonName));
        }
    }

    @Test
    public void checkNonExistingPokemonById() {
        for (int i = -1; i > -5; i--) {
            Assert.assertEquals(-1, catchPoke.initialize(i));
        }
    }

    @Test
    public void checkNonExistingPokemonByName() {
        for (int i = -1; i > -5; i--) {
            Assert.assertEquals(-1, catchPoke.initialize(i + "a"));
        }
    }

    private void checkStringNotEmpty(int result) {
        Assert.assertEquals(1, result);

        // return String should not be empty if the pokemon exists
        String catchResult = catchPoke.attemptCatch().getCatchResultString();
        Assert.assertNotEquals("", catchResult);
    }
}
