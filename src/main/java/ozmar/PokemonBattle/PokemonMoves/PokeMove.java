package ozmar.PokemonBattle.PokemonMoves;

import ozmar.PokemonBattle.PokemonType.PokeTypeEnum;

public class PokeMove {
    private final int id;
    private final String name;
    private int maxPp;
    private int currPp;
    private int power;
    private int accuracy;
    private int priority;
    private PokeTypeEnum moveType;
    private final PokeMoveDamageClass damageClass;
    private final PokeTarget moveTarget;

    private PokeMoveMetaData metaData;

    public PokeMove(int id, String name, int maxPp, int power, int accuracy, int priority, PokeTypeEnum moveType,
                    PokeMoveDamageClass damageClass, PokeTarget moveTarget, PokeMoveMetaData metaData) {
        this.id = id;
        this.name = name;
        this.maxPp = maxPp;
        this.currPp = maxPp;
        this.power = power;
        this.accuracy = accuracy;
        this.priority = priority;
        this.moveType = moveType;
        this.damageClass = damageClass;
        this.moveTarget = moveTarget;
        this.metaData = metaData;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxPp() {
        return maxPp;
    }

    public void setMaxPp(int maxPp) {
        this.maxPp = maxPp;
    }

    public int getCurrPp() {
        return currPp;
    }

    public void setCurrPp(int currPp) {
        this.currPp = currPp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PokeTypeEnum getMoveType() {
        return moveType;
    }

    public void setMoveType(PokeTypeEnum moveType) {
        this.moveType = moveType;
    }

    public PokeMoveDamageClass getDamageClass() {
        return damageClass;
    }

    public PokeTarget getMoveTarget() {
        return moveTarget;
    }

    public PokeMoveMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(PokeMoveMetaData metaData) {
        this.metaData = metaData;
    }
}
