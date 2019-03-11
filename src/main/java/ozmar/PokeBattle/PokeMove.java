package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeMoveDamageClass;
import ozmar.PokeBattle.enums.PokeMoveTarget;
import ozmar.PokeBattle.enums.PokeTypeEnum;

public class PokeMove {
    private int accuracy;
    private PokeMoveDamageClass damageClass;
    private int effectChance;
    // Effect Entries   ???
    private int id;
    private PokeMoveMetaData metaData;
    private String name;
    private int power;
    private int pp;
    private int priority;
    private PokeTypeEnum moveType;
    private PokeMoveTarget moveTarget;

}
