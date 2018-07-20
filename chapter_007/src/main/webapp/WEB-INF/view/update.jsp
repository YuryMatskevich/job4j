<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>update</title>
    <style> <%@include file="/css/first.css" %></style>
</head>
<body>
    <div class="content">
        <h3>Updating user</h3>
         <form action="${pageContext.servletContext.contextPath}/update" method="post">
            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
             <c:if test="${error != ''}">
                 <div class="errorMessage">
                     <c:out value="${error}"/>
                 </div>
             </c:if>
             <ul style="list-style-type: none">
                 <li><span>Name:</span><input type="text" name="name" value="<c:out value="${user.name}"/>"></li>
                 <li><span>Login:</span><input type="text" name="login" value="<c:out value="${user.login}"/>"></li>
                 <li><span>Email:</span><input type="text" name="email" value="<c:out value="${user.email}"/>"></li>
                 <li><span>Password:</span><input type="text" name="password" value="<c:out value="${user.password}"/>"></li>
                 <c:if test="${activeUser.role == initParam['roleAdmin'] and user.id != activeUser.id}">
                     <li>
                         <span>Role:</span>
                         <select name="roles">
                             <option value="${initParam['roleAdmin']}">Administrator</option>
                             <option value="${initParam['roleUser']}">User</option>
                         </select>
                     </li>
                 </c:if>
             </ul>
             <a href="/users">Go back</a>
            <input class="button" type="submit" value="update">
        </form>
    </div>
</body>
</html>
