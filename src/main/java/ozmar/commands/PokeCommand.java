package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.CaughtPokeInfo;
import ozmar.PokemonPoke;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.commands.interfaces.PokeCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;
import reactor.util.annotation.NonNull;

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
    private final int MAX_POKEDEX_INDEX = 807;
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
        String input = event.getCommand().trim().toLowerCase();

        // Shortest Pokemon name is 3 letters long (Mew, Muk)
//        String pokeInput = (input.length() < 3) ? "" : StringHelper.getFirstWord(input);
        String pokeInput = StringHelper.getFirstWord(input);

        String output;
        if (pokeInput.equals("missingno")) {
            output = String.format("%s, use '!catchpoke' or '!catchpoke <region>' for that one moon2WUT",
                    event.getUser().getName());
        } else {
            CaughtPokeInfo caughtPokeInfo = null;
            Integer pokedexIndex = getPokedexIndex(pokeInput);
            if (pokedexIndex != null && (pokedexIndex < 1 || pokedexIndex > 807)) {
                pokeInput = getUnReleasedPokeName(pokedexIndex);
                pokedexIndex = null;
            }

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
                        initializeResult = catchPoke.initialize(RandomHelper.getRandNumInRange(1, MAX_POKEDEX_INDEX));
                    }
                } else {
                    initializeResult = catchPoke.initialize(pokedexIndex);
                }
            } else {
                initializeResult = 1;
            }

            if (initializeResult != -1) {
                if (caughtPokeInfo == null) {
                    caughtPokeInfo = catchPoke.attemptCatch();
                }
                output = event.getUser().getName() + caughtPokeInfo.getCatchResultString();

                if (caughtPokeInfo.isCaptured()) {
                    output += caughtPokeHelper(event, caughtPokeInfo);
                }
            } else {
                output = String.format("%s, Poke not found, replace spaces/punctuation in a name with '-' if it's not working",
                        event.getUser().getName());
            }

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
    private Integer getPokedexIndex(@NonNull String index) {
        Integer num;
        try {
            num = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            num = null;
        }

        return num;
    }


    /**
     * Gets the name of the Pokemon from the index
     * These Pokemon have been announced but are not part of the PokeApi
     *
     * @param index index of the Poke
     * @return String
     */
    @Nonnull
    private String getUnReleasedPokeName(int index) {
        String name;
        switch (index) {
            case 808:
                name = "meltan";
                break;
            case 809:
                name = "melmetal";
                break;
            case 810:
                name = "grookey";
                break;
            case 813:
                name = "scorbunny";
                break;
            case 816:
                name = "sobble";
                break;
            default:
                name = "";
        }
        return name;
    }

    /**
     * Checks if the user already has the max number of Pokemon
     * If they have less than the max, the caught Pokemon is saved to the db
     * If they have more than the max, the user must specify if they want the Pokemon and the caught
     * Pokemon is stored in a buffer
     *
     * @param event    info about the user
     * @param pokeInfo info about the captured Pokemon
     * @return string
     */
    @Nonnull
    private String caughtPokeHelper(@Nonnull CommandEvent event, @Nonnull CaughtPokeInfo pokeInfo) {
        String output = "";
        long userId = event.getUser().getId();
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

        CaughtPokeHelper(long timeStored, @NonNull CaughtPokeInfo pokeInfo) {
            this.timeStored = timeStored;
            this.pokeInfo = pokeInfo;
        }
    }

    private void saveCatchToBuffer(long userId, @NonNull CaughtPokeInfo pokeInfo) {
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