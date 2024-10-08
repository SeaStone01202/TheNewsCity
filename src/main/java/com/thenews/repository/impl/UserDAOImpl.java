package com.thenews.repository.impl;

import com.thenews.entity.User;
import com.thenews.repository.UserDAO;
import com.thenews.util.JdbcManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final JdbcManagement jdbcManagement;

    public UserDAOImpl(JdbcManagement jdbcManagement) {
        this.jdbcManagement = jdbcManagement;
    }

    @Override
    public void save(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "insert into Users (Fullname, Password, Username, Birthday, Gender, Mobile, Email) " +
                    "values(?,?,?,?,?,?,?)";
            conn = jdbcManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUsername());
            ps.setDate(4, user.getBirthday());
            ps.setBoolean(5, user.isGender());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
            conn.close();
        } catch (SQLException e) {
            try{
                conn.rollback();
            } catch (SQLException ex){
                throw new RuntimeException("Rollback failed - " + ex);
            }
                throw new RuntimeException("Error exception - " + e);
        } finally {
            try {
                conn.setAutoCommit(true);
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close Exception - " + closeException);
            }
        }
    }

    @Override
    public void update(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "UPDATE Users SET " +
                    "Fullname = ?," +
                    "Password = ?," +
                    "Username = ?," +
                    "Birthday = ?," +
                    "Gender = ?," +
                    "Mobile = ?," +
                    "Email = ?" +
                    "WHERE Id = ?";
            conn = jdbcManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUsername());
            ps.setDate(4, user.getBirthday());
            ps.setBoolean(5, user.isGender());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            ps.setInt(8, user.getUserId());
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (Exception exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback Exception - " + rollbackException);
                }
            }
            throw new RuntimeException("Exception - " + exception);
        } finally {
            try {
                conn.setAutoCommit(true);
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close Exception - " + closeException);
            }
        }
    }

    @Override
    public void delete(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "delete from Users where Id = ?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (Exception exception) {
            try {
                conn.rollback();
            } catch (SQLException rollbackException) {
                throw new RuntimeException("Rollback Exception - " + rollbackException);
            }
            throw new RuntimeException("Exception - " + exception);
        } finally {
            try {
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close Exception - " + closeException);
            }
        }
    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<User>();
        try {
            String query = "SELECT * FROM Users";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
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
        } catch (Exception exception) {
            throw new RuntimeException("Exception - " + exception);
        } finally {
            try {
                jdbcManagement.closeConnection(ps, rs, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close Exception - " + closeException);
            }
        }
        return list;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
