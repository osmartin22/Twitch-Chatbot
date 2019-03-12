package ozmar.PokeBattle;

public class test {
// TODO: Maybe add a category for moves that make contact with the opponent
    /*
    BINDING MOVES
    These moves partially trap the foe and deal a small amount of damage for anywhere between two and five turns
    in Generations I-IV and for four to five turns in Generation V. If the user of a partial trapping move is holding
    a Grip Claw, the move will last for 7 turns (5 turns prior to Generation VI). A Pokémon can be only affected by
    one partial trapping move at a time.
        Bind (move)             Clamp (move)        Fire Spin (move)    Infestation (move)
        Magma Storm (move)      Sand Tomb (move)    Whirlpool (move)    Wrap (move)


    CONSECUTIVELY EXECUTED MOVES
    These are moves that are automatically used for one or more turns after being selected, only using 1 PP in
    the process. These moves will continue to execute, even when the user is affected by Torment.
    A similar move, Bide, causes the user to wait for two turns (2-3 turns prior to Generation IV); after those
    turns, Bide inflicts damage to the last Pokémon that damaged it equal to double the amount of damage it took
    during those turns.
        Ice Ball (move)     Outrage (move)      Petal Dance (move)    Rollout (move)
        Thrash (move)       Uproar (move)

        MOVES THAT CONFUSE A USER DUT TO FATIGUE
        These are moves that automatically execute for 2-3 turns after being selected, and then confuse
        the user after it has finished.
            Outrage (move)      Petal Dance (move)      Thrash (move)


    DECREASED PRIORITY MOVES
    These moves have decreased priority. If a Pokémon uses one of these moves, it will go after other higher
    priority moves that turn. If other Pokémon in the battle also use an decreased priority move with the
    same value, the attack order will be determined normally.
        Avalanche (move)        Beak Blast (move)       Circle Throw (move)     Counter (move)
        Dragon Tail (move)      Focus Punch (move)      Magic Room (move)       Mirror Coat (move)
        Revenge (move)          Roar (move)             Shell Trap (move)       Teleport (move)
        Trick Room (move)       Vital Throw (move)      Whirlwind (move)        Wonder Room (move)


    EFFECTS THAT CAN MODIFY MOVE TYPES
    Moves and Abilities which modify the type of other moves.
        Electrify (move)        Ion Deluge (move)

    EVOLUTION INDUCING MOVES
    Moves in this category will cause evolution in certain Pokémon when they level up while knowing it.
        Ancient Power (move)    Double Hit (move)       Dragon Pulse (move)     Mimic (move)
        Rollout (move)          Stomp (move)

        FAIRY TYPE MOVES???? NOT SURE WHY HERE
        These are Fairy-type moves.
            Aromatic Mist (move)    Baby-Doll Eyes (move)       Charm (move)                    Crafty Shield (move)
            Dazzling Gleam (move)   Disarming Voice (move)      Draining Kiss (move)            Fairy Lock (move)
            Fairy Wind (move)       Fleur Cannon (move)         Floral Healing (move)           Flower Shield (move)
            Geomancy (move)         Guardian of Alola (move)    Let's Snuggle Forever (move)    Light of Ruin (move)
            Misty Terrain (move)    Moonblast (move)            Moonlight (move)                Nature's Madness (move)
            Play Rough (move)       Sparkly Swirl (move)        Sweet Kiss (move)               Twinkle Tackle (move)


    HP DRAINING MOVES
    These moves restore a portion of their user's HP after damaging an opponent, healing a set percentage
    of the damage inflicted.
    Holding a Big Root will increase the amount of HP recovered with these moves, without altering the
    move's base power. If used on a Pokémon with Liquid Ooze (except Dream Eater prior to Generation V), the
    user will take equivalent damage instead of being healed.
    A similar move, Strength Sap, also restores the user's HP, but lowers the opponent's Attack instead of damaging it.
        Absorb (move)           Bouncy Bubble (move)    Drain Punch (move)      Draining Kiss (move)
        Dream Eater (move)      Giga Drain (move)       Horn Leech (move)       Leech Life (move)
        Leech Seed (move)       Mega Drain (move)       Oblivion Wing (move)    Parabolic Charge (move)


    INCREASED PRIORITY MOVES
    These moves have increased priority. If a Pokémon uses one of these moves, it will go before other lower
    priority moves that turn. If other Pokémon in the battle also use an increased priority move with the
    same value, the attack order will be determined normally.
        Accelerock (move)       Ally Switch (move)          Aqua Jet (move)         Baby-Doll Eyes (move)
        Baneful Bunker (move)   Bide (move)                 Bullet Punch (move)     Crafty Shield (move)
        Detect (move)           Endure (move)               Extreme Speed (move)    Fake Out (move)
        Feint (move)            First Impression (move)     Follow Me (move)        Helping Hand (move)
        Ice Shard (move)        Ion Deluge (move)           King's Shield (move)    Mach Punch (move)
        Magic Coat (move)       Powder (move)               Protect (move)          Quick Attack (move)
        Quick Guard (move)      Rage Powder (move)          Shadow Sneak (move)     Snatch (move)
        Spiky Shield (move)     Spotlight (move)            Sucker Punch (move)     Vacuum Wave (move)
        Water Shuriken (move)   Wide Guard (move)           Zippy Zap (move)


    ITEM MANIPULATING MOVES
    These are moves that affect how items are used by the player's Pokémon, wild Pokémon, or trainer Pokémon.
        Bestow (move)       Bug Bite (move)     Covet (move)        Fling (move)
        Incinerate (move)   Knock Off (move)    Magic Room (move)   Natural Gift (move)
        Pluck (move)        Recycle (move)      Switcheroo (move)   Thief (move)
        Trick (move)


    MOVES AFFECTED BY WEIGHT
    These moves either change the weight of the user or change in power depending on the target's weight.
    Autotomize (move)       Grass Knot (move)       Heat Crash (move)       Heavy Slam (move)
    Low Kick (move)         Sky Drop (move)


    MOVES BY STAT MODIFICATION
    These are moves that can modify the user and/or the target's stats.
        MOVES THAT LOWER TARGETS ACCURACY
        The following moves can lower their target's accuracy.
            Flash (move)        Kinesis (move)      Leaf Tornado (move)     Mirror Shot (move)
            Mud Bomb (move)     Mud-Slap (move)     Muddy Water (move)      Night Daze (move)
            Octazooka (move)    Sand Attack (move)  Secret Power (move)     Smokescreen (move)

        MOVES THAT LOWER TARGETS ATTACK
        The following moves can lower their target's Attack.
            Aurora Beam (move)      Baby-Doll Eyes (move)       Charm (move)            Feather Dance (move)
            Growl (move)            King's Shield (move)        Lunge (move)            Memento (move)
            Noble Roar (move)       Parting Shot (move)         Play Nice (move)        Play Rough (move)
            Secret Power (move)     Strength Sap (move)         Tearful Look (move)     Tickle (move)
            Trop Kick (move)        Venom Drench (move)

        MOVES THAT LOWER TARGETS DEFENSE
        The following moves can lower their target's Defense.
            Acid (move)             Crunch (move)       Crush Claw (move)       Fire Lash (move)
            Iron Tail (move)        Leer (move)         Liquidation (move)      Razor Shell (move)
            Rock Smash (move)       Screech (move)      Secret Power (move)     Shadow Bone (move)
            Shadow Down (move)      Tail Whip (move)    Tickle (move)

        MOVES THAT LOWER TARGETS EVASION
        The following moves can lower their target's evasion.
            Defog (move)    Shadow Mist (move)      Sweet Scent (move)

        MOVES THAT LOWER TARGETS SPECIAL ATTACK
        The following moves can lower their target's Special Attack.
            Captivate (move)        Confide (move)          Eerie Impulse (move)        Memento (move)
            Mist Ball (move)        Moonblast (move)        Mystical Fire (move)        Noble Roar (move)
            Parting Shot (move)     Secret Power (move)     Snarl (move)                Struggle Bug (move)
            Tearful Look (move)     Venom Drench (move)

        MOVES THAT LOWER TARGETS SPECIAL DEFENSE
        The following moves can lower their target's Special Defense.
            Acid (move)             Acid Spray (move)       Bug Buzz (move)         Crunch (move)
            Earth Power (move)      Energy Ball (move)      Fake Tears (move)       Flash Cannon (move)
            Focus Blast (move)      Luster Purge (move)     Metal Sound (move)      Psychic (move)
            Seed Flare (move)       Shadow Ball (move)

        MOVES THAT CAN LOWER TARGETS SPEED
        The following moves can lower their target's Speed.
            Bubble (move)           Bubble Beam (move)          Bulldoze (move)         Constrict (move)
            Cotton Spore (move)     Electroweb (move)           Glaciate (move)         Icy Wind (move)
            Low Sweep (move)        Mud Shot (move)             Rock Tomb (move)        Scary Face (move)
            Secret Power (move)     Sticky Web (move)           String Shot (move)      Toxic Thread (move)
            Venom Drench (move)

        MOVES THAT CAN LOWER USERS ATTACK
        The following moves can lower their user's Attack.
            Superpower (move)

        MOVES THAT CAN LOWER USERS DEFENSE
        The following moves can lower their user's Defense.
            Clanging Scales (move)      Close Combat (move)     Dragon Ascent (move)        Hyperspace Fury (move)
            Shell Smash (move)          Superpower (move)       V-create (move)

        MOVES THAT CAN LOWER USERS SPECIAL ATTACK
        The following moves can lower their user's Special Attack.
            Draco Meteor (move)     Fleur Cannon (move)     Leaf Storm (move)       Overheat (move)
            Psycho Boost (move)

        MOVES THAT CAN LOWER USERS SPECIAL DEFENSE
        The following moves can lower their user's Special Defense.
            Close Combat (move)     Dragon Ascent (move)        Shell Smash (move)      V-create (move)

        MOVES THAT CAN LOWER USERS SPEED
        The following moves can lower their user's Speed.
            Curse (move)        Hammer Arm (move)       Ice Hammer (move)       V-create (move)

        MOVES THAT CAN RAISE TARGETS ACCURACY
        The following moves can raise their target's accuracy.
            Acupressure (move)

        MOVES THAT CAN RAISE TARGETS ATTACK
        The following moves can raise their target's Attack.
            Acupressure (move)      Gear Up (move)      Rototiller (move)       Swagger (move)

        MOVES THAT CAN RAISE TARGETS DEFENSE
        The following moves can raise their target's Defense.
            Acupressure (move)      Flower Shield (move)        Magnetic Flux (move)

        MOVES THAT CAN RAISE TARGETS EVASION
        The following moves can raise their target's Evasion.
            Acupressure (move)

        MOVES THAT CAN RAISE TARGETS SPECIAL ATTACK
        The following moves can raise their target's Special Attack.
            Acupressure (move)      Flatter (move)      Gear Up (move)      Rototiller (move)

        MOVES THAT CAN RAISE TARGETS SPECIAL DEFENSE
        The following moves can raise their target's Special Defense.
            Acupressure (move)      Aromatic Mist (move)        Magnetic Flux (move)

        MOVES THAT CAN RAISE TARGETS SPEED
        The following moves can raise their target's Speed.
            Acupressure (move)

        MOVES THAT CAN RAISE USERS ACCURACY
        The following moves can raise their user's Accuracy.
            Acupressure (move)      Coil (move)     Hone Claws (move)

        MOVES THAT CAN RAISE USERS ATTACK
        The following moves can raise their user's Attack.
            Acupressure (move)              Ancient Power (move)    Belly Drum (move)       Bulk Up (move)
            Clangorous Soulblaze (move)     Coil (move)             Curse (move)            Dragon Dance (move)
            Extreme Evoboost (move)         Fell Stinger (move)     Growth (move)           Hone Claws (move)
            Howl (move)                     Meditate (move)         Metal Claw (move)       Meteor Mash (move)
            Ominous Wind (move)             Power-Up Punch (move)   Rage (move)             Sharpen (move)
            Shell Smash (move)              Shift Gear (move)       Silver Wind (move)      Swords Dance (move)
            Work Up (move)

        MOVES THAT CAN RAISE USERS DEFENSE
        The following moves can raise their user's Defense.
            Acid Armor (move)       Acupressure (move)              Ancient Power (move)    Barrier (move)
            Bulk Up (move)          Clangorous Soulblaze (move)     Coil (move)             Cosmic Power (move)
            Cotton Guard (move)     Curse (move)                    Defend Order (move)     Defense Curl (move)
            Diamond Storm (move)    Extreme Evoboost (move)         Flower Shield (move)    Harden (move)
            Iron Defense (move)     Ominous Wind (move)             Silver Wind (move)      Skull Bash (move)
            Steel Wing (move)       Stockpile (move)                Withdraw (move)

        MOVES THAT CAN RAISE USERS EVASION
        The following moves can raise their user's Evasion.
            Acupressure (move)      Double Team (move)      Minimize (move)

        MOVES THAT CAN RAISE USERS SPECIAL ATTACK
        The following moves can raise their user' Special Attack.
            Acupressure (move)              Ancient Power (move)        Calm Mind (move)        Charge Beam (move)
            Clangorous Soulblaze (move)     Extreme Evoboost (move)     Fiery Dance (move)      Geomancy (move)
            Growth (move)                   Nasty Plot (move)           Ominous Wind (move)     Quiver Dance (move)
            Shell Smash (move)              Silver Wind (move)          Tail Glow (move)        Work Up (move)

        MOVES THAT CAN RAISE USERS SPECIAL DEFENSE
        The following moves can raise their user' Special Defense.
            Acupressure (move)      Amnesia (move)              Ancient Power (move)            Aromatic Mist (move)
            Calm Mind (move)        Charge (move)               Clangorous Soulblaze (move)     Cosmic Power (move)
            Defend Order (move)     Extreme Evoboost (move)     Geomancy (move)                 Ominous Wind (move)
            Quiver Dance (move)     Silver Wind (move)          Stockpile (move)

        MOVES THAT CAN RAISE USERS SPEED
        The following moves can raise their user' Speed.
            Acupressure (move)              Agility (move)          Ancient Power (move)        Autotomize (move)
            Clangorous Soulblaze (move)     Dragon Dance (move)     Extreme Evoboost (move)     Flame Charge (move)
            Geomancy (move)                 Ominous Wind (move)     Quiver Dance (move)         Rock Polish (move)
            Shell Smash (move)              Shift Gear (move)       Silver Wind (move)


    MOVES BY USAGE METHOD
    These are moves by the method they are used. The method in which a move is used impacts how the move
    interacts with certain types, Abilities, items, and moves. It can also influence the move's targeting.

        AURA AND PULSE MOVES
        These moves are based on aura and pulses. In Japanese, these moves all include the word はどう hadō,
        which is translated as either aura or pulse in their English names. These moves receive a 50% boost in
        power if the user has Mega Launcher and most can be used to target non-adjacent opponents in Triple Battles.
            Aura Sphere (move)      Dark Pulse (move)       Dragon Pulse (move)     Heal Pulse (move)
            Origin Pulse (move)     Water Pulse (move)

        BALL AND BOMB MOVES
        These moves are based on balls and bombs. Pokémon with Bulletproof are immune to these moves.
            Acid Spray (move)       Aura Sphere (move)      Barrage (move)          Beak Blast (move)
            Bullet Seed (move)      Egg Bomb (move)         Electro Ball (move)     Energy Ball (move)
            Focus Blast (move)      Gyro Ball (move)        Ice Ball (move)         Magnet Bomb (move)
            Mist Ball (move)        Mud Bomb (move)         Octazooka (move)        Pollen Puff (move)
            Rock Blast (move)       Rock Wrecker (move)     Searing Shot (move)     Seed Bomb (move)
            Shadow Ball (move)      Sludge Bomb (move)      Weather Ball (move)     Zap Cannon (move)

        BITING MOVES
        These moves are based on biting; they receive a boost in power if the user has Strong Jaw.
            Bite (move)         Crunch (move)           Fire Fang (move)        Hyper Fang (move)
            Ice Fang (move)     Poison Fang (move)      Psychic Fangs (move)    Thunder Fang (move)

        DANCE MOVES
        These moves are based on dancing.
        Pokémon with Dancer can copy these moves when used by another Pokémon.
            Dragon Dance (move)     Feather Dance (move)        Fiery Dance (move)          Lunar Dance (move)
            Petal Dance (move)      Quiver Dance (move)         Revelation Dance (move)     Swords Dance (move)
            Teeter Dance (move)

        EXPLOSIVE MOVES
        These moves are based on explosives. These moves cannot be used if a Pokémon with Damp is on the field.
            Explosion (move)        Mind Blown (move)       Self-Destruct (move)

        POWDER AND SPORE MOVES
        These moves are based on powders and spores. From Generation VI onward, Grass-type Pokémon,
        Pokémon with Overcoat, and Pokémon holding the Safety Goggles are immune to these moves.
        While not a move, the same Pokémon that are unaffected by powder and spore moves are also
        unaffected by Effect Spore.
            Cotton Spore (move)     Poison Powder (move)    Powder (move)   Rage Powder (move)
            Sleep Powder (move)     Spore (move)            Stun Spore (move)

        PUNCHING MOVES
        These moves are based on punching; they receive a 20% boost in power if the user has Iron Fist.
            Bullet Punch (move)     Comet Punch (move)      Dizzy Punch (move)          Drain Punch (move)
            Dynamic Punch (move)    Fire Punch (move)       Focus Punch (move)          Hammer Arm (move)
            Ice Hammer (move)       Ice Punch (move)        Mach Punch (move)           Mega Punch (move)
            Meteor Mash (move)      Plasma Fists (move)     Power-Up Punch (move)       Shadow Punch (move)
            Sky Uppercut (move)     Thunder Punch (move)

        SOUND BASED MOVES
        These moves are moves which use sound.
        Pokémon with the Soundproof Ability are unaffected by all of these moves, except Heal Bell in Generation V.
        Pokémon affected by Throat Chop cannot use sound-based moves for two turns. Sound-based moves used by a
        Pokémon with Liquid Voice become Water-type moves. From Generation VI onward, Pokémon behind substitutes
        can be hit by them.
            Boomburst (move)                Bug Buzz (move)     Chatter (move)              Clanging Scales (move)
            Clangorous Soulblaze (move)     Confide (move)      Disarming Voice (move)      Echoed Voice (move)
            Grass Whistle (move)            Growl (move)        Heal Bell (move)            Hyper Voice (move)
            Metal Sound (move)              Noble Roar (move)   Parting Shot (move)         Perish Song (move)
            Relic Song (move)               Roar (move)         Round (move)                Screech (move)
            Shadow Panic (move)             Sing (move)         Snarl (move)                Snore (move)
            Sparkling Aria (move)           Supersonic (move)   Uproar (move)


    MOVES THAT AFFECT ABILITIES
    These are moves that can affect the Ability of the user or other Pokémon.
        Core Enforcer (move)    Gastro Acid (move)

        ABILITY CHANGING MOVES
        These are moves that can change the Ability of the user or other Pokémon.
            Entrainment (move)      Role Play (move)        Simple Beam (move)      Skill Swap (move)
            Transform (move)        Worry Seed (move)


    MOVES THAT BECOME STRONGER AGAINST MINIMIZED OPPONENT
    These are moves that become stronger when used against a Pokémon that has previously used the
    move Minimize. Since Generation VI, these also bypass accuracy checks to always hit, unless the opponent
    is in the semi-invulnerable turn of a move such as Dig or Fly.
    These moves become stronger by either dealing double the damage, or having their power doubled
    (resulting in virtually the same effect), depending on the generation.
    Some of these moves may become stronger against Minimized Pokémon in specific generations only. Astonish,
    for example, deals double the damage in Generation III only.
        Astonish (move)         Body Slam (move)        Dragon Rush (move)      Extrasensory (move)
        Flying Press (move)     Heat Crash (move)       Heavy Slam (move)       Malicious Moonsault (move)
        Needle Arm (move)       Phantom Force (move)    Shadow Force (move)     Steamroller (move)
        Stomp (move)


    MOVES THAT BREAK THROUGH PROTECTION
    These moves can bypass protection moves and lift all effects of protection moves from the target for the
    rest of the turn (even protection moves that would not normally protect the Pokémon from these moves).
    For protection moves that protect an entire side of the field, if any Pokémon is hit by one of these moves,
    the effects of those team protection moves are lifted from all Pokémon on that side of the field.
        Feint (move)        Hyperspace Fury (move)      Hyperspace Hole (move)      Phantom Force (move)
        Shadow Force (move)


    MOVES THAT CALL OTHER MOVES
    These are moves that call or turn into other moves.
    Transform allows the user to copy the target's entire moveset, but does not call them. Mimic and Sketch
    temporarily and permanently, respectively, copy the target's most recently used move, but do not call it.
        Assist (move)           Copycat (move)          Me First (move)     Metronome (move)
        Mirror Move (move)      Nature Power (move)     Sleep Talk (move)


    MOVES THAT CAN CAUSE FLINCHING
    The following moves can cause their targets to flinch.
        Air Slash (move)        Astonish (move)             Bite (move)             Bone Club (move)
        Dark Pulse (move)       Double Iron Bash (move)     Dragon Rush (move)      Extrasensory (move)
        Fake Out (move)         Fire Fang (move)            Fling (move)            Headbutt (move)
        Heart Stamp (move)      Hyper Fang (move)           Ice Fang (move)         Icicle Crash (move)
        Iron Head (move)        Low Kick (move)             Needle Arm (move)       Rock Slide (move)
        Rolling Kick (move)     Secret Power (move)         Sky Attack (move)       Snore (move)
        Steamroller (move)      Stomp (move)                Thunder Fang (move)     Twister (move)
        Waterfall (move)        Zen Headbutt (move)         Zing Zap (move)


    MOVES THAT CAN CONFUSE
    The following moves can confuse their targets.
        Chatter (move)          Confuse Ray (move)      Confusion (move)        Dizzy Punch (move)
        Dynamic Punch (move)    Flatter (move)          Hurricane (move)        Psybeam (move)
        Rock Climb (move)       Secret Power (move)     Shadow Panic (move)     Signal Beam (move)
        Supersonic (move)       Swagger (move)          Sweet Kiss (move)       Teeter Dance (move)
        Water Pulse (move)

        MOVES THAT CONFUSE A USER DUT TO FATIGUE
        These are moves that automatically execute for 2-3 turns after being selected, and then confuse
        the user after it has finished.
            Outrage (move)      Petal Dance (move)      Thrash (move)


    MOVES THAT CAN HEAL NON VOLATILE STATUS CONDITIONS
    The following moves can cure either their user or their target(s) from non-volatile status conditions.
    In addition to these moves, Fire-type moves thaw out a frozen target when they hit.
        Aromatherapy (move)         Heal Bell (move)            Psycho Shift (move)     Purify (move)
        Refresh (move)              Rest (move)                 Scald (move)            Smelling Salts (move)
        Sparkling Aria (move)       Steam Eruption (move)       Uproar (move)           Wake-Up Slap (move)

        MOVES THAT THAW OUT A USER
        These are moves that will thaw out the user when used, and as such, are the only moves that can
        be used while frozen. They will thaw out the user even if they are blocked, miss, deal no damage due
        an Ability like Flash Fire, or are prevented from being executed by Powder, heavy rain or extremely harsh
        sunlight; however, they cannot thaw out the user if they would fail (such as a non-Fire-type Pokémon
        using Burn Up).
            Burn Up (move)          Flame Wheel (move)      Flare Blitz (move)      Fusion Flare (move)
            Sacred Fire (move)      Scald (move)            Steam Eruption (move)


    MOVES THAT CAN HIT SEMI INVULNERABLE POKEMON
    Any Pokémon being affected by Sky Drop may also be targeted and hit by the same moves as the user of Sky Drop.
    If a Pokémon using Fly or Bounce is hit by Smack Down or Thousand Arrows, it is returned to the ground and
    the move is canceled.
    These are moves which can hit Pokémon that are in a semi-invulnerable turn.
    The moves Lock-On and Mind Reader cannot hit a Pokémon in a semi-invulnerable state, but will enable
    the next move used to strike a Pokémon in a semi-invulnerable state. No Guard allows any move used by
    that Pokémon to hit a Pokémon in a semi-invulnerable state, and any move used against that Pokémon to hit
    it in a semi-invulnerable state.

    Bide, Swift, Transfomr: only Gen I
    Helping Hand: Can target ally in invulnerable phase
    Toxic: Pokemon using it must be poison type
    Bounce, Fly, Sky Drop: Gust, Hurricane, Sky Uppercut, Smack Down, Thousand Arrows, Thunder, Twister, Whirlwind*
    Dig: Earthquake, Magnitude, Fissure
    Dive: Surf, Whirlpool

        Bide (move)             Earthquake (move)       Fissure (move)          Gust (move)
        Helping Hand (move)     Hurricane (move)        Magnitude (move)        Sky Uppercut (move)
        Smack Down (move)       Surf (move)             Swift (move)            Thousand Arrows (move)
        Thunder (move)          Toxic (move)            Transform (move)        Twister (move)
        Whirlpool (move)        Whirlwind (move)


    MOVES THAT CAN INFLICT A BURN
    The following moves can cause their targets to become burned.
        Beak Blast (move)           Blaze Kick (move)       Blue Flare (move)           Ember (move)
        Fire Blast (move)           Fire Fang (move)        Fire Punch (move)           Flame Wheel (move)
        Flamethrower (move)         Flare Blitz (move)      Fling (move)                Heat Wave (move)
        Ice Burn (move)             Inferno (move)          Lava Plume (move)           Psycho Shift (move)
        Sacred Fire (move)          Scald (move)            Searing Shot (move)         Secret Power (move)
        Shadow Fire (move)          Sizzly Slide (move)     Steam Eruption (move)       Tri Attack (move)
        Will-O-Wisp (move)


    MOVES THAT CAN INFLICT FREEZE
    The following moves can cause their targets to become frozen.
        Blizzard (move)         Freeze-Dry (move)           Ice Beam (move)         Ice Fang (move)
        Ice Punch (move)        Powder Snow (move)          Secret Power (move)     Shadow Chill (move)
        Tri Attack (move)


    MOVES THAT CAN INFLICT PARALYSIS
    The following moves can cause their targets to become paralyzed.
        Body Slam (move)        Bolt Strike (move)          Bounce (move)           Buzzy Buzz (move)
        Discharge (move)        Dragon Breath (move)        Fling (move)            Force Palm (move)
        Freeze Shock (move)     Glare (move)                Lick (move)             Nuzzle (move)
        Psycho Shift (move)     Secret Power (move)         Shadow Bolt (move)      Spark (move)
        Splishy Splash (move)   Stoked Sparksurfer (move)   Stun Spore (move)       Thunder (move)
        Thunder Fang (move)     Thunder Punch (move)        Thunder Shock (move)    Thunder Wave (move)
        Thunderbolt (move)      Tri Attack (move)           Volt Tackle (move)      Zap Cannon (move)


    MOVES THAT CAN INFLICT POISON
    The following moves can cause their targets to become poisoned.
        Baneful Bunker (move)       Cross Poison (move)     Fling (move)            Gunk Shot (move)
        Poison Fang (move)          Poison Gas (move)       Poison Jab (move)       Poison Powder (move)
        Poison Sting (move)         Poison Tail (move)      Psycho Shift (move)     Secret Power (move)
        Sludge (move)               Sludge Bomb (move)      Sludge Wave (move)      Smog (move)
        Toxic (move)                Toxic Spikes (move)     Toxic Thread (move)     Twineedle (move)


    MOVES THAT CAN INFLICT SLEEP
    The following moves can cause their targets to fall asleep.
        Dark Void (move)        Grass Whistle (move)        Hypnosis (move)     Lovely Kiss (move)
        Psycho Shift (move)     Relic Song (move)           Rest (move)         Secret Power (move)
        Sing (move)             Sleep Powder (move)         Spore (move)        Yawn (move)


    MOVES THAT CANNOT MISS
    These moves bypass accuracy checks to always hit their target. Most of these moves are still unable to
    hit a target in the semi-invulnerable turn of a move such as Dig or Fly, but a select few can.
    Blizzard, Hurricane, Thunder, moves that become stronger against a Minimized target, and Toxic may only
    bypass accuracy checks under specific conditions; otherwise, they have to pass a full accuracy check like
    other moves.
    The effects of No Guard, Lock-On, Mind Reader, and Telekinesis will also cause moves to bypass accuracy checks.
    In Pokémon Mystery Dungeon: Explorers of Time and Explorers of Darkness and Pokémon Mystery Dungeon: Explorers
    of Sky a Pokémon with Forewarn is sometimes able to evade these moves. Under Whiffer status, even these attacks
    will miss.
        10,000,000 Volt Thunderbolt (move)  Acid Downpour (move)        Aerial Ace (move)       After You (move)
        All-Out Pummeling (move)            Aura Sphere (move)          Baddy Bad (move)        Bestow (move)
        Bide (move)                         Black Hole Eclipse (move)   Blizzard (move)         Block (move)
        Bloom Doom (move)                   Body Slam (move)            Bouncy Bubble (move)    Breakneck Blitz (move)
        Buzzy Buzz (move)                   Catastropika (move)         Clear Smog (move)       Confide (move)
        Continental Crush (move)            Conversion 2 (move)         Corkscrew Crash (move)  Defog (move)
        Devastating Drake (move)            Disarming Voice (move)      Dragon Rush (move)      Electrify (move)
        Feint Attack (move)                 Floral Healing (move)       Flying Press (move)     Foresight (move)
        Freezy Frost (move)                 Gigavolt Havoc (move)       Glitzy Glow (move)      Guard Split (move)
        Guard Swap (move)                   Guardian of Alola (move)    Heal Pulse (move)       Heart Swap (move)
        Heat Crash (move)                   Hurricane (move)            Hydro Vortex (move)     Hyperspace Fury (move)
        Hyperspace Hole (move)              Inferno Overdrive (move)    Lock-On (move)          Magical Leaf (move)
        Magnet Bomb (move)                  Malicious Moonsault (move)  Me First (move)         Mean Look (move)
        Mimic (move)                        Mind Reader (move)          Miracle Eye (move)      Never-Ending Nightmare (move)
        Nightmare (move)                    Noble Roar (move)           Oceanic Operetta (move) Odor Sleuth (move)
        Pain Split (move)                   Phantom Force (move)        Pika Papow (move)       lay Nice (move)
        Powder (move)                       Power Split (move)          Power Swap (move)       Psych Up (move)
        Pulverizing Pancake (move)          Pursuit (move)              Reflect Type (move)     Roar (move)
        Role Playk (move)                    Sappy Seed (move)           Savage Spin-Out (move)  Shadow Force (move)
        Shadow Punch (move)                 Shattered Psyche (move)     Shock Wave (move)       Sinister Arrow Raid (move)
        Sizzly Slide (move)                 Sketch (move)               Skill Swap (move)       Smart Strike (move)
        Soul-Stealing 7-Star Strike (move)  Sparkly Swirl (move)        Speed Swap (move)       Spider Web (move)
        Steamroller (move)                  Stoked Sparksurfer (move)   Stomp (move)            Struggle (move)
        Subzero Slammer (move)              Supersonic Skystrike (move) Swift (move)            Tectonic Rage (move)
        Telekinesis (move)                  Thunder (move)              Topsy-Turvy (move)      Toxic (move)
        Transform (move)                    Trump Card (move)           Twinkle Tackle (move)   Veevee Volley (move)
        Vital Throw (move)                  Whirlwind (move)            Yawn (move)


    MOVES THAT CAUSE THE USER TO FAINT
    These are moves which cause the user to faint when used in battle.
        Explosion (move)        Final Gambit (move)     Healing Wish (move)     Lunar Dance (move)
        Memento (move)          Perish Song (move)      Self-Destruct (move)


    MOVES THAT CHANGE A POKEMON'S TYPE
    These are moves that can directly change the type of a Pokémon.
        Burn Up (move)              Camouflage (move)       Conversion (move)       Conversion 2 (move)
        Forest's Curse (move)       Reflect Type (move)     Roost (move)            Soak (move)
        Transform (move)            Trick-or-Treat (move)


    MOVES THAT CHANGE TERRAIN
    These are moves that alter the terrain of the battlefield.
        Electric Terrain (move)     Genesis Supernova (move)        Grassy Terrain (move)       Misty Terrain (move)
        Psychic Terrain (move)

    MOVES THAT CHANGE TYPE
    These are moves that can have a different type (from their own effect).
        Hidden Power (move)         Judgment (move)         Multi-Attack (move)     Natural Gift (move)
        Revelation Dance (move)     Techno Blast (move)     Weather Ball (move)


    MOVES THAT COST HP TO USE
    These moves require their user to sacrifice a portion of their maximum HP in order to use them. Depending on
    the move, it may fail without deducting HP or succeed but KO the user if they do not have enough HP remaining
    to use it. Moves in this category are similar to but should not be confused with moves that cause recoil damage,
    which deal damage to their user as a side-effect of their successful use, or moves that cause the user to faint,
    which outright render their user unconscious in order to be used
        Belly Drum (move)       Curse (move)        Mind Blown (move)       Substitute (move)


    MOVES THAT DEAL DIRECT DAMAGE
    These moves deal direct damage, unaffected by the attacker's or defender's stats.
        Bide (move)                 Counter (move)          Endeavor (move)         Final Gambit (move)
        Guardian of Alola (move)    Metal Burst (move)      Mirror Coat (move)      Nature's Madness (move)
        Night Shade (move)          Psywave (move)          Seismic Toss (move)     Super Fang (move)

        ONE HIT KNOCKOUT MOVES
            Fissure (move)      Guillotine (move)       Horn Drill (move)       Sheer Cold (move)

        SET DAMAGE MOVES
            Dragon Rage (move)      Sonic Boom (move)


    MOVES THAT HAVE RECOIL
    The following moves can cause their users recoil damage.
        Brave Bird (move)       Double-Edge (move)      Flare Blitz (move)      Head Charge (move)
        Head Smash (move)       High Jump Kick (move)   Jump Kick (move)        Light of Ruin (move)
        Shadow End (move)       Shadow Rush (move)      Struggle (move)         Submission (move)
        Take Down (move)        Volt Tackle (move)      Wild Charge (move)      Wood Hammer (move)


    MOVES THAT HAVE SPECIAL TYPE EFFECTIVENESS PROPERTIES
    These are moves that deviate from the type effectiveness chart in some respect.
        Flying Press (move)     Freeze-Dry (move)       Thousand Arrows (move)


    MOVES THAT HAVE VARIABLE POWER
    These are moves that do not have a fixed base power; usually the power is displayed as "—". The power
    of these moves is determined by factors such as the user or target's weight, HP, or friendship; some
    may depend on a random factor. For some of these moves, the power may be calculated directly from these
    factors and have a continuous range of possible power (e.g., Return), but for others, the power will have
    discrete levels depending on the exact conditions (e.g., Reversal).
        Beat Up (move)          Crush Grip (move)           Electro Ball (move)         Eruption (move)
        Flail (move)            Fling (move)                Frustration (move)          Grass Knot (move)
        Gyro Ball (move)        Heat Crash (move)           Heavy Slam (move)           Hidden Power (move)
        Low Kick (move)         Magnitude (move)            Natural Gift (move)         Pika Papow (move)
        Power Trip (move)       Present (move)              Punishment (move)           Return (move)
        Reversal (move)         Spit Up (move)              Stored Power (move)         Trump Card (move)
        Veevee Volley (move)    Water Spout (move)          Wring Out (move)


    MOVES THAT IGNORE ABILITIES
    These are moves that ignore the target's Ability when they are used.
        Light That Burns the Sky (move)     Menacing Moonraze Maelstrom (move)      Moongeist Beam (move)
        Photon Geyser (move)                Searing Sunraze Smash (move)            Sunsteel Strike (move)


    MOVES THAT MAKE THE TARGET THE CENTER OF ATTENTION
    These are moves that make the target the center of attention.
        Follow Me (move)        Rage Powder (move)      Spotlight (move)


    MOVES THAT POWER UP
    These are moves that receive an increase to their base power under certain conditions.
    Usually, these moves' power doubles when their condition is met.
    Some of these moves may receive an increase in their base power in specific generations only.
    Earthquake, for example, has its power doubled against Pokémon in the semi-invulnerable turn of Dig
    from Generation II to IV only (but does not have any special interaction with them in Generation I,
    and will technically have the damage against them doubled from Generation V onwards instead).
        Acrobatics (move)           Assurance (move)            Avalanche (move)        Brine (move)
        Earthquake (move)           Echoed Voice (move)         Facade (move)           Fire Pledge (move)
        Fury Cutter (move)          Fusion Bolt (move)          Fusion Flare (move)     Grass Pledge (move)
        Gust (move)                 Hex (move)                  Ice Ball (move)         Magnitude (move)
        Malicious Moonsault (move)  Payback (move)              Pursuit (move)          Retaliate (move)
        Revenge (move)              Rollout (move)              Round (move)            Smelling Salts (move)
        Stomp (move)                Stomping Tantrum (move)     Surf (move)             Triple Kick (move)
        Twister (move)              Venoshock (move)            Wake-Up Slap (move)      Water Pledge (move)
        Weather Ball (move)         Whirlpool (move)


    MOVES THAT REMOVE SOME TYPE IMMUNITIES
    Moves in this category can remove one or more immunities granted by type. For example,
    Odor Sleuth removes the Ghost-type's immunity to Fighting-type and Normal-type attacks. They may
    also have additional effects, typically dealing with Evasion.
    Some of the moves affect the target, while Gravity affects all Pokémon for several turns.
        Foresight (move)        Gravity (move)      Miracle Eye (move)      Odor Sleuth (move)
        Smack Down (move)       Thousand Arrows (move)


    MOVES THAT REQUIRE RECHARGING
    This is a list of moves that, if used successfully, the user must recharge for one turn. The recharge
    turns takes place the very next turn and the user cannot make any other action such as using a move, switching
    out or use an item.
        Blast Burn (move)       Frenzy Plant (move)     Giga Impact (move)      Hydro Cannon (move)
        Hyper Beam (move)       Prismatic Laser (move)  Roar of Time (move)     Rock Wrecker (move)
        Shadow Half (move)


    MOVES THAT RESTORE HP
    These moves restore a portion of the user's or target's HP.
        Aqua Ring (move)        Floral Healing (move)       Grassy Terrain (move)       Heal Pulse (move)
        Healing Wish (move)     Ingrain (move)              Leech Seed (move)           Lunar Dance (move)
        Pain Split (move)       Pollen Puff (move)          Present (move)              Wish (move)

        HP DRAINING MOVES
        These moves restore a portion of their user's HP after damaging an opponent, healing a set percentage
        of the damage inflicted.
        Holding a Big Root will increase the amount of HP recovered with these moves, without altering the move's
        base power. If used on a Pokémon with Liquid Ooze (except Dream Eater prior to Generation V), the user will
        take equivalent damage instead of being healed.
        A similar move, Strength Sap, also restores the user's HP, but lowers the opponent's Attack instead of damaging it.
            Absorb (move)       Bouncy Bubble (move)        Drain Punch (move)      Draining Kiss (move)
            Dream Eater (move)  Giga Drain (move)           Horn Leech (move)       Leech Life (move)
            Leech Seed (move)   Mega Drain (move)           Oblivion Wing (move)    Parabolic Charge (move)

        STATUS MOVES THAT HEAL THE USER IMMEDIATELY
        These status moves restore a portion of their user's HP on the same turn as they are used.
        A number of other moves can also recover HP for the user in different ways. HP-draining moves are moves that
        simultaneously do damage and recover HP for the user immediately. Aqua Ring and Ingrain can heal the user
        continually, starting at the end of the turn they are used. Leech Seed and Wish can heal either the user or a
        Pokémon that replaces it.
            Heal Order (move)       Milk Drink (move)       Moonlight (move)        Morning Sun (move)
            Purify (move)           Recover (move)          Rest (move)             Roost (move)
            Shore Up (move)         Slack Off (move)        Soft-Boiled (move)      Strength Sap (move)
            Synthesis (move)


    MOVES THAT SWITCH THE TARGET OUT
    These are moves that switch their target out.
        Circle Throw (move)     Dragon Tail (move)      Roar (move)     Whirlwind (move)


    MOVES THAT SWITCH THE USER OUT
    These are moves that switch the user out when they are used.
        Baton Pass (move)       Parting Shot (move)     Teleport (move)     U-turn (move)
        Volt Switch (move)


    MOVES THAT USE STATS FROM DIFFERENT CATEGORIES
    These are moves for which the damage is calculated using the user's Attack and target's Special Defense,
    or the user's Special Attack and target's Defense.
        Light That Burns the Sky (move)     Photon Geyser (move)        Psyshock (move)     Psystrike (move)
        Secret Sword (move)


    MOVES THAT VARY WITH ENVIRONMENT
    These are moves whose effect changes based on the environment they are used in.
        Camouflage (move)       Nature Power (move)     Secret Power (move)


    MOVES WITH A CHARGING TURN
    These are moves that take two turns to complete. On the first turn, the user charges (sometimes causing some
    effect such as becoming semi-invulnerable or raising a stat). On the second turn, the user executes the move
    fully. All of these moves, except Sky Drop, can be performed in one turn using the Power Herb; some of these
    moves can be performed in one turn if some condition is met.
    A similar move, Bide, causes to user to wait for two turns (2-3 turns prior to Generation IV); after those
    two turns, Bide inflicts damage to the last Pokémon to damage it equal to double the amount of damage it took
    during those turns.
        Bounce (move)           Dig (move)              Dive (move)         Fly (move)
        Freeze Shock (move)     Geomancy (move)         Ice Burn (move)     Phantom Force (move)
        Razor Wind (move)       Shadow Force (move)     Skull Bash (move)   Sky Attack (move)
        Sky Drop (move)         Solar Beam (move)       Solar Blade (move)

        MOVES WITH A SEMI INVULNERABLE TURN
        These moves take two turns to execute. During the first turn, the user of the move will perform an action
        (ascending into the sky, hiding underwater, etc.) that renders them invulnerable to all but a few specific
        moves; Pokémon in this state can also be hit by a Pokémon that used Lock-On or Mind Reader on it on the
        previous turn, or if either it or the attacking Pokémon has No Guard. On the second turn, PP will be deducted,
        the move will deal damage, and it will count as the last move used.
        While these moves have these characteristics in common, they are not strictly variations of each other,
        since many of their other attributes (like power, accuracy, power points, or secondary effects, including
        which moves can still strike them) are different.
            Bounce (move)           Dig (move)              Dive (move)         Fly (move)
            Phantom Force (move)    Shadow Force (move)     Sky Drop (move)


    MOVES WITH A HIGH CRITICAL HIT RATIO
    These moves have an increased chance of landing a critical hit.
        10,000,000 Volt Thunderbolt (move)      Aeroblast (move)        Air Cutter (move)   Attack Order (move)
        Blaze Kick (move)                       Crabhammer (move)       Cross Chop (move)   Cross Poison (move)
        Drill Run (move)                        Karate Chop (move)      Leaf Blade (move)   Night Slash (move)
        Poison Tail (move)                      Psycho Cut (move)       Razor Leaf (move)   Razor Wind (move)
        Shadow Blast (move)                     Shadow Claw (move)      Sky Attack (move)   Slash (move)
        Spacial Rend (move)                     Stone Edge (move)


    MOVES WITH A PERFECT CRITICAL HIT RATIO
    These moves will always result in a critical hit, unless the critical hit is prevented by another effect.
        Frost Breath (move)     Storm Throw (move)      Zippy Zap (move)


    MOVES WITH A SEMI INVULNERABLE TURN
    These moves take two turns to execute. During the first turn, the user of the move will perform an action
    (ascending into the sky, hiding underwater, etc.) that renders them invulnerable to all but a few specific
    moves; Pokémon in this state can also be hit by a Pokémon that used Lock-On or Mind Reader on it on the
    previous turn, or if either it or the attacking Pokémon has No Guard. On the second turn, PP will be deducted,
    the move will deal damage, and it will count as the last move used.
    While these moves have these characteristics in common, they are not strictly variations of each other,
    since many of their other attributes (like power, accuracy, power points, or secondary effects, including
    which moves can still strike them) are different.
        Bounce (move)           Dig (move)              Dive (move)         Fly (move)
        Phantom Force (move)    Shadow Force (move)     Sky Drop (move)


    MOVES WITH NO EFFECT
    These are moves that do not have any known effect. However, when turned into Z-Moves, they may have Z-Power effects.
        Celebrate (move)        Hold Hands (move)       Splash (move)


    MULTI STRIKE MOVES
    Multi-strike moves are moves which have multiple strikes per turn.
        Arm Thrust (move)           Barrage (move)          Beat Up (move)          Bone Rush (move)
        Bonemerang (move)           Bullet Seed (move)      Comet Punch (move)      Double Hit (move)
        Double Iron Bash (move)     Double Kick (move)      Double Slap (move)      Dual Chop (move)
        Fury Attack (move)          Fury Swipes (move)      Gear Grind (move)       Icicle Spear (move)
        Pin Missile (move)          Rock Blast (move)       Spike Cannon (move)     Tail Slap (move)
        Triple Kick (move)          Twineedle (move)        Water Shuriken (move)


    ONE HIT KNOCKOUT MOVES
        Fissure (move)      Guillotine (move)       Horn Drill (move)       Sheer Cold (move)


    PROTECTION MOVES
    These moves protect the user entirely from certain moves for a turn. Moves that break through protection can
    bypass their protection (even if the protection move would not normally protect the Pokémon from those moves)
    and remove their effects for the rest of the turn.
        Baneful Bunker (move)       Crafty Shield (move)        Detect (move)           King's Shield (move)
        Mat Block (move)            Protect (move)              Quick Guard (move)      Spiky Shield (move)
        Wide Guard (move)


    SET DAMAGE MOVES
        Dragon Rage (move)      Sonic Boom (move)


    STATUS MOVES THAT HEAL THE USER IMMEDIATELY
    These status moves restore a portion of their user's HP on the same turn as they are used.
    A number of other moves can also recover HP for the user in different ways. HP-draining moves are moves that
    simultaneously do damage and recover HP for the user immediately. Aqua Ring and Ingrain can heal the user
    continually, starting at the end of the turn they are used. Leech Seed and Wish can heal either the user or a
    Pokémon that replaces it.
        Heal Order (move)       Milk Drink (move)       Moonlight (move)        Morning Sun (move)
        Purify (move)           Recover (move)          Rest (move)             Roost (move)
        Shore Up (move)         Slack Off (move)        Soft-Boiled (move)      Strength Sap (move)
        Synthesis (move)


    TRAPPING MOVES
    These are moves that prevent an affected Pokémon from switching or escaping. Since Generation VI,
    Ghost-type Pokémon are unaffected by the trapping effect of these moves.
        Anchor Shot (move)      Block (move)            Fairy Lock (move)       Ingrain (move)
        Mean Look (move)        Shadow Hold (move)      Spider Web (move)       Spirit Shackle (move)
        Thousand Waves (move)

        BINDING MOVES
        These moves partially trap the foe and deal a small amount of damage for anywhere between two and five turns
        in Generations I-IV and for four to five turns in Generation V. If the user of a partial trapping move is holding
        a Grip Claw, the move will last for 7 turns (5 turns prior to Generation VI). A Pokémon can be only affected by
        one partial trapping move at a time.
            Bind (move)             Clamp (move)        Fire Spin (move)    Infestation (move)
            Magma Storm (move)      Sand Tomb (move)    Whirlpool (move)    Wrap (move)


    WEATHER CHANGING MOVES
    These are moves that alter the weather in battle.
        Defog (move)        Hail (move)     Rain Dance (move)       Sandstorm (move)
        Shadow Sky (move)   Sunny Day (move)


    LIST OF MOVES THAT CAN CAUSE ENTRY HAZARDS
    An entry hazard is any battlefield effect that affects the opponent's Pokémon as they are sent out of their
    Poké Ball. Any Pokémon already on the battlefield when these effects begin will be unaffected. All moves that
    create entry hazards are status moves.
    Entry hazards are removed by Rapid Spin (user's side) and Defog (Generation VI: both sides; Generation IV-V:
    only opponent's side). In addition, Toxic Spikes is removed by a grounded Poison-type Pokémon upon switching in.
    Entry hazards will not trigger for support trainers joining the battle in Pokemon: Let's Go, Pikachu! and
    Let's Go, Eevee!




    CATEGORIES NOT INCLUDED AS THEY DON'T PERTAIN TO BATTLES(only doing battles right now)
    MOVES THAT CAN JAM (only used in pokemon contests)
    MOVES USABLE OUTSIDE OF BATTLE (no need to know if moves are usable outside of battle)
     */

}
