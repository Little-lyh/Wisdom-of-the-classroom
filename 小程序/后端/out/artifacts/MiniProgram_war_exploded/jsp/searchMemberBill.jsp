<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2019/11/30
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">


    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script>
        function createXMLHttpRequest() {           //万能模板
            var XMLHttpReq;
            if (window.XMLHttpRequest) {            // 是Mozilla浏览器
                XMLHttpReq = new XMLHttpRequest();
            } else if (window.ActiveXObject()) { // IE浏览器
                try {
                    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP"); //因为ie也会出现不兼容所以抛异常
                } catch (e) {
                    XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");  //微软的，记住就好
                }
            }
            return XMLHttpReq;
        }
        $(function () {
            $('#search_course').click(function () {
                var cterm =  $('#term option:selected') .val();
                var tno = document.getElementById("tno").innerText;
                //var tbody = document.getElementById("#scorebody");
                $('#course_body').html("");
                $.ajax({
                    type: "post",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    url: "../GetTeacherCourseServlet.do?tno="+tno+"&&cterm="+cterm,
                    success: function (data) {
                        var str = "";
                        var sum=0;
                        for (var i=0;i<data.length;i++) {
                            str += "<div class=\"col-md-4 col-sm-6\">\n" +
                                "                            <div class=\"project-item\">\n" +
                                "                                <img src=\"../images/"+data[i].courseno+".jpg\" alt=\"\">\n" +
                                "                                <div class=\"project-hover\">\n" +
                                "                                    <div class=\"inside\">\n" +
                                "                                        <h5>"+data[i].coursename+"</h5>\n" +
                                "                                        <h5>"+data[i].classname+"</h5>\n" +
                                "                                        <p><a href=\"../GetClassReportsServlet.do?tno="+tno+"&&courseno="+data[i].courseno+"&&classno="+data[i].classno+"+&&classname="+data[i].classname+"\">查看详情</a></p>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>"
                        }
                        if(data.length==0){
                            $('#course_body').html("该学期无课程");
                        }else {
                            $('#course_body').html(str);
                        }
                    },
                    error: function () {
                        alert("查询失败")
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="main-content">
    <div class="fluid-container">
        <div class="content-wrapper">
            <div class="page-section" id="about1">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label></label>
                            <button type="submit" class="btn btn-info btn-fill btn-wd" id="search_course">查询</button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4 class="widget-title" style="font-size: 20px;font-family: 等线">我的信息</h4>
                        <p></p>
                    </div>
                </div>
                <table class="table table-striped" style="width: 50%">
                    <tbody>
                    <tr>
                        <td>教师编号</td>
                        <td id="tno">${teacher.tno}</td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td>${teacher.tname}</td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>${teacher.tpassword}</td>
                    </tr>
                    <tr>
                        <td>年龄</td>
                        <td>${teacher.tage}</td>
                    </tr>
                    <tr>
                        <td>职称</td>
                        <td>${teacher.ttitle}</td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td>${teacher.tphone}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- ABOUT -->
            <div class="page-section" id="about">
                <div class="row">
                    <div class="col-md-12">
                        <h4 class="widget-title" style="font-size: 20px;font-family: 等线">任课查询</h4>
                        <p></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>学期</label>
                            <select class="form-last-name form-control"  name="tno1" id="term">
                                <option value ="全部">全部</option>
                                <option value ="大一上">大一上</option>
                                <option value ="大一下">大一下</option>
                                <option value ="大二上">大二上</option>
                                <option value ="大二下">大二下</option>
                                <option value ="大三上">大三上</option>
                                <option value ="大三下">大三下</option>
                                <option value ="大四上">大四上</option>
                                <option value ="大四下">大四下</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row projects-holder" id="course_body">

                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4 class="widget-title" id="tips"></h4>
                        <p></p>
                    </div>
                </div>
                <!-- PROJECTS -->
                <hr>
            </div>
            <hr>
        </div>
    </div>
</div>
<script src="../assets/js/jquery.min.js"></script>
<script src="../HomePage/js/plugins.js"></script>
<script src="../HomePage/js/main.js"></script>
</body>
</html>
