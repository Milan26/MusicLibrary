<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page errorPage="error-pages/default.jsp" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:message var="title" key="music.title"/>
<f:message var="header_title" key="label.about"/>
<f:message var="header_subtitle" key="about.subtitle"/>
<my:layout title="${title}" header_title="${header_title}" header_subtitle="${header_subtitle}">
    <jsp:attribute name="content">
        <div id="content_wrapper">
        <div id="team">
            <div class="member">
                <img class="avatar" src="https://avatars1.githubusercontent.com/u/5015486?v=3&s=400"/>

                <div>
                    <h3><c:out value="Milan Pánik"/></h3>

                    <h4><f:message key="about.position"/></h4>
                    <a href="mailto:396198@mail.muni.cz">
                        <c:out value="396198@mail.muni.cz"/>
                    </a>
                </div>
            </div>
            <div class="member">
                <img class="avatar" src="https://avatars1.githubusercontent.com/u/9092254?v=3&s=400"/>

                <div>
                    <h3><c:out value="Matúš Burda"/></h3>

                    <h4><f:message key="about.position"/></h4>
                    <a href="mailto:374281@mail.muni.cz">
                        <c:out value="374281@mail.muni.cz"/>
                    </a>
                </div>
            </div>
            <div class="member">
                <img class="avatar" src="https://avatars1.githubusercontent.com/u/9092249?v=3&s=400"/>

                <div>
                    <h3><c:out value="Alexander Lörinc"/></h3>

                    <h4><f:message key="about.position"/></h4>
                    <a href="mailto:373970@mail.muni.cz">
                        <c:out value="373970@mail.muni.cz"/>
                    </a>
                </div>
            </div>
        </div>
    </jsp:attribute>
</my:layout>