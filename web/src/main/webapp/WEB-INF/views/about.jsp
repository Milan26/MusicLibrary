<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="header.about.title"/>
<f:message var="header_subtitle" key="header.about.subtitle"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-about.css"/>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="content_wrapper">

        </div>
    </jsp:attribute>
</my:layout>