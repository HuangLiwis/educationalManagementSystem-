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
    <title>财务处系统</title>
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
<div id="financialInfo">
    <div class="myHeader">
        <h1>武汉工程大学财务处</h1>
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
                    <td class="title td_content">{{financialInfo.money}}</td>
                    <td class="title td_title">电子邮件</td>
                    <td class="title td_content"></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                个人财务明细
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>消费类型</th>
                    <th>消费金额</th>
                    <th>剩余金额</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="value in financialInfo.moneyRecords">
                    <td>{{value.time}}</td>
                    <td>{{value.type}}</td>
                    <td>{{value.fee}}</td>
                    <td>{{value.overage}}</td>
                </tr>
                </tbody>
            </table>
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
        }
    });
</script>
</body>
</html>
