package ozmar.PokemonBattle.PokemonField.PokemonTerrain;

public enum PokeTerrainEnum {
    NONE(0),
    ELECTRIC_TERRAIN(1),
    GRASSY_TERRAIN(2),
    MISTY_TERRAIN(3),
    PSYCHIC_TERRAIN(4);

    private int id;

    PokeTerrainEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
