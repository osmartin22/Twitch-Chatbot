package ozmar.pokemonBattle.pokemonField;

import ozmar.pokemonBattle.pokemonField.PokemonEntryHazard.PokeEntryHazard;
import ozmar.pokemonBattle.pokemonField.PokemonSideProtection.PokeSideProtection;

public class PokeSide {
    PokeEntryHazard entryHazard;
    PokeSideProtection protection;

    // Hold other effects that are only for one side

    public PokeSide() {
        this.entryHazard = new PokeEntryHazard();
        this.protection = new PokeSideProtection();
    }
}
