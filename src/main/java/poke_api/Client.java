package poke_api;

import poke_models.berries.Berry;
import poke_models.berries.BerryFirmness;
import poke_models.berries.BerryFlavor;
import poke_models.contests.ContestEffect;
import poke_models.contests.ContestType;
import poke_models.contests.SuperContestEffect;
import poke_models.encounters.EncounterCondition;
import poke_models.encounters.EncounterConditionValue;
import poke_models.encounters.EncounterMethod;
import poke_models.evolution.EvolutionChain;
import poke_models.evolution.EvolutionTrigger;
import poke_models.games.Generation;
import poke_models.games.Pokedex;
import poke_models.games.Version;
import poke_models.games.VersionGroup;
import poke_models.items.*;
import poke_models.locations.Location;
import poke_models.locations.LocationArea;
import poke_models.locations.PalParkArea;
import poke_models.locations.Region;
import poke_models.machines.Machine;
import poke_models.moves.*;
import poke_models.pokemon.*;
import poke_models.resource.APIResourceList;
import poke_models.resource.NamedAPIResourceList;
import poke_models.utility.Language;

// heart of wrapper, contains all possible poke_api calls
public class Client {

    // if true, save poke_api response to local database for speed and poke_api call limit help
    public static boolean CACHE = true;

    // get a Gender instance by the id
    public Gender getGenderById(int id) {
        return Gender.getById(id);
    }

    // get a Gender instance by the name
    public Gender getGenderByName(String name) {
        return Gender.getByName(name);
    }

    // get Gender list
    public NamedAPIResourceList getGenderList(int limit, int offset) {
        return Gender.getList(limit, offset);
    }

    // get a Item instance by the id
    public Item getItemById(int id) {
        return Item.getById(id);
    }

    // get a Item instance by the name
    public Item getItemByName(String name) {
        return Item.getByName(name);
    }

    // get Item list
    public NamedAPIResourceList getItemList(int limit, int offset) {
        return Item.getList(limit, offset);
    }

    // get a ItemCategory instance by the id
    public ItemCategory getItemCategoryById(int id) {
        return ItemCategory.getById(id);
    }

    // get a ItemCategory instance by the name
    public ItemCategory getItemCategoryByName(String name) {
        return ItemCategory.getByName(name);
    }

    // get ItemCategory list
    public NamedAPIResourceList getItemCategoryList(int limit, int offset) {
        return ItemCategory.getList(limit, offset);
    }

    // get a MoveDamageClass instance by the id
    public MoveDamageClass getMoveDamageClassById(int id) {
        return MoveDamageClass.getById(id);
    }

    // get a MoveDamageClass instance by the name
    public MoveDamageClass getMoveDamageClassByName(String name) {
        return MoveDamageClass.getByName(name);
    }

    // get MoveDamageClass list
    public NamedAPIResourceList getMoveDamageClassList(int limit, int offset) {
        return MoveDamageClass.getList(limit, offset);
    }

    // get a EncounterMethod instance by the id
    public EncounterMethod getEncounterMethodById(int id) {
        return EncounterMethod.getById(id);
    }

    // get a EncounterMethod instance by the name
    public EncounterMethod getEncounterMethodByName(String name) {
        return EncounterMethod.getByName(name);
    }

    // get EncounterMethod list
    public NamedAPIResourceList getEncounterMethodList(int limit, int offset) {
        return EncounterMethod.getList(limit, offset);
    }

    // get a BerryFlavor instance by the id
    public BerryFlavor getBerryFlavorById(int id) {
        return BerryFlavor.getById(id);
    }

    // get a BerryFlavor instance by the name
    public BerryFlavor getBerryFlavorByName(String name) {
        return BerryFlavor.getByName(name);
    }

    // get BerryFlavor list
    public NamedAPIResourceList getBerryFlavorList(int limit, int offset) {
        return BerryFlavor.getList(limit, offset);
    }

