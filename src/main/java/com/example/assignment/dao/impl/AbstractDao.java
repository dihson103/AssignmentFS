package com.example.assignment.dao.impl;

import com.example.assignment.dao.GenericDao;
import com.example.assignment.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> implements GenericDao<T> {

    private final String serverName = "localhost";
    private final String dbName = "EmployeeAssignment";
    private final String portNumber = "1111";
    private final String userID = "sa";
    private final String password = "12345";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName;//+"; integratedSecurity=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> list = new ArrayList<>();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rowMapper.MapRow(rs));
            }
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            closeAll();
        }
    }

    private void setParameters(PreparedStatement ps, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(String sql, Object... parameters) {
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            setParameters(ps, parameters);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            closeAll();
        }
        return true;
    }

    @Override
    public int insert(String sql, Object... parameters) {
        int id = 0;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameters(ps, parameters);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            conn.commit();
            return id;
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } finally {
            closeAll();
        }
        return -1;
    }

    @Override
    public int count(String sql, Object... parameters) {
        int num = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll();
        }
        return num;
    }

    @Override
    public Object queryReturnNumber(String sql, Object... parameters) {
        Object number = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getObject(1);
            }
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            closeAll();
        }
        return number;
    }

    private void closeAll(){
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException exx) {
            System.out.println(exx.getMessage());
        }
    }

}
