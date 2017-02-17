<%--
  Created by IntelliJ IDEA.
  User: wit
  Date: 2/15/2017 AD
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<%String removeUsername =  request.getParameter("removeUsername");%>
Do you want to remove <%= removeUsername%> from the user list?
<form action="/user/remove?removeUsername=<%= removeUsername%>" method="post">
    <input type="submit" value="yes" />
</form>
<form  action="/home" method="get" >
    <input type="submit" value="no" />
</form>
</body>
</html>
