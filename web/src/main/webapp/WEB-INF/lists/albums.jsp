<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td><f:message key="album.id"/></td>
        <td><f:message key="album.title"/></td>
        <td><f:message key="album.releaseDate"/></td>
        <td><f:message key="album.coverArt"/></td>
        <td><f:message key="album.note"/></td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${albums}" var="album">
        <tr>
            <td><c:out value="${album.id}"/></td>
            <td><c:out value="${album.title}"/></td>
            <td><c:out value="${album.releaseDate}"/></td>
            <td><img src="<c:out value='${album.coverArt}'/>"/></td>
            <td><c:out value="${album.note}"/></td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/albums/update">
                    <input type="hidden" name="id" value="${album.id}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/albums/delete/${album.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/albums/update">
        <input type="submit" class="submit_btn" value="<f:message key='label.add'/>">
    </form>
</div>