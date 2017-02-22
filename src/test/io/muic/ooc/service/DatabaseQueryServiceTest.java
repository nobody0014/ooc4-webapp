package io.muic.ooc.service;

import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wit on 2/15/2017 AD.
 */
public class DatabaseQueryServiceTest {
    @Test
    public void isUserExists() throws Exception {
        assertEquals(true, DatabaseQueryService.isUserExists("admin"));
        assertEquals(false, StringUtils.isBlank("admin") || StringUtils.equals("dede","admin") || !DatabaseQueryService.isUserExists("admin"));
        System.out.println("password " + BCrypt.hashpw("password", BCrypt.gensalt()));
        System.out.println("pan " + BCrypt.hashpw("password", BCrypt.gensalt()));
        System.out.println("helloworld " + BCrypt.hashpw("password", BCrypt.gensalt()));
        System.out.println("do " + BCrypt.hashpw("password", BCrypt.gensalt()));
        System.out.println("dedjeiejiejdeijeidjeidjeidjeid" + BCrypt.hashpw("password", BCrypt.gensalt()));
    }


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Ignore
    public void addUserQuery() throws Exception {
        assertEquals(true,DatabaseQueryService.addUserQuery("hello","welcome","hello","hello"));
    }

    @Test
    @Ignore
    public void isDuplicateUsername() throws Exception {
        assertEquals(true,DatabaseQueryService.isDuplicateUsername("hello"));
        assertEquals(true,DatabaseQueryService.isDuplicateUsername("admin"));
        assertEquals(false,DatabaseQueryService.isDuplicateUsername("nice"));
    }

    @Test
    @Ignore
    public void removeUserQuery() throws Exception {
//        DatabaseQueryService.addUserQuery("hello","welcome","hello","hello");
        assertEquals(true,DatabaseQueryService.removeUserQuery("hello"));
        assertEquals(false,DatabaseQueryService.removeUserQuery("hello"));
    }

    @Test
    public void updateUserQuery() throws Exception{
//        assertEquals(true,DatabaseQueryService.updateUserQuery("hello"));
    }

    @Test
    public void getUsernameList() throws Exception {

    }

    @Test
    @Ignore
    public void getUserInformation() throws Exception {
        List<String> items = DatabaseQueryService.getUserInformation("admin");
        System.out.println(items.toString());
        assertEquals(3,items);

    }

    @Test
    public void authenticate() throws Exception {

    }

    @Test
    public void isAuthorized() throws Exception {

    }

    @Test
    public void closeObjects() throws Exception {

    }

    @Test
    public void closeObjects1() throws Exception {

    }


}