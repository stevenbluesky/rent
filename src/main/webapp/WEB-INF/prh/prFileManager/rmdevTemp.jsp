
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>单位档案管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.jbox-title-panel {
	background: #dc6c6c !important;
	text-align: center;
}

.area-content {
	min-height: 84px;
	min-width: 475px;
}
</style>
<script type="text/javascript">
	$("#state").submit(function() {
		alert("Submitted");
	});
</script>

</head>
<body>






	<div class="row-fluid">
		<div class="span4" style="width:100%">
			<ul class="nav nav-tabs">
				<c:forEach items="${roomtypes}" var="e">
					<li <c:if test="${e.id==roid}">class="active"</c:if>><a
						href="${ctx}/../prhRmdevMoBan?roid=${e.id}&houseid=${houseid}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>
		</div>
		<div class="row-fluid repaire-search" style="margin-bottom: 10px;">
			<form id="form2" method="post" action="${ctx}/../prhAddRmdevList">
			<input type="hidden" value="${roid }" name="roid"/>
			<input type="hidden" value="${houseid }" name="houseid"/>
				<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>类别</th>
				<th>项目</th>
				
				<th>价格</th>
				
				<th>数量</th>	
				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="b" items="${page.list}">
				<tr style="text-align: center;">					
						<td>${b.id}</td>
						<td>${b.cla}</td>
						<c:forEach var="n" items="${equipment}">
						<c:if test="${b.eqid==n.id}">
							<td>${n.name}</td>
							<td>${n.price}</td>
						</c:if>
						</c:forEach>
					<td>${b.num}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
				<div class="span2 search-input">
					<input id="search" class="btn" type="submit" value="添加">
				</div>

				
			</form>
		</div>
	</div>
	<sys:message content="${message}" />

	<div class="pagination">${numpage}</div>
	<div></div>

</body>
</html>
