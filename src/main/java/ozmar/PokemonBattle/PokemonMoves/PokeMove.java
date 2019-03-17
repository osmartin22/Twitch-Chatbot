package ozmar.PokemonBattle.PokemonMoves;

import ozmar.PokemonBattle.PokemonType.PokeTypeEnum;

public class PokeMove {
    private int id;
    private String name;
    private int pp;
    private int power;
    private int accuracy;
    private int priority;
    private PokeTypeEnum moveType;
    private PokeMoveDamageClass damageClass;
    private PokeMoveTarget moveTarget;

    private PokeMoveMetaData metaData;

    private int effectChance;
    private boolean isDirectAttack;
}
