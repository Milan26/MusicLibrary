<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/user/edit" modelAttribute="user">
    <table>
        <tr>
            <th><form:label path="email"><f:message key="user.email"/>:</form:label></th>
            <td><form:input path="email"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="firstName"><f:message key="user.firstName"/>:</form:label></th>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="lastName"><f:message key="user.lastName"/>:</form:label></th>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>
    </table>
    <input class="submit_btn" type="submit" value="<f:message key='user.submit'/>"/>
</form:form>
