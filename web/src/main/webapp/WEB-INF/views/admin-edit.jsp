<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page errorPage="error-pages/default.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="label.admin"/>
<f:message var="header_subtitle" key="admin.subtitle"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-admin.css"/>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="content_wrapper">
            <c:choose>
                <c:when test="${not empty album}">
                    <jsp:include page="../forms/album.jsp"/>
                </c:when>
                <c:when test="${not empty artist}">
                    <jsp:include page="../forms/artist.jsp"/>
                </c:when>
                <c:when test="${not empty song}">
                    <jsp:include page="../forms/song.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </jsp:attribute>
</my:layout>