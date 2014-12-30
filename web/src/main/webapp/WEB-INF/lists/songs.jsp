<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Track Number</td>
        <td>Length [s]</td>
        <td>Bitrate</td>
        <td>Genre</td>
        <td>Artist Id</td>
        <td>Album Id</td>
        <td>Note</td>
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
                    <input type="submit" class="submit_btn" value="<f:message key='song.submit.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/songs/delete/${song.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='song.submit.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/songs/update">
        <input type="submit" class="submit_btn" value="<f:message key='song.add.submit'/>">
    </form>
</div>