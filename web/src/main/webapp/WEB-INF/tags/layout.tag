<!DOCTYPE html>
<%@tag description="Template" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@attribute name="title" required="true" %>
<%@attribute name="header_title" required="true" %>
<%@attribute name="header_subtitle" required="false" %>
<%@attribute name="head" fragment="true" required="false" %>
<%@attribute name="body" fragment="true" required="false" %>
<%@attribute name="header" fragment="true" required="false" %>
<%@attribute name="content" fragment="true" required="true" %>
<%@attribute name="footer" fragment="true" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${pageContext.request.locale}">
<%-- HEAD --%>
<head>
    <title>${title}</title>
    <meta charset="utf-8" content="text/html">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skel.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-main.css"/>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/vendor/modernizr-2.6.2.min.js"></script>

    <jsp:invoke fragment="head"/>
</head>
<%-- BODY --%>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->
<div id="wrapper">
    <%-- NAVIGATION --%>
    <div id="nav">
        <div class="left">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp"><f:message key="label.home"/></a></li>
                <li><a href="${pageContext.request.contextPath}/music"><f:message key="label.music"/></a></li>
                <li><a href="${pageContext.request.contextPath}/about"><f:message key="label.about"/></a></li>
                <%--<div class="left-submenu">--%>
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                            <li class="menu">
                                <a href=""><f:message key="label.admin"/></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/admin/albums"><f:message key="label.albums"/></a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/songs"><f:message key="label.songs"/></a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/artists"><f:message key="label.artists"/></a></li>
                                </ul>
                            </li>
                        </c:if>
                    </c:if>
                <%--</div>--%>
            </ul>
        </div>
        <div class="right">
            <ul>
                <li>
                    <c:choose>
                        <c:when test="${not empty pageContext.request.userPrincipal}">
                            <form method="GET" action="${pageContext.request.contextPath}/user/profile">
                                <input type="submit" value="<c:out value='${pageContext.request.userPrincipal.name}'/>"/>
                            </form>
                            <ul>
                                <li>
                                    <form action="${pageContext.request.contextPath}/j_spring_security_logout" method="post" id="logoutForm">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <input type="submit" value="<f:message key='user.logout'/>"/>
                                    </form>
                                </li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/user/login"><f:message key='user.login'/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
        <div class="mid">
            <h3>MusicLib.</h3>
        </div>
    </div>
    <%-- CONTENT --%>
    <div id="main">
        <%-- HEADER ITEMS --%>
        <div id="header_wrapper">
            <div id="header">
                <div class="inner">
                    <h1>${header_title}</h1>

                    <p>${header_subtitle}</p>
                </div>
            </div>
            <jsp:invoke fragment="header"/>
        </div>
        <jsp:invoke fragment="content"/>
    </div>
    <%-- FOOTER --%>
    <div id="footer">
        <div class="copyright">
            Copyright &copy; 2014 <a href="https://github.com/Milan26/MusicLibrary">MusicLib</a>. All Rights Reserved.
        </div>
        <jsp:invoke fragment="footer"/>
    </div>
</div>
<jsp:invoke fragment="body"/>
</body>
</html>