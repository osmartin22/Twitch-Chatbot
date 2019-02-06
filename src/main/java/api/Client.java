package api;

import models.berries.BerryFirmness;
import models.contests.SuperContestEffect;
import models.games.Pokedex;
import models.games.VersionGroup;
import models.locations.Location;
import models.locations.Region;
import models.moves.Move;
import models.moves.MoveAilment;
import models.moves.MoveTarget;
import models.pokemon.*;
import models.resource.APIResourceList;
import models.resource.NamedAPIResourceList;
import models.utility.Language;

// heart of wrapper, contains all possible api calls
public class Client {

    // if true, save api response to local database for speed and api call limit help
    public static boolean CACHE = true;

    // get a Gender instance by the id
    public models.pokemon.Gender getGenderById(int id) {
        return models.pokemon.Gender.getById(id);
    }

    // get a Gender instance by the name
    public models.pokemon.Gender getGenderByName(String name) {
        return models.pokemon.Gender.getByName(name);
    }

    // get Gender list
    public models.resource.NamedAPIResourceList getGenderList(int limit, int offset) {
        return models.pokemon.Gender.getList(limit, offset);
    }

    // get a Item instance by the id
    public models.items.Item getItemById(int id) {
        return models.items.Item.getById(id);
    }

    // get a Item instance by the name
    public models.items.Item getItemByName(String name) {
        return models.items.Item.getByName(name);
    }

    // get Item list
    public models.resource.NamedAPIResourceList getItemList(int limit, int offset) {
        return models.items.Item.getList(limit, offset);
    }

    // get a ItemCategory instance by the id
    public models.items.ItemCategory getItemCategoryById(int id) {
        return models.items.ItemCategory.getById(id);
    }

    // get a ItemCategory instance by the name
    public models.items.ItemCategory getItemCategoryByName(String name) {
        return models.items.ItemCategory.getByName(name);
    }

    // get ItemCategory list
    public models.resource.NamedAPIResourceList getItemCategoryList(int limit, int offset) {
        return models.items.ItemCategory.getList(limit, offset);
    }

    // get a MoveDamageClass instance by the id
    public models.moves.MoveDamageClass getMoveDamageClassById(int id) {
        return models.moves.MoveDamageClass.getById(id);
    }

    // get a MoveDamageClass instance by the name
    public models.moves.MoveDamageClass getMoveDamageClassByName(String name) {
        return models.moves.MoveDamageClass.getByName(name);
    }

    // get MoveDamageClass list
    public models.resource.NamedAPIResourceList getMoveDamageClassList(int limit, int offset) {
        return models.moves.MoveDamageClass.getList(limit, offset);
    }

    // get a EncounterMethod instance by the id
    public models.encounters.EncounterMethod getEncounterMethodById(int id) {
        return models.encounters.EncounterMethod.getById(id);
    }

    // get a EncounterMethod instance by the name
    public models.encounters.EncounterMethod getEncounterMethodByName(String name) {
        return models.encounters.EncounterMethod.getByName(name);
    }

    // get EncounterMethod list
    public models.resource.NamedAPIResourceList getEncounterMethodList(int limit, int offset) {
        return models.encounters.EncounterMethod.getList(limit, offset);
    }

    // get a BerryFlavor instance by the id
    public models.berries.BerryFlavor getBerryFlavorById(int id) {
        return models.berries.BerryFlavor.getById(id);
    }

    // get a BerryFlavor instance by the name
    public models.berries.BerryFlavor getBerryFlavorByName(String name) {
        return models.berries.BerryFlavor.getByName(name);
    }

    // get BerryFlavor list
    public models.resource.NamedAPIResourceList getBerryFlavorList(int limit, int offset) {
        return models.berries.BerryFlavor.getList(limit, offset);
    }

    // get a EvolutionChain instance by the id
    public models.evolution.EvolutionChain getEvolutionChainById(int id) {
        return models.evolution.EvolutionChain.getById(id);
    }

