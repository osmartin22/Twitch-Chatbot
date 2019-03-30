package twitch4j_packages.kraken.domain;

import lombok.Data;

import java.util.List;

/**
 * Model representing a list of ingest servers.
 */
@Data
public class KrakenIngestList {
    /**
     * Data
     */
    private List<KrakenIngest> ingests;

}
