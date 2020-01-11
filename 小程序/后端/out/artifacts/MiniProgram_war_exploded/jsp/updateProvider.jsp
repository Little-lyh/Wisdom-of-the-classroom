<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/27
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加老师</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">
    <style>
        body{
            background-image: url("${pageContext.request.contextPath}/assets/img/bg11.jpg");
        }
    </style>
</head>
<body>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>添加新老师</strong></h1>
                    <div class="description">
                        <p>
                            添加新的老师
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">

                    <form role="form" action="${pageContext.request.contextPath }/ManagePersonAction_updateProvider" method="post" class="registration-form" >

                        <fieldset>
                            <div class="form-top">
                                <div class="form-top-left">
                                    <p style="font-size: 20px"><strong>请继续填写填写要修改的会员相关信息:</strong></p>
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-user"></i>
                                </div>
                            </div>
                            <div class="form-bottom">
                                <div class="form-group">
                                    <p>会员号:</p>
                                    <input type="text" name="number" placeholder="${requestScope.provider.number}" class="form-last-name form-control" readonly  unselectable="on" value="${requestScope.provider.number}">
                                </div>
                                <div class="form-group">
                                    <p>姓名:</p>
                                    <input type="text" name="name" placeholder="${requestScope.provider.name}" class="form-last-name form-control" value="${requestScope.provider.name}">
                                </div>
                                <div class="form-group">
                                    <p>地址:</p>
                                    <input type="text" name="address" placeholder="${requestScope.provider.address}" class="form-last-name form-control" value="${requestScope.provider.address}">
                                </div>
                                <div class="form-group">
                                    <p>城市:</p>
                                    <input type="text" name="city" placeholder="${requestScope.provider.city}" class="form-last-name form-control" value="${requestScope.provider.city}">
                                </div>
                                <div class="form-group">
                                    <p>国家：</p>
                                    <input type="text" name="country" placeholder="${requestScope.provider.country}" class="form-last-name form-control" value="${requestScope.provider.country}">
                                </div>
                                <div class="form-group">
                                    <span>ZIP码:</span>
                                    <input type="text" name="ZIP" placeholder="${requestScope.provider.ZIP}" class="form-last-name form-control"  value="${requestScope.provider.ZIP}">
                                </div>
                                <div class="form-group">
                                    <span>银行账户:</span>
                                    <input type="text" name="account" placeholder="${requestScope.provider.account}" class="form-last-name form-control" id="form-last-name" value="${requestScope.provider.account}">
                                </div>
                                <input type="submit" value="修改">
                            </div>
                        </fieldset>

                    </form>

                </div>
            </div>
        </div>
    </div>
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/retina-1.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
    <!--[if lt IE 10]>
    <script src="../assets/js/placeholder.js"></script>
    <![endif]-->

</body>
</html>

