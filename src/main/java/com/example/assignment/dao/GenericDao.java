package com.example.assignment.dao;

import com.example.assignment.mapper.RowMapper;

import java.util.List;

public interface GenericDao<T> {

    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    boolean update(String sql, Object... parameters);

    int insert(String sql, Object... parameters);
    
    int count(String sql, Object... parameters);

    Object queryReturnNumber(String sql, Object... parameters);
}