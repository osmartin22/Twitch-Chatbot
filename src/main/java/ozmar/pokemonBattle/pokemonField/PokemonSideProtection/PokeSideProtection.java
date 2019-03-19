package ozmar.pokemonBattle.pokemonField.PokemonSideProtection;

import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonMoves.PokeMoveDamageClass;
import ozmar.pokemonBattle.pokemonMoves.PokeTarget;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO: Lower chances of moves below to succeed consecutively
// Protect, Detect, Endure, King's Shield, and Spiky Shield.

public class PokeSideProtection {
    private final Set<PokeSideProtectionEnum> sideProtectionSet;
    private final Set<String> movesThatByPassProtection;

    public PokeSideProtection() {
        this.sideProtectionSet = new HashSet<>();
        this.movesThatByPassProtection = new HashSet<>(Arrays.asList("Feint", "Hyperspace Fury", "Hyperspace Hole",
                "Phantom Force", "Shadow Force"));
    }

    public boolean isProtected(PokeMove move) {
        if (movesThatByPassProtection.contains(move.getName())) {
            sideProtectionSet.clear();
            return false;
        } else {
            if (sideProtectionSet.contains(PokeSideProtectionEnum.CRAFTY_SHIELD) && isProtectedByCraftyShield(move)) {
                return true;
            }
            if (sideProtectionSet.contains(PokeSideProtectionEnum.MAT_BLOCK) && isProtectedByMatBlock(move)) {
                return true;
            }
            if (sideProtectionSet.contains(PokeSideProtectionEnum.QUICK_GUARD) && isProtectedByQuickGuard(move)) {
                return true;
            }
            if (sideProtectionSet.contains(PokeSideProtectionEnum.WIDE_GUARD) && isProtectedByWideGuard(move)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the passed in move is protected
     * Craft Shield protects against status moves
     * Moves contained in movesThatByPassProtection remove the effects of Crafty Shield
     *
     * @param move move to check for protection
     * @return boolean
     */
    private boolean isProtectedByCraftyShield(@Nonnull PokeMove move) {
        boolean isProtected;
        boolean isStatus = move.getDamageClass() == PokeMoveDamageClass.STATUS;
        if (isStatus) {
            String moveName = move.getName();
            isProtected = !moveName.equals("Perish Song") && !moveName.equals("Rototiller") && !moveName.equals("Flower Shield");
        } else {
            isProtected = false;
        }

        return isProtected;
    }

    /**
     * Checks if the passed in move is protected
     * Mat Block protects from all moves except Status Moves
     * Moves contained in movesThatByPassProtection remove the effects of Crafty Shield
     *
     * @param move move to check for protection
     * @return boolean
     */
    private boolean isProtectedByMatBlock(@Nonnull PokeMove move) {
        boolean isProtected;
        isProtected = move.getDamageClass() != PokeMoveDamageClass.STATUS;
        return isProtected;
    }

    private boolean isProtectedByQuickGuard(@Nonnull PokeMove move) {
        boolean isProtected;
        isProtected = move.getPriority() > 0;

        return isProtected;
    }

    private boolean isProtectedByWideGuard(@Nonnull PokeMove move) {
        boolean isProtected;
        isProtected = move.getMoveTarget() == PokeTarget.ALL_POKE;
        return isProtected;
    }

    /*
    Crafty Shield
        Protects from status moves
        Does not protect from damage moves or moves that target the user
        Feint, Hyperspace Fury, Hyperspace Hole, Shadow Force, and Phantom Force lift the effect of Crafty Shield from the target's team
     */

    /*
    Mat Block
    Can only be used on the 1st turn after a Pokemon switches in (must switch out and back in to reuse)
    Protects from any moves except status moves
    Feint, Hyperspace Fury, Hyperspace Hole, Phantom Force, and Shadow Force will hit regardless and lift the effect
    Cannot protect against Future Sight or Doom Desire
     */

    /*
    Quick Guard
    Protects from all moves that have an increased priority
    Cannot block any moves that would normally bypass Protect, Detect and Spiky Shield
    Using it successfully decreases the chance of success for Protect, Detect, Endure, King's Shield, and Spiky Shield
    Feint, Hyperspace Fury, Hyperspace Hole, Phantom Force, and Shadow Force will hit regardless and lift the effect
     */

    /*
    Wide Guard
    Protects user's side from all attacks that target multiple Pokemon
    Using it successfully decreases the chance of success for Protect, Detect, Endure, King's Shield, and Spiky Shield
    Feint, Hyperspace Fury, Hyperspace Hole, Phantom Force, and Shadow Force will hit regardless and lift the effect
     */
}
