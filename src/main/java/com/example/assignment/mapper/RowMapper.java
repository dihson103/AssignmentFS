package com.example.assignment.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T MapRow(ResultSet rs) throws SQLException;
}