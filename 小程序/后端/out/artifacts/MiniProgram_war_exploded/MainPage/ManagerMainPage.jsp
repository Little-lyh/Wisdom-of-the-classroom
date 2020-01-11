<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/1/5
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/MainPage/assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MainPage/assets/font/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MainPage/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MainPage/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MainPage/assets/css/responsive.css">
</head>
<body>
<!-- Preloader -->
<div id="preloader">
    <div class="preloader">
        <span></span>
        <span></span>
    </div>
</div>

<div class="side-wrapper">
    <!-- Top Menu -->
    <header class="top-nav">
        <nav id="cssmenu" class="container ">
            <div class="logo">
                <h3>积分系统</h3>
            </div>
        </nav>
    </header>
    <!-- End Top Menu -->
    <!-- Home 1 Hero -->
    <section id="home1-hero" class="home1-hero">
        <!-- animet  -->
        <div class="home1-hero-animet">
            <img src="${pageContext.request.contextPath}/MainPage/assets/img/bg/shedo1.png" alt="">
            <img src="${pageContext.request.contextPath}/MainPage/assets/img/bg/shedo2.png" alt="">
            <img src="${pageContext.request.contextPath}/MainPage/assets/img/bg/shedo3.png" alt="">
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="home1-hero-content">
                        <a href="${pageContext.request.contextPath}/jsp/delStudent.jsp" class="btn-1">学生管理</a><br><br>
                        <a href="${pageContext.request.contextPath}/jsp/delTeacher.jsp" class="btn-1">教师管理</a><br><br>
                        <a href="${pageContext.request.contextPath}/jsp/delStudent.jsp" class="btn-1">奖品管理</a><br><br>
                        <a href="${pageContext.request.contextPath}/jsp/delTeacher.jsp" class="btn-1">奖品处理</a><br><br>
                        <a href="${pageContext.request.contextPath}/jsp/delTeacher.jsp" class="btn-1">概要报告</a><br><br>
                        <a href="${pageContext.request.contextPath}/searchBill.jsp" class="btn-1">奖品处理</a><br><br>
                    </div>
                </div>
                <div class="hero1-right">
                    <div class="hero1-right-animet alltuchtopdown">
                        <img src="${pageContext.request.contextPath}/MainPage/assets/img/h1-1.png" alt="">
                    </div>
                    <div class="mobile-img-hero1-right-animet">
                        <img src="${pageContext.request.contextPath}/MainPage/assets/img/h1-2.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Home 1 Hero -->
</div>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/menu.js"></script>
<script src="${pageContext.request.contextPath}/MainPage/assets/js/script.js"></script>
<script>
    $(window).on('load', function() {
        $('#preloader').delay(350).fadeOut('slow');
        $('body').delay(350).css({
            'overflow': 'visible'
        });
    });
</script>
</body>
</html>

