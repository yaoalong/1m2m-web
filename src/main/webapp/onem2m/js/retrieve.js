/**
 * Created by yaoalong on 2016/6/10.
 */
$(document).ready(function () {
    $(".image").click(function () {
        alert($(this).attr("id"));
    });
    $("#retrieveParking").click(function () {
        var selectValue = $("#parking").val();
        var floorNumber = selectValue.split("层")[0];
        var parkingNumber = $("#parkingNumber").val();
        if (!parkingNumber) {
            bootbox.alert("车位号不能为空!", function () {
            });
            return;
        }
        var retrieveKey = (parseInt(floorNumber) - 1) * 1000 + (parseInt(parkingNumber)) - 1;
        $.getJSON("/parkingRetrieve.do", {key: retrieveKey}, function (data) {
            if (data.unUsed) {
                bootbox.alert("车位空闲", function () {
                });
            }
            else {
                bootbox.alert("车位被占用", function () {

                });
            }
        });
    });
    $("#retrieveMachine").click(function () {
        var buildingId = $("#buildingId").val().split("栋")[0];
        var floorId = $("#floorId").val().split("层")[0];
        var apartmentId = $("#apartmentId").val().split("户")[0];
        var roomId = $("#roomId").val().split("房间")[0];
        var machineType = $("#machineType").val();
        var machineId;
        if (machineType == "防盗器") {
            machineId = 0;
        }
        else if (machineType == "空调") {
            machineId = 1;
        }
        else {
            machineId = 2;
        }
        var retrieveKey = (parseInt(buildingId) - 1) * 880 + (parseInt(floorId) - 1) * 44 + (parseInt(apartmentId) - 1) * 11 + roomId * machineId;
        console.log("key:" + retrieveKey);
        $.getJSON("/retrieve.do", {key: retrieveKey}, function (data) {
            if (data.isClosed) {
                bootbox.alert("关闭", function () {
                });
            }
            else {
                bootbox.alert("打开", function () {
                });
            }
        });

    });


});