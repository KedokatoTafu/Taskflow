package taskflow.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:taskflow.db";

    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS todos ("
                   + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "  title TEXT NOT NULL,"
                   + "  completed INTEGER DEFAULT 0,"
                   + "  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                   + ");";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo Database: " + e.getMessage());
        }
    }
}
