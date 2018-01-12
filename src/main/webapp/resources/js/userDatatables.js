var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function changeEnabled(id ,enabled) {
    $.ajax({
        type: "POST",
        url: ajaxUrl+id+"?enabled="+!enabled,
        success: function () {
            updateTable();
            successNoty("Enable changed");
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
        ],
        "createdRow": function (row, data, index) {
            var enabled=data['enabled'].toString();
            if ((enabled==="false"||enabled.length>5&&enabled.indexOf("checked") === -1)) {
                $(row).addClass('disabled');
            }
        }

    });

    makeEditable();
});