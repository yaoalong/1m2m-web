<!DOCTYPE HTML>
<html>


<head>
    <script type="text/javascript">
        window.onload=function () {
            var chart=new CanvasJS.Chart("chartContainer",{
                title:{
                    text:"Number of Student"
                },
                axisX:{
                    title:"Rooms"
                },
                axisY:{
                    title:"percentent"
                },
                data:[
                    {
                        type:"stackedColumn100",
                        indexLabel:"#percent %",
                        indexLabelPlacement:"inside",
                        indexLabelFontColor:"white",
                        dataPoints:[
                            {  y: 40, label: "Cafeteria"},
                            {  y: 10, label: "Lounge" },
                            {  y: 72, label: "Games Room" },
                            {  y: 30, label: "Lecture Hall" },
                            {  y: 21, label: "Library"}
                        ]
                    },
                    {
                        type:"stackedColumn100",
                        indexLabel:"#percent %",
                        indexLabelPlacement:"inside",
                        indexLabelFontColor:"white",
                        dataPoints:[
                            {  y: 11, label: "Cafeteria"},
                            {  y: 50, label: "Lounge" },
                            {  y: 2, label: "Games Room" },
                            {  y: 80, label: "Lecture Hall" },
                            {  y: 44, label: "Library"}
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
    <div id="chartContainer" style="height:300px;width:100%;"></div></body>

</body>

</html>