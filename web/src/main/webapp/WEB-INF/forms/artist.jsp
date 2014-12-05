<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/admin/artists/update/${artist.id}"
           modelAttribute="artist">
    <table>
        <tr>
            <th><form:label path="alias"><f:message key="artist.alias"/>:</form:label></th>
            <td><form:input path="alias"/></td>
            <td><form:errors path="alias" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="note"><f:message key="artist.note"/>:</form:label></th>
            <td><form:input path="note"/></td>
            <td><form:errors path="note" cssClass="error"/></td>
        </tr>
    </table>
    <input class="submit_btn" type="submit" value="<f:message key='artist.submit'/>"/>
</form:form>