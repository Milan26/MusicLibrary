<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

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
    <h1>WHOOPS!</h1>
    <p>Something Went Horribly Wrong</p>
    <p>
      Go back to the
      <a href="javascript:history.go(-1)">previous page</a>
       or visit our
      <a href="${pageContext.request.contextPath}/index.jsp">homepage</a>
    </p>
  </div>
</div>
</body>
</html>
