package ozmar.PokemonBattle.PokemonTerrain;

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
}
