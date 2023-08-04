package com.example.assignment.dao;

import com.example.assignment.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> getAllEmployee();

    int addEmployee(Employee employeeDTO);

    void deleteEmployeeById(Integer id);

    List<Employee> searchByName(String searchTxt);

    List<Employee> searchByDob(String searchTxt);

    List<Employee> searchByAddress(String searchTxt);

    List<Employee> searchByPhone(String searchTxt);

    List<Employee> searchByDepartment(String searchTxt);

    void updateEmployee(Employee employee);
}