    // get EvolutionChain list
    public models.resource.NamedAPIResourceList getEvolutionChainList(int limit, int offset) {
        return models.evolution.EvolutionChain.getList(limit, offset);
    }

    // get a PokemonShape instance by the id
    public models.pokemon.PokemonShape getPokemonShapeById(int id) {
        return models.pokemon.PokemonShape.getById(id);
    }

    // get a PokemonShape instance by the name
    public models.pokemon.PokemonShape getPokemonShapeByName(String name) {
        return models.pokemon.PokemonShape.getByName(name);
    }

    // get PokemonShape list
    public models.resource.NamedAPIResourceList getPokemonShapeList(int limit, int offset) {
        return models.pokemon.PokemonShape.getList(limit, offset);
    }

    // get a Berry instance by the id
    public models.berries.Berry getBerryById(int id) {
        return models.berries.Berry.getById(id);
    }

    // get a Berry instance by the name
    public models.berries.Berry getBerryByName(String name) {
        return models.berries.Berry.getByName(name);
    }

    // get Berry list
    public models.resource.NamedAPIResourceList getBerryList(int limit, int offset) {
        return models.berries.Berry.getList(limit, offset);
    }

    // get a PokeathlonStat instance by the id
    public models.pokemon.PokeathlonStat getPokeathlonStatById(int id) {
        return models.pokemon.PokeathlonStat.getById(id);
    }

    // get a PokeathlonStat instance by the name
    public models.pokemon.PokeathlonStat getPokeathlonStatByName(String name) {
        return models.pokemon.PokeathlonStat.getByName(name);
    }

    // get PokeathlonStat list
    public models.resource.NamedAPIResourceList getPokeathlonStatList(int limit, int offset) {
        return models.pokemon.PokeathlonStat.getList(limit, offset);
    }

    // get a ItemFlingEffect instance by the id
    public models.items.ItemFlingEffect getItemFlingEffectById(int id) {
        return models.items.ItemFlingEffect.getById(id);
    }

    // get a ItemFlingEffect instance by the name
    public models.items.ItemFlingEffect getItemFlingEffectByName(String name) {
        return models.items.ItemFlingEffect.getByName(name);
    }

    // get ItemFlingEffect list
    public models.resource.NamedAPIResourceList getItemFlingEffectList(int limit, int offset) {
        return models.items.ItemFlingEffect.getList(limit, offset);
    }

    // get a Generation instance by the id
    public models.games.Generation getGenerationById(int id) {
        return models.games.Generation.getById(id);
    }

    // get a Generation instance by the name
    public models.games.Generation getGenerationByName(String name) {
        return models.games.Generation.getByName(name);
    }

    // get Generation list
    public models.resource.NamedAPIResourceList getGenerationList(int limit, int offset) {
        return models.games.Generation.getList(limit, offset);
    }

    // get a GrowthRate instance by the id
    public models.pokemon.GrowthRate getGrowthRateById(int id) {
        return models.pokemon.GrowthRate.getById(id);
    }

    // get a GrowthRate instance by the name
    public models.pokemon.GrowthRate getGrowthRateByName(String name) {
        return models.pokemon.GrowthRate.getByName(name);
    }

    // get GrowthRate list
    public models.resource.NamedAPIResourceList getGrowthRateList(int limit, int offset) {
        return models.pokemon.GrowthRate.getList(limit, offset);
    }

    // get a ItemPocket instance by the id
    public models.items.ItemPocket getItemPocketById(int id) {
        return models.items.ItemPocket.getById(id);
    }

    // get a ItemPocket instance by the name
    public models.items.ItemPocket getItemPocketByName(String name) {
        return models.items.ItemPocket.getByName(name);
    }

    // get ItemPocket list
    public models.resource.NamedAPIResourceList getItemPocketList(int limit, int offset) {
        return models.items.ItemPocket.getList(limit, offset);
    }

    // get a ItemAttribute instance by the id
    public models.items.ItemAttribute getItemAttributeById(int id) {
        return models.items.ItemAttribute.getById(id);
    }

