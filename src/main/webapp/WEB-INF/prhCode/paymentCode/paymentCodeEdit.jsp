<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>修改付款代码</title>
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
</head>

<body>
	<form action="paymentCodeEdit.do" method="post">
		<input type="hidden" name="id" value="${paymentCode.id}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">修改付款代码</td>

			</tr>
			<tr>
				<td class="title">付款方式:</td>
				<td><input type="text" name="name" value="${paymentCode.name}"></td>
			</tr>
			<tr>
				<td class="title">组别:</td>
				<td><select name="group">
						<option value="现金" <c:if test="${paymentCode.group=='现金'}">selected="selected"</c:if>  >现金</option>
						<option value="支票" <c:if test="${paymentCode.group=='支票'}">selected="selected"</c:if>>支票</option>
						<option value="银行卡" <c:if test="${paymentCode.group=='银行卡'}">selected="selected"</c:if>>银行卡</option>
						<option value="其它" <c:if test="${paymentCode.group=='其他'}">selected="selected"</c:if>>其它</option>
				</select>
				</td>
			</tr>

			<tr>
				<td class="title">银行费用:</td>
				<td>
				<select name="isPay">
					    <option value="不收" <c:if test="${paymentCode.isPay=='不收'}">selected="selected"</c:if> >不收</option>
				 	    <option value="按比例"  <c:if test="${paymentCode.isPay=='按比例'}">selected="selected"</c:if>>按比例</option>
				 	    <option value="定额"  <c:if test="${paymentCode.isPay=='定额'}">selected="selected"</c:if>>定额</option>
				</select>
				</td>
			</tr>
			
			<tr>
			<td class="title">费用设置:</td>
			<td> 
				<input type="text" name="pay" value="${paymentCode.pay}">
			</td></tr>
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input type="submit" class="btn" value="修改" 
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   <input
					type="button" style="width: 70px;" class="btn" value="返回"
					onclick="location.href='findAllPaymentCodePaged.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
