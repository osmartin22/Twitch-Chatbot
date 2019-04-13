package PokeBattleTests;


import org.junit.Test;
import ozmar.pokemonBattle.convertData.GetMovesData;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatsEffect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetMovesFromFileTest {

    @Test
    public void getStuff() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("MovesData.txt");

        if (is != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
                boolean wordFound = false;
                while (!wordFound) {
                    String line = bufferedReader.readLine();
                    if (line.contains("Pound")) {
                        System.out.println(line);
                        line = line.trim();

                        String[] array = line.split(",");
                        String[] temp = new String[array.length];
                        for (int i = 0; i < array.length; i++) {
                            temp[i] = array[i].trim();
                        }

//                        for (String s : array) {
//                            System.out.println(s);
//                        }
                        GetMovesData data = new GetMovesData();
                        PokeMove move = data.createMove(temp);
                        System.out.println("Id: " + move.getId());
                        System.out.println("Power: " + move.getPower());
                        System.out.println("Damage Class: " + move.getDamageClass());

                        for (PokeStatsEffect effect : move.getMetaData().getUserStatsEffect()) {
                            System.out.println("Effect: " + effect);
                        }
                        wordFound = true;
                    }
                }
                is.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }

        System.out.println("Ended reading file ");
    }
}
