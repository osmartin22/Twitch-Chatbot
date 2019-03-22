package PokeBattleTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.pokemonBattle.PokeBattle;
import ozmar.pokemonBattle.PokeBattleCalculator;
import ozmar.pokemonBattle.convertData.ConvertIntoPoke;
import ozmar.pokemonBattle.convertData.GetMovesData;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonField.PokemonWeather.PokeWeatherEnum;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class PokeBattleTest {

    private static Trainer trainerRed;
    private static Trainer trainerBlue;
    private static PokeBattle pokeBattle;
    private static ConvertIntoPoke convertIntoPoke;

    @BeforeClass
    public static void init() {
        convertIntoPoke = new ConvertIntoPoke();
        List<Poke> redPokes = new ArrayList<>();
        redPokes.add(convertIntoPoke.createPoke("bulbasaur"));
        redPokes.add(convertIntoPoke.createPoke("charmander"));
        redPokes.add(convertIntoPoke.createPoke("squirtle"));
        trainerRed = new Trainer("Red", redPokes);

        List<Poke> bluePokes = new ArrayList<>();
        bluePokes.add(convertIntoPoke.createPoke("bulbasaur"));
        bluePokes.add(convertIntoPoke.createPoke("charmander"));
        bluePokes.add(convertIntoPoke.createPoke("squirtle"));
        trainerBlue = new Trainer("Blue", bluePokes);

        pokeBattle = new PokeBattle(trainerRed, redPokes.get(0), trainerBlue, bluePokes.get(0));
    }

    @Test
    public void basicSwitchingOfPokes() {
        List<Poke> redPokes = trainerRed.getPokeList();
        List<Poke> bluePokes = trainerBlue.getPokeList();

        Assert.assertEquals("bulbasaur", redPokes.get(0).getName());
        Assert.assertEquals("bulbasaur", bluePokes.get(0).getName());

        Assert.assertTrue(pokeBattle.setPokeToSwitchIn(trainerRed, 2, 0));
        Assert.assertFalse(pokeBattle.setPokeToSwitchIn(trainerRed, 1, 0));

        Assert.assertFalse(pokeBattle.trainersReady());

        Assert.assertTrue(pokeBattle.setPokeToSwitchIn(trainerBlue, 1, 0));
        Assert.assertFalse(pokeBattle.setPokeToSwitchIn(trainerBlue, 2, 0));

        Assert.assertTrue(pokeBattle.trainersReady());

        pokeBattle.doTrainerChoice(0);
        Assert.assertFalse(pokeBattle.trainersReady());

        Assert.assertEquals("squirtle", pokeBattle.getPokeInBattle(trainerRed, 0).getPoke().getName());
        Assert.assertEquals("charmander", pokeBattle.getPokeInBattle(trainerBlue, 0).getPoke().getName());
    }

    @Test
    public void damageTest() {
        GetMovesData movesData = new GetMovesData();
        PokeBattleCalculator calculator = new PokeBattleCalculator();
        PokeInBattle pokeInBattleRed = new PokeInBattle(trainerRed.getPokeList().get(0));
        PokeInBattle pokeInBattleBlue = new PokeInBattle(trainerBlue.getPokeList().get(0));
        PokeMove move = movesData.getMove("Pound,");
        pokeInBattleRed.getPokeStages().modifyStage(PokeStatStage.ATK_STAGE, -2);
        pokeInBattleBlue.getPokeStages().modifyStage(PokeStatStage.DEF_STAGE, 2);

        double dmg = calculator.calculateDamage(pokeInBattleRed, pokeInBattleBlue, move, PokeWeatherEnum.CLEAR_SKIES);
        System.out.println(dmg);
    }
}
