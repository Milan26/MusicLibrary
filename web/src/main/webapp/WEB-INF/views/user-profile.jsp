<%@page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="header.user.title"/>
<f:message var="header_subtitle" key="header.user.subtitle"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-user.css"/>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="content_wrapper">
            <div class="message">
                <h1>${message}</h1>
            </div>
            <form:form method="GET" action="${pageContext.request.contextPath}/user/edit" modelAttribute="user">
                <table>
                    <tr>
                        <th><form:label path="email"><f:message key="user.email"/>:</form:label></th>
                        <td><c:out value="${user.email}"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="firstName"><f:message key="user.firstName"/>:</form:label></th>
                        <td><c:out value="${user.firstName}"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="lastName"><f:message key="user.lastName"/>:</form:label></th>
                        <td><c:out value="${user.lastName}"/></td>
                    </tr>
                </table>
                <input class="submit_btn" type="submit" value="<f:message key='user.profile.edit'/>"/>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>