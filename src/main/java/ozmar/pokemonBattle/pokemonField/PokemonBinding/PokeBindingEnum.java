package ozmar.pokemonBattle.pokemonField.PokemonBinding;

public enum PokeBindingEnum {
    NONE(0),
    BIND(1),
    CLAMP(2),
    FIRE_SPIN(3),
    INFESTATION(4),
    MAGMA_STORM(5),
    SAND_TOMB(6),
    WHIRLPOOL(7),
    WRAP(8);

    private final int id;

    PokeBindingEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
