package ozmar.pokemonBattle.pokemonBattleHelpers;

public enum TrainerAction {
    ACTION_WAITING(0),
    ACTION_MOVE(1),
    ACTION_SWITCH(2);

    private final int id;

    TrainerAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
