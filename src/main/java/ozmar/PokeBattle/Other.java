package ozmar.PokeBattle;

public class Other {
    /*
    BINDING MOVES - Sub Category of Trapping Moves
    Lasts 4-5 turns
    Deals 1/8th of the targets max HP as damage at the end of the turn
    Only one Binding move can affect a Pokemon at a time
    Pokemon affected are unable to escape
    Rapid Spin can be used to break free
    Ghost types can not be trapped but is affected by the rest of the moves effects
        BindingMoves {

        }

            Bind (move)             Clamp (move)        Fire Spin (move)    Infestation (move)
            Magma Storm (move)      Sand Tomb (move)    Whirlpool (move)    Wrap (move)
     */

    /*
    MOVES WITH A CHARGING TURN
    Semi-invulnerable Moves are a subset category
    These are moves that take two turns to complete. On the first turn, the user charges (sometimes causing some
    effect such as becoming semi-invulnerable or raising a stat). On the second turn, the user executes the move
    fully. All of these moves, except Sky Drop, can be performed in one turn using the Power Herb; some of these
    moves can be performed in one turn if some condition is met.
    A similar move, Bide, causes to user to wait for two turns, after those two turns, Bide inflicts damage to
    the last Pokemon to damage it equal to double the amount of damage it took during those turns.
        Bounce (move)           Dig (move)              Dive (move)         Fly (move)
        Freeze Shock (move)     Geomancy (move)         Ice Burn (move)     Phantom Force (move)
        Razor Wind (move)       Shadow Force (move)     Skull Bash (move)   Sky Attack (move)
        Sky Drop (move)         Solar Beam (move)       Solar Blade (move)
     */

    /*
    MOVES WITH A PERFECT CRITICAL HIT RATIO
    These moves will always result in a critical hit, unless the critical hit is prevented by another effect.

    PerfectCritHitMoves {
        // Can probably just be a list of moves that is checked instead of a separate class
    }

        Frost Breath (move)     Storm Throw (move)      Zippy Zap (move)
     */

    /*
    MOVES WITH A SEMI INVULNERABLE TURN- subset category of
    These moves take two turns to execute. During the first turn, the user of the move will perform an action
    (ascending into the sky, hiding underwater, etc.) that renders them invulnerable to all but a few specific
    moves
    Pokemon in this state can also be hit by a Pokemon that used Lock-On or Mind Reader on it on the previous turn,
    or if either it or the attacking Pokemon has No Guard.
    On the second turn, PP will be deducted, the move will deal damage, and it will count as the last move used.
    While these moves have these characteristics in common, they are not strictly variations of each other,
    since many of their other attributes (like power, accuracy, power points, or secondary effects, including
    which moves can still strike them) are different.

    SemiInvulnerableMoves {
        // Only similarities are that semi invulnerable turn and how PP is deducted
        // HAve a list that affects each move maybe
    }

        Bounce (move)           Dig (move)              Dive (move)         Fly (move)
        Phantom Force (move)    Shadow Force (move)     Sky Drop (move)
     */

    /*
    MOVES WITH NO EFFECT
    These are moves that do not have any known effect. However, when turned into Z-Moves, they may have Z-Power effects.

    MovesWithNoEffect {
        // Moves do nothing except output special text
    }

        Celebrate (move)        Hold Hands (move)       Splash (move)
     */

