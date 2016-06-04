<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript">

        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {
                title:{
                    text: "Number of Students in Each Room"
                },
                data: [
                    {
                        type: "stackedColumn100",
                        dataPoints: [

                            {y: 40, label: "Cafeteria"},
                            {y: 10, label: "Lounge"},
                            {y: 72, label: "Games Room"},
                            {y: 30, label: "Lecture Hall"},
                            {y: 21, label: "Library"}
                        ]
                    }, {
                        type: "stackedColumn100",
                        dataPoints: [
                            {y: 20, label: "Cafeteria"},
                            {y: 14, label: "Lounge"},
                            {y: 40, label: "Games Room"},
                            {y: 43, label: "Lecture Hall"},
                            {y: 17, label: "Library"}
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
<div id="chartContainer" style="height: 300px; width: 100%;">
</div>
</body>

</html>