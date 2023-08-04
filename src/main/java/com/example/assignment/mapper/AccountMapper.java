package com.example.assignment.mapper;

import com.example.assignment.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account MapRow(ResultSet rs) throws SQLException {
        return Account.builder()
                .id(rs.getInt(1))
                .username(rs.getString(2))
                .email(rs.getString(3))
                .password(rs.getString(4))
                .status(rs.getInt(5) == 1)
                .employeeId(rs.getInt(6))
                .build();
    }
}
