package ozmar.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import ozmar.commands.interfaces.LootBoxInterface;
import ozmar.enums.Rarity;
import ozmar.utils.RandomHelper;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

@Slf4j
public class LootBox implements LootBoxInterface {

    // Legendary    5%      1-5
    // Epic         10%     6-15
    // Rare         25%     16-40
    // Common       60%     41-100

    private final MessageSource source;
    private final Locale defaultLocale;

    public LootBox(MessageSource messageSource) {
        this.source = messageSource;
        this.defaultLocale = new Locale("en");
    }

    /**
     * Gets random loot based on the rarity
     *
     * @return String
     */
    @Nonnull
    @Override
    public String getLoot() {
        String result = "";
        Rarity rarity = getRarity();
        if (rarity == Rarity.COMMON) {
            result = readFile(source.getMessage("cmd.file.common", null, defaultLocale));
        } else if (rarity == Rarity.RARE) {
            result = readFile(source.getMessage("cmd.file.rare", null, defaultLocale));
        } else if (rarity == Rarity.EPIC) {
            result = readFile(source.getMessage("cmd.file.epic", null, defaultLocale));
        } else if (rarity == Rarity.LEGENDARY) {
            result = readFile(source.getMessage("cmd.file.legendary", null, defaultLocale));
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
    @Nonnull
    private String readFile(@Nonnull String file) {
        String line;
        String path = source.getMessage("cmd.base.path.loot", null, defaultLocale) + file;
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
            log.error("Failed opening loot file: {}", e.getMessage());
            line = "";
        }

        return line;
    }

    /**
     * Gets a Rarity based on weighted probabilities
     *
     * @return Rarity
     */
    @Nonnull
    private Rarity getRarity() {
        int rand = RandomHelper.getRandNumInRange(1, 100);
        if (rand > 30) {
            return Rarity.COMMON;
        }
        if (rand > 20) {
            return Rarity.RARE;
        }
        if (rand > 10) {
            return Rarity.EPIC;
        }
        return Rarity.LEGENDARY;
    }
}
