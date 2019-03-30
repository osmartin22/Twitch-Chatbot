package twitch4jTests.kraken.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import twitch4j_packages.kraken.domain.KrakenIngestList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Tag("integration")
public class IngestsServiceTest extends AbstractKrakenServiceTest {

    @Test
    @DisplayName("getIngestServerList")
    @Disabled
    public void getIngestServerList() {
        KrakenIngestList resultList = getTwitchKrakenClient().getIngestServers().execute();

        assertTrue(resultList.getIngests().size() > 0, "Didn't find any ingest servers!");
    }

}
