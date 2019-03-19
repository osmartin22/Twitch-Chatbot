package ozmar.pokemonBattle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PokeMovesHelper {

    private PokeMovesHelper() {

    }

    // Moves that bind the user
    public static final Set<String> BINDING_MOVES = new HashSet<>(Arrays.asList("Bind", "Clamp", "Fire Spin",
            "Infestation", "Magma Storm", "Sand Tomb", "Whirlpool", "Wrap"));

    // Consecutively executed moves
    public static final Set<String> CONSEC_MOVES = new HashSet<>(
            Arrays.asList("Ice Ball", "Outrage", "Petal Dance", "Rollout", "Thrash", "Uproar"));

    // Moves that modifies a moves type
    public static final Set<String> MOD_MOVE_TYPE_MOVES = new HashSet<>(Arrays.asList("Electrify", "Ion Deluge"));

    // Moves that Drain HP
    public static final Set<String> HP_DRAIN_MOVES = new HashSet<>(Arrays.asList("Absorb", "Bouncy Bubble",
            "Drain Punch", "Draining Kiss", "Dream Eater", "Giga Drain", "Horn Leech", "Leech Life", "Leech Seed",
            "Mega Drain", "Oblivion Wing", "Parabolic Charge"));

    // Moves affected by the user or targets weight
    public static final Set<String> AFFECTED_BY_WEIGHT_MOVES = new HashSet<>(Arrays.asList("Automize", "Grass Knot",
            "Heat Crash", "Heavy Slam", "Low Kick", "Sky Drop"));

    // Moves that affect Abilities

    // Moves that are strong against a target that is Minimized
    public static final Set<String> STRONG_AGAINST_MINIMIZED_MOVES = new HashSet<>(Arrays.asList("Astonish",
            "Body Slam", "Dragon Rush", "Extrasensory", "Flying Press", "Heat Crash", "Heavy Slam",
            "Malicious Moonsault", "Needle Arm", "Phantom Force", "Shadow Force", "Steamroller", "Stomp"));

    // Moves that break Protection
    public static final Set<String> BREAK_PROTECTION_MOVES = new HashSet<>(Arrays.asList("Feint", "Hyperspace Fury",
            "Hyperspace Hole", "Phantom Force", "Shadow Force"));

    // Moves that call other moves
    public static final Set<String> CALLS_OTHER_MOVES_MOVES = new HashSet<>(Arrays.asList("Assist", "Copycat",
            "Me First", "Metronome", "Mirror Move", "Nature Power", "Sleep Talk"));

    // Moves that can heal non volatile status
    public static final Set<String> CAN_HEAL_NVS_MOVES = new HashSet<>(Arrays.asList("Aromatherapy", "Heal Bell",
            "Psycho Shift", "Purify", "Refresh", "Rest", "Scald", "Smelling Salts", "Sparkling Aria", "Steam Eruption",
            "Uproar", "Wake-Up Slap"));

    // Moves that can hit a semi invulnerable target
    public static final Set<String> CAN_HIT_SEMI_INVUL_MOVES = new HashSet<>(Arrays.asList("Earthquake", "Fissure",
            "Gust", "Helping Hand", "Hurricane", "Magnitude", "Sky Uppercut", "Smack Down", "Surf", "Thousand Arrows",
            "Thunder", "Toxic", "Twister", "Whirlpool", "Whirlwind"));

    public static final Set<String> CANNOT_MISS_MOVES = new HashSet<>(Arrays.asList("10,000,000 Volt Thunderbolt",
            "Acid Downpour", "Aerial Ace", "After You", "All-Out Pummeling", "Aura Sphere", "Baddy Bad", "Bestow",
            "Bide", "Black Hole Eclipse", "Blizzard", "Block", "Bloom Doom", "Body Slam", "Bouncy Bubble",
            "Breakneck Blitz", "Buzzy Buzz", "Catastropika", "Clear Smog", "Confide", "Continental Crush", "Conversion 2",
            "Corkscrew Crash", "Defog", "Devastating Drake", "Disarming Voice", "Dragon Rush", "Electrify", "Feint Attack",
            "Floral Healing", "Flying Press", "Foresight", "Freezy Frost", "Gigavolt Havoc", "Glitzy Glow", "Guard Split",
            "Guard Swap", "Guardian of Alola", "Heal Pulse", "Heart Swap", "Heat Crash", "Hurricane", "Hydro Vortex",
            "Hyperspace Fury", "Hyperspace Hole", "Inferno Overdrive", "Lock-On", "Magical Leaf", "Magnet Bomb",
            "Malicious Moonsault", "Me First", "Mean Look", "Mimic", "Mind Reader", "Miracle Eye", "Never-Ending Nightmare",
            "Nightmare", "Noble Roar", "Oceanic Operetta", "Odor Sleuth", "Pain Split", "Phantom Force", "Pika Papow",
            "Play Nice", "Powder", "Power Split", "Power Swap", "Psych Up", "Pulverizing Pancake", "Pursuit",
            "Reflect Type", "Roar", "Role Play", "Sappy Seed", "Savage-Spin Out", "Shadow Force", "Shadow Punch",
            "Shattered Psyche", "Shock Wave", "Sinister Arrow Raid", "Sizzly Slide", "Sketch", "Skill Swap", "Smart Strike",
            "Soul-Stealing 7-Star Strike", "Sparkly Swirl", "Speed Swap", "Spider Web", "Steamroller", "Stoked Sparksurfer",
            "Stomp", "Struggle", "Subzero Slammer", "Supersonic Skystrike", "Swift", "Tectonic Rage", "Telekinesis",
            "Thunder", "Topsy-Turvy", "Toxic", "Transform", "Trump Card", "Twinkle Tackle", "Veevee Volley", "Vital Throw",
            "Whirlwind", "Yawn"));

    // Moves that cause the user to faint
    public static final Set<String> USER_FAINTS_MOVES = new HashSet<>(Arrays.asList("Explosion", "Final Gambit",
            "Healing Wish", "Lunar Dance", "Memento", "Perish Song", "Self-Destruct"));

    // Moves that change a Pokemon's type
    public static final Set<String> CHANGES_POKE_TYPE_MOVES = new HashSet<>(Arrays.asList("Burn Up", "Camouflage",
            "Conversion", "Conversion 2", "Forest's Curse", "Reflect Type", "Roost", "Soak", "Transform", "Trick-or-Treat"));

    // Moves that change the terrain
    public static final Set<String> CHANGE_TERRAIN_MOVES = new HashSet<>(Arrays.asList("Electric Terrain",
            "Genesis Supernova", "Grassy Terrain", "Misty Terrain", "Psychic Terrain"));

    // Moves that can change its type
    public static final Set<String> CAN_CHANGE_TYPE_MOVES = new HashSet<>(Arrays.asList("Hidden Power", "Judgement",
            "Multi-Attack", "Natural Gift", "Revelation Dance", "Techno Blast", "Weather Ball"));

    // Moves that cost HP to use
    public static final Set<String> COST_HP_MOVES = new HashSet<>(Arrays.asList("Belly Drum", "Curse", "Mind Blown",
            "Substitute"));

    // Moves that deal direct damage ignoring the targets stats
    public static final Set<String> DIRECT_DAMAGE_MOVES = new HashSet<>(Arrays.asList("Bide", "Counter", "Dragon Rage",
            "Endeavor", "Final Gambit", "Fissure", "Guardian of Alola", "Guillotine", "Horn Drill", "Metal Burst",
            "Mirror Coat", "Nature's Madness", "Night Shade", "Psywave", "Seismic Toss", "Sheer Cold", "Sonic Boom",
            "Super Fang"));

    // Moves that have recoil damage
    public static final Set<String> RECOIL_MOVES = new HashSet<>(Arrays.asList("Brave Bird", "Double-Edge", "Flare Blitz",
            "Head Charge", "Head Smash", "High Jump Kick", "Jump Kick", "Light of Ruin", "Shadow End", "Shadow Rush",
            "Struggle", "Submission", "Take Down", "Volt Tackle", "Wild Charge", "Wood Hammer"));

    // Moves that deviate from the type chart
    public static final Set<String> DEVIATE_FROM_TYPE_MOVES = new HashSet<>(Arrays.asList("Flying Press",
            "Freeze-Dry", "Thousand Arrows"));

    // Moves that have variable power
    public static final Set<String> VARIABLE_POW_MOVES = new HashSet<>(Arrays.asList("Beat Up", "Crush Grip", "Electro Ball",
            "Eruption", "Flail", "Fling", "Frustration", "Grass Knot", "Gyro Ball", "Heat Crash", "Heavy Slam",
            "Hidden Power", "Low Kick", "Magnitude", "Natural Gift", "Pika Papow", "Power Trip", "Present", "Punishment",
            "Return", "Reversal", "Spit Up", "Stored Power", "Trump Card", "Veevee Volley", "Water Spout", "Wring Out"));

    // Moves that ignore Abilities


    // Moves that make the target the center of attention
    public static final Set<String> TARGET_IS_CENTER_MOVES = new HashSet<>(Arrays.asList("Follow Me", "Rage Powder",
            "Spotlight"));

    // Moves that power up
    public static final Set<String> POWER_UP_MOVES = new HashSet<>(Arrays.asList("Acrobatics", "Assurance", "Avalanche",
            "Brine", "Earthquake", "Echoed Voice", "Facade", "Fire Pledge", "Fury Cutter", "Fusion Bolt", "Fusion Flare",
            "Grass Pledge", "Gust", "Hex", "Ice Ball", "Magnitude", "Malicious Moonsault", "Payback", "Pursuit",
            "Retaliate", "Revenge", "Rollout", "Round", "Smelling Salts", "Stomp", "Stomping Tantrum", "Surf",
            "Triple Kick", "Twister", "Venoshock", "Wake-Up Slap", "Water Pledge", "Weather Ball", "Whirlpool"));

    // Moves that removes some type immunities
    public static final Set<String> REMOVE_IMMUNITY_MOVES = new HashSet<>(Arrays.asList("Foresight", "Gravity",
            "Miracle Eye", "Odor Sleuth", "Smack Down", "Thousand Arrows"));

    // Moves that require recharging after using
    public static final Set<String> RECHARGE_MOVES = new HashSet<>(Arrays.asList("Blast Burn", "Frenzy Plant",
            "Giga Impact", "Hydro Cannon", "Hyper Beam", "Prismatic Laser", "Roar of Time", "Rock Wrecker", "Shadow Half"));

    // Moves that restore HP
    public static final Set<String> RESTORE_HP_MOVES = new HashSet<>(Arrays.asList("Aqua Ring", "Floral Healing",
            "Grassy Terrain", "Heal Pulse", "Healing Wish", "Ingrain", "Leech Seed", "Lunar Dance", "Pain Split",
            "Pollen Puff", "Present", "Wish"));

    // Moves that switches the user/target out
    public static final Set<String> SWITCH_MOVES = new HashSet<>(Arrays.asList("Baton Pass", "Circle Throw", "Dragon Tail",
            "Parting Shot", "Roar", "Teleport", "U-turn", "Volt Switch", "Whirlwind"));

    // Moves that can thaw the user out
    public static final Set<String> CAN_THAW_USER_MOVES = new HashSet<>(Arrays.asList("Burn Up", "Flame Wheel",
            "Flare Blitz", "Fusion Flare", "Sacred Fire", "Scald", "Steam Eruption"));

    // Moves that uses stats from different categories
    public static final Set<String> DIFF_STATS_MOVES = new HashSet<>(Arrays.asList("Light That Burns The Sky",
            "Photon Geyser", "Psyshock", "Psystrike", "Secret Sword"));

    // Moves that vary with the environment
    public static final Set<String> VARY_ENV_MOVES = new HashSet<>(Arrays.asList("Camouflage", "Nature Power", "Secret Power"));

    // Moves that have a charging turn
    public static final Set<String> CHARGING_MOVES = new HashSet<>(Arrays.asList("Bounce", "Dig", "Dive", "Fly",
            "Freeze Shock", "Geomancy", "Ice Burn", "Phantom Force", "Razor Wind", "Shadow Force", "Skull Bash",
            "Sky Attack", "Sky Drop", "Solar Beam", "Solar Blade"));

    // Moves with a high crit ratio
    public static final Set<String> HIGH_CRIT_MOVES = new HashSet<>(Arrays.asList("10,000,000 Volt Thunderbolt",
            "Aeroblast", "Air Cutter", "Attack Order", "Blaze Kick", "Crabhammer", "Cross Chop", "Cross Poison",
            "Drill Run", "Karate Chop", "Leaf Blade", "Night Slash", "Poison Tail", "Psycho Cut", "Razor Leaf",
            "Razor Wind", "Shadow Blast", "Shadow Claw", "Sky Attack", "Slash", "Spacial Rend", "Stone Edge"));

    // Moves with a perfect crit ratio
    public static final Set<String> PERFECT_CRIT_MOVES = new HashSet<>(Arrays.asList("Frost Breath", "Storm Throw",
            "Zippy Zap"));

    // Moves wit ha semi invulnerable turn
    public static final Set<String> SEMI_INV_MOVES = new HashSet<>(Arrays.asList("Bounce", "Dig", "Dive", "Fly",
            "Phantom Force", "Shadow Force", "Sky Drop"));

    // Moves with no effect
    public static final Set<String> NO_EFFECT_MOVES = new HashSet<>(Arrays.asList("Celebrate", "Holding Hands", "Splash"));

    // Moves that hit for multiple times (Multi strike)
    public static final Set<String> MULTI_STRIKE_MOVES = new HashSet<>(Arrays.asList("Arm Thrust", "Barrage", "Beat Up",
            "Bone Rush", "Bonemerang", "Bullet Seed", "Comet Punch", "Double Hit", "Double Iron Bash", "Double Kick",
            "Double Slap", "Dual Chop", "Fury Attack", "Fury Swipes", "Gear Grind", "Icicle Spear", "Pin Missile",
            "Rock Blast", "Spike Cannon", "Tail Slap", "Triple Kick", "Twineedle", "Water Shuriken"));

    // Moves that One Hit KO
    public static final Set<String> OHKO_MOVES = new HashSet<>(Arrays.asList("Fissure", "Guillotine", "Horn Drill",
            "Sheer Cold"));

    // Moves that Protect
    public static final Set<String> PROTECTION_MOVES = new HashSet<>(Arrays.asList("Baneful Bunker", "Crafty Shield",
            "Detect", "King's Shield", "Mat Block", "Protect", "Quick Guard", "Spiky Shield", "Wide Guard"));

    // Moves that have a set damage
    public static final Set<String> SET_DMG_MOVES = new HashSet<>(Arrays.asList("Dragon Rage", "Sonic Boom"));

    // Moves that heal the user immediately
    public static final Set<String> HEALS_IMMED_MOVES = new HashSet<>(Arrays.asList("Heal Order", "Milk Drink",
            "Moonlight", "Morning Sun", "Purify", "Recover", "Rest", "Roost", "Shore Up", "Slack Off", "Soft-Boiled",
            "Strength Sap", "Synthesis"));

    // Moves that trap the target
    public static final Set<String> TRAPPING_MOVES = new HashSet<>(Arrays.asList("Anchor Shot", "Block", "Fairy Lock",
            "Ingrain", "Mean Look", "Shadow Hold", "Spider Web", "Spirit Shackle", "Thousand Waves"));

    // Moves that change the weather
    public static final Set<String> WEATHER_MOVES = new HashSet<>(Arrays.asList("Defog", "Hail", "Rain Dance",
            "Sandstorm", "Shadow Sky", "Sunny Day"));

    // Moves that cause entry hazards
    public static final Set<String> HAZARD_MOVES = new HashSet<>(Arrays.asList("Spikes", "Stealth Rock", "Sticky Web",
            "Toxic Spikes"));
}
