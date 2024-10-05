package com.thenews.repository.impl;

import com.thenews.entity.NewsLetter;
import com.thenews.repository.NewsLetterDAO;
import com.thenews.util.JdbcManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsLetterDAOImpl implements NewsLetterDAO {

    private final JdbcManagement jdbcManagement;

    public NewsLetterDAOImpl(JdbcManagement jdbcManagement) {
        this.jdbcManagement = jdbcManagement;
    }

    @Override
    public int save(NewsLetter newsletter) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "INSERT INTO NewsLetters (Email) values(?)";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newsletter.getEmail());
            conn.setAutoCommit(false);
            ps.setString(1, newsletter.getEmail());
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (Exception exception) {
            if (conn != null) {
                try{
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback exception - " + rollbackException);
                }
            }
            throw new RuntimeException("Save exception - " + exception);
        } finally {
            try{
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close exception - " + closeException);
            }
        }
        return result;
    }

    @Override
    public List<NewsLetter> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<NewsLetter> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM NewsLetters";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                NewsLetter entity = new NewsLetter();
                entity.setNewsletterId(rs.getInt("Id"));
                entity.setEmail(rs.getString("Email"));
                entity.setEnabled(rs.getBoolean("Enabled"));
                list.add(entity);
            }
        } catch (Exception exception) {
            if (conn != null) {
                try{
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback exception - " + rollbackException);
                }
            }
            throw new RuntimeException("Find all exception - " + exception);
        } finally {
            try{
                jdbcManagement.closeConnection(ps, rs, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close exception - " + closeException);
            }
        }
        return list;
    }

    @Override
    public NewsLetter findByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        NewsLetter entity = null;
        try {
            String query = "SELECT TOP 1 * FROM NewsLetters WHERE Email = ?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                entity = new NewsLetter();
                entity.setNewsletterId(rs.getInt("Id"));
                entity.setEmail(rs.getString("Email"));
                entity.setEnabled(rs.getBoolean("Enabled"));
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback exception - " + rollbackException);
                }
            }
            throw new RuntimeException("Find by email exception - " + e);
        }
        return entity;
    }
}

