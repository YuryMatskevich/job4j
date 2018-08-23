<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Update a new ad</title>
</head>
<body>
<div class="container">
    <h3 class="text-center">Updating a new ad</h3>
    <form action="${pageContext.servletContext.contextPath}/updateAd" method="POST">
        <jsp:include page="_error.jsp"/>
        <input type="hidden" name="id" value="<c:out value="${ad.id}"/>">
        <div class="form-group">
            <label for="sort">Марка:</label>
            <select class="form-control" id="sort" name="sort">
                <c:forEach items="${applicationScope['sorts']}" var="sort">
                    <option value="<c:out value="${sort}"/>" <c:if test="${ad.car.sort == sort}">selected</c:if>><c:out value="${sort}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="frame">Тип кузова:</label>
            <select class="form-control" id="frame" name="frame">
                <c:forEach items="${applicationScope['frames']}" var="frame">
                    <option value="<c:out value="${frame}"/>" <c:if test="${ad.car.frame == frame}">selected</c:if>><c:out value="${frame}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="engine">Двигатель:</label>
            <select class="form-control" id="engine" name="engine">
                <c:forEach items="${applicationScope['engines']}" var="engine">
                    <option value="<c:out value="${engine}"/>" <c:if test="${ad.car.engine == engine}">selected</c:if>><c:out value="${engine}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="transmission">Коробка передач:</label>
            <select class="form-control" id="transmission" name="transmission">
                <c:forEach items="${applicationScope['transmissions']}" var="transmission">
                    <option value="<c:out value="${transmission}"/>" <c:if test="${ad.car.transmission == transmission}">selected</c:if>><c:out value="${transmission}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="manipulator">Сторона руля:</label>
            <select class="form-control" id="manipulator" name="manipulator">
                <c:forEach items="${applicationScope['manipulators']}" var="manipulator">
                    <option value="<c:out value="${manipulator}"/>" <c:if test="${ad.car.manipulator == manipulator}">selected</c:if>><c:out value="${manipulator}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="age">Возраст машины, лет:</label>
            <input type="text" class="form-control" name="age" id="age" value="${ad.car.age}">
        </div>
        <div class="form-group">
            <label for="way">Пробег, км.:</label>
            <input type="text" class="form-control" name="way" id="way" value="${ad.car.way}">
        </div>
        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea class="form-control" name="description" id="description"><c:out value="${ad.describe}"/></textarea>
        </div>
        <div class="form-group">
            <label for="price">Цена, руб:</label>
            <input type="text" class="form-control" name="price" id="price" value="${ad.price}">
        </div>
        <div class="form-check">
            <label class="form-check-label">
                <input type="checkbox" class="form-check-input" name="sold" id="sold" value="sold">Отметить как продано
            </label>
        </div>
        <div class="form-row text-center">
            <div class="col-12" style="margin-bottom: 20px">
                <a href="${pageContext.servletContext.contextPath}/carsList" class="alert-link text-center">
                    <small>Go back</small>
                </a>
            </div>
        </div>
        <div class="form-row text-center">
            <div class="col-12">
                <button type="submit" class="btn btn-primary">Update</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
