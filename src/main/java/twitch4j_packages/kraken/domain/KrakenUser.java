package twitch4j_packages.kraken.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class KrakenUser {

    @JsonProperty("_id")
    private Long id;

    private String name;

    @JsonProperty("display_name")
    private String displayName;

    private String logo;

    private String type;

    private String bio;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("created_at")
    private Date createdAt;

}
