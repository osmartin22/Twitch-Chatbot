package ozmar.pokemonBattle;

public enum TrainerChoice {
    CHOICE_WAITING(0),
    CHOICE_MOVE(1),
    CHOICE_SWITCH(2);

    private final int id;

    TrainerChoice(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
