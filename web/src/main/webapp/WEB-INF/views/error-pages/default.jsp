<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="${pageContext.request.locale}">
<head>
  <meta charset="utf-8" content="text/html">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>
    <c:choose>
      <c:when test="${not empty message}"><c:out value="Error"/></c:when>
      <c:otherwise>${pageContext.errorData.statusCode}</c:otherwise>
    </c:choose>
  </title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-error.css"/>
</head>
<body>
<div id="wrapper">
  <div>
    <c:if test="${pageContext.errorData.statusCode eq 404}">
      <span class="status-code">${pageContext.errorData.statusCode}</span>
    </c:if>
  </div>
  <div>
    <h1><c:out value="WHOOPS!"/></h1>
    <c:choose>
      <c:when test="${not empty message}">
        <p>${message}</p>
      </c:when>
      <c:when test="${pageContext.errorData.statusCode eq 404}">
        <p><spring:message code="error.page.404.msg"/></p>
      </c:when>
      <c:otherwise>
        <p><spring:message code="error.page.generic.msg"/></p>
      </c:otherwise>
    </c:choose>
    <p>
      <spring:message code="error.page.generic.msg.return.part1"/>
      <a href="javascript:history.go(-1)"><spring:message code="error.page.generic.msg.return.part2"/></a>
      <spring:message code="error.page.generic.msg.return.part3"/>
      <a href="${pageContext.request.contextPath}/index.jsp"><spring:message code="error.page.generic.msg.return.part4"/></a>
    </p>
  </div>
</div>
</body>
</html>