    // get a ItemAttribute instance by the name
    public models.items.ItemAttribute getItemAttributeByName(String name) {
        return models.items.ItemAttribute.getByName(name);
    }

    // get ItemAttribute list
    public models.resource.NamedAPIResourceList getItemAttributeList(int limit, int offset) {
        return models.items.ItemAttribute.getList(limit, offset);
    }

    // get a MoveCategory instance by the id
    public models.moves.MoveCategory getMoveCategoryById(int id) {
        return models.moves.MoveCategory.getById(id);
    }

    // get a MoveCategory instance by the name
    public models.moves.MoveCategory getMoveCategoryByName(String name) {
        return models.moves.MoveCategory.getByName(name);
    }

    // get MoveCategory list
    public models.resource.NamedAPIResourceList getMoveCategoryList(int limit, int offset) {
        return models.moves.MoveCategory.getList(limit, offset);
    }

    // get a EncounterCondition instance by the id
    public models.encounters.EncounterCondition getEncounterConditionById(int id) {
        return models.encounters.EncounterCondition.getById(id);
    }

    // get a EncounterCondition instance by the name
    public models.encounters.EncounterCondition getEncounterConditionByName(String name) {
        return models.encounters.EncounterCondition.getByName(name);
    }

    // get EncounterCondition list
    public models.resource.NamedAPIResourceList getEncounterConditionList(int limit, int offset) {
        return models.encounters.EncounterCondition.getList(limit, offset);
    }

    // get a ContestEffect instance by the id
    public models.contests.ContestEffect getContestEffectById(int id) {
        return models.contests.ContestEffect.getById(id);
    }

    // get ContestEffect list
    public models.resource.NamedAPIResourceList getContestEffectList(int limit, int offset) {
        return models.contests.ContestEffect.getList(limit, offset);
    }

    // get a LocationArea instance by the id
    public models.locations.LocationArea getLocationAreaById(int id) {
        return models.locations.LocationArea.getById(id);
    }

    // get LocationArea list
    public models.resource.NamedAPIResourceList getLocationAreaList(int limit, int offset) {
        return models.locations.LocationArea.getList(limit, offset);
    }

    // get a Machine instance by the id
    public models.machines.Machine getMachineById(int id) {
        return models.machines.Machine.getById(id);
    }

    // get Machine list
    public models.resource.NamedAPIResourceList getMachineList(int limit, int offset) {
        return models.machines.Machine.getList(limit, offset);
    }

    // get a Stat instance by the id
    public models.pokemon.Stat getStatById(int id) {
        return models.pokemon.Stat.getById(id);
    }

    // get a Stat instance by the name
    public models.pokemon.Stat getStatByName(String name) {
        return models.pokemon.Stat.getByName(name);
    }

    // get Stat list
    public models.resource.NamedAPIResourceList getStatList(int limit, int offset) {
        return models.pokemon.Stat.getList(limit, offset);
    }

    // get a MoveLearnMethod instance by the id
    public models.moves.MoveLearnMethod getMoveLearnMethodById(int id) {
        return models.moves.MoveLearnMethod.getById(id);
    }

    // get a MoveLearnMethod instance by the name
    public models.moves.MoveLearnMethod getMoveLearnMethodByName(String name) {
        return models.moves.MoveLearnMethod.getByName(name);
    }

    // get MoveLearnMethod list
    public models.resource.NamedAPIResourceList getMoveLearnMethodList(int limit, int offset) {
        return models.moves.MoveLearnMethod.getList(limit, offset);
    }

    // get a PokemonHabitat instance by the id
    public models.pokemon.PokemonHabitat getPokemonHabitatById(int id) {
        return models.pokemon.PokemonHabitat.getById(id);
    }

    // get a PokemonHabitat instance by the name
    public models.pokemon.PokemonHabitat getPokemonHabitatByName(String name) {
        return models.pokemon.PokemonHabitat.getByName(name);
    }

    // get PokemonHabitat list
    public models.resource.NamedAPIResourceList getPokemonHabitatList(int limit, int offset) {
        return models.pokemon.PokemonHabitat.getList(limit, offset);
    }