    /*
    MULTI STRIKE MOVES
    Multi-strike moves are moves which have multiple strikes per turn.

    Accuracy check only on the first hit
    Each strike has an independent chance of being a critical hit
    Each strike from a multi-strike move has an independent chance of activating an Ability that activates on taking damage,
    and each strike of a multi-strike move that makes contact has an independent chance of activating an Ability that activates on contact
    If a Pokemon using a multi-strike move falls asleep or faints between strikes, such as due to Effect Spore or Rough Skin, the move will end immediately.
    Pokemon with Skill Link will always hit five times when using a 2-5 hit multi-strike move
    Each individual strike from a physical multi-strike move will activate Weak Armor
    Each individual strike from a Dark-type multi-strike move will activate Justified
    Grass-type multi-strike move will only activate Sap Sipper once
    Each strike has an independent chance of activating Stench
    Color Change activates only for the last hit
    Parental Bond turns most single-strike damaging moves into multi-strike moves that hit twice per use.
    If a contact multi-strike move is blocked by Spiky Shield or King's Shield, the negative effect will only apply once
    Each individual strike from one of these moves will activate Stamina
    Bide and Counter only acknowledge the last hit

        MultiStrikeMove {
            int minHits
            int maxHits

            2-5Strikes() {}
            2Strikes() {}
            3Strikes() {}
            1-6Strikes() {}
        }

        2-5 Strikes Per Turn
        (1/3)2, (1/3)3, (1/6)4, (1/6)5
        These moves always hit for 5 times with the Skill Link Ability
            Arm Thrust     Barrage         Bone Rush       Bullet Seed     Comet Punch     Double Slap
            Fury Attack    Fury Swipes     Icicle Spear    Pin Missile     Rock Blast      Spike Cannon
            Tail Slap      Water Shuriken

        2 Strikes Per Turn
            Bonemerang      Double Hit      Double Kick     Dual Chop       Gear Grind      Twineedle
            Double Iron Bash

        3 Strikes Per Turn
        Up to 3 hits
        If one hit misses, the entire move ends
        Accuracy check on each hit
        Base power increases by 10
            Triple Kick

        1-6 Strikes Per Turn
        Hits up to the number of Pokemon in the opponents party, not including those fainted or have a non volatile status
            Beat Up

        Arm Thrust (move)           Barrage (move)          Beat Up (move)          Bone Rush (move)
        Bonemerang (move)           Bullet Seed (move)      Comet Punch (move)      Double Hit (move)
        Double Iron Bash (move)     Double Kick (move)      Double Slap (move)      Dual Chop (move)
        Fury Attack (move)          Fury Swipes (move)      Gear Grind (move)       Icicle Spear (move)
        Pin Missile (move)          Rock Blast (move)       Spike Cannon (move)     Tail Slap (move)
        Triple Kick (move)          Twineedle (move)        Water Shuriken (move)
     */

    /*
    ONE HIT KNOCKOUT MOVES  OHKO
        Immediately drop the targets HP to zero
        ALL OHKO Moves have 5 PP, and have a power of 180 if it becomes a Z-Move
        Pokemon with Sturdy are unaffected by these moves unless the ability is negated
        A Pokemon with Wonder Guard is also immune unless the it is weak to the moves type
        Automatically fail if the user has a lower level than the target
        Accuracy and Evasion stats are ignored for accuracy calculations
        Also ignores Abilities that affect Accuracy or Evasion
        Effects that allow moves to always hit still work

        Formula for Fissure, Guillotine, and Horn Drill
        Acc = ( ( user_level - target_level ) + 30 ) * 100%

        Sheer Cold
        Accuracy = ((level of user - level of target) + 30)% for Ice types
        Accuracy = ((level of user - level of target) + 20)% for non Ice types

        OHKO_Moves {

            // Sheer Cold is unique, Ice types are immune and the moves accuracy is lowered if the user is not an Ice type
            // have special checks for Sheer Cold to accommodate  the above
            checkUsersTargetLevel() {}
            formulaForNonSheerColdMoves(){}
            formulaForSheerColdMove() {}
        }

        Fissure (move)      Guillotine (move)       Horn Drill (move)       Sheer Cold (move)
     */

    /*
        PROTECTION MOVES
        TODO
     */

    /*
        SET DAMAGE MOVES
        No secondary effects
        Always deal a fixed amount of direct damage, unaffected by the attacker's or defender's stats

        SetDamageMoves {
            // Nothing special except the deal the same amount of damage
        }

        Dragon Rage (move)      Sonic Boom (move)
     */

    /*
    STATUS MOVES THAT HEAL THE USER IMMEDIATELY
        These status moves restore a portion of their user's HP on the same turn as they are used
        Do not do any damage to an opponent

        NonDamageHealMoves() {
            // Heals the user
            // Does no damage
        }

        Heal Order (move)       Milk Drink (move)       Moonlight (move)        Morning Sun (move)
        Purify (move)           Recover (move)          Rest (move)             Roost (move)
        Shore Up (move)         Slack Off (move)        Soft-Boiled (move)      Strength Sap (move)
        Synthesis (move)
     */

    /*
    TRAPPING MOVES
    These are moves that prevent an affected Pokemon from switching or escaping
    Binding moves are a sub category
    Does not stop moves that force switch
        TrappingMoves {
            BindingMoves bindingMoves
        }

        Anchor Shot (move)      Block (move)            Fairy Lock (move)       Ingrain (move)
        Mean Look (move)        Shadow Hold (move)      Spider Web (move)       Spirit Shackle (move)
        Thousand Waves (move)
     */

    /*
    WEATHER CHANGING MOVES
    These are moves that alter the weather in battle.
    Last for 5 turns (except Defog)

    WeatherChangingMoves {

    }

        Defog (move)        Hail (move)     Rain Dance (move)       Sandstorm (move)
        Shadow Sky (move)   Sunny Day (move)
     */
}
