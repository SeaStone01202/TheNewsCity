package com.thenews.repository.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryRepository;
import com.thenews.utils.ConnectionManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepoImpl implements CategoryRepository {

    private final ConnectionManagement connectionManager;

    public CategoryRepoImpl(ConnectionManagement connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Category save(Category entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO Categories (Name) VALUES(?)";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, entity.getCategoryName());
            conn.setAutoCommit(false);
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return entity;
            }
        } catch (SQLException saveException) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed during save operation - " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Failed to save category - " + saveException.getMessage());
        } catch (IOException loadPropertiesException) {
            throw new RuntimeException("IO error occurred while saving the category - " + loadPropertiesException.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    connectionManager.closeConnection(ps, conn);
                }
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close resources after save operation - " + closeException.getMessage());
            }
        }
        return null;
    }

    @Override
    public Category update(Category entity) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE Categories SET Name=? WHERE Id=?";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setString(1, entity.getCategoryName());
            ps.setInt(2, entity.getCategoryId());
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
                    throw new RuntimeException("Rollback failed during update operation - " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Failed to update category - " + exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO error occurred during update operation - " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    connectionManager.closeConnection(ps, conn);
                }
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close resources after update operation - " + closeException.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "DELETE FROM Categories WHERE Id=?";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                conn.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException exception) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException("Rollback failed during delete operation - " + rollbackException.getMessage());
                }
            }
            throw new RuntimeException("Failed to delete category - " + exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO error occurred during delete operation - " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    connectionManager.closeConnection(ps, conn);
                }
            } catch (SQLException closeException) {
                throw new RuntimeException("Failed to close resources after delete operation - " + closeException.getMessage());
            }
        }
    }

    @Override
    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Categories";
            connectionManager.init();
            conn = connectionManager.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category entity = new Category();
                entity.setCategoryId(rs.getInt("Id"));
                entity.setCategoryName(rs.getString("Name"));
                list.add(entity);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to retrieve categories - " + exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO error occurred during findAll operation - " + e.getMessage());
        } finally {
            connectionManager.closeConnection(ps, rs, conn);
        }
        return list;
    }

    @Override
    public Category findById(Integer id) {
        Category entity = null;
        List<Category> list = findAll();
        for (Category category : list) {
            if (category.getCategoryId().equals(id)) {
                entity = category;
            }
        }
        return entity;
    }
}