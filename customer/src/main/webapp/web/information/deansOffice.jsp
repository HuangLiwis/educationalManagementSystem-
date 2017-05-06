<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 小智
  Date: 2017/4/26 0026
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/vue-resource.js"></script>
    <script type="text/javascript" src="../../js/vue.min.js"></script>
    <title>教务处处系统</title>
    <style>
        body {
            padding: 0px 20px;
        }

        .myHeader {
            text-align: center;
            margin-bottom: 10px;
        }

        .tableGeren td {
            width: 20%;
        }
    </style>
</head>
<body>
<div id="financialInfo">
    <div class="myHeader">
        <h1>武汉工程大学教务处</h1>
    </div>
    <div class="panel panel-default">
        <ul class="nav nav-tabs" style="background: #f5f5f5">
            <li class="active"><a data-toggle="tab" href="#gerenxinxi">个人信息</a></li>
            <li><a data-toggle="tab" href="#xuanxiu">选修课程</a></li>
        </ul>
        <div class="panel-body">
            <div class="tab-content">
                <div class="panel-body tab-pane fade in active" id="gerenxinxi">
                    <table class="table table-bordered tableGeren">
                        <tr>
                            <td class="title td_title">用户真实姓名</td>
                            <td class="title td_content">${student.name}</td>
                            <td class="title td_title">性别</td>
                            <td class="title td_content">${student.sex}</td>
                        </tr>
                        <tr>
                            <td class="title td_title">手机号码</td>
                            <td class="title td_content">${student.phoneNumber}</td>
                            <td>院系</td>
                            <td>${student.academy}${student.grade}</td>
                        <tr>
                            <td class="title td_title">学号</td>
                            <td class="title td_content">${student.id}</td>
                            <td class="title td_title">身份证</td>
                            <td class="title td_content">${student.peopleId}</td>
                        </tr>
                    </table>
                    <table class="table table-bordered">
                        <caption> 已选课列表</caption>
                        <thead>
                        <tr>
                            <th>课程名称</th>
                            <th>课程代码</th>
                            <th>教师姓名</th>
                            <th>上课时间</th>
                            <th>上课地点</th>
                            <th>学分</th>
                            <th>容量</th>
                            <th>开课学院</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="value in studentCourse">
                            <td>{{value.name}}</td>
                            <td>{{value.id}}</td>
                            <td>{{value.teacherName}}</td>
                            <td>{{value.time}}</td>
                            <td>{{value.place}}</td>
                            <td>{{value.score}}</td>
                            <td>{{value.total}}</td>
                            <td>{{value.academy}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="panel-body tab-pane fade" id="xuanxiu">
                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <th>课程名称</th>
                            <th>课程代码</th>
                            <th>教师姓名</th>
                            <th>上课时间</th>
                            <th>上课地点</th>
                            <th>学分</th>
                            <th>容量</th>
                            <th>已选</th>
                            <th>余量</th>
                            <th>开课学院</th>
                            <th>选课</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="value in courseInfo">
                            <td>{{value.name}}</td>
                            <td>{{value.id}}</td>
                            <td>{{value.teacherName}}</td>
                            <td>{{value.time}}</td>
                            <td>{{value.place}}</td>
                            <td>{{value.score}}</td>
                            <td>{{value.total}}</td>
                            <td>{{value.selected}}</td>
                            <td>{{value.remain}}</td>
                            <td>{{value.academy}}</td>
                            <td>
                                <button class="btn btn-default btn-sm" v-on:click="selectCourse(value.id)">选课
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    Vue.use(VueResource);
    new Vue({
        el: '#financialInfo',
        data: {
            courseInfo: "",
            studentCourse: ""
        },
        created: function () {
            //课程列表
            var url = "/beansOffice/courseList";
            this.$http.get(url).then(function (data) {
                this.courseInfo = data.body.data;
            }, function (response) {
                console.info(response);
                if (response.status == 400) {
                    window.location.href = "/web/login/deansOffice.jsp";
                }
            });
            //学生已选课程数据
            this.$http.get("/beansOffice/listStudentCourse").then(function (data) {
                    if (data.status == 200 && data.body.code == 200) {
                        this.studentCourse = data.body.data;
                    }
                }, function (response) {
                    console.info(response);
                }
            );
        },
        methods: {
            selectCourse: function (courseId) {
                this.$http.get("/beansOffice/selectCourse?courseId=" + courseId).then(function (data) {
                        if (data.status == 200) {
                            if (data.body.code == 200) {
                                window.location.href = "/web/information/deansOffice.jsp";
                                alert("选课成功！");
                            } else if (data.body.code == 204) {
                                alert(data.body.describe);
                            } else if (data.body.code == 205) {
                                alert(data.body.describe);
                            } else if (data.body.code == 206) {
                                alert(data.body.describe);
                            } else {
                                alert(data.body.describe);
                            }
                        }
                    }, function (response) {
                        console.info(response);
                    }
                );
            }
        }
    });
</script>
</body>
</html>
