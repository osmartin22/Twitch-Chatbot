package ozmar.pokemonBattle.pokemonField.PokemonSideProtection;

public enum PokeSideProtectionEnum {
    CRAFTY_SHIELD(1),
    MAT_BLOCK(2),
    QUICK_GUARD(3),
    WIDE_GUARD(4);

    private final int id;

    PokeSideProtectionEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
