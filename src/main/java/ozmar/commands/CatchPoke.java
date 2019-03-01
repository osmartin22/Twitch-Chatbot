package ozmar.commands;

import org.springframework.util.StringUtils;
import ozmar.commands.interfaces.CatchPokeInterface;
import ozmar.utils.RandomHelper;
import poke_models.pokemon.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CatchPoke implements CatchPokeInterface {
    private Pokemon pokemon;
    private PokemonSpecies pokemonSpecies;
    private Nature nature;
    private Set<Integer> specialFormPokemonSet;
    private Set<String> unreleasedPokemon;
    private boolean isUnreleased;

    public CatchPoke() {
        // unown=201, burmy=412, shellos=422, gastrodon=423, deerling=585, sawsbuck=586, vivillon=666
        // flabebe=669, floette=670, florges=671, furfrou=676
        specialFormPokemonSet = new HashSet<>(Arrays.asList(201, 412, 422, 423, 585, 586, 666, 669, 670, 671, 676));
        unreleasedPokemon = new HashSet<>(Arrays.asList("meltan", "melmetal", "grookey", "scorbunny", "sobble"));
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
        try {
            isUnreleased = unreleasedPokemon.contains(pokeName);
            if (pokeName.isEmpty()) {
                pokemonSpecies = PokemonSpecies.getById(pokeId);
            } else {
                if (isUnreleased) {
                    pokemonSpecies = PokemonSpecies.getById(150);   // Use Mew as the default template
                    pokemon = Pokemon.getById(150);
                    pokemon.setName(pokeName);
                    PokemonForm tempPokeForm = new PokemonForm();
                    tempPokeForm.setName(pokeName);
                    List<PokemonForm> temp = new ArrayList<>(Collections.singletonList(tempPokeForm));
                    pokemon.setForms(temp);
                } else {
                    pokemonSpecies = PokemonSpecies.getByName(pokeName);
                }
            }

            if (pokemonSpecies != null && !isUnreleased) {
                int pokeVariety = pokemonSpecies.getVarieties().size();
                int randVariety = RandomHelper.getRandNumInRange(0, pokeVariety - 1);
                PokemonSpeciesVariety pokemonSpeciesVariety = pokemonSpecies.getVarieties().get(randVariety);
                pokemon = Pokemon.getByName(pokemonSpeciesVariety.getPokemon().getName());
                nature = Nature.getById(RandomHelper.getRandNumInRange(1, 25)); // Only 25 natures exist
            } else if (pokemonSpecies == null && !isUnreleased) {
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Failed to initialize: " + e.getMessage());
            return -1;
        }

        return 1;
    }

    /**
     * Creates a string to return whether the pokemon was captured or not
     *
     * @return String
     */
    @Nullable
    @Override
    public String attemptCatch() {
        int gender;
        // Meowstic is a special case pokemon for gender
        // The api returns the gender for Meowstic, so no need to decide its gender here
        if (pokemon.getId() == 678 || isUnreleased) {
            gender = -1;
        } else {
            gender = pokemonSpecies.getGenderRate();
        }

        String pokeGender = decideGender(gender);
        if (pokeGender == null) {
            return null;
        }

        String output;
        output = decideIfShiny() + nature.getName() + pokeGender + decidePokeName(pokemon);

        // index 5 is for the hp stat
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
        if (decideCapture(captureRate, pokemon.getStats().get(5).getBaseStat())) {
            output = String.format(" caught a %s", output);
        } else {
            output = String.format(" let a %s get away", output);
        }

        return output;
    }

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

    @Nonnull
    private String decideIfShiny() {
        String shinyStatus = "";
        if (RandomHelper.getRandNumInRange(1, 100) < 16) {
            shinyStatus = "shiny ";
        }
        return shinyStatus;
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
     * @return String
     */
    @Nullable
    private String decideGender(int genderRate) {
        String gender = null;
        try {
            if (genderRate == -1) { // Genderless
                gender = " ";
            } else if (genderRate == 0) {   // Only males
                gender = " male ";
            } else if (genderRate == 8) {   // Only females
                gender = " female ";
            } else {
                int genderChance = RandomHelper.getRandNumInRange(1, 8);    // Gender ratios are done in eighths
                if (genderChance <= genderRate) {
                    gender = " female ";
                } else {
                    gender = " male ";
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to get gender: " + e.getMessage());
        }

        return gender;
    }

    /**
     * Decides whether pokemon is captured or not
     *
     * @param captureRate capture rate of the pokemon
     * @param hpMax       max hp of the pokemon at level 1
     * @return boolean
     */
    private boolean decideCapture(int captureRate, int hpMax) {
//        int captureRate = pokemonSpecies.getCaptureRate();
//        int hpMax = pokemon.getStats().get(5).getBaseStat();   // index 5 is for the hp stat

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
}
