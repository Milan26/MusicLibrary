<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td><f:message key="artist.id"/></td>
        <td><f:message key="artist.name"/></td>
        <td><f:message key="artist.note"/></td>
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
                <form method="GET" action="${pageContext.request.contextPath}/admin/artists/update">
                    <input type="hidden" name="id" value="${artist.id}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/artists/delete/${artist.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="submit_btn" value="<f:message key='label.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/artists/update">
        <input type="submit" class="submit_btn" value="<f:message key='label.add'/>">
    </form>
</div>