<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>

<title>修改楼层</title>
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
	<form action="editBuildingFloor.do" method="post">
		<input type="hidden" name="id" value="${buildingFloor.id}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">修改楼层信息</td>
			</tr>

			
			<tr>
				<td class="title">楼层名:</td>
				<td><input type="text" readonly="readonly" name="name"
					value="${buildingFloor.name }"></td>
			</tr>
			<tr>
				<td class="title">英文名:</td>
				<td><input type="text" name="englishName"
					value="${buildingFloor.englishName}"></td>
			</tr>
			<tr>
				<td class="title">备注:</td>
				<td><input type="text" name="remark"
					value="${buildingFloor.remark}"></td>
			</tr>

			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input class="btn" type="submit" value="修改"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" class="btn" style="width: 70px;" value="返回"
					onclick="location.href='findAllBuildingFloorPaged.do'"></td>

			</tr>
		</table>
	</form>
</body>
</html>
