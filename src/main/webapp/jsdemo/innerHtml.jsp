<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>我的第一段 javaScript</h1>

<p id="demo">

    javaScript能改变HTML元素的内容
</p>
<script>
    function  myFunction() {
       var x=document.getElementById("demo");
        x.innerHTML="Hello JavaScript!";
    }
</script>
<button type="button" onclick="myFunction()">点击这里</button>
</body>
</html>
