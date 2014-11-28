<!DOCTYPE html>
<meta charset="UTF-8" />

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
            <div id="team">
                <div id="team_table">
                <table id="team" style="table-layout: fixed; padding-left: 50px" width=100% border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align=center>
                            <div style='margin-left:50px'>
                                <img class="avatar" src="https://avatars1.githubusercontent.com/u/5015486?v=3&s=400" width="100" height="100" />
                                <p class="name">Bc. Milan Pánik</p>
                                <p class="position">Developer</p>
                                <div class="contact-info">
                                    <p class="email bulleted"><a class="icon" href="mailto:396198@mail.muni.cz"><i class="icon-envelope"></i>396198@mail.muni.cz</a></p>

                                    </a>
                                    </p>
                                </div>
                            </div>
                        </td>
                        <td align=center>
                            <div style='margin-left:50px'>
                                <img class="avatar" src="https://avatars1.githubusercontent.com/u/9092254?v=3&s=400" width="100" height="100" />
                                <p class="name">Bc. Matú? Burda</p>
                                <p class="position">Project Manager</p>
                                <div class="contact-info">
                                    <p class="email bulleted"><a class='icon' href='mailto:374281@mail.muni.cz'><i class="icon-envelope"></i>374281@mail.muni.cz</a></p>

                                </div>
                            </div>
                        </td>
                        <td align=center>
                            <div style='margin-left:50px'>
                                <img class="avatar" src="https://avatars2.githubusercontent.com/u/9092249?v=3&s=400" width="100" height="100" />
                                <p class="name">Bc. Alexander Lörinc</p>
                                <p class="position">Semi Developer</p>
                                <div class="contact-info">
                                    <p class="email bulleted"><a class="icon" href="mailto:373970@mail.muni.cz"><i class="icon-envelope"></i>373970@mail.muni.cz</a></p>
                                </div>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
            </div>
            <div id="product">
                <div id="product_description">
                    Let us introduce you the Music Library used for managing songs. Each song has a title, bitrate, its position on the album's playlist, and commentary. 
                    For the sake of simplicity, each song belongs to exactly one musician, it is part of exactly one album, and can be of exactly one genre. 
                    Each album has basic attributes such as date of release, title, commentary, musician and album art. 
                    In order to support compilation albums each album can contain songs of different genres from different musicians.
                </div>
            </div>
        </div>
    </jsp:attribute>
</my:layout>