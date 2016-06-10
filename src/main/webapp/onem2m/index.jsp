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
    <title>Title</title>
    <meta charset="UTF-8">
    <title>oneM2M</title>
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="../canvas/jquery.canvasjs.min.js"></script>
    <script type="text/javascript" src="js/chart.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/floorselect.js">
    </script>
</head>
<body>
<div class="row first">
    <div id="chartContainer" class="span8 offset3 statistics"></div>
    <div id="serverLoadChart" class="span8 offset3 statistics"></div>
</div>
<div class="row first">
    <div class="select">
        <div class="dropdown">
            <button class="dropdown-toggle" type="button" data-toggle="dropdown">所有
                <span class="caret"></span></button>
            <ul class="dropdown-menu" id="main">
                <c:forEach var="i" begin="1" end="10">
                    <c:out value="<li class='dropdown-submenu'>" escapeXml="false"></c:out>
                    <c:out value="<a href='/bulding${i}'>${i}栋</a>" escapeXml="false"></c:out>
                    <c:out value="<ul class='dropdown-menu'>" escapeXml="false"></c:out>
                    <c:forEach var="w" begin="1" end="10">
                        <c:out value="<li class='dropdown-submenu'>" escapeXml="false"></c:out>
                        <c:out value="<a href='/floor/${w}'>${w}楼</a>" escapeXml="false"></c:out>

                        <c:out value="<ul class='dropdown-menu'>" escapeXml="false"></c:out>
                        <c:forEach var="t" begin="1" end="4">


                            <c:out value="<li class='dropdown-submenu'>" escapeXml="false"></c:out>
                            <c:out value="<a href='/ceil/${t}}'>${t}层</a>" escapeXml="false"></c:out>

                            <c:out value="<ul class='dropdown-menu'>" escapeXml="false"></c:out>
                            <c:forEach var="f" begin="1" end="4">
                                <c:out value="<li><a href='/family/${f}'>${f}户</a></li>" escapeXml="false"></c:out>
                            </c:forEach>
                            <c:out value="</ul>" escapeXml="false"></c:out>
                            <c:out value="</li>" escapeXml="false"></c:out>
                        </c:forEach>
                        <c:out value="</ul>" escapeXml="false"></c:out>
                        <c:out value="</li>" escapeXml="false"></c:out>
                    </c:forEach>
                    <c:out value="</ul>" escapeXml="false"></c:out>
                    <c:out value="</li>" escapeXml="false"></c:out>
                </c:forEach>
            </ul>

        </div>
    </div>
</div>
<div class="row">
    <div id="airconditionStatistics" class="span6 offset3" style="height: 300px"></div>
    <div id="lightStatusStatistics" class="span6 offset3" style="height: 300px"></div>
</div>

<div class="row">
    <div id="antiTheftStatistics" class="span6 offset3" style="height: 300px"></div>
    <div id="parkingStatistics" class="span6 offset3" style="height: 300px"></div>
</div>
</body>
</html>
