package com.example.assignment.mapper;

import com.example.assignment.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee MapRow(ResultSet rs) throws SQLException {
        return Employee
                .builder()
                .id(rs.getInt(1))
                .firstName(rs.getString(2))
                .lastName(rs.getString(3))
                .gender(rs.getBoolean(4))
                .dob(rs.getString(5))
                .phone(rs.getString(6))
                .address(rs.getString(7))
                .department(rs.getString(8))
                .remark(rs.getString(9))
                .build();
    }
}
