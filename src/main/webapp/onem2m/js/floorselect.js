/**
 * Created by Administrator on 2016/6/5.
 */

$(document).ready(function () {
    $(".parkingStatistics  li a").click(function () {
        $.getJSON("/parkingStatistics.do", {}, function (data) {
            var chart = $("#parkingStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.used;
            chart.options.data[0].dataPoints[1].y = data.unUsed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
    $(".antiTheftStatistics  li a").click(function () {
        $.getJSON("/antiTheftStatistics.do", {}, function (data) {
            var chart = $("#antiTheftStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
    $(".lightStatusStatistics  li a").click(function () {
        $.getJSON("/lightStatusStatistics.do", {}, function (data) {
            var chart = $("#lightStatusStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
    $(".airconditionStatistics  li a").click(function () {
        $.getJSON("/airConditionStatistics.do", {}, function (data) {
            var chart = $("#airconditionStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
});