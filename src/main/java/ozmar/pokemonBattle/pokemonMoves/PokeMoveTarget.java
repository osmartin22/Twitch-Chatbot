package ozmar.pokemonBattle.pokemonMoves;

public enum PokeMoveTarget {
    SPECIFIC_MOVE(1),
    SELECTED_POKEMON_ME_FIRST(2),
    ALLY(3),
    USERS_FIELD(4),
    USER_OR_ALLY(5),
    OPPONENTS_FIELD(6),
    USER(7),
    RANDOM_OPPONENT(8),
    ALL_OTHER_POKEMON(9),
    SELECTED_POKEMON(10),
    ALL_OPPONENTS(11),
    ENTIRE_FIELD(12),
    USER_AND_ALLIES(13),
    ALL_POKEMON(14);

    private final int id;

    PokeMoveTarget(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
