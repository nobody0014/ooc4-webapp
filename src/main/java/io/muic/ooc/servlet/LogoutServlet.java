package io.muic.ooc.servlet;

import io.muic.ooc.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wit on 2/15/2017 AD.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(SecurityService.isAuthorized(request)){
            SecurityService.logout(request);
        }
        response.sendRedirect("login");
    }
}
