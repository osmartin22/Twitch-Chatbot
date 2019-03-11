package ozmar.PokeBattle.enums;

public enum PokeMoveCategory {
    DAMAGE(1),
    AILMENT(2),
    NET_GOOD_STATS(3),
    HEAL(4),
    DAMAGE_AND_AILMENT(4),
    SWAGGER(5),
    DAMAGE_AND_LOWER(6),
    DAMAGE_AND_RAISE(7),
    DAMAGE_AND_HEAL(8),
    OHKO(9),
    WHOLE_FIELD_EFFECT(10),
    FIELD_EFFECT(11),
    FORCE_SWITCH(12),
    UNIQUE(13);

    private final int id;

    PokeMoveCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
/*
Aqua Ring (move)
Floral Healing (move)   1
Grassy Terrain (move)
Heal Pulse (move)   1
Healing Wish (move)
Ingrain (move)
Leech Seed (move)
Lunar Dance (move)
Pain Split (move)
Pollen Puff (move)
Present (move)
Wish (move)
 */

/*
Heal Order (move)   1
Milk Drink (move)   1
Moonlight (move)    1
Morning Sun (move)  1
Purify (move)
Recover (move)  1
Rest (move)
Roost (move)    1
Shore Up (move) 1
Slack Off (move)    1
Soft-Boiled (move)  1
Strength Sap (move)
Synthesis (move)    1
 */