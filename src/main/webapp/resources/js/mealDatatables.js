var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
    }).done(updateTableByData);
}



function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    if (type === "display") {
                       return data.replace("T"," ").substring(0,16)
                    }
                    return data;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            /*if (data.exceed) {
                $(row).addClass("exceeded");
            }
            else $(row).addClass("normal");*/
            data.exceed?$(row).addClass("exceeded"):$(row).addClass("normal");
        },
        "initComplete": makeEditable
    });

    $('#startDate').datetimepicker(
        {
            timepicker:false,
            format:'Y-m-d'
        }
    );

    $('#endDate').datetimepicker(
        {
            timepicker:false,
            format:'Y-m-d'
        }
    );

    $('#startTime').datetimepicker(
        {
            datepicker:false,
            format:'H:i'
        }
    );

    $('#endTime').datetimepicker(
        {
            datepicker:false,
            format:'H:i'
        }
    );

    $('#dateTime').datetimepicker(
        {
            format:'Y-m-d H:i'
        }
    );

});