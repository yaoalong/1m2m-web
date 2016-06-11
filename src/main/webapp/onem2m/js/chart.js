/**
 * Created by Administrator on 2016/6/4.
 */
var xMaxNumber=20;
window.onload = function () {
    tps();
    serverLoad();
    ariconditionStatistics();
    lightStatistics();
    antitheftStatistics();
    parkingStatistics();
    var updateChart = function () {
        $.getJSON("/connection/tps.do", {}, function (data) {
            var chart = $("#chartContainer").CanvasJSChart();
            var length = chart.options.data[0].dataPoints.length;
            if(length>xMaxNumber){
                chart.options.data[0].dataPoints.shift();
            }
            chart.options.data[0].dataPoints.push({ x: new Date(), y: data.connectionNumber});
            chart.render();
            chart = $("#serverLoadChart").CanvasJSChart();
            var length = chart.options.data[0].dataPoints.length;
            if(length>xMaxNumber){
                chart.options.data[0].dataPoints.shift();
            }
            chart.options.data[0].dataPoints.push({ x: new Date(), y: data.avgResonseTime});
            chart.render();

        });


    }
    setInterval(function () {
        updateChart();
    }, 1000);
}
function tps(){
    var data = [];
    $("#chartContainer").CanvasJSChart({
        title: {
            text: "连接信息",
            fontSize: 22
        },
        axisY: {
            title: "连接数",
        },
        axisX:{
            valueFormatString: "HH:ss:mm" ,
            labelAngle: 45,
            labelFontSize: 15,
        },
        data: [{
            type: "line",
            dataPoints: data
        }
        ]
    });


};
function serverLoad() {
    var data = [];
    $("#serverLoadChart").CanvasJSChart({
        title: {
            text: "响应时间",
            fontSize: 22
        },
        axisY: {
            title: "响应时间(/ms)",
        },
        axisX:{
            valueFormatString: "HH:ss:mm" ,
            labelAngle: 45,
            labelFontSize: 15,

        },
        data: [{
            type: "line",
            dataPoints: data
        }
        ]
    });
}



function ariconditionStatistics(){
    $("#airconditionStatistics").CanvasJSChart({
        title: {
            text: "空调开放情况",
            fontSize: 24
        },
        legend :{
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
                    { label: "开放",  y: 30.3, legendText: "开放"},
                    { label: "关闭",    y: 19.1, legendText: "关闭"  }
                ]
            }
        ]
    });
}
function lightStatistics(){
    $("#lightStatusStatistics").CanvasJSChart({
        title: {
            text: "灯打开情况",
            fontSize: 24
        },
        legend :{
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
                    { label: "开灯",  y: 50, legendText: "开灯"},
                    { label: "关闭",    y: 50, legendText: "关闭"  },
                ]
            }
        ]
    });
}
function antitheftStatistics(){
    $("#antiTheftStatistics").CanvasJSChart({
        title: {
            text: "防盗器打开情况",
            fontSize: 24
        },
        legend :{
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
                    { label: "打开",  y:35, legendText: "打开"},
                    { label: "关闭",    y: 65, legendText: "关闭"  },
                ]
            }
        ]
    });
}
function parkingStatistics(){
    $("#parkingStatistics").CanvasJSChart({
        title: {
            text: "停车位情况",
            fontSize: 24
        },
        legend :{
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
                    { label: "有车",  y: 30.3, legendText: "有车"},
                    { label: "无车",    y: 19.1, legendText: "无车"  }
                ]
            }
        ]
    });
}