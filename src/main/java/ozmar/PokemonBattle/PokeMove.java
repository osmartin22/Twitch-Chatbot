package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonType.PokeTypeEnum;
import ozmar.PokemonBattle.enums.PokeMoveDamageClass;
import ozmar.PokemonBattle.enums.PokeMoveTarget;

public class PokeMove {
    private int accuracy;
    private PokeMoveDamageClass damageClass;
    private int effectChance;
    private int id;
    private PokeMoveMetaData metaData;
    private String name;
    private int power;
    private int pp;
    private int priority;
    private PokeTypeEnum moveType;
    private PokeMoveTarget moveTarget;

    private boolean isDirectAttack;
}
