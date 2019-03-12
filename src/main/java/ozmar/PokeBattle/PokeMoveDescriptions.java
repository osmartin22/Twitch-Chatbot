package ozmar.PokeBattle;

public class PokeMoveDescriptions {
    /*
    TODO: Include abilities, started including at Whirlwind
    NOTE: Moves are using Gen VII
        Z-Moves not taken into account
        Abilities are not taken into account, therefore if a move is affected by an ability,it is not written here
        Moves affecting results after a battle are not taken into account, (e.g. Payday)
        Moves only consider single battles(no 2v2 battles)

#	Name	                Type	    Category    Contest     PP	Power	Accuracy	Gen
1	Pound	                Normal      Physical	Tough	    35	40	    100%	    I
        No secondary effects

2	Karate Chop 	        Fighting    Physical	Tough	    25	50	    100%	    I
        Increased crit-hit ratio

3	Double Slap	            Normal	    Physical	Cute	    10	15	    85%	        I
        (Multi Strike)2-5 hits (33.33) for 2/3, (16.7) for 4/5, each hit is calculated separately

4	Comet Punch	            Normal	    Physical	Tough	    15	18	    85%	        I
        (Multi Strike)2-5 hits (33.33) for 2/3, (16.7) for 4/5, each hit is calculated separately

5	Mega Punch	            Normal	    Physical	Tough	    20	80	    85%	        I
        No secondary effects

6	Pay Day	                Normal	    Physical    Clever	    20	40	    100%	    I
        No secondary effects

7	Fire Punch	            Fire	    Physical    Tough	    15	75	    100%	    I
        10% chance of burning the target, Fire types are immune

8	Ice Punch	            Ice	        Physical	Beautiful	15	75	    100%	    I
        10% chance of freezing the target, Ice types are immune

9	Thunder Punch	        Electric	Physical	Cool	    15	75	    100%	    I
        10% chance of paralyzing the target, Electric types are immune

10	Scratch	                Normal	    Physical	Tough	    35	40	    100%	    I
        No secondary effects

11	Vice Grip	            Normal	    Physical	Tough	    30	55	    100%	    I
        No secondary effects

12	Guillotine	            Normal	    Physical	Cool	    5	—	    —* *	    I
        OHKO, accuracy calculation, Acc = ( ( user_level - target_level ) + 30 ) * 100%
        unaffected by accuracy and evasion stats

13	Razor Wind	            Normal	    Special	    Cool	    10	80	    100%*	    I
        Increased crit-hit ratio, Charges up, Does nothing when first used , other than saying "<Pokémon> whipped up a whirlwind!"
        On the following turn, Razor Wind inflicts damage, PP is deducted, and it will count as the last move used.
        User unable to swap out until razor wind completed.
        If not fully executed, no PP is deducted, and does not count as the last used move.
        Mirror move will copy previous move if the opponent uses Razor Wind, or fail if it can't
        Sleep, freeze, partial trapping, and flinching will pause but not disrupt the duration of Razor Wind.

14	Swords Dance	        Normal	    Status	    Beautiful	20	—	    —	        I
        Increases the user's Attack stat by two stages

15	Cut	                    Normal	    Physical	Cool	    30	50	    95%	        I
        No secondary effects

16	Gust	                Flying	    Special	    Clever	    35	40	    100%	    I
        Can hit Pokemon during the semi-invulnerable turn of Bounce, Fly, and Sky Drop, and if it does, it will have its power doubled

17	Wing Attack	            Flying	    Physical	Cool	    35	60	    100%	    I
        No secondary effects

18	Whirlwind	            Normal	    Status	    Clever	    20	—	    —*	        I
        Bypasses accuracy checks to always hit unless Pokemon is in semi invulnerable phase. Reflected by Magic Coat and Magic Bounce.
        Fails against Pokemon with the ability Suction Cups or rooted by Ingrain. Can now hit a target even if it has
        used Protect, Detect, or Spiky Shield, but will fail if the target is protected by Crafty Shield. Forces
        target Pokemon to switch with another Pokemon on the team. Fails if no other Pokemon is available.
        Priority of -6

19	Fly	                    Flying      Physical	Clever	    15	90*	    95%	        I
        On turn of selection, user will fly high up and attack on the second turn lowering PP if the move succeeded.
        Opponents using Mirror Move will copy last used move. User not able to switch out until move is fully executed.
        Full paralysis and self-inflicted damage due to confusion will disrupt Fly.
        Gust(2x), Thunder, Twister(2x), Sky Uppercut, Whirlwind, Hurricane, Smack Down and Thousand Arrows
        can hit during the semi-invulnerable turn. If hit by Smack Down or Thousand Arrows, the move is cancelled.
        Can also be hit if it was previously targeted by Lock-On or Mind Reader, and struck the following turn.
        Unable to avoid moves with Pokemon with the ability No Guard or if the user itself has the ability.

20	Bind	                Normal	    Physical	Tough	    20	15	    85%	        I
        Traps the Pokemon for 4-5 turns
        Deals 1/16 of the targets max HP damage per turn. On the last turn it deals 1/8 of the targets max HP
         Prevents Pokemon from switching out. Effect ends if user is no longer on
        the field. Rapid Spin can be used to break free. Cannot affect Ghost Types

21	Slam	                Normal	    Physical	Tough	    20	80	    75%	        I
        No secondary effects

22	Vine Whip	            Grass	    Physical	Cool	    25	45	    100%	    I
        No secondary effects

23	Stomp	                Normal	    Physical	Tough	    20	65	    100%	    I
        30% chance of causing target to flinch, Targets with substitute can not be flinched by it.
        If the target has used minimize. Accuracy checks are bypassed to always hit unless the Pokemon is in the
        semi invulnerable phase. The damage dealt will be doubled as well

24	Double Kick	            Fighting	Physical	Cool	    30	30	    100%	    I
        Multi Strike Move. Attacks the user twice, damage calculated separately.
        Bide and counter acknowledge only the last hit
        Targets with an ability that activate upon contact can have the ability activate on each turn
        If the first strike activates the Sturdy(for the target), the next attack causes it to faint
        Targets with weak armor will activate the ability on each hit

25	Mega Kick	            Normal	    Physical	Cool	    5	120 	75%	        I
        No secondary effects

26	Jump Kick	            Fighting	Physical	Cool	    10*	100*	95%	        I
        User takes crash damage equal to 1/2 of its max HP, rounded down. Can still take crash damage if the move
        was protected against by a move like Protect, but not to type immunity, (i.e. ghost type).
        Cannot be used if the move Gravity is in effect

27	Rolling Kick	        Fighting	Physical	Cool	    15	60	    85%	        I
        30% chance to cause target to flinch. Targets with substitute cannot be flinched

28	Sand Attack 	        Ground	    Status	    Cute	    15	—	    100%	    I
        Decreases the targets accuracy stat by one stage
        Still affects Flying type pokemon and pokemon with the ability levitate.

29	Headbutt	            Normal	    Physical	Tough	    15	70	    100%	    I
        30% chance of causing the target to flinch

30	Horn Attack	            Normal	    Physical	Cool	    25	65	    100%	    I
        No secondary effects

31	Fury Attack	            Normal	    Physical	Cool	    20	15	    85%	        I
        Multi Strike Move 2-5 times (33.33)2-3, (16.7)4-5. Each hit calculated separately. Abilities can activate
        on each hit(contact)
        Pokemon with the ability Skill Link will hit 5 times (unless it misses).
        If sturdy activates on the target on the first hit, the next hit will cause it to faint
        If the target has Weak Armor, it will activate on each strike

32	Horn Drill	            Normal	    Physical	Cool	    5	—	    —* *	    I
        OHKO
        Accuracy calculation Acc = ( ( user_level - target_level ) + 30 ) * 100%
        Unaffected by accuracy and evasion, cannot affect targets with a higher level

33	Tackle	                Normal	    Physical	Tough	    35	40	    100%	    I
        No secondary effects

34	Body Slam	            Normal	    Physical	Tough	    15	85	    100%	    I
        30% chance of paralyzing the target
        Bypasses accuracy if the target uses minimize and does 2x damage, except if target is in semi invulnerable phase

35	Wrap	                Normal	    Physical	Tough	    20	15	    90%*	    I
        TODO: More research
        Move lasts for 4-5 turns causing 1/16th of max targets HP as damge, 1/8th on the last turn

36	Take Down	            Normal	    Physical	Tough	    20	90	    85%	        I
        User takes 25% of damage dealt to opponent as recoil damage. The turn ends if the user faints
        Recoil damage is still taken if a substitute breaks

37	Thrash	                Normal	    Physical	Tough	    10*	120*	100%	    I
        Attacks for 2-3 turns. PP only deducted on the turn Thrash is first used. User is unable to switch out.
        User becomes confused after the end of Thrash. If the move is disrupted, execution of the move stops.
        It is disrupted if it is not successful due to missing, sleeping,
        paralysis, freeze, flinching, a Protecting target, Ghost-type or Wonder Guard immunity. If it is disrupted on
        the last turn, the user will still become confused.

38	Double-Edge	            Normal	    Physical	Tough	    15	120*	100%	    I
        User takes 1/3 of the damage dealt as recoil damage.
        The turn ends if the user faints due to recoil damage
        Pokemon with Rock Head do not take recoil damage from this move

39	Tail Whip	            Normal	    Status	    Cute	    30	—	    100%	    I
        Decreases the Defense stat of all adjacent opponents by one stage

40	Poison Sting	        Poison	    Physical	Clever	    35	15	    100%	    I
        30% chance to poison the target

41	Twineedle	            Bug	        Physical	Cool	    20	25	    100%	    I
        Multi Strike Move
        Hits target twice per use. Each strike is calculated independently, and has a 20% chance to poison
        Bide and counter only acknowledge the last hit,
        If the attack activates sturdy on the target on the first hit, the next hit will cause the target to faint.
        Weak Armor(target) will activate on each hit

42	Pin Missile	            Bug	        Physical	Cool	    20	25*	    95%*	    I
        Same as fury attack

43	Leer	                Normal	    Status	    Cool	    30	—	    100%	    I
        Decreases the Defense stat of all adjacent opponents by one stage

44	Bite	                Dark	    Physical	Tough	    25	60	    100%	    I
        30% chance of causing the target to flinch

45	Growl	                Normal	    Status	    Cute	    40	—	    100%	    I
        Decreases the Attack stat of all adjacent opponents by one stage. It does not affect Pokemon with the
        Abilities Soundproof, Hyper Cutter, Clear Body, White Smoke, or Full Metal Body

46	Roar	                Normal	    Status	    Cool	    20	—	    —*	        I
        Causes target Pokemon to switch out with a random one, fails if no other Pokemon to switch to
        Priority of -6. Reflected by Magic Coat and Magic Bounce
        Bypasses accuracy checks to always hit, unless the target is in the semi-invulnerable state
        Can hit a target even if it has used Protect, Detect, or Spiky Shield, but will fail if the target is protected by Crafty Shield

47	Sing	                Normal	    Status	    Cute	    15	—	    55%	        I
        Puts the target to sleep, even behind substitute
        Pokemon with Insomnia, Vital Spirit or Soundproof as their Ability are unaffected by Sing
        Pokemon under the effect of Sweet Veil are also unaffected

48	Supersonic	            Normal	    Status	    Clever	    20	—	    55%	        I
        Causes the target to become confused, even behind substitute
        Fails if the target is already confused or has the ability Soundproof or Own Tempo

49	Sonic Boom	            Normal	    Special	    Cool	    20	*	    90%	        I
        Always inflicts 20 damage exactly.  No secondary effects, does not take weaknesses or resistances into account
        Cannot hit Ghost types

50	Disable	                Normal	    Status	    Clever	    20	—	    100%*	    I
        Disables the lst move the target used. Turn count is reduced every time the target tries to use the attack
        Will fail if no move was used yet or the last move used was Struggle.
        Sleep Talk can still call a disabled move.
        Lasts for 4 turns and can be reflected by Magic Coat
        Does not affect Pokémon under the protection of Aroma Veil

51	Acid	                Poison	    Special	    Clever	    30	40	    100%	    I
        10% chance of lowering the target's Special Defense by one stage

52	Ember	                Fire	    Special	    Cute	    25	40	    100%	    I
        10% chance of burning the target

53	Flamethrower	        Fire	    Special	    Beautiful	15	90*	    100%	    I
        10% chance of burning the target

54	Mist	                Ice	        Status	    Beautiful	30	—	    —	        I
        Affects all Pokemon on the users side and lasts for 5 turns. If a Pokemon on the user's side is switched out,
        the new Pokemon is still protected. Neither opponents or allied Pokemon can affect the stats of any Pokemon under
        the effect of Mist. Also protects against abilities that affect stats.
        The effects of Mist can be removed by Defog
        The effect can be passed with Baton Pass

55	Water Gun	            Water	    Special	    Cute	    25	40	    100%	    I
        No secondary effects

56	Hydro Pump	            Water	    Special	    Beautiful	5	110 	80%	        I
        No secondary effects

57	Surf	                Water	    Special	    Beautiful	15	90	    100%	    I
        Hits all opposing Pokemon. Can hit Pokemon in the semi invulnerable turn of Dig dealing 2x damage

58	Ice Beam	            Ice	        Special	    Beautiful	10	90     100%	        I
        10% chance of freezing the target

59	Blizzard	            Ice 	    Special	    Beautiful	5	110*	70%*	    I
        10% chance of freezing the target
        Hits all opponents
        When used during hail, it bypasses all accuracy checks to hit unless the target is in a semi invulnerable turn

60	Psybeam	                Psychic	    Special	    Beautiful	20	65	    100%	    I
        10% chance of confusing the target

61	Bubble Beam	            Water	    Special	    Beautiful	20	65	    100%	    I
        10% chance of lowering the target's Speed stat by one stage

62	Aurora Beam	            Ice	        Special	    Beautiful	20	65	    100%	    I
        10% chance of lowering the target's Attack stat by one stage

63	Hyper Beam	            Normal	    Special	    Cool	    5	150	    90% 	    I
        Needs to charge for one turn
        Hyper Beam will always need to recharge if it hits, even if the target faints
        If the user has Truant, the recharge and "loafing around" take place on the same turn

64	Peck	                Flying	    Physical	Cool	    35	35	    100%	    I
        No secondary effects

65	Drill Peck	            Flying	    Physical	Cool	    20	80	    100%	    I
        No secondary effects

66	Submission	            Fighting	Physical	Cool	    20	80	    80%	        I
        User takes damage equal to 25% of the damage dealt to the target
        Turn ends if the user faints

67	Low Kick	            Fighting	Physical	Tough	    20	—*	    100%*	    I
        Power is determined on the weight of the target
        0.1 - 9.9 kg	    0.1 - 21.8 lbs.	    20
        10.0 - 24.9 kg	    21.9 - 55.0 lbs.	40
        25.0 - 49.9 kg	    55.1 - 110.0 lbs.	60
        50.0 - 99.9 kg	    110.2 - 220.2 lbs.	80
        100.0 - 199.9 kg	220.4 - 440.7 lbs.	100
        200.0 kg or more	440.9 lbs. or more  120

68	Counter	                Fighting	Physical	Tough	    20	—	    100%	    I
        TODO: More research
        Priority -5
        If the opponent does not use a decreased priority move during the round that it is used,
        Counter will go last regardless of the user's or opponent's speed
        If the opponent also uses a decreased priority move during the same round, the attack order of the
        users will be determined normally
        Counter can cause a critical hit but the amount of damage will not be affected by it
        Counter cannot counter itself
        Counters all physical moves for 2x damage done to the user. Cannot counter a move that hits a substitute
        Ghost type Pokemon are immune
        If the Pokemon using counter is hit by a physical attack with 0 damage, Counter becomes a physical move with 1 base power
        In Double Battles, Counter will hit the last opponent that dealt physical damage to the attacker.
        Counter cannot affect allied Pokémon
        Cannot be copied by Mirror Move
        Counter cannot counter Hidden Power(sepcial move)

69	Seismic Toss	        Fighting	Physical	Tough	    20	—	    100%	    I
        inflicts damage equal to the user's level

70	Strength	            Normal	    Physical	Tough	    15	80	    100%	    I
        No secondary effects

71	Absorb	                Grass	    Special	    Clever	    15	40	    100%	    I
        Up to 50% of the damage dealt to the target is restored to the user as HP
        At least 1HP will be restored i.e. 1 HP of damage heals 1HP
        Damage to a substitute, even if broken will still heal
        When used on a Pokemon with the Liquid Ooze Ability, the user will lose the amount of HP it would have gained instead
        Cannot be used if the user is under the effects of Heal Block

72	Mega Drain	            Grass	    Special 	Clever	    10	75	    100%	    I
        Same as Absorb but a stringer move

73	Leech Seed	            Grass	    Status	    Clever	    10	—	    90%	        I
        1/8 of the targets HP is drained. Rapid Spin will clear the effect
        A substitute cannot have leech seed applied to it. It's effect can still effect the target behind a substitute if
        it was affected before using Substitute
        Drains HP at the end of the turn
        In multi battles, it only heals the Pokemon in the same position it was originally used. No health taken if
        a Pokemon is not present(fainted)
        If the target has the Ability Liquid Ooze, the user takes damage instead of restoring HP
        Heal Block prevents HP from being restored, but the damage is still done
        Sappy Seed leaves Leech Seed on the target

74	Growth	                Normal	    Status	    Beautiful	20*	—	    —	        I
        Increases the user's Special Attack and Attack stat by one stage. In harsh sunlight, it's raised by two stages

75	Razor Leaf	            Grass	    Physical	Cool	    25	55	    95%	        I
        Has an increased critical hit ratio
        Targets all adjacent foes

76	Solar Beam	            Grass	    Special	    Cool	    10	200	100%	        I
        Needs one turn to charge
        Does not need to charge during harsh sunlight
        PP is deducted on the turn damage is done and will be the last used move
        User will not be able to switch out while using Solar Beam
        No PP is deducted if the the move is not fully executed
        Sleep, freezing, partial trapping, and flinching will pause, but not disrupt, the duration of SolarBeam
        PP will be deducted during harsh sunlight if the move did not execute
        If it is disrupted during harsh sunlight, the move is cancelled instead of paused
        Its power is halved if used during rain, hail, fog or when a sandstorm is raging

77	Poison Powder	        Poison	    Status	    Clever	    35	—	    75%	        I
        Poisons the target except those type immune or abilities that grant immunity to it
        Grass-type Pokemon or Pokemon with Overcoat are immune as well

78	Stun Spore	            Grass	    Status	    Clever	    30	—	    75%	        I
        Paralyzes the target
        Pokemon with the ability Limber are immune
        Grass-type and Electric-type Pokemon, or Pokemon with Overcoat are immune

79	Sleep Powder	        Grass	    Status	    Clever	    15	—	    75%     	I
        Puts the target to sleep
        Pokemon with Insomnia, Vital Spirit or Sap Sipper as their Ability are immune
        Grass-type Pokemon or Pokemon with Overcoat are immune

80	Petal Dance	            Grass	    Special 	Beautiful	10*	120*	100%	    I
        Inflicts damage for 2-3 turns, causes confusion at the end
        PP deducted only on the turn first used
        User can not switch until the end of the move
        If the move is disrupted, it will end immediately
        It is disrupted if it is not successful due to missing, sleeping, paralysis, freeze, flinching,
        a Protecting target, or a type immunity
        If the disruption occurs on the would be last turn, the user still becomes confused
        User only becomes confused if the move fully executed

81	String Shot	            Bug	        Status	    Clever	    40	—	    95%	        I
        Targets all adjacent opponents
        Decreases the targets' Speed stat by two stages

82	Dragon Rage	            Dragon	    Special 	Cool	    10	*	    100%	    I
        Does 40 damage exactly. No secondary effects and does not take weaknesses or resistances into account
        Does not ignore type immunity

83	Fire Spin	            Fire	    Special 	Beautiful	15	35* 	85%*	    I
        Lasts 4-5 turns, can thaw out a frozen target
        Does 1/16 of targets Max HP as damage. 1/8 on the last turn
        Ghost types cannot be trapped
        Rapid Spin can be used to break free

84	Thunder Shock	        Electric	Special 	Cool	    30	40	    100%	    I
        10% chance of paralyzing the target, Electric types immune

85	Thunderbolt     	    Electric	Special	    Cool	    15	90*	    100%	    I
        10% chance of paralyzing the target, Electric types immune

86	Thunder Wave	        Electric	Status	    Cool	    20	—	    90%*	    I
        Paralyzes the target
        Move takes type changes into account,
        i.e. normalize changes move to normal type and no longer affects ghost types, but can now affect ground types

87	Thunder         	    Electric	Special	    Cool	    10	110*	70%	        I
        30% chance to paralyze the target
        When used during rain, Thunder bypasses accuracy checks to always hit, unless the target is in the semi-invulnerable turn
        When used during harsh sunlight, its accuracy is reduced to 50%
        Thunder can hit Pokemon during the semi-invulnerable turn of Fly, Bounce and Sky Drop

88	Rock Throw	            Rock	    Physical	Tough	    15	50	    90%*	    I
        No secondary effects

89	Earthquake	            Ground	    Physical	Tough	    10	100 	100%	    I
        Move hits all Pokemon, including allies
        Can hit a Pokemon during the semi-invulnerable turn of Dig, damage is doubled
        If Grassy Terrain is in effect, Earthquake deals only half the usual damage

90	Fissure         	    Ground	    Physical	Tough   	5	—	    —* *	    I
        OHKO
        Inflicts damage equal to the targets HP
        Breaks a substitute if it hits
        Cannot affect Pokemon with a higher level
        Accuracy formula Acc = ( ( user_level - target_level ) + 30 ) * 100%
        Unaffected by accuracy and evasion stats
        Can also now affect Pokemon during the semi-invulnerable turn of Dig

91	Dig	                    Ground	    Physical	Tough	    10	80* 	100%	    I
        Semi invulnerable move
        PP deducted when the turn does damage and becomes the last used move
        Can be hit if the user or opponent attacking has No Guard
        The user can now be hit by Earthquake(2x), Magnitude(2x), and Fissure during the semi-invulnerable turn
        Can also be hit if it was previously targeted by Lock-On or Mind Reader
        Mirror Move behaves like other Semi Invulnerable Moves
        Full paralysis and self-inflicted damage due to confusion will disrupt Dig

92	Toxic	                Poison	    Status	    Clever	    10	—	    90%*	    I
        TODO: research more on how N is affected
        Badly poisons the target
        Target takes damage of N*X where N starts at 1 and x is 1/16 of the target's max HP(rounded down but greater than 1)
        N increases by 1 every time Toxic does damage
        When the target switches out, the status becomes regular poison
        Roost can fully clear Badly poisoned status and reset N
        If a badly poisoned Pokemon uses Baton Pass or Heal Bell, N will not be reset
        Cannot affect steel type
        Cannot affect targets with Immunity as their ability
        Will never miss if the user is a poison type, even in semi invulnerable turns

93	Confusion	            Psychic 	Special 	Clever	    25	50	    100%	    I
        10% chance of confusing the target

94	Psychic	                Psychic	    Special 	Clever	    10	90	    100%	    I
        10% chance of lowering the target's Special Defense by one stage

95	Hypnosis	            Psychic 	Status	    Clever	    20	—	    60%*	    I
        Hypnosis puts the target to sleep
        Can affect a target behind a substitute

96	Meditate	            Psychic	    Status	    Beautiful	40	—	    —	        I
        Meditate increases the user's Attack by one stage

97	Agility	                Psychic	    Status  	Cool	    30	—	    —	        I
        Agility increases the user's Speed by two stages

98	Quick Attack    	    Normal  	Physical	Cool    	30	40	    100%	    I
        Priority +1, goes before all moves that do not have increased Priority

99	Rage	                Normal	    Physical	Tough   	20	20	    100%	    I
        When Rage is used consecutively, the user's Attack is increased by one stage each time it is damaged by an attack
        A Pokemon's rage will only build after it successfully used Rage

100	Teleport	            Psychic	    Status  	Cool	    20	—	    —	        I
        Always fails in battles

101	Night Shade     	    Ghost	    Special 	Clever  	15	—	    100%	    I
        Inflicts damage equal to the user's level

102	Mimic	                Normal	    Status  	Cute	    10	—	    —*	        I
        Copies the targets last moved, fails to copy Sketch, Struggle, Metronome, or any known moves
        Retains the copied move until it faints or is switched out
        Bypasses accuracy checks to always hit unless in a semi invulnerable turn
        The mimicked move has max PP
        Cannot mimic Transform, Shadow moves, or Chatter

103	Screech	                Normal  	Status  	Clever	    40	—	    85%	        I
        Decreases the target's Defense stat by two stat stages

104	Double Team	            Normal	    Status  	Cool	    15	—	    —	        I
        Increases the user's evasion by one stage

105	Recover	                Normal	    Status  	Clever  	10*	—	    —	        I
        Restores up to 50% of the users max HP

106	Harden	                Normal	    Status	    Tough   	30	—	    —	        I
        Increases the user's Defense stat by one stage

107	Minimize	            Normal  	Status	    Cute	    10*	—	    —	        I
        Raises the user's evasion stat by two stages
        The moves Body Slam, Dragon Rush, Flying Press, Heat Crash, Steamroller, Stomp, Heavy Slam, Malicious Moonsault,
        and Double Iron Bash will now bypass accuracy checks and deal double the damage when used against a
        Pokemon that has used Minimize

108	Smokescreen	            Normal	    Status	    Clever  	20	—	    100%	    I
        Lowers the target's accuracy stat by one stage

109	Confuse Ray	            Ghost	    Status	    Clever      10	—	    100%	    I
        Causes the target to become confused

110	Withdraw	            Water	    Status	    Cute	    40	—	    —	        I
        Increases the user's Defense by one stage

111	Defense Curl    	    Normal	    Status	    Cute	    40	—	    —	        I
        Increases the user's Defense by 1 stage
        Also doubles the power of the user's Rollout and Ice Ball as long as the user remains in battle
        Cannot be Baton Passed

112	Barrier         	    Psychic	    Status	    Cool	    20*	—	    —	        I
        Increases the user's Defense by two stages

113	Light Screen	        Psychic	    Status	    Beautiful	30	—	    —	        I
        Remains in effect for 5 turns
        Light Screen now technically halves the damage done to them by special moves; it still does not reduce
        damage done by moves that deal direct damage
        Light Screen is removed from a Pokemon's side of the field if it is hit by Brick Break, Defog or
        Psychic Fangs. Light Screen is not removed if the target is immune to the move
        Pokemon with the Ability Infiltrator ignore the effects of Light Screen when attacking

114	Haze	                Ice	        Status	    Beautiful	30	—	    —	        I
        TODO: Research more
        Resets the stat stages of all active Pokemon on the field to 0

115	Reflect	                Psychic 	Status	    Clever  	20	—	    —	        I
        Physical counterpart to Light Screen, All the same except
        Using Baddy Bad sets up Reflect on the user's side

116	Focus Energy    	    Normal	    Status	    Cool	    30	—	    —	        I
        Increases the user's critical hit ratio by two stages
        Effect can be transferred by Baton Pass

117	Bide	                Normal	    Physical	Tough	    10	—	    —*	        I
        TODO: Research more
        Priority +1
        Bide endures attacks for two turns and attacks the the last Pokemon to attack the user, even allies
        Cannot switch out until the move ends
        Bide cannot hit Ghost types or Pokemon in semi invulnerable turns
        Bypasses accuracy checks
        If sleep status is inflicted on a Pokemon using Bide, the move is disrupted and doesn't continue even after the Pokemon wakes up.

118	Metronome       	    Normal	    Status	    Cute	    10	—	    —	        I
        TODO: Research more
        Randomly selects a move and fully executes the attack. Moves called by Metronome count as the last used move
        Selected move is not affected by Priority
        Metronome ignores the effects of Lock-On and Mind Reader when choosing a Pokemon to target
        Will randomly select an opponent or ally to target even if the move will heal.
        Can use moves that target the field, team, or user
        Metronome can use a move that is disabled

119	Mirror Move	            Flying	    Status	    Clever  	20	—	    —	        I
        Mirror Move uses the last move targeted at the user by a Pokemon still on the field. A move called by
        Mirror Move in this way counts as the last move used. Moves that target multiple Pokemon count as long
        as the user of Mirror Move is one of the targets
        Fails if the target did not use a move prior, was switched out, or the last move used was Mirror Move
        Can use a move that was disabled
        The move can also target allies

120	Self-Destruct   	    Normal	    Physical	Beautiful	5	200*	100%	    I
        Damages the target before the user faints
        If the target faints before the move is executed, the user still faints
        Sturdy will not prevent fainting
        Cannot be used if a Pokemon with the Ability Damp is on the field

121	Egg Bomb	            Normal	    Physical	Cute	    10	100	    75%	        I
        No secondary effects.
        Does no damage to opponents with the Bulletproof Ability

122	Lick	                Ghost	    Physical	Cute	    30	30	    100%	    I
        30% chance of paralyzing the target

123	Smog	                Poison	    Special	    Tough	    20	30*    70%	        I
        40% chance of poisoning the target

124	Sludge	                Poison	    Special	    Tough	    20	65	    100%	    I
        30% chance of poisoning the target

125	Bone Club	            Ground	    Physical	Tough	    20	65	    85%	        I
        10% chance of causing the target to flinch
        Cannot cause a target with substitute to flinch

126	Fire Blast      	    Fire	    Special	    Beautiful	5	110     85%	        I
        10% chance to burn the target

127	Waterfall       	    Water	    Physical	Tough	    15	80	    100%	    I
        20% chance of causing the target to flinch

128	Clamp	                Water	    Physical	Tough	    15*	35	    85%*	    I
        TODO: More research
        Lasts 2-5 turns     (37.5)2-3, (12.5)4-5
        Does 1/16 of target's max HP as damage, does 1/8 of the target's max HP on the last turn
        Ghost type Pokemon cannot be trapped by Clamp
        Rapid Spin can break free

129	Swift	                Normal	    Special 	Cool	    20	60	    —	        I
        Bypasses accuracy checks to always hit, unless the target is in the semi-invulnerable turn
        Hits adjacent Pokemon in multi battles

130	Skull Bash	            Normal	    Physical	Tough	    10*	130*	100%	    I
        Charges for one turn
        Raise the user's Defense by one level on the turn it is selected
        PP deducted when the move does damage on the next turn, becomes the last move used
        No PP deduction if the move fails
        Sleep, freeze, partial trapping, and flinching will pause but not disrupt the duration of Skull Bash
        User cannot switch out until the move finishes executing

131	Spike Cannon	        Normal	    Physical	Cool	    15	20	    100%	    I
        Lasts 2-5 turns     (33.3)2-3, (16.7)4-5
        Each hit calculated separately
        Ability Skill link makes the move hits for 5 times
        Same as other moves if Sturdy activates on the target on first strike, next hit makes it faint
        If the target has Weak Armor, each strike will activate it

132	Constrict       	    Normal	    Physical	Tough	    35	10	    100%	    I
        10% chance of lowering the target's Speed stat by one stage

133	Amnesia	                Psychic	    Status	    Cute	    20	—	    —	        I
        Raises the user's Special Defense by two stages

134	Kinesis	                Psychic	    Status	    Clever	    15	—	    80%	        I
        Decreases the target's accuracy stat by one stage
        Can be reflected by Magic Coat

135	Soft-Boiled	            Normal	    Status	    Cute	    10	—	    —	        I
        Restores up to 50% of max HP, fails if the user already has max HP

136	High Jump Kick	        Fighting	Physical	Cool	    10*	130*	90%	        I
        Takes crash damage if the move is protected against or due to type immunity
        Cannot be used if Gravity is in effect
        Crash damage is equal to half of the user's max HP(rounded down)

137	Glare	                Normal	    Status	    Tough   	30	—	    100%*	    I
        Paralyzes the target
        Can hit ghost type

138	Dream Eater	            Psychic	    Special	    Clever	    15	100	    100%	    I
        Will always fail if the target is not asleep
        Up to 50% of the damages is restored as health
        When used on a Pokemon with Liquid Ooze,damages instead of heals
        Cannot be used if the user is under the effect of Heal Block
        Dream Eater affects targets with the Comatose Ability, which act as though they are asleep without the status condition

139	Poison Gas      	    Poison	    Status	    Clever	    40	—	    90%*	    I
        Poisons the target, attacks all adjacent Pokemon

140	Barrage	                Normal  	Physical	Cute	    20	15	    85%	        I
        2-5 turns, same percentage as other multi strike moves
        Same effect of Sturdy as other multi strike moves
        Each hit has a chance to flinch, all strikes calculated independently
        If the target has Weak Armor, each strike will activate it
        No effect on targets with Bulletproof

141	Leech Life	            Bug	        Physical	Clever	    10*	80*	    100%	    I
        Heals up to 50% of the damage dealt
        Liquid Ooze causes damage instead of healing
        Heal Block prevents use of this move

142	Lovely Kiss	            Normal	    Status	    Beautiful	10	—	    75%	        I
        Causes the target to fall asleep
        Pokemon with Insomnia or Vital Spirit as their Ability are unaffected by Lovely Kiss
        Pokemon under the effect of Sweet Veil are also unaffected by Lovely Kiss

143	Sky Attack      	    Flying	    Physical	Cool	    5	200*	90%	        I
        Takes one turn to charge
        PP deducted same as other charging moves
        Last move affected in the same manner
        30% chance of causing flinch
        Has an increased critical hit ratio
        Can hit non adjacent opponents
        Sleep, freeze, partial trapping, and flinching will pause but not disrupt the duration of Sky Attack

144	Transform       	    Normal	    Status	    Clever	    10	—	    —	        I
        TODO: More research
        Changes the user's current type, current stats, current stat modifications, current moves,
        current species, and current cry to that of the target's
        Bypasses accuracy checks to always hit, even if the target is in the semi-invulnerable turn
        Does not copy the target's status conditions, level, current or maximum HP
        If a transformed Pokemon takes damage from a critical hit, its original stats will be used rather than
        its stats after Transform. The probability of a transformed Pokemon scoring a critical hit is based on the
        original Pokemon's base Speed, not the base Speed of the Pokemon it transformed into
        Each move copied by Transform will have 5 remaining PP of the usual maximum

145	Bubble	                Water	    Special	    Cute	    30	40*	    100%	    I
        10% chance of lowering the target's Speed stat by one stage
        Targets both Pokemon in double battles

146	Dizzy Punch     	    Normal	    Physical	Cute	    10	70	    100%	    I
        20% chance of confusing the target

147	Spore	                Grass	    Status	    Beautiful	15	—	    100%	    I
        Spore puts the target to sleep. Spore cannot affect targets with Insomnia or Vital Spirit as their Ability
        Grass-type Pokemon, Pokemon with Overcoat, and Pokemon under the effect of Sweet Veil are also unaffected by Spore

148	Flash	                Normal	    Status	    Beautiful	20	—	    100%*	    I
        Decreases the target's accuracy stat by one stage

149	Psywave	                Psychic	    Special	    Clever	    15	—	    100%*	    I
        Damage formula  (user's level) * (r * 10 + 50) / 100, where r is a random number from 0 to 10, deals at least 1 HP of damage

150	Splash	                Normal	    Status	    Cute	    40	—	    —	        I
        No effect, Splash's message is "But nothing happened!"
        Cannot be used while Gravity is in effect

151	Acid Armor	            Poison	    Status	    Tough	    20*	—	    —	        I
        Increases the user's Defense by two stages

152	Crabhammer	            Water	    Physical	Tough	    10	100*	90%*	    I
        Has an increased critical hit ratio

153	Explosion	            Normal	    Physical	Beautiful	5	250*	100%	    I
        Damages the target before the user faints
        Same as Self Destruct

154	Fury Swipes	            Normal	    Physical	Tough	    15	18	    80%	        I
        Same effect as other Multi Strike Moves

155	Bonemerang	            Ground	    Physical	Tough	    10	50	    90%	        I
        Hits the target twice, each hit calculated independently
        Bide only acknowledges the last hit
        Same effect on Sturdy an Weak Armor

156	Rest	                Psychic	    Status	    Cute	    10	—	    —	        I
        Causes the user's HP to go to its max value, removes existing non volatile status conditions and then falls asleep
        The user will awaken from Rest three turns after it is used and can attack on the third
        Rest fails if used via Sleep Talk regardless of HP
        If a Pokemon with the Early Bird Ability uses Rest, its Ability still applies and the
        Pokemon's turns asleep will be rounded down from three to one
        Rest will automatically fail if used by a Pokemon with Insomnia or Vital Spirit
        Rest will fail if it is used by a Pokemon with Leaf Guard during harsh sunlight
        Rest will fail if Electric Terrain or Misty Terrain is in effect and the user is grounded.
        Rest will also fail if the user is affected by Sweet Veil.
        Rest will now fail when used while Uproar is in effect even if the user has Soundproof

157	Rock Slide	            Rock	    Physical	Tough	    10	75	    90%	        I
        30% chance of causing the target to flinch
        Will hit adjacent opposing Pokemon

158	Hyper Fang	            Normal	    Physical	Cool	    15	80	    90%	        I
        10% chance of causing the target to flinch
        50% power boost when used by a Pokémon with Strong Jaw

159	Sharpen         	    Normal	    Status	    Cute	    30	—	    —	        I
        Increases the user's Attack stat by one stage

160	Conversion      	    Normal	    Status	    Beautiful	30	—	    —	        I
        Changes the user's current type to match the type of the move the user has in its first move slot
        Can be stolen by Snatch
        Can be used on Curse

161	Tri Attack	            Normal	    Special	    Beautiful	10	80	    100%	    I
        20% chance of either paralyzing, freezing, or burning the target
        Each ailment has a 6.67% chance of being inflicted

162	Super Fang	            Normal	    Physical	Tough	    10	—	    90%	        I
        Does damage equal to 50% of the targets current HP, deals at least 1 HP of damage

163	Slash	                Normal	    Physical	Cool	    20	70	    100%	    I
        Has an increased critical hit ratio

164	Substitute	            Normal	    Status	    Cute	    10	—	    —	        I
        Can not block moves used by Pokemon with the Ability Infiltrator
        The moves Hyperspace Hole, Hyperspace Fury, Play Nice, and Spectral Thief can also bypass a substitute

165	Struggle	            Normal	    Physical	Tough	    1*	50	    —*	        I
        TODO: More research
        Every Pokemon knows this move but it can not be learned
        User takes 1/4 of its HP as recoil damage
        Considered a normal type but deals neutral damage and unaffected by STAB
        Can hit through Wonder Guard
        Target randomly selected in double battles
        Still takes recoil damage even if its ability is  Rock Head or Magic Guard
        Can cause the Pokemon to knock itself out
        Bypasses accuracy checks to always hit, unless the target is in the semi-invulnerable turn
        The ability Reckless will not be boosted by the recoil
        Can not be copied by Mirror Move




166	Sketch	                Normal	    Status	    Clever	    1	—	    —	        II
167	Triple Kick	            Fighting	Physical	Cool	    10	10	    90%	        II
168	Thief	                Dark	    Physical	Tough	    25*	60*	    100%	    II
169	Spider Web	            Bug	        Status	    Clever	    10	—	    —	        II
170	Mind Reader     	    Normal	    Status	    Clever	    5	—	    —*	        II
171	Nightmare	            Ghost	    Status	    Clever	    15	—	    100%	    II
172	Flame Wheel	            Fire	    Physical	Beautiful	25	60	    100%	    II
173	Snore	                Normal	    Special	    Cute	    15	50*	    100%	    II
174	Curse*	                Ghost	    Status	    Tough	    10	—	    —	        II
175	Flail	                Normal	    Physical	Cute	    15	—	    100%	    II
176	Conversion 2	        Normal	    Status	    Beautiful	30	—	    —	        II
177	Aeroblast	            Flying	    Special	    Cool	    5	100	    95%	        II
178	Cotton Spore            Grass	    Status	    Beautiful	40	—	    100%*	    II
179	Reversal	            Fighting	Physical	Cool	    15	—	    100%	    II
180	Spite	                Ghost	    Status	    Tough	    10	—	    100%	    II
181	Powder Snow	            Ice	        Special	    Beautiful	25	40	    100%	    II
182	Protect	                Normal	    Status	    Cute	    10	—	    —	        II
183	Mach Punch	            Fighting	Physical	Cool	    30	40	    100%	    II
184	Scary Face	            Normal	    Status	    Tough	    10	—	    100%*	    II
185	Feint Attack	        Dark	    Physical	Clever	    20	60	    —	        II
186	Sweet Kiss*	            Fairy	    Status	    Cute	    10	—	    75%	        II
187	Belly Drum	            Normal	    Status	    Cute	    10	—	    —	        II
188	Sludge Bomb	            Poison	    Special	    Tough	    10	90	    100%	    II
189	Mud-Slap	            Ground	    Special	    Cute	    10	20	    100%	    II
190	Octazooka	            Water	    Special	    Tough	    10	65	    85%	        II
191	Spikes	                Ground	    Status	    Clever	    20	—	    —	        II
192	Zap Cannon	            Electric	Special     Cool	    5	120*	50%	        II
193	Foresight	            Normal	    Status	    Clever	    40	—	    —*	        II
194	Destiny Bond	        Ghost	    Status	    Clever	    5	—	    —	        II
195	Perish Song     	    Normal	    Status	    Beautiful	5	—	    —	        II
196	Icy Wind	            Ice	        Special 	Beautiful	15	55	    95%	        II
197	Detect	                Fighting	Status	    Cool	    5	—	    —	        II
198	Bone Rush	            Ground	    Physical	Tough	    10	25	    90%*	    II
199	Lock-On	                Normal	    Status	    Clever	    5	—	    —*	        II
200	Outrage	                Dragon	    Physical	Cool	    10*	120*	100%	    II
201	Sandstorm	            Rock	    Status	    Tough	    10	—	    —	        II
202	Giga Drain	            Grass	    Special	    Clever	    10*	75*	    100%    	II
203	Endure	                Normal	    Status	    Tough	    10	—	    —	        II
204	Charm*	                Fairy	    Status	    Cute	    20	—	    100%    	II
205	Rollout	                Rock	    Physical	Cute	    20	30	    90%	        II
206	False Swipe	            Normal	    Physical	Cool	    40	40	    100%    	II
207	Swagger	                Normal	    Status	    Cute	    15	—	    85%*    	II
208	Milk Drink	            Normal	    Status	    Cute	    10	—	    —	        II
209	Spark	                Electric	Physical	Cool	    20	65	    100%    	II
210	Fury Cutter	            Bug 	    Physical	Cool	    20	40*	    95%	        II
211	Steel Wing	            Steel	    Physical	Cool	    25	70	    90%	        II
212	Mean Look	            Normal	    Status	    Beautiful	5	—	    —	        II
213	Attract	                Normal	    Status	    Cute	    15	—	    100%    	II
214	Sleep Talk	            Normal  	Status	    Cute	    10	—	    —	        II
215	Heal Bell	            Normal  	Status	    Beautiful	5	—	    —	        II
216	Return	                Normal	    Physical	Cute	    20	—	    100%	    II
217	Present         	    Normal	    Physical	Cute	    15	—	    90%	        II
218	Frustration	            Normal	    Physical	Cute	    20	—	    100%    	II
219	Safeguard	            Normal	    Status	    Beautiful	25	—	    —	        II
220	Pain Split	            Normal	    Status	    Clever	    20	—	    —*      	II
221	Sacred Fire     	    Fire	    Physical	Beautiful	5	100	    95%     	II
222	Magnitude	            Ground      Physical	Tough	    30	—	    100%    	II
223	Dynamic Punch	        Fighting	Physical	Cool	    5	100	    50%	        II
224	Megahorn	            Bug	        Physical	Cool	    10	120	    85%	        II
225	Dragon Breath	        Dragon	    Special	    Cool	    20	60	    100%	    II
226	Baton Pass	            Normal	    Status	    Cute	    40	—	    —       	II
227	Encore	                Normal	    Status	    Cute	    5	—	    100%    	II
228	Pursuit	                Dark	    Physical	Clever	    20	40	    100%    	II
229	Rapid Spin	            Normal	    Physical	Cool	    40	20	    100%    	II
230	Sweet Scent     	    Normal	    Status	    Cute	    20	—	    100%    	II
231	Iron Tail	            Steel	    Physical	Cool	    15	100	    75%	        II
232	Metal Claw	            Steel	    Physical	Cool	    35	50	    95%     	II
233	Vital Throw     	    Fighting	Physical	Cool	    10	70	    —       	II
234	Morning Sun	            Normal	    Status	    Beautiful	5	—	    —       	II
235	Synthesis	            Grass	    Status  	Clever  	5	—	    —       	II
236	Moonlight*	            Fairy	    Status	    Beautiful	5	—	    —       	II
237	Hidden Power	        Normal	    Special	    Clever	    15	60* 	100%    	II
238	Cross Chop	            Fighting	Physical	Cool	    5	100 	80%	        II
239	Twister	                Dragon	    Special	    Cool	    20	40	    100%    	II
240	Rain Dance      	    Water	    Status	    Beautiful	5	—	    —	        II
241	Sunny Day	            Fire	    Status	    Beautiful	5	—	    —	        II
242	Crunch	                Dark	    Physical	Tough	    15	80	    100%    	II
243	Mirror Coat	            Psychic	    Special 	Beautiful	20	—	    100%    	II
244	Psych Up	            Normal	    Status	    Clever	    10	—	    —	        II
245	Extreme Speed	        Normal	    Physical	Cool	    5	80	    100%	    II
246	Ancient Power   	    Rock	    Special 	Tough	    5	60	    100%    	II
247	Shadow Ball	            Ghost	    Special	    Clever	    15	80	    100%	    II
248	Future Sight	        Psychic	    Special	    Clever	    10*	120*	100%*   	II
249	Rock Smash	            Fighting	Physical	Tough	    15	40*	    100%	    II
250	Whirlpool	            Water	    Special 	Beautiful	15	35*	    85%*	    II
251	Beat Up	                Dark	    Physical	Clever  	10	—*	    100%    	II
252	Fake Out	            Normal	    Physical	Cute	    10	40	    100%    	III
253	Uproar	                Normal	    Special	    Cute	    10	90*	    100%    	III
254	Stockpile	            Normal	    Status	    Tough	    20*	—	    —	        III
255	Spit Up	                Normal	    Special 	Tough	    10	—	    100%    	III
256	Swallow	                Normal	    Status	    Tough	    10	—	    —	        III
257	Heat Wave	            Fire	    Special 	Beautiful	10	95* 	90%	        III
258	Hail	                Ice	        Status	    Beautiful	10	—	    —	        III
259	Torment	                Dark	    Status	    Tough	    15	—	    100%	    III
260	Flatter	                Dark	    Status	    Clever	    15	—   	100%	    III
261	Will-O-Wisp	            Fire	    Status	    Beautiful	15	—	    85%*	    III
262	Memento	                Dark	    Status	    Tough	    10	—	    100%	    III
263	Facade	                Normal	    Physical	Cute	    20	70      100%	    III
264	Focus Punch	            Fighting	Physical	Tough	    20	150 	100%	    III
265	Smelling Salts	        Normal	    Physical	Tough	    10	70* 	100%	    III
266	Follow Me	            Normal	    Status	    Cute	    20	—	    —	        III
267	Nature Power	        Normal	    Status	    Beautiful	20	—	    —	        III
268	Charge	                Electric	Status  	Clever	    20	—	    —	        III
269	Taunt	                Dark	    Status	    Clever	    20	—	    100%	    III
270	Helping Hand	        Normal	    Status      Clever	    20	—	    —	        III
271	Trick	                Psychic	    Status	    Clever	    10	—	    100%	    III
272	Role Play	            Psychic	    Status  	Cute	    10	—	    —	        III
273	Wish	                Normal	    Status  	Cute	    10	—	    —	        III
274	Assist	                Normal	    Status	    Cute	    20	—	    —	        III
275	Ingrain	                Grass	    Status	    Clever	    20	—	    —	        III
276	Superpower	            Fighting	Physical	Tough	    5	120 	100%	    III
277	Magic Coat	            Psychic	    Status	    Beautiful	15	—	    —	        III
278	Recycle	                Normal	    Status	    Clever	    10	—	    —	        III
279	Revenge	                Fighting	Physical	Tough	    10	60	    100%	    III
280	Brick Break     	    Fighting	Physical	Cool	    15	75	    100%    	III
281	Yawn	                Normal	    Status  	Cute	    10	—	    —       	III
282	Knock Off	            Dark	    Physical	Clever	    20	65*	    100%	    III
283	Endeavor	            Normal	    Physical	Tough	    5	—	    100%	    III
284	Eruption	            Fire	    Special 	Beautiful	5	150 	100%	    III
285	Skill Swap	            Psychic 	Status  	Clever	    10	—	    —	        III
286	Imprison	            Psychic     Status	    Clever	    10	—	    —	        III
287	Refresh	                Normal	    Status	    Cute	    20	—	    —	        III
288	Grudge	                Ghost	    Status	    Tough	    5	—	    —	        III
289	Snatch	                Dark	    Status	    Clever	    10	—	    —	        III
290	Secret Power	        Normal	    Physical	Clever	    20	70	    100%	    III
291	Dive	                Water	    Physical	Beautiful	10	80*	    100%	    III
292	Arm Thrust	            Fighting	Physical	Tough	    20	15	    100%	    III
293	Camouflage	            Normal	    Status	    Clever	    20	—	    —	        III
294	Tail Glow	            Bug     	Status	    Beautiful	20	—	    —	        III
295	Luster Purge	        Psychic	    Special	    Clever	    5	70	    100%	    III
296	Mist Ball	            Psychic	    Special	    Clever	    5	70	    100%	    III
297	Feather Dance	        Flying	    Status	    Beautiful	15	—	    100%	    III
298	Teeter Dance	        Normal	    Status	    Cute	    20	—	    100%	    III
299	Blaze Kick	            Fire	    Physical	Cool	    10	85	    90%	        III
300	Mud Sport	            Ground	    Status	    Cute	    15	—	    —	        III
301	Ice Ball	            Ice	        Physical	Beautiful	20	30  	90%     	III
302	Needle Arm	            Grass	    Physical	Clever	    15	60  	100%	    III
303	Slack Off	            Normal	    Status	    Cute	    10	—   	—	        III
304	Hyper Voice	            Normal	    Special 	Cool	    10	90  	100%    	III
305	Poison Fang     	    Poison	    Physical	Clever	    15	50  	100%	    III
306	Crush Claw	            Normal	    Physical	Cool	    10	75  	95%	        III
307	Blast Burn	            Fire	    Special	    Beautiful	5	150 	90%	        III
308	Hydro Cannon	        Water	    Special	    Beautiful	5	150 	90%     	III
309	Meteor Mash	            Steel	    Physical	Cool	    10	90* 	90%*    	III
310	Astonish	            Ghost	    Physical	Cute	    15	30	    100%	    III
311	Weather Ball	        Normal	    Special	    Beautiful	10	50	    100%	    III
312	Aromatherapy	        Grass	    Status	    Clever	    5	—	    —	        III
313	Fake Tears	            Dark	    Status	    Cute	    20	—	    100%    	III
314	Air Cutter	            Flying	    Special	    Cool	    25	60*	    95%	        III
315	Overheat	            Fire	    Special	    Beautiful	5	130*	90%	        III
316	Odor Sleuth	            Normal	    Status	    Clever	    40	—	    —*         III
317	Rock Tomb	            Rock	    Physical	Clever	    15*	60* 	95%*    	III
318	Silver Wind	            Bug	        Special 	Beautiful	5	60  	100%    	III
319	Metal Sound	            Steel	    Status	    Clever	    40	—   	85%     	III
320	Grass Whistle   	    Grass	    Status	    Clever	    15	—   	55%	        III
321	Tickle	                Normal	    Status	    Cute	    20	—   	100%    	III
322	Cosmic Power	        Psychic 	Status	    Beautiful	20	—	    —	        III
323	Water Spout	            Water	    Special 	Beautiful	5	150 	100%    	III
324	Signal Beam	            Bug	        Special	    Beautiful	15	75	    100%    	III
325	Shadow Punch	        Ghost	    Physical	Clever	    20	60	    —	        III
326	Extrasensory	        Psychic	    Special	    Cool	    20*	80	    100%    	III
327	Sky Uppercut    	    Fighting    Physical	Cool	    15	85	    90%	        III
328	Sand Tomb	            Ground	    Physical	Clever	    15	35* 	85%*	    III
329	Sheer Cold	            Ice	        Special	    Beautiful	5	—	    —*	        III
330	Muddy Water	            Water	    Special	    Tough	    10	90* 	85%	        III
331	Bullet Seed	            Grass	    Physical	Cool	    30	25* 	100%	    III
332	Aerial Ace	            Flying  	Physical	Cool	    20	60	    —	        III
333	Icicle Spear	        Ice	        Physical	Beautiful	30	25* 	100%	    III
334	Iron Defense	        Steel	    Status	    Tough	    15	—	    —	        III
335	Block	                Normal  	Status	    Cute	    5	—	    —	        III
336	Howl	                Normal	    Status	    Cool	    40	—	    —	        III
337	Dragon Claw         	Dragon	    Physical	Cool	    15	80	    100%    	III
338	Frenzy Plant	        Grass	    Special	    Cool	    5	150 	90%	        III
339	Bulk Up	                Fighting	Status	    Cool	    20	—   	—	        III
340	Bounce	                Flying	    Physical	Cute	    5	85  	85%	        III
341	Mud Shot	            Ground	    Special	    Tough	    15	55  	95%	        III
342	Poison Tail	            Poison	    Physical	Clever  	25	50  	100%	    III
343	Covet	                Normal	    Physical	Cute	    25*	60* 	100%	    III
344	Volt Tackle	            Electric	Physical	Cool	    15	120 	100%	    III
345	Magical Leaf	        Grass	    Special	    Beautiful	20	60	    —       	III
346	Water Sport	            Water	    Status	    Cute	    15	—	    —	        III
347	Calm Mind	            Psychic 	Status	    Clever  	20	—	    —	        III
348	Leaf Blade	            Grass	    Physical	Cool	    15	90* 	100%	    III
349	Dragon Dance	        Dragon	    Status	    Cool	    20	—	    —	        III
350	Rock Blast	            Rock	    Physical	Tough	    10	25	    90%*	    III
351	Shock Wave	            Electric	Special 	Cool	    20	60	    —	        III
352	Water Pulse	            Water	    Special 	Beautiful	20	60	    100%	    III
353	Doom Desire	            Steel	    Special 	Beautiful	5	140*	100%*   	III
354	Psycho Boost	        Psychic	    Special 	Clever	    5	140	    90%	        III
355	Roost	                Flying	    Status	    Clever	    10	—	    —	        IV
356	Gravity	                Psychic	    Status	    Clever	    5	—	    —	        IV
357	Miracle Eye     	    Psychic	    Status	    Clever	    40	—	    —	        IV
358	Wake-Up Slap	        Fighting	Physical	Tough	    10	70* 	100%	    IV
359	Hammer Arm	            Fighting	Physical	Tough	    10	100 	90%     	IV
360	Gyro Ball	            Steel	    Physical	Cool	    5	—	    100%    	IV
361	Healing Wish	        Psychic	    Status	    Beautiful	10	—	    —	        IV
362	Brine	                Water	    Special	    Tough	    10	65	    100%	    IV
363	Natural Gift	        Normal	    Physical	Clever	    15	—	    100%	    IV
364	Feint	                Normal	    Physical	Clever	    10	30* 	100%	    IV
365	Pluck	                Flying	    Physical	Cute	    20	60	    100%	    IV
366	Tailwind	            Flying	    Status	    Cool	    15*	—	    —	        IV
367	Acupressure     	    Normal	    Status	    Tough	    30	—	    —	        IV
368	Metal Burst	            Steel	    Physical	Cool	    10	—	    100%	    IV
369	U-turn	                Bug	        Physical	Cute	    20	70	    100%	    IV
370	Close Combat    	    Fighting	Physical	Tough	    5	120	    100%	    IV
371	Payback	                Dark	    Physical	Tough	    10	50	    100%	    IV
372	Assurance	            Dark	    Physical	Clever	    10	60*	    100%	    IV
373	Embargo	                Dark	    Status	    Clever	    15	—	    100%	    IV
374	Fling	                Dark	    Physical	Cute	    10	—	    100%	    IV
375	Psycho Shift	        Psychic	    Status	    Clever	    10	—	    100%*	    IV
376	Trump Card	            Normal	    Special	    Cool	    5	—	    —	        IV
377	Heal Block	            Psychic	    Status	    Clever	    15	—	    100%    	IV
378	Wring Out	            Normal	    Special	    Tough	    5	—	    100%	    IV
379	Power Trick	            Psychic 	Status	    Clever	    10	—   	—	        IV
380	Gastro Acid	            Poison      Status  	Tough	    10	—   	100%	    IV
381	Lucky Chant     	    Normal  	Status	    Cute	    30	—	    —	        IV
382	Me First	            Normal	    Status	    Clever	    20	—	    —	        IV
383	Copycat	                Normal	    Status	    Cute	    20	—	    —	        IV
384	Power Swap	            Psychic	    Status	    Clever	    10	—	    —	        IV
385	Guard Swap	            Psychic	    Status	    Clever	    10	—	    —	        IV
386	Punishment	            Dark	    Physical	Cool	    5	—	    100%	    IV
387	Last Resort	            Normal	    Physical	Cute	    5	140*	100%	    IV
388	Worry Seed	            Grass	    Status	    Clever	    10	—	    100%	    IV
389	Sucker Punch	        Dark	    Physical	Clever	    5	70* 	100%    	IV
390	Toxic Spikes	        Poison	    Status	    Clever	    20	—	    —	        IV
391	Heart Swap	            Psychic 	Status	    Clever	    10	—	    —	        IV
392	Aqua Ring	            Water	    Status	    Beautiful	20	—	    —	        IV
393	Magnet Rise     	    Electric	Status	    Clever	    10	—	    —	        IV
394	Flare Blitz	            Fire	    Physical	Cool	    15	120	    100%	    IV
395	Force Palm	            Fighting	Physical	Cool	    10	60	    100%	    IV
396	Aura Sphere	            Fighting	Special 	Beautiful	20	80*	    —	        IV
397	Rock Polish	            Rock	    Status	    Tough	    20	—	    —	        IV
398	Poison Jab	            Poison	    Physical	Tough	    20	80	    100%	    IV
399	Dark Pulse	            Dark	    Special	    Cool	    15	80	    100%	    IV
400	Night Slash	            Dark	    Physical	Cool	    15	70	    100%	    IV
401	Aqua Tail	            Water	    Physical	Beautiful	10	90	    90%     	IV
402	Seed Bomb	            Grass	    Physical	Tough	    15	80	    100%	    IV
403	Air Slash	            Flying	    Special	    Cool	    15*	75	    95%	        IV
404	X-Scissor	            Bug	        Physical	Cool	    15	80	    100%	    IV
405	Bug Buzz	            Bug	        Special	    Beautiful	10	90	    100%	    IV
406	Dragon Pulse    	    Dragon	    Special	    Beautiful	10	85*	    100%	    IV
407	Dragon Rush	            Dragon	    Physical	Tough   	10	100	    75%     	IV
408	Power Gem	            Rock	    Special	    Beautiful	20	80*	    100%	    IV
409	Drain Punch	            Fighting	Physical	Tough	    10*	75*	    100%	    IV
410	Vacuum Wave	            Fighting	Special 	Cool	    30	40	    100%	    IV
411	Focus Blast	            Fighting	Special	    Cool	    5	120	    70%	        IV
412	Energy Ball	            Grass	    Special	    Beautiful	10	90*	    100%	    IV
413	Brave Bird	            Flying	    Physical	Cool	    15	120	    100%	    IV
414	Earth Power	            Ground	    Special	    Beautiful	10	90	    100%	    IV
415	Switcheroo	            Dark	    Status	    Clever	    10	—	    100%	    IV
416	Giga Impact	            Normal	    Physical	Tough	    5	150	    90%	        IV
417	Nasty Plot	            Dark	    Status	    Clever  	20	—   	—	        IV
418	Bullet Punch    	    Steel	    Physical	Tough	    30	40	    100%	    IV
419	Avalanche	            Ice	        Physical	Beautiful	10	60	    100%	    IV
420	Ice Shard	            Ice	        Physical	Beautiful	30	40	    100%	    IV
421	Shadow Claw	            Ghost	    Physical	Cool	    15	70	    100%	    IV
422	Thunder Fang    	    Electric	Physical	Cool	    15	65	    95%	        IV
423	Ice Fang	            Ice     	Physical	Cool	    15	65	    95%	        IV
424	Fire Fang	            Fire	    Physical	Cool	    15	65	    95%     	IV
425	Shadow Sneak            Ghost	    Physical	Clever	    30	40	    100%    	IV
426	Mud Bomb	            Ground	    Special	    Cute	    10	65	    85%	        IV
427	Psycho Cut	            Psychic 	Physical	Cool	    20	70	    100%	    IV
428	Zen Headbutt	        Psychic 	Physical	Clever	    15	80	    90%	        IV
429	Mirror Shot	            Steel	    Special 	Beautiful	10	65	    85%	        IV
430	Flash Cannon	        Steel	    Special 	Beautiful	10	80	    100%	    IV
431	Rock Climb	            Normal	    Physical	Tough	    20	90	    85%	        IV
432	Defog	                Flying	    Status	    Cool	    15	—	    —	        IV
433	Trick Room	            Psychic 	Status	    Clever  	5	—	    —	        IV
434	Draco Meteor    	    Dragon	    Special	    Beautiful	5	130*	90%	        IV
435	Discharge	            Electric	Special 	Beautiful	15	80	    100%	    IV
436	Lava Plume	            Fire	    Special	    Tough	    15	80	    100%	    IV
437	Leaf Storm	            Grass	    Special	    Beautiful	5	130*	90%	        IV
438	Power Whip	            Grass	    Physical	Tough	    10	120 	85%	        IV
439	Rock Wrecker    	    Rock	    Physical	Tough   	5	150 	90%	        IV
440	Cross Poison	        Poison	    Physical	Cool	    20	70	    100%    	IV
441	Gunk Shot	            Poison	    Physical	Tough   	5	120 	80%*	    IV
442	Iron Head	            Steel	    Physical	Tough   	15	80	    100%	    IV
443	Magnet Bomb	            Steel	    Physical	Cool	    20	60	    —	        IV
444	Stone Edge	            Rock	    Physical	Tough	    5	100	    80%     	IV
445	Captivate	            Normal	    Status  	Cute	    20	—	    100%	    IV
446	Stealth Rock    	    Rock	    Status  	Cool	    20	—	    —	        IV
447	Grass Knot	            Grass	    Special 	Cute	    20	—	    100%	    IV
448	Chatter	                Flying	    Special 	Cute	    20	65*	    100%	    IV
449	Judgment	            Normal  	Special	    Beautiful	10	100	    100%	    IV
450	Bug Bite	            Bug     	Physical	Cute	    20	60	    100%	    IV
451	Charge Beam	            Electric	Special	    Beautiful	10	50	    90%	        IV
452	Wood Hammer	            Grass	    Physical	Tough	    15	120	    100%    	IV
453	Aqua Jet	            Water	    Physical	Cool	    20	40	    100%    	IV
454	Attack Order    	    Bug	        Physical	Clever	    15	90	    100%	    IV
455	Defend Order	        Bug	        Status	    Clever	    10	—   	—	        IV
456	Heal Order	            Bug	        Status	    Clever	    10	—	    —	        IV
457	Head Smash	            Rock	    Physical	Tough	    5	150 	80%	        IV
458	Double Hit	            Normal	    Physical	Cool	    10	35	    90%	        IV
459	Roar of Time	        Dragon	    Special 	Beautiful	5	150	    90%	        IV
460	Spacial Rend	        Dragon	    Special	    Beautiful	5	100	    95%	        IV
461	Lunar Dance	            Psychic 	Status	    Beautiful	10	—	    —	        IV
462	Crush Grip	            Normal	    Physical	Tough	    5	—	    100%	    IV
463	Magma Storm	            Fire	    Special 	Tough	    5	100*	75%*	    IV
464	Dark Void	            Dark	    Status	    Clever	    10	—	    50%*	    IV
465	Seed Flare	            Grass	    Special	    Beautiful	5	120	    85%     	IV
466	Ominous Wind    	    Ghost	    Special	    Beautiful	5	60	    100%	    IV
467	Shadow Force    	    Ghost	    Physical	Cool	    5	120	    100%	    IV
468	Hone Claws	            Dark	    Status	    Cute	    15	—	    —	        V
469	Wide Guard	            Rock	    Status	    Tough	    10	—	    —	        V
470	Guard Split	            Psychic	    Status	    Clever  	10	—	    —	        V
471	Power Split	            Psychic	    Status	    Clever  	10	—	    —	        V
472	Wonder Room	            Psychic 	Status	    Clever	    10	—	    —	        V
473	Psyshock	            Psychic 	Special	    Beautiful	10	80	    100%	    V
474	Venoshock	            Poison	    Special	    Beautiful	10	65	    100%	    V
475	Autotomize	            Steel	    Status	    Beautiful	15	—	    —	        V
476	Rage Powder	            Bug	        Status	    Clever	    20	—	    —	        V
477	Telekinesis	            Psychic     Status	    Clever	    15	—	    —       	V
478	Magic Room	            Psychic 	Status	    Clever	    10	—	    —	        V
479	Smack Down	            Rock	    Physical	Tough	    15	50	    100%	    V
480	Storm Throw	            Fighting	Physical	Cool	    10	60*	    100%	    V
481	Flame Burst	            Fire	    Special 	Beautiful	15	70	    100%	    V
482	Sludge Wave	            Poison	    Special 	Tough	    10	95	    100%	    V
483	Quiver Dance    	    Bug	        Status	    Beautiful	20	—	    —	        V
484	Heavy Slam	            Steel	    Physical	Tough	    10	—	    100%	    V
485	Synchronoise	        Psychic 	Special 	Clever	    10*	120*	100%	    V
486	Electro Ball	        Electric	Special 	Cool	    10	—	    100%	    V
487	Soak	                Water	    Status	    Cute	    20	—	    100%	    V
488	Flame Charge	        Fire	    Physical	Cool	    20	50	    100%	    V
489	Coil	                Poison	    Status	    Tough	    20	—	    —	        V
490	Low Sweep	            Fighting	Physical	Clever	    20	65*	    100%	    V
491	Acid Spray	            Poison	    Special 	Beautiful	20	40	    100%	    V
492	Foul Play	            Dark	    Physical	Clever	    15	95	    100%	    V
493	Simple Beam     	    Normal	    Status	    Cute	    15	—	    100%	    V
494	Entrainment	            Normal	    Status	    Cute	    15	—	    100%	    V
495	After You	            Normal	    Status	    Cute	    15	—	    —	        V
496	Round	                Normal	    Special	    Beautiful	15	60	    100%	    V
497	Echoed Voice	        Normal	    Special	    Beautiful	15	40	    100%	    V
498	Chip Away	            Normal	    Physical	Tough	    20	70	    100%	    V
499	Clear Smog	            Poison	    Special	    Beautiful	15	50	    —	        V
500	Stored Power	        Psychic	    Special	    Clever	    10	20	    100%	    V
501	Quick Guard     	    Fighting	Status	    Cool	    15	—	    —	        V
502	Ally Switch	            Psychic	    Status	    Clever	    15	—	    —	        V
503	Scald	                Water	    Special	    Tough	    15	80	    100%	    V
504	Shell Smash	            Normal	    Status	    Tough	    15	—	    —	        V
505	Heal Pulse	            Psychic     Status	    Beautiful	10	—	    —	        V
506	Hex	                    Ghost	    Special	    Clever	    10	65*	    100%	    V
507	Sky Drop	            Flying  	Physical	Tough	    10	60	    100%	    V
508	Shift Gear      	    Steel	    Status	    Clever	    10	—	    —	        V
509	Circle Throw	        Fighting	Physical	Cool	    10	60	    90%	        V
510	Incinerate	            Fire	    Special	    Tough	    15	60*	    100%	    V
511	Quash	                Dark	    Status	    Clever	    15	—	    100%	    V
512	Acrobatics	            Flying	    Physical	Cool	    15	55	    100%	    V
513	Reflect Type	        Normal  	Status	    Clever	    15	—	    —	        V
514	Retaliate	            Normal	    Physical	Cool	    5	70	    100%	    V
515	Final Gambit	        Fighting	Special	    Tough	    5	—	    100%	    V
516	Bestow	                Normal	    Status	    Cute	    15	—	    —	        V
517	Inferno	                Fire	    Special	    Beautiful	5	100	    50%	        V
518	Water Pledge	        Water	    Special	    Beautiful	10	80*	    100%	    V
519	Fire Pledge	            Fire	    Special	    Beautiful	10	80*	    100%	    V
520	Grass Pledge	        Grass   	Special 	Beautiful	10	80*	    100%	    V
521	Volt Switch     	    Electric	Special 	Cool	    20	70	    100%	    V
522	Struggle Bug	        Bug	        Special 	Cute	    20	50*	    100%	    V
523	Bulldoze	            Ground	    Physical	Tough	    20	60	    100%	    V
524	Frost Breath	        Ice	        Special 	Beautiful	10	60*	    90%	        V
525	Dragon Tail     	    Dragon	    Physical	Tough	    10	60	    90%	        V
526	Work Up	                Normal  	Status	    Tough	    30	—	    —	        V
527	Electroweb	            Electric	Special	    Beautiful	15	55	    95%	        V
528	Wild Charge	            Electric	Physical	Tough	    15	90	    100%	    V
529	Drill Run	            Ground	    Physical	Tough	    10	80	    95%	        V
530	Dual Chop	            Dragon  	Physical	Tough	    15	40	    90%	        V
531	Heart Stamp	            Psychic 	Physical	Cute	    25	60	    100%	    V
532	Horn Leech	            Grass	    Physical	Tough	    10	75	    100%	    V
533	Sacred Sword	        Fighting	Physical	Cool	    15*	90	    100%	    V
534	Razor Shell	            Water	    Physical	Cool	    10	75	    95%	        V
535	Heat Crash	            Fire	    Physical	Tough	    10	—	    100%	    V
536	Leaf Tornado	        Grass	    Special	    Cool	    10	65	    90%	        V
537	Steamroller	            Bug	        Physical	Tough	    20	65	    100%	    V
538	Cotton Guard	        Grass   	Status	    Cute	    10	—	    —	        V
539	Night Daze	            Dark	    Special	    Cool	    10	85	    95%	        V
540	Psystrike	            Psychic 	Special	    Cool	    10	100	    100%	    V
541	Tail Slap	            Normal  	Physical	Cute	    10	25	    85%	        V
542	Hurricane	            Flying  	Special	    Tough	    10	110*	70%	        V
543	Head Charge	            Normal	    Physical	Tough	    15	120	    100%	    V
544	Gear Grind	            Steel	    Physical	Clever	    15	50	    85%	        V
545	Searing Shot	        Fire	    Special	    Cool	    5	100	    100%	    V
546	Techno Blast	        Normal	    Special	    Cool	    5	120*	100%	    V
547	Relic Song	            Normal  	Special	    Beautiful	10	75	    100%	    V
548	Secret Sword	        Fighting	Special	    Beautiful	10	85	    100%	    V
549	Glaciate	            Ice	        Special	    Beautiful	10	65	    95%	        V
550	Bolt Strike	            Electric	Physical	Beautiful	5	130	    85%	        V
551	Blue Flare	            Fire	    Special	    Beautiful	5	130	    85%	        V
552	Fiery Dance	            Fire	    Special	    Beautiful	10	80	    100%	    V
553	Freeze Shock	        Ice	        Physical	Beautiful	5	140	    90%	        V
554	Ice Burn	            Ice	        Special	    Beautiful	5	140	    90%	        V
555	Snarl	                Dark    	Special	    Tough	    15	55	    95%	        V
556	Icicle Crash	        Ice     	Physical	Beautiful	10	85	    90%	        V
557	V-create	            Fire	    Physical	Cool	    5	180	    95%	        V
558	Fusion Flare	        Fire	    Special	    Beautiful	5	100	    100%	    V
559	Fusion Bolt	            Electric	Physical	Cool	    5	100	    100%	    V
560	Flying Press	        Fighting	Physical	Tough	    10	100*	95%	        VI
561	Mat Block	            Fighting	Status	    Cool	    10	—	    —	        VI
562	Belch	                Poison	    Special	    Tough	    10	120	    90%	        VI
563	Rototiller	            Ground	    Status	    Tough	    10	—	    —	        VI
564	Sticky Web      	    Bug	        Status	    Tough	    20	—	    —	        VI
565	Fell Stinger	        Bug     	Physical	Cool	    25	50*	    100%	    VI
566	Phantom Force	        Ghost	    Physical	Cool	    10	90	    100%	    VI
567	Trick-or-Treat          Ghost   	Status	    Cute	    20	—	    100%	    VI
568	Noble Roar	            Normal  	Status	    Tough	    30	—	    100%	    VI
569	Ion Deluge	            Electric	Status	    Beautiful	25	—	    —	        VI
570	Parabolic Charge	    Electric	Special	    Clever	    20	65*	    100%	    VI
571	Forest's Curse	        Grass	    Status	    Clever	    20	—	    100%	    VI
572	Petal Blizzard	        Grass	    Physical	Beautiful	15	90	    100%	    VI
573	Freeze-Dry	            Ice	        Special	    Beautiful	20	70	    100%	    VI
574	Disarming Voice	        Fairy	    Special	    Cute	    15	40	    —	        VI
575	Parting Shot	        Dark	    Status	    Cool	    20	—	    100%	    VI
576	Topsy-Turvy	            Dark	    Status	    Clever	    20	—	    —*	        VI
577	Draining Kiss	        Fairy	    Special	    Cute	    10	50	    100%	    VI
578	Crafty Shield	        Fairy	    Status	    Clever	    10	—	    —	        VI
579	Flower Shield	        Fairy	    Status	    Beautiful	10	—	    —	        VI
580	Grassy Terrain	        Grass	    Status	    Beautiful	10	—	    —	        VI
581	Misty Terrain	        Fairy	    Status	    Beautiful	10	—	    —	        VI
582	Electrify	            Electric	Status	    Clever	    20	—	    —	        VI
583	Play Rough	            Fairy	    Physical	Cute	    10	90	    90%	        VI
584	Fairy Wind	            Fairy	    Special	    Beautiful	30	40	    100%	    VI
585	Moonblast	            Fairy	    Special	    Beautiful	15	95	    100%	    VI
586	Boomburst	            Normal  	Special	    Tough	    10	140	    100%	    VI
587	Fairy Lock	            Fairy   	Status	    Clever	    10	—	    —	        VI
588	King's Shield	        Steel	    Status	    Cool	    10	—	    —	        VI
589	Play Nice	            Normal  	Status	    Cute	    20	—	    —	        VI
590	Confide	                Normal	    Status	    Cute	    20	—	    —	        VI
591	Diamond Storm	        Rock	    Physical	Beautiful	5	100	    95%	        VI
592	Steam Eruption	        Water	    Special	    Beautiful	5	110	    95%	        VI
593	Hyperspace Hole	        Psychic 	Special	    Clever	    5	80	    —	        VI
594	Water Shuriken*	        Water	    Special	    Cool	    20	15	    100%	    VI
595	Mystical Fire	        Fire	    Special	    Beautiful	10	75*	    100%	    VI
596	Spiky Shield	        Grass	    Status	    Tough	    10	—	    —	        VI
597	Aromatic Mist	        Fairy	    Status	    Beautiful	20	—	    —	        VI
598	Eerie Impulse	        Electric	Status	    Clever	    15	—	    100%	    VI
599	Venom Drench	        Poison	    Status	    Clever	    20	—	    100%	    VI
600	Powder	                Bug	        Status	    Clever	    20	—	    100%	    VI
601	Geomancy	            Fairy	    Status	    Beautiful	10	—	    —	        VI
602	Magnetic Flux	        Electric	Status	    Clever	    20	—	    —	        VI
603	Happy Hour	            Normal	    Status	    Cute	    30	—	    —	        VI
604	Electric Terrain	    Electric	Status	    Clever	    10	—	    —	        VI
605	Dazzling Gleam  	    Fairy	    Special	    Beautiful	10	80	    100%	    VI
606	Celebrate       	    Normal	    Status	    Cute	    40	—	    —	        VI
607	Hold Hands	            Normal	    Status	    Cute	    40	—	    —	        VI
608	Baby-Doll Eyes	        Fairy	    Status	    Cute	    30	—	    100%	    VI
609	Nuzzle	                Electric	Physical	Cute	    20	20	    100%	    VI
610	Hold Back	            Normal	    Physical	Cool	    40	40	    100%	    VI
611	Infestation     	    Bug	        Special	    Cute	    20	20	    100%	    VI
612	Power-Up Punch  	    Fighting	Physical	Tough	    20	40	    100%	    VI
613	Oblivion Wing	        Flying	    Special 	Cool	    10	80	    100%    	VI
614	Thousand Arrows 	    Ground	    Physical	Beautiful	10	90	    100%	    VI
615	Thousand Waves	        Ground	    Physical	Tough	    10	90	    100%	    VI
616	Land's Wrath	        Ground  	Physical	Beautiful	10	90	    100%	    VI
617	Light of Ruin	        Fairy	    Special 	Beautiful	5	140	    90%	        VI
618	Origin Pulse	        Water	    Special	    Beautiful	10	110	    85%	        VI*
619	Precipice Blades	    Ground  	Physical	Cool	    10	120	    85%	        VI*
620	Dragon Ascent	        Flying  	Physical	Beautiful	5	120	    100%	    VI*
621	Hyperspace Fury	        Dark	    Physical	Tough	    5	100	    —	        VI*
622	Breakneck Blitz 	    Normal  	Physical	???	        1	—	    —	        VII
623	Breakneck Blitz	        Normal	    Special 	???	        1	—	    —	        VII
624	All-Out Pummeling	    Fighting	Physical	???	        1	—	    —	        VII
625	All-Out Pummeling	    Fighting	Special	    ???	        1	—	    —	        VII
626	Supersonic Skystrike	Flying	    Physical	???	        1	—	    —	        VII
627	Supersonic Skystrike	Flying	    Special 	???	        1	—	    —	        VII
628	Acid Downpour	        Poison	    Physical	???	        1	—	    —	        VII
629	Acid Downpour	        Poison	    Special 	???	        1	—	    —	        VII
630	Tectonic Rage	        Ground	    Physical	???	        1	—	    —	        VII
631	Tectonic Rage	        Ground  	Special	    ???	        1	—	    —	        VII
632	Continental Crush	    Rock	    Physical	???	        1	—	    —	        VII
633	Continental Crush	    Rock	    Special 	???	        1	—	    —	        VII
634	Savage Spin-Out     	Bug	        Physical	???	        1	—	    —	        VII
635	Savage Spin-Out	        Bug	        Special	    ???	        1	—	    —	        VII
636	Never-Ending Nightmare	Ghost	    Physical	???	        1	—	    —	        VII
637	Never-Ending Nightmare	Ghost	    Special	    ???	        1	—	    —	        VII
638	Corkscrew Crash     	Steel	    Physical	???	        1	—	    —	        VII
639	Corkscrew Crash     	Steel   	Special 	???	        1	—	    —	        VII
640	Inferno Overdrive	    Fire	    Physical	???     	1	—	    —	        VII
641	Inferno Overdrive	    Fire	    Special	    ???	        1	—	    —	        VII
642	Hydro Vortex	        Water   	Physical	???	        1	—	    —	        VII
643	Hydro Vortex	        Water   	Special	    ???     	1	—   	—	        VII
644	Bloom Doom	            Grass	    Physical	???     	1	—	    —	        VII
645	Bloom Doom	            Grass	    Special	    ???	        1	—	    —	        VII
646	Gigavolt Havoc	        Electric	Physical	???	        1	—	    —	        VII
647	Gigavolt Havoc	        Electric	Special	    ???	        1	—	    —	        VII
648	Shattered Psyche	    Psychic	    Physical	???	        1	—	    —	        VII
649	Shattered Psyche	    Psychic	    Special	    ???	        1	—	    —	        VII
650	Subzero Slammer     	Ice	        Physical	???	        1	—	    —	        VII
651	Subzero Slammer	        Ice	        Special	    ???	        1	—	    —	        VII
652	Devastating Drake	    Dragon  	Physical	???     	1	—	    —	        VII
653	Devastating Drake	    Dragon	    Special	    ???	        1	—	    —	        VII
654	Black Hole Eclipse	    Dark	    Physical	???	        1	—	    —       	VII
655	Black Hole Eclipse	    Dark	    Special	    ???     	1	—	    —       	VII
656	Twinkle Tackle      	Fairy   	Physical	???	        1	—	    —       	VII
657	Twinkle Tackle	        Fairy   	Special	    ???	        1	—	    —	        VII
658	Catastropika        	Electric	Physical	???	        1	210	    —	        VII
659	Shore Up	            Ground  	Status	    ???	        10	—	    —	        VII
660	First Impression	    Bug     	Physical	???	        10	90	    100%    	VII
661	Baneful Bunker	        Poison	    Status	    ???	        10	—	    —	        VII
662	Spirit Shackle	        Ghost	    Physical	???	        10	80	    100%    	VII
663	Darkest Lariat	        Dark	    Physical	???	        10	85	    100%    	VII
664	Sparkling Aria	        Water	    Special	    ???	        10	90	    100%	    VII
665	Ice Hammer	            Ice	        Physical	???	        10	100	    90%	        VII
666	Floral Healing      	Fairy	    Status	    ???	        10	—	    —	        VII
667	High Horsepower	        Ground	    Physical	???	        10	95	    95%	        VII
668	Strength Sap	        Grass	    Status	    ???	        10	—	    100%    	VII
669	Solar Blade	            Grass	    Physical	???	        10	125	    100%      	VII
670	Leafage	                Grass	    Physical	???	        40	40	    100%	    VII
671	Spotlight	            Normal	    Status  	???	        15	—	    —	        VII
672	Toxic Thread	        Poison	    Status  	???	        20	—	    100%	    VII
673	Laser Focus         	Normal	    Status  	???	        30	—	    —	        VII
674	Gear Up             	Steel	    Status  	???	        20	—	    —	        VII
675	Throat Chop         	Dark	    Physical	???     	15	80	    100%	    VII
676	Pollen Puff	            Bug	        Special	    ???	        15	90	    100%	    VII
677	Anchor Shot	            Steel	    Physical	???	        20	80	    100%	    VII
678	Psychic Terrain     	Psychic	    Status	    ???     	10	—	    —	        VII
679	Lunge	                Bug	        Physical	???     	15	80	    100%	    VII
680	Fire Lash	            Fire	    Physical	???	        15	80	    100%	    VII
681	Power Trip	            Dark	    Physical	???	        10	20	    100%	    VII
682	Burn Up	                Fire	    Special 	???     	5	130	    100%	    VII
683	Speed Swap	            Psychic 	Status	    ???     	10	—	    —	        VII
684	Smart Strike	        Steel	    Physical	???	        10	70	    —	        VII
685	Purify	                Poison	    Status	    ???	        20	—	    —	        VII
686	Revelation Dance	    Normal	    Special	    ???     	15	90	    100%	    VII
687	Core Enforcer	        Dragon	    Special	    ???	        10	100	    100%	    VII
688	Trop Kick	            Grass	    Physical	???     	15	70	    100%	    VII
689	Instruct	            Psychic	    Status	    ???     	15	—	    —	        VII
690	Beak Blast	            Flying	    Physical	???	        15	100	    100%	    VII
691	Clanging Scales         Dragon	    Special 	???	        5	110	    100%	    VII
692	Dragon Hammer	        Dragon	    Physical	???     	15	90	    100%	    VII
693	Brutal Swing	        Dark	    Physical	???     	20	60	    100%	    VII
694	Aurora Veil         	Ice	        Status	    ???     	20	—	    —	        VII
695	Sinister Arrow Raid 	Ghost	    Physical	???	        1	180	    —	        VII
696	Malicious Moonsault	    Dark	    Physical	???	        1	180	    —	        VII
697	Oceanic Operetta	    Water	    Special	    ???	        1	195	    —	        VII
698	Guardian of Alola	    Fairy	    Special	    ???	        1	—	    —	        VII
699	Soul-Stealing 7-Star Strike
                            Ghost	Physical	    ???	        1	195 	—	        VII
700	Stoked Sparksurfer	    Electric	Special	    ???     	1	175 	—	        VII
701	Pulverizing Pancake	    Normal	    Physical	???	        1	210 	—	        VII
702	Extreme Evoboost	    Normal	    Status	    ???	        1	—	    —	        VII
703	Genesis Supernova	    Psychic	    Special	    ???	        1	185 	—	        VII
704	Shell Trap	            Fire	    Special	    ???     	5	150 	100%	    VII
705	Fleur Cannon	        Fairy	    Special	    ???     	5	130	    90%	        VII
706	Psychic Fangs	        Psychic	    Physical	???	        10	85	    100%	    VII
707	Stomping Tantrum	    Ground	    Physical	???	        10	75	    100%	    VII
708	Shadow Bone     	    Ghost	    Physical	???	        10	85	    100%	    VII
709	Accelerock	            Rock	    Physical	???	        20	40	    100%	    VII
710	Liquidation	            Water	    Physical	???	        10	85	    100%	    VII
711	Prismatic Laser	        Psychic	    Special	    ???	        10	160 	100%	    VII
712	Spectral Thief	        Ghost	    Physical	???	        10	90	    100%	    VII
713	Sunsteel Strike	        Steel	    Physical	???     	5	100 	100%	    VII
714	Moongeist Beam	        Ghost	    Special	    ???     	5	100 	100%	    VII
715	Tearful Look	        Normal	    Status	    ???     	20	—	    —       	VII
716	Zing Zap	            Electric	Physical	???     	10	80  	100%	    VII
717	Nature's Madness    	Fairy	    Special	    ???     	10	—	    90%	        VII
718	Multi-Attack	        Normal	    Physical	???	        10	90	    100%	    VII
719	10,000,000 Volt Thunderbolt
                            Electric	Special	    ???	        1	195	    —	        VII
720	Mind Blown	            Fire	    Special	    ???     	5	150	    100%	    VII*

721	Plasma Fists	        Electric	Physical	???	        15	100 	100%	    VII*
722	Photon Geyser	        Psychic	    Special     ???     	5	100 	100%	    VII*
723	Light That Burns the Sky
                            Psychic	    Special	    ???	        1	200	    —	        VII*
724	Searing Sunraze Smash	Steel	    Special	    ???     	1	200	    —	        VII*
725	Menacing Moonraze Maelstrom
                            Ghost	    Special	    ???     	1	200	    —	        VII*
726	Let's Snuggle Forever	Fairy	    Physical	???	        1	190	    —	        VII*
727	Splintered Stormshards	Rock	    Physical	???	        1	190	    —	        VII*
728	Clangorous Soulblaze	Dragon	    Special	    ???	        1	185	    —	        VII*
729	Zippy Zap	            Electric	Physical	???	        15	50	    100%	    VII*

730	Splishy Splash	        Water	    Special	    ???	        15	90	    100%	    VII*
731	Floaty Fall	            Flying	    Physical	???	        15	90	    95%	        VII*
732	Pika Papow          	Electric	Special	    ???	        20	—	    —	        VII*
733	Bouncy Bubble       	Water	    Special 	???	        15	90	    100%	    VII*
734	Buzzy Buzz	            Electric	Special	    ???	        15	90	    100%	    VII*
735	Sizzly Slide	        Fire	    Physical	???	        15	90	    100%	    VII*
736	Glitzy Glow	            Psychic	    Special	    ???	        15	90	    100%	    VII*
737	Baddy Bad	            Dark	    Special	    ???	        15	90	    100%	    VII*
738	Sappy Seed	            Grass	    Physical	???	        15	90	    100%	    VII*
739	Freezy Frost	        Ice	        Special	    ???	        15	90	    100%	    VII*
740	Sparkly Swirl	        Fairy	    Special	    ???	        15	90	    100%	    VII*
741	Veevee Volley	        Normal	    Physical	???	        20	—	    —	        VII*
742	Double Iron Bash	    Steel	    Physical	???	        5	60	    100%	    VII*
     */
}
