<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/admin/artists/update" modelAttribute="artist">
    <table>
        <tr>
            <th><form:label path="alias"><f:message key="artist.name"/>:</form:label></th>
            <td><form:input path="alias"/></td>
            <td><form:errors path="alias" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="note"><f:message key="artist.note"/>:</form:label></th>
            <td><form:input path="note"/></td>
            <td><form:errors path="note" cssClass="error"/></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${artist.id}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input class="submit_btn" type="submit" value="<f:message key='label.submit'/>"/>
</form:form>