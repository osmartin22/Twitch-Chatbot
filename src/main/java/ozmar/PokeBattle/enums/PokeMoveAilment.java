package ozmar.PokeBattle.enums;

public enum PokeMoveAilment {
    NONE(0),
    PARALYSIS(1),
    SLEEP(2),
    FREEZE(3),
    BURN(4),
    POISON(5),
    CONFUSION(6),
    INFATUATION(7),
    TRAP(8),
    NIGHTMARE(9),
    TORMENT(12),
    DISABLE(13),
    YAWN(14),
    HEAL_BLOCK(15),
    NO_TYPE_IMMUNITY(17),   // IDENTIFIED
    LEECH_SEED(18),
    EMBARGO(19),
    PERISH_SONG(20),
    INGRAIN(21);

    private final int id;

    PokeMoveAilment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

/*
"fire-punch"
name:"ember"
name:"flamethrower"
name:"fire-blast"
name:"flame-wheel"
name:"sacred-fire"
name:"heat-wave"
name:"will-o-wisp"  1
name:"blaze-kick"
name:"flare-blitz"
name:"fire-fang"
name:"lava-plume"
name:"scald"    1
name:"inferno"
name:"searing-shot" 1
name:"blue-flare"
name:"ice-burn"
name:"steam-eruption"   1
 */
