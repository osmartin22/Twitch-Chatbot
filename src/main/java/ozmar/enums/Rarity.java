package ozmar.enums;

public enum Rarity {

    COMMON(1),
    RARE(2),
    EPIC(3),
    LEGENDARY(4);

    int rarity;

    Rarity(int rarity) {
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }
}
