<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 06-Jan-21
  Time: 23:51
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
                <a href="${pageContext.servletContext.contextPath}/controller?command=home"
                 class="nav-link active"><i class="la la-home"></i><span><fmt:message key="label.home" bundle="${loc}"/></span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=genres" class="nav-link"><i class="la la-diamond"></i><span><fmt:message key="label.genres" bundle="${loc}"/></span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=artists" class="nav-link"><i class="la la-microphone"></i><span><fmt:message key="label.artists" bundle="${loc}"/></span></a>
            </li>
            <%--
            <li class="nav-item">
                <a href="history.html" class="nav-link"><i class="la la-history"></i><span>Albums</span></a>
            </li>
            --%>
        </ul>
    </nav>
    <!-- End | Navbar -->

    <!--TODO: only ADMIN-->
    <!-- Begin | Sidebar Footer -->
    <div class="sidebar-footer">
        <a href="${pageContext.servletContext.contextPath}/controller?command=addArtistPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span><fmt:message key="label.addArtist" bundle="${loc}"/></span>
        </a>

        <a href="${pageContext.servletContext.contextPath}/controller?command=addAlbumPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span><fmt:message key="label.addAlbum" bundle="${loc}"/></span>
        </a>

        <a href="${pageContext.servletContext.contextPath}/controller?command=addMusicPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span><fmt:message key="label.addSong" bundle="${loc}"/></span>
        </a>



    </div>


    <!-- End | Sidebar Footer -->

</aside>
<!-- End | Sidebar -->

