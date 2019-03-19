package ozmar.pokemonBattle;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonMoves.PokeTarget;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GetMovesData {

    private ClassLoader loader;
    private InputStream is;
    private BufferedReader bufferedReader;

    public GetMovesData() {

    }

    public void createInputStream() {
        loader = Thread.currentThread().getContextClassLoader();
        is = loader.getResourceAsStream("ZMoves_Data.txt");
    }

    public void close() {

    }

    /**
     * @param moveNames list containing the names of the desired moves
     * @return
     */
    public List<PokeMove> convertNamesToMoves(@Nonnull List<String> moveNames) {


        return null;
    }

    public PokeMove test(@Nonnull String name) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("MovesData.txt"))) {
            String testLine;
            boolean lineFound = false;
            while (!lineFound) {
                testLine = bufferedReader.readLine();
                if (testLine.contains("Razor Wind")) {
                    String[] array = testLine.split(",");
                    lineFound = true;
                }
            }


        } catch (IOException e) {

        }

        return null;
    }

    private PokeMove createMove(@Nonnull String[] moveData) {
        Integer id = convertToNum(moveData[0]);
        String moveName = moveData[1].trim();
        PokeTypeEnum type = PokeTypeEnum.types[convertToNum(moveData[2])];
        PokeMoveDamageClass damageClass = PokeMoveDamageClass.damageClass[convertToNum(moveData[3])];
        // [4] Contest type
        int pp = convertToNum(moveData[5]);
        int power = convertToNum(moveData[6]);
        int accuracy = convertToNum(moveData[7]);
        // [8] Generation
        boolean isContact = convertToNum(moveData[9]) == 0;
        PokeTarget target = PokeTarget.target[convertToNum(moveData[10])];
        int prioirty = convertToNum(moveData[11]);
        // [12] TODO: split the binary numbers into enums
        int statChance = convertToNum(moveData[13]);
        int stageRaise = convertToNum(moveData[14]);
        NonVolatileStatus nonVolatileStatus = NonVolatileStatus.status[convertToNum(moveData[15])];
        int nonVolatileChance = convertToNum(moveData[16]);
        int flinchChance = convertToNum(moveData[17]);
        int confusionChance = convertToNum(moveData[18]);
        // [19]


        return null;
    }

    private Integer convertToNum(@Nonnull String num) {
        Integer newNum = null;
        try {
            newNum = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println("Not a number: " + e.getMessage());
        }

        return newNum;
    }
}
