package com.example.assignment.controller;

import com.example.assignment.model.Account;
import com.example.assignment.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    private AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getRequestURI();
        if (pathInfo != null && pathInfo.equals("/Assignment_war_exploded/logout")) {
            HttpSession session = req.getSession();
            session.setAttribute("account", null);
            resp.sendRedirect("login");
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("Username");
        String password = req.getParameter("password");

        Account account = accountService.login(username, password);

        if(account != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            resp.sendRedirect("admin");
        }else {
            req.setAttribute("mess", "Wrong email or password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
}
