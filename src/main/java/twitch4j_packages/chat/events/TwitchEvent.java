package twitch4j_packages.chat.events;

import com.github.philippheuer.events4j.domain.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import twitch4j_packages.chat.TwitchChat;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class TwitchEvent extends Event {

    /**
     * Constructor
     */
    public TwitchEvent() {
        super();
    }

    /**
     * Get TwitchChat
     *
     * @return TwitchChat Instance
     */
    public TwitchChat getTwitchChat() {
        return getServiceMediator().getService(TwitchChat.class, "twitch4j-chat");
    }
}
