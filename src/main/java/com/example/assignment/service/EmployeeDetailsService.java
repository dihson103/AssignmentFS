package com.example.assignment.service;

import com.example.assignment.dao.IAccountDao;
import com.example.assignment.dao.IEmployeeDetailsDao;
import com.example.assignment.dao.impl.AccountDao;
import com.example.assignment.dao.impl.EmployeeDetailsDao;
import com.example.assignment.model.EmployeeDetails;

import java.util.List;

public class EmployeeDetailsService {

    private IEmployeeDetailsDao employeeDetailsDao;
    private static EmployeeDetailsService instance;

    public static EmployeeDetailsService getInstance(){
        if (instance == null){
            instance = new EmployeeDetailsService();
        }
        return instance;
    };

    private EmployeeDetailsService(){
        employeeDetailsDao = new EmployeeDetailsDao();
    }

    public List<EmployeeDetails> getEmployeeDetailsById (String id) {
        return employeeDetailsDao.getEmployeeDetailsById(id);
    }

}
