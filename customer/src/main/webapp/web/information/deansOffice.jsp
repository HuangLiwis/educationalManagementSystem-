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
                            <td class="title td_content">男</td>
                        </tr>
                        <tr>
                            <td class="title td_title">手机号码</td>
                            <td class="title td_content">${student.phoneNumber}</td>
                            <td>专业班级</td>
                            <td></td>
                        <tr>
                            <td class="title td_title">证件类型</td>
                            <td class="title td_content">身份证</td>
                            <td class="title td_title">证件号码</td>
                            <td class="title td_content">${student.peopleId}</td>
                        </tr>
                        <tr>
                            <td class="title td_title">账户余额</td>
                            <td class="title td_content">{{financialInfo.money}}
                                <button style="float: right" class="btn btn-default"
                                        data-toggle="modal" data-target="#myModal">
                                    充值
                                </button>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">
                                                    &times;
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    财务充值
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <input required="required" type="number" class="form-control"
                                                       name="netMoney"
                                                       placeholder="请输入充值金额" id="moneyValue">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                                </button>
                                                <button v-on:click="addMoney" type="button" class="btn btn-primary"
                                                        id="moneyAdd">
                                                    提交
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="title td_title">电子邮件</td>
                            <td class="title td_content"></td>
                        </tr>
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
                        <tr v-for="value in financialInfo.moneyRecords">
                            <td>{{value.time}}</td>
                            <td>{{value.type}}</td>
                            <td>{{value.fee}}</td>
                            <td>{{value.overage}}</td>
                            <td>{{value.wqwe}}</td>
                            <td>{{value.qwewqe}}</td>
                            <td>{{value.asdsad}}</td>
                            <td>{{value.dfsf}}</td>
                            <td>{{value.fgd}}</td>
                            <td>{{value.fgd}}</td>
                            <td>
                                <button class="btn btn-default btn-sm">选课</button>
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
            financialInfo: ""
        },
        created: function () {
            var url = "/financial/info";
            this.$http.get(url).then(function (data) {
                console.info(data.body);
                this.financialInfo = data.body;
            }, function (response) {
                console.info(response);
            })
        },
        methods: {
            addMoney: function () {
                this.$http.post("/financial/updateFinancial", {
                    id:${student.id},
                    money: $("#moneyValue").val()
                }).then(function (data) {
                        if (data.status == 200 && data.body.code == 200) {
                            window.location.href = "/web/information/financial.jsp";
                            alert("充值成功！");
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
