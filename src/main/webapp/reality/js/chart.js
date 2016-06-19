/**
 * Created by Administrator on 2016/6/4.
 */
var xMaxNumber = 20;
window.onload = function () {
    $('.dropdown-submenu a.test').on("click", function (e) {
        $(this).next('ul').toggle();
        e.stopPropagation();
        e.preventDefault();
    });
    ariconditionStatistics();
    lightStatistics();
    antitheftStatistics();
    parkingStatistics();
}




function ariconditionStatistics() {
    $("#chart1").CanvasJSChart({
        title: {
            text: "空调",
            fontSize: 15
        },
        legend: {
            verticalAlign: "center",
            horizontalAlign: "right"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    {label: "开放", y: 30.3, legendText: "开放"},
                    {label: "关闭", y: 19.1, legendText: "关闭"}
                ]
            }
        ]
    });
}
function lightStatistics() {
    $("#chart2").CanvasJSChart({
        title: {
            text: "灯",
            fontSize: 15
        },
        legend: {
            verticalAlign: "center",
            horizontalAlign: "right"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    {label: "开灯", y: 50, legendText: "开灯"},
                    {label: "关闭", y: 50, legendText: "关闭"},
                ]
            }
        ]
    });
}
function antitheftStatistics() {
    $("#chart3").CanvasJSChart({
        title: {
            text: "防盗器",
            fontSize: 15
        },
        legend: {
            verticalAlign: "center",
            horizontalAlign: "right"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    {label: "打开", y: 35, legendText: "打开"},
                    {label: "关闭", y: 65, legendText: "关闭"},
                ]
            }
        ]
    });
}
function parkingStatistics() {
    $("#chart4").CanvasJSChart({
        title: {
            text: "停车位",
            fontSize: 15
        },
        legend: {
            verticalAlign: "center",
            horizontalAlign: "right"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    {label: "有车", y: 30.3, legendText: "有车"},
                    {label: "无车", y: 19.1, legendText: "无车"}
                ]
            }
        ]
    });
}