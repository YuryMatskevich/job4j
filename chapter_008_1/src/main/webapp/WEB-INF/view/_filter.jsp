<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form action="${pageContext.servletContext.contextPath}/doSearch" method="POST">
        <jsp:include page="_error.jsp"/>
        <div class="form-group">
            <input type="checkbox" class="form-check-input" name="today" id="today" value="today">за сегодняшний
        </div>
        <div class="form-group">
            <input type="checkbox" class="form-check-input" name="withphoto" id="withphoto" value="withphoto">c фото
        </div>
        <div class="form-group">
            <label for="sort">Марка:</label>
            <select class="form-control" id="sort" name="sort">
                <option selected></option>
                <c:forEach items="${applicationScope['sorts']}" var="sort">
                    <option value="<c:out value="${sort}"/>"><c:out value="${sort}"/></option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Seatch</button>
    </form>
</div>