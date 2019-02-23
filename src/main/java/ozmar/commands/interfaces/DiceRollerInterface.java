package ozmar.commands.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface DiceRollerInterface {

    @Nullable
    Integer roll(int dieSides, int dieCount);

    @Nullable
    Integer roll(@Nonnull String dieSides, @Nonnull String dieCount);

    int rollPosDie(int dieSides);

    int rollNegDie(int dieSides);
}
