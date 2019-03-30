package twitch4j_packages.kraken.domain;

import lombok.Data;

import java.util.List;

/**
 * Model representing teams.
 */
@Data
public class KrakenTeamList {
    /**
     * Data
     */
    private List<KrakenTeam> teams;

}
