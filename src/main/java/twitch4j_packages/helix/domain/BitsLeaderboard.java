package twitch4j_packages.helix.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * Bits Leaderboard
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitsLeaderboard {
    /**
     * Data
     */
    @JsonProperty("data")
    private List<BitsLeaderboardEntry> entries;

    @JsonProperty("pagination")
    private HelixPagination pagination;

}
