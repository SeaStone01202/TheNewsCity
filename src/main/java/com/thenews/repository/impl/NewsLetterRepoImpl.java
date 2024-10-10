package com.thenews.repository.impl;

import com.thenews.entity.NewsLetter;
import com.thenews.repository.NewsLetterRepository;
import com.thenews.utils.ConnectionManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsLetterRepoImpl implements NewsLetterRepository {

    private final ConnectionManagement connectionManager;

    public NewsLetterRepoImpl(ConnectionManagement jdbcManagement) {
        this.connectionManager = jdbcManagement;
    }

    @Override
    public NewsLetter save(NewsLetter entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "INSERT INTO NewsLetters (Email) VALUES(?)";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setString(1, entity.getEmail());
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed while saving the newsletter - " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Failed to save newsletter - " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IO error occurred while saving the newsletter - " + exception.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    connectionManager.closeConnection(ps, conn);
                }
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close resources - " + closeException.getMessage());
            }
        }
        return null;
    }

    @Override
    public NewsLetter update(NewsLetter entity) {
        throw new UnsupportedOperationException("Update operation is not implemented yet.");
    }

    @Override
    public boolean delete(Integer integer) {
        throw new UnsupportedOperationException("Delete operation is not implemented yet.");
    }

    @Override
    public List<NewsLetter> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<NewsLetter> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM NewsLetters";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                NewsLetter entity = new NewsLetter();
                entity.setNewsletterId(rs.getInt("Id"));
                entity.setEmail(rs.getString("Email"));
                entity.setEnabled(rs.getBoolean("Enabled"));
                list.add(entity);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to retrieve newsletters - " + exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException("IO error occurred while retrieving newsletters - " + exception.getMessage());
        } finally {
            connectionManager.closeConnection(ps, rs, conn);
        }
        return list;
    }
}
