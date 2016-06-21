/**
 * Created by yaoalong on 2016/6/19.
 */
/**
 * Created by yaoalong on 2016/6/18.
 */
var parkingFloor = 0;
var parkingRegion = 0;
var parkingId = 0;

$(document).ready(function () {
    $(".parkingFloorPosition ul li").click(function () {
        $(this).parent().find('li').each(function () {
            $(this).removeClass("active");
        });
        parkingFloor = parseInt($(this).children().text().split("层")[0]) - 1;
        $(this).addClass("active");
        getParkingStatistics();
    });
    $(".parkingAreaPosition ul li").click(function () {
        $(this).parent().find('li').each(function () {
            $(this).removeClass("blue");
        });
        parkingRegion = parseInt($(this).children().attr("value"));
        $(this).addClass("blue");
        getParkingStatistics();
    });
    $("#parkingButton").click(function () {
        $(".parkingArea").css("display", "block");
        getParkingStatistics();
    });
    $(".parking  p span").click(function () {
        parkingId = parseInt($(this).text()) - 1;
        retrieveParkingStatus();
    });
    $(".parking2  p span").click(function () {
        parkingId = parseInt($(this).text()) - 1;
        retrieveParkingStatus();
    });
    $(".parkingFloor3  p span").click(function () {
        parkingId = parseInt($(this).text()) - 1;
        retrieveParkingStatus();
    });
    function getParkingStatistics() {
        $.getJSON("/getParkingFlooAndRegion.do", {key: parkingFloor + "c" + parkingRegion}, function (data) {
            $("#parkingRegion").text(parkingRegion + 1);
            $("#parkingFloor").text(parkingFloor + 1);
            $("#parkingCount").text(data.sum);
            $("#parkingUnUsed").text(data.unUsed);
            $("#parkingStatus").text(data.unUsed ? "空闲" : "被占用");
        });
    }

    function retrieveParkingStatus() {
        var result = parkingFloor + "c" + parkingRegion + "c" + parkingId;
        $.getJSON("/parkingRetrieve.do", {key: result}, function (data) {
            $("#parkingId").text(parkingId + 1);
            $("#parkingstatus").text(data.unUsed ? "空闲" : "被占用");
        });
    }

});