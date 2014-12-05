<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Note</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${artists}" var="artist">
        <tr>
            <td><c:out value="${artist.id}"/></td>
            <td><c:out value="${artist.alias}"/></td>
            <td><c:out value="${artist.note}"/></td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/artists/update/${artist.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='artist.submit.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/artists/delete/${artist.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='artist.submit.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/artists/update">
        <input type="submit" class="submit_btn" value="<f:message key='artist.add.submit'/>">
    </form>
</div>