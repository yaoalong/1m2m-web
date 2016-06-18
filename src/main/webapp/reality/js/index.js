/**
 * Created by yaoalong on 2016/6/18.
 */
var banId = 1;
var floorId = 1;
$(document).ready(function () {

    $(".banId").click(function () {
        banId = $(this).attr("id");
        getFloorStatistics();
    });
    $("#u247_input").change(function () {
        var checkValue = $(this).val();
        floorId=parseInt(checkValue);
        getFloorStatistics();
    })
    function getFloorStatistics(){
        $.getJSON("/getFloorStatistics.do", {key: banId + "c" + floorId}, function (data) {
            console.log(data.antiTheftValues.length);
            $("#apartmentNumber").text(data.antiTheftValues.length);
            for (var i = 1; i <= data.antiTheftValues.length; i++) {
                if (data.antiTheftValues[i] == false) {
                    $("#apartment" + i).css("color", "#FF0000");
                }
                else {
                    $("#apartment" + i).css("color", "#009900");

                }
                $("#apartment" + i).text(data.antiTheftValues[i] == false ? "不安全" : "安全");
            }
        });
    }
    getFloorStatistics();


});