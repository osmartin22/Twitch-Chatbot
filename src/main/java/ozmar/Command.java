package ozmar;

import ozmar.enums.CommandNumPermission;

public class Command {

    private int id = 0;
    private String command;
    private CommandNumPermission permission;
    private int usage;

    public Command(String command, CommandNumPermission permission, int usage) {
        this.command = command;
        this.permission = permission;
        this.usage = usage;
    }

    public Command(int id, String command, CommandNumPermission permission, int usage) {
        this.id = id;
        this.command = command;
        this.permission = permission;
        this.usage = usage;
    }

    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public CommandNumPermission getPermission() {
        return permission;
    }

    public int getUsage() {
        return usage;
    }

    public void incrementUsage() {
        this.usage++;
    }

    public void setPermission(CommandNumPermission permission) {
        this.permission = permission;
    }
}
