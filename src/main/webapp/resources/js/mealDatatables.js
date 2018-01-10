var ajaxUrl = "ajax/profile/meals/";
var datatableApi;


function updateTable() {
    var form = $("#filter");
    $.ajax({
        type: "GET",
        url: ajaxUrl+"filter",
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function clearFilter() {
    $("#filter").find(":input").val("");
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});