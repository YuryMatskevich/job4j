<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Creat</title>
    <style> <%@include file="/css/first.css" %></style>
</head>
<body>
    <div class="content">
        <h3>Creating a new user</h3>
        <form action="${pageContext.servletContext.contextPath}/create" method="POST">
            <c:if test="${error != ''}">
                <div class="errorMessage">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <ul style="list-style-type: none">
                <li><span>Name:</span><input type="text" name="name"></li>
                <li><span>Login:</span><input type="text" name="login"></li>
                <li><span>Email:</span><input type="text" name="email"></li>
                <li><span>Password:</span><input type="text" name="password"></li>
                <c:if test="${activeUser.role == initParam['roleAdmin']}">
                    <li>
                        <span>Role:</span>
                        <select name="roles">
                            <option value="${initParam['roleAdmin']}">Administrator</option>
                            <option value="${initParam['roleUser']}" selected>User</option>
                        </select>
                    </li>
                </c:if>
            </ul>
            <a href="/signin">Go back</a>
            <input class="button" type="submit" value="create">
        </form>
    </div>
</body>
</html>
