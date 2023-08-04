package com.example.assignment.dao.impl;

import com.example.assignment.dao.IEmployeeDao;
import com.example.assignment.mapper.EmployeeDetailsMapper;
import com.example.assignment.mapper.EmployeeMapper;
import com.example.assignment.model.Employee;
import com.example.assignment.model.EmployeeDetails;

import java.util.List;

public class EmployeeDao extends AbstractDao<Employee> implements IEmployeeDao {

    private EmployeeMapper employeeMapper = new EmployeeMapper();
    @Override
    public List<Employee> getAllEmployee(){
        String sql = "Select * from Employee";
        return query(sql, employeeMapper);
    }

    @Override
    public int addEmployee(Employee employeeDTO){
        String sql = "Insert into Employee (first_name, last_name, gender, date_of_birth, phone, address, department_name, remark)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getGender() ? 1 : 0,
                employeeDTO.getDob(), employeeDTO.getPhone(), employeeDTO.getAddress(), employeeDTO.getDepartment(), employeeDTO.getRemark());
    }

    @Override
    public void deleteEmployeeById(Integer id){
        String sql = "DELETE FROM Employee where employee_id = ?";
        update(sql, id);
    }

    @Override
    public List<Employee> searchByName(String searchTxt){
        String sql = "Select * from Employee Where (first_name + ' ' + last_name) Like ?";
        return query(sql, employeeMapper, searchTxt);
    }

    @Override
    public List<Employee> searchByDob(String searchTxt){
        String sql = "Select * from Employee where date_of_birth Like ?";
        return query(sql, employeeMapper, searchTxt);
    }

    @Override
    public List<Employee> searchByAddress(String searchTxt){
        String sql = "Select * from Employee where [address] Like ?";
        return query(sql, employeeMapper, searchTxt);
    }

    @Override
    public List<Employee> searchByPhone(String searchTxt){
        String sql = "Select * from Employee where phone Like ?";
        return query(sql, employeeMapper, searchTxt);
    }

    @Override
    public List<Employee> searchByDepartment(String searchTxt){
        String sql = "Select * from Employee where department_name Like ?";
        return query(sql, employeeMapper, searchTxt);
    }

    @Override
    public void updateEmployee(Employee employee) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Employee ");
        sql.append("set first_name = ?, last_name = ?, gender = ?, date_of_birth = ?, ");
        sql.append("phone = ?, [address] = ?, department_name = ?, remark = ? ");
        sql.append("where employee_id = ?");
        update(sql.toString(), employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getDob(),
                employee.getPhone(), employee.getAddress(), employee.getDepartment(), employee.getRemark(), employee.getId());
    }
}
