// Avoid `console` errors in browsers that lack a console.
(function () {
    var method;
    var noop = function () {
    };
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = (window.console = window.console || {});

    while (length--) {
        method = methods[length];

        // Only stub undefined methods.
        if (!console[method]) {
            console[method] = noop;
        }
    }
}());

// Place any jQuery/helper plugins in here.

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
    $("#w-input-search").autocomplete({
        minLength: 2,
        delay: 500,
        //define callback to format results
        source: function (request, response) {
            $.getJSON("/music/getAlbums", request, function (result) {
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
        select : function(event, ui) {
            getAlbum(ui.item.id);
            showPopup();
        }
    });
});