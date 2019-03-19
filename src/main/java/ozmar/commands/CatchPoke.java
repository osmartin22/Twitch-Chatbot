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

    /*
    Capture Rate        Health   Pokeball       Status          Percent of capture
    255 (Caterpie)          25          1           1           87.193
    235 (Shinx)             25          1           1           82.004
    225 (Stunky)            25          1           1           79.391
    200 (Swirlix)           20          1           1           74.87
    190 (Buizel)            20          1           1           72.023
    180 (Honedge)           30          1           1           65.13
    150 (Clefairy)          30          1           1           56.813
    140 (Sandygast)         40          1           1           50.545
    120 (Weepinbell)        40          1           1           45.022
     90 (Lampent)           50          1.5         1           45.761
     75 (Azumarill          40          1.5         1           42.897
     60 (Klang)             40          1.5         1           36.287
     45 (VilePlume)         40          2           1           36.287
     30 (Togekiss)          40          2           1.5         36.287
     25 (Mantyke)           40          2           1.5         31.65
      3 (Mewtwo)            1           2           2.5         11.89
     */

public class CatchPoke implements CatchPokeInterface {
    private Pokemon pokemon;
    private PokemonSpecies pokemonSpecies;
    private Nature nature;
    private final Set<Integer> specialFormPokemonSet;
    private final Set<String> unreleasedPokemon;
    private Set<Integer> pokemonWithMegaForm;
    private boolean isUnreleased;
    private int pokeShake;

    public CatchPoke() {
        // unown=201, burmy=412, shellos=422, gastrodon=423, deerling=585, sawsbuck=586, vivillon=666
        // flabebe=669, floette=670, florges=671, furfrou=676
        specialFormPokemonSet = new HashSet<>(Arrays.asList(201, 412, 422, 423, 585, 586, 666, 669, 670, 671, 676));
        unreleasedPokemon = new HashSet<>(Arrays.asList("meltan", "melmetal", "grookey", "scorbunny", "sobble"));
        pokemonWithMegaForm = new HashSet<>(Arrays.asList(3, 6, 9, 65, 94, 115, 127, 130, 142, 150, 181, 212, 214, 229,
                248, 257, 282, 306, 310, 354, 359, 445, 448, 15, 18, 80, 208, 260, 302, 319, 323, 334, 362, 373, 376,
                380, 381, 384, 428, 475, 531, 719));
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
                PokemonSpeciesVariety pokemonSpeciesVariety = getRandomVariety();
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
     * Gets a "random" variety of a Pokemon Species
     * Mega forms of a Pokemon have a lower chance of being selected
     * So far every Pokemon with a mega evolution does not have ant other forms
     *
     * @return PokemonSpeciesVariety
     */
    @Nonnull
    private PokemonSpeciesVariety getRandomVariety() {
        int randVariety;
        int pokeVariety = pokemonSpecies.getVarieties().size();

        if (pokemonWithMegaForm.contains(pokemonSpecies.getId())) {
            if (RandomHelper.getRandNumInRange(1, 4) > 3) {
                // Skip over the base form and select a Pokemon's mega form, some have more than 1
                randVariety = RandomHelper.getRandNumInRange(1, pokeVariety - 1);
            } else {
                randVariety = 0;
            }

        } else {
            randVariety = RandomHelper.getRandNumInRange(0, pokeVariety - 1);
        }

        return pokemonSpecies.getVarieties().get(randVariety);
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
        pokeShake = 0;
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
                    String.format(" let a %s get away. %s", output, getShakeOutput());
        }

        return output;
    }

