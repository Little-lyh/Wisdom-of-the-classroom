<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/30
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/bootstrap.min.css">
    <!-- Loding font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <!-- Custom Styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/styles.css">
    <title>账单查询</title>
    <script>
        function searchMember(){
            var num = document.getElementById('number').value;
            if(num==""){
                alert("号码不能为空！！")
                return false;
            }else{
                document.form1.action="${pageContext.request.contextPath }/BillAction_searchMemberBill";
                document.form1.submit();
            }
        }
        function searchProvider(){
            var num = document.getElementById('number').value;
            if(num==""){
                alert("号码不能为空！！")
                return false;
            }else{
                document.form1.action="${pageContext.request.contextPath }/BillAction_searchProviderBill";
                document.form1.submit();
            }
        }
    </script>
</head>
<body>
<!-- Backgrounds -->
<div id="login-bg" class="container-fluid">

    <div class="bg-img"></div>
    <div class="bg-color"></div>
</div>
<!-- End Backgrounds -->
<div class="container" id="login">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="login">
                <h1>请输入号码</h1>
                <!-- Loging form -->
                <form method="post" name="form1">
                    <div class="form-group">
                        <input type="text" name="number" class="form-control" id="number" placeholder="Number">
                    </div>
                    <br>
                    <button  type="button" onclick="searchMember();" class="btn btn-lg btn-block btn-success">查询会员账单</button><br>
                    <button  type="button" onclick="searchProvider();" class="btn btn-lg btn-block btn-success">查询提供者账单</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
