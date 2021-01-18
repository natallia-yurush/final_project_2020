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

                            <td>
                                <ul class="custom-card--labels d-flex ml-auto">
                                        <%--TODO: появляется, если в избранном--%>
                                        <%--
                                        <li><span class="badge badge-pill badge-danger">
                                            <i class="la la-heart"></i></span></li>
                                        --%>


                                    <li class="dropleft">
                                        <a href="javascript:void(0);"
                                           class="btn btn-icon-only p-0 w-auto h-auto"
                                           data-toggle="dropdown" aria-haspopup="true"
                                           aria-expanded="false">
                                            <i class="la la-ellipsis-h"></i>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li class="dropdown-item">
                                                <a href="javascript:void(0);"
                                                   class="dropdown-link favorite">
                                                    <i class="la la-heart-o"></i>
                                                    <span>Favorite</span>
                                                </a>
                                            </li>




                                            <li class="dropdown-item">
                                                <a onclick="disp(document.getElementById('form1'))"
                                                   class="dropdown-link">
                                                    <i class="la la-plus"></i>
                                                    <span>Add to Playlist</span>
                                                </a>


                                                <form id="form1" style="display: none;">
                                                    <input type="text" value="Я тут">
                                                </form>



                                                    <%--<ul class="dropdown-menu ">
                                                        <li class="dropdown-item">
                                                            <a href="javascript:void(0);"
                                                               class="dropdown-link">
                                                                <i class="la la-heart-o"></i>
                                                                <span>l,kjmnhgfvds</span>
                                                            </a>
                                                        </li>
                                                        <li class="dropdown-item">
                                                            <a href="javascript:void(0);"
                                                               class="dropdown-link">
                                                                <i class="la la-heart-o"></i>
                                                                <span>;uhikgydtfd</span>
                                                            </a>
                                                        </li>
                                                    </ul>--%>
                                            </li>





                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=deleteSong&songId=${song.id}" class="dropdown-link">
                                                    <i class="la la-trash"></i>
                                                    <span>Delete</span>
                                                </a>
                                            </li>
                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=editSong&songId=${song.id}" class="dropdown-link">
                                                    <i class="la la-edit"></i>
                                                    <span>Edit</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </td>

                        </tr>
                    </c:forEach>
                </table>


                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table class="page-table">
                    <tr>

                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=home&page=${requestScope.currentPage - 1}"
                                   class="for-page">Previous</a></td>
                        </c:if>


                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=home&page=${i}"
                                           class="for-page">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                        <%--For displaying Next link --%>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=home&page=${requestScope.currentPage + 1}"
                                   class="for-page">Next</a></td>
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
</script>
