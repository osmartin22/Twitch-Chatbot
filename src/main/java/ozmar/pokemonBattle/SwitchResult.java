package ozmar.pokemonBattle;

public class SwitchResult {

    private final boolean canSwitch;
    private final BattlePhase phase;

    public SwitchResult(boolean canSwitch, BattlePhase phase) {
        this.canSwitch = canSwitch;
        this.phase = phase;
    }

    public boolean canSwitch() {
        return canSwitch;
    }

    public BattlePhase getPhase() {
        return phase;
    }
}
