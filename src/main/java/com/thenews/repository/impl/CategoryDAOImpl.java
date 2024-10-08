package com.thenews.repository.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryDAO;
import com.thenews.util.JdbcManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private final JdbcManagement jdbcManagement;

    public CategoryDAOImpl(JdbcManagement jdbcManagement) {
        this.jdbcManagement = jdbcManagement;
    }

    @Override
    public int save(Category category) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "INSERT INTO Categories (Name) VALUES(?)";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            conn.setAutoCommit(false);
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (SQLException saveException) {
            throw new RuntimeException("Save exception - " + saveException);
        } finally {
            try {
                conn.setAutoCommit(true);
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Close exception - " + closeException);
            }
        }
        return result;
    }

    @Override
    public int update(Category category) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "UPDATE Categories SET Name=? WHERE Id=?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed: - " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Update failed: - " + exception.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Closing resources failed: - " + closeException.getMessage());
            }
        }
        return result;
    }

    @Override
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            String query = "DELETE FROM Categories WHERE Id=?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setInt(1, id);
            result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed: - " + rollbackException);
                }
            }
            throw new RuntimeException("Delete failed: - " + exception.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
                jdbcManagement.closeConnection(ps, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Closing resources failed: - " + closeException);
            }
        }
        return result;
    }

    @Override
    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Categories";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category entity = new Category();
                entity.setCategoryId(rs.getInt("Id"));
                entity.setCategoryName(rs.getString("Name"));
                list.add(entity);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("FindAll failed: - " + exception.getMessage());
        } finally {
            try {
                jdbcManagement.closeConnection(ps, rs, conn);
            } catch (SQLException closeException) {
                throw new RuntimeException("Closing resources failed: - " + closeException);
            }
        }
        return list;
    }

    @Override
    public Category findById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category entity = null;
        try {
            String query = "SELECT TOP 1 * FROM Categories WHERE Id=?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                entity = new Category();
                entity.setCategoryId(rs.getInt("Id"));
                entity.setCategoryName(rs.getString("Name"));
            }
        } catch (Exception exception) {
            throw new RuntimeException("FindById failed: - " + exception.getMessage());
        } finally {
            if (conn != null) {
                try {
                    jdbcManagement.closeConnection(ps, rs, conn);
                } catch (SQLException closeException) {
                    throw new RuntimeException("Closing resources failed: - " + closeException.getMessage());
                }
            }
        }
        return entity;
    }

    @Override
    public Category findByName(String categoryName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category entity = null;
        try {
            String query = "SELECT TOP 1 * FROM Categories WHERE Name=?";
            conn = jdbcManagement.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                entity = new Category();
                entity.setCategoryId(rs.getInt("Id"));
                entity.setCategoryName(rs.getString("Name"));
            }
        } catch (Exception exception) {
            throw new RuntimeException("FindByName failed: - " + exception);
        } finally {
            if (conn != null) {
                try {
                    jdbcManagement.closeConnection(ps, rs, conn);
                } catch (SQLException closeException) {
                    throw new RuntimeException("Closing resources failed: - " + closeException);
                }
            }
        }
        return entity;
    }
}
