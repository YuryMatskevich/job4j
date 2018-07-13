<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>creat</title>
</head>
<body>
    <h1>Creating a new user</h1>
    <% String path = request.getContextPath(); %>
    <form action="<%= path %>/create" method="POST">
        Name: <input type="text" name="name"><br><br>
        Login: <input type="text" name="login"><br><br>
        Email: <input type="text" name="email"><br><br>
        <input type="submit" value="create">
    </form>
</body>
</html>
