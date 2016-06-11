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
    <title>检索</title>
    <meta charset="UTF-8">
    <title>oneM2M</title>
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link href="css/normalize.css" rel="stylesheet">
    <link href="css/skeleton.css" rel="stylesheet">
    <script type="text/javascript" src="../jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="../canvas/jquery.canvasjs.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/retrieve.js"></script>
</head>
<body>
<div class="row first">
    <div class="span2 offset3"><input type='button' value='查看设备状态' class='right button-primary'></div>
    <div class="select  span10 offset1">
        <form class="bs-example bs-example-form " role="form">
            <div class="form-inline">
                <label>楼栋</label>
                <select class="form-control" name="ddd">
                    <c:forEach var="i" begin="1" end="10">
                        <c:out value="<option class='set-center'>${i}栋</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>
                <label>楼层</label>
                <select class="form-control" name="ddd">
                    <c:forEach var="i" begin="1" end="10">
                        <c:out value="<option class='set-center'>${i}层</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>
                <label>户</label>
                <select class="form-control" name="ddd">
                    <c:forEach var="i" begin="1" end="10">
                        <c:out value="<option class='set-center'>${i}户</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>

            </div>
        </form>

    </div>
</div>
<div class="row first">
    <div class="select  span10 offset6">
        <form class="bs-example bs-example-form form-inline " role="form">
            <label>房间</label>
            <select class="form-control" name="ddd">
                <c:forEach var="i" begin="1" end="10">
                    <c:out value="<option class='set-center'>${i}房间</option>" escapeXml="false"></c:out>
                </c:forEach>
            </select>
            <label>设备</label>
            <select class="form-control" name="ddd">
                <option>防盗器</option>
                <option>空调</option>
                <option>灯</option>
            </select>
            <input type='button' value='确认' class='button right button-primary'>
        </form>
    </div>
</div>
<div class="row second">
    <div class="span2 offset3"><input type='button' value='查看车位情况' class='right button-primary'></div>
    <div class="span8 offset1">
        <div
                class='top'>
            <form class="bs-example bs-example-form " role="form">
                <div class="form-inline">
                    <label for="sel1">楼层</label>
                    <select class="form-control" id="sel1" name="ddd">
                        <option disabled="disabled" selected="selected">楼层</option>
                        <option>2层</option>
                    </select>
                    <input type="text" style="padding: 5px; width: 200px;" class="form-control" placeholder="车位号">
                    <input type='button' value='确认' class='button right button-primary'>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>


</body>
</html>
