/**
 * Created by yaoalong on 2016/6/18.
 */
var banId = 1;
var floorId = 1;
var apartmentId = 1;
var red = "#FF0000";
var green = "#008000";
$(document).ready(function () {

    $(".banId").click(function () {
        banId = $(this).attr("id");
        $("#banId").text(banId);
        $(".parkingArea").css("display", "none");
        getFloorStatistics();
        getApartmentStatistics();
    });
    /**
     * 获取空调的统计信息
     */
    $.getJSON("/allAirConditionStatistics.do", {}, function (data) {
        var chart = $("#chart1").CanvasJSChart();
        chart.options.data[0].dataPoints[0].y = data.open;
        chart.options.data[0].dataPoints[1].y = data.closed;
        chart.render();
    });
    /**
     * 获取灯的统计信息
     */
    $.getJSON("/allLightStatusStatistics.do", {}, function (data) {
        var chart = $("#chart2").CanvasJSChart();
        chart.options.data[0].dataPoints[0].y = data.open;
        chart.options.data[0].dataPoints[1].y = data.closed;
        chart.render();
    });
    /**
     * 获取防盗器的统计信息
     */
    $.getJSON("/allAntiTheftStatistics.do", {}, function (data) {
        var chart = $("#chart3").CanvasJSChart();
        chart.options.data[0].dataPoints[0].y = data.open;
        chart.options.data[0].dataPoints[1].y = data.closed;
        chart.render();
    });
    /**
     * 获取停车场的统计信息
     */
    $.getJSON("/allParkingStatistics.do", {}, function (data) {
        var chart = $("#chart4").CanvasJSChart();
        chart.options.data[0].dataPoints[0].y = data.open;
        chart.options.data[0].dataPoints[1].y = data.closed;
        chart.render();
    });
    $("#u247_input").change(function () {
        var checkValue = $(this).val();
        floorId = parseInt(checkValue);
        $("#floorId").text(floorId);
        getFloorStatistics();
        getApartmentStatistics();
    });
    $(".apartmentNO").click(function () {
        var text = $(this).children().html();
        var result = text.split("-");
        apartmentId = result[result.length - 1];
        $("#apartmentId").text(apartmentId);
        getApartmentStatistics();
    });
    function getFloorStatistics() {
        $.getJSON("/getFloorStatistics.do", {key: banId + "c" + floorId}, function (data) {
            $("#apartmentNumber").text(data.antiTheftValues.length);
            for (var i = 1; i <= data.antiTheftValues.length; i++) {
                if (data.antiTheftValues[i] == false) {
                    $("#apartment" + i).css("color", red);
                }
                else {
                    $("#apartment" + i).css("color", green);

                }
                $("#apartment" + i).text(data.antiTheftValues[i] == false ? "不安全" : "安全");
            }
        });
    }

    /**
     * 获取某层的信息
     */
    function getApartmentStatistics() {
        $.getJSON("/getApartmentStatistics.do", {key: banId + "c" + floorId + "c" + apartmentId}, function (data) {
            $("#roomNumbers").text(data.lightSensorValues.length);
            if (data.antiTheft == false) {
                $("#safetyStatus").css("color", red);
            }
            else {
                $("#safetyStatus").css("color", green);

            }
            $("#safetyStatus").text(data.antiTheft == false ? "不安全" : "安全");
            $("#lightSensorNumber").text(data.lightSensorValues.length);
            var i = 0;
            for (var index = 0; index < data.lightStatuses.length; index++) {
                if (data.lightStatuses[index] == false) {
                    i++;
                    $("#light" + index).css("color", green);
                }
                else {
                    $("#light" + index).css("color", red);
                }
                $("#light" + index).text(data.lightStatuses[index] == false ? "开" : "关");
            }
            for (var index = 0; index < data.airConditionStatuses.length; index++) {
                if (data.airConditionStatuses[index] == false) {
                    $("#aircondition" + index).css("color", green);
                }
                else {
                    $("#aircondition" + index).css("color", red);
                }
                $("#aircondition" + index).text(data.airConditionStatuses[index] == false ? "开" : "关");
            }
            for (var index = 0; index < data.lightSensorValues.length; index++) {
                if (data.lightSensorValues[index] == false) {
                    $("#lightSensor" + index).css("color", green);
                }
                else {
                    $("#lightSensor" + index).css("color", red);
                }
                $("#lightSensor" + index).text(data.lightSensorValues[index]);
            }
            for (var index = 0; index < data.temperatureSensorValues.length; index++) {
                if (data.temperatureSensorValues[i] == false) {
                    $("#temperatureSensor" + index).css("color", green);
                }
                else {
                    $("#temperatureSensor" + index).css("color", red);
                }
                $("#temperatureSensor" + index).text(data.temperatureSensorValues[index]);
            }
            $("#lightOpenNumber").text(i);
            $("#ligthOffNumber").text(data.lightSensorValues.length - i);
            $("#temperatureSensorNumber").text(data.temperatureSensorValues.length);
        });
    }

    getFloorStatistics();
    getApartmentStatistics();
});