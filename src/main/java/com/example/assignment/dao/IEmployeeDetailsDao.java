package com.example.assignment.dao;

import com.example.assignment.model.EmployeeDetails;

import java.util.List;

public interface IEmployeeDetailsDao {
    List<EmployeeDetails> getEmployeeDetailsById(String id);
}
