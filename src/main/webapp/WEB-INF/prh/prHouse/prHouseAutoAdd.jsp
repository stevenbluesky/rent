<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>新增房源信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<c:if test="${error!=null}">
	<script>$.jBox.tip("表格信息有误！请检查物业、楼层和房间类型是否存在！");</script>
</c:if>
<c:if test="${autoSuccess!=null}">
	<script>$.jBox.tip("导入成功！");</script>
</c:if>

<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 490px;
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
	
}
 
</style>
<script type="text/javascript">
	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
	});
</script>
</head>
<body>
	<form  action="prHouseAutoAdd.do"  enctype="multipart/form-data" method="post">
		<input type="hidden" name="estateId" class="estateId"
			value="<%=request.getParameter("estateId")%>">
		<div style="margin: 20px 10px 20px;">
			<h2 >请按照以下格式导入excel文件</h2>
			<img  src="${ctxStatic}/image/excelPic.png">
		</div> 
		<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	

			<tr style="text-align: center;">
				<td colspan="2"><br/>
					 
          			<input class="btn" type="file" name="file"  style="width: 240px;"  />
          				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          			<input type="button" class="btn" value="模板下载" onclick="location.href='downloadHouse.do'">
          			
          			<br/><br/>
          			&nbsp;&nbsp;&nbsp;&nbsp;<input class="btn" type="submit" value="导入">
				 </td>
			</tr>
			
			<tr>
				<td colspan="2">
					<p style="padding-left: 50px;line-height: 30px;"><span style="color: red;">注:</span>
					    1.请确保物业 ，楼号，楼层，房型在程序中已经存在。<br/>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.请确保您有权限导入该小区的房源。<br/>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.请确保房号开头和楼层相对应。<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.房间状态可选：4（锁定），6（可租），7（空置维修）。<br/>
					
					</p>
				
				</td>
				
			</tr>
		</table>
		
		
	</form>
</body>
</html>
