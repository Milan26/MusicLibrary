/*********************************************************************************/
/* Helper functions                                                              */
/*********************************************************************************/

/*** Popup box ***/

/* Cached variables */
var info,
    songs,
    black_overlay,
    popup_box;

function preparePopup() {
    info.html('');
    $("#songs tbody").remove();
    songs.append(" <tbody></tbody> ");
}

function showPopup() {
    black_overlay.show();
    popup_box.show();
}
function hidePopup() {
    black_overlay.hide();
    popup_box.hide();
}

function getTime(seconds) {
    var minutes = parseInt(seconds/60);
    var left_seconds = (seconds%60);
    return minutes + ":" + (left_seconds < 10 ? "0" + left_seconds : left_seconds);
}

function appendRowsToSongsTable(songs) {
    for (var i = 0; i < songs.length; i++) {
        $("#songs tbody").append(" <tr> " +
            " <td> " + songs[i].trackNumber + " </td> " +
            " <td class='title'> " + songs[i].title + " </td> " +
            " <td> " + getTime(songs[i].duration) + " </td> " +
            " <td> " + songs[i].genre + " </td> " +
            " </tr> "
        )
    }
}

function appendAlbumInfo(album) {
    $("#info").append(
        " <img src= " + album.coverArt + " + /> " +
        " <div> " +
        " <h3> " + album.title + " </h3> " +
        " <h2> " + "Artist" + "</h2> " +
        " </div> ");
}

function getAlbum(id) {
    preparePopup();

    $.ajax({
        type: "GET",
        url: "/music/getAlbum?id=" + id,
        dataType: "json",

        success: function (album) {
            appendAlbumInfo(album);
            appendRowsToSongsTable(album.songs);
            showPopup();
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }
    });
}

/*** Item width ***/

/* Cached variables */
var thumbs;

function calculateItemWidth(idealWidth) {
    var width = $(window).width();
    var numberOfItemsPerRow = (width * 0.96) / idealWidth;
    var itemPadding = 5;
    var usableWidth = (width * 0.96) - (numberOfItemsPerRow * itemPadding);
    return Math.floor(usableWidth) / Math.floor(numberOfItemsPerRow);
}

function setItemWidth(itemWidth) {
    thumbs.css('width', itemWidth);
    thumbs.css('height', itemWidth);
}

/*********************************************************************************/
/* Main                                                                          */
/*********************************************************************************/

$(document).ready(function () {

    thumbs = $('.thumb');
    info = $("#info");
    songs = $("#songs");
    black_overlay = $('#black_overlay');
    popup_box = $('#popup_box');

    /* Set width of thumbs */
    setItemWidth(calculateItemWidth(224));

    /* Hide popup */
    $(document).keyup(function (e) {
        /*  if (e.keyCode == 13) { $('.save').click(); }     // enter*/
        // esc
        if (e.keyCode == 27) {
            hidePopup();
        }
    });
});