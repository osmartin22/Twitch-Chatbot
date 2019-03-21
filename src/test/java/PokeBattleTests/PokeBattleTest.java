package PokeBattleTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.pokemonBattle.PokeBattle;
import ozmar.pokemonBattle.convertData.ConvertIntoPoke;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class PokeBattleTest {

    private static Trainer trainerRed;
    private static Trainer trainerBlue;
    private static PokeBattle pokeBattle;

    @BeforeClass
    public static void init() {
        ConvertIntoPoke convertIntoPoke = new ConvertIntoPoke();
        List<Poke> redPokes = new ArrayList<>();
        redPokes.add(convertIntoPoke.createPoke("bulbasaur"));
        redPokes.add(convertIntoPoke.createPoke("charmander"));
        redPokes.add(convertIntoPoke.createPoke("squirtle"));
        trainerRed = new Trainer("Red", redPokes);

        List<Poke> bluePokes = new ArrayList<>();
        bluePokes.add(convertIntoPoke.createPoke("torchic"));
        bluePokes.add(convertIntoPoke.createPoke("treecko"));
        bluePokes.add(convertIntoPoke.createPoke("mudkip"));
        trainerBlue = new Trainer("Blue", bluePokes);

        pokeBattle = new PokeBattle(trainerRed, redPokes.get(0), trainerBlue, bluePokes.get(0));
    }

    @Test
    public void basicSwitchingOfPokes() {
        List<Poke> redPokes = trainerRed.getPokeList();
        List<Poke> bluePokes = trainerBlue.getPokeList();
        Assert.assertEquals("bulbasaur", redPokes.get(0).getName());
        Assert.assertEquals("torchic", bluePokes.get(0).getName());

        pokeBattle.setPokeToSwitchIn(trainerRed, 2);
        Assert.assertFalse(pokeBattle.trainersReady());
        pokeBattle.setPokeToSwitchIn(trainerBlue, 1);

        Assert.assertTrue(pokeBattle.trainersReady());
        pokeBattle.doTrainerChoice();
        Assert.assertFalse(pokeBattle.trainersReady());

        Assert.assertEquals("squirtle", pokeBattle.getCurrPoke(trainerRed).getName());
        Assert.assertEquals("treecko", pokeBattle.getCurrPoke(trainerBlue).getName());
    }
}
