package twitch4j_packages.common.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import twitch4j_packages.common.builder.TwitchAPIBuilder;

/**
 * Injects ClientId Header, the User Agent and other common headers into each API Request
 */
public class TwitchClientIdInterceptor implements RequestInterceptor {

    /**
     * Reference to the Client Builder
     */
    private final TwitchAPIBuilder twitchAPIBuilder;

    /**
     * Constructor
     *
     * @param twitchHelixBuilder Twitch Client Builder
     */
    public TwitchClientIdInterceptor(TwitchAPIBuilder twitchHelixBuilder) {
        this.twitchAPIBuilder = twitchHelixBuilder;
    }

    /**
     * Interceptor
     *
     * @param template Feign RequestTemplate
     */
    @Override
    public void apply(RequestTemplate template) {
        template.header("Client-Id", twitchAPIBuilder.getClientId());
        template.header("User-Agent", twitchAPIBuilder.getUserAgent());
    }
}
