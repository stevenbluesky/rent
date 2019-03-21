<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" />
    <title>删除</title>
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
        <!--
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

        .main {
            clear: both;
            padding: 8px;
            text-align: left;
        }
        /*第一种形式*/
        #tabs0 {
            height: 800px;
            width: 100%;
            border: 1px solid #red;
            background-color: #f2f6fb;
        }

        .menu0 {
            width: 400px;
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

        .menu0 li.hover {
            background: #ff0;
        }

        #main0 .menu0 {
            display: none;
        }

        #main0 .menu0.block {
            display: block;
        }
        /*第二种形式*/
        #tabs {
            text-align: left;
            width: 400px;
        }

        .menu1box {
            position: relative;
            overflow: hidden;
            height: 22px;
            width: 400px;
            text-align: left;
        }

        #menu1 {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
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

        #menu1 li.hover {
            background: #fff;
            border-left: 1px solid #333;
            border-top: 1px solid #333;
            border-right: 1px solid #333;
        }

        .main1box {
            clear: both;
            margin-top: -1px;
            border: 1px solid #333;
            height: 181px;
            width: 400px;
        }

        #main1 ul {
            display: none;
        }

        #main1 ul.block {
            display: block;
        }
        -->
    </style>
    
</head>
<body>
<form id="myForm" method="post">
    <input type="hidden" name="doorlockuserid" value="${doorlockuserid}">

    <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >

        <tr>
            <td class="title">删除原因*:</td>
            <td><input type="text" id="reason" name="reason" class="reason" value=""></td>
        </tr>


        <tr style="text-align: center;">

            <td colspan="2" style="padding-left:80px;" >
                  <input type="button" style="width: 70px;" value="返回" class="btn"
                    onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="btn"  type="button" value="确定" onclick="deletePassword()"  style="width: 70px;"></td>
        </tr>
    </table>
</form>
</body>

<script type="text/javascript">
    function deletePassword() {
        if ($("#reason").val()=="") {
            $.jBox.tip("删除原因必填!");
            return;
        }

        $.ajax({
            type: "POST",
            dataType: "Text",
            url: 'deletePassword.do',
            data: $('#myForm').serialize(),
            success: function (result) {
                if(result=="-1"){
                    $>jBox.tip("删除失败！");
                    $(".addBtn").removeAttr("disabled");
                }else if (result=="1") {
                    alert("操作成功，待删除！")
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }else{
                    alert("操作成功，待删除！")
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
            },
            error: function(data) {
                $(".addBtn").removeAttr("disabled");
                $.jBox.tip("操作失败！");
            }
        });
    }
</script>
</html>
