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
import java.util.List;

/**
 * Created by wit on 2/16/2017 AD.
 */
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SecurityService.isAuthorized(request)){
            String editingUsername = request.getParameter("editingUsername");
            List<String> userInfo = DatabaseQueryService.getUserInformation(editingUsername);
            if (userInfo != null && userInfo.size() > 0 && DatabaseQueryService.isUserExists(editingUsername)){
                String editingFirstName = userInfo.get(1);
                String editingLastName = userInfo.get(2);

                request.setAttribute("editingUsername",editingUsername);
                request.setAttribute("editingFirstName", editingFirstName);
                request.setAttribute("editingLastName", editingLastName);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editUser.jsp");
                rd.include(request, response);
            }else {
                response.sendRedirect("/home");
            }
        }else {
            response.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SecurityService.isAuthorized(request)){
            String editingUsername = request.getParameter("editingUsername");
            String editingFirstName = request.getParameter("editingFirstName");
            String editingLastName = request.getParameter("editingLastName");
            String desiredUsername = request.getParameter("username");
            String desiredFirstName = request.getParameter("firstName");
            String desiredLastName = request.getParameter("lastName");


            if (desiredUsername == null && desiredUsername.equals("")){
                desiredUsername = "";
            }
            if (!DatabaseQueryService.isDuplicateUsername(desiredUsername)){
                String usernameToUse, firstNameToUse, lastnameToUse;
                if (isValidInput(desiredUsername, 30)){
                    usernameToUse = desiredUsername;
                }else {
                    usernameToUse = editingUsername;
                }
                if (isValidInput(desiredFirstName, 20)){
                    firstNameToUse = desiredFirstName;
                }else {
                    firstNameToUse = editingFirstName;
                }
                if (isValidInput(desiredLastName, 20)){
                    lastnameToUse = desiredLastName;
                }else {
                    lastnameToUse = editingLastName;
                }

                if(DatabaseQueryService.updateUserQuery(usernameToUse,firstNameToUse,lastnameToUse, editingUsername)){
                    request.setAttribute("editingUsername",usernameToUse);
                    request.setAttribute("editingFirstName",firstNameToUse);
                    request.setAttribute("editingLastName",lastnameToUse);
                    request.setAttribute("updateStatus","Update successful");
                }else {
                    request.setAttribute("editingUsername",editingUsername);
                    request.setAttribute("editingFirstName",editingFirstName);
                    request.setAttribute("editingLastName",editingLastName);
                    request.setAttribute("updateStatus","Update failed");
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editUser.jsp");
                rd.include(request, response);
            }else {
                request.setAttribute("editingUsername",editingUsername);
                request.setAttribute("editingFirstName",editingFirstName);
                request.setAttribute("editingLastName",editingLastName);
                request.setAttribute("updateStatus","Update failed: could be duplicate username");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editUser.jsp");
                rd.include(request, response);
            }
        }else {
            response.sendRedirect("/home");
        }
    }

    private boolean isValidInput(String newString, int validLength){
        return !StringUtils.isEmpty(newString) && StringUtils.length(newString) <= validLength;
    }


}
