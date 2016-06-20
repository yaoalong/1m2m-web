/**
 * Created by yaoalong on 2016/6/19.
 */
/**
 * Created by yaoalong on 2016/6/18.
 */
var parkingFloor = 1;
var parkingRegion = 1;
var parkingId = 1;
var parkingRegionName="A";
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
        parkingRegionName=$(this).children().text();
        $(this).addClass("blue");
        getParkingStatistics();
    });
    $("#parkingButton").click(function () {
        $(".parkingArea").css("display", "block");
        getParkingStatistics();
    });
    $(".parking  p span").click(function () {
        parkingId = parseInt($(this).text()) ;
        retrieveParkingStatus();
    });
    $(".parkingFloor2  p span").click(function () {
        parkingId = parseInt($(this).text()) ;
        retrieveParkingStatus();
    });
    $(".parkingFloor3  p span").click(function () {
        parkingId = parseInt($(this).text()) ;
        retrieveParkingStatus();
    });
    updateParkingNo();
    /**
     * 设置车位的编码
     */
    function updateParkingNo(){
        $(".parkingFloor3").each(function () {
            var tex=$(this).children().children().text();
            var result = tex.split("-");
            $(this).children().children().text(parkingRegionName+"-"+floorId+"-"+result[result.length - 1]);
        });
        $(".parkingFloor2").each(function () {
            var tex=$(this).children().children().text();
            var result = tex.split("-");
            $(this).children().children().text(parkingRegionName+"-"+floorId+"-"+result[result.length - 1]);
        });
        $(".parking").each(function () {
            var tex=$(this).children().children().text();
            var result = tex.split("-");
            $(this).children().children().text(parkingRegionName+"-"+floorId+"-"+result[result.length - 1]);
        });
    }
    function getParkingStatistics() {
        $.getJSON("/getParkingFlooAndRegion.do", {key: (parkingFloor-1) + "c" + (parkingRegion-1)}, function (data) {
            $("#parkingRegion").text(parkingRegion);
            $("#parkingFloor").text(parkingFloor);
            $("#parkingCount").text(data.sum);
            $("#parkingUnUsed").text(data.unUsed);
            $("#parkingStatus").text(data.unUsed ? "空闲" : "被占用");
        });
    }

    function retrieveParkingStatus() {
        var result = (parkingFloor-1) + "c" + (parkingRegion-1) + "c" + (parkingId-1);
        $.getJSON("/parkingRetrieve.do", {key: result}, function (data) {
            $("#parkingId").text(parkingId );
            $("#parkingstatus").text(data.unUsed ? "空闲" : "被占用");
        });
    }

});