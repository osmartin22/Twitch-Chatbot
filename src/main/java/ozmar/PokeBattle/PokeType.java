package ozmar.PokeBattle;

import ozmar.PokeBattle.enums.PokeTypeEnum;

import java.util.List;

public class PokeType {
    private PokeTypeEnum type;
    private List<PokeTypeEnum> doubleDamageFrom;
    private List<PokeTypeEnum> doubleDamageTo;
    private List<PokeTypeEnum> halfDamageFrom;
    private List<PokeTypeEnum> halfDamageTo;
    private List<PokeTypeEnum> noDamageFrom;
    private List<PokeTypeEnum> noDamageTo;

    public PokeType(PokeTypeEnum type, List<PokeTypeEnum> doubleDamageFrom, List<PokeTypeEnum> doubleDamageTo,
                    List<PokeTypeEnum> halfDamageFrom, List<PokeTypeEnum> halfDamageTo,
                    List<PokeTypeEnum> noDamageFrom, List<PokeTypeEnum> noDamageTo) {
        this.type = type;
        this.doubleDamageFrom = doubleDamageFrom;
        this.doubleDamageTo = doubleDamageTo;
        this.halfDamageFrom = halfDamageFrom;
        this.halfDamageTo = halfDamageTo;
        this.noDamageFrom = noDamageFrom;
        this.noDamageTo = noDamageTo;
    }

    public PokeTypeEnum getType() {
        return type;
    }

    public List<PokeTypeEnum> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public List<PokeTypeEnum> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public List<PokeTypeEnum> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public List<PokeTypeEnum> getHalfDamageTo() {
        return halfDamageTo;
    }

    public List<PokeTypeEnum> getNoDamageFrom() {
        return noDamageFrom;
    }

    public List<PokeTypeEnum> getNoDamageTo() {
        return noDamageTo;
    }
}
