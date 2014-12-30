<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/admin/songs/update" modelAttribute="song">
    <table>
        <tr>
            <th><form:label path="title"><f:message key="song.title"/>:</form:label></th>
            <td><form:input path="title"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="trackNumber"><f:message key="song.trackNumber"/>:</form:label></th>
            <td><form:input path="trackNumber"/></td>
            <td><form:errors path="trackNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="duration"><f:message key="song.duration"/>:</form:label></th>
            <td><form:input path="duration"/></td>
            <td><form:errors path="duration" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="bitrate"><f:message key="song.bitrate"/>:</form:label></th>
            <td><form:input path="bitrate"/></td>
            <td><form:errors path="bitrate" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="genre"><f:message key="song.genre"/>:</form:label></th>
            <td><form:select path="genre"><form:options/></form:select></td>
            <td><form:errors path="genre" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="artist.id"><f:message key="song.artist"/>:</form:label></th>
            <td>
                <form:select path="artist.id">
                    <form:options items="${artists}" itemValue="id" itemLabel="id"/>
                </form:select>
            </td>
            <td><form:errors path="artist.id" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="album.id"><f:message key="song.album"/>:</form:label></th>
            <td>
                <form:select path="album.id">
                    <form:options items="${albums}" itemValue="id" itemLabel="id"/>
                </form:select>
            </td>
            <td><form:errors path="album.id" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="note"><f:message key="song.note"/>:</form:label></th>
            <td><form:textarea path="note"/></td>
            <td><form:errors path="note" cssClass="error"/></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${song.id}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input class="submit_btn" type="submit" value="<f:message key='song.submit'/>"/>
</form:form>