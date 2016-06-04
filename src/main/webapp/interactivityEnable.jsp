<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/3
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript">
        window.onload=function () {
            var chart=new CanvasJS.Chart("chartContainer",{
                interactivityEnabled: true,
                exportEnabled:true,
                exportFileName:"Ranges Spline Area",
                axisY2:{
                    title: "axisY2 Title",
                    interlacedColor: "#F8F1E4"
                },
                title:{
                    text:"Chart with Interactivity Disable"
                },
                data:[
                    {
                        type:"pie",
                        dataPoints:[
                            {x:10,y:70},
                            { x: 20, y: 55},
                            { x: 30, y: 50 },
                            { x: 40, y: 65 },
                            { x: 50, y: 95 },
                            { x: 60, y: 68 },
                            { x: 70, y: 28 },
                            { x: 80, y: 34 },
                            { x: 90, y: 14}

                        ]
                    }
                ]
            });
            chart.render();
        }
    </script>
    <script type="text/javascript" src="canvas/canvasjs.min.js"></script>
</head>
<body>
    <div id="chartContainer" style="height:300px;width:100%;"></div>
</body>
</html>
