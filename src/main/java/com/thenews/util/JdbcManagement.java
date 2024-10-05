package com.thenews.util;

import java.sql.*;

public class JdbcManagement {

    private final String URL_DATABASE = "jdbc:sqlserver://localhost;database=newscity;encrypt=false";

    private final String USER_DATABASE = "sa";

    private final String PASSWORD_DATABASE = "Admin123";

    // Just return connection, not working every thing
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_DATABASE, USER_DATABASE, PASSWORD_DATABASE);
    }

    // Close all object connection to Database
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void closeConnection(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
    }

    public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }

        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }

        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
    }
}
