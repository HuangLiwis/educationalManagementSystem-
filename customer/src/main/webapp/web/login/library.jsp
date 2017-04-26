<!--
Created by IntelliJ IDEA.
User: 小智
Date: 2017/4/19 0019
Time: 15:02
To change this template use File | Settings | File Templates.
-->
<!--图书馆-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="../../js/vue.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../css/login_common.css">

    <script>
        $(document).ready(function () {
            $("#mySubmit").click(function () {
                $.post("/login/studentLogin", {
                        studentId: $("#studentId").val(),
                        password: $("#password").val()
                    },
                    function (data, status) {
                        if (data.code == 200)
                            window.location.href = "/web/information/library.jsp";
                        else
                            alert("帐号或密码错误！");
                    });
            });
        });
    </script>
</head>
<body>
<div id="main">
    <div class="formData" style="background: url('../../img/formbgCommon.png')">
        <div class="myText">
            <p id="hanzi">图书馆身份验证</p>
            <p id="yingwen">Library Identity Service</p>
        </div>
        <ul>
            <li><input type="number" id="studentId" name="studentId" placeholder="请输入学号"></li>
            <li><input type="password" id="password" name="password" placeholder="请输入密码"></li>
            <li>
                <input type="reset" class="btn btn-info" id="myReset" value="重 置">
                <input type="submit" class="btn btn-info" id="mySubmit" value="登  录">
            </li>
            <li id="forgetP"><a>忘记密码？</a></li>
        </ul>
    </div>
    <div class="footData">
        <p>Copyright©2010-2015 Wuhan Institute of Technology All Rights Reserved.<br/>
            版权所有©武汉工程大学 | 地址：中国.湖北.武汉市东湖新技术开发区光谷一路206号 | 邮编：430205 | 鄂ICP备05003333号
        </p>
    </div>
</div>

<script>
    new Vue({
        el: '#main',
        data: {
            message: '<h1></h1>'
        }
    })
</script>
</body>
</html>
