package io.muic.ooc.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import io.muic.ooc.service.DatabaseQueryService;
import io.muic.ooc.service.SecurityService;
import org.apache.commons.lang.StringUtils;


/**
 * Created by wit on 2/15/2017 AD.
 */
public class RemoveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = SecurityService.isAuthorized(request);
        if (authorized) {
            String username = (String) request.getSession().getAttribute("username");
            String removeUsername = request.getParameter("removeUsername");
            if (StringUtils.isBlank(removeUsername) || StringUtils.equals(username,removeUsername)
                    || !DatabaseQueryService.isUserExists(removeUsername)){
                response.sendRedirect("/home");
            }else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/removeConfirmation.jsp");
                request.setAttribute("username",removeUsername);
                rd.include(request, response);
            }
        } else {
            response.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SecurityService.isAuthorized(request)){
            String removeUsername = request.getParameter("removeUsername");
            if (DatabaseQueryService.removeUserQuery(removeUsername)){
                request.getSession().setAttribute("removeStatus", "Removed " + removeUsername + " successfully");
            }else {
                request.getSession().setAttribute("removeStatus", "Error trying to remove " + removeUsername);
            }
            response.sendRedirect("/user");
        }else{
            response.sendRedirect("/home");
        }

    }
}
