package ozmar.enums;

public enum Operator {
    ADD(2),
    SUBTRACT(2),
    MULTIPLY(3),
    DIVIDE(3),
    OPENPAREN(1),
    CLOSEPAREN(1),
    BLANK(0);

    private final int priority;

    Operator(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
