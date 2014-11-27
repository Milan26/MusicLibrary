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

/*********************************************************************************/
/* Main                                                                          */
/*********************************************************************************/

$(document).ready(function () {

    black_overlay = $('#black_overlay');
    popup_box = $('#popup_box');

    /* Hide popup */
    $(document).keyup(function (e) {
        // enter
        if (e.keyCode == 13) {
            hidePopup();
        }
        // esc
        if (e.keyCode == 27) {
            hidePopup();
        }
    });

    document.getElementById("black_overlay").addEventListener('click', function () {
        hidePopup();
    }, false);

    document.getElementById("views").addEventListener('change', function () {
        window.location = this.value;
    }, false);
});


