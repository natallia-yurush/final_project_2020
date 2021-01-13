<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 06-Jan-21
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <li class="nav-item nav-header">Browse Music</li>
            <li class="nav-item">
                <a href="home.html" class="nav-link active"><i class="la la-home"></i><span>Home</span></a>
            </li>
            <li class="nav-item">
                <a href="genres.html" class="nav-link"><i class="la la-diamond"></i><span>Genres</span></a>
            </li>
            <li class="nav-item">
                <a href="artists.html" class="nav-link"><i class="la la-microphone"></i><span>Artists</span></a>
            </li>

            <li class="nav-item nav-header">Your Music</li>
            <li class="nav-item">
                <a href="favorites.html" class="nav-link"><i class="la la-heart-o"></i><span>Favorites</span></a>
            </li>
            <li class="nav-item">
                <a href="history.html" class="nav-link"><i class="la la-history"></i><span>My playlists</span></a>
            </li>
        </ul>
    </nav>
    <!-- End | Navbar -->

    <!--TODO: only ADMIN-->
    <!-- Begin | Sidebar Footer -->
    <div class="sidebar-footer">
        <a href="${pageContext.servletContext.contextPath}/controller?command=addArtistPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span>Add Artist</span>
        </a>

        <a href="${pageContext.servletContext.contextPath}/controller?command=addMusicPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span>Add Music</span>
        </a>

        <a href="${pageContext.servletContext.contextPath}/controller?command=createAlbumPage" class="btn btn-block btn-danger btn-air btn-bold">
            <span>Create Album</span>
        </a>

    </div>


    <!-- End | Sidebar Footer -->

</aside>
<!-- End | Sidebar -->