    // get a EvolutionChain instance by the id
    public EvolutionChain getEvolutionChainById(int id) {
        return EvolutionChain.getById(id);
    }

    // get EvolutionChain list
    public NamedAPIResourceList getEvolutionChainList(int limit, int offset) {
        return EvolutionChain.getList(limit, offset);
    }

    // get a PokemonShape instance by the id
    public PokemonShape getPokemonShapeById(int id) {
        return PokemonShape.getById(id);
    }

    // get a PokemonShape instance by the name
    public PokemonShape getPokemonShapeByName(String name) {
        return PokemonShape.getByName(name);
    }

    // get PokemonShape list
    public NamedAPIResourceList getPokemonShapeList(int limit, int offset) {
        return PokemonShape.getList(limit, offset);
    }

    // get a Berry instance by the id
    public Berry getBerryById(int id) {
        return Berry.getById(id);
    }

    // get a Berry instance by the name
    public Berry getBerryByName(String name) {
        return Berry.getByName(name);
    }

    // get Berry list
    public NamedAPIResourceList getBerryList(int limit, int offset) {
        return Berry.getList(limit, offset);
    }

    // get a PokeathlonStat instance by the id
    public PokeathlonStat getPokeathlonStatById(int id) {
        return PokeathlonStat.getById(id);
    }

    // get a PokeathlonStat instance by the name
    public PokeathlonStat getPokeathlonStatByName(String name) {
        return PokeathlonStat.getByName(name);
    }

    // get PokeathlonStat list
    public NamedAPIResourceList getPokeathlonStatList(int limit, int offset) {
        return PokeathlonStat.getList(limit, offset);
    }

    // get a ItemFlingEffect instance by the id
    public ItemFlingEffect getItemFlingEffectById(int id) {
        return ItemFlingEffect.getById(id);
    }

    // get a ItemFlingEffect instance by the name
    public ItemFlingEffect getItemFlingEffectByName(String name) {
        return ItemFlingEffect.getByName(name);
    }

    // get ItemFlingEffect list
    public NamedAPIResourceList getItemFlingEffectList(int limit, int offset) {
        return ItemFlingEffect.getList(limit, offset);
    }

    // get a Generation instance by the id
    public Generation getGenerationById(int id) {
        return Generation.getById(id);
    }

    // get a Generation instance by the name
    public Generation getGenerationByName(String name) {
        return Generation.getByName(name);
    }

    // get Generation list
    public NamedAPIResourceList getGenerationList(int limit, int offset) {
        return Generation.getList(limit, offset);
    }

    // get a GrowthRate instance by the id
    public GrowthRate getGrowthRateById(int id) {
        return GrowthRate.getById(id);
    }

    // get a GrowthRate instance by the name
    public GrowthRate getGrowthRateByName(String name) {
        return GrowthRate.getByName(name);
    }

    // get GrowthRate list
    public NamedAPIResourceList getGrowthRateList(int limit, int offset) {
        return GrowthRate.getList(limit, offset);
    }

    // get a ItemPocket instance by the id
    public ItemPocket getItemPocketById(int id) {
        return ItemPocket.getById(id);
    }

    // get a ItemPocket instance by the name
    public ItemPocket getItemPocketByName(String name) {
        return ItemPocket.getByName(name);
    }

    // get ItemPocket list
    public NamedAPIResourceList getItemPocketList(int limit, int offset) {
        return ItemPocket.getList(limit, offset);
    }

    // get a ItemAttribute instance by the id
    public ItemAttribute getItemAttributeById(int id) {
        return ItemAttribute.getById(id);
    }

    // get a ItemAttribute instance by the name
    public ItemAttribute getItemAttributeByName(String name) {
        return ItemAttribute.getByName(name);
    }

    // get ItemAttribute list
    public NamedAPIResourceList getItemAttributeList(int limit, int offset) {
        return ItemAttribute.getList(limit, offset);
    }

    // get a MoveCategory instance by the id
    public MoveCategory getMoveCategoryById(int id) {
        return MoveCategory.getById(id);
    }

