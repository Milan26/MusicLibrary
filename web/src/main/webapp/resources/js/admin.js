$(document).ready(function () {
    var dataTable = $('#data-table').dataTable({
        "dom": '<"top"lp<"clear">>rt<"bottom"if<"clear">>',
        "columnDefs": [
            {
                "width": "85px", "targets": [-1, -2],
                "orderable": false, "targets": [-1, -2],
                "searchable": false, "targets": [-1, -2]
            }
        ]
    });
});
