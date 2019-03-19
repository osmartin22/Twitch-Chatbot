package ozmar.commands;

import ozmar.pokemonBattle.pokemonField.PokeTrainerSide;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;

import javax.annotation.Nonnull;
import java.util.*;

// TODO: Temp location of commands so the bot can interact with the PokeBattle package so that
//  the package can be used separately outside of the bot
public class PokeBattleCommand {

    public Map<Integer, String> getListOfCurrPokeMoves(PokeTrainerSide trainerSide) {
        List<PokeMove> moveList = trainerSide.getTrainerInBattle().getCurrPokeMoves();
        return getMovesInMap(getMoveNamesRandomly(moveList));
    }

    private List<String> getMoveNamesRandomly(@Nonnull List<PokeMove> list) {
        List<String> moveNamesList = new ArrayList<>();
        for (PokeMove move : list) {
            moveNamesList.add(move.getName());
        }
        moveNamesList.add("Switch Poke");
        Collections.shuffle(moveNamesList);
        return moveNamesList;
    }


    private Map<Integer, String> getMovesInMap(@Nonnull List<String> list) {
        Map<Integer, String> moveMap = new HashMap<>();
        moveMap.put(1, list.get(0));
        moveMap.put(2, list.get(1));
        moveMap.put(3, list.get(2));
        moveMap.put(4, list.get(3));
        moveMap.put(5, list.get(4));
        return moveMap;
    }
}
