<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 18-Jan-21
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>


<div class="alert alert-success alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Success!</strong> Indicates a successful or positive action.
</div>

<div class="alert alert-info">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Info!</strong> Indicates a neutral informative change or action.
</div>


<!-- Begin | Section [[ Find at scss/base/core.scss ]] -->
<div class="section">

    <%--<div class="heading">
        <div class="d-flex flex-wrap align-items-end">
            <div class="flex-grow-1">
                <h4><fmt:message key="label.songs" bundle="${loc}"/></h4>
                <p><fmt:message key="label.listenEnjoy" bundle="${loc}"/></p>
            </div>
            <%--<a href="songs.html" class="btn btn-sm btn-pill btn-air btn-primary">View All</a>
        </div>
        <hr>
    </div>--%>
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
                                    <img src="${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}"
                                         alt="${song.album.artist.imagePath}"
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

                            <td>
                                <ul class="custom-card--labels d-flex ml-auto">
                                        <%--TODO: появляется, если в избранном--%>
                                        <%--
                                        <li><span class="badge badge-pill badge-danger">
                                            <i class="la la-heart"></i></span></li>
                                        --%>


                                    <li class="dropleft">
                                        <a href=""
                                           class="btn btn-icon-only p-0 w-auto h-auto"
                                           data-toggle="dropdown" aria-haspopup="true"
                                           aria-expanded="false">
                                            <i class="la la-ellipsis-h"></i>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
                                                <li class="dropdown-item">
                                                    <a href="${pageContext.servletContext.contextPath}/controller?command=addToPlaylist&songId=${song.id}&playlistName=favorite">
                                                        <i class="la la-heart-o"></i>
                                                        <span><fmt:message key="label.favorite" bundle="${loc}"/></span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=addToPlaylistPage&songId=${song.id}"
                                                >
                                                    <i class="la la-plus"></i>
                                                    <span><fmt:message key="label.addToPlaylist"
                                                                       bundle="${loc}"/></span>
                                                </a>
                                            </li>

                                            <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
                                                <li class="dropdown-item">
                                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deleteSong&songId=${song.id}"
                                                    >
                                                        <i class="la la-trash"></i>
                                                        <span><fmt:message key="label.delete" bundle="${loc}"/></span>
                                                    </a>
                                                </li>
                                                <li class="dropdown-item">
                                                    <a href="${pageContext.servletContext.contextPath}/controller?command=editSong&songId=${song.id}"
                                                    >
                                                        <i class="la la-edit"></i>
                                                        <span><fmt:message key="label.edit" bundle="${loc}"/></span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </li>
                                </ul>
                            </td>

                        </tr>


                    </c:forEach>
                </table>


                <%--<div class="popup-fade">
                    <div class="popup">
                        &lt;%&ndash; <a class="popup-close" href="#">Close</a>&ndash;%&gt;
                        <div class="card-header">
                            <h6 class="card-title mb-0">Add To Playlist</h6>
                        </div>
                        <div class="card-body">
                            <form action="#" method="post">

                                <div class="form-row form-group">
                                    <select name="playlistsList" class="form-control" style="width: 300px">

                                        <option value="" disabled selected hidden>Choose a Playlist...</option>

                                        <option>${editId}</option>

                                        <c:forEach var="item" items="${requestScope.playlistList}">
                                            <option>${item.artistName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <button type="submit" class="btn btn-brand btn-air btn-center">Add to Playlist</button>
                            </form>

                            <form action="#" method="post">

                                <div class="form-row form-group">
                                    <input type="text" class="form-control" placeholder="Create new Playlist"
                                           id="playlistName" name="playlistName" style="width: 300px"
                                           autocomplete="off">
                                </div>
                                <button type="submit" class="btn btn-brand btn-air btn-center">Create and Add</button>
                            </form>
                        </div>
                    </div>
                </div>--%>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table class="page-table">
                    <tr>

                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${requestScope.currentPage - 1}"
                                   class="for-page"><fmt:message key="label.prev" bundle="${loc}"/></a></td>
                        </c:if>


                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${i}"
                                           class="for-page">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                        <%--For displaying Next link --%>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${requestScope.currentPage + 1}"
                                   class="for-page"><fmt:message key="label.next" bundle="${loc}"/></a></td>
                        </c:if>


                    </tr>


                </table>


            </div>

        </div>
        <!-- End | Custom List Item -->
    </div>
</div>


<script>
    function disp(form) {
        if (form.style.display == "none") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }

    function openForm() {
        document.getElementById("myForm").style.display = "flex";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }


    function changeValue(x) {

    }


    $(document).ready(function ($) {
        $('.popup-open').click(function () {
            $('.popup-fade').fadeIn();
            return false;
        });

        $('.popup-close').click(function () {
            $(this).parents('.popup-fade').fadeOut();
            return false;
        });

        $(document).keydown(function (e) {
            if (e.keyCode === 27) {
                e.stopPropagation();
                $('.popup-fade').fadeOut();
            }
        });

        $('.popup-fade').click(function (e) {
            if ($(e.target).closest('.popup').length == 0) {
                $(this).fadeOut();
            }
        });
    });


</script>
