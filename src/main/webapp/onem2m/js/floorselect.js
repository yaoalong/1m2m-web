/**
 * Created by Administrator on 2016/6/5.
 */
$(document).ready(function () {
    $(".parkingStatistics  li a").click(function () {
        var text = $(this).html().split("层")[0];
        $.getJSON("/parkingStatistics.do", {floorId: parseInt(text)}, function (data) {
            tt=text;
            var chart = $("#parkingStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
    });
    $(".antiTheftStatistics  li a").click(function () {
        var text = $(this).attr("id");
        $.getJSON("/antiTheftStatistics.do", {key: text}, function (data) {
            var chart = $("#antiTheftStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
            $(this).attr("href", "####");
        });
        $(this).attr("href", "####");
    });
    $(".lightStatusStatistics  li a").click(function () {
        var text = $(this).attr("id");
        $.getJSON("/lightStatusStatistics.do", {key: text}, function (data) {
            var chart = $("#lightStatusStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
    $(".airconditionStatistics  li a").click(function () {
        var text = $(this).attr("id");
        $.getJSON("/airConditionStatistics.do", {key: text}, function (data) {
            var chart = $("#airconditionStatistics").CanvasJSChart();
            chart.options.data[0].dataPoints[0].y = data.open;
            chart.options.data[0].dataPoints[1].y = data.closed;
            chart.render();
        });
        $(this).attr("href", "####");
    });
});