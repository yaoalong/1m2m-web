/**
 * Created by Administrator on 2016/6/4.
 */
window.onload = function () {
    tps();
    serverLoad();
    ariconditionStatistics();
    lightStatistics();
    antitheftStatistics();
    parkingStatistics();
}
function tps(){
    var data = [
    ];
    $("#chartContainer").CanvasJSChart({

        title: {
            text: "tps信息",
            fontSize: 22
        },
        axisY: {
            title: "tps",
        },
        axisX: {
            interval:1,
            tickLength: 10
        },
        data: [{
            type: "line",
            dataPoints: data
        }
        ]
    });
    var i=0;
    var updateChart = function () {
        i++;
        var chart = $("#chartContainer").CanvasJSChart();
        data.push({x: i, y: Math.random()});
        if(data.length>10){
            data.shift();
        }
        chart.render();

    }
    setInterval(function () {
        updateChart();
    }, 1000);
};
function serverLoad() {
    var data = [
        {x: 1, y: 1},
        {x: 2, y: 2}
    ];
    $("#serverLoadChart").CanvasJSChart({

        title: {
            text: "tps信息",
            fontSize: 22
        },
        axisY: {
            title: "tps",
        },
        axisX: {},
        data: [{
            type: "column",
            dataPoints: data
        }
        ]
    });
}



function ariconditionStatistics(){
    $("#airconditionStatistics").CanvasJSChart({
        title: {
            text: "Worldwide Smartphone sales by brand - 2012",
            fontSize: 24
        },
        axisY: {
            title: "Products in %"
        },
        legend :{
            verticalAlign: "center",
            horizontalAlign: "bottom"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    { label: "Samsung",  y: 30.3, legendText: "Samsung"},
                    { label: "Apple",    y: 19.1, legendText: "Apple"  },
                    { label: "Huawei",   y: 4.0,  legendText: "Huawei" },
                    { label: "LG",       y: 3.8,  legendText: "LG Electronics"},
                    { label: "Lenovo",   y: 3.2,  legendText: "Lenovo" },
                    { label: "Others",   y: 39.6, legendText: "Others" }
                ]
            }
        ]
    });
}
function lightStatistics(){
    $("#lightStatistics").CanvasJSChart({
        title: {
            text: "Worldwide Smartphone sales by brand - 2012",
            fontSize: 24
        },
        axisY: {
            title: "Products in %"
        },
        legend :{
            verticalAlign: "center",
            horizontalAlign: "bottom"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    { label: "Samsung",  y: 30.3, legendText: "Samsung"},
                    { label: "Apple",    y: 19.1, legendText: "Apple"  },
                    { label: "Huawei",   y: 4.0,  legendText: "Huawei" },
                    { label: "LG",       y: 3.8,  legendText: "LG Electronics"},
                    { label: "Lenovo",   y: 3.2,  legendText: "Lenovo" },
                    { label: "Others",   y: 39.6, legendText: "Others" }
                ]
            }
        ]
    });
}
function antitheftStatistics(){
    $("#antitheftStatistics").CanvasJSChart({
        title: {
            text: "Worldwide Smartphone sales by brand - 2012",
            fontSize: 24
        },
        axisY: {
            title: "Products in %"
        },
        legend :{
            verticalAlign: "center",
            horizontalAlign: "bottom"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    { label: "Samsung",  y: 30.3, legendText: "Samsung"},
                    { label: "Apple",    y: 19.1, legendText: "Apple"  },
                    { label: "Huawei",   y: 4.0,  legendText: "Huawei" },
                    { label: "LG",       y: 3.8,  legendText: "LG Electronics"},
                    { label: "Lenovo",   y: 3.2,  legendText: "Lenovo" },
                    { label: "Others",   y: 39.6, legendText: "Others" }
                ]
            }
        ]
    });
}
function parkingStatistics(){
    $("#parkingStatistics").CanvasJSChart({
        title: {
            text: "Worldwide Smartphone sales by brand - 2012",
            fontSize: 24
        },
        axisY: {
            title: "Products in %"
        },
        legend :{
            verticalAlign: "center",
            horizontalAlign: "bottom"
        },
        data: [
            {
                type: "pie",
                showInLegend: true,
                toolTipContent: "{label} <br/> {y} %",
                indexLabel: "{y} %",
                dataPoints: [
                    { label: "Samsung",  y: 30.3, legendText: "Samsung"},
                    { label: "Apple",    y: 19.1, legendText: "Apple"  },
                    { label: "Huawei",   y: 4.0,  legendText: "Huawei" },
                    { label: "LG",       y: 3.8,  legendText: "LG Electronics"},
                    { label: "Lenovo",   y: 3.2,  legendText: "Lenovo" },
                    { label: "Others",   y: 39.6, legendText: "Others" }
                ]
            }
        ]
    });
}