package ozmar.pokemonBattle.pokemonStats.enums;

public enum PokeStat {
    HP(0),
    ATK(1),
    DEF(2),
    SPC_ATK(3),
    SPC_DEF(4),
    SPD(5);

    private final int id;
    public static final PokeStat[] stats = values();

    PokeStat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
