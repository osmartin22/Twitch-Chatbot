package ozmar.PokemonBattle.PokemonType;

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

    public PokeTypeEnum getType1() {
        return type1;
    }

    public PokeTypeEnum getType2() {
        return type2;
    }

    public PokeTypeEnum getType3() {
        return type3;
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
        double multiplier1 = PokeTypeChart.TYPE_CHART[pokeType.getType1().getId()][type1.getId()];
        double multiplier2 = PokeTypeChart.TYPE_CHART[pokeType.getType2().getId()][type2.getId()];
        double multiplier3 = PokeTypeChart.TYPE_CHART[pokeType.getType3().getId()][type3.getId()];

        return multiplier1 * multiplier2 * multiplier3;
    }

    // TODO: Add methods to rearrange Types after removing a type so that NONE always appears after the other types(not necessary)
    // TODO: Add methods to modify a type(maybe keep track of previous type), should be able to hand UNKNOWN(???)
}