    // get a Nature instance by the id
    public models.pokemon.Nature getNatureById(int id) {
        return models.pokemon.Nature.getById(id);
    }

    // get a Nature instance by the name
    public models.pokemon.Nature getNatureByName(String name) {
        return models.pokemon.Nature.getByName(name);
    }

    // get Nature list
    public models.resource.NamedAPIResourceList getNatureList(int limit, int offset) {
        return models.pokemon.Nature.getList(limit, offset);
    }

    // get a PalParkArea instance by the id
    public models.locations.PalParkArea getPalParkAreaById(int id) {
        return models.locations.PalParkArea.getById(id);
    }

    // get a PalParkArea instance by the name
    public models.locations.PalParkArea getPalParkAreaByName(String name) {
        return models.locations.PalParkArea.getByName(name);
    }

    // get PalParkArea list
    public models.resource.NamedAPIResourceList getPalParkAreaList(int limit, int offset) {
        return models.locations.PalParkArea.getList(limit, offset);
    }

    // get a EncounterConditionValue instance by the id
    public models.encounters.EncounterConditionValue getEncounterConditionValueById(int id) {
        return models.encounters.EncounterConditionValue.getById(id);
    }

    // get a EncounterConditionValue instance by the name
    public models.encounters.EncounterConditionValue getEncounterConditionValueByName(String name) {
        return models.encounters.EncounterConditionValue.getByName(name);
    }

    // get EncounterConditionValue list
    public models.resource.NamedAPIResourceList getEncounterConditionValueList(int limit, int offset) {
        return models.encounters.EncounterConditionValue.getList(limit, offset);
    }

    // get a Type instance by the id
    public models.pokemon.Type getTypeById(int id) {
        return models.pokemon.Type.getById(id);
    }

    // get a Type instance by the name
    public models.pokemon.Type getTypeByName(String name) {
        return models.pokemon.Type.getByName(name);
    }

    // get Type list
    public models.resource.NamedAPIResourceList getTypeList(int limit, int offset) {
        return models.pokemon.Type.getList(limit, offset);
    }

    // get a PokemonForm instance by the id
    public models.pokemon.PokemonForm getPokemonFormById(int id) {
        return models.pokemon.PokemonForm.getById(id);
    }

    // get a PokemonForm instance by the name
    public models.pokemon.PokemonForm getPokemonFormByName(String name) {
        return models.pokemon.PokemonForm.getByName(name);
    }

    // get PokemonForm list
    public models.resource.NamedAPIResourceList getPokemonFormList(int limit, int offset) {
        return models.pokemon.PokemonForm.getList(limit, offset);
    }

    // get a ContestType instance by the id
    public models.contests.ContestType getContestTypeById(int id) {
        return models.contests.ContestType.getById(id);
    }

    // get a ContestType instance by the name
    public models.contests.ContestType getContestTypeByName(String name) {
        return models.contests.ContestType.getByName(name);
    }

    // get ContestType list
    public models.resource.NamedAPIResourceList getContestTypeList(int limit, int offset) {
        return models.contests.ContestType.getList(limit, offset);
    }

    // get a EvolutionTrigger instance by the id
    public models.evolution.EvolutionTrigger getEvolutionTriggerById(int id) {
        return models.evolution.EvolutionTrigger.getById(id);
    }

    // get a EvolutionTrigger instance by the name
    public models.evolution.EvolutionTrigger getEvolutionTriggerByName(String name) {
        return models.evolution.EvolutionTrigger.getByName(name);
    }

    // get EvolutionTrigger list
    public models.resource.NamedAPIResourceList getEvolutionTriggerList(int limit, int offset) {
        return models.evolution.EvolutionTrigger.getList(limit, offset);
    }

    // get a MoveBattleStyle instance by the id
    public models.moves.MoveBattleStyle getMoveBattleStyleById(int id) {
        return models.moves.MoveBattleStyle.getById(id);
    }

