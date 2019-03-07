package ozmar.enums;

import javax.annotation.Nonnull;

public enum PokemonGender {
    NO_GENDER(0),
    MALE(1),
    FEMALE(2);

    private final int genderNum;
    public static final PokemonGender[] genders = values();

    PokemonGender(int ordinal) {
        this.genderNum = ordinal;
    }

    public int getGenderNum() {
        return genderNum;
    }

    @Nonnull
    public String getGenderString() {
        String gender;
        switch (this) {
            case NO_GENDER:
            default:
                gender = "";
                break;

            case MALE:
                gender = "male";
                break;

            case FEMALE:
                gender = "female";
                break;
        }

        return gender;
    }
}
