package twitch4jTests.kraken.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import twitch4j_packages.kraken.domain.KrakenTeam;
import twitch4j_packages.kraken.domain.KrakenTeamList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Tag("integration")
public class TeamServiceTest extends AbstractKrakenServiceTest {

    @Test
    @DisplayName("getAllTeams")
    @Disabled
    public void getAllTeams() {
        KrakenTeamList resultList = getTwitchKrakenClient().getAllTeams(null, null).execute();

        assertTrue(resultList.getTeams().size() > 0, "Didn't find any teams!");
    }

    @Test
    @DisplayName("getTeamByName")
    @Disabled
    public void getTeamByName() {
        KrakenTeam result = getTwitchKrakenClient().getTeamByName("staff").execute();

        assertNotNull(result, "Didn't find the specified team!");
    }

}
