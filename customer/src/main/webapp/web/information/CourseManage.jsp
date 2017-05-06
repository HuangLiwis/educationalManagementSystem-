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
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../css/bootstrap-datetimepicker.min.css"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/vue-resource.js"></script>
    <script type="text/javascript" src="../../js/vue.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-datetimepicker.zh-CN.js"></script>
    <title>选课管理</title>
    <style>
        body {
            padding: 0px 20px;
        }

        .myHeader {
            text-align: center;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div id="financialInfo">
    <div class="myHeader">
        <h1>选课管理</h1>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                课程输入
            </h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <tr>
                    <td>
                        <label>开始时间：</label>
                        <input size="16" type="text" id="datetimeStart" readonly class="form_datetime form-control">
                    </td>
                    <td>
                        <label>结束时间：</label>
                        <input size="16" type="text" id="datetimeEnd" readonly class="form_datetime form-control">
                    </td>
                    <td>

                    </td>
                </tr>
            </table>
            <table class="table table-striped">
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
            <div style="float: right;margin-right: 20px">
                <button class="btn btn-default" data-toggle="modal" data-target="#myModal">增加</button>
                &nbsp;&nbsp;&nbsp;
                <button class="btn btn-default" v-on:click="addCourseAll">提交</button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    课程信息
                                </h4>
                            </div>
                            <div class="modal-body">
                                <p>课程名名称：</p><input required="required" class="form-control" id="courseName">
                                <p>课程代码：</p><input required="required" class="form-control" id="courseId">
                                <p> 教师姓名：</p><input required="required" class="form-control" id="courseTeacherName">
                                <p>上课时间：</p><input required="required" class="form-control" id="courseTime">
                                <p>上课地点：</p><input required="required" class="form-control" id="coursePlace">
                                <p>学分：</p><input required="required" class="form-control" id="courseScore">
                                <p>容量：</p><input required="required" class="form-control" id="courseTotal"
                                                 type="number">
                                <p>开课学院：</p><input required="required" class="form-control" id="courseAcademy">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button v-on:click="addCourse" type="button" class="btn btn-primary">
                                    增加
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    Vue.use(VueResource);
    var vue = new Vue({
        el: '#financialInfo',
        data: {
            studentCourse: new Array(),
            courseView: new Array()
        },
        created: function () {
            var url = "/beansOffice/listManageCourse";
            this.$http.get(url).then(function (data) {
                console.info(data.body);
                this.studentCourse = data.body.data;
            }, function (response) {
                console.info(response);
            })
        },
        methods: {
            addCourse: function () {
                $('#myModal').modal('hide');
                this.courseView.push(new Object({
                    name: $("#courseName").val(),
                    id: $("#courseId").val(),
                    teacherName: $("#courseTeacherName").val(),
                    time: $("#courseTime").val(),
                    place: $("#coursePlace").val(),
                    score: $("#courseScore").val(),
                    total: $("#courseTotal").val(),
                    academy: $("#courseAcademy").val()
                }));
                this.studentCourse.push(new Object({
                    name: $("#courseName").val(),
                    id: $("#courseId").val(),
                    teacherName: $("#courseTeacherName").val(),
                    time: $("#courseTime").val(),
                    place: $("#coursePlace").val(),
                    score: $("#courseScore").val(),
                    total: $("#courseTotal").val(),
                    academy: $("#courseAcademy").val()
                }));
            },
            addCourseAll: function () {
                //取出时间戳
                var timeStart = $('#datetimeStart').val();
                var timeEnd = $("#datetimeEnd").val();
                var timeStartStamp = Date.parse(timeStart).toString();
                var timeEndStamp = Date.parse(timeEnd).toString();
                console.info(timeStartStamp + "----" + timeEndStamp);
                console.info(JSON.stringify(this.courseView));
                this.$http.post("/beansOffice/addCourseData", this.courseView).then(function (data) {
                        if (data.status == 200 && data.body.code == 200) {
                            this.$http.get("/beansOffice/addCourseTime?startTime=" + timeStartStamp + "&&endTime=" + timeEndStamp)
                                .then(function (data) {
                                    if (data.status == 200 && data.body.code == 200) {
                                        window.location.href = "/web/information/CourseManage.jsp";
                                        alert("提交成功！");
                                    }
                                });
                        }
                    }, function (response) {
                        console.info(response);
                    }
                );
            }
        }
    });
</script>
<script>
    //再次显示model时清除数据
    $(function () {
        $('#myModal').on('show.bs.modal',
            function () {
                $("#myModal input").val("");
            })
    });
    //初始化时间插件
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
</script>
</body>
</html>
