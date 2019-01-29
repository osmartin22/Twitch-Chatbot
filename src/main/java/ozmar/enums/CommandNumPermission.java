package ozmar.enums;

import com.github.twitch4j.common.enums.CommandPermission;

import java.util.Set;

public enum CommandNumPermission {

    // TODO: CHECK PERMISSION TO MAKE SURE A SUB DOESN'T GET THE WRONG LEVEL
    EVERYONE(0),
    SUBSCRIBER(1),
    VIP(2),
    MODERATOR(3),
    BROADCASTER(4),
    OWNER(5);

    private final int commandLevel;

    CommandNumPermission(int commandLevel) {
        this.commandLevel = commandLevel;
    }

    public int getCommandLevel() {
        return commandLevel;
    }

    /**
     * Converts a set of CommandPermission to a singular CommandNumPermission
     *
     * @param commandPermissionSet Set of permissions using CommandPermission
     * @return CommandNumPermission
     */
    public static CommandNumPermission convertToNumPermission(Set<CommandPermission> commandPermissionSet) {
        if (commandPermissionSet.contains(CommandPermission.OWNER)) {
            return CommandNumPermission.OWNER;

        } else if (commandPermissionSet.contains(CommandPermission.BROADCASTER)) {
            return CommandNumPermission.BROADCASTER;

        } else if (commandPermissionSet.contains(CommandPermission.MODERATOR)) {
            return CommandNumPermission.MODERATOR;

        } else if (commandPermissionSet.contains(CommandPermission.VIP)) {
            return CommandNumPermission.VIP;

        } else if (commandPermissionSet.contains(CommandPermission.SUBSCRIBER)) {
            return CommandNumPermission.SUBSCRIBER;

        } else {
            return CommandNumPermission.EVERYONE;
        }
    }

}
