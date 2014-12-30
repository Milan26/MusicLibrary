<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title><f:message key="user.login.title"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-login.css"/>
</head>
<body>
<div id="wrapper">
  <form:form name="login-form" class="login-form signup-form" action="${pageContext.request.contextPath}/user/signup" method="POST" modelAttribute="user">
    <div class="header">
      <h1><f:message key="user.signup.form.title"/></h1>
    </div>
    <div class="content">
        <table>
          <tr>
            <td><input type="text" class="input" name="email" placeholder="Email"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
          </tr>
          <tr>
            <td><input type="text" class="input" name="firstName" placeholder="First Name"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
          </tr>
          <tr>
            <td><input type="text" class="input" name="lastName" placeholder="Last Name"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
          </tr>
          <tr>
            <td><input type="password" class="input" name="password" placeholder="Password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
          </tr>
        </table>
    </div>
    <div class="footer">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="submit" name="submit" value="<f:message key='user.profile.submit'/>" class="register" />
    </div>
  </form:form>
</div>
</body>
</html>