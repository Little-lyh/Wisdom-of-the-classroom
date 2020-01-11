<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/30
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查询结果</title>
    <style>
        body{
            width: 100%;
            height: 100%;
            background: url("${pageContext.request.contextPath}/assets/images/11.jpg");
            overflow: hidden;
            background-size:cover;
            margin:0 auto;
            padding:0;
        }
        div.main{
            font-size: 40px;
            font-family: 等线;
            color: aliceblue;
            margin:30px auto;
            text-align: center;
            position:relative;
            top:80px;
        }
        table.main {
            font-family: 汉仪瘦金书简;
            width: 80%;
            font-family: verdana,arial,sans-serif;
            font-size:15px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
            margin: auto;
            filter:alpha(opacity=60)
        }
        table.main th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.main td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            text-align: center;
            background-color: #ffffff;
        }
        p{
            height: 50px;
            font-size: 80px;
            font-family: 汉仪瘦金书简;
            color: aliceblue;
            margin-top: 70px;
        }
    </style>
</head>
<body>
<div class="main">
    <p >账单</p>
    <table class="main" style="width: 50%">
        <tbody>
        <tr>
            <td style="background-color: #dedede;">姓名</td>
            <td id="tno">${provider.name}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">编号</td>
            <td>${provider.number}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">街道地址</td>
            <td>${provider.address}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">城市</td>
            <td>${provider.city}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">国家</td>
            <td>${provider.country}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">ZIP码</td>
            <td>${provider.ZIP}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">一周总费用</td>
            <td>$${cost}</td>
        </tr>
        <tr>
            <td style="background-color: #dedede;">服务总次数</td>
            <td>${count}</td>
        </tr>
        </tbody>
    </table>
    <table class="main">
        <caption style="font-size: 30px;height: 40px"></caption>
        <tr align="center" style="background-color: coral">
            <th>服务日期</th>
            <th>会员编号</th>
            <th>会员姓名</th>
            <th>服务代码</th>
            <th>服务费用</th>
        </tr>
        <c:forEach items="${requestScope.serviceList}" var="service" varStatus="status">
            <tr style="text-align: center">
                <td>${service.date}</td>
                <td>${service.member.number}</td>
                <td>${service.member.name}</td>
                <td>${service.serviceCode.code}</td>
                <td>$${service.serviceCode.cost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>



