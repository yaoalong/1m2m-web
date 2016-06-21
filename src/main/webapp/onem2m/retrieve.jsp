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
    <title>检索状态</title>
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
    <script type="text/javascript" src="../bootstrap/js/bootbox.min.js"></script>
</head>
<body>
<div class="row first">
    <div class="col-md-2 col-md-offset-2"><input type='button' value='查看设备状态' class='right button-primary'></div>
    <div class="select  col-md-offset-8">
        <form class="form-inline">
            <div class="form-group">

                <label>楼栋</label>
                <select id="buildingId">
                    <c:forEach var="i" begin="1" end="${ban}">
                        <c:out value="<option>${i}栋</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>
                <label>楼层</label>
                <select id="floorId">
                    <c:forEach var="i" begin="1" end="${floor}">
                        <c:out value="<option>${i}层</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>
                <label>户</label>
                <select id="apartmentId">
                    <c:forEach var="i" begin="1" end="${apartment}">
                        <c:out value="<option>${i}户</option>" escapeXml="false"></c:out>
                    </c:forEach>
                </select>

            </div>
        </form>

    </div>
</div>
<div class="row first">
    <div class="select  col-md-5 col-md-offset-3">
        <form class="bs-example bs-example-form form-inline " role="form">
            <label>房间</label>
            <select class="form-control" id="roomId">
                <c:forEach var="i" begin="1" end="${rooms}">
                    <c:out value="<option>${i}房间</option>" escapeXml="false"></c:out>
                </c:forEach>
            </select>
            <label>设备</label>
            <select class="form-control" id="machineType">
                <option>防盗器</option>
                <option>空调</option>
                <option>灯</option>
            </select>
            <input type='button' value='确认' class='button right button-primary' id="retrieveMachine">
        </form>
    </div>
</div>
<div class="row second">
    <div class="col-md-2 col-md-offset-2"><input type='button' value='查看车位情况' class='right button-primary'></div>
    <div class="col-md-4 col-md-offset-1">
        <div
                class='top'>
            <form class=" " role="form">
                <div class="form-inline">
                    <label for="parking">车层</label>
                    <select id="parking" name="ddd">
                        <option selected="selected">1层</option>
                        <option>2层</option>
                    </select>
                    <input type="text" style=" width: 200px;" class="form-control" placeholder="车位号" id="parkingNumber">
                    <input type='button' value='确认' class='button right button-primary' id="retrieveParking">
                </div>
                <br>
            </form>
        </div>
    </div>
</div>
</body>
</html>
