$(function () {
    userList();
});

function userList() {
    $.ajax({
        type: "GET",
        async: false,
        url: projectName + "sysUser/list",
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            var dataList = data.data;
            $("#userDataInfo").html("");
            dataList.forEach(function (obj, index) {
                $("#userDataInfo").append('<tr><td>' + (index + 1) + '</td>'
                    + '<td>' + obj.username + '</td>'
                    + '<td>' + obj.mobile + '</td>'
                    + '<td>' + obj.email + '</td>'
                    + '<td><button type="button" onclick="jumpRole(' + obj.userId + ')">角色管理</button></td></tr>');
            });
        }
    });
}

function jumpRole(userId) {
    localStorage.setItem("userId", userId);
    window.location.href = "./user-role.html";
}


