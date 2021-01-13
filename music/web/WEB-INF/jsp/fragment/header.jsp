<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 05-Jan-21
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>

<!-- Begin | Header [[ Find at scss/framework/base/header/header.scss ]] -->
<header id="header" class="bg-primary">

    <div class="d-flex align-items-center">


        <form action="#" id="searchForm">

            <button type="button" class="btn ion-ios-search"></button>
            <input type="text" placeholder="<fmt:message key="label.search" bundle="${loc}"/>" id="searchInput"
                   class="form-control" autocomplete="off">

            <!-- Begin | Search Card [[ Find at scss/framework/base/search/search.scss ]] -->
            <div class="search-card" data-scrollable="true">
                <!-- Begin | Search Result List -->
                <div class="mb-3">
                    <!-- Begin | Search Result List Header -->
                    <div class="d-flex">
                        <%--TODO: ???--%>
                        <%--
                        <span class="text-uppercase mr-auto font-weight-bold text-dark">Artists</span>
                        <a href="artists.html">View All</a>
                        --%>
                    </div>

                    <!-- End | Search Result List Header -->
                    <hr>
                    <!-- Begin | Result List -->
                    <!--TODO: просто инфа при поиске (не нужна)-->
                    <!--<div class="row">
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/1.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Arebica Luna</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/2.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Gerrina Linda</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/3.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Zunira Willy</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/4.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Johnny Marro</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/5.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Jina Moore</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-2 col-md-4 col-6">
                            <div class="custom-card mb-3">
                                <a href="artist-details.html" class="text-dark">
                                    <img src="../../../assets/images/cover/medium/6.jpg" alt="" class="card-img&#45;&#45;radius-md">
                                    <p class="text-truncate mt-2">Rasomi Pelina</p>
                                </a>
                            </div>
                        </div>
                    </div>-->
                    <!-- End | Result List -->
                </div>
                <!-- End | Search Result List -->

                <!-- Begin | Search Result List -->
                <!--TODO: здесь вывод результата!!!!!-->
                <!--<div class="mb-3">
                    &lt;!&ndash; Begin | Search Result List Header &ndash;&gt;
                    <div class="d-flex">
                        <span class="text-uppercase mr-auto font-weight-bold text-dark">Track</span>
                        <a href="songs.html">View All</a>
                    </div>
                    &lt;!&ndash; End | Search Result List Header &ndash;&gt;
                    <hr>
                    &lt;!&ndash; Begin | Result List &ndash;&gt;
                    <div class="row">
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/1.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">I Love You Mummy</p>
                                        <p class="text-truncate text-muted font-sm">Arebica Luna</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/2.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">Shack your butty</p>
                                        <p class="text-truncate text-muted font-sm">Gerrina Linda</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/3.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">Do it your way(Female)</p>
                                        <p class="text-truncate text-muted font-sm">Zunira Willy & Nutty Nina</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    &lt;!&ndash; End | Result List &ndash;&gt;
                </div>-->
                <!-- End | Search Result List -->

                <!-- Begin | Search Result List -->
                <!--<div>
                    &lt;!&ndash; Begin | Search Result List Header &ndash;&gt;
                    <div class="d-flex">
                        <span class="text-uppercase mr-auto font-weight-bold text-dark">Albums</span>
                        <a href="songs.html">View All</a>
                    </div>
                    &lt;!&ndash; End | Search Result List Header &ndash;&gt;
                    <hr>
                    &lt;!&ndash; Begin | Result List &ndash;&gt;
                    <div class="row">
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/4.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">Say yes</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/5.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">Where is your letter</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-12">
                            <div class="custom-card mb-3">
                                <a href="song-details.html" class="text-dark custom-card&#45;&#45;inline">
                                    <div class="custom-card&#45;&#45;inline-img">
                                        <img src="../../../assets/images/cover/small/6.jpg" alt="" class="card-img&#45;&#45;radius-sm">
                                    </div>

                                    <div class="custom-card&#45;&#45;inline-desc">
                                        <p class="text-truncate mb-0">Hey not me</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    &lt;!&ndash; End | Result List &ndash;&gt;
                </div>-->
                <!-- End | Search Result List -->
            </div>
            <!-- End | Search Card -->

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
