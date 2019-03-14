package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeTypeEnum;

public class PokeType {
    PokeTypeEnum type1;
    PokeTypeEnum type2;
    PokeTypeEnum type3;

    // Pokemon all start with a max of 2 types at the start of a battle
    // Can gain a 3rd through certain moves
    public PokeType(PokeTypeEnum type1, PokeTypeEnum type2) {
        this.type1 = type1;
        this.type2 = type2;
        this.type2 = PokeTypeEnum.NONE;
    }

    /**
     * Checks if the used move will have STAB
     *
     * @param moveType enum for the moves type
     * @return boolean
     */
    public boolean moveWillHaveStab(PokeTypeEnum moveType) {
        boolean hasStab = false;
        if (moveType != PokeTypeEnum.NONE && moveType != PokeTypeEnum.UNKNOWN) {
            hasStab = type1 == moveType || type2 == moveType || type3 == moveType;
        }

        return hasStab;
    }

    /**
     * Gets the total multiplier the move will have on the given Pokemon
     *
     * @param pokeType contains all of the  types of the given Pokemon
     * @return double
     */
    public double damageReceivedMultiplier(PokeType pokeType) {
        // TODO: Compare the types to an Object to be created later that contains the damage type chart
        // TODO: Should be able to handle UNKNOWN(???)
        return 0;
    }

    // TODO: Add methods to rearrange Types after removing a type so that NONE always appears after the other types
    // TODO: Add methods to modify a type(maybe keep track of previous type), should be able to hand UNKNOWN(???)
}
