<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td><f:message key="song.id"/></td>
        <td><f:message key="song.title"/></td>
        <td><f:message key="song.trackNumber"/></td>
        <td><f:message key="song.duration"/></td>
        <td><f:message key="song.bitrate"/></td>
        <td><f:message key="song.genre"/></td>
        <td><f:message key="song.artist"/></td>
        <td><f:message key="song.album"/></td>
        <td><f:message key="song.note"/></td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${songs}" var="song">
        <tr>
            <td><c:out value="${song.id}"/></td>
            <td><c:out value="${song.title}"/></td>
            <td><c:out value="${song.trackNumber}"/></td>
            <td><c:out value="${song.duration}"/></td>
            <td><c:out value="${song.bitrate}"/></td>
            <td><c:out value="${song.genre}"/></td>
            <td><c:out value="${song.artist.id}"/></td>
            <td><c:out value="${song.album.id}"/></td>
            <td><c:out value="${song.note}"/></td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/songs/update">
                    <input type="hidden" name="id" value="${song.id}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/songs/delete/${song.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/songs/update">
        <input type="submit" class="submit_btn" value="<f:message key='label.add'/>">
    </form>
</div>