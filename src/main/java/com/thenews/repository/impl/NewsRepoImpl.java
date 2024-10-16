package com.thenews.repository.impl;

import com.thenews.entity.News;
import com.thenews.repository.NewsRepository;
import com.thenews.utils.ConnectionManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsRepoImpl implements NewsRepository {

    private final ConnectionManagement connectionManagement;

    public NewsRepoImpl(ConnectionManagement connectionManagement) {
        this.connectionManagement = connectionManagement;
    }

    @Override
    public News save(News entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO news (Title, Content, Image, PostedDate, Author, CategoryId, Home) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getContent());
            ps.setString(3, entity.getImage());
            ps.setDate(4, entity.getPostedDate());
            ps.setString(5, entity.getAuthorId());
            ps.setInt(6, Integer.parseInt(entity.getCategoryId()));
            ps.setBoolean(7, entity.getIsHome());
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            } else {
                throw new RuntimeException("Insert operation failed: No rows were inserted.");
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            throw new RuntimeException("Database error during save operation: " + exception.getMessage(), exception);
        } catch (IOException e) {
            throw new RuntimeException("I/O error during save operation: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException ex) {
                throw new RuntimeException("Error while closing resources: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public News update(News entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE News SET Title = ?, Content = ?, Image = ?, PostedDate = ?, CategoryId = ?, Home = ? WHERE Id = ?";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getContent());
            ps.setString(3, entity.getImage());
            ps.setDate(4, entity.getPostedDate());
            ps.setString(5, entity.getCategoryId());
            ps.setBoolean(6, entity.getIsHome());
            ps.setInt(7, entity.getNewsId());
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            } else {
                throw new RuntimeException("Update operation failed: No rows were updated.");
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Database error during update operation: " + exception.getMessage(), exception);
        } catch (IOException e) {
            throw new RuntimeException("I/O error during update operation: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException ex) {
                throw new RuntimeException("Error while closing resources: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "DELETE FROM news WHERE Id = ?";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return true;
            } else {
                return false; // No rows affected
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Database error during delete operation: " + exception.getMessage(), exception);
        } catch (IOException e) {
            throw new RuntimeException("I/O error during delete operation: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                connectionManagement.closeConnection(ps, conn);
            } catch (SQLException ex) {
                throw new RuntimeException("Error while closing resources: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public List<News> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<News> newsList = new ArrayList<>();
        try {
            String query = "SELECT * FROM News";
            connectionManagement.init();
            conn = connectionManagement.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsId(rs.getInt("Id"));
                news.setTitle(rs.getString("Title"));
                news.setContent(rs.getString("Content"));
                news.setImage(rs.getString("Image"));
                news.setPostedDate(rs.getDate("PostedDate"));
                news.setAuthorId(rs.getString("Author"));
                news.setCategoryId(rs.getString("CategoryId"));
                news.setIsHome(rs.getBoolean("Home"));
                newsList.add(news);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Database error during findAll operation: " + exception.getMessage(), exception);
        } catch (IOException ex) {
            throw new RuntimeException("I/O error during findAll operation: " + ex.getMessage(), ex);
        } finally {
            connectionManagement.closeConnection(ps, rs, conn);
        }
        return newsList;
    }
}