package poke_api_packages.poke_api;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

// responsible for cache responses from online API, and therefore helping with speeds and limit api calls (300 per day)
@Slf4j
class Database {

    private static Database db = null;

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    private final String DB_NAME = "jdbc:sqlite:C:\\TwitchBotFiles\\Databases\\PokeApiCache.db";
    private final String TABLE_NAME = "information";

    public Database() {
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        try {
            DriverManager.getConnection(DB_NAME);
        } catch (Exception e) {
            log.error("Failed creating db: {}", e.getMessage());
        }
    }

    private void createTable() {
        // SQLite connection string

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n"
                + "	url text NOT NULL UNIQUE,\n"
                + "	response text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (Exception e) {
            log.error("Failed creating table: {}", e.getMessage());
        }
    }

    private Connection getConnection() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_NAME);
        } catch (Exception e) {
            log.error("Failed getting connection to db: {}", e.getMessage());
        }
        return conn;
    }

    public boolean insert(String url, String response) {
        url = url.toLowerCase();
        String sql = "INSERT INTO " + TABLE_NAME + "(url,response) VALUES(?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, url);
            pstmt.setString(2, response);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            log.error("Failed inserting into db: {}", e.getMessage());
            return false;
        }
    }

    public String getByUrl(String url) {
        url = url.toLowerCase();
        String sql = "SELECT response "
                + "FROM " + TABLE_NAME + " WHERE url = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the value
            pstmt.setString(1, url);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return rs.getString("response");
            }
            return null;
        } catch (SQLException e) {
            log.error("Failed getting URL from db: {}", e.getMessage());
            return null;
        }
    }
}