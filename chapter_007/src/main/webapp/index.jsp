<%@ page import="ru.job4j.crud.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <%
            String path = request.getContextPath();
            List<User> users = (List<User>)request.getAttribute("users");
            if (users != null) {
                for (User user : users) {
                int id = user.getId();
        %>
        <tr>
            <td><%= user.toString()%></td>
            <td>
                <form action="<%= path %>/update" method="get">
                    <input type="hidden" name="id" value="<%= id %>">
                    <input type="submit" value="update" style="width:100%">
                </form>
                <form action="<%= path %>/users" method="post">
                    <input type="hidden" name="id" value="<%= id %>">
                    <input type="submit" value="delete" style="width:100%">
                </form>
            </td>
        </tr>
        <%
            }
        }
        %>
    </table><br>
    <form action="<%= path %>/create" method="get">
        <input type="submit" value="create">
    </form>
</body>
</html>
