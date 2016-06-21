<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/5
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <meta charset="UTF-8">
    <title>oneM2M</title>

    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <script type="text/javascript" src="../jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="../canvas/jquery.canvasjs.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/retrieve.js"></script>
</head>
<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#">A停车场</a></li>
            <li role="presentation"><a href="#">B停车场</a></li>
            <li role="presentation"><a href="#">C停车场</a></li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-1">
        <ul class="nav nav-pills nav-stacked nav-vertical-width">
            <li role="presentation" class="active"><a href="#">一层</a></li>
            <li role="presentation"><a href="#">2层</a></li>
        </ul>
    </div>
    <div class="col-md-10">

        <c:forEach var="i" begin="1" end="5">
            <c:out value="<div class='row'>" escapeXml="false"></c:out>
            <c:forEach var="j" begin="1" end="10">
                <c:out value="  <div class='col-md-1 div-top car-background right'><i class='fa fa-car'></i></div>"
                       escapeXml="false"></c:out>
            </c:forEach>
            <c:out value="</div>" escapeXml="false"></c:out>
        </c:forEach>
    </div>
</div>
</body>
</html>
