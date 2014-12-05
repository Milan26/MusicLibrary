<%@page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="header.admin.title"/>
<f:message var="header_subtitle" key="header.admin.subtitle"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-admin.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-common.css"/>
        <script src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="content_wrapper">
            <c:if test="${not empty message}">
                <div class="message">
                    <h1>${message}</h1>
                </div>
            </c:if>
            <c:choose>
                <c:when test="${not empty albums}">
                    <jsp:include page="../lists/albums.jsp"/>
                </c:when>
                <c:when test="${not empty artists}">
                    <jsp:include page="../lists/artists.jsp"/>
                </c:when>
                <c:when test="${not empty songs}">
                    <jsp:include page="../lists/songs.jsp"/>
                </c:when>
                <%--<c:when test="${not empty users}">--%>
                <%--<jsp:include page="../lists/users.jsp"/>--%>
                <%--</c:when>--%>
            </c:choose>
        </div>
    </jsp:attribute>
</my:layout>