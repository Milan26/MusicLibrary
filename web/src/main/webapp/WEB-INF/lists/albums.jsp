<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Release Date</td>
        <td>Cover Art</td>
        <td>Note</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${albums}" var="album">
        <tr>
            <td><c:out value="${album.id}"/></td>
            <td><c:out value="${album.title}"/></td>
            <td><f:formatDate pattern="dd-MM-yyyy" value="${album.releaseDate}"/></td>
            <td><img src="<c:out value='${album.coverArt}'/>"/></td>
            <td><c:out value="${album.note}"/></td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/albums/update/${album.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='album.submit.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/albums/delete/${album.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='album.submit.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/albums/update">
        <input type="submit" class="submit_btn" value="<f:message key='album.add.submit'/>">
    </form>
</div>