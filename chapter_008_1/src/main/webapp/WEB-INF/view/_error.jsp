<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <c:if test="${errorMessage != ''}">
        <h4 class="text-center text-danger"><c:out value="${errorMessage}"/></h4>
    </c:if>
</div>