package ozmar.pokemonBattle.pokemonField.PokemonEntryHazard;

public enum PokeEntryHazardEnum {
    SPIKES(0),
    STEALTH_ROCK(1),
    STICKY_WEB(2),
    TOXIC_SPIKES(3);

    private final int id;

    PokeEntryHazardEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
