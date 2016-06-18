/**
 * Created by yaoalong on 2016/6/18.
 */
var banId = 1;
var floorId = 1;
var apartmentId = 1;
var red = "#FF0000";
var green = "#FF0000";
$(document).ready(function () {

    $(".banId").click(function () {
        banId = $(this).attr("id");
        getFloorStatistics();
    });
    $("#u247_input").change(function () {
        var checkValue = $(this).val();
        floorId = parseInt(checkValue);
        getFloorStatistics();
    })
    function getFloorStatistics() {
        $.getJSON("/getFloorStatistics.do", {key: banId + "c" + floorId}, function (data) {
            console.log(data.antiTheftValues.length);
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

    function getParkingStatistics() {
        $.getJSON("/getApartmentStatistics.do", {key: banId + "c" + floorId + "c" + floorId}, function (data) {
            $("#roomNumbers").text(data.lightSensorValues.length);
            if (data.antiTheft == false) {
                $("#safetyStatus").css("color", red);
            }
            else {
                $("#safetyStatus").css("color", green);

            }
            $("#safetyStatus").text(data.antiTheft == false ? "不安全" : "安全");
            $("#lightSensorNumber").text(data.lightSensorValues.length);
            var i=0;
            for(var index=0;index<data.lightStatuses.length;index++){
                if(data.lightStatuses[i].isClosed==false){
                    i++;
                    $("#light"+index).css("color", green);
                }
                else{
                    $("#light"+index).css("color", red);
                }
                $("#light"+index).text(data.lightStatuses[index]==false?"开":"关");
            }
            for(var index=0;index<data.airConditionStatuses.length;index++){
                if(data.airConditionStatuses[i].isClosed==false){
                    $("#aircondition"+index).css("color", green);
                }
                else{
                    $("#aircondition"+index).css("color", red);
                }
                $("#aircondition"+index).text(data.airConditionStatuses[index]==false?"开":"关");
            }
            for(var index=0;index<data.lightSensorValues.length;index++){
                if(data.lightSensorValues[i].isClosed==false){
                    $("#lightSensor"+index).css("color", green);
                }
                else{
                    $("#lightSensor"+index).css("color", red);
                }
                $("#lightSensor"+index).text(data.lightSensorValues[index]);
            }
            for(var index=0;index<data.temperatureSensorValues.length;index++){
                if(data.temperatureSensorValues[i].isClosed==false){
                    $("#temperatureSensor"+index).css("color", green);
                }
                else{
                    $("#temperatureSensor"+index).css("color", red);
                }
                $("#temperatureSensor"+index).text(data.temperatureSensorValues[index]);
            }
            $("#lightOpenNumber").text(i);
            $("#ligthOffNumber").text(data.lightSensorValues.length-i);
            $("#temperatureSensorNumber").text(data.temperatureSensorValues.length);
        });
    }

    getFloorStatistics();
    getParkingStatistics();
});