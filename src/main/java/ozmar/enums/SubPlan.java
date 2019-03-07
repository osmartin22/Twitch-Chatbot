package ozmar.enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum SubPlan {
    NONE("none"),
    TWITCH_PRIME("Prime"),
    TIER1("1000"),
    TIER2("2000"),
    TIER3("3000");

    private final String ordinal;

    SubPlan(String ordinal) {
        this.ordinal = ordinal;
    }

    @Nonnull
    public static SubPlan getValue(@Nullable String plan) {
        if (plan != null) {
            for (SubPlan subPlan : values()) {
                if (plan.equalsIgnoreCase(subPlan.ordinal)) {
                    return subPlan;
                }
            }
        }

        return NONE;
    }

    @Override
    public String toString() {
        return ordinal;
    }

    @Nonnull
    public String getSubPlanName() {
        switch (this) {
            case TWITCH_PRIME:
                return "Twitch Prime";
            case TIER1:
                return "Tier 1";
            case TIER2:
                return "Tier 2";
            case TIER3:
                return "Tier 3";

            case NONE:
            default:
                return "None";
        }
    }
}
