package twitch4j_packages.kraken.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * Abstract base for result lists.
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractResultList {
    /**
     * Cursor
     */
    @JsonProperty("_cursor")
    private String cursor;

    /**
     * Total Entries
     */
    @JsonProperty("_total")
    private Long total;

}
