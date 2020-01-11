<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/27
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改会员</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-57-precomposed.png">
    <script>

        function del(){
            var num = document.getElementById('form-first-name').value;
            if(num==""){
                alert("会员号不能为空！！")
                return false;
            }else{
                document.form1.action="${pageContext.request.contextPath }/ManagePersonAction_delMember";
                document.form1.submit();
            }
        }
        function insert(){
            var num = document.getElementById('form-first-name').value;
            if(num==""){
                alert("会员号不能为空！！")
                return false;
            }else{
                document.form1.action="${pageContext.request.contextPath }/ManagePersonDispatcherAction_addMember";
                document.form1.submit();
            }
        }
        function update(){
            var num = document.getElementById('form-first-name').value;
            if(num==""){
                alert("会员号不能为空！！")
                return false;
            }else{
                document.form1.action="${pageContext.request.contextPath }/ManagePersonDispatcherAction_updateMember";
                document.form1.submit();
            }
        }
    </script>
    <style>
        body{
            background-image: url("../assets/img/bg11.jpg");
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
                    <h1><strong>删除会员</strong></h1>
                    <div class="description">
                        <p>
                            您将删除一位会员
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">

                    <form role="form" name="form1" method="post" class="registration-form">

                        <fieldset>
                            <div class="form-top">
                                <div class="form-top-left">
                                    <p style="font-size: 20px"><strong>请填写要管理的会员号:</strong></p>
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-user"></i>
                                </div>
                            </div>
                            <div class="form-bottom">
                                <div class="form-group">
                                    <label class="sr-only" for="form-first-name">姓名</label>
                                    <input type="text" name="number" placeholder="会员号..." class="form-first-name form-control" id="form-first-name">
                                </div>
                                <div class="form-group">
                                    <input type="button" value="删除" onclick="del();">&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="button" value="插入" onclick="insert();">&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="button" value="更新" onclick="update();">
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath }/assets/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/jquery.backstretch.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/retina-1.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/scripts.js"></script>
    <!--[if lt IE 10]>
    <script src="${pageContext.request.contextPath }/assets/js/placeholder.js"></script>
    <![endif]-->

</body>
</html>
