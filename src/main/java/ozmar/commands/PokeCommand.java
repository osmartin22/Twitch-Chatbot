package ozmar.commands;

import com.github.twitch4j.chat.events.CommandEvent;
import ozmar.CaughtPokeInfo;
import ozmar.PokemonPoke;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.commands.interfaces.PokeCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class PokeCommand implements PokeCommandInterface {

    private final DatabaseHandlerInterface db;
    private CatchPokeInterface catchPoke;
    private final int MAX_POKE_OWNED = 5;

    public PokeCommand(DatabaseHandlerInterface db, CatchPokeInterface catchPoke) {
        this.db = db;
        this.catchPoke = catchPoke;
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
        // Only 807 pokemon currently exist
        String pokeName = (input.length() < 3) ? "" : StringHelper.getFirstWord(input);
        int initializeResult = (!pokeName.isEmpty()) ? catchPoke.initialize(pokeName) :
                catchPoke.initialize(RandomHelper.getRandNumInRange(1, 807));

        String output;
        if (initializeResult != -1) {
            CaughtPokeInfo caughtPokeInfo = catchPoke.attemptCatch();
            output = event.getUser().getName() + caughtPokeInfo.getCatchResultString();
            if (caughtPokeInfo.isCaptured()) {
                long userId = event.getUser().getId();
                int pokeCount = db.getPokemonDao().getUsersPokemonCount(userId);
                if (pokeCount < MAX_POKE_OWNED) {
                    db.getPokemonDao().insertPokemon(userId, caughtPokeInfo.getPoke());
                } else {
                    catchPoke.saveCatch(userId, caughtPokeInfo);
                    output += ", use !replacepoke [Num] to replace a Poke with this one, use !mypoke to check numbering";
                }
            }

        } else {
            output = String.format("%s, I couldn't find that Pokemon," +
                    " replace spaces/punctuation with '-' if a Pokemon name is not working", event.getUser().getName());
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
            output = String.format("%s, you have 0/%s Pokemon, use !catchpoke to get some",
                    event.getUser().getName(), MAX_POKE_OWNED);
        } else {
            int count = 1;
            output = String.format(" %s, you have %s/%s Pokemon,", event.getUser().getName(),
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
        CaughtPokeInfo pokeInfo = catchPoke.getSavedCatch(userId);

        String output = null;
        if (pokeInfo != null) {
            String userName = event.getUser().getName();
            String input = event.getCommand().trim();
            if (input.isEmpty()) {
                output = String.format("%s, specify the pokeNumber, use !mypoke to see your Pokemon's number", userName);
            } else {
                int pokeNum;
                try {
                    pokeNum = Integer.valueOf(StringHelper.getFirstWord(input));
                    if (pokeNum > 0 && pokeNum <= MAX_POKE_OWNED) {
                        db.getPokemonDao().updatePokemon(userId, pokeInfo.getPoke(), pokeNum);
                        catchPoke.removeCatch(userId);
                    } else {
                        output = String.format("%s, number not found, use !mypoke to see your Pokemon's number", userName);
                    }
                } catch (NumberFormatException e) {
                    output = String.format("%s, that was not a number, use !mypoke to see your Pokemon's number", userName);
                }
            }
        }

        return output;
    }
}