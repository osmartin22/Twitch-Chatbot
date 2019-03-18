package ozmar.PokemonBattle;

import ozmar.PokemonBattle.PokemonField.PokeField;
import ozmar.PokemonBattle.PokemonField.PokeSide;

// Pursuit is the only move that goes before the target switches out
public class PokeBattle {
    Trainer red;
    PokeSide redSide;

    Trainer blue;
    PokeSide blueSide;

    PokeField field;

    // Who's turn


    public PokeBattle(Trainer red, Trainer blue) {
        this.red = red;
        this.blue = blue;
        this.redSide = new PokeSide();
        this.blueSide = new PokeSide();
        this.field = new PokeField();
    }

/*
Sequence of events
Users select there Pokemon along with the first Pokemon to send out

PokeBattle object is created containing the above information

Starting at this point, the battle starts

    Entering Pokemon are damage by Entry Hazards (Only for Pokemon that are switching in)

    WAIT FOR MOVE
    Trainers are asked what they will do (switch Pokemon, select a move, get info pertaining to the battle)

    Stay at WAIT FOR MOVE until both users switch their Pokemon or select a move
    Asking for more info will tell the user the info ans still wait for the Trainer's decision

    BATTLE PHASE
    If a Trainer switches out their Pokemon, this will execute first before any moves (Pursuit is a special move that can by pass this)
    The entering Pokemon will then take damage from Entry Hazards if present
    The other Pokemon will then attack if it selected a move, or switch out and follow the above

    If both Pokemon selected a move
    Calculate who will go first
    Pokemon going first will then do their move
    Move effects will then occur
    Pokemon going second will then do their move
    Move effects will then occur

    POST BATTLE PHASE
    After both Pokemon have switched or done their move
    Status effects occur, e.g. burn status effect burns the target
    Other one turn effects are removed, e.g. protection moves disappear

    Goes back to waiting for user input -> WAIT FOR MOVE
 */

/*
Switching a Pokemon will always go first (Pursuit can hit first if it targets the Poke switching out

Check for Priority of the moves before deciding who will go first

Check if the move is affected from effects on the user/target Poke or on the field
e.g. Moves that are amplified in power or moves that the target Pokemon is immune to

Check if the move is prevented from effects on the user or target poke or on the field
 */
}
