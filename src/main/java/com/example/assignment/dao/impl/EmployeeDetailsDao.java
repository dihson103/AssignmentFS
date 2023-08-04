package com.example.assignment.dao.impl;

import com.example.assignment.dao.IEmployeeDetailsDao;
import com.example.assignment.mapper.EmployeeDetailsMapper;
import com.example.assignment.model.EmployeeDetails;

import java.util.List;

public class EmployeeDetailsDao extends AbstractDao<EmployeeDetails> implements IEmployeeDetailsDao {

    private EmployeeDetailsMapper employeeDetailsMapper = new EmployeeDetailsMapper();

    @Override
    public List<EmployeeDetails> getEmployeeDetailsById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select * ");
        sql.append("from Employee e join Account a ");
        sql.append("on e.employee_id = a.employee_id ");
        sql.append("where e.employee_id = ?");
        return query(sql.toString(), employeeDetailsMapper, id);
    }
}
