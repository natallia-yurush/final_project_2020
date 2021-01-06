<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 29-Dec-20
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--<fmt:setLocale value="${sessionScope.local}" scope="session"/>--%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>


<html>
<head>
    <title><fmt:message key="title.login" bundle="${loc}"/></title>
    <link rel="shortcut icon" href='<c:url value="/resource/img/wallpaper-music.png"/>' type="image/png">
    <link type="text/css" rel="stylesheet" href='<c:url value="/resource/css/loginpage.css"/>'>
</head>

<body>

<div id="head">
    <nav>
        <ul>
            <jsp:include page="../fragment/nav-language.jsp">
                <jsp:param name="page" value="login"/>
            </jsp:include>
        </ul>
    </nav>
</div>


<div class="server-answer">
    <c:if test="${not empty requestScope.parametersInfo}">
        <p class="infos">${sessionScope.parametersInfo}</p>
    </c:if>
</div>

<form class="form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=login">

    <h1><fmt:message key="label.signIn" bundle="${loc}"/></h1>
    <div class="input-form">
        <input type="text" name="login" placeholder=<fmt:message key="label.login" bundle="${loc}"/> required>
    </div>
    <div class="input-form">
        <input type="password" name="password" placeholder=<fmt:message key="label.password" bundle="${loc}"/> required>
    </div>

    <c:if test="${not empty requestScope.errorAuthorisation}">
        <div class="errorInfo">
            <c:out value="${requestScope.errorAuthorisation}"/>
        </div>
    </c:if>


    <div class="input-form">
        <input type="submit" value=<fmt:message key="label.signIn" bundle="${loc}"/>>
    </div>

    <p><fmt:message key="label.noAccount" bundle="${loc}"/></p>
    <a href="${pageContext.servletContext.contextPath}/controller?command=signup"><fmt:message key="label.signUp" bundle="${loc}"/></a>

</form>


</body>
</html>
