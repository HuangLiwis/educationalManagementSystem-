<%--
  Created by IntelliJ IDEA.
  User: 小智
  Date: 2017/4/25 0025
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校园网系统</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/vue-resource.js"></script>
    <script type="text/javascript" src="../../js/vue.min.js"></script>
    <style>
        body {
            padding: 0px 20px;
        }

        .myHeader {
            text-align: center;
            margin-bottom: 10px;
        }

        table td {
            width: 20%;
        }
    </style>

</head>
<body>
<div class="myHeader">
    <h1>武汉工程大学校园网</h1>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            个人基本信息
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-bordered">
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
            <tr>
                <td class="title td_title">用户住址</td>
                <td class="title td_content"></td>
                <td class="title td_title">电子邮件</td>
                <td class="title td_content"></td>
            </tr>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            个人网络
        </h3>
    </div>
    <div class="panel-body" id="netWorkInfo">
        <table class="table table-bordered">
            <tr>
                <td class="title td_title">用户名</td>
                <td class="title td_content">${student.id}
                </td>
                <td class="title td_title">
                    当前状态
                </td>
                <td class="title td_content">
                    用户状态： <span style="color:blue">正常</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    账户状态： <span style="color:blue">正常</span>
                </td>
            </tr>
            <tr>
                <td class="title td_title">账户余额</td>
                <td class="title td_content">
                    {{campusNetwork.overage}}元
                    <button style="float: right" class="btn btn-default" data-toggle="modal" data-target="#myModal">充值
                    </button>
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
                                        充值网费
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <input required="required" type="number" class="form-control" name="netMoney"
                                           placeholder="请输入充值金额" id="moneyValue">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button v-on:click="addMoney" type="button" class="btn btn-primary" id="moneyAdd">
                                        提交
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                <td class="title td_title">本周期截至日期</td>
                <td class="title td_content">2017-05-10</td>
                </td>
            </tr>
            <tr>
                <td class="title td_title">当前计费组</td>
                <td class="title td_content">学生_教学区_5元</td>
                <td class="title td_title">下个计费组</td>
                <td class="title td_content">学生_教学区_5元</td>
            </tr>
            <tr>
                <td class="title td_title">已用时长</td>
                <td class="title td_content">(IPV4) 51小时22分9秒; (IPV6) 0秒</td>
                <td class="title td_title">已用流量</td>
                <td class="title td_content">(IPV4) 3.37G; (IPV6) 0B</td>
            </tr>
            <tr>
                <td class="title td_title">可用时长</td>
                <td class="title td_content">不限制</td>
                <td class="title td_title">可用流量</td>
                <td class="title td_content"></td>
            </tr>

        </table>
    </div>
</div>
<script>
    Vue.use(VueResource);
    new Vue({
        el: '#netWorkInfo',
        data: {
            campusNetwork: ""
        },
        created: function () {
            var url = "/campusNetwork/networkInfo";
            this.$http.get(url).then(function (data) {
                this.campusNetwork = data.body;
            }, function (response) {
                console.info(response);
                if(response.status==400){
                    window.location.href = "/web/login/campusNetwork.jsp";
                }
            })
        },
        methods: {
            addMoney: function () {
                this.$http.post("/financial/addMoneyRecord", {
                    id:${student.id},
                    fee: $("#moneyValue").val(),
                    type: "校园网充值"
                }).then(function (data) {
                        var moneyData = data.body;
                        if (data.status == 200 && moneyData.code == 200) {
                            this.$http.post("/campusNetwork/update", {
                                id:${student.id},
                                overage: moneyData.data.fee + this.campusNetwork.overage,
                            }).then(function (data) {
                                    if (data.status == 200 && moneyData.code == 200) {
                                        window.location.href = "/web/information/campusNetwork.jsp";
                                        alert("充值成功");
                                    }
                                }, function (response) {
                                    console.info(response);
                                }
                            );
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
