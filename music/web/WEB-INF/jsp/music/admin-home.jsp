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

        <jsp:include page="../fragment/header.jsp"/>

        <!-- Page Banner [[ Find at scss/base/core.scss ]] -->
        <div class="banner bg-song"></div>

        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container" id="appRoute">


            <!-- Begin | Section [[ Find at scss/base/core.scss ]] -->
            <div class="section">

                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4>Songs</h4>
                            <p>Listen and Enjoy</p>
                        </div>
                        <%--<a href="songs.html" class="btn btn-sm btn-pill btn-air btn-primary">View All</a>--%>
                    </div>
                    <hr>
                </div>
                <!-- Begin | Custom List [[ Find at scss/framework/components/custom-list.scss ]] -->
                <div class="section custom-list">
                    <!-- Begin | Custom List Item -->
                    <div class="custom-list--item">
                        <div class="text-dark custom-card--inline">

                            <table>

                                <c:forEach items="${requestScope.songs}" var="song">
                                    <tr>
                                        <td>
                                            <div class="custom-card--inline-img">

                                                <c:url value='/resource/img/artists/${song.album.artist.imagePath}'
                                                       var="img"/>

                                                <c:url value='${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}'
                                                       var="imgUrl"/>


                                                    <%--
                                                     <img scr="<c:url value='/resource/img/artists/${song.album.artist.imagePath}'/>" class="card-img--radius-sm"/>

                                                     <img scr="${ img }" class="card-img--radius-sm"/>
                                                    <img scr='/resource/img/artists/${song.album.artist.imagePath}' class="card-img--radius-sm"/>
                                                    img src="${pageContext.request.contextPath}/resource/img/artists/palina_respublika.jpg"
                                                                                                     class="card-img--radius-sm">
                                                    --%>

                                                <img src="${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}"
                                                     class="card-img--radius-sm">

                                            </div>
                                        </td>
                                        <td>
                                            <div class="custom-card--inline-desc">
                                                <p class="text-truncate mb-0"><c:out value="${ song.trackName }"/></p>
                                                <p class="text-truncate text-muted font-sm"><c:out
                                                        value="${ song.album.artist.artistName }"/></p>
                                            </div>
                                        </td>

                                        <td>
                                            <audio preload="auto" controls>
                                                    <%--<c:url value="${pageContext.request.contextPath}/resource/songs/${song.trackPath}" var="songUrl" />--%>
                                                <source src='${pageContext.request.contextPath}/resource/songs/${song.trackPath}'/>
                                            </audio>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>


                        </div>
                        <ul class="custom-card--labels d-flex ml-auto">
                            <%--TODO: появляется, если в избранном--%>
                            <li><span class="badge badge-pill badge-danger"><i class="la la-heart"></i></span></li>

                            <%--<li>05:03</li>--%>

                            <li class="dropleft">
                                <a href="javascript:void(0);" class="btn btn-icon-only p-0 w-auto h-auto"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="la la-ellipsis-h"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-item">
                                        <a href="javascript:void(0);" class="dropdown-link favorite">
                                            <i class="la la-heart-o"></i>
                                            <span>Favorite</span>
                                        </a>
                                    </li>
                                    <li class="dropdown-item">
                                        <a href="javascript:void(0);" class="dropdown-link">
                                            <i class="la la-plus"></i>
                                            <span>Add to Playlist</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- End | Custom List Item -->
                </div>
            </div>

            <div class="section for-genres">
                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4>Genres</h4>
                            <p>Select your genre</p>
                        </div>
                        <a href="genres.html" class="btn btn-sm btn-pill btn-air btn-primary">View All</a>
                    </div>
                    <hr>
                </div>

                <div class="carousel-item-6 arrow-pos-1">

                    <c:forEach items="${sessionScope.genres}" var="genre">
                        <div class="custom-card">
                            <div class="custom-card--img">
                                <a href="javascript:void(0);">
                                         <img src="${pageContext.request.contextPath}/resource/img/genres/Music-ear-Image.jpg" alt="${genre}"
                                              class="card-img--radius-md">
                                    <span class="bg-blur"><c:out value="${genre}"/></span>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>

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
