<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page errorPage="error-pages/default.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="header_subtitle" key="browse.albums"/>
<my:music header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-popup.css"/>
        <script src="${pageContext.request.contextPath}/resources/js/vendor/jPages.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/music-albums.js"></script>
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:forEach items="${albums}" var="album">
            <li class="item thumb">
                <h2><c:out value="${album.title}"/></h2>
                <a onclick="getAlbum(${album.id})">
                    <img src="${album.coverArt}" alt="${album.coverArt}">
                </a>
            </li>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="table_wrapper">
            <div id="table_scroll" class="scrollbar">
                <table id="album_songs">
                    <thead>
                    <tr>
                        <th><c:out value="#"/></th>
                        <th><f:message key="song.title"/></th>
                        <th><f:message key="song.duration"/></th>
                        <th><f:message key="song.genre"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:music>