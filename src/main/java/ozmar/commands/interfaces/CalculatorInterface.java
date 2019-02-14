package ozmar.commands.interfaces;

import javax.annotation.Nonnull;

public interface CalculatorInterface {

    void setOperation(@Nonnull String operation);

    double parse();
}
