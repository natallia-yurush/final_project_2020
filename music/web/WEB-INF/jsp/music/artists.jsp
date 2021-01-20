<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 16-Jan-21
  Time: 14:14
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

<!-- Begin | Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
<div id="wrapper" data-scrollable="true">


    <%--<jsp:include page="../fragment/left-aside-admin.jsp"/>--%>
    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
        <jsp:include page="../fragment/left-aside-client.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
        <jsp:include page="../fragment/left-aside-admin.jsp"/>
    </c:if>


    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="artists"/>
        </jsp:include>

        <!-- Page Banner [[ Find at scss/base/core.scss ]] -->
        <div class="banner bg-song"></div>

        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container" id="appRoute">


            <c:if test="${not empty requestScope.songsList}">
            <!-- Begin | Section [[ Find at scss/base/core.scss ]] -->
            <div class="section">

                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">


                            <c:choose>
                                <c:when test="${not empty param.albumName}">
                                    <h4><fmt:message key="label.album" bundle="${loc}"/> <c:out value="${param.albumName}"/>. <fmt:message key="label.songs" bundle="${loc}"/></h4>
                                </c:when>
                                <c:otherwise>
                                    <h4><fmt:message key="label.artist" bundle="${loc}"/> <c:out value="${param.artistName}"/>. <fmt:message key="label.songs" bundle="${loc}"/></h4>
                                </c:otherwise>
                            </c:choose>


                                <%--<h4><c:out value="${param.artistName}"/> Songs</h4>--%>
                            <p><fmt:message key="label.listenEnjoy" bundle="${loc}"/></p>
                        </div>
                        <hr>
                    </div>
                </div>
                <!-- Begin | Custom List [[ Find at scss/framework/components/custom-list.scss ]] -->
                <div class="section custom-list">
                    <!-- Begin | Custom List Item -->
                    <div class="custom-list--item">
                        <div class="text-dark custom-card--inline">
                            <table>
                                <c:forEach items="${requestScope.songsList}" var="song">
                                    <tr>
                                        <td>
                                            <div class="custom-card--inline-img">
                                                <img src="${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}" alt="${song.album.artist.imagePath}"
                                                     class="card-img--radius-sm">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="custom-card--inline-desc">
                                                <p class="text-truncate mb-0"><c:out
                                                        value="${ song.trackName }"/></p>
                                                <p class="text-truncate text-muted font-sm"><c:out
                                                        value="${ song.album.artist.artistName }"/></p>
                                            </div>
                                        </td>

                                        <td>
                                            <audio preload="auto" controls>
                                                <source src='${pageContext.request.contextPath}/resource/songs/${song.trackPath}'/>
                                            </audio>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </table>


                        </div>

                    </div>
                    <!-- End | Custom List Item -->
                </div>


                <div class="section for-genres">
                    <div class="heading">
                        <div class="d-flex flex-wrap align-items-end">
                            <div class="flex-grow-1">
                                <h4><fmt:message key="label.albums" bundle="${loc}"/></h4>
                                <p><c:out value="${param.artistName}"/></p>
                            </div>
                        </div>
                        <hr>
                    </div>

                    <div class="carousel-item-6 arrow-pos-1">

                        <c:forEach items="${requestScope.albums}" var="album">
                            <div class="custom-card">
                                <div class="custom-card--img">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${param.artistName}&albumName=${album.albumName}">
                                        <img src="resource/img/album1.jpg"
                                             class="card-img--radius-md">
                                        <span class="bg-blur"><c:out value="${album.albumName}"/></span>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>
                </c:if>


                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4><fmt:message key="label.artists" bundle="${loc}"/></h4>
                        </div>
                    </div>
                    <hr>
                </div>

                <div class="section row">
                    <c:forEach items="${requestScope.artistsList}" var="artist">
                        <div class="col-xl-3 col-lg-4 col-sm-6 pb-4">
                            <div class="custom-card">
                                <div class="custom-card--img">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${artist.artistName}">
                                        <img src="${pageContext.request.contextPath}/resource/img/artists/${artist.imagePath}"
                                             class="card-img--radius-lg">
                                    </a>
                                </div>

                                <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${artist.artistName}"
                                   class="custom-card--link mt-2">
                                    <h6 class="mb-0"><c:out value="${artist.artistName}"/></h6>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>


            </div>


            <!--TODO: удалить верхнее объявление дива и редачить нижнее!-->

            <!-- End | Main Container -->

            <!-- Begin | Footer [[ Find at scss/framework/base/footer/footer.scss ]] -->
            <footer id="footer" class="bg-img">

            </footer>
            <!-- End | Footer -->

            <%--TODO: playeer--%>
            <%--
            <jsp:include page="../fragment/audio-player.jsp"/>--%>

    </main>
    <!-- End | Page Wrapper -->

</div>
<!-- End | Wrapper -->

<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

<!-- Scripts -->


</body>
</html>