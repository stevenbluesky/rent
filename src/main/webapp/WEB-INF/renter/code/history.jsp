<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" />
<title>房间特征管理</title>
<style type="text/css">
<!--
body,div,ul,li {
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: left;
}

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


#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}
-->
</style>
<%--<link rel="stylesheet" href="css/page.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/page.js"></script>--%>

<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}

</script>


</head>
<body>

	<!--第一种形式-->
	<div id="tabs0">
		<form id="myForm"  method="post">
		<div class="main" id="main0">
			
				<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
				<tr >	
						
						<th>序号</th>
						<th>身份证对码</th>
						<th>操作人</th>
						<th>挂失时间</th>
				</tr>
				<c:if test="${fn:length(historys)==0}">
				<tr>
					<td colspan="4">
						<div style="height: 70px;">
							<h2 style="text-align: center;margin-top: 26px;">尚无记录！</h2>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${fn:length(historys)!=0}">
				
					<c:forEach var="history" items="${historys}" varStatus="status">
						<tr>
						<td>${status.count}</td>
						<td>${history.idenPwd }</td>
						<td>${history.userName }</td>
						<td><fmt:formatDate value="${history.createTime }" pattern="yyyy-MM-dd" /></td>
					</tr>
					</c:forEach>
				</c:if>
				
					
				</table>
			<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>
				
			
		</div>
		</form>
	</div>
	<br />
	<br />

</body>
</html>