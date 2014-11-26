var black_overlay,
    popup_box;

function showPopup() {
    black_overlay.show();
    popup_box.show();
}
function hidePopup() {
    black_overlay.hide();
    popup_box.hide();
}

function getTime(seconds) {
    var minutes = parseInt(seconds / 60);
    var left_seconds = (seconds % 60);
    return minutes + ":" + (left_seconds < 10 ? "0" + left_seconds : left_seconds);
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

    document.getElementById("views").addEventListener('change', function () {
        window.location = this.value;
    }, false);
});


