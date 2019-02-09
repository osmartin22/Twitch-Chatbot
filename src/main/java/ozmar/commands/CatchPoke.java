package ozmar.commands;

import org.springframework.util.StringUtils;
import ozmar.utils.RandomHelper;
import poke_models.pokemon.Nature;
import poke_models.pokemon.Pokemon;
import poke_models.pokemon.PokemonSpecies;

public class CatchPoke {
    private Pokemon pokemon;
    private PokemonSpecies pokemonSpecies;
    private Nature nature;

    public CatchPoke() {

    }

    /**
     * Initializes Pokemon specific objects from the api or cache using the pokemon id
     * On failure of creating the objects, -1 is returned to inform a failure
     *
     * @param pokeId id of pokemon to get
     * @return int
     */
    public int initialize(int pokeId) {
        try {
            pokemon = Pokemon.getById(pokeId);
            pokemonSpecies = PokemonSpecies.getById(pokeId);
            nature = Nature.getById(RandomHelper.getRandNumInRange(1, 25)); // Only 25 natures exist
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
    public String attemptCatch() {
        String pokeGender = getPokeGender();
        if (pokeGender == null) {
            return "";
        }
        String natureName = nature.getName();
        String pokeName = StringUtils.capitalize(pokemon.getName());

        String output = natureName + pokeGender + " " + pokeName;
        if (decideCapture()) {
            output = " caught a " + output;
        } else {
            output = " let a " + output + " get away";
        }

        return output;
    }

    /**
     * Returns the gender of a pokemon
     * Gender rates are done in eights and are given from the female rate from the api
     * i.e. gender rate = 1 means a 1/8 chance the pokemon is female, 7/8 it is male
     * 0 means the pokemon can only be male
     * 8 means the pokemon can only be female
     * -1 means the pokemon is genderless
     *
     * @return String
     */
    private String getPokeGender() {
        String gender;
        try {
            int genderRate = pokemonSpecies.getGenderRate();
            if (genderRate == -1) { // Genderless
                gender = "";
            } else if (genderRate == 0) {   // Only males
                gender = "male ";
            } else if (genderRate == 8) {   // Only females
                gender = "female ";
            } else {
                int genderChance = RandomHelper.getRandNumInRange(1, 8);    // Gender ratios are done in eighths
                if (genderRate <= genderChance) {
                    gender = "female ";
                } else {
                    gender = "male ";
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to get gender: " + e.getMessage());
            return null;
        }

        return gender;
    }

    /**
     * Decides whether pokemon is captured or not
     *
     * @return boolean
     */
    private boolean decideCapture() {
        int captureRate = pokemonSpecies.getCaptureRate();
        int hpMax = pokemon.getStats().get(5).getBaseStat();   // index 5 is for the hp stat

        // Add status and lower health even more only if pokemon has a low catch rate(usually a legendary)
        double hpCurr = (captureRate == 3) ? hpMax * .1 : hpMax * .25;
        double status = (captureRate == 3) ? 1.5 : 1;

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
