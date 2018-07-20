<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <style> <%@include file="/css/first.css" %></style>
</head>
<body>
    <div class="content">
        <h3>Login in a system</h3>
        <form action="${pageContext.servletContext.contextPath}/signin" method="post">
            <c:if test="${error != ''}">
                <div class="errorMessage">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <ul style="list-style-type: none">
                <li><span>login:</span><input type="text" name="login"></li>
                <li><span>password:</span><input type="password" name="password"></li>
            </ul>
            <a href="/create">Create a new user</a>
            <input class="button" type="submit" value="login">
        </form>
    </div>
</body>
</html>
