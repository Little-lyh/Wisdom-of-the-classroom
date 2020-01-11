<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/9
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成功</title>
    <style type="text/css">
        p{
            font-family: 微软雅黑;
            font-size: 80px;
            color: #BEBEBE;
        }
        body{
            text-align: center;
            background: url("${pageContext.request.contextPath}/assets/images/back3.jpg") no-repeat;
            height:100%;
            width:100%;
            overflow: hidden;
            background-size:cover;
        }
        div.photo{
            position:relative;
            top:50px;
        }
    </style>
</head>
<body>
<div class="photo">
    <img src="${pageContext.request.contextPath}/assets/images/succeed.png" width="100"/>
</div>
<div>
    <p class="welcome">${requestScope.message}</p>
</div>
</body>
</html>
