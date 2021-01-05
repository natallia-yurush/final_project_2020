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

    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="../fragment/loading.jsp"/>

<div id="wrapper" data-scrollable="true">

    <jsp:include page="../fragment/left-aside-client.jsp"/>

    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp"/>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">
            <div class="row section">
                <div class="col-xl-10 mx-auto">

                        <div class="col-xl-8 col-md-7">
                            <div class="card h-auto">
                                <div class="card-body">
                                    <form action="${pageContext.servletContext.contextPath}/controller?command=profile" method="post" class="row">
                                        <div class="col-xl-6 form-group">
                                            <label for="firstName" class="form-label">First Name</label>
                                            <input type="text" id="firstName" class="form-control" value="${user.firstName}">
                                            <%--TODO: везде поставить нормальные значения value--%>
                                        </div>
                                        <div class="col-xl-6 form-group">
                                            <label for="lastName" class="form-label">Last Name</label>
                                            <input type="text" id="lastName" class="form-control" value="${user.lastName}">
                                        </div>
                                        <div class="col-12 form-group">
                                            <label for="email" class="form-label">Email</label>
                                            <input type="email" id="email" class="form-control" value="${user.email}">
                                        </div>
                                        <div class="col-xl-6 form-group">
                                            <label for="login" class="form-label">Login</label>
                                            <input type="text" id="login" class="form-control" value="${user.login}">
                                        </div>
                                        <div class="col-xl-6 form-group">
                                            <label for="password" class="form-label">Password</label>
                                            <input id="password" type="password" class="form-control" value="${user.password}">
                                        </div>
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary btn-air">Save Changes</button>
                                        </div>
                                        <%--TODO: запросить пароль может быть?--%>
                                    </form>
                                </div>
                            </div>
                            <%--TODO: подписка--%>
                            <div class="plan-info-card text-center px-sm-5 py-sm-4 p-3">
                                <h6>No plan selected yet</h6>
                                <p>Your 30 days free subscription is going to expired within 2 days please select you plan.</p>
                                <a href="plan.html" class="btn btn-pill btn-air btn-success">Select Plan</a>
                            </div>
                        </div>

                </div>
            </div>
        </div>

        <footer id="footer" class="bg-img"></footer>
        <jsp:include page="../fragment/audio-player.jsp"/>

    </main>
    <!-- End | Page Wrapper -->

    <!-- Back Drop -->
<%--    <div class="backdrop header-backdrop"></div>
    <div class="backdrop sidebar-backdrop"></div>--%>

    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/assets/js/vendors.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>

</div>
</body>
</html>
