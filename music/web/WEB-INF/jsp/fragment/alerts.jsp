<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 22-Jan-21
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>


<c:if test="${not empty requestScope.infoMessage}">
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong><fmt:message key="label.info" bundle="${loc}"/></strong>
        <fmt:message key="${requestScope.infoMessage}" bundle="${loc}"/>

    </div>
</c:if>
<c:if test="${not empty requestScope.errorMessage}">
    <div class="alert alert-danger fade in alert-dismissible show">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true" style="font-size:20px">&times;</span>
        </button>
        <strong><fmt:message key="label.error" bundle="${loc}"/></strong>
        <fmt:message key="${requestScope.errorMessage}" bundle="${loc}"/>
    </div>
</c:if>
<c:if test="${not empty requestScope.successMessage}">
    <div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong><fmt:message key="label.success" bundle="${loc}"/></strong>
        <fmt:message key="${requestScope.successMessage}" bundle="${loc}"/>
    </div>
</c:if>