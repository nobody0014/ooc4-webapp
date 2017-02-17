package io.muic.ooc.servlet;

import io.muic.ooc.service.DatabaseQueryService;
import io.muic.ooc.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by wit on 2/15/2017 AD.
 */
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SecurityService.isAuthorized(request)){
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addUser.jsp");
            rd.include(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }

    /**
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        //Check input
        String inputError = checkValidInputs(username,password,firstName,lastName);
        if (inputError.length() > 0){
            request.setAttribute("addUserStatus", inputError);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addUser.jsp");
            rd.include(request, response);
        }
        else{
            if (!DatabaseQueryService.isDuplicateUsername(username) && DatabaseQueryService.addUserQuery(username,password,firstName,lastName)){
                request.setAttribute("addUserStatus","User successfully added");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addUser.jsp");
                rd.include(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addUser.jsp");
                request.setAttribute("addUserStatus","Username " + username + " probably is taken");
                rd.include(request, response);
            }
        }

    }

    /**
     * @return errorString
     * return error to be printed on the screen if there's any problem.
     * Otherwise, return empty string.
     */
    private String checkValidInputs(String username, String password, String firstName, String lastName){
        StringBuilder errorString = new StringBuilder();

        if (StringUtils.isBlank(username)){
            errorString.append("Username is blank.\n");
        }
        if (StringUtils.isBlank(password)){
            errorString.append("Password is blank.\n");
        }
        if(StringUtils.isBlank(firstName)){
            errorString.append("First name is blank.\n");
        }
        if(StringUtils.isBlank(lastName)){
            errorString.append("Last name is blank.\n");
        }
        if(StringUtils.length(firstName) > 20){
            errorString.append("Length of first name is more than 20.\n");
        }
        if(StringUtils.length(lastName) > 20 ){
            errorString.append("Length of last name is more than 20.\n");
        }
        if(StringUtils.length(username) > 30){
            errorString.append("Length of username is more than 30.\n");
        }
        if (StringUtils.length(password) > 30){
            errorString.append("Length of password is more than 30.\n");
        }

        return errorString.toString();
    }
}
