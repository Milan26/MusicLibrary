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
                <li><a href="${pageContext.request.contextPath}/index.jsp"><f:message key="navigation.home"/></a></li>
                <li><a href="${pageContext.request.contextPath}/music"><f:message key="navigation.music"/></a></li>
                <li><a href="${pageContext.request.contextPath}/about"><f:message key="navigation.about"/></a></li>
            </ul>
        </div>
        <div class="right">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/user"><f:message key="navigation.user"/></a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/user"><f:message key="navigation.user.profile"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/user/settings"><f:message key="navigation.user.settings"/></a></li>
                    </ul>
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