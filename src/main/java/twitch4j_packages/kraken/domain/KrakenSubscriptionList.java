package twitch4j_packages.kraken.domain;

import lombok.Data;

import java.util.List;

@Data
public class KrakenSubscriptionList {

    private List<KrakenSubscription> subscriptions;

}
