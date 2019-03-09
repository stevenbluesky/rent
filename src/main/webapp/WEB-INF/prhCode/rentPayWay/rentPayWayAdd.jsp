<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>新增支付方式设置</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 390px;
}

.addFloor tr {
	height: 40px;
}

.addFloor  td {
	padding: 0px 8px 0px;
}

.addFloor .title {
	text-align: right;
}

.addFloor input {
	width: 200px;
}
</style>
<script type="text/javascript">
	$(function(){
		$(".myForm").submit(function(){
			var name= $(".name").val();
			var depositPay=$(".depositPay").val();
			var rentPay=$(".rentPay").val();
			var dayOrMonth=$(".dayOrMonth").val();
			if (name.length==0) {
				$.jBox.tip("支付方式不能为空！");
				return false;
			}
			if (depositPay.length==0) {
				$.jBox.tip("押金月数不能为空！");
				return false;
			}
            if (depositPay>12) {
                alert("请确保押金月数填写正确！")
            }
            if(rentPay>12){
                alert("请确保租金月数填写正确！")
			}
			if (rentPay.length==0) {
				$.jBox.tip("租金月数不能为空！");
				return false;
			}
			if (dayOrMonth.length==0) {
				$.jBox.tip("“日”不能为空！");
				return false;
			}
			if(dayOrMonth.match(/\D/)!=null||dayOrMonth>31){
                $.jBox.tip("“日”为每月交房租的日期，请注意格式！");
                return false;
			}
		});
	});


</script>
</head>

<body>
	<form class="myForm" action="rentPayWayAdd.do" method="post">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">新增支付方式设置</td>

			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>名称:</td>
				<td><input type="text" class="name" name="name"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>押金月数:</td>
				<td><input type="text" class="depositPay" name="depositPay"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>租金月数:</td>
				<td><input type="text" class="rentPay" name="rentPay"></td>
			</tr>
			
			<tr>
				<td class="title"><span style="color: red;">* </span>模式:</td>
				<td>
				<select name="mode">
						<option value="普通">普通</option>
						<option value="月付">月付</option>
						<option value="季付">季付</option>
						<option value="特殊">特殊</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>日:</td>
				<td><input type="text" class="dayOrMonth" name="dayOrMonth"></td>
			</tr>
			<tr>
				<td class="title">备注:</td>
				<td><input type="text" name="descript1"></td>
			</tr>
		
			
			
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input type="submit" class="btn" value="新增"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" style="width: 70px;" class="btn" value="返回"
					onclick="location.href='findAllRentPayWayPaged.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
