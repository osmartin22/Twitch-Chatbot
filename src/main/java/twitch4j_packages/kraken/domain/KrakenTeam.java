package twitch4j_packages.kraken.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Model representing a team.
 */
@Data
public class KrakenTeam {

    @JsonProperty("_id")
    private long id;

    private String name;

    private String displayName;

    private String info;

    private String logo;

    private String background;

    private String banner;

    private Date createdAt;

    private Date updatedAt;
}
