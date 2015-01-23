$(document).ready(function () {
    var dataTable = $('#data-table').dataTable({
        "dom": '<"top"flp<"clear">>rt<"bottom"i<"clear">>',
        "columnDefs": [
            {
                "width": "85px", "targets": [-1, -2],
                "orderable": false, "targets": [-1, -2],
                "searchable": false, "targets": [-1, -2]
            }
        ]
    });
});
