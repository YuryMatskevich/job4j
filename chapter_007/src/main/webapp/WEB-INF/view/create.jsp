<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>creat</title>
</head>
<body>
    <h1>Creating a new user</h1>
    <form action="${pageContext.servletContext.contextPath}/create" method="POST">
        Name: <input type="text" name="name"><br><br>
        Login: <input type="text" name="login"><br><br>
        Email: <input type="text" name="email"><br><br>
        <input type="submit" value="create">
    </form>
</body>
</html>
