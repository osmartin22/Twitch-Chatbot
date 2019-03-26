package PokeBattleTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ozmar.pokemonBattle.PokeBattle;
import ozmar.pokemonBattle.convertData.ConvertIntoPoke;
import ozmar.pokemonBattle.convertData.GetMovesData;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemon.PokeInBattle;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeBattleCalculator;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeTargetPosition;
import ozmar.pokemonBattle.pokemonField.PokemonWeather.PokeWeatherEnum;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatStage;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokeBattleTest {
    // In 1v1 battles there are only 2 sides, 2 trainers and 2 pokemon on the field
    // Therefore trainerPosition and fieldPosition are 0 for both sides
    private static Trainer trainerRed;      // Side: 0
    private static Trainer trainerBlue;     // Side: 1
    private static PokeBattle pokeBattle;

    private static ConvertIntoPoke convertIntoPoke;
    private static GetMovesData movesData;

    @BeforeClass
    public static void init() {
        movesData = new GetMovesData();
        convertIntoPoke = new ConvertIntoPoke();
        List<Poke> redPokes = new ArrayList<>();
        redPokes.add(convertIntoPoke.createPoke("bulbasaur"));
        redPokes.add(convertIntoPoke.createPoke("charmander"));
        redPokes.add(convertIntoPoke.createPoke("squirtle"));
        trainerRed = new Trainer(0, "Red", redPokes);

        List<Poke> bluePokes = new ArrayList<>();
        bluePokes.add(convertIntoPoke.createPoke("bulbasaur"));
        bluePokes.add(convertIntoPoke.createPoke("charmander"));
        bluePokes.add(convertIntoPoke.createPoke("squirtle"));
        trainerBlue = new Trainer(1, "Blue", bluePokes);

        createMoves(redPokes);
        createMoves(bluePokes);

        List<List<Trainer>> listList = new ArrayList<>();
        listList.add(new ArrayList<>(Collections.singleton(trainerRed)));
        listList.add(new ArrayList<>(Collections.singleton(trainerBlue)));
        pokeBattle = new PokeBattle(listList);
    }

    private static void createMoves(List<Poke> pokeList) {
        PokeMove m1 = movesData.getMove("Pound");
        PokeMove m2 = movesData.getMove("Leer");
        PokeMove m3 = movesData.getMove("Swift");
        PokeMove m4 = movesData.getMove("Thunder");
        for (Poke poke : pokeList) {
            List<PokeMove> list = poke.getMoveList();
            list.add(m1);
            list.add(m2);
            list.add(m3);
            list.add(m4);
        }
    }

    @Test
    public void basicSwitchingOfPokes() {
        List<Poke> redPokes = trainerRed.getPokeList();
        List<Poke> bluePokes = trainerBlue.getPokeList();

        Assert.assertEquals("bulbasaur", redPokes.get(0).getName());
        Assert.assertEquals("bulbasaur", bluePokes.get(0).getName());

        Assert.assertTrue(pokeBattle.setPokeToSwitchIn(0, 0, 0, 2));
        Assert.assertFalse(pokeBattle.setPokeToSwitchIn(0, 0, 0, 1));

        Assert.assertFalse(pokeBattle.trainersReady());

        Assert.assertTrue(pokeBattle.setPokeToSwitchIn(1, 0, 0, 1));
        Assert.assertFalse(pokeBattle.setPokeToSwitchIn(1, 0, 0, 2));

        Assert.assertTrue(pokeBattle.trainersReady());
        pokeBattle.doTrainerChoice();

        Assert.assertEquals("squirtle", pokeBattle.getPokeInBattle(0, 0, 0).getPoke().getName());
        Assert.assertEquals("charmander", pokeBattle.getPokeInBattle(1, 0, 0).getPoke().getName());
    }

    @Test
    public void test() {
        PokeTargetPosition targetPosition;
        targetPosition = new PokeTargetPosition(1, 0, 0);
        Assert.assertTrue(pokeBattle.setMoveToUse(0, 0, 0, 0, targetPosition));

        targetPosition = new PokeTargetPosition(0, 0, 0);
        Assert.assertTrue(pokeBattle.setMoveToUse(1, 0, 0, 0, targetPosition));

        pokeBattle.doTrainerChoice();

    }

    @Test
    public void damageTest() {
        GetMovesData movesData = new GetMovesData();
        PokeBattleCalculator calculator = new PokeBattleCalculator();
        PokeInBattle pokeInBattleRed = new PokeInBattle(trainerRed.getPokeList().get(0), 0);
        PokeInBattle pokeInBattleBlue = new PokeInBattle(trainerBlue.getPokeList().get(0), 0);
        PokeMove move = movesData.getMove("Pound,");
        pokeInBattleRed.getPokeStages().modifyStage(PokeStatStage.ATK_STAGE, -2);
        pokeInBattleBlue.getPokeStages().modifyStage(PokeStatStage.DEF_STAGE, 2);

        double dmg = calculator.calculateDamage(pokeInBattleRed, pokeInBattleBlue, move, PokeWeatherEnum.CLEAR_SKIES);
        System.out.println(dmg);
    }
}
