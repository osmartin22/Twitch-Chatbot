package ozmar.commands;

import ozmar.enums.CommandNumPermission;

import java.util.Objects;

public class Command {

    private int id = 0;
    private String command;
    private CommandNumPermission permission;
    private int usage;
    private long cooldown;

    public Command(String command, CommandNumPermission permission, int usage, long cooldown) {
        this.command = command;
        this.permission = permission;
        this.usage = usage;
        this.cooldown = cooldown;
    }

    public Command(int id, String command, CommandNumPermission permission, int usage, long cooldown) {
        this.id = id;
        this.command = command;
        this.permission = permission;
        this.usage = usage;
        this.cooldown = cooldown;
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

    public long getCooldown() {
        return cooldown;
    }

    public void incrementUsage() {
        this.usage++;
    }

    public void setPermission(CommandNumPermission permission) {
        this.permission = permission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, command, permission, usage, cooldown);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Command)) {
            return false;
        }
        Command command = (Command) obj;
        return this.id == command.id &&
                Objects.equals(this.command, command.command) &&
                Objects.equals(this.permission, command.permission) &&
                Objects.equals(this.usage, command.usage) &&
                Objects.equals(this.cooldown, command.cooldown);
    }
}
