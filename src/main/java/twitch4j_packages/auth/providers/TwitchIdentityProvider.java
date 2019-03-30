package twitch4j_packages.auth.providers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.credentialmanager.identityprovider.OAuth2IdentityProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Twitch Identity Provider
 */
public class TwitchIdentityProvider extends OAuth2IdentityProvider {

    /**
     * Constructor
     *
     * @param clientId     OAuth Client Id
     * @param clientSecret OAuth Client Secret
     * @param redirectUrl  Redirect Url
     */
    public TwitchIdentityProvider(String clientId, String clientSecret, String redirectUrl) {
        super("twitch", "oauth2", clientId, clientSecret, "https://id.twitch.tv/oauth2/authorize", "https://id.twitch.tv/oauth2/token", redirectUrl);

        // configuration
        this.tokenEndpointPostType = "QUERY";
    }

    /**
     * Get Auth Token Information
     *
     * @param credential OAuth2 Credential
     */
    public Optional<OAuth2Credential> getAdditionalCredentialInformation(OAuth2Credential credential) {
        try {
            // api call
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://id.twitch.tv/oauth2/validate")
                    .header("Authorization", "OAuth " + credential.getAccessToken())
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            // parse response
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<String, Object> tokenInfo = objectMapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>() {
                });
                String userId = (String) tokenInfo.get("user_id");
                String userName = (String) tokenInfo.get("login");
                List<String> scopes = (List<String>) tokenInfo.get("scopes");

                // create credential instance
                OAuth2Credential newCredential = new OAuth2Credential(credential.getIdentityProvider(), credential.getAccessToken(), credential.getRefreshToken(), userId, userName, null, scopes);

                return Optional.ofNullable(newCredential);
            } else {
                throw new RuntimeException("Request Failed! Code: " + response.code() + " - " + responseBody);
            }

        } catch (Exception ex) {
            // ignore, invalid token
        }

        return Optional.empty();
    }

}
