package twitch4j_packages.common.events.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import twitch4j_packages.chat.events.TwitchEvent;
import twitch4j_packages.common.enums.CommandPermission;
import twitch4j_packages.common.events.domain.EventUser;

import java.util.Set;

/**
 * This event gets called when the bot gets a private message.
 */
@Value
@Getter
@EqualsAndHashCode(callSuper = false)
public class PrivateMessageEvent extends TwitchEvent {

    /**
     * User
     */
    private final EventUser user;

    /**
     * Message
     */
    private final String message;

    /**
     * Permissions of the user
     */
    private final Set<CommandPermission> permissions;

    /**
     * Event Constructor
     *
     * @param user        The user who triggered the event.
     * @param message     The plain text of the message.
     * @param permissions The permissions of the triggering user.
     */
    public PrivateMessageEvent(EventUser user, String message, Set<CommandPermission> permissions) {
        this.user = user;
        this.message = message;
        this.permissions = permissions;
    }

    public void sendResponse(String message) {
        // getClient().getMessageInterface().sendPrivateMessage(user.getName(), message);
    }
}