    // get a MoveCategory instance by the name
    public MoveCategory getMoveCategoryByName(String name) {
        return MoveCategory.getByName(name);
    }

    // get MoveCategory list
    public NamedAPIResourceList getMoveCategoryList(int limit, int offset) {
        return MoveCategory.getList(limit, offset);
    }

    // get a EncounterCondition instance by the id
    public EncounterCondition getEncounterConditionById(int id) {
        return EncounterCondition.getById(id);
    }

    // get a EncounterCondition instance by the name
    public EncounterCondition getEncounterConditionByName(String name) {
        return EncounterCondition.getByName(name);
    }

    // get EncounterCondition list
    public NamedAPIResourceList getEncounterConditionList(int limit, int offset) {
        return EncounterCondition.getList(limit, offset);
    }

    // get a ContestEffect instance by the id
    public ContestEffect getContestEffectById(int id) {
        return ContestEffect.getById(id);
    }

    // get ContestEffect list
    public NamedAPIResourceList getContestEffectList(int limit, int offset) {
        return ContestEffect.getList(limit, offset);
    }

    // get a LocationArea instance by the id
    public LocationArea getLocationAreaById(int id) {
        return LocationArea.getById(id);
    }

    // get LocationArea list
    public NamedAPIResourceList getLocationAreaList(int limit, int offset) {
        return LocationArea.getList(limit, offset);
    }

    // get a Machine instance by the id
    public Machine getMachineById(int id) {
        return Machine.getById(id);
    }

    // get Machine list
    public NamedAPIResourceList getMachineList(int limit, int offset) {
        return Machine.getList(limit, offset);
    }

    // get a Stat instance by the id
    public Stat getStatById(int id) {
        return Stat.getById(id);
    }

    // get a Stat instance by the name
    public Stat getStatByName(String name) {
        return Stat.getByName(name);
    }

    // get Stat list
    public NamedAPIResourceList getStatList(int limit, int offset) {
        return Stat.getList(limit, offset);
    }

    // get a MoveLearnMethod instance by the id
    public MoveLearnMethod getMoveLearnMethodById(int id) {
        return MoveLearnMethod.getById(id);
    }

    // get a MoveLearnMethod instance by the name
    public MoveLearnMethod getMoveLearnMethodByName(String name) {
        return MoveLearnMethod.getByName(name);
    }

    // get MoveLearnMethod list
    public NamedAPIResourceList getMoveLearnMethodList(int limit, int offset) {
        return MoveLearnMethod.getList(limit, offset);
    }

    // get a PokemonHabitat instance by the id
    public PokemonHabitat getPokemonHabitatById(int id) {
        return PokemonHabitat.getById(id);
    }

    // get a PokemonHabitat instance by the name
    public PokemonHabitat getPokemonHabitatByName(String name) {
        return PokemonHabitat.getByName(name);
    }

    // get PokemonHabitat list
    public NamedAPIResourceList getPokemonHabitatList(int limit, int offset) {
        return PokemonHabitat.getList(limit, offset);
    }

    // get a Nature instance by the id
    public Nature getNatureById(int id) {
        return Nature.getById(id);
    }

    // get a Nature instance by the name
    public Nature getNatureByName(String name) {
        return Nature.getByName(name);
    }

    // get Nature list
    public NamedAPIResourceList getNatureList(int limit, int offset) {
        return Nature.getList(limit, offset);
    }

    // get a PalParkArea instance by the id
    public PalParkArea getPalParkAreaById(int id) {
        return PalParkArea.getById(id);
    }

    // get a PalParkArea instance by the name
    public PalParkArea getPalParkAreaByName(String name) {
        return PalParkArea.getByName(name);
    }

    // get PalParkArea list
    public NamedAPIResourceList getPalParkAreaList(int limit, int offset) {
        return PalParkArea.getList(limit, offset);
    }

