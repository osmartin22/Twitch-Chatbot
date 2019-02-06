package poke_models.pokemon;

import com.google.gson.Gson;

import java.util.List;

public class TypeRelations {
    // A list of types this type has no effect on
    private List<Type> no_damage_to;

    // A list of types this type is not very effect against
    private List<Type> half_damage_to;

    // A list of types this type is very effect against
    private List<Type> double_damage_to;

    // A list of types that have no effect on this type
    private List<Type> no_damage_from;

    // A list of types that are not very effective against this type
    private List<Type> half_damage_from;

    // A list of types that are very effective against this type
    private List<Type> double_damage_from;

    public List<Type> getNoDamageTo() {
        return no_damage_to;
    }

    public TypeRelations setNoDamageTo(List<Type> no_damage_to) {
        this.no_damage_to = no_damage_to;
        return this;
    }

    public List<Type> getHalfDamageTo() {
        return half_damage_to;
    }

    public TypeRelations setHalfDamageTo(List<Type> half_damage_to) {
        this.half_damage_to = half_damage_to;
        return this;
    }

    public List<Type> getDoubleDamageTo() {
        return double_damage_to;
    }

    public TypeRelations setDoubleDamageTo(List<Type> double_damage_to) {
        this.double_damage_to = double_damage_to;
        return this;
    }

    public List<Type> getNoDamageFrom() {
        return no_damage_from;
    }

    public TypeRelations setNoDamageFrom(List<Type> no_damage_from) {
        this.no_damage_from = no_damage_from;
        return this;
    }

    public List<Type> getHalfDamageFrom() {
        return half_damage_from;
    }

    public TypeRelations setHalfDamageFrom(List<Type> half_damage_from) {
        this.half_damage_from = half_damage_from;
        return this;
    }

    public List<Type> getDoubleDamageFrom() {
        return double_damage_from;
    }

    public TypeRelations setDoubleDamageFrom(List<Type> double_damage_from) {
        this.double_damage_from = double_damage_from;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}