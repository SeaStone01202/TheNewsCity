package com.thenews.repository.impl;

import com.thenews.entity.User;
import com.thenews.repository.UserRepository;
import com.thenews.util.ConnectionManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepository {

    private final ConnectionManagement connectionManagement;

    public UserRepoImpl(ConnectionManagement connectionManagement) {
        this.connectionManagement = connectionManagement;
    }

    @Override
    public User save(User entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "insert into Users (Fullname, Password, Username, Birthday, Gender, Mobile, Email) " +
                    "values(?,?,?,?,?,?,?)";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, entity.getFullname());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getUsername());
            ps.setDate(4, entity.getBirthday());
            ps.setBoolean(5, entity.isGender());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getEmail());
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Rollback failed due to SQL error: " + ex.getMessage());
            }
            throw new RuntimeException("Failed to save user. SQL error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred while managing connection: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close connection. SQL error: " + closeException.getMessage());
            }
        }
        return null;
    }

    @Override
    public User update(User entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE Users SET Fullname = ?, Password = ?, Username = ?, Birthday = ?, Gender = ?, Mobile = ?, Email = ? WHERE Id = ?";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, entity.getFullname());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getUsername());
            ps.setDate(4, entity.getBirthday());
            ps.setBoolean(5, entity.isGender());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getEmail());
            ps.setInt(8, entity.getUserId());
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed due to SQL error: " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Failed to update user. SQL error: " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IOException occurred while managing connection: " + exception.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close connection. SQL error: " + closeException.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "delete from Users where Id = ?";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            conn.setAutoCommit(false);
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException exception) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackException) {
                throw new RuntimeException("Rollback failed due to SQL error: " + rollbackException.getMessage());
            }
            throw new RuntimeException("Failed to delete user. SQL error: " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IOException occurred while managing connection: " + exception.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close connection. SQL error: " + closeException.getMessage());
            }
        }
    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("Id"));
                user.setFullname(rs.getString("Fullname"));
                user.setPassword(rs.getString("Password"));
                user.setUsername(rs.getString("Username"));
                user.setBirthday(rs.getDate("Birthday"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPhone(rs.getString("Mobile"));
                user.setEmail(rs.getString("Email"));
                user.setRole(rs.getBoolean("Role"));
                list.add(user);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to retrieve users. SQL error: " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IOException occurred while managing connection: " + exception.getMessage());
        } finally {
            connectionManagement.closeConnection(ps, rs, conn);
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User entity = null;
        try {
            String query = "SELECT * FROM Users WHERE Id = ?";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                entity = new User();
                entity.setUserId(rs.getInt("Id"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setPassword(rs.getString("Password"));
                entity.setUsername(rs.getString("Username"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setPhone(rs.getString("Mobile"));
                entity.setEmail(rs.getString("Email"));
                entity.setRole(rs.getBoolean("Role"));
            }
            if (entity != null) {
                return entity;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find user by ID. SQL error: " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IOException occurred while managing connection: " + exception.getMessage());
        } finally {
            if (conn != null) {
                connectionManagement.closeConnection(ps, rs, conn);
            }
        }
        return null;
    }
}