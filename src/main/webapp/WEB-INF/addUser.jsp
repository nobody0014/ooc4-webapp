<html>
    <body>
        <h2><a href="/home">Home</a></h2>
        <h3>Add User Page</h3>
        <form action="/user/add" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br/>
            First Name:<br/>
            <input type="text" name="firstName">
            <br/>
            Last Name:<br/>
            <input type="text" name="lastName">
            <br><br>
            <input type="submit" value="Submit">
        </form>
        <p>${addUserStatus}</p>
    </body>
</html>