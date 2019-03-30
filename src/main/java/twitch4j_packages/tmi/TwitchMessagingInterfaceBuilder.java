package twitch4j_packages.tmi;

import com.netflix.config.ConfigurationManager;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.common.builder.TwitchAPIBuilder;
import twitch4j_packages.common.feign.interceptor.TwitchClientIdInterceptor;

/**
 * Twitch API - Messaging Interface
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TwitchMessagingInterfaceBuilder extends TwitchAPIBuilder<TwitchMessagingInterfaceBuilder> {

    /**
     * BaseUrl
     */
    private String baseUrl = "https://tmi.twitch.tv";

    /**
     * Initialize the builder
     *
     * @return Twitch Helix Builder
     */
    public static TwitchMessagingInterfaceBuilder builder() {
        return new TwitchMessagingInterfaceBuilder();
    }

    /**
     * Twitch API Client (Helix)
     *
     * @return TwitchHelix
     */
    public TwitchMessagingInterface build() {
        log.debug("TMI: Initializing Module ...");
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 2500);
        TwitchMessagingInterface client = HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Logger.ErrorLogger())
                .errorDecoder(new TwitchMessagingInterfaceErrorDecoder(new JacksonDecoder()))
                .logLevel(Logger.Level.BASIC)
                .requestInterceptor(new TwitchClientIdInterceptor(this))
                .retryer(new Retryer.Default(1, 10000, 3))
                .options(new Request.Options(5000, 15000))
                .target(TwitchMessagingInterface.class, baseUrl);

        // register with serviceMediator
        getEventManager().getServiceMediator().addService("twitch4j-api-tmi", client);

        return client;
    }
}
