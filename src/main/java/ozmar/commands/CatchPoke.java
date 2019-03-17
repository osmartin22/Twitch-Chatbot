package ozmar.commands;

import org.springframework.util.StringUtils;
import ozmar.CaughtPokeInfo;
import ozmar.PokemonPoke;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.enums.PokemonGender;
import ozmar.utils.RandomHelper;
import ozmar.utils.StringHelper;
import poke_models.pokemon.*;
import reactor.util.annotation.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CatchPoke implements CatchPokeInterface {
    private Pokemon pokemon;
    private PokemonSpecies pokemonSpecies;
    private Nature nature;
    private Set<Integer> specialFormPokemonSet;
    private Set<String> unreleasedPokemon;
    private boolean isUnreleased;

    private Map<Long, CaughtPokeHelper> caughtPokes;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CatchPoke() {
        // unown=201, burmy=412, shellos=422, gastrodon=423, deerling=585, sawsbuck=586, vivillon=666
        // flabebe=669, floette=670, florges=671, furfrou=676
        specialFormPokemonSet = new HashSet<>(Arrays.asList(201, 412, 422, 423, 585, 586, 666, 669, 670, 671, 676));
        unreleasedPokemon = new HashSet<>(Arrays.asList("meltan", "melmetal", "grookey", "scorbunny", "sobble"));
        caughtPokes = new HashMap<>();
        startTimer();
    }

    /**
     * Initializes Pokemon specific objects from the api or cache using the pokemon id
     * On failure of creating the objects, -1 is returned to inform a failure
     *
     * @param pokeId id of pokemon to get
     * @return int
     */
    @Override
    public int initialize(int pokeId) {
        return initialize(pokeId, "");
    }

    /**
     * Initializes Pokemon specific objects from the api or cache using the pokemon name
     * On failure of creating the objects, -1 is returned to inform a failure
     *
     * @param pokeName name of pokemon to get
     * @return int
     */
    @Override
    public int initialize(@Nonnull String pokeName) {
        return initialize(-1, pokeName);
    }

    /**
     * @param pokeId   id of the pokemon
     * @param pokeName name of the pokemon
     * @return int
     */
    private int initialize(int pokeId, String pokeName) {
        pokeName = pokeName.toLowerCase();
        try {
            isUnreleased = unreleasedPokemon.contains(pokeName);
            if (pokeName.isEmpty()) {
                pokemonSpecies = PokemonSpecies.getById(pokeId);
            } else {
                if (isUnreleased) {
                    // Use Mewtwo as the default template
                    // Modify the parts that are used so that it imitates the unreleased Pokemon
                    pokemonSpecies = PokemonSpecies.getById(150);
                    pokemon = Pokemon.getById(150);
                    pokemon.setName(pokeName);
                    PokemonForm tempPokeForm = new PokemonForm();
                    tempPokeForm.setName(pokeName);
                    pokemon.setForms(Collections.singletonList(tempPokeForm));
                } else {
                    pokemonSpecies = PokemonSpecies.getByName(pokeName);
                }
            }

            if (pokemonSpecies != null && !isUnreleased) {
                int pokeVariety = pokemonSpecies.getVarieties().size();
                int randVariety = RandomHelper.getRandNumInRange(0, pokeVariety - 1);
                PokemonSpeciesVariety pokemonSpeciesVariety = pokemonSpecies.getVarieties().get(randVariety);
                pokemon = Pokemon.getByName(pokemonSpeciesVariety.getPokemon().getName());
            }

            nature = Nature.getById(RandomHelper.getRandNumInRange(1, 25)); // Only 25 natures exist

            if (pokemonSpecies == null || pokemon == null || nature == null) {
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Failed to initialize: " + e.getMessage());
            return -1;
        }

        return 1;
    }

    /**
     * Creates a CaughtPokeInfo object to return info about the pokemon and its capture status
     *
     * @return CaughtPokeInfo
     */
    @NonNull
    @Override
    public CaughtPokeInfo attemptCatch() {
        PokemonPoke poke = getPokeResult();
        boolean isCaptured = isCaptured(pokemon, pokemonSpecies);
        String catchResult = getCaptureResult(poke, isCaptured);
        return new CaughtPokeInfo(poke, isCaptured, isUnreleased, catchResult);
    }

    /**
     * Creates the Pokemon
     *
     * @return PokemonPoke
     */
    @NonNull
    private PokemonPoke getPokeResult() {
        PokemonPoke poke = new PokemonPoke();

        // Meowstic is a special case pokemon for gender
        // The api returns the gender for Meowstic, so no need to decide its gender here
        PokemonGender pokemonGender;
        if (isUnreleased || pokemonSpecies.getId() == 678) {
            pokemonGender = PokemonGender.genders[0];
        } else {
            pokemonGender = decideGender(pokemonSpecies.getGenderRate());
        }

        poke.setPokemonSpecies(pokemonSpecies.getName());
        poke.setPokemonName(decidePokeName(pokemon));
        poke.setPokemonNickName(poke.getPokemonName());
        poke.setShiny(isShiny(pokemonSpecies.getCaptureRate()));
        poke.setNature(nature.getName());
        poke.setGender(pokemonGender);
        poke.setPokemonMoves(new HashSet<>());

        return poke;
    }

    /**
     * Gets the output of the capture result
     *
     * @param poke Pokemon info
     * @return String
     */
    @NonNull
    private String getCaptureResult(@NonNull PokemonPoke poke, boolean isCaptured) {
        String output = poke.getPokeString();
        if (isCaptured) {
            output = (StringHelper.startsWithVowel(output)) ? String.format(" caught an %s", output) :
                    String.format(" caught a %s", output);
        } else {
            output = (StringHelper.startsWithVowel(output)) ? String.format(" let an %s get away", output) :
                    String.format(" let a %s get away", output);
        }

        return output;
    }

    /**
     * Picks a Pokemon form for the given pokemon
     *
     * @param pokemon Pokemon to choose from
     * @return String
     */
    @Nonnull
    private String decidePokeName(@Nonnull Pokemon pokemon) {
        String pokeName;
        // Check for pokemon that have unique forms, but aren't found in PokemonSpeciesVariety
        if (specialFormPokemonSet.contains(pokemon.getId())) {
            List<PokemonForm> pokemonForms = pokemon.getForms();
            PokemonForm randomForm = pokemon.getForms().get(RandomHelper.getRandNumInRange(0, pokemonForms.size() - 1));
            pokeName = StringUtils.capitalize(randomForm.getName());
        } else {
            pokeName = StringUtils.capitalize(pokemon.getName());
        }

        return pokeName;
    }

    /**
     * Decides if the pokemon will be a shiny
     * Rarer Pokemon have a higher chance of being shiny
     *
     * @return boolean
     */
    private boolean isShiny(int captureRate) {
        boolean isShiny;
        if (captureRate < 45) {
            isShiny = RandomHelper.getRandNumInRange(1, 100) < 16;
        } else {
            isShiny = RandomHelper.getRandNumInRange(1, 100) < 6;
        }

        return isShiny;
    }

    /**
     * Returns the gender of a pokemon
     * Gender rates are done in eights and are given from the female rate from the api
     * i.e. gender rate = 1 means a 1/8 chance the pokemon is female, 7/8 it is male
     * 0 means the pokemon can only be male
     * 8 means the pokemon can only be female
     * -1 means the pokemon is genderless
     *
     * @param genderRate gender rate of the pokemon
     * @return PokemonGender
     */
    private PokemonGender decideGender(int genderRate) {
        PokemonGender gender;

        if (genderRate == -1) {
            gender = PokemonGender.genders[0];
        } else if (genderRate == 0) {
            gender = PokemonGender.genders[1];
        } else if (genderRate == 8) {
            gender = PokemonGender.genders[2];
        } else {
            int genderChance = RandomHelper.getRandNumInRange(1, 8);    // Gender ratios are done in eighths
            if (genderChance <= genderRate) {
                gender = PokemonGender.genders[2];
            } else {
                gender = PokemonGender.genders[1];
            }
        }

        return gender;
    }

    /**
     * Decides whether pokemon was captured
     *
     * @return boolean
     */
    private boolean isCaptured(@NonNull Pokemon pokemon, @NonNull PokemonSpecies pokemonSpecies) {
        int hpMax = pokemon.getStats().get(5).getBaseStat();
        int captureRate;
        if (isUnreleased) {
            String name = pokemon.getName();
            if (name.equals("meltan") || name.equals("melmetal")) {
                captureRate = 3;
            } else {
                captureRate = 45;
            }
        } else {
            captureRate = pokemonSpecies.getCaptureRate();
        }


        // Add status and lower health even more only if pokemon has a low catch rate(usually a legendary)
        double hpCurr = (captureRate == 3) ? hpMax * .05 : hpMax * .15;
        double status = (captureRate == 3) ? 2 : 1;

        // Catch rate formula Gen III - IV
        // a = ((3 * HPmax - 2 * HPcurr) * rate * BONUSball * BONUSstate) / (3 * HPmax)
        double a = ((3 * hpMax - 2 * hpCurr) * captureRate * 2 * status) / (3 * hpMax);    // Using ultra ball(2x)
        if (a > 255) {   // Always caught
            return true;
        }

        // Shake probability
        // b = 65536 / (255/a)^0.1875
        long b = Math.round(65536 / Math.pow((255 / a), 0.1875));

        for (int i = 0; i < 4; i++) {   //Shake ball 4 times
            int shakeNum = RandomHelper.getRandNumInRange(0, 65535);
            if (shakeNum >= b) {
                return false;
            }
        }

        return true;
    }


    private class CaughtPokeHelper {
        private long timeStored;
        private CaughtPokeInfo pokeInfo;

        CaughtPokeHelper(long timeStored, @NonNull CaughtPokeInfo pokeInfo) {
            this.timeStored = timeStored;
            this.pokeInfo = pokeInfo;
        }
    }

    @Override
    public void saveCatch(long userId, @NonNull CaughtPokeInfo pokeInfo) {
        CaughtPokeHelper helper = new CaughtPokeHelper(System.currentTimeMillis(), pokeInfo);
        caughtPokes.put(userId, helper);
    }

    @Override
    public void removeCatch(long userId) {
        caughtPokes.remove(userId);
    }

    @Nullable
    @Override
    public CaughtPokeInfo getSavedCatch(long userId) {
        CaughtPokeHelper helper = caughtPokes.get(userId);
        if (helper != null) {
            return helper.pokeInfo;
        }

        return null;
    }

    public void startTimer() {
        final long removeTimer = 240000;    // Temporary time while users get used to the updated commands
        final Runnable clearUnclaimedPokes = () -> caughtPokes.entrySet()
                .removeIf(entry -> System.currentTimeMillis() - entry.getValue().timeStored > removeTimer);

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(clearUnclaimedPokes, 60, 60, TimeUnit.SECONDS);
    }
}
