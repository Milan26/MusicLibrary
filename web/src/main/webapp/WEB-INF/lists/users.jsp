<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="data-table" class="compact hover row-border">
    <thead>
    <tr>
        <td>Id</td>
        <td>Email</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Role</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/songs/update/${user.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='user.submit.edit'/>">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/admin/songs/delete/${user.id}">
                    <input type="submit" class="submit_btn" value="<f:message key='user.submit.delete'/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/admin/users/update/">
        <input type="submit" class="submit_btn" value="<f:message key='user.add.submit'/>">
    </form>
</div>