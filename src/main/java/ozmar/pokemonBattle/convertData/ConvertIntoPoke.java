package ozmar.pokemonBattle.convertData;

import javafx.util.Pair;
import ozmar.PokemonPoke;
import ozmar.enums.PokemonGender;
import ozmar.pokemonBattle.PokeInfoHelper;
import ozmar.pokemonBattle.pokemon.Poke;
import ozmar.pokemonBattle.pokemonMoves.PokeMove;
import ozmar.pokemonBattle.pokemonNature.PokeNatureEnum;
import ozmar.pokemonBattle.pokemonStats.PokeAllStats;
import ozmar.pokemonBattle.pokemonStats.enums.PokeStat;
import ozmar.pokemonBattle.pokemonType.PokeType;
import ozmar.pokemonBattle.pokemonType.PokeTypeEnum;
import poke_models.pokemon.Pokemon;
import poke_models.pokemon.PokemonStat;
import poke_models.pokemon.PokemonType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConvertIntoPoke {

    public ConvertIntoPoke() {

    }

    public Poke getPoke(@Nonnull PokemonPoke pokemonPoke) {
        Pokemon pokemon = Pokemon.getByName(pokemonPoke.getPokemonName());
        int id = pokemon.getId();
        int level = 100;
        String name = pokemonPoke.getPokemonName();
        int height = pokemon.getHeight();
        int weight = pokemon.getWeight();
        Pair<PokeTypeEnum, PokeTypeEnum> types = getTypes(pokemon);
        PokeType pokeType = new PokeType(types.getKey(), types.getValue());
        PokeNatureEnum natureEnum = PokeNatureEnum.getEnum(pokemonPoke.getNature());
        PokeAllStats pokeAllStats = getAllStats(pokemon, natureEnum);
        List<PokeMove> moveList = new ArrayList<>();

        return new Poke(id, level, name, name, height, weight, pokeType, natureEnum, pokeAllStats, moveList);
    }

    @Nonnull
    private PokeAllStats getAllStats(@Nonnull Pokemon pokemon, @Nonnull PokeNatureEnum natureEnum) {
        List<PokemonStat> statList = pokemon.getStats();

        int hp = PokeInfoHelper.calculatePokeHp(statList.get(5).getBaseStat());
        int attack = PokeInfoHelper.calculateOtherStats(statList.get(4).getBaseStat(), PokeStat.ATK, natureEnum);
        int defense = PokeInfoHelper.calculateOtherStats(statList.get(3).getBaseStat(), PokeStat.DEF, natureEnum);
        int specialAttack = PokeInfoHelper.calculateOtherStats(statList.get(2).getBaseStat(), PokeStat.SPC_ATK, natureEnum);
        int specialDefense = PokeInfoHelper.calculateOtherStats(statList.get(1).getBaseStat(), PokeStat.SPC_DEF, natureEnum);
        int speed = PokeInfoHelper.calculateOtherStats(statList.get(0).getBaseStat(), PokeStat.SPD, natureEnum);

        return new PokeAllStats(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    @Nonnull
    private Pair<PokeTypeEnum, PokeTypeEnum> getTypes(@Nonnull Pokemon pokemon) {
        PokeTypeEnum pokeType1 = null;
        PokeTypeEnum pokeType2 = null;
        List<PokemonType> typeList = pokemon.getTypes();

        for (PokemonType type : typeList) {
            int slot = type.getSlot();

            String stringType = type.getType().getUrl();
            stringType = stringType.substring(stringType.length() - 3).replaceAll("/", "");

            if (slot == 1) {
                pokeType1 = PokeTypeEnum.types[Integer.parseInt(stringType)];
            } else {
                pokeType2 = PokeTypeEnum.types[Integer.parseInt(stringType)];
            }
        }

        if (pokeType2 == null) {
            pokeType2 = PokeTypeEnum.NONE;
        }

        assert pokeType1 != null;
        return new Pair<>(pokeType1, pokeType2);
    }

    // TODO: Temp method
    public Poke createPoke(@Nonnull String pokemonName) {
        PokemonPoke pokemonPoke = new PokemonPoke();
        pokemonPoke.setId(1);
        pokemonPoke.setPokemonSpecies(pokemonName);
        pokemonPoke.setPokemonName(pokemonName);
        pokemonPoke.setPokemonNickName(pokemonName);
        pokemonPoke.setShiny(true);
        pokemonPoke.setGender(PokemonGender.MALE);
        pokemonPoke.setNature("adamant");
        pokemonPoke.setPokemonMoves(new HashSet<>());
        return getPoke(pokemonPoke);
    }
}
