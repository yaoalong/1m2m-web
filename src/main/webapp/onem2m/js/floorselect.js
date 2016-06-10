/**
 * Created by Administrator on 2016/6/5.
 */

$(document).ready(function(){
    $(".dropdown-menu li a").click( function() {
        var yourText = $(this).text();
        $.getJSON("/statistics.do", {}, function (data) {
            var chart = $("#airconditionStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y=data.airConditionStatistics.open;
            chart.options.data[0].dataPoints[1].y=data.airConditionStatistics.closed;
            chart.render();
             chart = $("#lightStatusStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y=data.lightStatusStatistics.open;
            chart.options.data[0].dataPoints[1].y=data.lightStatusStatistics.closed;
            chart.render();
            chart = $("#antiTheftStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y=data.antiTheftStatistics.open;
            chart.options.data[0].dataPoints[1].y=data.antiTheftStatistics.closed;
            chart.render();
        });

        $(this).attr("href","####");
    });
});