    // get a EncounterConditionValue instance by the id
    public EncounterConditionValue getEncounterConditionValueById(int id) {
        return EncounterConditionValue.getById(id);
    }

    // get a EncounterConditionValue instance by the name
    public EncounterConditionValue getEncounterConditionValueByName(String name) {
        return EncounterConditionValue.getByName(name);
    }

    // get EncounterConditionValue list
    public NamedAPIResourceList getEncounterConditionValueList(int limit, int offset) {
        return EncounterConditionValue.getList(limit, offset);
    }

    // get a Type instance by the id
    public Type getTypeById(int id) {
        return Type.getById(id);
    }

    // get a Type instance by the name
    public Type getTypeByName(String name) {
        return Type.getByName(name);
    }

    // get Type list
    public NamedAPIResourceList getTypeList(int limit, int offset) {
        return Type.getList(limit, offset);
    }

    // get a PokemonForm instance by the id
    public PokemonForm getPokemonFormById(int id) {
        return PokemonForm.getById(id);
    }

    // get a PokemonForm instance by the name
    public PokemonForm getPokemonFormByName(String name) {
        return PokemonForm.getByName(name);
    }

    // get PokemonForm list
    public NamedAPIResourceList getPokemonFormList(int limit, int offset) {
        return PokemonForm.getList(limit, offset);
    }

    // get a ContestType instance by the id
    public ContestType getContestTypeById(int id) {
        return ContestType.getById(id);
    }

    // get a ContestType instance by the name
    public ContestType getContestTypeByName(String name) {
        return ContestType.getByName(name);
    }

    // get ContestType list
    public NamedAPIResourceList getContestTypeList(int limit, int offset) {
        return ContestType.getList(limit, offset);
    }

    // get a EvolutionTrigger instance by the id
    public EvolutionTrigger getEvolutionTriggerById(int id) {
        return EvolutionTrigger.getById(id);
    }

    // get a EvolutionTrigger instance by the name
    public EvolutionTrigger getEvolutionTriggerByName(String name) {
        return EvolutionTrigger.getByName(name);
    }

    // get EvolutionTrigger list
    public NamedAPIResourceList getEvolutionTriggerList(int limit, int offset) {
        return EvolutionTrigger.getList(limit, offset);
    }

    // get a MoveBattleStyle instance by the id
    public MoveBattleStyle getMoveBattleStyleById(int id) {
        return MoveBattleStyle.getById(id);
    }

    // get a MoveBattleStyle instance by the name
    public MoveBattleStyle getMoveBattleStyleByName(String name) {
        return MoveBattleStyle.getByName(name);
    }

    // get MoveBattleStyle list
    public NamedAPIResourceList getMoveBattleStyleList(int limit, int offset) {
        return MoveBattleStyle.getList(limit, offset);
    }

    // get a Version instance by the id
    public Version getVersionById(int id) {
        return Version.getById(id);
    }

    // get a Version instance by the name
    public Version getVersionByName(String name) {
        return Version.getByName(name);
    }

    // get Version list
    public NamedAPIResourceList getVersionList(int limit, int offset) {
        return Version.getList(limit, offset);
    }

    // get a NamedAPIResourceList instance by the endpoint
    public NamedAPIResourceList getNamedAPIResourceListByEndpoint(String endpoint) {
        return NamedAPIResourceList.getByEndpoint(endpoint);
    }

    // get NamedAPIResourceList list
    public NamedAPIResourceList getNamedAPIResourceListList(int limit, int offset) {
        return NamedAPIResourceList.getList(limit, offset);
    }

    // get a Ability instance by the id
    public Ability getAbilityById(int id) {
        return Ability.getById(id);
    }

    // get a Ability instance by the name
    public Ability getAbilityByName(String name) {
        return Ability.getByName(name);
    }

    // get Ability list
    public NamedAPIResourceList getAbilityList(int limit, int offset) {
        return Ability.getList(limit, offset);
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
        return MoveAilment.getById(id);
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