<%--
  Created by IntelliJ IDEA.
  User: wit
  Date: 2/16/2017 AD
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
    <h2>
        <a href="/webapp/home">Home</a>
    </h2>

    <form action="/webapp/user/edit?editingUsername=${editingUsername}&editingFirstName=${editingFirstName}&editingLastName=${editingLastName}" method="post">
        Current Username: ${editingUsername}
        <br/>
        Desired Username: <input type="text" name="username"/>
        <br/>
        Current first name: ${editingFirstName}
        <br/>
        Desired first name: <input type="text" name="firstName"/>
        <br/>
        Current last name: ${editingLastName}
        <br/>
        Desired last name: <input type="text" name="lastName"/>
        <br/>
        <input type="submit" value="Submit">
        ${updateStatus}
    </form>
</body>
</html>