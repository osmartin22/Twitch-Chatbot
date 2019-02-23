package ozmar.database.tables;

import ozmar.commands.Command;
import ozmar.database.tables.interfaces.CommandsTableInterface;
import ozmar.enums.CommandNumPermission;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandsTable extends Table implements CommandsTableInterface {

    private static final String COMMANDS_TABLE = "commandsTable";
    private static final String COLUMN_COMMAND_ID = "id";
    private static final String COLUMN_COMMAND_NAME = "commandName";
    private static final String COLUMN_COMMAND_PERMISSION = "commandPermission";
    private static final String COLUMN_COMMAND_USAGE = "commandUsage";
    private static final String COLUMN_COOLDOWN = "cooldown";

    private static final String CREATE_COMMANDS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + COMMANDS_TABLE + " (" +
                    COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_COMMAND_NAME + " TEXT, " +
                    COLUMN_COMMAND_PERMISSION + " INTEGER, " +
                    COLUMN_COMMAND_USAGE + " INTEGER DEFAULT 0, " +
                    COLUMN_COOLDOWN + " INTEGER DEFAULT 4000)";

    private static final String insertCommandSql =
            "INSERT INTO " + COMMANDS_TABLE + " (" +
                    COLUMN_COMMAND_NAME + ", " +
                    COLUMN_COMMAND_PERMISSION + ", " +
                    COLUMN_COOLDOWN + ") " +
                    " VALUES(?, ?, ?)";

    private static final String updateCommandUsageSql =
            "UPDATE " + COMMANDS_TABLE +
                    " SET " + COLUMN_COMMAND_USAGE + " = ? " +
                    " WHERE " + COLUMN_COMMAND_ID + " = ? " +
                    " VALUES(?, ?)";

    public CommandsTable() {
        createTable(CREATE_COMMANDS_TABLE);
        initializeCommands();
    }


    /**
     * Meant for first time use only
     * Creates and inserts a List of Commands to the table from the given text file
     */
    private void initializeCommands() {
        // File is assumed to be in the correct format
        int rowCount = getRowCount();
        if (rowCount == 0) {
            List<Command> commandList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\TwitchBotFiles\\commands.txt"))) {
                String line = br.readLine();
                while (line != null) {
                    String[] tokens = line.split("\\s+");
                    commandList.add(new Command(tokens[0], CommandNumPermission.valueOf(tokens[1]), 0, 4000));
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Failed opening commands file: " + e.getMessage());
            }
            insertCommandsList(commandList);
        }
    }

    private int getRowCount() {
        String sql = "SELECT COUNT(*) FROM " + COMMANDS_TABLE;
        Connection connection = openConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Failed to get number of rows: " + e.getMessage());
        }

        return -1;
    }


    /**
     * Fetches all the commands from the table
     *
     * @return List of Commands
     */
    @Nullable
    @Override
    public List<Command> queryCommands() {
        Connection connection = openConnection();

        List<Command> commandList = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + COMMANDS_TABLE)) {

            commandList = new ArrayList<>();
            while (resultSet.next()) {
                int commandId = resultSet.getInt(COLUMN_COMMAND_ID);
                String commandName = resultSet.getString(COLUMN_COMMAND_NAME);
                int commandPermissions = resultSet.getInt(COLUMN_COMMAND_PERMISSION);
                int usage = resultSet.getInt(COLUMN_COMMAND_USAGE);
                long cooldown = resultSet.getLong(COLUMN_COOLDOWN);

                commandList.add(new Command(commandId, commandName,
                        CommandNumPermission.values()[commandPermissions], usage, cooldown));
            }
        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return commandList;
    }

    /**
     * Inserts the list of commands into the table
     *
     * @param list list of commands
     */
    private void insertCommandsList(@Nonnull List<Command> list) {
        Connection connection = openConnectionCommitOff();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCommandSql)) {
            for (Command command : list) {
                preparedStatement.setString(1, command.getCommand());
                preparedStatement.setInt(2, command.getPermission().getCommandLevel());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Inserting command list failed: " + e.getMessage());
        } finally {
            closeConnectionCommitOn(connection);
        }
    }

    /**
     * Inserts the command into the table
     *
     * @param command Command
     */
    @Override
    public void insertCommand(@Nonnull Command command) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCommandSql)) {
            preparedStatement.setString(1, command.getCommand());
            preparedStatement.setInt(2, command.getPermission().getCommandLevel());
            preparedStatement.setLong(3, command.getCooldown());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Adding command failed: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Updates usage of a command
     *
     * @param command command to update the usage of
     */
    @Override
    public void updateCommandUsage(@Nonnull Command command) {
        Connection connection = openConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCommandUsageSql)) {
            preparedStatement.setInt(1, command.getUsage());
            preparedStatement.setInt(2, command.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Failed to update usage for " + command.getCommand() + " : " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
