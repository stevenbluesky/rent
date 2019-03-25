<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 加载jeesite.properties配置文件 -->
<fmt:setBundle basename="jeesite" var="jsite" />
<!-- 读取配置值key，并赋值给变量var -->
<fmt:message key="tcpHost" var="tcpHost" bundle="${jsite}" />
<fmt:message key="tcpPort" var="tcpPort" bundle="${jsite}" />
<fmt:message key="websockettype" var="websockettype" bundle="${jsite}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" />
    <title>增加门锁用户</title>
    <style type="text/css">
        .active>a{
            border-top: 2px solid #cc0000 !important;
            color: #cc0000 !important;
            font-weight: bold;
        }

        a:link {
            color: #444547;
            text-decoration: none;
        }

        a:visited {
            color: #444547;
            text-decoration: none;
        }

        a:hover {
            color: #c00;
            text-decoration: underline;
        }

        body, div, ul, li {
            padding: 0;
        }

        body {
            font: 12px "宋体";
            text-align: left;
        }

        a:link {
            color: #00F;
            text-decoration: none;
        }

        a:visited {
            color: #00F;
            text-decoration: none;
        }

        a:hover {
            color: #c00;
            text-decoration: underline;
        }

        ul {
            list-style: none;
        }

        .menu0 li {
            display: block;
            float: left;
            padding: 4px 0;
            width: 100px;
            text-align: center;
            cursor: pointer;
            background: orange;
        }

        #menu1 li {
            float: left;
            display: block;
            cursor: pointer;
            width: 72px;
            text-align: center;
            line-height: 21px;
            height: 21px;
        }

        #main1 ul {
            display: none;
        }

    </style>
    
</head>
<body>
<form id="myForm" method="post">
    <input type="hidden" name="masterId" value="${master.id}">
    <input type="hidden" name="guestNo" value="${profile.guestno}"/>
    <input type="hidden" name="userName" class="userName" value="${profile.name}"/>
    <input type="hidden" name="mobilePhone" class="mobilePhone" value="${profile.mobile}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >

        <tr>
            <td class="title">密码:</td>
            <td><input type="text" id="password" name="password" class="password" value=""></td>
        </tr>
        <tr>
            <td class="title">身份证对码:</td>
            <td><input type="text" id="idcard" name="idcard" class="idcard" value=""></td>
        </tr>
        <tr>
            <td class="title">MF卡:</td>
            <td><input type="text" id="mfcard" name="mfcard" class="mfcard" value=""></td>
        </tr>
        <tr>
            <td class="title">指纹:<span id="fingertip"><b>(未采集)</b></span></td>
            <td>
                <select id="fingerprintdevice" name="fingerprintdevice"  class="fingerprintdevice" style="width: 210px" title="选择指纹采集器" onchange="deviceChange()">
                    <c:if test="${locklist==null}"><option value=''>没有可用的指纹采集器</option></c:if>
                    <c:if test="${locklist!=null}"><option value='' >选择指纹采集器</option></c:if>
                    <c:forEach var="b" items="${locklist }">
                        <option value="${b._deviceid}">${b._deviceid}</option>
                    </c:forEach>
                </select>
                <button type="button" id="enterBtn" class="btn">开始指纹采集</button>
                <input type="hidden" name="fingerprint" id="fingerprint" value=""/>
                <input type="hidden" id="invokefield"  value=''/>
            </td>

        </tr>
        <tr style="text-align: center;">
            <td colspan="2">提示：若点击“开始指纹采集”后，界面/设备无反应，请检查设备后重试！</td>
        </tr>
        <tr style="text-align: center;">

            <td colspan="2" style="padding-left:160px;" >
                  <input type="button" style="width: 70px;" value="返回" class="btn" id="backbtn"
                    onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="btn"  type="button" value="确定" onclick="addPassword()"  style="width: 70px;"></td>
        </tr>
    </table>
</form>
</body>

