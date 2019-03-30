package twitch4jTests.kraken.endpoints;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import twitch4j_packages.kraken.TwitchKraken;
import twitch4j_packages.kraken.TwitchKrakenBuilder;

abstract class AbstractKrakenServiceTest {

    /**
     * Gets the OAuth Credential for integration tests
     *
     * @return OAuth2Credential
     */
    public static OAuth2Credential getCredential() {
        return new OAuth2Credential("twitch", System.getenv("TWITCH_AUTH_TOKEN"));
    }

    /**
     * Sleeps for x millis
     *
     * @param millis Millis
     */
    public static void sleepFor(Integer millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
            // nothing
        }
    }

    /**
     * Gets a instance of the Helix Client
     *
     * @return TwitchHelix
     */
    public static TwitchKraken getTwitchKrakenClient() {
        TwitchKraken client = TwitchKrakenBuilder.builder().build();
        return client;
    }

}
