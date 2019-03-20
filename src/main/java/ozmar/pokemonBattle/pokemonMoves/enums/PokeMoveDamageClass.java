package ozmar.pokemonBattle.pokemonMoves.enums;

public enum PokeMoveDamageClass {
    PHYSICAL(0),
    SPECIAL(1),
    STATUS(2);

    private final int id;
    public static final PokeMoveDamageClass[] damageClass = values();

    PokeMoveDamageClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
