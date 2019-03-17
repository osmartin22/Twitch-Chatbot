package ozmar.PokemonBattle.PokemonMoves;

public enum PokeMoveDamageClass {
    PHYSICAL(0),
    SPECIAL(1),
    STATUS(2);

    private int id;

    PokeMoveDamageClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
