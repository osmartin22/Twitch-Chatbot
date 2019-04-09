package ozmar.pokemonBattle.pokemonMoves.enums;

public enum PokeMoveByEffect {
    AFFECTED_BY_WEIGHT,
    STRONG_AGAINST_MINIMIZED,
    CALL_OTHER_MOVES,
    CANNOT_MISS,
    COST_HP,
    VARIABLE_POWER,
    CENTER_OF_ATTENTION,
    POWER_UP,
    REMOVES_SOME_TYPE_IMMUNITIES,
    NO_EFFECT,
    THAWS_USER,
    VARY_WITH_ENVIRONMENT,
    RECHARGES,
    BREAK_PROTECTION,
    PROTECTION,
    CONSECUTIVE,
    MULTI_STRIKE,
    CAN_HIT_SEMI_INVUL,


    SPECIAL_TYPE_EFFECTIVENESS,
    STATS_FROM_DIFF_CAT,

    HIGH_CRIT,
    PERFECT_CRIT,

    BINDING,
    TRAPPING,

    MODIFIES_MOVE_TYPES,
    CHANGES_POKE_TYPE,
    CHANGES_TYPE,

    RECOIL,
    CAUSE_USER_TO_FAINT,

    SWITCHES_TARGET,
    SWITCHES_USER,

    SEMI_INVUL,
    CHARGES,

    DIRECT_DAMAGE,         // Not sure if OHKO and SET_DAMAGE count here
    SET_DAMAGE,
    OHKO,

    HP_DRAINING,
    RESTORES_HP,
    HEALS_USER_IMMEDIATELY,

    CHANGES_TERRAIN,
    WEATHER_CHANGING,
    ENTRY_HAZARD,   // Maybe goes here
}

/*
TRAPPING -> Trapping, Binding

FIELD -> Change Terrain, Change Weather

AFFECTS_TYPES -> Modifies Move Types, Changes Poke Type, Changes Type

SWITCHES -> Switches Target, Switches User

DIRECT -> Direct Attack, Set Damage, OHKO

SELF_DAMAGE -> Recoil, Causes the user to faint

HEALING -> Heals user immediately, Hp Draining, Restores Hp

SEMI_INVUL -> Semi invulnerable moves, Moves that can hit semi invulnerable

CRIT -> High Crit, Perfect Crit
 */