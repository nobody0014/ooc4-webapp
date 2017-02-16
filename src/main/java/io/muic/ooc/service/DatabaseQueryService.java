package io.muic.ooc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wit on 2/15/2017 AD.
 */
public class DatabaseQueryService {



    public static boolean removeUserQuery(String username){
        Connection currentCon = null;
        PreparedStatement pst = null;
        int rs;
        String searchQuery = "delete from users where username=?";
        boolean isRemoved = false;
        try {
            currentCon = DatabaseConnectionService.getConnection();

            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);

            rs = pst.executeUpdate();
            if (rs > 0){
                isRemoved = true;
            }
        } catch (Exception ex) {
            System.out.println("Unable to remove user from the database: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,currentCon);
        }
        return isRemoved;
    }

    //
    public static boolean isUserExists(String username){
        return isDuplicateUsername(username);
    }

    public static boolean isDuplicateUsername(String username){
        Connection currentCon = null;
        PreparedStatement pst = null;
        ResultSet rs;
        String searchQuery = "select * from users where username=?";
        boolean isDuplicate = false;

        try {
            currentCon = DatabaseConnectionService.getConnection();
            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);
            rs = pst.executeQuery();
            boolean more = rs.next();
            if (more) {
                isDuplicate = true;
            }
        } catch (Exception ex) {
            System.out.println("Unable to check user from the database: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,currentCon);
        }
        return isDuplicate;
    }

    public static boolean addUserQuery(String username, String password, String firstName, String lastName){
        Connection currentCon = null;
        PreparedStatement pst = null;
        int rs;
        String searchQuery = "insert into users values (NULL,?,?,?,?)";
        boolean isAdded = false;
        try {
            currentCon = DatabaseConnectionService.getConnection();

            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,firstName);
            pst.setString(4,lastName);

            rs = pst.executeUpdate();
            if (rs > 0){
                isAdded = true;
            }
        } catch (Exception ex) {
            System.out.println("Unable to add user to the database: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,currentCon);
        }
        return isAdded;
    }

    public static boolean updateUserQuery(String username, String firstName, String lastName){
        Connection currentCon = null;
        PreparedStatement pst = null;
        int rs;
        String searchQuery = "update users set username=?, FirstName=?, LastName=? where username=?";
        boolean isUpdated = false;
        try {
            currentCon = DatabaseConnectionService.getConnection();

            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);
            pst.setString(2,firstName);
            pst.setString(3,lastName);
            pst.setString(4,username);

            rs = pst.executeUpdate();
            if (rs > 0){
                isUpdated = true;
            }
        } catch (Exception ex) {
            System.out.println("Unable to add user to the database: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,currentCon);
        }
        return isUpdated;
    }

    public static List<String> getUsernameList(){
        Connection currentCon = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> usernames = null;
        String searchQuery = "select * from users";

        try {
            currentCon = DatabaseConnectionService.getConnection();
            pst = currentCon.prepareStatement(searchQuery);
            rs = pst.executeQuery();
            usernames = new ArrayList<>();
            while (rs.next()){
                String username = rs.getString("username");
                usernames.add(username);
            }
        } catch (Exception ex) {
            System.out.println("Getting all username failed: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,rs,currentCon);
        }
        return usernames;
    }

    //Get desired user information
    //information slots, 0=username, 1=firstName, 2=lastName
    public static List<String> getUserInformation(String username){
        Connection currentCon = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> userInfo = null;
        String searchQuery = "select username, FirstName, LastName from users where username=?";

        try {
            currentCon = DatabaseConnectionService.getConnection();
            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1, username);
            rs = pst.executeQuery();
            userInfo = new ArrayList<>();
            while (rs.next()){
                userInfo.add(rs.getString("username"));
                userInfo.add(rs.getString("FirstName"));
                userInfo.add(rs.getString("LastName"));
            }
            System.out.println(userInfo.toString());
            System.out.println(userInfo.get(0) + " " + userInfo.get(1) + " " + userInfo.get(2));
        } catch (Exception ex) {
            System.out.println("Getting user information failed: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,rs,currentCon);
        }
        return userInfo;
    }



    public static boolean authenticate(String username, String password) {
        boolean authenticate = false;
        Connection currentCon = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String searchQuery = "select * from users where username=? AND password=?";

        try {
            currentCon = DatabaseConnectionService.getConnection();
            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            rs = pst.executeQuery();
            boolean more = rs.next();
            if (more) {

                authenticate = true;
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,rs,currentCon);
        }
        return authenticate;
    }

    public static boolean isAuthorized(String username) {
        boolean isAuthorized = false;
        Connection currentCon = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String searchQuery = "select * from users where username=?";
        try {
            currentCon = DatabaseConnectionService.getConnection();
            pst = currentCon.prepareStatement(searchQuery);
            pst.setString(1,username);
            rs = pst.executeQuery();
            boolean more = rs.next();
            if (more) {
                isAuthorized = true;
            }
        } catch (Exception ex) {
            System.out.println("Authorization failed: An Exception has occurred! " + ex);
        } finally {
            closeObjects(pst,rs,currentCon);
        }
        return isAuthorized;
    }

    public static void closeObjects(PreparedStatement pst, ResultSet rs, Connection currentCon){
        if (rs != null)	{
            try {
                rs.close();
            } catch (Exception e) {}
            rs = null;
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (Exception e) {}
            pst = null;
        }
        if (currentCon != null) {
            try {
                currentCon.close();
            } catch (Exception e) {
            }

            currentCon = null;
        }
    }
    public static void closeObjects(PreparedStatement pst, Connection currentCon){
        if (pst != null) {
            try {
                pst.close();
            } catch (Exception e) {}
            pst = null;
        }
        if (currentCon != null) {
            try {
                currentCon.close();
            } catch (Exception e) {
            }

            currentCon = null;
        }
    }
}
