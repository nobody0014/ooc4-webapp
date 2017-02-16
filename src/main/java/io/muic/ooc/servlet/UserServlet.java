/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.servlet;

import io.muic.ooc.service.DatabaseQueryService;
import io.muic.ooc.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author gigadot
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = SecurityService.isAuthorized(request);
        if (authorized) {

            List<String> usernameList = DatabaseQueryService.getUsernameList();
            String mainUsername = (String)request.getSession().getAttribute("username");
            String usernameListString = constructHtmlUsernameList(usernameList, mainUsername);
            List<String> userInfo = DatabaseQueryService.getUserInformation(mainUsername);

            request.setAttribute("username",mainUsername);
            request.setAttribute("usernameList",usernameListString);
            request.setAttribute("userFirstName", userInfo.get(1));
            request.setAttribute("userLastName", userInfo.get(2));


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            if (request.getSession().getAttribute("removeStatus") != null){
                request.setAttribute("removeStatus", request.getSession().getAttribute("removeStatus"));
                request.getSession().removeAttribute("removeStatus");
            }
            rd.include(request, response);

        } else {
            response.sendRedirect("/webapp/login");
        }
    }

    private String constructHtmlUsernameList(List<String> ul, String mainUsername){
        StringBuilder usernameListString = new StringBuilder();
        for (String username : ul){
            usernameListString.append("<tr><td>" + username + "</td><td>");
            if (!mainUsername.equals(username)){
                //a tag for removing this user
                usernameListString.append("<a href=\"/webapp/user/remove?removeUsername=" + username + "\">remove</a>   ");
                //a tag for editing this user information
                usernameListString.append("<a href=\"/webapp/user/edit?editingUsername=" + username + "\">edit</a></td></tr>");
            }
        }
        return usernameListString.toString();
    }

}
