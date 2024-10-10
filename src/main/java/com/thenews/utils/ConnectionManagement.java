package com.thenews.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagement {

    private String urlDatabase;

    private String userDatabase;

    private String passwordDatabase;

    // Phương thức khởi tạo để đọc thông tin từ tệp db.properties
    public void init() throws IOException {
        loadDatabaseProperties();
        loadDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Phương thức đọc thông tin kết nối từ tệp properties
    private void loadDatabaseProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("db.properties not found");
            }
            properties.load(input);
        }

        // Lấy thông tin kết nối từ properties
        this.urlDatabase = properties.getProperty("url");
        this.userDatabase = properties.getProperty("user");
        this.passwordDatabase = properties.getProperty("password");
    }

    // Phương thức lấy kết nối
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlDatabase, userDatabase, passwordDatabase);
    }

    // Phương thức đóng tài nguyên
    public void closeConnection(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.err.println("Error closing resource: " + e.getMessage());
                }
            }
        }
    }
}
