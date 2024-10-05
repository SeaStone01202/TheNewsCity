package com.thenews.repository.impl;

import com.thenews.entity.Category;
import com.thenews.repository.CategoryDAO;
import com.thenews.util.JdbcManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO{

    private final JdbcManagement jm = new JdbcManagement();

    @Override
    public int save(Category category){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try{
            conn = jm.getConnection();
            String query = "insert into Categories (Name) values(?)";
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setString(1, category.getCategoryName());
            result = ps.executeUpdate();
            if(result > 0){
                conn.commit();
            }
        } catch (Exception exception){
            if(conn != null){
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException(rollbackException);
                }
            }
            throw new RuntimeException(exception);
        } finally {
            if(conn != null){
                try{
                    jm.closeConnection(conn,ps);
                } catch (SQLException closeException) {
                    throw new RuntimeException(closeException);
                }
            }
        }
        return result;
    }

    @Override
    public int update(Category category) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try{
            String query = "update Categories set Name=? where Id=?";
            conn = jm.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setString(1, category.getCategoryName());
            ps.setString(2, String.valueOf(category.getCategoryId()));
            result = ps.executeUpdate();
            if(result > 0){
                conn.commit();
            }
        } catch (Exception exception) {
            if(conn != null){
                try{
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException(rollbackException);
                }
            }
            throw new RuntimeException(exception);
        } finally {
            if(conn != null){
                try{
                    jm.closeConnection(conn,ps);
                } catch (SQLException closeException) {
                    throw new RuntimeException(closeException);
                }
            }
        }
        return result;
    }

    @Override
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try{
            String query = "delete from Categories where Id=?";
            conn = jm.getConnection();
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            ps.setInt(1, id);
            result = ps.executeUpdate();
            if(result > 0){
                conn.commit();
            }
        } catch (Exception exception){
            if(conn != null){
                try{
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    throw new RuntimeException(rollbackException);
                }
            }
        } finally {
            if(conn != null){
                try{
                    jm.closeConnection(conn, ps);
                } catch (SQLException closeException) {
                    throw new RuntimeException(closeException);
                }
            }
        }
        return result;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public Category findByName(String categoryName) {
        return null;
    }
}
