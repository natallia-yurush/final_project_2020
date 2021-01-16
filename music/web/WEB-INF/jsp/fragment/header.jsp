<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 05-Jan-21
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>

<!-- Begin | Header [[ Find at scss/framework/base/header/header.scss ]] -->
<header id="header" class="bg-primary">

    <div class="d-flex align-items-center">


        <form action="${pageContext.servletContext.contextPath}/controller?command=search" method="post" id="searchForm">

            <button type="button" class="btn ion-ios-search"></button>
            <input type="text" placeholder="<fmt:message key="label.search" bundle="${loc}"/>" id="searchInput"
                   name="searchInput"
                   class="form-control"
                   autocomplete="off">


            <%--ARTISTS--%>
            <div class="search-card" data-scrollable="true">

                <!-- Begin | Search Result List -->
                <div class="mb-3">
                    <c:if test="${not empty requestScope.artistsList}">
                        <!-- Begin | Search Result List Header -->
                        <div class="d-flex">
                            <span class="text-uppercase mr-auto font-weight-bold text-dark">Artists</span>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=artists">View All</a>
                        </div>
                        <!-- End | Search Result List Header -->
                        <hr>

                        <!-- Begin | Result List -->
                        <div class="row">
                            <c:forEach items="${requestScope.artistsList}" var="artist">
                                <div class="col-xl-2 col-md-4 col-6">
                                    <div class="custom-card mb-3">
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${artist.artistName}"
                                           class="text-dark">
                                            <img src="${pageContext.request.contextPath}/resource/img/artists/${artist.imagePath}"
                                                 alt="" class="card-img--radius-md">
                                            <p class="text-truncate mt-2"><c:out value="${artist.artistName}"/></p>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- End | Result List -->
                    </c:if>
                </div>
                <!-- End | Search Result List -->


                <c:if test="${not empty requestScope.songsList}">
                    <%--TODO--%>
                    <!-- Begin | Search Result List -->
                    <div class="mb-3">
                        <!-- Begin | Search Result List Header -->
                        <div class="d-flex">
                            <span class="text-uppercase mr-auto font-weight-bold text-dark">Track</span>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=home">View All</a>
                        </div>
                        <!-- End | Search Result List Header -->
                        <hr>
                        <!-- Begin | Result List -->
                        <div class="row">
                            <div class="col-xl-4 col-md-6 col-12">
                                <div class="custom-card mb-3">
                                    <table>
                                        <c:forEach items="${requestScope.songsList}" var="song">
                                            <tr>
                                                <td>
                                                    <div class="custom-card--inline-img">
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
                                                        <source src='${pageContext.request.contextPath}/resource/songs/${song.trackPath}'/>
                                                    </audio>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- End | Result List -->
                    </div>
                    <!-- End | Search Result List -->
                </c:if>


                <!-- Begin | Search Result List -->
                <div>
                    <c:if test="${not empty requestScope.albums}">
                        <!-- Begin | Search Result List Header -->
                        <div class="d-flex">
                            <span class="text-uppercase mr-auto font-weight-bold text-dark">Albums</span>
                            <a href="songs.html">View All</a>
                        </div>
                        <!-- End | Search Result List Header -->
                        <hr>
                        <!-- Begin | Result List -->
                        <div class="row">
                            <div class="col-xl-4 col-md-6 col-12">
                                <div class="custom-card mb-3">
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
                        </div>
                        <!-- End | Result List -->
                    </c:if>
                </div>
                <!-- End | Search Result List -->

            </div>
        </form>

        <!-- Begin | Header Options -->
        <ul class="header-options d-flex align-items-center">

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                <jsp:include page="../fragment/nav-language.jsp">
                    <jsp:param name="page" value="login"/>
                </jsp:include>
            </div>


            <li class="dropdown fade-in">
                <a href="javascript:void(0);" class="d-flex align-items-center py-2" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="pl-2"><fmt:message key="title.language" bundle="${loc}"/></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">

                    <a class="dropdown-item"
                       href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=en_EN&page=${param.page}">
                        <span><fmt:message bundle="${loc}" key="label.languageEn"/></span>
                    </a>

                    <a class="dropdown-item"
                       href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=ru_RU&page=${param.page}">
                        <span><fmt:message bundle="${loc}" key="label.languageRu"/></span>
                    </a>

                </div>
            </li>

            <li class="dropdown fade-in">
                <a href="javascript:void(0);" class="d-flex align-items-center py-2" role="button" id="userMenu"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%--
                                        <div class="avatar avatar-sm avatar-circle"><img src="${pageContext.request.contextPath}/assets/images/users/thumb.jpg" alt="user"></div>
                    --%>
                    <span class="pl-2">Halo Admin</span> <%--TODO: имя пользователя--%>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                    <a class="dropdown-item"
                       href="${pageContext.servletContext.contextPath}/controller?command=profilePage">
                        <i class="ion-md-contact"></i>
                        <span><fmt:message key="label.profile" bundle="${loc}"/></span>
                    </a>

                    <div class="dropdown-divider"></div>
                    <div class="px-4 py-2">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=logout"
                           class="btn btn-sm btn-air btn-pill btn-danger">
                            <fmt:message key="label.logout" bundle="${loc}"/>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
        <!-- End | Header Options -->
    </div>

</header>

<div class="banner"></div>
<!-- End | Header -->
