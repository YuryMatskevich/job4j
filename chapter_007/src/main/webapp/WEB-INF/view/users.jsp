<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>users</title>
    <style> <%@include file="/css/second.css" %></style>

</head>
<body>
    <div class="wrapper">
        <div class="up">
            <div>
                <span>Hello, <c:out value="${activeUser.login}"/></span>
                <span>
                    <form action="${pageContext.servletContext.contextPath}/update" method="get">
                        <input type="hidden" name="id" value="<c:out value="${activeUser.id}"/>">
                        <input type="submit" value="update" style="width:100%">
                    </form>
                </span>
                <span>
                    <form action="${pageContext.servletContext.contextPath}/logout" method="get">
                        <input type="submit" value="logout">
                    </form>
                </span>
            </div>
        </div>
        <c:if test="${activeUser.role == initParam['roleAdmin']}">
            <div class="content">
                <h3>A user's table</h3>
                <table border="1" cellspacing="0">
                    <tr>
                        <th>user</th>
                        <th>action</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><c:out value="${user.login}"/></td>
                            <td>
                                <div class="button">
                                    <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                        <input type="submit" value="update" style="width:100%">
                                    </form>
                                </div>
                                <div class="button">
                                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                        <input type="submit" value="delete" style="width:100%">
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table><br>
                <form action="${pageContext.servletContext.contextPath}/create" method="get">
                    <span class="create"><input type="submit" value="create"></span>
                </form>
            </div>
        </c:if>
    </div>
</body>
</html>
