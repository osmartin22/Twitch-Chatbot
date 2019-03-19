package ozmar.pokemonBattle.pokemonType;

import ozmar.pokemonBattle.PokeInfoHelper;

import java.util.ArrayList;
import java.util.List;

public class PokeType {
    List<PokeTypeEnum> typesList;
    PokeTypeEnum type1;
    PokeTypeEnum type2;
    PokeTypeEnum type3;

    // Pokemon all start with a max of 2 types at the start of a battle
    // Can gain a 3rd through certain moves
    public PokeType(PokeTypeEnum type1, PokeTypeEnum type2) {
        this.typesList = new ArrayList<>();
        this.typesList.add(type1);
        this.typesList.add(type2);
        this.typesList.add(PokeTypeEnum.NONE);
    }

    public PokeTypeEnum getType1() {
        return typesList.get(0);
    }

    public PokeTypeEnum getType2() {
        return typesList.get(1);
    }

    public PokeTypeEnum getType3() {
        return typesList.get(2);
    }

    /**
     * Gets the number of types the Pokemon has
     *
     * @return int
     */
    public int getNumOfTypes() {
        int count = 0;
        for (PokeTypeEnum type : typesList) {
            if (type != PokeTypeEnum.NONE && type != PokeTypeEnum.UNKNOWN) {
                count++;
            }
        }

        return count;
    }

    /**
     * Checks if the Pokemon is a multi type
     *
     * @return boolean
     */
    public boolean isMultiType() {
        return getNumOfTypes() > 1;
    }

    /**
     * Checks if the Pokemon has the passed in type
     *
     * @param type type wanting to find
     * @return boolean
     */
    public boolean isTypeFound(PokeTypeEnum type) {
        if (type != PokeTypeEnum.NONE) {
            for (PokeTypeEnum typeEnum : typesList) {
                if (typeEnum == type) {
                    return true;
                }
            }
        }

        return false;
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
        double multiplier1 = PokeInfoHelper.TYPE_CHART[pokeType.getType1().getId()][type1.getId()];
        double multiplier2 = PokeInfoHelper.TYPE_CHART[pokeType.getType2().getId()][type2.getId()];
        double multiplier3 = PokeInfoHelper.TYPE_CHART[pokeType.getType3().getId()][type3.getId()];

        return multiplier1 * multiplier2 * multiplier3;
    }

    public double damageReceivedMultiplier(PokeTypeEnum pokeTypeEnum) {
        double multiplier1 = PokeInfoHelper.TYPE_CHART[pokeTypeEnum.getId()][type1.getId()];
        double multiplier2 = PokeInfoHelper.TYPE_CHART[pokeTypeEnum.getId()][type2.getId()];
        double multiplier3 = PokeInfoHelper.TYPE_CHART[pokeTypeEnum.getId()][type3.getId()];

        return multiplier1 * multiplier2 * multiplier3;
    }

    // Might not need
    // Need to write more code to see if it can be useful somewhere
    private void rearrangeTypes() {
        /*
        Should be called whenever a Pokemon's type is removed and reorder types
        e.g.
        Type1 = Fire, Type2 = Flying, Type3 = None
        Pokemon uses Burn Up and loses its fire type

        Without rearranging it becomes
        Type1 = None, Type2 = Flying, Type3 = None

        Rearranging should be
        Type1 = Flying, Type2 = None, Type3 = None
        */
    }

    // TODO: Add methods to rearrange Types after removing a type so that NONE always appears after the other types(not necessary)
}
