<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page errorPage="../error-pages/default.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="label.user"/>
<f:message var="header_subtitle" key="user.edit"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-user.css"/>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="content_wrapper">
            <form:form method="POST" action="${pageContext.request.contextPath}/user/profile/edit" modelAttribute="user">
                <table>
                    <tr>
                        <th><form:label path="email"><f:message key="user.email"/>:</form:label></th>
                        <td><form:input readonly="true" path="email"/></td>
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
                    <tr>
                        <th><form:label path="password"><f:message key="user.password"/>:</form:label></th>
                        <td><form:password path="password" showPassword="false"/></td>
                        <td><form:errors path="password" cssClass="error"/></td>
                    </tr>
                </table>
                <form:hidden path="id"/>
                <form:hidden path="enabled"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="submit_btn" type="submit" value="<f:message key='label.submit'/>"/>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>