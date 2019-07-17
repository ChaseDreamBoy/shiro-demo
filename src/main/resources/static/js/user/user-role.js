$(function () {
    var userId = localStorage.getItem("userId");
    // localStorage.removeItem("userId");
    // localStorage.clear();​
    // https://segmentfault.com/a/1190000012057010
    getUserRoleList(userId);
    getUserNotHaveRoles(userId);
});

function getUserRoleList(userId) {
    $.ajax({
        type: "GET",
        async: false,
        url: projectName + "sys-user-role/userRoles",
        data: {"userId": userId},
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            var dataList = data.data;
            $("#userRoleListTable").html("");
            dataList.forEach(function (obj, index) {
                $("#userRoleListTable").append('<tr><td>' + (index + 1) + '</td>'
                    + '<td>' + obj.roleName + '</td>'
                    + '<td>' + obj.remark + '</td>'
                    + '<td><button type="button" onclick="delUserRole(' + obj.userRoleId + ',' + userId + ')">解绑</button></td></tr>');
            });
        }
    });
}

function delUserRole(userRoleId, userId) {
    $.ajax({
        type: "POST",
        async: false,
        url: projectName + "sys-user-role/delUserRole",
        data: {"userRoleId": userRoleId, "userId": userId},
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            alert(data.msg);
        }
    });
}

function getUserNotHaveRoles(userId) {
    $.ajax({
        type: "GET",
        async: false,
        url: projectName + "sys-role/roleNotInUser",
        data: {"userId": userId},
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            var dataList = data.data;
            $("#userNotHasRoleListTable").html("");
            dataList.forEach(function (obj, index) {
                $("#userNotHasRoleListTable").append('<tr><td>' + (index + 1) + '</td>'
                    + '<td>' + obj.roleName + '</td>'
                    + '<td>' + obj.remark + '</td>'
                    + '<td><button type="button" onclick="addUserRole(' + obj.roleId + ',' + userId + ')">绑定</button></td></tr>');
            });
        }
    });
}

function addUserRole(roleId, userId) {
    $.ajax({
        type: "POST",
        async: false,
        url: projectName + "sys-user-role/addUserRole",
        data: {"userId": userId, "roleId": roleId},
        error: function () {
            alert('请求失败');
        },
        success: function (data) {
            alert(data.msg);
        }
    });
}

