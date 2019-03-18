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
    private CatchPokeInterface catchPoke;
    private final int MAX_POKE_OWNED = 6;
    private Map<Long, CaughtPokeHelper> caughtPokes;
    private final ScheduledExecutorService caughtPokeTimer = Executors.newScheduledThreadPool(1);
    private Map<Long, Long> replacePokes;
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
        String pokeName = (input.length() < 3) ? "" : StringHelper.getFirstWord(input);

        String output;
        if (pokeName.equals("missingno")) {
            output = String.format("%s, use just '!catchpoke' for MissingNo moon2WUT", event.getUser().getName());
        } else {

            CaughtPokeInfo caughtPokeInfo = null;
            int initializeResult;
            if (pokeName.isEmpty()) {
                caughtPokeInfo = catchPoke.catchMissingNo();
                if (caughtPokeInfo != null) {
                    initializeResult = 1;
                } else {
                    initializeResult = catchPoke.initialize(RandomHelper.getRandNumInRange(1, 807));
                }
            } else {
                initializeResult = catchPoke.initialize(pokeName);
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
                output = String.format("%s, Poke not found, replace spaces/punctuation with '-' if it's not working",
                        event.getUser().getName());
            }

        }

        return output;
    }

    @Nonnull
    private String caughtPokeHelper(@Nonnull CommandEvent event, @Nonnull CaughtPokeInfo pokeInfo) {
        String output = "";
        long userId = event.getUser().getId();
        int pokeCount = db.getPokemonDao().getUsersPokemonCount(userId);
        if (pokeCount < MAX_POKE_OWNED) {
            db.getPokemonDao().insertPokemon(userId, pokeInfo.getPoke());
        } else {
            saveCatch(userId, pokeInfo);
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
        CaughtPokeInfo pokeInfo = getSavedCatch(userId);

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
                            removeCatch(userId);
                        }
                    } catch (NumberFormatException e) {
                        // No output if wrong input
                    }
                }
            }
        }

        return output;
    }

    private class CaughtPokeHelper {
        private long timeStored;
        private CaughtPokeInfo pokeInfo;

        CaughtPokeHelper(long timeStored, @NonNull CaughtPokeInfo pokeInfo) {
            this.timeStored = timeStored;
            this.pokeInfo = pokeInfo;
        }
    }

    private void saveCatch(long userId, @NonNull CaughtPokeInfo pokeInfo) {
        CaughtPokeHelper helper = new CaughtPokeHelper(System.currentTimeMillis(), pokeInfo);
        caughtPokes.put(userId, helper);
    }

    private void removeCatch(long userId) {
        caughtPokes.remove(userId);
    }

    @Nullable
    private CaughtPokeInfo getSavedCatch(long userId) {
        CaughtPokeHelper helper = caughtPokes.get(userId);
        if (helper != null) {
            return helper.pokeInfo;
        }

        return null;
    }

    private void startCaughtPokeTimer() {
        final long removeTimer = 240000;
        final Runnable clearUnclaimedPokes = () -> caughtPokes.entrySet()
                .removeIf(entry -> System.currentTimeMillis() - entry.getValue().timeStored > removeTimer);
        caughtPokeTimer.scheduleAtFixedRate(clearUnclaimedPokes, 60, 60, TimeUnit.SECONDS);
    }

    private void startReplacePokeTimer() {
        final long removeTimer = 60000;
        final Runnable removeUser = () -> replacePokes.entrySet()
                .removeIf(entry -> System.currentTimeMillis() - entry.getValue() > removeTimer);
        replacePokeTimer.scheduleAtFixedRate(removeUser, 0, 30, TimeUnit.SECONDS);
    }

    public void pokeBattleCommand() {


    }
}