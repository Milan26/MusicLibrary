/*********************************************************************************/
/* Helper functions                                                              */
/*********************************************************************************/

/*** Popup box ***/

/* Cached variables */
var info,
    songs;

function preparePopup() {
    info.html('');
    $("#album_songs tbody").remove();
    songs.append(" <tbody></tbody> ");
}

function appendRowsToSongsTable(songs) {
    for (var i = 0; i < songs.length; i++) {
        $("#album_songs tbody").append(" <tr> " +
            " <td> " + songs[i].trackNumber + " </td> " +
            " <td class='title'> " + songs[i].title + " </td> " +
            " <td> " + getTime(songs[i].duration) + " </td> " +
            " <td> " + songs[i].genre + " </td> " +
            " </tr> "
        )
    }
}

function appendAlbumInfo(album) {
    var releaseDate = $.datepicker.formatDate('MM dd, yy', new Date(album.releaseDate));
    $("#info").append(
        " <img src= " + album.coverArt + " /> " +
        " <div> " +
        " <h3> " + album.title + " </h3> " +
        " <h5>" + releaseDate + " </h5> " +
        " </div> ");
}

function getAlbum(id) {
    preparePopup();

    $.ajax({
        type: "GET",
        url: "/music/albums/" + id,
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
    var width = $("#gallery").width();
    var numberOfItemsPerRow = width / idealWidth;
    return (width / Math.floor(numberOfItemsPerRow));
}

function setItemWidth(itemWidth) {
    thumbs.css('width', itemWidth -.1);
    thumbs.css('height', itemWidth -.1);
}

/*********************************************************************************/
/* Main                                                                          */
/*********************************************************************************/

$(document).ready(function () {
    thumbs = $('.thumb');
    info = $("#info");
    songs = $("#album_songs");

    setItemWidth(calculateItemWidth(224));
    /* Set width of thumbs */
    $( window ).resize(function() {
        setItemWidth(calculateItemWidth(224));
    });

    $("#views option:first").prop("selected", true);
});

$(document).ready(function () {
    $("div.holder").jPages({
        containerID: "gallery",
        perPage: 32,
        startPage: 1,
        startRange: 1,
        midRange: 7,
        endRange: 1
    });
});

$(document).ready(function () {
    //attach autocomplete
    $("#searchbox").autocomplete({
        minLength: 2,
        delay: 500,
        //define callback to format results
        source: function (request, response) {
            $.getJSON("/music/albums/search", request, function (result) {
                response($.map(result, function (item) {
                    return {
                        // custom property for select handler
                        id: item.id,
                        // following property gets displayed in drop down
                        label: item.title,
                        // following property gets entered in the textbox
                        value: item.title
                    }
                }));
            });
        },
        //define select handler
        select: function (event, ui) {
            getAlbum(ui.item.id);
            showPopup();
        }
    });
});