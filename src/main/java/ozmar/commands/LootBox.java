package ozmar.commands;

import ozmar.enums.Rarity;
import ozmar.utils.RandomHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LootBox {

    // Legendary    5%      1-5
    // Epic         10%     6-15
    // Rare         25%     16-40
    // Common       60%     41-100
    public LootBox() {

    }

    /**
     * Gets random loot based on the rarity
     *
     * @return String
     */
    public String getLoot() {
        String result = "";
        Rarity rarity = getRarity();
        if (rarity == Rarity.COMMON) {
            result = readFile("commonLoot.txt");
        } else if (rarity == Rarity.RARE) {
            result = readFile("rareLoot.txt");
        } else if (rarity == Rarity.EPIC) {
            result = readFile("epicLoot.txt");
        } else if (rarity == Rarity.LEGENDARY) {
            result = readFile("legendaryLoot.txt");
        }
        return result;
    }

    /**
     * Reads the desired file and chooses a random line(loot) to get.
     * All lines have the same chance of being selected
     *
     * @param file desired file to read from
     * @return String
     */
    private String readFile(String file) {
        String line;
        String path = "C:\\TwitchBotFiles\\Lootbox\\" + file;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // First line contains file info, skip
            // Second line contains the number of loot
            br.readLine();
            int randLine = RandomHelper.getRandNumInRange(1, Integer.valueOf(br.readLine()));
            int count = 1;
            while (count < randLine) {
                br.readLine();
                count++;
            }
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Failed opening commands file: " + e.getMessage());
            line = "";
        }

        return line;
    }

    /**
     * Gets a Rarity based on weighted probabilities
     *
     * @return Rarity
     */
    private Rarity getRarity() {
        int rand = RandomHelper.getRandNumInRange(1, 100);
        if (rand > 40) {
            return Rarity.COMMON;
        }
        if (rand > 15) {
            return Rarity.RARE;
        }
        if (rand > 5) {
            return Rarity.EPIC;
        }
        return Rarity.LEGENDARY;
    }
}
