<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <h1>Updating user</h1>
     <form action="${pageContext.servletContext.contextPath}/update" method="post">
        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
        Name: <input type="text" name="name" value="<c:out value="${user.name}"/>"><br><br>
        Login: <input type="text" name="login" value="<c:out value="${user.login}"/>"><br><br>
        Email: <input type="text" name="email" value="<c:out value="${user.email}"/>"><br><br>
        <input type="submit" value="update">
    </form>
</body>
</html>
