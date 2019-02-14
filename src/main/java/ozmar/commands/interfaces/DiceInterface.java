package ozmar.commands.interfaces;

public interface DiceInterface {

    void setSides(int sides);

    int rollPosDie();

    int rollNegDie();

    Integer rollNDie(int dieCount);
}
