var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function changeStatus() {
    var form = $("#checkbox").is(':checked');
    $.ajax({
        url: ajaxUrl + id,
        type: "PUT",
        success: function () {
            updateTable();
            successNoty("Status changed");
        }
    });
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
                "asc"
            ]
        ]
    });
    makeEditable();
});