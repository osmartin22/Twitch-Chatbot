package PokeBattleTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.pokemonBattle.ConvertIntoPoke;
import ozmar.pokemonBattle.PokeBattle;
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

        pokeBattle.setPokeToSwitchIn(trainerRed, redPokes.get(2));
        pokeBattle.doTrainerChoice();
        Assert.assertEquals("squirtle", pokeBattle.getCurrPoke(trainerRed).getName());

        pokeBattle.setPokeToSwitchIn(trainerBlue, bluePokes.get(1));
        pokeBattle.doTrainerChoice();
        Assert.assertEquals("treecko", pokeBattle.getCurrPoke(trainerBlue).getName());
    }
}