    // get a MoveBattleStyle instance by the name
    public models.moves.MoveBattleStyle getMoveBattleStyleByName(String name) {
        return models.moves.MoveBattleStyle.getByName(name);
    }

    // get MoveBattleStyle list
    public models.resource.NamedAPIResourceList getMoveBattleStyleList(int limit, int offset) {
        return models.moves.MoveBattleStyle.getList(limit, offset);
    }

    // get a Version instance by the id
    public models.games.Version getVersionById(int id) {
        return models.games.Version.getById(id);
    }

    // get a Version instance by the name
    public models.games.Version getVersionByName(String name) {
        return models.games.Version.getByName(name);
    }

    // get Version list
    public models.resource.NamedAPIResourceList getVersionList(int limit, int offset) {
        return models.games.Version.getList(limit, offset);
    }

    // get a NamedAPIResourceList instance by the endpoint
    public models.resource.NamedAPIResourceList getNamedAPIResourceListByEndpoint(String endpoint) {
        return models.resource.NamedAPIResourceList.getByEndpoint(endpoint);
    }

    // get NamedAPIResourceList list
    public models.resource.NamedAPIResourceList getNamedAPIResourceListList(int limit, int offset) {
        return models.resource.NamedAPIResourceList.getList(limit, offset);
    }

    // get a Ability instance by the id
    public models.pokemon.Ability getAbilityById(int id) {
        return models.pokemon.Ability.getById(id);
    }

    // get a Ability instance by the name
    public Ability getAbilityByName(String name) {
        return Ability.getByName(name);
    }

    // get Ability list
    public NamedAPIResourceList getAbilityList(int limit, int offset) {
        return models.pokemon.Ability.getList(limit, offset);
    }

    // get a MoveTarget instance by the id
    public MoveTarget getMoveTargetById(int id) {
        return MoveTarget.getById(id);
    }

    // get a MoveTarget instance by the name
    public MoveTarget getMoveTargetByName(String name) {
        return MoveTarget.getByName(name);
    }

    // get MoveTarget list
    public NamedAPIResourceList getMoveTargetList(int limit, int offset) {
        return MoveTarget.getList(limit, offset);
    }

    // get a APIResourceList instance by the endpoint
    public APIResourceList getAPIResourceListByEndpoint(String endpoint) {
        return APIResourceList.getByEndpoint(endpoint);
    }

    // get APIResourceList list
    public NamedAPIResourceList getAPIResourceListList(int limit, int offset) {
        return APIResourceList.getList(limit, offset);
    }

    // get a Characteristic instance by the id
    public Characteristic getCharacteristicById(int id) {
        return Characteristic.getById(id);
    }

    // get Characteristic list
    public NamedAPIResourceList getCharacteristicList(int limit, int offset) {
        return Characteristic.getList(limit, offset);
    }

    // get a EggGroup instance by the id
    public EggGroup getEggGroupById(int id) {
        return EggGroup.getById(id);
    }

    // get a EggGroup instance by the name
    public EggGroup getEggGroupByName(String name) {
        return EggGroup.getByName(name);
    }

    // get EggGroup list
    public NamedAPIResourceList getEggGroupList(int limit, int offset) {
        return EggGroup.getList(limit, offset);
    }

    // get a Region instance by the id
    public Region getRegionById(int id) {
        return Region.getById(id);
    }

    // get a Region instance by the name
    public Region getRegionByName(String name) {
        return Region.getByName(name);
    }

    // get Region list
    public NamedAPIResourceList getRegionList(int limit, int offset) {
        return Region.getList(limit, offset);
    }

    // get a MoveAilment instance by the id
    public MoveAilment getMoveAilmentById(int id) {
        return models.moves.MoveAilment.getById(id);
    }

    // get a MoveAilment instance by the name
    public MoveAilment getMoveAilmentByName(String name) {
        return MoveAilment.getByName(name);
    }

    // get MoveAilment list
    public NamedAPIResourceList getMoveAilmentList(int limit, int offset) {
        return MoveAilment.getList(limit, offset);
    }

