<!DOCTYPE html>
<html>
<head>
    <script src="canvas/canvasjs.min.js"></script>
    <script src="jquery/jquery-2.2.4.min.js"></script>
    <script type = "text/javascript" >
        window.onload = function () {
            var dataPoints = [{y : 10}, {y : 13}, {y : 18}, {y : 20}, {y : 17}];
            var chart = new CanvasJS.Chart("chartContainer", {
                title : {
                    text : "Dynamic Data"
                },
                data : [{
                    type : "spline",
                    dataPoints : dataPoints
                }
                ]
            });

            chart.render();

            var yVal = 15, updateCount = 0;
            var updateChart = function () {

                yVal = yVal + Math.round(5 + Math.random() * (-5 - 5));
                updateCount++;

                dataPoints.push({
                    y : yVal
                });

                chart.options.title.text = "Update " + updateCount;
                chart.render();

            };

            // update chart every second
            setInterval(function(){updateChart()}, 1000);
        }
    </script>
</head>
<body>
<div id="chartContainer" style="width:100%; height:280px"></div>
</body>
</html>