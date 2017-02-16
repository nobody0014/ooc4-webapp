/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.service;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gigadot
 */
public  class SecurityService {


    public static boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        return (username != null && DatabaseQueryService.isAuthorized(username));
    }


    public static boolean authenticate(String username, String password, HttpServletRequest request) {
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)
                && DatabaseQueryService.authenticate(username,password)) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }
    public static void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
