<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>修改电器设置</title>
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
				$.jBox.tip("押金不能为空！");
				return false;
			}
			if (rentPay.length==0) {
				$.jBox.tip("租金不能为空！");
				return false;
			}
			if (dayOrMonth.length==0) {
				$.jBox.tip("“日/月”不能为空！");
				return false;
			}
		});
	});


</script>
</head>

<body>
	<form class="myForm" action="rentPayWayEdit.do" method="post">
		<input type="hidden" value="${rentPayWay.id }" name="id"> 
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">修改电器设置</td>

			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>名称:</td>
 				<td><input type="text" class="name" name="name" value="${rentPayWay.name }"></td> 
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>押金:</td>
				<td><input type="text" class="depositPay" name="depositPay" value="${rentPayWay.depositPay }"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>租金:</td>
				<td><input type="text" class="rentPay" name="rentPay" value="${rentPayWay.rentPay }"></td>
			</tr>
			
			<tr>
				<td class="title"><span style="color: red;">* </span>模式:</td>
				<td>
				<select name="mode">
						<option value="普通" <c:if test="${rentPayWay.mode=='普通'}">selected="selected"</c:if>>普通</option>
						<option value="月付" <c:if test="${rentPayWay.mode=='月付'}">selected="selected"</c:if>>月付</option>
						<option value="季付" <c:if test="${rentPayWay.mode=='季付'}">selected="selected"</c:if>>季付</option>
						<option value="特殊" <c:if test="${rentPayWay.mode=='特殊'}">selected="selected"</c:if>>特殊</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>日/月:</td>
				<td><input type="text" class="dayOrMonth" name="dayOrMonth" value="${rentPayWay.dayOrMonth }"></td>
			</tr>
			<tr>
				<td class="title">备注:</td>
				<td><input type="text" name="descript1" value="${rentPayWay.descript1 }"></td>
			</tr>
			
			
			
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input type="submit" class="btn" value="修改"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" style="width: 70px;" class="btn" value="返回"
					onclick="location.href='findAllRentPayWayPaged.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
