package twitch4j_packages.common.events.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventChannel {

    /**
     * Channel Id
     */
    private final Long id;

    /**
     * Channel Name
     */
    private final String name;

}
