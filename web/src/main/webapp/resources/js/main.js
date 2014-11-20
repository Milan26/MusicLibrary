function showPopup() {
    $('#black_overlay').css('display', 'block');
    $('#popup_box').css('display', 'block');
}
function hidePopup() {
    $('#black_overlay').css('display', 'none');
    $('#popup_box').css('display', 'none');
}

function calculateItemWidth(idealWidth) {
    var width = $(window).width();
    var numberOfItemsPerRow = (width * 0.96) / idealWidth;
    var itemPadding = 5;
    var usableWidth = (width * 0.96) - (numberOfItemsPerRow * itemPadding);
    return Math.floor(usableWidth) / Math.floor(numberOfItemsPerRow);
}

function setItemWidth(itemWidth) {
    $('.thumb').css('width', itemWidth);
    $('.thumb').css('height', itemWidth);
}

$(document).ready(function () {

    setItemWidth(calculateItemWidth(224));

    /* Hide popup */
    $(document).keyup(function (e) {
        /*  if (e.keyCode == 13) { $('.save').click(); }     // enter*/
        if (e.keyCode == 27) {
            hidePopup();
        }   // esc
    });
    /* Show popup */
    $('.item').click(function () {
        showPopup();
    });
});