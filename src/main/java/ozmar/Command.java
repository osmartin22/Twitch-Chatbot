package ozmar;

import ozmar.enums.CommandNumPermission;

public class Command {

    private int id = 0;
    private String command;
    private CommandNumPermission permission;

    public Command(String command, CommandNumPermission permission) {
        this.command = command;
        this.permission = permission;
    }


    public Command(int id, String command, CommandNumPermission permission) {
        this.id = id;
        this.command = command;
        this.permission = permission;
    }

    public String getCommand() {
        return command;
    }

    public CommandNumPermission getPermission() {
        return permission;
    }

    public void setPermission(CommandNumPermission permission) {
        this.permission = permission;
    }
}
