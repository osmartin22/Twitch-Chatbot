package ozmar.PokemonBattle.PokemonField;

import ozmar.PokemonBattle.PokemonField.PokemonEntryHazard.PokeEntryHazard;
import ozmar.PokemonBattle.PokemonField.PokemonSideProtection.PokeSideProtection;

public class PokeSide {
    PokeEntryHazard entryHazard;
    PokeSideProtection protection;

    // Hold other effects that are only for one side

    public PokeSide() {
        this.entryHazard = new PokeEntryHazard();
        this.protection = new PokeSideProtection();
    }
}
