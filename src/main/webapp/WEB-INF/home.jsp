<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
    <h2>Welcome Home, ${username}</h2>
    Your first name: ${userFirstName} <br/>
    Your last name: ${userLastName} <br/>
    <table style="width:100%">
        <tr>
            <th>Username</th>
            <th>Actions</th>
        </tr>
        ${usernameList}
    </table>
    <h3>${removeStatus}</h3>
    <h3>
        <a href="/user/add">add user</a>
    </h3>
    <h3>
        <a href="/logout">logout</a>
    </h3>
</body>
</html>
