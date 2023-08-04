package com.example.assignment.service;

import com.example.assignment.dao.IEmployeeDao;
import com.example.assignment.dao.impl.EmployeeDao;
import com.example.assignment.model.Employee;
import com.example.assignment.model.ListEmployeeResponse;
import com.example.assignment.model.Page;
import com.example.assignment.utils.ValidationUtil;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    private static EmployeeService instance;
    private IEmployeeDao employeeDao;

    public static EmployeeService getInstance(){
        if (instance == null){
            instance = new EmployeeService();
        }
        return instance;
    };

    private EmployeeService(){
        employeeDao = new EmployeeDao();
    }

    private List<Employee> getAllEmployees (){
        return employeeDao.getAllEmployee();
    }

    public int addEmployee (Employee employeeDTO){
        validateEmployee(employeeDTO);
        return employeeDao.addEmployee(employeeDTO);
    }

    public void deleteEmployeeById(Integer id){
        employeeDao.deleteEmployeeById(id);
    }

    private List<Employee> handleSearch (String searchTxt, String searchBy){
        searchTxt = "%" + searchTxt + "%";
        switch (searchBy){
            case "name":
                return employeeDao.searchByName(searchTxt);
            case "address":
                return employeeDao.searchByAddress(searchTxt);
            case "date_of_birth":
                return employeeDao.searchByDob(searchTxt);
            case "phone":
                return employeeDao.searchByPhone(searchTxt);
            case "department":
                return employeeDao.searchByDepartment(searchTxt);
        }
        return null;
    }

    public List<Employee> searchEmployee (String searchTxt, String searchBy){
        if(searchTxt == null || searchTxt.isEmpty()){
            return getAllEmployees();
        }
        return handleSearch(searchTxt, searchBy);
    }

    public ListEmployeeResponse filterEmployeeAndPaging (String searchTxt, String searchBy, Integer pageIndex, Integer pageSize){
        List<Employee> employees = searchEmployee(searchTxt, searchBy);
        List<Employee> list;
        Page page = new Page(pageIndex, pageSize, employees.size());

        if(!employees.isEmpty()){
            int startIndex = page.getStartIndex();
            list = employees.stream()
                    .skip(startIndex)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        }else {
            list = null;
        }

        return ListEmployeeResponse.builder().employees(list).page(page).build();
    }

    public void updateEmployee (Employee employee){
        validateEmployee(employee);
        employeeDao.updateEmployee(employee);
    }

    private void validateEmployee (Employee employee){
        ValidationUtil.validateEmpty(employee.getFirstName(), "First name can not be empty!");
        ValidationUtil.validateEmpty(employee.getLastName(), "Last name can not be empty!");
        ValidationUtil.validateNull(employee.getGender(), "Gender can not be empty!");
        ValidationUtil.validateEmpty(employee.getDob(), "Date of birth can not be empty!");
        ValidationUtil.validatePhoneNumber(employee.getPhone(), "Wrong phone number!");
        ValidationUtil.validateEmpty(employee.getDepartment(), "Department can not be empty!");
    }
}
