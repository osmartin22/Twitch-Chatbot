package ozmar.pokemonBattle.pokemonMoves.enums;

public enum PokeTarget {
    ANY_ADJ_FOE_POKE(0),
    ANY_ADJ_POKE(1),
    ANY_POKE(2),
    USER_OR_ANY_ADJ_ALLY(3),
    ALL_ADJ_FOES(4),
    ALL_ADJ_POKE(5),
    ALL_FOES(6),
    ALL_POKE(7),
    ONE_ADJ_ALLY(8),
    USER(9),
    USER_AND_ALLIES(10);

    private final int id;
    public static final PokeTarget[] target = values();

    PokeTarget(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
