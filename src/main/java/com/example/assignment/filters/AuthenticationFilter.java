package com.example.assignment.filters;

import com.example.assignment.model.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/api/admin/*"})
public class AuthenticationFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("account");

        if(account == null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
