package ozmar.pokemonBattle;

import ozmar.PokemonPoke;
import ozmar.enums.PokemonGender;
import ozmar.pokemonBattle.convertData.ConvertIntoPoke;
import ozmar.pokemonBattle.convertData.GetMovesData;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonTrainer.Trainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class meant to get a default trainers for 1v1 battles
 * Used for testing
 */
public class DefaultTrainer {
    ConvertIntoPoke convert;
    GetMovesData movesData;

    private List<String> defaultNames1 = new ArrayList<>(Arrays.asList("bulbasaur", "charmander", "squirtle",
            "chikorita", "cyndaquil", "totodile"));

    private List<String> defaultNames2 = new ArrayList<>(Arrays.asList("treecko", "torchic", "mudkip",
            "turtwig", "chimchar", "piplup"));

    private List<String> moveNames = new ArrayList<>(Arrays.asList("Pound", "Quick Attack", "Swift", "Scratch"));

    public DefaultTrainer() {
        convert = new ConvertIntoPoke();
        movesData = new GetMovesData();
    }

    public Trainer redTrainer(long id) {
        List<PokemonPoke> pokemonPokeList = new ArrayList<>();
        for (String s : defaultNames1) {
            pokemonPokeList.add(createPokemonPoke(s));
        }
        List<Poke> pokeList = convertIntoPoke(pokemonPokeList);

        Trainer trainer = new Trainer(id, "RED", pokeList);
        setMoves(trainer);

        return trainer;
    }

    public Trainer blueTrainer(long id) {
        List<PokemonPoke> pokemonPokeList = new ArrayList<>();
        for (String s : defaultNames2) {
            pokemonPokeList.add(createPokemonPoke(s));
        }
        List<Poke> pokeList = convertIntoPoke(pokemonPokeList);

        Trainer trainer = new Trainer(id, "BLUE", pokeList);
        setMoves(trainer);

        return trainer;
    }

    @Nonnull
    private PokemonPoke createPokemonPoke(@Nonnull String name) {
        PokemonPoke pokemonPoke = new PokemonPoke();
        pokemonPoke.setId(1);
        pokemonPoke.setPokemonSpecies(name);
        pokemonPoke.setPokemonName(name);
        pokemonPoke.setPokemonNickName(name);
        pokemonPoke.setShiny(true);
        pokemonPoke.setGender(PokemonGender.MALE);
        pokemonPoke.setNature("adamant");
        pokemonPoke.setPokemonMoves(new ArrayList<>());
        return pokemonPoke;
    }

    @Nonnull
    private List<Poke> convertIntoPoke(@Nonnull List<PokemonPoke> list) {
        List<Poke> pokeList = new ArrayList<>();
        for (PokemonPoke pokemonPoke : list) {
            pokeList.add(convert.getPoke(pokemonPoke.getPokemonName(), pokemonPoke.getNature()));
        }

        return pokeList;
    }

    private void setMoves(Trainer trainer) {
        for (int i = 0; i < 6; i++) {
            setPokeMoves(trainer, i, moveNames);
        }
    }

    public void setPokeMoves(@Nonnull Trainer trainer, int pokePosition, @Nonnull List<String> moveList) {
        List<PokeMove> pokeMoves = movesData.convertNamesToMoves(moveList);
        List<PokeMove> currMoves = trainer.getPokeList().get(pokePosition).getMoveList();
        currMoves.clear();
        currMoves.addAll(pokeMoves);
    }
}
