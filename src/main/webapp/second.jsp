<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript">
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer",
                    {
                        title: {
                            text: "Using formatNumber Function"
                        },
                        axisY: {
                            labelFormatter: function (e) {
                                return "$" + CanvasJS.formatNumber(e.value, "#,#00");
                            },
                            includeZero: false
                        },
                        data: [
                            {
                                type: "spline",
                                dataPoints: [
                                    { y: 1088 },
                                    { y: 1104 },
                                    { y: 1112 },
                                    { y: 1136 },
                                    { y: 1157 },
                                    { y: 1162 },
                                    { y: 1180 }
                                ]
                            }
                        ]
                    });
            chart.render();
        }
    </script>
    <script src="canvas/canvasjs.min.js"></script>
</head>
<body>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
</body>
</html>