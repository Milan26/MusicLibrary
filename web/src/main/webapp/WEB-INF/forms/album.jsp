<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/admin/albums/update" modelAttribute="album">
    <table>
        <tr>
            <th><form:label path="title"><f:message key="album.title"/>:</form:label></th>
            <td><form:input path="title"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="releaseDate">
                <f:message key="album.releaseDate"/>
                <c:out value=" (dd-mm-yyyy):"/>
            </form:label></th>
            <td><form:input path="releaseDate"/></td>
            <td><form:errors path="releaseDate" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="coverArt"><f:message key="album.coverArt"/>:</form:label></th>
            <td><form:input path="coverArt"/></td>
            <td><form:errors path="coverArt" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="note"><f:message key="album.note"/>:</form:label></th>
            <td><form:textarea path="note"/></td>
            <td><form:errors path="note" cssClass="error"/></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${album.id}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input class="submit_btn" type="submit" value="<f:message key='label.submit'/>"/>
</form:form>