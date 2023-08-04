package com.example.assignment.controller;

import com.example.assignment.model.*;
import com.example.assignment.service.AccountService;
import com.example.assignment.service.EmployeeDetailsService;
import com.example.assignment.service.EmployeeService;
import com.example.assignment.utils.HttpUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/admin")
public class AdminRestController extends HttpServlet {

    private EmployeeService employeeService = EmployeeService.getInstance();
    private EmployeeDetailsService employeeDetailsService = EmployeeDetailsService.getInstance();
    private AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String searchTxt = req.getParameter("searchTxt");
        String filterBy = req.getParameter("filterBy");
        Integer pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));

        if ("id".equals(filterBy)) {
            List<EmployeeDetails> employeeDetails = employeeDetailsService.getEmployeeDetailsById(searchTxt);
            String objectResponse = HttpUtil.gson.toJson(employeeDetails);
            out.print(objectResponse);
        } else {
            //List<Employee> employees = employeeService.searchEmployee(searchTxt, filterBy);
            ListEmployeeResponse employeeResponse = employeeService.filterEmployeeAndPaging(
                    searchTxt,
                    filterBy,
                    pageIndex,
                    pageSize
            );
            String objectResponse = HttpUtil.gson.toJson(employeeResponse);
            out.print(objectResponse);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ApiResponse apiResponse;

        EmployeeDetails employeeDetails = HttpUtil.convertToObject(req.getReader(), EmployeeDetails.class);
        try {
            employeeService.updateEmployee(employeeDetails.getEmployee());
            accountService.updateAccount(employeeDetails.getAccount());

            apiResponse = new ApiResponse("Update employee success!");
        }catch (Exception e){
            apiResponse = new ApiResponse(e.getMessage());
        }

        out.print(HttpUtil.gson.toJson(apiResponse));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ApiResponse apiResponse;
        Integer employeeId = null;

        EmployeeDetails employeeDetails = HttpUtil.convertToObject(req.getReader(), EmployeeDetails.class);
        try {
            employeeId = employeeService.addEmployee(employeeDetails.getEmployee());
            Account account = employeeDetails.getAccount();
            account.setEmployeeId(employeeId);
            accountService.addAccount(account);

            apiResponse = new ApiResponse("Add employee success!");
        }catch (Exception e){
            if(employeeId != null){
                employeeService.deleteEmployeeById(employeeId);
            }
            apiResponse = new ApiResponse(e.getMessage());
        }

        out.print(HttpUtil.gson.toJson(apiResponse));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        DeleteEmployeeRequest deleteEmployeeRequest = HttpUtil.convertToObject(
                req.getReader(),
                DeleteEmployeeRequest.class
        );
        accountService.deleteAccountByEmployeeId(deleteEmployeeRequest.getEmployeeId());
        employeeService.deleteEmployeeById(deleteEmployeeRequest.getEmployeeId());

        ApiResponse apiResponse = new ApiResponse("Delete employee success!");
        out.print(HttpUtil.gson.toJson(apiResponse));
    }
}
