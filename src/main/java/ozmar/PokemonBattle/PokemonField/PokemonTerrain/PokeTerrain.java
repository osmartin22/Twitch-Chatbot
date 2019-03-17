package ozmar.PokemonBattle.PokemonField.PokemonTerrain;

public class PokeTerrain {

    private PokeTerrainEnum terrain;
    private int turnsRemaining; // TODO: Not sure if the turn the move is used is counted as a turn

    public PokeTerrain(PokeTerrainEnum terrain, int turnsRemaining) {
        this.terrain = terrain;
        this.turnsRemaining = turnsRemaining;
    }

    public PokeTerrainEnum getTerrain() {
        return terrain;
    }

    public void setTerrain(PokeTerrainEnum terrain) {
        this.terrain = terrain;
    }

    public void setTurnsRemaining(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public void decrementTurn() {
        this.turnsRemaining--;
    }

    /*
    ELECTRIC TERRAIN
        This terrain has the following effects on Pokemon that are on the ground and not in the semi-invulnerable turn of a move
            It prevents affected Pokemon from being afflicted by sleep or Yawn, even if the Pokemon is already drowsy
            Rest will fail if used on an afflicted Pokemon
            It boosts the power of Electric-type moves used by affected Pokemon by 50%
            This boost includes moves that became Electric type due to type-changing moves like Electrify
        Nature Power becomes Thunderbolt, Secret Power can now cause paralysis, Camouflage causes the user to become an Electric Type
        Pokemon with the Ability Surge Surfer have their Speed stat doubled when under the effects of Electric Terrain
        When a Pokemon with the Ability Electric Surge enters battle, Electric Terrain is automatically created
     */

    /*
    GRASSY TERRAIN
        This terrain has the following effects on Pokemon that are on the ground and not in the semi-invulnerable turn of a move
            At the end of each turn, the terrain restores the HP of each affected Pokemon by 1/16 of its maximum HP
            It boosts the power of Grass-type moves used by affected Pokemon by 50%
        The power of Bulldoze, Earthquake, and Magnitude is halved, except if the target is in a semi invulnerable turn
        Nature Power becomes Energy Ball, Secret Power can cause Sleep, Camouflage causes the user to become a Grass Type
        Grassy Terrain also activates the Ability Grass Pelt
        When a Pokemon with the Ability Grassy Surge enters battle, Grassy Terrain is automatically created
     */

    /*
    MISTY TERRAIN
        This terrain has the following effects on Pokemon that are on the ground and not in the semi-invulnerable turn of a move
            Prevents affected Pokemon from being afflicted by non-volatile status conditions
            Pokemon affected can no longer be confused
            If a Pokemon is drowsy, it is prevented from falling asleep
            Rest will fail if used by an affected Pokemon
            It halves the power of Dragon-type moves used against affected Pokemon
        Nature Power becomes Moon Blast, Secret Power can lower the target's Special Attack, Camouflage causes the user to become a Fairy Type
        When a Pokemon with the Ability Misty Surge enters battle, Misty Terrain is automatically created
     */

    /*
    PSYCHIC TERRAIN
        This terrain has the following effects on Pokemon that are on the ground and not in the semi-invulnerable turn of a move
            It prevents affected Pokemon from being hit by opponents' moves with increased priority (including moves boosted by Prankster, Gale Wings, or Triage)
            Moves that target all Pokemon (except Perish Song, Flower Shield, and Rototiller) and moves that target all foes cannot be blocked by Psychic Terrain even if they become priority moves
            It boosts the power of Psychic-type moves used by affected Pokemon by 50%
        Nature Power becomes Psychic, Secret Power can lower the target's Speed, Camouflage causes the user to become Psychic Type
        When a Pokemon with the Ability Psychic Surge enters battle, Psychic Terrain is automatically created
        Upon successfully doing damage, Genesis Supernova creates Psychic Terrain on the field which lasts for 5 turns
     */
}
