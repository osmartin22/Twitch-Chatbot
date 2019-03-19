package ozmar.pokemonBattle.pokemonStats.enums;

public enum PokeStatStage {
    ATK_STAGE(0),
    DEF_STAGE(1),
    SPC_ATK_STAGE(2),
    SPC_DEF_STAGE(3),
    SPD_STAGE(4),
    ACC_STAGE(5),
    EVA_STAGE(6);

    private final int id;
    public static final PokeStatStage[] statStage = values();

    PokeStatStage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
