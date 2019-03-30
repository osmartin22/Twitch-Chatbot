package twitch4j_packages.common.events;

import com.github.philippheuer.events4j.domain.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class TwitchEvent extends Event {

    /**
     * Constructor
     */
    public TwitchEvent() {
        super();
    }

}
