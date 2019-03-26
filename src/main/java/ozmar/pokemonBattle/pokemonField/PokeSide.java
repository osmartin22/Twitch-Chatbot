package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonField.PokemonEntryHazard.PokeEntryHazard;
import ozmar.pokemonBattle.pokemonField.PokemonSideProtection.PokeSideProtection;

public class PokeSide {
    private final int position;
    private final PokeEntryHazard entryHazard;
    private final PokeSideProtection protection;

    // Hold other effects that are only for one side

    public PokeSide(int position) {
        this.position = position;
        this.entryHazard = new PokeEntryHazard();
        this.protection = new PokeSideProtection();
    }
}
