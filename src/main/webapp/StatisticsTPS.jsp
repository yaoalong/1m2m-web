<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/3
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tps statistics</title>
    <script type="text/javascript" src="jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="canvas/jquery.canvasjs.min.js"></script>
    <script type="text/javascript" src="onem2m/js/chart.js"></script>
    <script type="text/javascript">
        window.onload = function () {
                tps();
        }

    </script>
</head>
<body>
<div id="chartContainer" style="width: 100%; height: 300px"></div>
</body>
</html>
