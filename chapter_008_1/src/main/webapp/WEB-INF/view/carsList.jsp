<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Users</title>
</head>
<body>
    <div class="container">
        <jsp:include page="_menu.jsp"/>
        <div>
            <div class="row">
                <div class="col-sm-12">
                    <c:forEach items="${ads}" var="ad">
                        <div class="row" style="border:solid 2px black;">
                            <div class="col-sm-4 col text-center">
                                <img src="${pageContext.servletContext.contextPath}/loadPhoto?id=<c:out value="${ad.id}"/>" alt="Нет фото">
                            </div>
                            <div class="col-sm-8">
                                <div class="row">
                                    <div class="col-sm-8">
                                        Марка: <c:out value="${ad.car.sort}"/><br>
                                        Тип кузова: <c:out value="${ad.car.frame}"/><br>
                                        Тип двигателя: <c:out value="${ad.car.engine}"/><br>
                                        Коробка передач: <c:out value="${ad.car.transmission}"/><br>
                                        Сторона руля: <c:out value="${ad.car.manipulator}"/><br>
                                        Возраст машины: <c:out value="${ad.car.age}"/> лет<br>
                                        Пробег: <c:out value="${ad.car.way}"/> км.<br>
                                        Описание: <c:out value="${ad.describe}"/><br>
                                    </div>
                                    <div class="col-sm-4 col text-center">
                                        <c:if test="${logenedUser != null}">
                                            <div class="row">
                                                <c:if test="${myCar != null}">
                                                    <form action="${pageContext.servletContext.contextPath}/updateAd"
                                                          method="GET">
                                                        <input type="hidden" name="id" value="<c:out value="${ad.id}"/>">
                                                        <button type="submit" class="btn btn-primary">Update</button>
                                                    </form>
                                                </c:if>
                                            </div>
                                            <div class="row">
                                                <c:if test="${logenedUser.role == 'ADMIN'}">
                                                    <form action="${pageContext.servletContext.contextPath}/carsList"
                                                          method="POST">
                                                        <input type="hidden" name="id" value="<c:out value="${ad.id}"/>">
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                    </form>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4"><c:out value="${ad.timeString}"/></div>
                                    <div class="col-sm-4">Статус:
                                        <c:choose>
                                            <c:when test="${ad.state}">Продано</c:when>
                                            <c:otherwise>Продается</c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-sm-4"><p class="font-weight-bold"><c:out value="${ad.price}"/> р.</p></div>
                                </div>
                            </div>
                        </div><br>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${logenedUser != null}">
                <div class="form-row text-center" style="margin-top: 20px">
                    <div class="col-12">
                        <form action="${pageContext.servletContext.contextPath}/createAd" method="GET">
                            <button type="submit" class="btn btn-success">Create</button>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
