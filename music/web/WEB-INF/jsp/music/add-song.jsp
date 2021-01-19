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
    <link href='<c:url value="/assets/plugin/chosen.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/css/vendors.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/css/styles.bundle.css"/>' rel="stylesheet" type="text/css"/>



    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700" rel="stylesheet">

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.js" type="text/javascript"></script>
    <script src='<c:url value="/assets/plugin/chosen.jquery.js"/>'></script>

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
            <jsp:param name="page" value="addMusicPage"/>
        </jsp:include>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">
            <div class="row section">
                <div class="col-xl-8 col-md-10 mx-auto">

                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">
                                <fmt:message key="label.addSong" bundle="${loc}"/>
                            </h6>
                        </div>
                        <div class="card-body">
                            <form method="post" enctype="multipart/form-data" acceptcharset="UTF-8"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addMusic">
                                <div class="form-row form-group">
                                    <label for="songName" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.songName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <input type="text" id="songName" name="songName" class="form-control" required>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.genre" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <%--<select class="form-control chosen-select">--%>
                                        <select data-placeholder="<fmt:message key="label.chooseGenre" bundle="${loc}"/>" class="form-control chosen-select" required
                                                name="genre">
                                            <c:forEach var="genre" items="${sessionScope.genres}">
                                                <option>${genre.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label for="songFile" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.songFile" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <div class=" custom-file">
                                            <input type="file" class="custom-file-input" id="songFile" name="songFile" required>
                                            <%--todo--%>
                                            <label class="custom-file-label" for="songFile" id="file">
                                                <fmt:message key="label.chooseFile" bundle="${loc}"/>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.artistName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select data-placeholder="Choose a Artist(s)..."
                                                class="form-control chosen-select" multiple name="artistsName" required>

                                            <c:forEach var="item" items="${requestScope.artistsName}">
                                                <option>${item.artistName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.album" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select class="form-control chosen-select" name="album" required>

                                            <%--TODO: разобраться, как вывести альбомы артиста--%>
                                            <option><fmt:message key="label.single" bundle="${loc}"/></option>
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
                                            <button type="submit" class="btn btn-brand btn-air">
                                                <fmt:message key="label.saveSong" bundle="${loc}"/>
                                            </button>
                                            <button type="reset" class="btn btn-outline-secondary">
                                                <fmt:message key="label.clearForm" bundle="${loc}"/>
                                            </button>
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
       <%-- <jsp:include page="../fragment/audio-player.jsp"/>--%>

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
        $(".chosen-select").chosen();

        $('#file').click(function () {
            $("input[type='file']").trigger('click');
        })

        $("input[type='file']").change(function () {
            $('#file').text(this.value.replace(/C:\\fakepath\\/i, ''))
            $('.customform-control').hide();
        })

        /*$(document).ready(function () {
            $(".chosen-select").chosen();
        });*/

    </script>

</div>
</body>
</html>
