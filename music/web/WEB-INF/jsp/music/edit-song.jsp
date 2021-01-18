<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 18-Jan-21
  Time: 19:26
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


    <link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/audio-touch/audio-touch.css"/>

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

                    <c:set var="song" value="${requestScope.song}"/>


                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Edit Song</h6>
                        </div>
                        <div class="card-body">
                            <form method="post" enctype="multipart/form-data" acceptcharset="UTF-8"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addSong">
                                <div class="form-row form-group">
                                    <label for="songName" class="col-md-4 text-md-right col-form-label">Song
                                        Name</label>
                                    <div class="col-md-7">
                                        <input type="text" id="songName" name="songName" class="form-control"
                                               value="${song.trackName}" required>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Genre</label>
                                    <div class="col-md-7">
                                        <%--<select class="form-control chosen-select">--%>
                                        <select data-placeholder="Choose a Genre..." class="form-control chosen-select"
                                                name="genre" value="${song.genre}" required>
                                            <c:forEach var="genre" items="${sessionScope.genres}">
                                                <option>${genre.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label for="songFile" class="col-md-4 text-md-right col-form-label">Song
                                        File</label>
                                    <div class="col-md-7">
                                        <div class=" custom-file">
                                            <input type="file" class="custom-file-input" id="songFile" name="songFile"
                                                   required>
                                            <%--todo--%>
                                            <label class="custom-file-label" for="songFile" id="file">Choose
                                                file</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Artist Name</label>
                                    <div class="col-md-7">
                                        <select data-placeholder="Choose a Artist(s)..."
                                                required class="form-control chosen-select" multiple name="artistsName"
                                                value="${song.album.artist.artistName}">

                                            <c:forEach var="item" items="${requestScope.artistsName}">
                                                <option>${item.artistName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">Album</label>
                                    <div class="col-md-7">
                                        <select class="form-control chosen-select" name="album"
                                                value="${song.album.albumName}" required>

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

    </main>
    <!-- End | Page Wrapper -->
</div>
<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

<!-- Scripts -->


</body>
</html>

