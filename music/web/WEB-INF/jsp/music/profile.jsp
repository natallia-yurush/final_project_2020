<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 05-Jan-21
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>

<html>
<head>
    <title>Music Time</title>

    <!-- Favicon -->
    <link href='<c:url value="/resource/img/purple-img.png"/>' rel="icon"/>

    <!-- IOS Touch Icons -->

    <!-- Styles -->
    <link href='<c:url value="/assets/css/vendors.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/css/styles.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/resource/css/loginpage.css"/>' rel="stylesheet" type="text/css"/>

    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700" rel="stylesheet">

    <script src="assets/js/vendors.bundle.js"></script>
    <script src="assets/js/scripts.bundle.js"></script>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="../fragment/loading.jsp"/>

<div id="wrapper" data-scrollable="true">

    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
        <jsp:include page="../fragment/left-aside-client.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
        <jsp:include page="../fragment/left-aside-admin.jsp"/>
    </c:if>

    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <%--TODO: delete если изменишь подход к смене языка (switch)--%>
            <jsp:include page="../fragment/header.jsp"/>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">
            <div class="row section">
                <div class="col-xl-10 mx-auto">

                    <div class="col-xl-8 col-md-7">
                        <div class="card h-auto">
                            <div class="card-body">
                                <form action="${pageContext.servletContext.contextPath}/controller?command=profile"
                                      method="post" class="row">
                                    <div class="col-xl-6 form-group">
                                        <label for="firstName" class="form-label">
                                            <fmt:message key="label.firstName" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="firstName" name="firstName" class="form-control"
                                               value="${user.firstName}" required>
                                    </div>
                                    <c:if test="${not empty requestScope.invalidFirstName}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidFirstName}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="lastName" class="form-label">
                                            <fmt:message key="label.lastName" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="lastName" name="lastName" class="form-control" required
                                               value="${user.lastName}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidLastName}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidLastName}"/></div>
                                    </c:if>

                                    <div class="col-12 form-group">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" id="email" name="email" class="form-control" required
                                               value="${user.email}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidEmail}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidEmail}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="login" class="form-label">
                                            <fmt:message key="label.login" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="login" name="login" class="form-control" required
                                               value="${user.login}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidLogin}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidLogin}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="password" class="form-label">
                                            <fmt:message key="label.password" bundle="${loc}"/>
                                        </label>
                                        <input id="password" name="password" type="password" class="form-control"
                                               value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidPassword}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidPassword}"/></div>
                                    </c:if>
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-primary btn-air">
                                            <fmt:message key="label.saveChanges" bundle="${loc}"/>
                                        </button>
                                    </div>

                                    <c:if test="${not empty requestScope.successfulChanges}">
                                        <div class="errorInfo"><c:out value="${requestScope.successfulChanges}"/></div>
                                    </c:if>

                                    <%--TODO: запросить пароль может быть?--%>
                                </form>
                            </div>
                        </div>
                        <%--TODO: подписка--%>
                        <div class="plan-info-card text-center px-sm-5 py-sm-4 p-3">
                            <h6>No plan selected yet</h6>
                            <p>Your 30 days free subscription is going to expired within 2 days please select you
                                plan.</p>
                            <a href="plan.html" class="btn btn-pill btn-air btn-success">Select Plan</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <footer id="footer" class="bg-img"></footer>

    </main>
    <!-- End | Page Wrapper -->
</div>
    <!-- Back Drop -->
    <div class="backdrop header-backdrop"></div>
    <div class="backdrop sidebar-backdrop"></div>

    <!-- Scripts -->


</body>
</html>
