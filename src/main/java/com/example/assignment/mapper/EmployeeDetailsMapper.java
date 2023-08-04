package com.example.assignment.mapper;

import com.example.assignment.model.Account;
import com.example.assignment.model.Employee;
import com.example.assignment.model.EmployeeDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDetailsMapper implements RowMapper<EmployeeDetails> {
    @Override
    public EmployeeDetails MapRow(ResultSet rs) throws SQLException {
        Employee employee = Employee
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
        Account account = Account
                .builder()
                .id(rs.getInt(10))
                .username(rs.getString(11))
                .email(rs.getString(12))
                .password(rs.getString(13))
                .status(rs.getBoolean(14))
                .build();

        return EmployeeDetails
                .builder()
                .employee(employee)
                .account(account)
                .build();
    }
}
