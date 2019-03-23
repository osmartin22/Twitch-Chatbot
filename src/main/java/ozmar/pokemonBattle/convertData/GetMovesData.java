package ozmar.pokemonBattle.convertData;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.PokeMoveMetaData;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeContestCondition;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveByUsage;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonMoves.enums.PokeTarget;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStatsEffect;
import ozmar.pokemonBattle.pokemonStatusConditions.NonVolatileStatus;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetMovesData {

    public GetMovesData() {

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

    public PokeMove getMove(@Nonnull String name) {
        PokeMove pokeMove = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("MovesData.txt");

        if (is != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
                boolean lineFound = false;
                while (!lineFound) {
                    String line = bufferedReader.readLine();
                    if (line.contains(name)) {
                        line = line.trim();

                        String[] array = line.split(",");
                        String[] temp = new String[array.length];
                        for (int i = 0; i < array.length; i++) {
                            temp[i] = array[i].trim();
                        }
                        pokeMove = createMove(temp);
                        lineFound = true;
                    }
                }
            } catch (IOException e) {
                pokeMove = null;
            }
        }

        return pokeMove;
    }

    public PokeMove createMove(@Nonnull String[] moveData) {
        Integer id = convertToNum(moveData[0]);
        String moveName = moveData[1].trim();
        PokeTypeEnum type = PokeTypeEnum.types[convertToNum(moveData[2])];
        PokeMoveDamageClass damageClass = PokeMoveDamageClass.damageClass[convertToNum(moveData[3])];
        PokeContestCondition condition = PokeContestCondition.condiitons[convertToNum(moveData[4])];
        int pp = convertToNum(moveData[5]);
        int power = convertToNum(moveData[6]);
        int accuracy = convertToNum(moveData[7]);
        int generation = convertToNum(moveData[8]);
        boolean isContact = convertToNum(moveData[9]) == 0;
        PokeTarget target = PokeTarget.target[convertToNum(moveData[10])];
        int priority = convertToNum(moveData[11]);
        Set<PokeStatsEffect> statsEffectSet = convertToStatsList(moveData[12]);
        int statChance = convertToNum(moveData[13]);
        int stageRaise = convertToNum(moveData[14]);
        Set<NonVolatileStatus> nonVolatileStatusSet = convertToNonVolatileList(moveData[15]);
        int nonVolatileChance = convertToNum(moveData[16]);
        int flinchChance = convertToNum(moveData[17]);
        int confusionChance = convertToNum(moveData[18]);
        Set<PokeMoveByUsage> moveByUsages = convertToUsageList(moveData[19]);
        boolean isDirectAttack = convertToNum(moveData[20]) == 1;
        int critStage = convertToNum(moveData[21]);

        PokeMoveMetaData metaData = new PokeMoveMetaData(generation, condition, isContact, isDirectAttack, flinchChance
                , confusionChance, nonVolatileChance, nonVolatileStatusSet, target, stageRaise, statChance, statsEffectSet,
                moveByUsages, critStage);
        return new PokeMove(id, moveName, pp, power, accuracy, priority, type, damageClass, target, metaData);
    }

    @NonNull
    private Set<NonVolatileStatus> convertToNonVolatileList(@NonNull String nonVolatile) {
        Set<NonVolatileStatus> set = new HashSet<>();
        for (int i = 0; i < nonVolatile.length(); i++) {
            if (nonVolatile.charAt(i) == '1') {
                set.add(NonVolatileStatus.status[i + 1]);
            }
        }

        if (set.isEmpty()) {
            set.add(NonVolatileStatus.NONE);
        }

        return set;
    }

    @Nonnull
    private Set<PokeStatsEffect> convertToStatsList(@Nonnull String statsEffect) {
        Set<PokeStatsEffect> stats = new HashSet<>();
        for (int i = 0; i < statsEffect.length(); i++) {
            if (statsEffect.charAt(i) == '1') {
                stats.add(PokeStatsEffect.statsEffect[i + 1]);
            }
        }

        if (stats.isEmpty()) {
            stats.add(PokeStatsEffect.NONE);
        }

        return stats;
    }

    @Nonnull
    private Set<PokeMoveByUsage> convertToUsageList(@Nonnull String usage) {
        Set<PokeMoveByUsage> moveByUsages = new HashSet<>();
        for (int i = 0; i < usage.length(); i++) {
            if (usage.charAt((i)) == '1') {
                moveByUsages.add(PokeMoveByUsage.usage[i + 1]);
            }
        }

        if (moveByUsages.isEmpty()) {
            moveByUsages.add(PokeMoveByUsage.NONE);
        }

        return moveByUsages;
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