    // get a PokemonSpecies instance by the id
    public PokemonSpecies getPokemonSpeciesById(int id) {
        return PokemonSpecies.getById(id);
    }

    // get a PokemonSpecies instance by the name
    public PokemonSpecies getPokemonSpeciesByName(String name) {
        return PokemonSpecies.getByName(name);
    }

    // get PokemonSpecies list
    public NamedAPIResourceList getPokemonSpeciesList(int limit, int offset) {
        return PokemonSpecies.getList(limit, offset);
    }

    // get a Pokedex instance by the id
    public Pokedex getPokedexById(int id) {
        return Pokedex.getById(id);
    }

    // get a Pokedex instance by the name
    public Pokedex getPokedexByName(String name) {
        return Pokedex.getByName(name);
    }

    // get Pokedex list
    public NamedAPIResourceList getPokedexList(int limit, int offset) {
        return Pokedex.getList(limit, offset);
    }

    // get a Language instance by the id
    public Language getLanguageById(int id) {
        return Language.getById(id);
    }

    // get a Language instance by the name
    public Language getLanguageByName(String name) {
        return Language.getByName(name);
    }

    // get Language list
    public NamedAPIResourceList getLanguageList(int limit, int offset) {
        return Language.getList(limit, offset);
    }

    // get a BerryFirmness instance by the id
    public BerryFirmness getBerryFirmnessById(int id) {
        return BerryFirmness.getById(id);
    }

    // get a BerryFirmness instance by the name
    public BerryFirmness getBerryFirmnessByName(String name) {
        return BerryFirmness.getByName(name);
    }

    // get BerryFirmness list
    public NamedAPIResourceList getBerryFirmnessList(int limit, int offset) {
        return BerryFirmness.getList(limit, offset);
    }

    // get a Move instance by the id
    public Move getMoveById(int id) {
        return Move.getById(id);
    }

    // get a Move instance by the name
    public Move getMoveByName(String name) {
        return Move.getByName(name);
    }

    // get Move list
    public NamedAPIResourceList getMoveList(int limit, int offset) {
        return Move.getList(limit, offset);
    }

    // get a Pokemon instance by the id
    public Pokemon getPokemonById(int id) {
        return Pokemon.getById(id);
    }

    // get a Pokemon instance by the name
    public Pokemon getPokemonByName(String name) {
        return Pokemon.getByName(name);
    }

    // get Pokemon list
    public NamedAPIResourceList getPokemonList(int limit, int offset) {
        return Pokemon.getList(limit, offset);
    }

    // get a PokemonColor instance by the id
    public PokemonColor getPokemonColorById(int id) {
        return PokemonColor.getById(id);
    }

    // get a PokemonColor instance by the name
    public PokemonColor getPokemonColorByName(String name) {
        return PokemonColor.getByName(name);
    }

    // get PokemonColor list
    public NamedAPIResourceList getPokemonColorList(int limit, int offset) {
        return PokemonColor.getList(limit, offset);
    }

    // get a SuperContestEffect instance by the id
    public SuperContestEffect getSuperContestEffectById(int id) {
        return SuperContestEffect.getById(id);
    }

    // get SuperContestEffect list
    public NamedAPIResourceList getSuperContestEffectList(int limit, int offset) {
        return SuperContestEffect.getList(limit, offset);
    }

    // get a VersionGroup instance by the id
    public VersionGroup getVersionGroupById(int id) {
        return VersionGroup.getById(id);
    }

    // get a VersionGroup instance by the name
    public VersionGroup getVersionGroupByName(String name) {
        return VersionGroup.getByName(name);
    }

    // get VersionGroup list
    public NamedAPIResourceList getVersionGroupList(int limit, int offset) {
        return VersionGroup.getList(limit, offset);
    }

    // get a Location instance by the id
    public Location getLocationById(int id) {
        return Location.getById(id);
    }

    // get Location list
    public NamedAPIResourceList getLocationList(int limit, int offset) {
        return Location.getList(limit, offset);
    }

}