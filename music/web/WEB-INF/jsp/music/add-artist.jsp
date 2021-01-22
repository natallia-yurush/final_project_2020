<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 08-Jan-21
  Time: 15:52
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

    <jsp:include page="../fragment/left-aside-admin.jsp"/>

    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="addArtistPage"/>
        </jsp:include>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="row section">
                <div class="col-xl-8 col-md-10 mx-auto">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">
                                <fmt:message key="label.addArtist" bundle="${loc}"/>
                            </h6>
                        </div>
                        <div class="card-body">
                            <form method="post" enctype="multipart/form-data"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addArtist">
                                <div class="form-row form-group">
                                    <label for="artistName" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.artistName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <input type="text" id="artistName" name="artistName" class="form-control" required>
                                    </div>
                                </div>
                                <div class="form-row form-group">
                                    <label for="artistImage" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.artistImage" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="artistImage"
                                                   name="artistImage" required>
                                            <label class="custom-file-label" for="artistImage">
                                                <fmt:message key="label.chooseFile" bundle="${loc}"/>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-4"></div>
                                        <div class="col-md-7">
                                            <button type="submit" class="btn btn-brand btn-air">
                                                <fmt:message key="label.saveArtist" bundle="${loc}"/>
                                            </button>
                                            <button type="button" class="btn btn-outline-secondary">
                                                <fmt:message key="label.clearForm" bundle="${loc}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <footer id="footer" class="bg-img"></footer>

    </main>

    <!-- Scripts -->
    <code>$(".chosen-select").chosen()</code>

    <script src="${pageContext.request.contextPath}/assets/js/vendors.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>

</div>
</body>
</html>
