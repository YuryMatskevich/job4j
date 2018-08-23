<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <ul class="nav justify-content-end">
        <c:if test="${loginedUser != null}">
            <li class="nav-item">
                <p class="nav-link">Hello, <c:out value="${loginedUser.name}"/></p>
            </li>
            <c:if test="${myCar == null}">
                <li class="nav-item">
                    <form action="${pageContext.servletContext.contextPath}/myCarsList" method="GET">
                        <input type="hidden" name="id" value="<c:out value="${loginedUser.id}"/>">
                        <button type="submit" class="btn btn-primary">My ads</button>
                    </form>
                </li>
            </c:if>
            <c:if test="${myCar != null}">
                <li class="nav-item">
                    <form action="${pageContext.servletContext.contextPath}/carsList" method="GET">
                        <input type="hidden" name="id" value="<c:out value="${loginedUser.id}"/>">
                        <button type="submit" class="btn btn-primary">All ads</button>
                    </form>
                </li>
            </c:if>
            <li class="nav-item">
                <form action="${pageContext.servletContext.contextPath}/logout" method="GET">
                    <button type="submit" class="btn btn-primary">Logout</button>
                </form>
            </li>
        </c:if>
        <c:if test="${loginedUser == null}">
            <li class="nav-item">
                <p class="nav-link">Hello, Goust</p>
            </li>
            <c:if test="${filter != null}">
                <li class="nav-item">
                    <form action="${pageContext.servletContext.contextPath}/carsList" method="GET">
                        <button type="submit" class="btn btn-primary">All ads</button>
                    </form>
                </li>
            </c:if>
            <li class="nav-item">
                <form action="${pageContext.servletContext.contextPath}/login" method="GET">
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </li>
        </c:if>
    </ul>
</div>