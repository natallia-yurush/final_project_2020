<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 05-Jan-21
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>

<!-- Begin | Sidebar [[ Find at scss/framework/base/sidebar/left/sidebar.scss ]] -->
<%--TODO: add localization--%>
<aside id="sidebar" class="sidebar-primary">

    <!-- Begin | Sidebar Header -->
    <div class="sidebar-header d-flex align-items-center">
        <a href="home.jsp" class="brand"> <!-- переход на home page (себя же) -->
            <img src="${pageContext.request.contextPath}/resource/img/purple-img.png" alt="listen-app">
        </a>

        <button type="button" class="btn p-0 ml-auto" id="hideSidebar">
            <i class="ion-md-arrow-back h3"></i>
        </button>

        <button type="button" class="btn toggle-menu" id="toggleSidebar">
            <span></span>
            <span></span>
            <span></span>
        </button>
    </div>
    <!-- End | Sidebar Header -->

    <!-- Begin | Navbar [[ Find at scss/framework/components/navbar/navbar.scss ]] -->
    <nav class="navbar">
        <ul class="navbar-nav" data-scrollable="true">
            <li class="nav-item nav-header"><fmt:message key="label.browseMusic" bundle="${loc}"/></li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=home" class="nav-link active"><i class="la la-home"></i><span>
                    <fmt:message key="label.home" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=genres" class="nav-link"><i class="la la-diamond"></i><span>
                    <fmt:message key="label.genres" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=artists" class="nav-link"><i class="la la-microphone"></i><span>
                    <fmt:message key="label.artists" bundle="${loc}"/>
                </span></a>
            </li>

            <li class="nav-item nav-header">
                <fmt:message key="lable.yourMusic" bundle="${loc}"/>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=favorites" class="nav-link"><i class="la la-heart-o"></i><span>
                    <fmt:message key="lable.favorites" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=playlistsPage" class="nav-link"><i class="la la-history"></i><span>
                    <fmt:message key="label.myPlaylists" bundle="${loc}"/>
                </span></a>
            </li>
        </ul>
    </nav>
    <!-- End | Navbar -->

    <!--TODO: only ADMIN-->
    <!-- Begin | Sidebar Footer -->
    <%--<div class="sidebar-footer">
        <a href="add-music.html" class="btn btn-block btn-danger btn-air btn-bold">
            <i class="ion-md-musical-note"></i>
            <span>Add Music</span>
        </a>
    </div>--%>
    <!-- End | Sidebar Footer -->

</aside>
<!-- End | Sidebar -->
