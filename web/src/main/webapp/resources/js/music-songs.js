var info;

function preparePopup() {
    info.html('');
}

function appendSongInfo(song) {
    $("#info").append(
        " <img src= " + song.album.coverArt + " /> " +
        " <div> " +
        " <h3>Title: " + song.title + " </h3> " +
        " <h3>Artist: " + song.artist.alias + " </h3> " +
        " <h3>Album: " + song.album.title + " </h3><br/> " +
        " <h1>Length: " + getTime(song.duration) + " </h1> " +
        " <h1>Genre: " + song.genre + " </h1> " +
        " </div> ");
}

function getSong(id) {
    preparePopup();

    $.ajax({
        type: "GET",
        url: "songs/" + id,
        dataType: "json",

        success: function (song) {
            appendSongInfo(song);
            showPopup();
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }
    });
}

/*********************************************************************************/
/* Main                                                                          */
/*********************************************************************************/

$(document).ready(function () {
    info = $("#info");

    $("#views option:last").prop("selected", true);

    $("#holder").prop("hidden", true);
    // because of pagination on Music-Albums site
    $("#gallery_wrapper").css("margin-top", "28px");
});

$(document).ready(function () {
    var dataTable = $('#songs').dataTable({
        "dom": '<"top"lp<"clear">>rt<"bottom"if<"clear">>'
    });

    $("#searchbox").keyup(function () {
        dataTable.fnFilter(this.value);
    });
});