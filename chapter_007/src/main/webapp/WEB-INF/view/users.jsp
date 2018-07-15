<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>users</title>
</head>
<body>
    <h1>A user's table</h1>
    <table width="100%" border="1" cellpadding="4" cellspacing="0">
        <tr>
            <th>user</th>
            <th>action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/update" method="get">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                        <input type="submit" value="update" style="width:100%">
                    </form>
                    <form action="${pageContext.servletContext.contextPath}/" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                        <input type="submit" value="delete" style="width:100%">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type="submit" value="create">
    </form>
</body>
</html>
