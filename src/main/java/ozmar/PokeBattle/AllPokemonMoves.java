package ozmar.PokeBattle;

public class AllPokemonMoves {
    /*
NOTE: PokeAPI only has moves 1-728, (No Pokemon Let's Go Moves)
Moves 622-658, 695-703, 719, 723-728 are Z-Moves

BYPASS_ACC_CHECK some moves have special conditions before it can bypass
NOTE: Some moves without tags are still unique(Foresight)

Strength Sap unique

#	Name	                Type	    Category    Contest     PP	Power	Accuracy	Gen
1	Pound	                Normal      Physical	Tough	    35	40	    100%	    I
2	Karate Chop*	        Fighting    Physical	Tough	    25	50	    100%	    I       HIGH_CRIT_HIT
3	Double Slap	            Normal	    Physical	Cute	    10	15	    85%	        I       MULTI_STRIKE
4	Comet Punch	            Normal	    Physical	Tough	    15	18	    85%	        I       PUNCHING        MULTI_STRIKE
5	Mega Punch	            Normal	    Physical	Tough	    20	80	    85%	        I       PUNCHING
6	Pay Day	                Normal	    Physical    Clever	    20	40	    100%	    I
7	Fire Punch	            Fire	    Physical    Tough	    15	75	    100%	    I       PUNCHING        CAN_BRN
8	Ice Punch	            Ice	        Physical	Beautiful	15	75	    100%	    I       PUNCHING        CAN_FRZ
9	Thunder Punch	        Electric	Physical	Cool	    15	75	    100%	    I       PUNCHING        CAN_PAR
10	Scratch	                Normal	    Physical	Tough	    35	40	    100%	    I
11	Vice Grip	            Normal	    Physical	Tough	    30	55	    100%	    I
12	Guillotine	            Normal	    Physical	Cool	    5	—	    —* *	    I       OHKO        DIRECT_DAMAGE
13	Razor Wind	            Normal	    Special	    Cool	    10	80	    100%*	    I       CHARGES     HIGH_CRIT_HIT
14	Swords Dance	        Normal	    Status	    Beautiful	20*	—	    —	        I       STAT_USERS_ATT++        DANCE
15	Cut	                    Normal	    Physical	Cool	    30	50	    95%	        I
16	Gust*	                Flying	    Special	    Clever	    35	40	    100%	    I       CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER
17	Wing Attack	            Flying	    Physical	Cool	    35	60*	    100%	    I
18	Whirlwind	            Normal	    Status	    Clever	    20	—	    —*	        I       PRIORITY--      CAN_HIT_SEMI_INVULNERABLE       BYPASS_ACC_CHECK        SWITCHES_TARGET_OUT
19	Fly	                    Flying      Physical	Clever	    15	90*	    95%	        I       HAS_SEMI_INVULNERABLE TURN      CHARGES
20	Bind	                Normal	    Physical	Tough	    20	15	    85%*	    I       BINDING		TRAPPING		TRAPPING
21	Slam	                Normal	    Physical	Tough	    20	80	    75%	        I
22	Vine Whip	            Grass	    Physical	Cool	    25*	45*	    100%	    I
23	Stomp	                Normal	    Physical	Tough	    20	65	    100%	    I       MINIMIZED_TARGET_DMG++      CAN_FLINCH      BYPASS_ACC_CHECK        POWERS_UP       VARIABLE_POWER
24	Double Kick	            Fighting	Physical	Cool	    30	30	    100%	    I       MULTI_STRIKE
25	Mega Kick	            Normal	    Physical	Cool	    5	120 	75%	        I
26	Jump Kick	            Fighting	Physical	Cool	    10*	100*	95%	        I       HAVE_RECOIL
27	Rolling Kick	        Fighting	Physical	Cool	    15	60	    85%	        I       CAN_FLINCH
28	Sand Attack*	        Ground	    Status	    Cute	    15	—	    100%	    I       STAT_TARGET_ACC--
29	Headbutt	            Normal	    Physical	Tough	    15	70	    100%	    I       CAN_FLINCH
30	Horn Attack	            Normal	    Physical	Cool	    25	65	    100%	    I
31	Fury Attack	            Normal	    Physical	Cool	    20	15	    85%	        I       MULTI_STRIKE
32	Horn Drill	            Normal	    Physical	Cool	    5	—	    —* *	    I       OHKO        DIRECT_DAMAGE
33	Tackle	                Normal	    Physical	Tough	    35	40*	    100%*	    I
34	Body Slam	            Normal	    Physical	Tough	    15	85	    100%	    I       MINIMIZED_TARGET_DMG++      CAN_PAR     BYPASS_ACC_CHECK
35	Wrap	                Normal	    Physical	Tough	    20	15	    90%*	    I       BINDING		TRAPPING
36	Take Down	            Normal	    Physical	Tough	    20	90	    85%	        I       HAVE_RECOIL
37	Thrash	                Normal	    Physical	Tough	    10*	120*	100%	    I       CONSECUTIVE
38	Double-Edge	            Normal	    Physical	Tough	    15	120*	100%	    I       HAVE_RECOIL
39	Tail Whip	            Normal	    Status	    Cute	    30	—	    100%	    I       STAT_TARGET_DEF--
40	Poison Sting	        Poison	    Physical	Clever	    35	15	    100%	    I       CAN_PSN
41	Twineedle	            Bug	        Physical	Cool	    20	25	    100%	    I       CAN_PSN     MULTI_STRIKE
42	Pin Missile	            Bug	        Physical	Cool	    20	25*	    95%*	    I       MULTI_STRIKE
43	Leer	                Normal	    Status	    Cool	    30	—	    100%	    I       STAT_TARGET_DEF--
44	Bite*	                Dark	    Physical	Tough	    25	60	    100%	    I       BITING      CAN_FLINCH
45	Growl	                Normal	    Status	    Cute	    40	—	    100%	    I       STAT_TARGET_ATT--       SOUND_BASED
46	Roar	                Normal	    Status	    Cool	    20	—	    —*	        I       PRIORITY--      SOUND_BASED     BYPASS_ACC_CHECK        SWITCHES_TARGET_OUT
47	Sing	                Normal	    Status	    Cute	    15	—	    55%	        I       SOUND_BASED     CAN_SLP
48	Supersonic	            Normal	    Status	    Clever	    20	—	    55%	        I       SOUND_BASED     CAN_CONFUSE
49	Sonic Boom	            Normal	    Special	    Cool	    20	*	    90%	        I       SET_DAMAGE      DIRECT_DAMAGE
50	Disable	                Normal	    Status	    Clever	    20	—	    100%*	    I
51	Acid	                Poison	    Special	    Clever	    30	40	    100%	    I       STAT_TARGET_DEF--       STAT_TARGET_SPDEF--
52	Ember	                Fire	    Special	    Cute	    25	40	    100%	    I       CAN_BRN
53	Flamethrower	        Fire	    Special	    Beautiful	15	90*	    100%	    I       CAN_BRN
54	Mist	                Ice	        Status	    Beautiful	30	—	    —	        I
55	Water Gun	            Water	    Special	    Cute	    25	40	    100%	    I
56	Hydro Pump	            Water	    Special	    Beautiful	5	110*	80%	        I
57	Surf	                Water	    Special	    Beautiful	15	90*	    100%	    I       CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER
58	Ice Beam	            Ice	        Special	    Beautiful	10	90*     100%	    I       CAN_FRZ
59	Blizzard	            Ice 	    Special	    Beautiful	5	110*	70%*	    I       CAN_FRZ     BYPASS_ACC_CHECK
60	Psybeam	                Psychic	    Special	    Beautiful	20	65	    100%	    I       CAN_CONFUSE
61	Bubble Beam	            Water	    Special	    Beautiful	20	65	    100%	    I       STAT_TARGET_SPD--
62	Aurora Beam	            Ice	        Special	    Beautiful	20	65	    100%	    I       STAT_TARGET_ATT--
63	Hyper Beam	            Normal	    Special	    Cool	    5	150	    90% 	    I       RECHARGES
64	Peck	                Flying	    Physical	Cool	    35	35	    100%	    I
65	Drill Peck	            Flying	    Physical	Cool	    20	80	    100%	    I
66	Submission	            Fighting	Physical	Cool	    20*	80	    80%	        I       HAVE_RECOIL
67	Low Kick	            Fighting	Physical	Tough	    20	—*	    100%*	    I       AFFECTED_BY_WEIGHT      CAN_FLINCH      VARIABLE_POWER
68	Counter	                Fighting	Physical	Tough	    20	—	    100%	    I       PRIORITY--      DIRECT_DAMAGE
69	Seismic Toss	        Fighting	Physical	Tough	    20	—	    100%	    I       DIRECT_DAMAGE
70	Strength	            Normal	    Physical	Tough	    15	80	    100%	    I
71	Absorb	                Grass	    Special	    Clever	    15*	40*	    100%	    I       HP_DRAINING_DAMAGE		RESTORES_HP
72	Mega Drain	            Grass	    Special 	Clever	    10*	75*	    100%	    I       HP_DRAINING_DAMAGE		RESTORES_HP
73	Leech Seed	            Grass	    Status	    Clever	    10	—	    90%	        I       HP_DRAINING_DAMAGE		RESTORES_HP
74	Growth	                Normal	    Status	    Beautiful	20*	—	    —	        I       STAT_USERS_ATT++        STAT_USERS_SPATT++
75	Razor Leaf	            Grass	    Physical	Cool	    25	55	    95%	        I       HIGH_CRIT_HIT
76	Solar Beam	            Grass	    Special	    Cool	    10	200*	100%	    I       CHARGES
77	Poison Powder	        Poison	    Status	    Clever	    35	—	    75%	        I       POWDER_AND_SPORE        CAN_PSN
78	Stun Spore	            Grass	    Status	    Clever	    30	—	    75%	        I       POWDER_AND_SPORE        CAN_PAR
79	Sleep Powder	        Grass	    Status	    Clever	    15	—	    75%     	I       POWDER_AND_SPORE        CAN_SLP
80	Petal Dance	            Grass	    Special 	Beautiful	10*	120*	100%	    I       CONSECUTIVE     DANCE
81	String Shot	            Bug	        Status	    Clever	    40	—	    95%	        I       STAT_TARGET_SPD--
82	Dragon Rage	            Dragon	    Special 	Cool	    10	*	    100%	    I       SET_DAMAGE      DIRECT_DAMAGE
83	Fire Spin	            Fire	    Special 	Beautiful	15	35* 	85%*	    I       BINDING		TRAPPING
84	Thunder Shock	        Electric	Special 	Cool	    30	40	    100%	    I       CAN_PAR
85	Thunderbolt     	    Electric	Special	    Cool	    15	90*	    100%	    I       CAN_PAR
86	Thunder Wave	        Electric	Status	    Cool	    20	—	    90%*	    I       CAN_PAR
87	Thunder         	    Electric	Special	    Cool	    10	110*	70%	        I       CAN_HIT_SEMI_INVULNERABLE       CAN_PAR     BYPASS_ACC_CHECK
88	Rock Throw	            Rock	    Physical	Tough	    15	50	    90%*	    I
89	Earthquake	            Ground	    Physical	Tough	    10	100 	100%	    I       CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER
90	Fissure         	    Ground	    Physical	Tough   	5	—	    —* *	    I       CAN_HIT_SEMI_INVULNERABLE       OHKO        DIRECT_DAMAGE
91	Dig	                    Ground	    Physical	Tough	    10	80* 	100%	    I       HAS_SEMI_INVULNERABLE TURN      CHARGES
92	Toxic	                Poison	    Status	    Clever	    10	—	    90%*	    I       CAN_HIT_SEMI_INVULNERABLE       CAN_PSN     BYPASS_ACC_CHECK
93	Confusion	            Psychic 	Special 	Clever	    25	50	    100%	    I       CAN_CONFUSE
94	Psychic	                Psychic	    Special 	Clever	    10	90	    100%	    I       STAT_TARGET_SPDEF--
95	Hypnosis	            Psychic 	Status	    Clever	    20	—	    60%*	    I       CAN_SLP
96	Meditate	            Psychic	    Status	    Beautiful	40	—	    —	        I       STAT_USERS_ATT++
97	Agility	                Psychic	    Status  	Cool	    30	—	    —	        I       STAT_USERS_SPD++
98	Quick Attack    	    Normal  	Physical	Cool    	30	40	    100%	    I       PRIORITY++
99	Rage	                Normal	    Physical	Tough   	20	20	    100%	    I       STAT_USERS_ATT++
100	Teleport	            Psychic	    Status  	Cool	    20	—	    —	        I       PRIORITY--      SWITCHES_USER_OUT
101	Night Shade     	    Ghost	    Special 	Clever  	15	—	    100%	    I       DIRECT_DAMAGE
102	Mimic	                Normal	    Status  	Cute	    10	—	    —*	        I       BYPASS_ACC_CHECK
103	Screech	                Normal  	Status  	Clever	    40	—	    85%	        I       STAT_TARGET_DEF--       SOUND_BASED
104	Double Team	            Normal	    Status  	Cool	    15	—	    —	        I       STAT_USERS_EVA++
105	Recover	                Normal	    Status  	Clever  	10*	—	    —	        I       HEALS_USER_IMMEDIATELY       RESTORES_HP
106	Harden	                Normal	    Status	    Tough   	30	—	    —	        I       STAT_USERS_DEF++
107	Minimize	            Normal  	Status	    Cute	    10*	—	    —	        I       STAT_USERS_EVA++
108	Smokescreen	            Normal	    Status	    Clever  	20	—	    100%	    I       STAT_TARGET_ACC--
109	Confuse Ray	            Ghost	    Status	    Clever      10	—	    100%	    I       CAN_CONFUSE
110	Withdraw	            Water	    Status	    Cute	    40	—	    —	        I       STAT_USERS_DEF++
111	Defense Curl    	    Normal	    Status	    Cute	    40	—	    —	        I       STAT_USERS_DEF++
112	Barrier         	    Psychic	    Status	    Cool	    20*	—	    —	        I       STAT_USERS_DEF++
113	Light Screen	        Psychic	    Status	    Beautiful	30	—	    —	        I
114	Haze	                Ice	        Status	    Beautiful	30	—	    —	        I
115	Reflect	                Psychic 	Status	    Clever  	20	—	    —	        I
116	Focus Energy    	    Normal	    Status	    Cool	    30	—	    —	        I
117	Bide	                Normal	    Physical	Tough	    10	—	    —*	        I       PRIORITY++      BYPASS_ACC_CHECK        DIRECT_DAMAGE
118	Metronome       	    Normal	    Status	    Cute	    10	—	    —	        I       CALLS_OTHER_MOVES
119	Mirror Move	            Flying	    Status	    Clever  	20	—	    —	        I       CALLS_OTHER_MOVES
120	Self-Destruct   	    Normal	    Physical	Beautiful	5	200*	100%	    I       EXPLOSIVE       CAUSES_USER_TO_FAINT
121	Egg Bomb	            Normal	    Physical	Cute	    10	100	    75%	        I       BALL_AND_BOMB
122	Lick	                Ghost	    Physical	Cute	    30	30*	    100%	    I       CAN_PAR
123	Smog	                Poison	    Special	    Tough	    20	30*	    70%	        I       CAN_PSN
124	Sludge	                Poison	    Special	    Tough	    20	65	    100%	    I       CAN_PSN
125	Bone Club	            Ground	    Physical	Tough	    20	65	    85%	        I       CAN_FLINCH
126	Fire Blast      	    Fire	    Special	    Beautiful	5	110*    85%	        I       CAN_BRN
127	Waterfall       	    Water	    Physical	Tough	    15	80	    100%	    I       CAN_FLINCH
128	Clamp	                Water	    Physical	Tough	    15*	35	    85%*	    I       BINDING		TRAPPING
129	Swift	                Normal	    Special 	Cool	    20	60	    —	        I       BYPASS_ACC_CHECK
130	Skull Bash	            Normal	    Physical	Tough	    10*	130*	100%	    I       STAT_USERS_DEF++        CHARGES
131	Spike Cannon	        Normal	    Physical	Cool	    15	20	    100%	    I       MULTI_STRIKE
132	Constrict       	    Normal	    Physical	Tough	    35	10	    100%	    I       STAT_TARGET_SPD--
133	Amnesia	                Psychic	    Status	    Cute	    20	—	    —	        I       STAT_USERS_SPDEF++
134	Kinesis	                Psychic	    Status	    Clever	    15	—	    80%	        I       STAT_TARGET_ACC--
135	Soft-Boiled	            Normal	    Status	    Cute	    10	—	    —	        I       HEALS_USER_IMMEDIATELY       RESTORES_HP
136	High Jump Kick	        Fighting	Physical	Cool	    10*	130*	90%	        I       HAVE_RECOIL
137	Glare	                Normal	    Status	    Tough   	30	—	    100%*	    I       CAN_PAR
138	Dream Eater	            Psychic	    Special	    Clever	    15	100	    100%	    I       HP_DRAINING_DAMAGE		RESTORES_HP
139	Poison Gas      	    Poison	    Status	    Clever	    40	—	    90%*	    I       CAN_PSN
140	Barrage	                Normal  	Physical	Cute	    20	15	    85%	        I       BALL_AND_BOMB       MULTI_STRIKE
141	Leech Life	            Bug	        Physical	Clever	    10*	80*	    100%	    I       HP_DRAINING_DAMAGE		RESTORES_HP
142	Lovely Kiss	            Normal	    Status	    Beautiful	10	—	    75%	        I       CAN_SLP
143	Sky Attack      	    Flying	    Physical	Cool	    5	200*	90%	        I       CAN_FLINCH      CHARGES     HIGH_CRIT_HIT
144	Transform       	    Normal	    Status	    Clever	    10	—	    —	        I       ABILITY_CHANGING        BYPASS_ACC_CHECK        CHANGES_POKE_TYPE
145	Bubble	                Water	    Special	    Cute	    30	40*	    100%	    I       STAT_TARGET_SPD--
146	Dizzy Punch     	    Normal	    Physical	Cute	    10	70	    100%	    I       PUNCHING        CAN_CONFUSE
147	Spore	                Grass	    Status	    Beautiful	15	—	    100%	    I       POWDER_AND_SPORE        CAN_SLP
148	Flash	                Normal	    Status	    Beautiful	20	—	    100%*	    I       STAT_TARGET_ACC--
149	Psywave	                Psychic	    Special	    Clever	    15	—	    100%*	    I       DIRECT_DAMAGE
150	Splash	                Normal	    Status	    Cute	    40	—	    —	        I       NO_EFFECT
151	Acid Armor	            Poison	    Status	    Tough	    20*	—	    —	        I       STAT_USERS_DEF++
152	Crabhammer	            Water	    Physical	Tough	    10	100*	90%*	    I       HIGH_CRIT_HIT
153	Explosion	            Normal	    Physical	Beautiful	5	250*	100%	    I       EXPLOSIVE       CAUSES_USER_TO_FAINT
154	Fury Swipes	            Normal	    Physical	Tough	    15	18	    80%	        I       MULTI_STRIKE
155	Bonemerang	            Ground	    Physical	Tough	    10	50	    90%	        I       MULTI_STRIKE
156	Rest	                Psychic	    Status	    Cute	    10	—	    —	        I       CAN_HEAL_NON_VOL_STAT       CAN_SLP     HEALS_USER_IMMEDIATELY       RESTORES_HP
157	Rock Slide	            Rock	    Physical	Tough	    10	75	    90%	        I       CAN_FLINCH
158	Hyper Fang	            Normal	    Physical	Cool	    15	80	    90%	        I       BITING      CAN_FLINCH
159	Sharpen         	    Normal	    Status	    Cute	    30	—	    —	        I       STAT_USERS_ATT++
160	Conversion      	    Normal	    Status	    Beautiful	30	—	    —	        I       CHANGES_POKE_TYPE
161	Tri Attack	            Normal	    Special	    Beautiful	10	80	    100%	    I       CAN_BRN     CAN_FRZ     CAN_PAR
162	Super Fang	            Normal	    Physical	Tough	    10	—	    90%	        I       DIRECT_DAMAGE
163	Slash	                Normal	    Physical	Cool	    20	70	    100%	    I       HIGH_CRIT_HIT
164	Substitute	            Normal	    Status	    Cute	    10	—	    —	        I       COST_HP
165	Struggle	            Normal	    Physical	Tough	    1*	50	    —*	        I       BYPASS_ACC_CHECK        HAVE_RECOIL
166	Sketch	                Normal	    Status	    Clever	    1	—	    —	        II      BYPASS_ACC_CHECK
167	Triple Kick	            Fighting	Physical	Cool	    10	10	    90%	        II      POWERS_UP       VARIABLE_POWER      MULTI_STRIKE
168	Thief	                Dark	    Physical	Tough	    25*	60*	    100%	    II
169	Spider Web	            Bug	        Status	    Clever	    10	—	    —	        II      BYPASS_ACC_CHECK        TRAPPING
170	Mind Reader     	    Normal	    Status	    Clever	    5	—	    —*	        II      BYPASS_ACC_CHECK
171	Nightmare	            Ghost	    Status	    Clever	    15	—	    100%	    II      BYPASS_ACC_CHECK
172	Flame Wheel	            Fire	    Physical	Beautiful	25	60	    100%	    II      CAN_THAW_USER       CAN_BRN
173	Snore	                Normal	    Special	    Cute	    15	50*	    100%	    II      SOUND_BASED     CAN_FLINCH
174	Curse*	                Ghost	    Status	    Tough	    10	—	    —	        II      STAT_USERS_SPD--        STAT_USERS_ATT++        STAT_USERS_DEF++        COST_HP
175	Flail	                Normal	    Physical	Cute	    15	—	    100%	    II      VARIABLE_POWER
176	Conversion 2	        Normal	    Status	    Beautiful	30	—	    —	        II      BYPASS_ACC_CHECK        CHANGES_POKE_TYPE
177	Aeroblast	            Flying	    Special	    Cool	    5	100	    95%	        II      HIGH_CRIT_HIT
178	Cotton Spore            Grass	    Status	    Beautiful	40	—	    100%*	    II      STAT_TARGET_SPD--       POWDER_AND_SPORE
179	Reversal	            Fighting	Physical	Cool	    15	—	    100%	    II      VARIABLE_POWER
180	Spite	                Ghost	    Status	    Tough	    10	—	    100%	    II
181	Powder Snow	            Ice	        Special	    Beautiful	25	40	    100%	    II      CAN_FRZ
182	Protect	                Normal	    Status	    Cute	    10	—	    —	        II      PRIORITY++      PROTECTION
183	Mach Punch	            Fighting	Physical	Cool	    30	40	    100%	    II      PRIORITY++      PUNCHING
184	Scary Face	            Normal	    Status	    Tough	    10	—	    100%*	    II      STAT_TARGET_SPD--
185	Feint Attack	        Dark	    Physical	Clever	    20	60	    —	        II      BYPASS_ACC_CHECK
186	Sweet Kiss*	            Fairy	    Status	    Cute	    10	—	    75%	        II      CAN_CONFUSE
187	Belly Drum	            Normal	    Status	    Cute	    10	—	    —	        II      STAT_USERS_ATT++        COST_HP
188	Sludge Bomb	            Poison	    Special	    Tough	    10	90	    100%	    II      BALL_AND_BOMB       CAN_PSN
189	Mud-Slap	            Ground	    Special	    Cute	    10	20	    100%	    II      STAT_TARGET_ACC--
190	Octazooka	            Water	    Special	    Tough	    10	65	    85%	        II      STAT_TARGET_ACC--       BALL_AND_BOMB
191	Spikes	                Ground	    Status	    Clever	    20	—	    —	        II      ENTRY_HAZARD
192	Zap Cannon	            Electric	Special     Cool	    5	120*	50%	        II      BALL_AND_BOMB       CAN_PAR
193	Foresight	            Normal	    Status	    Clever	    40	—	    —*	        II      BYPASS_ACC_CHECK        REMOVES_SOME_TYPE_IMMUNITIES
194	Destiny Bond	        Ghost	    Status	    Clever	    5	—	    —	        II
195	Perish Song     	    Normal	    Status	    Beautiful	5	—	    —	        II      SOUND_BASED     CAUSES_USER_TO_FAINT
196	Icy Wind	            Ice	        Special 	Beautiful	15	55	    95%	        II      STAT_TARGET_SPD--
197	Detect	                Fighting	Status	    Cool	    5	—	    —	        II      PRIORITY++      PROTECTION
198	Bone Rush	            Ground	    Physical	Tough	    10	25	    90%*	    II      MULTI_STRIKE
199	Lock-On	                Normal	    Status	    Clever	    5	—	    —*	        II      BYPASS_ACC_CHECK
200	Outrage	                Dragon	    Physical	Cool	    10*	120*	100%	    II      CONSECUTIVE
201	Sandstorm	            Rock	    Status	    Tough	    10	—	    —	        II      WEATHER_CHANGING
202	Giga Drain	            Grass	    Special	    Clever	    10*	75*	    100%    	II      HP_DRAINING_DAMAGE		RESTORES_HP
203	Endure	                Normal	    Status	    Tough	    10	—	    —	        II      PRIORITY++
204	Charm*	                Fairy	    Status	    Cute	    20	—	    100%    	II      STAT_TARGET_ATT--
205	Rollout	                Rock	    Physical	Cute	    20	30	    90%	        II      CONSECUTIVE     POWERS_UP       VARIABLE_POWER
206	False Swipe	            Normal	    Physical	Cool	    40	40	    100%    	II
207	Swagger	                Normal	    Status	    Cute	    15	—	    85%*    	II      STAT_TARGETS_ATT++      CAN_CONFUSE
208	Milk Drink	            Normal	    Status	    Cute	    10	—	    —	        II      HEALS_USER_IMMEDIATELY       RESTORES_HP
209	Spark	                Electric	Physical	Cool	    20	65	    100%    	II      CAN_PAR
210	Fury Cutter	            Bug 	    Physical	Cool	    20	40*	    95%	        II      POWERS_UP       VARIABLE_POWER
211	Steel Wing	            Steel	    Physical	Cool	    25	70	    90%	        II      STAT_USERS_DEF++
212	Mean Look	            Normal	    Status	    Beautiful	5	—	    —	        II      BYPASS_ACC_CHECK        TRAPPING
213	Attract	                Normal	    Status	    Cute	    15	—	    100%    	II
214	Sleep Talk	            Normal  	Status	    Cute	    10	—	    —	        II      CALLS_OTHER_MOVES
215	Heal Bell	            Normal  	Status	    Beautiful	5	—	    —	        II      SOUND_BASED     CAN_HEAL_NON_VOL_STAT
216	Return	                Normal	    Physical	Cute	    20	—	    100%	    II      VARIABLE_POWER
217	Present         	    Normal	    Physical	Cute	    15	—	    90%	        II      VARIABLE_POWER      RESTORES_HP
218	Frustration	            Normal	    Physical	Cute	    20	—	    100%    	II      VARIABLE_POWER
219	Safeguard	            Normal	    Status	    Beautiful	25	—	    —	        II
220	Pain Split	            Normal	    Status	    Clever	    20	—	    —*      	II      BYPASS_ACC_CHECK        RESTORES_HP
221	Sacred Fire     	    Fire	    Physical	Beautiful	5	100	    95%     	II      CAN_THAW_USER       CAN_BRN
222	Magnitude	            Ground      Physical	Tough	    30	—	    100%    	II      CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER      VARIABLE_POWER
223	Dynamic Punch	        Fighting	Physical	Cool	    5	100	    50%	        II      PUNCHING        CAN_CONFUSE
224	Megahorn	            Bug	        Physical	Cool	    10	120	    85%	        II
225	Dragon Breath	        Dragon	    Special	    Cool	    20	60	    100%	    II      CAN_PAR
226	Baton Pass	            Normal	    Status	    Cute	    40	—	    —       	II      SWITCHES_USER_OUT
227	Encore	                Normal	    Status	    Cute	    5	—	    100%    	II
228	Pursuit	                Dark	    Physical	Clever	    20	40	    100%    	II      BYPASS_ACC_CHECK        POWERS_UP       VARIABLE_POWER
229	Rapid Spin	            Normal	    Physical	Cool	    40	20	    100%    	II
230	Sweet Scent     	    Normal	    Status	    Cute	    20	—	    100%    	II      STAT_TARGET_EVA--
231	Iron Tail	            Steel	    Physical	Cool	    15	100	    75%	        II      STAT_TARGET_DEF--
232	Metal Claw	            Steel	    Physical	Cool	    35	50	    95%     	II      STAT_USERS_ATT++
233	Vital Throw     	    Fighting	Physical	Cool	    10	70	    —       	II      PRIORITY--      BYPASS_ACC_CHECK
234	Morning Sun	            Normal	    Status	    Beautiful	5	—	    —       	II      HEALS_USER_IMMEDIATELY       RESTORES_HP
235	Synthesis	            Grass	    Status  	Clever  	5	—	    —       	II      HEALS_USER_IMMEDIATELY       RESTORES_HP
236	Moonlight*	            Fairy	    Status	    Beautiful	5	—	    —       	II      HEALS_USER_IMMEDIATELY       RESTORES_HP
237	Hidden Power	        Normal	    Special	    Clever	    15	60* 	100%    	II      CAN_CHANGE_TYPE     VARIABLE_POWER
238	Cross Chop	            Fighting	Physical	Cool	    5	100 	80%	        II      HIGH_CRIT_HIT
239	Twister	                Dragon	    Special	    Cool	    20	40	    100%    	II      CAN_FLINCH      CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER
240	Rain Dance      	    Water	    Status	    Beautiful	5	—	    —	        II      WEATHER_CHANGING
241	Sunny Day	            Fire	    Status	    Beautiful	5	—	    —	        II      WEATHER_CHANGING
242	Crunch	                Dark	    Physical	Tough	    15	80	    100%    	II      STAT_TARGET_DEF--       STAT_TARGET_SPDEF--     BITING
243	Mirror Coat	            Psychic	    Special 	Beautiful	20	—	    100%    	II      PRIORITY--      DIRECT_DAMAGE
244	Psych Up	            Normal	    Status	    Clever	    10	—	    —	        II      BYPASS_ACC_CHECK
245	Extreme Speed	        Normal	    Physical	Cool	    5	80	    100%	    II      PRIORITY++
246	Ancient Power   	    Rock	    Special 	Tough	    5	60	    100%    	II      STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++
247	Shadow Ball	            Ghost	    Special	    Clever	    15	80	    100%	    II      STAT_TARGET_SPDEF--     BALL_AND_BOMB
248	Future Sight	        Psychic	    Special	    Clever	    10*	120*	100%*   	II
249	Rock Smash	            Fighting	Physical	Tough	    15	40*	    100%	    II      STAT_TARGET_DEF--
250	Whirlpool	            Water	    Special 	Beautiful	15	35*	    85%*	    II      BINDING		TRAPPING     CAN_HIT_SEMI_INVULNERABLE       POWERS_UP       VARIABLE_POWER
251	Beat Up	                Dark	    Physical	Clever  	10	—*	    100%    	II      VARIABLE_POWER      MULTI_STRIKE
252	Fake Out	            Normal	    Physical	Cute	    10	40	    100%    	III     PRIORITY++      CAN_FLINCH
253	Uproar	                Normal	    Special	    Cute	    10	90*	    100%    	III     CONSECUTIVE     SOUND_BASED     CAN_HEAL_NON_VOL_STAT
254	Stockpile	            Normal	    Status	    Tough	    20*	—	    —	        III     STAT_USERS_DEF++        STAT_USERS_SPDEF++
255	Spit Up	                Normal	    Special 	Tough	    10	—	    100%    	III     VARIABLE_POWER
256	Swallow	                Normal	    Status	    Tough	    10	—	    —	        III
257	Heat Wave	            Fire	    Special 	Beautiful	10	95* 	90%	        III     CAN_BRN
258	Hail	                Ice	        Status	    Beautiful	10	—	    —	        III     WEATHER_CHANGING
259	Torment	                Dark	    Status	    Tough	    15	—	    100%	    III
260	Flatter	                Dark	    Status	    Clever	    15	—   	100%	    III     STAT_TARGETS_SPATK++        CAN_CONFUSE
261	Will-O-Wisp	            Fire	    Status	    Beautiful	15	—	    85%*	    III     CAN_BRN
262	Memento	                Dark	    Status	    Tough	    10	—	    100%	    III     STAT_TARGET_ATT--       STAT_TARGET_SPATT--     CAUSES_USER_TO_FAINT
263	Facade	                Normal	    Physical	Cute	    20	70      100%	    III     POWERS_UP       VARIABLE_POWER
264	Focus Punch	            Fighting	Physical	Tough	    20	150 	100%	    III     PRIORITY--      PUNCHING
265	Smelling Salts	        Normal	    Physical	Tough	    10	70* 	100%	    III     CAN_HEAL_NON_VOL_STAT       POWERS_UP       VARIABLE_POWER
266	Follow Me	            Normal	    Status	    Cute	    20	—	    —	        III     PRIORITY++      MAKES_TARGET_CENTER_OF_ATTENTION
267	Nature Power	        Normal	    Status	    Beautiful	20	—	    —	        III     CALLS_OTHER_MOVES       VARIES_WITH_ENVIRONMENT
268	Charge	                Electric	Status  	Clever	    20	—	    —	        III     STAT_USERS_SPDEF++
269	Taunt	                Dark	    Status	    Clever	    20	—	    100%	    III
270	Helping Hand	        Normal	    Status      Clever	    20	—	    —	        III     PRIORITY++      CAN_HIT_SEMI_INVULNERABLE
271	Trick	                Psychic	    Status	    Clever	    10	—	    100%	    III
272	Role Play	            Psychic	    Status  	Cute	    10	—	    —	        III     ABILITY_CHANGING        BYPASS_ACC_CHECK
273	Wish	                Normal	    Status  	Cute	    10	—	    —	        III     RESTORES_HP
274	Assist	                Normal	    Status	    Cute	    20	—	    —	        III     CALLS_OTHER_MOVES
275	Ingrain	                Grass	    Status	    Clever	    20	—	    —	        III     RESTORES_HP     TRAPPING
276	Superpower	            Fighting	Physical	Tough	    5	120 	100%	    III     STAT_USERS_ATT--
277	Magic Coat	            Psychic	    Status	    Beautiful	15	—	    —	        III     PRIORITY++
278	Recycle	                Normal	    Status	    Clever	    10	—	    —	        III
279	Revenge	                Fighting	Physical	Tough	    10	60	    100%	    III     PRIORITY--      POWERS_UP       VARIABLE_POWER
280	Brick Break     	    Fighting	Physical	Cool	    15	75	    100%    	III
281	Yawn	                Normal	    Status  	Cute	    10	—	    —       	III     CAN_SLP     BYPASS_ACC_CHECK
282	Knock Off	            Dark	    Physical	Clever	    20	65*	    100%	    III
283	Endeavor	            Normal	    Physical	Tough	    5	—	    100%	    III     DIRECT_DAMAGE
284	Eruption	            Fire	    Special 	Beautiful	5	150 	100%	    III     VARIABLE_POWER
285	Skill Swap	            Psychic 	Status  	Clever	    10	—	    —	        III     ABILITY_CHANGING        BYPASS_ACC_CHECK
286	Imprison	            Psychic     Status	    Clever	    10	—	    —	        III
287	Refresh	                Normal	    Status	    Cute	    20	—	    —	        III     CAN_HEAL_NON_VOL_STAT
288	Grudge	                Ghost	    Status	    Tough	    5	—	    —	        III
289	Snatch	                Dark	    Status	    Clever	    10	—	    —	        III     PRIORITY++
290	Secret Power	        Normal	    Physical	Clever	    20	70	    100%	    III     STAT_TARGET_ACC--       STAT_TARGET_ATT--       STAT_TARGET_DEF--       STAT_TARGET_SPATT--     STAT_TARGET_SPD--       CAN_FLINCH      CAN_CONFUSE     CAN_BRN     CAN_FRZ     CAN_PAR     CAN_PSN     CAN_SLP     VARIES_WITH_ENVIRONMENT
291	Dive	                Water	    Physical	Beautiful	10	80*	    100%	    III     HAS_SEMI_INVULNERABLE TURN      CHARGES
292	Arm Thrust	            Fighting	Physical	Tough	    20	15	    100%	    III     MULTI_STRIKE
293	Camouflage	            Normal	    Status	    Clever	    20	—	    —	        III     CHANGES_POKE_TYPE       VARIES_WITH_ENVIRONMENT
294	Tail Glow	            Bug     	Status	    Beautiful	20	—	    —	        III     STAT_USERS_SPATT++
295	Luster Purge	        Psychic	    Special	    Clever	    5	70	    100%	    III     STAT_TARGET_SPDEF--
296	Mist Ball	            Psychic	    Special	    Clever	    5	70	    100%	    III     STAT_TARGET_SPATT--     BALL_AND_BOMB
297	Feather Dance	        Flying	    Status	    Beautiful	15	—	    100%	    III     STAT_TARGET_ATT--       DANCE
298	Teeter Dance	        Normal	    Status	    Cute	    20	—	    100%	    III     DANCE       CAN_CONFUSE
299	Blaze Kick	            Fire	    Physical	Cool	    10	85	    90%	        III     CAN_BRN     HIGH_CRIT_HIT
300	Mud Sport	            Ground	    Status	    Cute	    15	—	    —	        III
301	Ice Ball	            Ice	        Physical	Beautiful	20	30  	90%     	III     CONSECUTIVE     BALL_AND_BOMB       POWERS_UP       VARIABLE_POWER
302	Needle Arm	            Grass	    Physical	Clever	    15	60  	100%	    III     MINIMIZED_TARGET_DMG++      CAN_FLINCH
303	Slack Off	            Normal	    Status	    Cute	    10	—   	—	        III     HEALS_USER_IMMEDIATELY       RESTORES_HP
304	Hyper Voice	            Normal	    Special 	Cool	    10	90  	100%    	III     SOUND_BASED
305	Poison Fang     	    Poison	    Physical	Clever	    15	50  	100%	    III     BITING      CAN_PSN
306	Crush Claw	            Normal	    Physical	Cool	    10	75  	95%	        III     STAT_TARGET_DEF--
307	Blast Burn	            Fire	    Special	    Beautiful	5	150 	90%	        III     RECHARGES
308	Hydro Cannon	        Water	    Special	    Beautiful	5	150 	90%     	III     RECHARGES
309	Meteor Mash	            Steel	    Physical	Cool	    10	90* 	90%*    	III     STAT_USERS_ATT++        PUNCHING
310	Astonish	            Ghost	    Physical	Cute	    15	30	    100%	    III     MINIMIZED_TARGET_DMG++      CAN_FLINCH
311	Weather Ball	        Normal	    Special	    Beautiful	10	50	    100%	    III     BALL_AND_BOMB       CAN_CHANGE_TYPE     POWERS_UP       VARIABLE_POWER
312	Aromatherapy	        Grass	    Status	    Clever	    5	—	    —	        III     CAN_HEAL_NON_VOL_STAT
313	Fake Tears	            Dark	    Status	    Cute	    20	—	    100%    	III     STAT_TARGET_SPDEF--
314	Air Cutter	            Flying	    Special	    Cool	    25	60*	    95%	        III     HIGH_CRIT_HIT
315	Overheat	            Fire	    Special	    Beautiful	5	130*	90%	        III     STAT_USERS_SPATT--
316	Odor Sleuth	            Normal	    Status	    Clever	    40	—       —*         III     BYPASS_ACC_CHECK     REMOVES_SOME_TYPE_IMMUNITIES
317	Rock Tomb	            Rock	    Physical	Clever	    15*	60* 	95%*    	III     STAT_TARGET_SPD--
318	Silver Wind	            Bug	        Special 	Beautiful	5	60  	100%    	III     STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++
319	Metal Sound	            Steel	    Status	    Clever	    40	—   	85%     	III     STAT_TARGET_SPDEF--     SOUND_BASED
320	Grass Whistle   	    Grass	    Status	    Clever	    15	—   	55%	        III     SOUND_BASED     CAN_SLP
321	Tickle	                Normal	    Status	    Cute	    20	—   	100%    	III     STAT_TARGET_ATT--       STAT_TARGET_DEF--
322	Cosmic Power	        Psychic 	Status	    Beautiful	20	—	    —	        III     STAT_USERS_DEF++        STAT_USERS_SPDEF++
323	Water Spout	            Water	    Special 	Beautiful	5	150 	100%    	III     VARIABLE_POWER
324	Signal Beam	            Bug	        Special	    Beautiful	15	75	    100%    	III     CAN_CONFUSE
325	Shadow Punch	        Ghost	    Physical	Clever	    20	60	    —	        III     PUNCHING        BYPASS_ACC_CHECK
326	Extrasensory	        Psychic	    Special	    Cool	    20*	80	    100%    	III     MINIMIZED_TARGET_DMG++      CAN_FLINCH
327	Sky Uppercut    	    Fighting    Physical	Cool	    15	85	    90%	        III     PUNCHING        CAN_HIT_SEMI_INVULNERABLE
328	Sand Tomb	            Ground	    Physical	Clever	    15	35* 	85%*	    III     BINDING		TRAPPING
329	Sheer Cold	            Ice	        Special	    Beautiful	5	—	    —*	        III     OHKO        DIRECT_DAMAGE
330	Muddy Water	            Water	    Special	    Tough	    10	90* 	85%	        III     STAT_TARGET_ACC--
331	Bullet Seed	            Grass	    Physical	Cool	    30	25* 	100%	    III     BALL_AND_BOMB       MULTI_STRIKE
332	Aerial Ace	            Flying  	Physical	Cool	    20	60	    —	        III     BYPASS_ACC_CHECK
333	Icicle Spear	        Ice	        Physical	Beautiful	30	25* 	100%	    III     MULTI_STRIKE
334	Iron Defense	        Steel	    Status	    Tough	    15	—	    —	        III     STAT_USERS_DEF++
335	Block	                Normal  	Status	    Cute	    5	—	    —	        III     BYPASS_ACC_CHECK        TRAPPING
336	Howl	                Normal	    Status	    Cool	    40	—	    —	        III     STAT_USERS_ATT++
337	Dragon Claw         	Dragon	    Physical	Cool	    15	80	    100%    	III
338	Frenzy Plant	        Grass	    Special	    Cool	    5	150 	90%	        III     RECHARGES
339	Bulk Up	                Fighting	Status	    Cool	    20	—   	—	        III     STAT_USERS_ATT++        STAT_USERS_DEF++
340	Bounce	                Flying	    Physical	Cute	    5	85  	85%	        III     CAN_PAR     HAS_SEMI_INVULNERABLE TURN      CHARGES
341	Mud Shot	            Ground	    Special	    Tough	    15	55  	95%	        III     STAT_TARGET_SPD--
342	Poison Tail	            Poison	    Physical	Clever  	25	50  	100%	    III     CAN_PSN     HIGH_CRIT_HIT
343	Covet	                Normal	    Physical	Cute	    25*	60* 	100%	    III
344	Volt Tackle	            Electric	Physical	Cool	    15	120 	100%	    III     CAN_PAR     HAVE_RECOIL
345	Magical Leaf	        Grass	    Special	    Beautiful	20	60	    —       	III     BYPASS_ACC_CHECK
346	Water Sport	            Water	    Status	    Cute	    15	—	    —	        III
347	Calm Mind	            Psychic 	Status	    Clever  	20	—	    —	        III     STAT_USERS_SPATT++      STAT_USERS_SPDEF++
348	Leaf Blade	            Grass	    Physical	Cool	    15	90* 	100%	    III     HIGH_CRIT_HIT
349	Dragon Dance	        Dragon	    Status	    Cool	    20	—	    —	        III     STAT_USERS_ATT++        STAT_USERS_SPD++        DANCE
350	Rock Blast	            Rock	    Physical	Tough	    10	25	    90%*	    III     BALL_AND_BOMB       MULTI_STRIKE
351	Shock Wave	            Electric	Special 	Cool	    20	60	    —	        III     BYPASS_ACC_CHECK
352	Water Pulse	            Water	    Special 	Beautiful	20	60	    100%	    III     AURA_AND_PULSE      CAN_CONFUSE
353	Doom Desire	            Steel	    Special 	Beautiful	5	140*	100%*   	III
354	Psycho Boost	        Psychic	    Special 	Clever	    5	140	    90%	        III     STAT_USERS_SPATT--
355	Roost	                Flying	    Status	    Clever	    10	—	    —	        IV      CHANGES_POKE_TYPE       HEALS_USER_IMMEDIATELY       RESTORES_HP
356	Gravity	                Psychic	    Status	    Clever	    5	—	    —	        IV      REMOVES_SOME_TYPE_IMMUNITIES
357	Miracle Eye     	    Psychic	    Status	    Clever	    40	—	    —	        IV      BYPASS_ACC_CHECK        REMOVES_SOME_TYPE_IMMUNITIES
358	Wake-Up Slap	        Fighting	Physical	Tough	    10	70* 	100%	    IV      CAN_HEAL_NON_VOL_STAT       POWERS_UP       VARIABLE_POWER
359	Hammer Arm	            Fighting	Physical	Tough	    10	100 	90%     	IV      STAT_USERS_SPD--        PUNCHING
360	Gyro Ball	            Steel	    Physical	Cool	    5	—	    100%    	IV      BALL_AND_BOMB       VARIABLE_POWER
361	Healing Wish	        Psychic	    Status	    Beautiful	10	—	    —	        IV      CAUSES_USER_TO_FAINT        RESTORES_HP
362	Brine	                Water	    Special	    Tough	    10	65	    100%	    IV      POWERS_UP       VARIABLE_POWER
363	Natural Gift	        Normal	    Physical	Clever	    15	—	    100%	    IV      CAN_CHANGE_TYPE     VARIABLE_POWER
364	Feint	                Normal	    Physical	Clever	    10	30* 	100%	    IV      PRIORITY++      BREAK_THROUGH_PROTECTION
365	Pluck	                Flying	    Physical	Cute	    20	60	    100%	    IV
366	Tailwind	            Flying	    Status	    Cool	    15*	—	    —	        IV
367	Acupressure     	    Normal	    Status	    Tough	    30	—	    —	        IV      STAT_TARGETS_ACC++      STAT_TARGETS_ATT++      STAT_TARGETS_DEF++      STAT_TARGETS_EVA++      STAT_TARGETS_SPATK++        STAT_TARGETS_SPDEF++        STAT_TARGETS_SPD++      STAT_USERS_ACC++        STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_EVA++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++
368	Metal Burst	            Steel	    Physical	Cool	    10	—	    100%	    IV      DIRECT_DAMAGE
369	U-turn	                Bug	        Physical	Cute	    20	70	    100%	    IV      SWITCHES_USER_OUT
370	Close Combat    	    Fighting	Physical	Tough	    5	120	    100%	    IV      STAT_USERS_DEF--        STAT_USERS_SPDEF--
371	Payback	                Dark	    Physical	Tough	    10	50	    100%	    IV      POWERS_UP       VARIABLE_POWER
372	Assurance	            Dark	    Physical	Clever	    10	60*	    100%	    IV      POWERS_UP       VARIABLE_POWER
373	Embargo	                Dark	    Status	    Clever	    15	—	    100%	    IV
374	Fling	                Dark	    Physical	Cute	    10	—	    100%	    IV      CAN_FLINCH      CAN_BRN     CAN_PAR     CAN_PSN     VARIABLE_POWER
375	Psycho Shift	        Psychic	    Status	    Clever	    10	—	    100%*	    IV      CAN_HEAL_NON_VOL_STAT       CAN_BRN     CAN_PAR     CAN_PSN     CAN_SLP
376	Trump Card	            Normal	    Special	    Cool	    5	—	    —	        IV      BYPASS_ACC_CHECK        VARIABLE_POWER
377	Heal Block	            Psychic	    Status	    Clever	    15	—	    100%    	IV
378	Wring Out	            Normal	    Special	    Tough	    5	—	    100%	    IV      VARIABLE_POWER
379	Power Trick	            Psychic 	Status	    Clever	    10	—   	—	        IV
380	Gastro Acid	            Poison      Status  	Tough	    10	—   	100%	    IV      AFFECTS_ABILITIES
381	Lucky Chant     	    Normal  	Status	    Cute	    30	—	    —	        IV
382	Me First	            Normal	    Status	    Clever	    20	—	    —	        IV      CALLS_OTHER_MOVES       BYPASS_ACC_CHECK
383	Copycat	                Normal	    Status	    Cute	    20	—	    —	        IV      CALLS_OTHER_MOVES
384	Power Swap	            Psychic	    Status	    Clever	    10	—	    —	        IV      BYPASS_ACC_CHECK
385	Guard Swap	            Psychic	    Status	    Clever	    10	—	    —	        IV      BYPASS_ACC_CHECK
386	Punishment	            Dark	    Physical	Cool	    5	—	    100%	    IV      VARIABLE_POWER
387	Last Resort	            Normal	    Physical	Cute	    5	140*	100%	    IV
388	Worry Seed	            Grass	    Status	    Clever	    10	—	    100%	    IV      ABILITY_CHANGING
389	Sucker Punch	        Dark	    Physical	Clever	    5	70* 	100%    	IV      PRIORITY++
390	Toxic Spikes	        Poison	    Status	    Clever	    20	—	    —	        IV      CAN_PSN     ENTRY_HAZARD
391	Heart Swap	            Psychic 	Status	    Clever	    10	—	    —	        IV      BYPASS_ACC_CHECK
392	Aqua Ring	            Water	    Status	    Beautiful	20	—	    —	        IV      RESTORES_HP
393	Magnet Rise     	    Electric	Status	    Clever	    10	—	    —	        IV
394	Flare Blitz	            Fire	    Physical	Cool	    15	120	    100%	    IV      CAN_THAW_USER       CAN_BRN     HAVE_RECOIL
395	Force Palm	            Fighting	Physical	Cool	    10	60	    100%	    IV      CAN_PAR
396	Aura Sphere	            Fighting	Special 	Beautiful	20	80*	    —	        IV      AURA_AND_PULSE      BALL_AND_BOMB       BYPASS_ACC_CHECK
397	Rock Polish	            Rock	    Status	    Tough	    20	—	    —	        IV      STAT_USERS_SPD++
398	Poison Jab	            Poison	    Physical	Tough	    20	80	    100%	    IV      CAN_PSN
399	Dark Pulse	            Dark	    Special	    Cool	    15	80	    100%	    IV      AURA_AND_PULSE      CAN_FLINCH
400	Night Slash	            Dark	    Physical	Cool	    15	70	    100%	    IV      HIGH_CRIT_HIT
401	Aqua Tail	            Water	    Physical	Beautiful	10	90	    90%     	IV
402	Seed Bomb	            Grass	    Physical	Tough	    15	80	    100%	    IV      BALL_AND_BOMB
403	Air Slash	            Flying	    Special	    Cool	    15*	75	    95%	        IV      CAN_FLINCH
404	X-Scissor	            Bug	        Physical	Cool	    15	80	    100%	    IV
405	Bug Buzz	            Bug	        Special	    Beautiful	10	90	    100%	    IV      STAT_TARGET_SPDEF--     SOUND_BASED
406	Dragon Pulse    	    Dragon	    Special	    Beautiful	10	85*	    100%	    IV      AURA_AND_PULSE
407	Dragon Rush	            Dragon	    Physical	Tough   	10	100	    75%     	IV      MINIMIZED_TARGET_DMG++      CAN_FLINCH      BYPASS_ACC_CHECK
408	Power Gem	            Rock	    Special	    Beautiful	20	80*	    100%	    IV
409	Drain Punch	            Fighting	Physical	Tough	    10*	75*	    100%	    IV      HP_DRAINING_DAMAGE		RESTORES_HP      PUNCHING
410	Vacuum Wave	            Fighting	Special 	Cool	    30	40	    100%	    IV      PRIORITY++
411	Focus Blast	            Fighting	Special	    Cool	    5	120	    70%	        IV      STAT_TARGET_SPDEF--     BALL_AND_BOMB
412	Energy Ball	            Grass	    Special	    Beautiful	10	90*	    100%	    IV      STAT_TARGET_SPDEF--     BALL_AND_BOMB
413	Brave Bird	            Flying	    Physical	Cool	    15	120	    100%	    IV      HAVE_RECOIL
414	Earth Power	            Ground	    Special	    Beautiful	10	90	    100%	    IV      STAT_TARGET_SPDEF--
415	Switcheroo	            Dark	    Status	    Clever	    10	—	    100%	    IV
416	Giga Impact	            Normal	    Physical	Tough	    5	150	    90%	        IV      RECHARGES
417	Nasty Plot	            Dark	    Status	    Clever  	20	—   	—	        IV      STAT_USERS_SPATT++
418	Bullet Punch    	    Steel	    Physical	Tough	    30	40	    100%	    IV      PRIORITY++      PUNCHING
419	Avalanche	            Ice	        Physical	Beautiful	10	60	    100%	    IV      PRIORITY--      POWERS_UP       VARIABLE_POWER
420	Ice Shard	            Ice	        Physical	Beautiful	30	40	    100%	    IV      PRIORITY++
421	Shadow Claw	            Ghost	    Physical	Cool	    15	70	    100%	    IV      HIGH_CRIT_HIT
422	Thunder Fang    	    Electric	Physical	Cool	    15	65	    95%	        IV      BITING      CAN_FLINCH      CAN_PAR
423	Ice Fang	            Ice     	Physical	Cool	    15	65	    95%	        IV      BITING      CAN_FLINCH      CAN_FRZ
424	Fire Fang	            Fire	    Physical	Cool	    15	65	    95%     	IV      BITING      CAN_FLINCH      CAN_BRN
425	Shadow Sneak            Ghost	    Physical	Clever	    30	40	    100%    	IV      PRIORITY++
426	Mud Bomb	            Ground	    Special	    Cute	    10	65	    85%	        IV      STAT_TARGET_ACC--       BALL_AND_BOMB
427	Psycho Cut	            Psychic 	Physical	Cool	    20	70	    100%	    IV      HIGH_CRIT_HIT
428	Zen Headbutt	        Psychic 	Physical	Clever	    15	80	    90%	        IV      CAN_FLINCH
429	Mirror Shot	            Steel	    Special 	Beautiful	10	65	    85%	        IV      STAT_TARGET_ACC--
430	Flash Cannon	        Steel	    Special 	Beautiful	10	80	    100%	    IV      STAT_TARGET_SPDEF--
431	Rock Climb	            Normal	    Physical	Tough	    20	90	    85%	        IV      CAN_CONFUSE
432	Defog	                Flying	    Status	    Cool	    15	—	    —	        IV      STAT_TARGET_EVA--       BYPASS_ACC_CHECK        WEATHER_CHANGING
433	Trick Room	            Psychic 	Status	    Clever  	5	—	    —	        IV      PRIORITY--
434	Draco Meteor    	    Dragon	    Special	    Beautiful	5	130*	90%	        IV      STAT_USERS_SPATT--
435	Discharge	            Electric	Special 	Beautiful	15	80	    100%	    IV      CAN_PAR
436	Lava Plume	            Fire	    Special	    Tough	    15	80	    100%	    IV      CAN_BRN
437	Leaf Storm	            Grass	    Special	    Beautiful	5	130*	90%	        IV      STAT_USERS_SPATT--
438	Power Whip	            Grass	    Physical	Tough	    10	120 	85%	        IV
439	Rock Wrecker    	    Rock	    Physical	Tough   	5	150 	90%	        IV      BALL_AND_BOMB       RECHARGES
440	Cross Poison	        Poison	    Physical	Cool	    20	70	    100%    	IV      CAN_PSN     HIGH_CRIT_HIT
441	Gunk Shot	            Poison	    Physical	Tough   	5	120 	80%*	    IV      CAN_PSN
442	Iron Head	            Steel	    Physical	Tough   	15	80	    100%	    IV      CAN_FLINCH
443	Magnet Bomb	            Steel	    Physical	Cool	    20	60	    —	        IV      BALL_AND_BOMB       BYPASS_ACC_CHECK
444	Stone Edge	            Rock	    Physical	Tough	    5	100	    80%     	IV      HIGH_CRIT_HIT
445	Captivate	            Normal	    Status  	Cute	    20	—	    100%	    IV      STAT_TARGET_SPATT--
446	Stealth Rock    	    Rock	    Status  	Cool	    20	—	    —	        IV      ENTRY_HAZARD
447	Grass Knot	            Grass	    Special 	Cute	    20	—	    100%	    IV      AFFECTED_BY_WEIGHT      VARIABLE_POWER
448	Chatter	                Flying	    Special 	Cute	    20	65*	    100%	    IV      SOUND_BASED     CAN_CONFUSE
449	Judgment	            Normal  	Special	    Beautiful	10	100	    100%	    IV      CAN_CHANGE_TYPE
450	Bug Bite	            Bug     	Physical	Cute	    20	60	    100%	    IV
451	Charge Beam	            Electric	Special	    Beautiful	10	50	    90%	        IV      STAT_USERS_SPATT++
452	Wood Hammer	            Grass	    Physical	Tough	    15	120	    100%    	IV      HAVE_RECOIL
453	Aqua Jet	            Water	    Physical	Cool	    20	40	    100%    	IV      PRIORITY++
454	Attack Order    	    Bug	        Physical	Clever	    15	90	    100%	    IV      HIGH_CRIT_HIT
455	Defend Order	        Bug	        Status	    Clever	    10	—   	—	        IV      STAT_USERS_DEF++        STAT_USERS_SPDEF++
456	Heal Order	            Bug	        Status	    Clever	    10	—	    —	        IV      HEALS_USER_IMMEDIATELY       RESTORES_HP
457	Head Smash	            Rock	    Physical	Tough	    5	150 	80%	        IV      HAVE_RECOIL
458	Double Hit	            Normal	    Physical	Cool	    10	35	    90%	        IV      MULTI_STRIKE
459	Roar of Time	        Dragon	    Special 	Beautiful	5	150	    90%	        IV      RECHARGES
460	Spacial Rend	        Dragon	    Special	    Beautiful	5	100	    95%	        IV      HIGH_CRIT_HIT
461	Lunar Dance	            Psychic 	Status	    Beautiful	10	—	    —	        IV      DANCE       CAUSES_USER_TO_FAINT      RESTORES_HP
462	Crush Grip	            Normal	    Physical	Tough	    5	—	    100%	    IV      VARIABLE_POWER
463	Magma Storm	            Fire	    Special 	Tough	    5	100*	75%*	    IV      BINDING		TRAPPING
464	Dark Void	            Dark	    Status	    Clever	    10	—	    50%*	    IV      CAN_SLP
465	Seed Flare	            Grass	    Special	    Beautiful	5	120	    85%     	IV      STAT_TARGET_SPDEF--
466	Ominous Wind    	    Ghost	    Special	    Beautiful	5	60	    100%	    IV      STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++
467	Shadow Force    	    Ghost	    Physical	Cool	    5	120	    100%	    IV      MINIMIZED_TARGET_DMG++      BREAK_THROUGH_PROTECTION        BYPASS_ACC_CHECK        HAS_SEMI_INVULNERABLE TURN      CHARGES
468	Hone Claws	            Dark	    Status	    Cute	    15	—	    —	        V       STAT_USERS_ACC++        STAT_USERS_ATT++
469	Wide Guard	            Rock	    Status	    Tough	    10	—	    —	        V       PRIORITY++      PROTECTION
470	Guard Split	            Psychic	    Status	    Clever  	10	—	    —	        V       BYPASS_ACC_CHECK
471	Power Split	            Psychic	    Status	    Clever  	10	—	    —	        V       BYPASS_ACC_CHECK
472	Wonder Room	            Psychic 	Status	    Clever	    10	—	    —	        V       PRIORITY--
473	Psyshock	            Psychic 	Special	    Beautiful	10	80	    100%	    V       USES_STATS_FROM_DIFF_CAT
474	Venoshock	            Poison	    Special	    Beautiful	10	65	    100%	    V       POWERS_UP       VARIABLE_POWER
475	Autotomize	            Steel	    Status	    Beautiful	15	—	    —	        V       AFFECTED_BY_WEIGHT      STAT_USERS_SPD++
476	Rage Powder	            Bug	        Status	    Clever	    20	—	    —	        V       PRIORITY++      POWDER_AND_SPORE        MAKES_TARGET_CENTER_OF_ATTENTION
477	Telekinesis	            Psychic     Status	    Clever	    15	—	    —       	V       BYPASS_ACC_CHECK
478	Magic Room	            Psychic 	Status	    Clever	    10	—	    —	        V       PRIORITY--
479	Smack Down	            Rock	    Physical	Tough	    15	50	    100%	    V       CAN_HIT_SEMI_INVULNERABLE       REMOVES_SOME_TYPE_IMMUNITIES
480	Storm Throw	            Fighting	Physical	Cool	    10	60*	    100%	    V       PERFECT_CRIT_HIT
481	Flame Burst	            Fire	    Special 	Beautiful	15	70	    100%	    V
482	Sludge Wave	            Poison	    Special 	Tough	    10	95	    100%	    V       CAN_PSN
483	Quiver Dance    	    Bug	        Status	    Beautiful	20	—	    —	        V       STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++        DANCE
484	Heavy Slam	            Steel	    Physical	Tough	    10	—	    100%	    V       AFFECTED_BY_WEIGHT      MINIMIZED_TARGET_DMG++      VARIABLE_POWER
485	Synchronoise	        Psychic 	Special 	Clever	    10*	120*	100%	    V
486	Electro Ball	        Electric	Special 	Cool	    10	—	    100%	    V       BALL_AND_BOMB       VARIABLE_POWER
487	Soak	                Water	    Status	    Cute	    20	—	    100%	    V       CHANGES_POKE_TYPE
488	Flame Charge	        Fire	    Physical	Cool	    20	50	    100%	    V       STAT_USERS_SPD++
489	Coil	                Poison	    Status	    Tough	    20	—	    —	        V       STAT_USERS_ACC++        STAT_USERS_ATT++        STAT_USERS_DEF++
490	Low Sweep	            Fighting	Physical	Clever	    20	65*	    100%	    V       STAT_TARGET_SPD--
491	Acid Spray	            Poison	    Special 	Beautiful	20	40	    100%	    V       STAT_TARGET_SPDEF--     BALL_AND_BOMB
492	Foul Play	            Dark	    Physical	Clever	    15	95	    100%	    V
493	Simple Beam     	    Normal	    Status	    Cute	    15	—	    100%	    V       ABILITY_CHANGING
494	Entrainment	            Normal	    Status	    Cute	    15	—	    100%	    V       ABILITY_CHANGING
495	After You	            Normal	    Status	    Cute	    15	—	    —	        V       BYPASS_ACC_CHECK
496	Round	                Normal	    Special	    Beautiful	15	60	    100%	    V       SOUND_BASED     POWERS_UP       VARIABLE_POWER
497	Echoed Voice	        Normal	    Special	    Beautiful	15	40	    100%	    V       SOUND_BASED     POWERS_UP       VARIABLE_POWER
498	Chip Away	            Normal	    Physical	Tough	    20	70	    100%	    V
499	Clear Smog	            Poison	    Special	    Beautiful	15	50	    —	        V       BYPASS_ACC_CHECK
500	Stored Power	        Psychic	    Special	    Clever	    10	20	    100%	    V       VARIABLE_POWER
501	Quick Guard     	    Fighting	Status	    Cool	    15	—	    —	        V       PRIORITY++      PROTECTION
502	Ally Switch	            Psychic	    Status	    Clever	    15	—	    —	        V       PRIORITY++
503	Scald	                Water	    Special	    Tough	    15	80	    100%	    V       CAN_THAW_USER       CAN_HEAL_NON_VOL_STAT       CAN_BRN
504	Shell Smash	            Normal	    Status	    Tough	    15	—	    —	        V       STAT_USERS_DEF--        STAT_USERS_SPDEF--      STAT_USERS_ATT++        STAT_USERS_SPATT++      STAT_USERS_SPD++
505	Heal Pulse	            Psychic     Status	    Beautiful	10	—	    —	        V       AURA_AND_PULSE      BYPASS_ACC_CHECK        RESTORES_HP
506	Hex	                    Ghost	    Special	    Clever	    10	65*	    100%	    V       POWERS_UP       VARIABLE_POWER
507	Sky Drop	            Flying  	Physical	Tough	    10	60	    100%	    V       AFFECTED_BY_WEIGHT      HAS_SEMI_INVULNERABLE TURN      CHARGES
508	Shift Gear      	    Steel	    Status	    Clever	    10	—	    —	        V       STAT_USERS_ATT++        STAT_USERS_SPD++
509	Circle Throw	        Fighting	Physical	Cool	    10	60	    90%	        V       PRIORITY--      SWITCHES_TARGET_OUT
510	Incinerate	            Fire	    Special	    Tough	    15	60*	    100%	    V
511	Quash	                Dark	    Status	    Clever	    15	—	    100%	    V
512	Acrobatics	            Flying	    Physical	Cool	    15	55	    100%	    V       POWERS_UP       VARIABLE_POWER
513	Reflect Type	        Normal  	Status	    Clever	    15	—	    —	        V       BYPASS_ACC_CHECK        CHANGES_POKE_TYPE
514	Retaliate	            Normal	    Physical	Cool	    5	70	    100%	    V       POWERS_UP       VARIABLE_POWER
515	Final Gambit	        Fighting	Special	    Tough	    5	—	    100%	    V       CAUSES_USER_TO_FAINT        DIRECT_DAMAGE
516	Bestow	                Normal	    Status	    Cute	    15	—	    —	        V       BYPASS_ACC_CHECK
517	Inferno	                Fire	    Special	    Beautiful	5	100	    50%	        V       CAN_BRN
518	Water Pledge	        Water	    Special	    Beautiful	10	80*	    100%	    V       POWERS_UP       VARIABLE_POWER
519	Fire Pledge	            Fire	    Special	    Beautiful	10	80*	    100%	    V       POWERS_UP       VARIABLE_POWER
520	Grass Pledge	        Grass   	Special 	Beautiful	10	80*	    100%	    V       POWERS_UP       VARIABLE_POWER
521	Volt Switch     	    Electric	Special 	Cool	    20	70	    100%	    V       SWITCHES_USER_OUT
522	Struggle Bug	        Bug	        Special 	Cute	    20	50*	    100%	    V       STAT_TARGET_SPATT--
523	Bulldoze	            Ground	    Physical	Tough	    20	60	    100%	    V       STAT_TARGET_SPD--
524	Frost Breath	        Ice	        Special 	Beautiful	10	60*	    90%	        V       PERFECT_CRIT_HIT
525	Dragon Tail     	    Dragon	    Physical	Tough	    10	60	    90%	        V       PRIORITY--      SWITCHES_TARGET_OUT
526	Work Up	                Normal  	Status	    Tough	    30	—	    —	        V       STAT_USERS_ATT++        STAT_USERS_SPATT++
527	Electroweb	            Electric	Special	    Beautiful	15	55	    95%	        V       STAT_TARGET_SPD--
528	Wild Charge	            Electric	Physical	Tough	    15	90	    100%	    V       HAVE_RECOIL
529	Drill Run	            Ground	    Physical	Tough	    10	80	    95%	        V       HIGH_CRIT_HIT
530	Dual Chop	            Dragon  	Physical	Tough	    15	40	    90%	        V       MULTI_STRIKE
531	Heart Stamp	            Psychic 	Physical	Cute	    25	60	    100%	    V       CAN_FLINCH
532	Horn Leech	            Grass	    Physical	Tough	    10	75	    100%	    V       HP_DRAINING_DAMAGE      RESTORES_HP
533	Sacred Sword	        Fighting	Physical	Cool	    15*	90	    100%	    V
534	Razor Shell	            Water	    Physical	Cool	    10	75	    95%	        V       STAT_TARGET_DEF--
535	Heat Crash	            Fire	    Physical	Tough	    10	—	    100%	    V       AFFECTED_BY_WEIGHT      MINIMIZED_TARGET_DMG++      BYPASS_ACC_CHECK        VARIABLE_POWER
536	Leaf Tornado	        Grass	    Special	    Cool	    10	65	    90%	        V       STAT_TARGET_ACC--
537	Steamroller	            Bug	        Physical	Tough	    20	65	    100%	    V       MINIMIZED_TARGET_DMG++      CAN_FLINCH      BYPASS_ACC_CHECK
538	Cotton Guard	        Grass   	Status	    Cute	    10	—	    —	        V       STAT_USERS_DEF++
539	Night Daze	            Dark	    Special	    Cool	    10	85	    95%	        V       STAT_TARGET_ACC--
540	Psystrike	            Psychic 	Special	    Cool	    10	100	    100%	    V       USES_STATS_FROM_DIFF_CAT
541	Tail Slap	            Normal  	Physical	Cute	    10	25	    85%	        V       MULTI_STRIKE
542	Hurricane	            Flying  	Special	    Tough	    10	110*	70%	        V       CAN_CONFUSE     CAN_HIT_SEMI_INVULNERABLE       BYPASS_ACC_CHECK
543	Head Charge	            Normal	    Physical	Tough	    15	120	    100%	    V       HAVE_RECOIL
544	Gear Grind	            Steel	    Physical	Clever	    15	50	    85%	        V       MULTI_STRIKE
545	Searing Shot	        Fire	    Special	    Cool	    5	100	    100%	    V       BALL_AND_BOMB       CAN_BRN
546	Techno Blast	        Normal	    Special	    Cool	    5	120*	100%	    V       CAN_CHANGE_TYPE
547	Relic Song	            Normal  	Special	    Beautiful	10	75	    100%	    V       SOUND_BASED     CAN_SLP
548	Secret Sword	        Fighting	Special	    Beautiful	10	85	    100%	    V       USES_STATS_FROM_DIFF_CAT
549	Glaciate	            Ice	        Special	    Beautiful	10	65	    95%	        V       STAT_TARGET_SPD--
550	Bolt Strike	            Electric	Physical	Beautiful	5	130	    85%	        V       CAN_PAR
551	Blue Flare	            Fire	    Special	    Beautiful	5	130	    85%	        V       CAN_BRN
552	Fiery Dance	            Fire	    Special	    Beautiful	10	80	    100%	    V       STAT_USERS_SPATT++      DANCE
553	Freeze Shock	        Ice	        Physical	Beautiful	5	140	    90%	        V       CAN_PAR     CHARGES
554	Ice Burn	            Ice	        Special	    Beautiful	5	140	    90%	        V       CAN_BRN     CHARGES
555	Snarl	                Dark    	Special	    Tough	    15	55	    95%	        V       STAT_TARGET_SPATT--     SOUND_BASED
556	Icicle Crash	        Ice     	Physical	Beautiful	10	85	    90%	        V       CAN_FLINCH
557	V-create	            Fire	    Physical	Cool	    5	180	    95%	        V       STAT_USERS_DEF--        STAT_USERS_SPDEF--      STAT_USERS_SPD--
558	Fusion Flare	        Fire	    Special	    Beautiful	5	100	    100%	    V       CAN_THAW_USER       POWERS_UP       VARIABLE_POWER
559	Fusion Bolt	            Electric	Physical	Cool	    5	100	    100%	    V       POWERS_UP       VARIABLE_POWER
560	Flying Press	        Fighting	Physical	Tough	    10	100*	95%	        VI      UNIQUE_TYPE_EFFECTIVENESS       MINIMIZED_TARGET_DMG++      BYPASS_ACC_CHECK        SPECIAL_TYPE_EFFECTIVENESS
561	Mat Block	            Fighting	Status	    Cool	    10	—	    —	        VI      PROTECTION
562	Belch	                Poison	    Special	    Tough	    10	120	    90%	        VI
563	Rototiller	            Ground	    Status	    Tough	    10	—	    —	        VI      STAT_TARGETS_ATT++      STAT_TARGETS_SPATK++
564	Sticky Web      	    Bug	        Status	    Tough	    20	—	    —	        VI      STAT_TARGET_SPD--       ENTRY_HAZARD
565	Fell Stinger	        Bug     	Physical	Cool	    25	50*	    100%	    VI      STAT_USERS_ATT++
566	Phantom Force	        Ghost	    Physical	Cool	    10	90	    100%	    VI      MINIMIZED_TARGET_DMG++      BREAK_THROUGH_PROTECTION        BYPASS_ACC_CHECK        HAS_SEMI_INVULNERABLE TURN      CHARGES
567	Trick-or-Treat          Ghost   	Status	    Cute	    20	—	    100%	    VI      CHANGES_POKE_TYPE
568	Noble Roar	            Normal  	Status	    Tough	    30	—	    100%	    VI      STAT_TARGET_ATT--       STAT_TARGET_SPATT--     SOUND_BASED     BYPASS_ACC_CHECK
569	Ion Deluge	            Electric	Status	    Beautiful	25	—	    —	        VI      MODIFIES_TARGETS_MOVE_TYPE      PRIORITY++
570	Parabolic Charge	    Electric	Special	    Clever	    20	65*	    100%	    VI      HP_DRAINING_DAMAGE		RESTORES_HP
571	Forest's Curse	        Grass	    Status	    Clever	    20	—	    100%	    VI      CHANGES_POKE_TYPE
572	Petal Blizzard	        Grass	    Physical	Beautiful	15	90	    100%	    VI
573	Freeze-Dry	            Ice	        Special	    Beautiful	20	70	    100%	    VI      UNIQUE_TYPE_EFFECTIVENESS       CAN_FRZ     SPECIAL_TYPE_EFFECTIVENESS
574	Disarming Voice	        Fairy	    Special	    Cute	    15	40	    —	        VI      SOUND_BASED     BYPASS_ACC_CHECK
575	Parting Shot	        Dark	    Status	    Cool	    20	—	    100%	    VI      STAT_TARGET_ATT--       STAT_TARGET_SPATT--     SOUND_BASED     SWITCHES_USER_OUT
576	Topsy-Turvy	            Dark	    Status	    Clever	    20	—	    —*	        VI      BYPASS_ACC_CHECK
577	Draining Kiss	        Fairy	    Special	    Cute	    10	50	    100%	    VI      HP_DRAINING_DAMAGE		RESTORES_HP
578	Crafty Shield	        Fairy	    Status	    Clever	    10	—	    —	        VI      PRIORITY++      PROTECTION
579	Flower Shield	        Fairy	    Status	    Beautiful	10	—	    —	        VI      STAT_TARGETS_DEF++      STAT_USERS_DEF++
580	Grassy Terrain	        Grass	    Status	    Beautiful	10	—	    —	        VI      CHANGES_TERRAIN     RESTORES_HP
581	Misty Terrain	        Fairy	    Status	    Beautiful	10	—	    —	        VI      CHANGES_TERRAIN
582	Electrify	            Electric	Status	    Clever	    20	—	    —	        VI      MODIFIES_TARGETS_MOVE_TYPE      BYPASS_ACC_CHECK
583	Play Rough	            Fairy	    Physical	Cute	    10	90	    90%	        VI      STAT_TARGET_ATT--
584	Fairy Wind	            Fairy	    Special	    Beautiful	30	40	    100%	    VI
585	Moonblast	            Fairy	    Special	    Beautiful	15	95	    100%	    VI      STAT_TARGET_SPATT--
586	Boomburst	            Normal  	Special	    Tough	    10	140	    100%	    VI      SOUND_BASED
587	Fairy Lock	            Fairy   	Status	    Clever	    10	—	    —	        VI      TRAPPING
588	King's Shield	        Steel	    Status	    Cool	    10	—	    —	        VI      PRIORITY++      STAT_TARGET_ATT--       PROTECTION
589	Play Nice	            Normal  	Status	    Cute	    20	—	    —	        VI      STAT_TARGET_ATT--       BYPASS_ACC_CHECK
590	Confide	                Normal	    Status	    Cute	    20	—	    —	        VI      STAT_TARGET_SPATT--     SOUND_BASED     BYPASS_ACC_CHECK
591	Diamond Storm	        Rock	    Physical	Beautiful	5	100	    95%	        VI      STAT_USERS_DEF++
592	Steam Eruption	        Water	    Special	    Beautiful	5	110	    95%	        VI      CAN_THAW_USER       CAN_HEAL_NON_VOL_STAT       CAN_BRN
593	Hyperspace Hole	        Psychic 	Special	    Clever	    5	80	    —	        VI      BREAK_THROUGH_PROTECTION        BYPASS_ACC_CHECK
594	Water Shuriken*	        Water	    Special	    Cool	    20	15	    100%	    VI      PRIORITY++      MULTI_STRIKE
595	Mystical Fire	        Fire	    Special	    Beautiful	10	75*	    100%	    VI      STAT_TARGET_SPATT--
596	Spiky Shield	        Grass	    Status	    Tough	    10	—	    —	        VI      PRIORITY++      PROTECTION
597	Aromatic Mist	        Fairy	    Status	    Beautiful	20	—	    —	        VI      STAT_TARGETS_SPDEF++        STAT_USERS_SPDEF++
598	Eerie Impulse	        Electric	Status	    Clever	    15	—	    100%	    VI      STAT_TARGET_SPATT--
599	Venom Drench	        Poison	    Status	    Clever	    20	—	    100%	    VI      STAT_TARGET_ATT--       STAT_TARGET_SPATT--     STAT_TARGET_SPD--
600	Powder	                Bug	        Status	    Clever	    20	—	    100%	    VI      PRIORITY++      POWDER_AND_SPORE        BYPASS_ACC_CHECK
601	Geomancy	            Fairy	    Status	    Beautiful	10	—	    —	        VI      STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++        CHARGES
602	Magnetic Flux	        Electric	Status	    Clever	    20	—	    —	        VI      STAT_TARGETS_DEF++      STAT_TARGETS_SPDEF++
603	Happy Hour	            Normal	    Status	    Cute	    30	—	    —	        VI
604	Electric Terrain	    Electric	Status	    Clever	    10	—	    —	        VI      CHANGES_TERRAIN
605	Dazzling Gleam  	    Fairy	    Special	    Beautiful	10	80	    100%	    VI
606	Celebrate       	    Normal	    Status	    Cute	    40	—	    —	        VI      NO_EFFECT
607	Hold Hands	            Normal	    Status	    Cute	    40	—	    —	        VI      NO_EFFECT
608	Baby-Doll Eyes	        Fairy	    Status	    Cute	    30	—	    100%	    VI      PRIORITY++      STAT_TARGET_ATT--
609	Nuzzle	                Electric	Physical	Cute	    20	20	    100%	    VI      CAN_PAR
610	Hold Back	            Normal	    Physical	Cool	    40	40	    100%	    VI
611	Infestation     	    Bug	        Special	    Cute	    20	20	    100%	    VI      BINDING		TRAPPING
612	Power-Up Punch  	    Fighting	Physical	Tough	    20	40	    100%	    VI      STAT_USERS_ATT++        PUNCHING
613	Oblivion Wing	        Flying	    Special 	Cool	    10	80	    100%    	VI      HP_DRAINING_DAMAGE		RESTORES_HP
614	Thousand Arrows 	    Ground	    Physical	Beautiful	10	90	    100%	    VI      UNIQUE_TYPE_EFFECTIVENESS       CAN_HIT_SEMI_INVULNERABLE       SPECIAL_TYPE_EFFECTIVENESS      REMOVES_SOME_TYPE_IMMUNITIES
615	Thousand Waves	        Ground	    Physical	Tough	    10	90	    100%	    VI      TRAPPING
616	Land's Wrath	        Ground  	Physical	Beautiful	10	90	    100%	    VI
617	Light of Ruin	        Fairy	    Special 	Beautiful	5	140	    90%	        VI      HAVE_RECOIL
618	Origin Pulse	        Water	    Special	    Beautiful	10	110	    85%	        VI*     AURA_AND_PULSE
619	Precipice Blades	    Ground  	Physical	Cool	    10	120	    85%	        VI*
620	Dragon Ascent	        Flying  	Physical	Beautiful	5	120	    100%	    VI*     STAT_USERS_DEF--        STAT_USERS_SPDEF--
621	Hyperspace Fury	        Dark	    Physical	Tough	    5	100	    —	        VI*     STAT_USERS_DEF--        BREAK_THROUGH_PROTECTION        BYPASS_ACC_CHECK
622	Breakneck Blitz 	    Normal  	Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
623	Breakneck Blitz	        Normal	    Special 	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
624	All-Out Pummeling	    Fighting	Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
625	All-Out Pummeling	    Fighting	Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
626	Supersonic Skystrike	Flying	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
627	Supersonic Skystrike	Flying	    Special 	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
628	Acid Downpour	        Poison	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
629	Acid Downpour	        Poison	    Special 	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
630	Tectonic Rage	        Ground	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
631	Tectonic Rage	        Ground  	Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
632	Continental Crush	    Rock	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
633	Continental Crush	    Rock	    Special 	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
634	Savage Spin-Out     	Bug	        Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
635	Savage Spin-Out	        Bug	        Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
636	Never-Ending Nightmare	Ghost	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
637	Never-Ending Nightmare	Ghost	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
638	Corkscrew Crash     	Steel	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
639	Corkscrew Crash     	Steel   	Special 	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
640	Inferno Overdrive	    Fire	    Physical	???     	1	—	    —	        VII     BYPASS_ACC_CHECK
641	Inferno Overdrive	    Fire	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
642	Hydro Vortex	        Water   	Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
643	Hydro Vortex	        Water   	Special	    ???     	1	—   	—	        VII     BYPASS_ACC_CHECK
644	Bloom Doom	            Grass	    Physical	???     	1	—	    —	        VII     BYPASS_ACC_CHECK
645	Bloom Doom	            Grass	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
646	Gigavolt Havoc	        Electric	Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
647	Gigavolt Havoc	        Electric	Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
648	Shattered Psyche	    Psychic	    Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
649	Shattered Psyche	    Psychic	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
650	Subzero Slammer     	Ice	        Physical	???	        1	—	    —	        VII     BYPASS_ACC_CHECK
651	Subzero Slammer	        Ice	        Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
652	Devastating Drake	    Dragon  	Physical	???     	1	—	    —	        VII     BYPASS_ACC_CHECK
653	Devastating Drake	    Dragon	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
654	Black Hole Eclipse	    Dark	    Physical	???	        1	—	    —       	VII     BYPASS_ACC_CHECK
655	Black Hole Eclipse	    Dark	    Special	    ???     	1	—	    —       	VII     BYPASS_ACC_CHECK
656	Twinkle Tackle      	Fairy   	Physical	???	        1	—	    —       	VII     BYPASS_ACC_CHECK
657	Twinkle Tackle	        Fairy   	Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK
658	Catastropika        	Electric	Physical	???	        1	210	    —	        VII     BYPASS_ACC_CHECK
659	Shore Up	            Ground  	Status	    ???	        10	—	    —	        VII     HEALS_USER_IMMEDIATELY       RESTORES_HP
660	First Impression	    Bug     	Physical	???	        10	90	    100%    	VII     PRIORITY++
661	Baneful Bunker	        Poison	    Status	    ???	        10	—	    —	        VII     PRIORITY++      CAN_PSN     PROTECTION
662	Spirit Shackle	        Ghost	    Physical	???	        10	80	    100%    	VII     TRAPPING
663	Darkest Lariat	        Dark	    Physical	???	        10	85	    100%    	VII
664	Sparkling Aria	        Water	    Special	    ???	        10	90	    100%	    VII     SOUND_BASED     CAN_HEAL_NON_VOL_STAT
665	Ice Hammer	            Ice	        Physical	???	        10	100	    90%	        VII     STAT_USERS_SPD--        PUNCHING
666	Floral Healing      	Fairy	    Status	    ???	        10	—	    —	        VII     BYPASS_ACC_CHECK        RESTORES_HP
667	High Horsepower	        Ground	    Physical	???	        10	95	    95%	        VII
668	Strength Sap	        Grass	    Status	    ???	        10	—	    100%    	VII     STAT_TARGET_ATT--       HEALS_USER_IMMEDIATELY       RESTORES_HP
669	Solar Blade	            Grass	    Physical	???	        10	125	    100%      	VII     CHARGES
670	Leafage	                Grass	    Physical	???	        40	40	    100%	    VII
671	Spotlight	            Normal	    Status  	???	        15	—	    —	        VII     PRIORITY++      MAKES_TARGET_CENTER_OF_ATTENTION
672	Toxic Thread	        Poison	    Status  	???	        20	—	    100%	    VII     STAT_TARGET_SPD--       CAN_PSN
673	Laser Focus         	Normal	    Status  	???	        30	—	    —	        VII
674	Gear Up             	Steel	    Status  	???	        20	—	    —	        VII     STAT_TARGETS_ATT++      STAT_TARGETS_SPATK++
675	Throat Chop         	Dark	    Physical	???     	15	80	    100%	    VII
676	Pollen Puff	            Bug	        Special	    ???	        15	90	    100%	    VII     BALL_AND_BOMB       RESTORES_HP
677	Anchor Shot	            Steel	    Physical	???	        20	80	    100%	    VII     TRAPPING
678	Psychic Terrain     	Psychic	    Status	    ???     	10	—	    —	        VII     CHANGES_TERRAIN
679	Lunge	                Bug	        Physical	???     	15	80	    100%	    VII     STAT_TARGET_ATT--
680	Fire Lash	            Fire	    Physical	???	        15	80	    100%	    VII     STAT_TARGET_DEF--
681	Power Trip	            Dark	    Physical	???	        10	20	    100%	    VII     VARIABLE_POWER
682	Burn Up	                Fire	    Special 	???     	5	130	    100%	    VII     CAN_THAW_USER       CHANGES_POKE_TYPE
683	Speed Swap	            Psychic 	Status	    ???     	10	—	    —	        VII     BYPASS_ACC_CHECK
684	Smart Strike	        Steel	    Physical	???	        10	70	    —	        VII     BYPASS_ACC_CHECK
685	Purify	                Poison	    Status	    ???	        20	—	    —	        VII     CAN_HEAL_NON_VOL_STAT       HEALS_USER_IMMEDIATELY       RESTORES_HP
686	Revelation Dance	    Normal	    Special	    ???     	15	90	    100%	    VII     DANCE       CAN_CHANGE_TYPE
687	Core Enforcer	        Dragon	    Special	    ???	        10	100	    100%	    VII     AFFECTS_ABILITIES
688	Trop Kick	            Grass	    Physical	???     	15	70	    100%	    VII     STAT_TARGET_ATT--
689	Instruct	            Psychic	    Status	    ???     	15	—	    —	        VII
690	Beak Blast	            Flying	    Physical	???	        15	100	    100%	    VII     PRIORITY--      BALL_AND_BOMB       CAN_BRN
691	Clanging Scales         Dragon	    Special 	???	        5	110	    100%	    VII     STAT_USERS_DEF--        SOUND_BASED
692	Dragon Hammer	        Dragon	    Physical	???     	15	90	    100%	    VII
693	Brutal Swing	        Dark	    Physical	???     	20	60	    100%	    VII
694	Aurora Veil         	Ice	        Status	    ???     	20	—	    —	        VII
695	Sinister Arrow Raid 	Ghost	    Physical	???	        1	180	    —	        VII     BYPASS_ACC_CHECK
696	Malicious Moonsault	    Dark	    Physical	???	        1	180	    —	        VII     MINIMIZED_TARGET_DMG++      BYPASS_ACC_CHECK        POWERS_UP       VARIABLE_POWER
697	Oceanic Operetta	    Water	    Special	    ???	        1	195	    —	        VII     BYPASS_ACC_CHECK
698	Guardian of Alola	    Fairy	    Special	    ???	        1	—	    —	        VII     BYPASS_ACC_CHECK        DIRECT_DAMAGE
699	Soul-Stealing 7-Star Strike
                            Ghost	Physical	    ???	        1	195 	—	        VII     BYPASS_ACC_CHECK
700	Stoked Sparksurfer	    Electric	Special	    ???     	1	175 	—	        VII     CAN_PAR     BYPASS_ACC_CHECK
701	Pulverizing Pancake	    Normal	    Physical	???	        1	210 	—	        VII     BYPASS_ACC_CHECK
702	Extreme Evoboost	    Normal	    Status	    ???	        1	—	    —	        VII     STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++
703	Genesis Supernova	    Psychic	    Special	    ???	        1	185 	—	        VII     CHANGES_TERRAIN
704	Shell Trap	            Fire	    Special	    ???     	5	150 	100%	    VII     PRIORITY--
705	Fleur Cannon	        Fairy	    Special	    ???     	5	130	    90%	        VII     STAT_USERS_SPATT--
706	Psychic Fangs	        Psychic	    Physical	???	        10	85	    100%	    VII     BITING
707	Stomping Tantrum	    Ground	    Physical	???	        10	75	    100%	    VII     POWERS_UP       VARIABLE_POWER
708	Shadow Bone     	    Ghost	    Physical	???	        10	85	    100%	    VII     STAT_TARGET_DEF--
709	Accelerock	            Rock	    Physical	???	        20	40	    100%	    VII     PRIORITY++
710	Liquidation	            Water	    Physical	???	        10	85	    100%	    VII     STAT_TARGET_DEF--
711	Prismatic Laser	        Psychic	    Special	    ???	        10	160 	100%	    VII     RECHARGES
712	Spectral Thief	        Ghost	    Physical	???	        10	90	    100%	    VII
713	Sunsteel Strike	        Steel	    Physical	???     	5	100 	100%	    VII     IGNORES_ABILITIES
714	Moongeist Beam	        Ghost	    Special	    ???     	5	100 	100%	    VII     IGNORES_ABILITIES
715	Tearful Look	        Normal	    Status	    ???     	20	—	    —       	VII     STAT_TARGET_ATT--       STAT_TARGET_SPATT--
716	Zing Zap	            Electric	Physical	???     	10	80  	100%	    VII     CAN_FLINCH
717	Nature's Madness    	Fairy	    Special	    ???     	10	—	    90%	        VII     DIRECT_DAMAGE
718	Multi-Attack	        Normal	    Physical	???	        10	90	    100%	    VII     CAN_CHANGE_TYPE
719	10,000,000 Volt Thunderbolt
                            Electric	Special	    ???	        1	195	    —	        VII     BYPASS_ACC_CHECK        HIGH_CRIT_HIT
720	Mind Blown	            Fire	    Special	    ???     	5	150	    100%	    VII*    EXPLOSIVE       COST_HP

721	Plasma Fists	        Electric	Physical	???	        15	100 	100%	    VII*    PUNCHING
722	Photon Geyser	        Psychic	    Special     ???     	5	100 	100%	    VII*    IGNORES_ABILITIES       USES_STATS_FROM_DIFF_CAT
723	Light That Burns the Sky
                            Psychic	    Special	    ???	        1	200	    —	        VII*    IGNORES_ABILITIES       USES_STATS_FROM_DIFF_CAT
724	Searing Sunraze Smash	Steel	    Special	    ???     	1	200	    —	        VII*    IGNORES_ABILITIES
725	Menacing Moonraze Maelstrom
                            Ghost	    Special	    ???     	1	200	    —	        VII*    IGNORES_ABILITIES
726	Let's Snuggle Forever	Fairy	    Physical	???	        1	190	    —	        VII*
727	Splintered Stormshards	Rock	    Physical	???	        1	190	    —	        VII*
728	Clangorous Soulblaze	Dragon	    Special	    ???	        1	185	    —	        VII*    STAT_USERS_ATT++        STAT_USERS_DEF++        STAT_USERS_SPATT++      STAT_USERS_SPDEF++      STAT_USERS_SPD++        SOUND_BASED
729	Zippy Zap	            Electric	Physical	???	        15	50	    100%	    VII*    PRIORITY++      PERFECT_CRIT_HIT

730	Splishy Splash	        Water	    Special	    ???	        15	90	    100%	    VII*    CAN_PAR
731	Floaty Fall	            Flying	    Physical	???	        15	90	    95%	        VII*
732	Pika Papow          	Electric	Special	    ???	        20	—	    —	        VII*    BYPASS_ACC_CHECK        VARIABLE_POWER
733	Bouncy Bubble       	Water	    Special 	???	        15	90	    100%	    VII*    HP_DRAINING_DAMAGE		RESTORES_HP      BYPASS_ACC_CHECK
734	Buzzy Buzz	            Electric	Special	    ???	        15	90	    100%	    VII*    CAN_PAR     BYPASS_ACC_CHECK
735	Sizzly Slide	        Fire	    Physical	???	        15	90	    100%	    VII*    CAN_BRN     BYPASS_ACC_CHECK
736	Glitzy Glow	            Psychic	    Special	    ???	        15	90	    100%	    VII*    BYPASS_ACC_CHECK
737	Baddy Bad	            Dark	    Special	    ???	        15	90	    100%	    VII*    BYPASS_ACC_CHECK
738	Sappy Seed	            Grass	    Physical	???	        15	90	    100%	    VII*    BYPASS_ACC_CHECK
739	Freezy Frost	        Ice	        Special	    ???	        15	90	    100%	    VII*    BYPASS_ACC_CHECK
740	Sparkly Swirl	        Fairy	    Special	    ???	        15	90	    100%	    VII*    BYPASS_ACC_CHECK
741	Veevee Volley	        Normal	    Physical	???	        20	—	    —	        VII*    BYPASS_ACC_CHECK        VARIABLE_POWER
742	Double Iron Bash	    Steel	    Physical	???	        5	60	    100%	    VII*    CAN_FLINCH      MULTI_STRIKE

     */
}
