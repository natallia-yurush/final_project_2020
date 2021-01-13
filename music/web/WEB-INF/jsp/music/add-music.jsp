<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 07-Jan-21
  Time: 00:16
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
    <link href='<c:url value="/assets/plugin/chosen.css"/>' rel="stylesheet" type="text/css"/>

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

        <jsp:include page="../fragment/header.jsp"/>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">
            <div class="row section">
                <div class="col-xl-8 col-md-10 mx-auto">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Add Song</h6>
                        </div>
                        <div class="card-body">
                            <form method="post" enctype="multipart/form-data" acceptcharset="UTF-8"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addMusic">
                                <div class="form-row form-group">
                                    <label for="songName" class="col-md-4 text-md-right col-form-label">Song
                                        Name</label>
                                    <div class="col-md-7">
                                        <input type="text" id="songName" name="songName" class="form-control">
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Genre</label>
                                    <div class="col-md-7">
                                        <%--<select class="form-control chosen-select">--%>
                                        <select data-placeholder="Choose a Genre..." class="form-control chosen-select"
                                                name="genre">
                                            <c:forEach var="item" items="${sessionScope.genres}">
                                                <option>${item}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label for="songFile" class="col-md-4 text-md-right col-form-label">Song
                                        File</label>
                                    <div class="col-md-7">
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="songFile" name="songFile">
                                            <label class="custom-file-label" for="songFile">Choose file</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Artist Name</label>
                                    <div class="col-md-7">
                                        <select data-placeholder="Choose a Artist(s)..."
                                                class="form-control chosen-select" multiple name="artistsName">

                                            <c:forEach var="item" items="${requestScope.artistsName}">
                                                <option>${item.artistName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Album</label>
                                    <div class="col-md-7">
                                        <select class="form-control chosen-select" name="album">

                                            <%--TODO: разобраться, как вывести альбомы артиста--%>
                                            <option>Single</option>
                                            <c:forEach var="item" items="${requestScope.albums}">
                                                <option>${item.albumName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-4"></div>
                                        <div class="col-md-7">
                                            <button type="submit" class="btn btn-brand btn-air">Save Song</button>
                                            <button type="button" class="btn btn-outline-secondary">Clear Form</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <c:if test="${not empty requestScope.saveResult}">
                                <div class="errorInfo"><c:out value="${requestScope.saveResult}"/></div>
                            </c:if>

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
    <script src="${pageContext.request.contextPath}/assets/plugin/chosen.jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/chosen.proto.js"></script>
    <script>
        $(document).ready(function () {
            $(".chosen-select").chosen();
        });
    </script>

</div>
</body>
</html>
