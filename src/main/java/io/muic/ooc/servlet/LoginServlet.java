/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.servlet;

import io.muic.ooc.service.SecurityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(SecurityService.isAuthorized(request)){
            response.sendRedirect("/webapp/home");
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
            rd.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (SecurityService.authenticate(username, password, request)) {
                response.sendRedirect("/webapp/home");
            } else {
                String error = "Wrong username or password.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
                rd.include(request, response);
            }
        } else {
            String error = "Username or password is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
            rd.include(request, response);
        }
    }
}