<script type="text/javascript">
    var authorization = "${authorization}";
    var tcpHost = "${tcpHost}";
    var tcpPort = "${tcpPort}";
    var websockettype = "${websockettype}";
    var noresponse = true;

    var ws1, ws2;
    $().ready(function(){
        if (window.WebSocket === undefined) {
            alert("Your browser does not support WebSockets");
            return;
        } else {
            ws1 = initWS1();
            ws2 = initWS2();
        }
        if (window.sessionStorage) {
            var pv = window.sessionStorage.getItem("passwordval");
            var mv = window.sessionStorage.getItem("mfcardval");
            var iv = window.sessionStorage.getItem("idcardval");
            var tip = window.sessionStorage.getItem("tip");
            if (pv != "" && pv != null){
                document.getElementById("password").value = pv;
            }
            if (mv != "" && mv != null){
                document.getElementById("mfcard").value = mv;
            }
            if (iv != "" && iv != null){
                document.getElementById("idcard").value = iv;
            }
            if(tip != "" && tip != "null" && tip !=null){
                $.jBox.tip(tip);
            }
            window.sessionStorage.removeItem("passwordval");
            window.sessionStorage.removeItem("mfcardval");
            window.sessionStorage.removeItem("idcardval");
            window.sessionStorage.removeItem("tip");
        }else
        {
            // 不支持 sessionStorage，用 Dojo 实现相同功能
        }
    });
    function initWS1() {
        var resultData = "";
        var socket = new WebSocket(websockettype+"://"+tcpHost+":"+tcpPort+"/ws/fingerprint")
        socket.onopen = function() {
            socket.send(authorization);
        };
        socket.onmessage = function (e) {
            resultData = eval('(' + JSON.parse(e.data) + ')');
            if(resultData.resultcode==1&&resultData.data.FingerPrintDataIndex==undefined&&resultData.data.FingerPrintDataHex==undefined&&resultData.id==undefined){
                noresponse = false;
                $.jBox.tip("请开始采集指纹!");
            }else if(resultData.resultcode==1&&resultData.data.FingerPrintDataIndex!=undefined&&resultData.data.FingerPrintDataHex!=undefined) {
                var finger = $("#fingerprint").val();
                $("#fingerprint").val(finger+resultData.data.FingerPrintDataHex );
            }else if(resultData.resultcode==1&&resultData.data.FingerPrintDataIndex==undefined&&resultData.data.FingerPrintDataHex==undefined&&resultData.id!=undefined) {
                $("#fingertip").html("<font color='green'><b>(已采集)</b></font>")
                $.jBox.tip("采集指纹成功!");
            }else if(resultData.resultcode===0){
                saveToStorage('指纹采集失败!');
            }else if(resultData.resultcode===3){
                $.jBox.tip("请再次采集指纹!");
            }else{
                noresponse = false;
                if(resultData.Tip_CN!=undefined){
                    saveToStorage(resultData.Tip_CN+"!");
                }else if(resultData.errorinfo_cn!=undefined){
                    saveToStorage(resultData.errorinfo_cn+"!");
                }else{
                    saveToStorage("请求回复："+resultData.resultcode);
                }

            }
        }
        socket.onclose = function () {
        }
        return socket;
    }

    function initWS2() {
        var container = $("#container")
        var socket = new WebSocket(websockettype+"://"+tcpHost+":"+tcpPort+"/ws/receiveReport")
        socket.onopen = function() {
            socket.send(authorization);
        };
        socket.onmessage = function (e) {
            // alert("<p> server push report:" + JSON.parse(e.data) + "</p>");
        }
        socket.onclose = function () {
        }
        return socket;
    }

    function deviceChange(){
        var deviceid = $("#fingerprintdevice").val();
        var requeststr = "{\"method\":\"thing.service.EnterReadFingerPrint\", \"deviceid\":\""+deviceid+"\"}";
        $("#invokefield").val(requeststr);
    }

    $("#enterBtn").click(function (e) {
        e.preventDefault();
        var deviceid = $("#fingerprintdevice").val();
        if(deviceid==""||deviceid==undefined||deviceid==null){
            $.jBox.tip("请先选择指纹录入器!");
            return;
        }
        $('#enterBtn').attr("disabled",true);
        ws1.send($("#invokefield").val());

        var repeat = 6;
        timer = setInterval(function () {
            if (repeat == 1 && noresponse) {
                clearInterval(timer);
                //设备无反应
                saveToStorage("未收到设备回复，请重试！");
            }
            repeat--;
        },1000);

    });

    function addPassword() {
        var reg = /^\d{4,8}$/;
        var passwordvalue = $("#password").val();
        if (passwordvalue!=""&&!reg.test(passwordvalue)) {
            $.jBox.tip("密码需要4-8位数字!");
            return;
        }
        var cardno = $("#idcard").val();
        if (cardno!=""&&cardno.length!=16) {
            $.jBox.tip("ID卡需要16位对码!");
            return;
        }
        var mfcardno = $("#mfcard").val();
        if (mfcardno!=""&&mfcardno.length!=8) {
            $.jBox.tip("MF卡需要8位卡号!");
            return;
        }
        var fingerprint = $("#fingerprint").val();
        if(passwordvalue==""&&cardno==""&&mfcardno==""&&fingerprint==""){
            $.jBox.tip("请至少选择一种!");
            return;
        }
        $.ajax({
            type: "POST",
            dataType: "Text",
            url: 'addPassword.do',
            data: $('#myForm').serialize(),
            success: function (result) {
                if(result=="0") {
                    alert("保存成功，待下发！")
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else if(result=="-1"){
                    $>jBox.tip("信息填写不正确！");
                    $(".addBtn").removeAttr("disabled");
                }else if (result=="-2") {
                    $>jBox.tip("房间尚未绑定门锁，请先将该房间与门锁绑定！");
                    $(".addBtn").removeAttr("disabled");
                }else if(result=="-4"){
                    $>jBox.tip("密码已存在！");
                    $(".addBtn").removeAttr("disabled");
                }else if(result=="-5"){
                    $>jBox.tip("MF卡已存在！");
                    $(".addBtn").removeAttr("disabled");
                }else if(result=="-6") {
                    $ > jBox.tip("身份证已存在！");
                    $(".addBtn").removeAttr("disabled");
                }else if(result=="-7") {
                    $ > jBox.tip("指纹已存在！");
                    $(".addBtn").removeAttr("disabled");
                }else{
                   var tip = "";
                    if(((result)%(Math.pow(2, 1)))/(Math.pow(2, 1-1)) >= 1.0){
                        tip +=" 密码";
                    }
                    if(((result)%(Math.pow(2, 2)))/(Math.pow(2, 2-1)) >= 1.0){
                        tip +=" 身份证";
                    }
                    if(((result)%(Math.pow(2, 3)))/(Math.pow(2, 3-1)) >= 1.0){
                        tip +=" MF卡";
                    }
                    if(((result)%(Math.pow(2, 4)))/(Math.pow(2, 4-1)) >= 1.0){
                        tip +=" 指纹";
                    }
                    alert(tip+"设置失败，请重试！")
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
            },
            error: function(data) {
                $(".addBtn").removeAttr("disabled");
                $.jBox.tip("信息填写不正确！");
            }
        });
    }
    function saveToStorage(tip) {
        if (window.sessionStorage) {
            var passwordval = document.getElementById("password").value;
            var mfcardval = document.getElementById("mfcard").value;
            var idcardval = document.getElementById("idcard").value;
            window.sessionStorage.setItem("passwordval", passwordval);
            window.sessionStorage.setItem("mfcardval", mfcardval);
            window.sessionStorage.setItem("idcardval", idcardval);
            window.sessionStorage.setItem("tip", tip);
            window.location.reload();
        } else {
            // 不支持 sessionStorage，用 Dojo 实现相同功能
        }
    }
</script>
</html>
