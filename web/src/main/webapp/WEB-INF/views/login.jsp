<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title><f:message key="user.login.title"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-login.css"/>
</head>
<body onload='document.loginForm.j_username.focus();'>
<div id="wrapper">
  <form name="login-form" class="login-form" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">
    <div class="header">
      <h1><f:message key="user.login.title"/></h1>
      <span><f:message key="user.login.subtitle"/><span class="title"><c:out value="MusicLib."/></span></span>
      <c:if test="${not empty error}"><div class="error">${error}</div></c:if>
      <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
    </div>
    <div class="content">
      <input name="j_username" type="text" class="input username" placeholder="Email"/>
      <div class="user-icon"></div>
      <input name="j_password" type="password" class="input password" placeholder="Password"/>
      <div class="pass-icon"></div>
    </div>
    <div class="footer">
      <input type="submit" name="submit" value="<f:message key='navigation.user.login'/>" class="button"/>
      <a name="register" class="register" href="${pageContext.request.contextPath}/user/signup">
        <f:message key="user.signup.title"/>
      </a>
    </div>
    <div class="submit">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
  </form>
</div>
</body>
</html>
