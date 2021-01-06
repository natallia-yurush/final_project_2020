<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 06-Jan-21
  Time: 23:50
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

    <link href='<c:url value="/resource/img/purple-img.png"/>' rel="icon"/>

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

<!-- Begin | Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
<div id="wrapper" data-scrollable="true">

    <jsp:include page="../fragment/left-aside-admin.jsp"/>


    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp"/>

        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container" id="appRoute">


        </div>
        <!--TODO: удалить верхнее объявление дива и редачить нижнее!-->

        <!-- End | Main Container -->

        <!-- Begin | Footer [[ Find at scss/framework/base/footer/footer.scss ]] -->
        <footer id="footer" class="bg-img">
        </footer>
        <!-- End | Footer -->

        <jsp:include page="../fragment/audio-player.jsp"/>

    </main>
    <!-- End | Page Wrapper -->

</div>
<!-- End | Wrapper -->

<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/assets/js/vendors.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
</body>
</html>