    /**
     * Gets the output the games give based on the number of shakes
     *
     * @return String
     */
    @Nonnull
    private String getShakeOutput() {
        String output;
        switch (pokeShake) {
            case 0:
            default:
                output = "Oh no! It broke free!";
                break;
            case 1:
                output = "Aww! It appeared to be caught!";
                break;

            case 2:
                output = "Aargh! Almost had it!";
                break;
            case 3:
                output = "Gah! It was so close, too!";
                break;
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
        int randomNum = RandomHelper.getRandNumInRange(1, 100);
        if (captureRate <= 3) {
            isShiny = randomNum <= 30;

        } else if (captureRate <= 35) {
            isShiny = randomNum <= 25;

        } else if (captureRate <= 45) {
            isShiny = randomNum <= 20;

        } else if (captureRate <= 100) {
            isShiny = randomNum <= 15;

        } else if (captureRate <= 180) {
            isShiny = randomNum <= 10;

        } else {
            isShiny = randomNum <= 5;
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
     * Decides whether the Pokemon was captured
     * Uses the catch rate from Gen VI from Bulbapedia
     * Ignores Capture Power as that is based on other parts of the game the user has played through
     * a = ((3 * HPmax - 2 * HPcurr) * rate * BONUSball * BONUSstate) / (3 * HPmax)
     *
     * @return boolean
     */
    private boolean isCaptured(@NonNull Pokemon pokemon, @NonNull PokemonSpecies pokemonSpecies) {
        int hpMax = pokemon.getStats().get(5).getBaseStat();
        int captureRate = getCaptureRate(pokemon, pokemonSpecies);

        double hpCurr = decideHpLeft(captureRate, hpMax);
        double status = decideStatus(captureRate);
        double pokeBall = decidePokeBall(captureRate);
        double a = ((3 * hpMax - 2 * hpCurr) * captureRate * pokeBall * status) / (3 * hpMax);
        boolean isCaught;
        if (a > 255) {   // Always caught
            isCaught = true;
        } else {
            isCaught = pokeBallShake(a);
        }

        return isCaught;
    }

    /**
     * Gets the capture rate of the given Pokemon
     * At the time Meltan and Melmetal are released but are not present in the API, their in game capture rate is 3
     * The newly revealed Gen VIII starters have no information about them and are assumed to have the same capture
     * rate as the other starter Pokemon, 45
     *
     * @return int
     */
    private int getCaptureRate(@NonNull Pokemon pokemon, @NonNull PokemonSpecies pokemonSpecies) {
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

        return captureRate;
    }

    /**
     * Checks if the shake test is passed
     * Uses the Shake formula from Gen VI gotten from Bulbapedia
     * b = 65536 / (255/a)^0.1875
     *
     * @param a value
     * @return boolean
     */
    private boolean pokeBallShake(double a) {
        long b = Math.round(65536 / Math.pow((255 / a), 0.1875));

        boolean isCaught = true;
        for (int i = 0; i < 4; i++) {   //Shake ball 4 times
            int shakeNum = RandomHelper.getRandNumInRange(0, 65535);
            if (shakeNum >= b) {
                pokeShake = i;
                isCaught = false;
                break;
            }
        }

        return isCaught;
    }

    /**
     * Decides the poke ball used from the capture rate
     *
     * @param captureRate capture rate of the Pokemon
     * @return double
     */
    private double decidePokeBall(int captureRate) {
        double pokeBall;
        if (captureRate <= 45) {
            pokeBall = 2.0;
        } else if (captureRate <= 90) {
            pokeBall = 1.5;
        } else {
            pokeBall = 1.0;
        }

        return pokeBall;
    }

    /**
     * Decides the hp left from the capture rate
     *
     * @param captureRate capture rate of the Pokemon
     * @param hpMax       max Hp of the Pokemon
     * @return double
     */
    private double decideHpLeft(int captureRate, int hpMax) {
        double hpLeft;
        if (captureRate == 90) {
            hpLeft = hpMax * 0.5;
        } else {
            if (captureRate <= 3) {
                hpLeft = hpMax * 0.01;
            } else if (captureRate <= 140) {
                hpLeft = hpMax * 0.4;
            } else if (captureRate <= 180) {
                hpLeft = hpMax * 0.3;
            } else if (captureRate <= 200) {
                hpLeft = hpMax * 0.2;
            } else {
                hpLeft = hpMax * 0.25;
            }
        }

        return hpLeft;
    }

    /**
     * Decides the status multiplier from the capture rate
     *
     * @param captureRate capture rate of the Pokemon
     * @return double
     */
    private double decideStatus(int captureRate) {
        double statusMultiplier;
        if (captureRate <= 3) {
            statusMultiplier = 2.5;
        } else if (captureRate <= 30) {
            statusMultiplier = 1.5;
        } else {
            statusMultiplier = 1;
        }

        return statusMultiplier;
    }

    /**
     * Attempts to catch MissingNo, a glitch Pokemon from Gen I
     *
     * @return CaughtPokeInfo
     */
    @Nullable
    @Override
    public CaughtPokeInfo catchMissingNo() {
        boolean caught = RandomHelper.getRandNumInRange(1, 807) == 117;
        if (caught) {
            Nature nature = Nature.getById(RandomHelper.getRandNumInRange(1, 25)); // Only 25 natures exist

            PokemonPoke poke = new PokemonPoke();
            poke.setId(-117);
            poke.setPokemonSpecies("missingno");
            poke.setPokemonName("MissingNo");
            poke.setPokemonNickName("MissingNo");
            poke.setShiny(RandomHelper.getRandNumInRange(1, 4) > 1);
            poke.setGender(PokemonGender.NO_GENDER);
            poke.setNature(nature.getName());
            poke.setPokemonMoves(new HashSet<>());

            String output = getCaptureResult(poke, true) + " moon2POGGYWOGGY";
            return new CaughtPokeInfo(poke, true, true, output);
        }

        return null;
    }
}
