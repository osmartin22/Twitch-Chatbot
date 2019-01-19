package ozmar;

import com.github.twitch4j.common.enums.CommandPermission;

import java.util.HashSet;
import java.util.Set;

public class Command {

    private int id = 0;
    private String command;
    private Set<CommandPermission> permissions;

    public Command(String command, Set<CommandPermission> permissions) {
        this.command = command;
        this.permissions = permissions;
    }

    public Command(int id, String command, Set<CommandPermission> permissions) {
        this.id = id;
        this.command = command;
        this.permissions = permissions;

    }

    public Command(int id, String command, String permissions) {
        this.id = id;
        this.command = command;
        this.permissions = convertStringToPermissions(permissions);
    }

    public String getCommand() {
        return command;
    }

    public Set<CommandPermission> getPermissions() {
        return permissions;
    }

    public void addPermission(CommandPermission commandPermission) {
        permissions.add(commandPermission);
    }

    public void removePermission(CommandPermission commandPermission) {
        permissions.remove(commandPermission);
    }

    public String convertPermissionsToString() {
        StringBuilder result = new StringBuilder();

        for (CommandPermission permission : CommandPermission.values()) {
            if (this.permissions.contains(permission)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }

        return result.toString();
    }

    private Set<CommandPermission> convertStringToPermissions(String permissions) {
        Set<CommandPermission> permissionSet = new HashSet<>();

        if (permissions == null || permissions.isEmpty()) {
            return permissionSet;
        }

        // Loop assumes the string contained a 1 or 0 for every permission
        for (int i = 0; i < permissions.length(); i++) {
            if (permissions.charAt(i) == '1') {
                permissionSet.add(CommandPermission.values()[i]);
            }
        }

        return permissionSet;
    }
}
