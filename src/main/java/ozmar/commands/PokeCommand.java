package ozmar.commands;

import ozmar.CaughtPokeInfo;
import ozmar.PokemonPoke;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.commands.interfaces.PokeCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;
import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PokeCommand implements PokeCommandInterface {

    private final DatabaseHandlerInterface db;
    private final CatchPokeInterface catchPoke;
    private final int MAX_POKE_OWNED = 6;
    private final int MAX_POKEDEX_INDEX = 812;
    private final Map<Long, CaughtPokeHelper> caughtPokes;
    private final ScheduledExecutorService caughtPokeTimer = Executors.newScheduledThreadPool(1);
    private final Map<Long, Long> replacePokes;
    private final ScheduledExecutorService replacePokeTimer = Executors.newScheduledThreadPool(1);


    public PokeCommand(DatabaseHandlerInterface db, CatchPokeInterface catchPoke) {
        this.db = db;
        this.catchPoke = catchPoke;
        this.caughtPokes = new HashMap<>();
        this.replacePokes = new HashMap<>();
        startCaughtPokeTimer();
        startReplacePokeTimer();
    }

    /**
     * Gets a random pokemon with a name, nature, and gender and decides if it was caught or not
     *
     * @param event User info and command data
     * @return String
     */
    @Nonnull
    @Override
    public String catchPokeCommand(@Nonnull CommandEvent event) {
        String pokeInput = StringHelper.getFirstWord(event.getCommand().trim().toLowerCase());

        String output;
        if (pokeInput.equals("missingno")) {
            output = String.format("%s, use '!catchpoke' or '!catchpoke <region>' for that one moon2WUT",
                    event.getUser().getName());
        } else {
            CaughtPokeInfo caughtPokeInfo = null;
            Integer pokedexIndex = getPokedexIndex(pokeInput);

            // Attempt to catch MissingNo if a specific Pokemon was not requested
            if (pokeInput.isEmpty() || catchPoke.getRegionNames().contains(pokeInput)) {
                caughtPokeInfo = catchPoke.catchMissingNo();
            }

            int initializeResult;
            if (caughtPokeInfo == null) {
                if (pokedexIndex == null) {
                    if (!pokeInput.isEmpty()) {
                        initializeResult = catchPoke.initialize(pokeInput);
                    } else {
                        int randomIndex = RandomHelper.getRandNumInRange(1, MAX_POKEDEX_INDEX);
                        if (randomIndex == 811) {
                            randomIndex = 813;
                        } else if (randomIndex == 812) {
                            randomIndex = 816;
                        }
                        initializeResult = catchPoke.initialize(randomIndex);
                    }
                } else {
                    initializeResult = catchPoke.initialize(pokedexIndex);
                }
            } else {
                initializeResult = 1;
            }

            if (initializeResult != -1) {
                output = getCatchPokeResult(caughtPokeInfo, event);
            } else {
                output = getCatchPokeErrorOutput(pokedexIndex, event.getUser().getName());
            }
        }

        return output;
    }

    /**
     * Gets the result string for the Pokemon that were found and ran away or were caught
     *
     * @param caughtPokeInfo info about the captured Pokemon
     * @param event          info about the user
     * @return String
     */
    @Nonnull
    private String getCatchPokeResult(@Nullable CaughtPokeInfo caughtPokeInfo, @Nonnull CommandEvent event) {
        String output;
        if (caughtPokeInfo == null) {
            caughtPokeInfo = catchPoke.attemptCatch();
        }
        output = event.getUser().getName() + caughtPokeInfo.getCatchResultString();

        if (caughtPokeInfo.isCaptured()) {
            output += caughtPokeHelper(event.getUser().getId(), caughtPokeInfo);
        }
        return output;
    }

    /**
     * Gets the output when a Pokemon was not found
     *
     * @param pokedexIndex index of a Pokemon
     * @param username     name of the user
     * @return String
     */
    @Nonnull
    private String getCatchPokeErrorOutput(@Nullable Integer pokedexIndex, @Nonnull String username) {
        String output;
        if (pokedexIndex != null) {
            output = String.format("%s, Id not found in the national Pokedex. Try 1-810, 813, or 816", username);
        } else {
            output = String.format("%s, Poke Species not found, replace spaces/punctuation in a name with" +
                    " '-' if it's not working", username);
        }
        return output;
    }

    /**
     * Converts the given string to an int
     * -1 is returned if it fails
     *
     * @param index string
     * @return int
     */
    private Integer getPokedexIndex(@Nonnull String index) {
        Integer num;
        try {
            num = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            num = null;
        }

        return num;
    }

    /**
     * Checks if the user already has the max number of Pokemon
     * If they have less than the max, the caught Pokemon is saved to the db
     * If they have more than the max, the user must specify if they want the Pokemon and the caught
     * Pokemon is stored in a buffer
     *
     * @param userId   Twitch id of the user
     * @param pokeInfo info about the captured Pokemon
     * @return string
     */
    @Nonnull
    private String caughtPokeHelper(long userId, @Nonnull CaughtPokeInfo pokeInfo) {
        String output = "";
        int pokeCount = db.getPokemonDao().getUsersPokemonCount(userId);
        if (pokeCount < MAX_POKE_OWNED) {
            db.getPokemonDao().insertPokemon(userId, pokeInfo.getPoke());
        } else {
            saveCatchToBuffer(userId, pokeInfo);
            output = ", use !replacepoke [Num], or '!replacepoke help'";
        }

        return output;
    }

    /**
     * Returns the current Pokemon a user has or informs the user they do not have any
     *
     * @param event User data and command info
     * @return String
     */
    @Nonnull
    @Override
    public String myPoke(@Nonnull CommandEvent event) {
        String output;
        List<PokemonPoke> pokeList = db.getPokemonDao().getPokemon(event.getUser().getId());
        if (pokeList.isEmpty()) {
            output = String.format("%s, you have 0/%s Poke, use !catchpoke to get some",
                    event.getUser().getName(), MAX_POKE_OWNED);
        } else {
            int count = 1;
            output = String.format("%s, you have %s/%s Poke,", event.getUser().getName(),
                    pokeList.size(), MAX_POKE_OWNED);

            StringBuilder pokeString = new StringBuilder();
            for (PokemonPoke poke : pokeList) {
                pokeString.append(String.format(" %s) %s,", count++, poke.getPokeString()));
            }

            pokeString.setLength(pokeString.length() - 1);
            output += pokeString;
        }

        return output;
    }

    /**
     * Replaces the Pokemon of a user with the most recent catch
     *
     * @param event User data and command info
     * @return String
     */
    @Nullable
    @Override
    public String replacePoke(@Nonnull CommandEvent event) {
        long userId = event.getUser().getId();
        CaughtPokeInfo pokeInfo = getCatchFromBuffer(userId);

        String output = null;
        if (pokeInfo != null) {
            String userName = event.getUser().getName();
            String input = StringHelper.getFirstWord(event.getCommand().trim().toLowerCase());

            if (!input.isEmpty()) {
                if (input.equals("help")) {
                    if (!replacePokes.containsKey(userId)) {
                        replacePokes.put(userId, System.currentTimeMillis());
                        output = String.format("%s, !replacepoke [Num] replaces a Poke you have with the one you just caught." +
                                " [Num] must be from 1-%s and can be found using !mypoke", userName, MAX_POKE_OWNED);
                    }
                } else {
                    int pokeNum;
                    try {
                        pokeNum = Integer.valueOf(StringHelper.getFirstWord(input));
                        if (pokeNum > 0 && pokeNum <= MAX_POKE_OWNED) {
                            db.getPokemonDao().updatePokemon(userId, pokeInfo.getPoke(), pokeNum);
                            removeCatchFromBuffer(userId);
                        }
                    } catch (NumberFormatException e) {
                        // No output if wrong input
                    }
                }
            }
        }

        return output;
    }

    /**
     * Class to help store the caught Pokemon in a buffer
     */
    private class CaughtPokeHelper {
        private long timeStored;
        private CaughtPokeInfo pokeInfo;

        CaughtPokeHelper(long timeStored, @Nonnull CaughtPokeInfo pokeInfo) {
            this.timeStored = timeStored;
            this.pokeInfo = pokeInfo;
        }
    }

    private void saveCatchToBuffer(long userId, @Nonnull CaughtPokeInfo pokeInfo) {
        CaughtPokeHelper helper = new CaughtPokeHelper(System.currentTimeMillis(), pokeInfo);
        caughtPokes.put(userId, helper);
    }

    private void removeCatchFromBuffer(long userId) {
        caughtPokes.remove(userId);
    }

    @Nullable
    private CaughtPokeInfo getCatchFromBuffer(long userId) {
        CaughtPokeHelper helper = caughtPokes.get(userId);
        if (helper != null) {
            return helper.pokeInfo;
        }

        return null;
    }

    /**
     * Timer to remove caught Pokemon from the buffer after a set time
     * Users that have not saved their Pokemon will lose their chance to keep it
     */
    private void startCaughtPokeTimer() {
        final long removeTimer = 120000;
        final Runnable clearUnclaimedPokes = () -> caughtPokes.entrySet()
                .removeIf(entry -> System.currentTimeMillis() - entry.getValue().timeStored > removeTimer);
        caughtPokeTimer.scheduleAtFixedRate(clearUnclaimedPokes, 60, 60, TimeUnit.SECONDS);
    }

    /**
     * Timer to help prevent users spamming the '!replacepoke help' command
     * Users of the command are put in this buffer and are then removed after a set time
     */
    private void startReplacePokeTimer() {
        final long removeTimer = 60000;
        final Runnable removeUser = () -> replacePokes.entrySet()
                .removeIf(entry -> System.currentTimeMillis() - entry.getValue() > removeTimer);
        replacePokeTimer.scheduleAtFixedRate(removeUser, 0, 30, TimeUnit.SECONDS);
    }

    public void pokeBattleCommand() {


    }
}