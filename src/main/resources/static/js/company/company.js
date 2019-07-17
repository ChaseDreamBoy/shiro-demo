$(function () {

});

function listCompany() {
    var token = $("#list-token").val();
    $.ajax({
        type: "GET",
        async: false,
        url: projectName + "company/list",
        data: {"token": token},
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            alert(data.msg);
        }
    });
}

