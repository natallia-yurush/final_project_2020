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


        <form action="${pageContext.servletContext.contextPath}/controller?command=search" method="post"
              id="searchForm">
            <button type="button" class="btn ion-ios-search"></button>
            <input type="text" placeholder="<fmt:message key="label.search" bundle="${loc}"/>" id="searchInput"
                   name="searchInput"
                   class="form-control"
                   autocomplete="off">
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
                    <span class="pl-2">Halo Admin</span> <%--TODO: имя пользователя--%>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
                        <a class="dropdown-item"
                           href="${pageContext.servletContext.contextPath}/controller?command=profilePage">
                            <i class="ion-md-contact"></i>
                            <span><fmt:message key="label.profile" bundle="${loc}"/></span>
                        </a>

                        <div class="dropdown-divider"></div>
                    </c:if>


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
