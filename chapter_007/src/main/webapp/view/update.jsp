<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <h1>Updating user</h1>
    <%
        String path = request.getContextPath();
        User user = (User) request.getAttribute("user");
    %>
    <form action="<%= path %>/update" method="post">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        Name: <input type="text" name="name" value="<%= user.getName() %>"><br><br>
        Login: <input type="text" name="login" value="<%= user.getLogin() %>"><br><br>
        Email: <input type="text" name="email" value="<%= user.getEmail() %>"><br><br>
        <input type="submit" value="update">
    </form>
</body>
</html>
