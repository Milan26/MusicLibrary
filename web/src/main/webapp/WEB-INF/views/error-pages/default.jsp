<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${pageContext.request.locale}">
<head>
  <title>${pageContext.errorData.statusCode}</title>
  <meta charset="utf-8" content="text/html">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>${pageContext.errorData.statusCode}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-error.css"/>
</head>
<body>
<div id="wrapper">
  <div>
    <span class="status-code">${pageContext.errorData.statusCode}</span>
  </div>
  <div>
    <h1><f:message key="error.page.whoops"/></h1>
    <p><f:message key="error.page.code.default"/></p>
    <p>
      <f:message key="error.page.msg1"/>
      <a href="javascript:history.go(-1)"><f:message key="error.page.history"/></a>
      <f:message key="error.page.msg2"/>
      <a href="${pageContext.request.contextPath}/index.jsp"><f:message key="error.page.homepage"/></a>
    </p>
  </div>
</div>
</body>
</html>
