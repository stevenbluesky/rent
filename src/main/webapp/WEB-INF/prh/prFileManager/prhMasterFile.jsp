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

.active>a {
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

.area-content {
	min-height: 84px;
	min-width: 475px;
}

.fileNo {
	width: 130px;
	height: 30px !important;
	color: red !important;
}

.fileNoContainer {
	width: 140px;
	padding: 5px !important;
}
</style>
	
	<c:set var="roleUpdateNo" value="0" ></c:set>
	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">		 
					<c:set var="roleUpdateNo" value="1" ></c:set>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==106}">
					<c:set var="roleUpdateNo" value="1" ></c:set>
	</c:if></c:forEach></c:if></c:forEach>

<c:if test="${roleUpdateNo==1}">
<script type="text/javascript">
	$(function() {
		$(".fileNoContainer")
				.click(
						function() {
							if ($(this).find(".txt").val() == null) {
								var val = $(this).find(".fileNo").val();
								var html = "<input type='text' value='"+val+"' class='fileNo txt' name='fileNos'/>";
								$(this).html(html);
							}
						});

	});
</script>
</c:if>

<script type="text/javascript">
	$(function(){
	$("#updateFileNo").submit(function(){
		if(confirm("确认更新档案编号吗？")){
			return true;
		}else{
			return false;
		}
		
	});
	});
</script>
</head>
<body>

	<div class="row-fluid">
		<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs">
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../prMasterFile?estateId=${e.id}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>

		</div>

	</div>

	<form method="post"
		action="${ctx}/../prMasterFile?estateId=${estateId}">

		<div class="search-input">
			&nbsp;&nbsp;&nbsp;租户姓名: <input name="name" value="${condition.name }"
				type="text" id="master" style="width: 100px">
			&nbsp;&nbsp;&nbsp; <input id="search" class="btn" type="submit"
				value="条件搜索"> </input>
		</div>


	</form>

	<sys:message content="${message}" />
	<form id="updateFileNo" action="updateFileNo.do?estateId=${estateId}" method="post">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>档案编号</th>
					<th>楼号</th>
					<th>单元</th>
					<th>房间</th>
					<th>姓名</th>
					<th>性别</th>

					<th>证件号码</th>

					<th>电话号码</th>
					<th>单位</th>
					<th>合同开始时间</th>
					<th>合同结束时间</th>
					<th>备注</th>

					<%--<th>角色</th> --%>
				</tr>
			</thead>


			<tbody>
				<c:forEach var="b" items="${page.list}">
					<tr style="text-align: center;">
						<input id="utilfileno" type="hidden" value="${b.profile.guestno}"/>
						<input  type="hidden" value="${b.id}" name="masterIds"/>
						
						
						<td class="fileNoContainer"><input type="hidden"
							value='${b.fileNo}' class='fileNo' name='fileNos'>
							${b.fileNo}</td>


						<td>${b.prHouse.buildingNo.buildingId}号楼</td>
						<td>${b.prHouse.buildingNo.unitName}</td>
						<td>${b.prHouse.roomNo}</td>

						<td>${b.profile.name}</td>
						<c:if test="${b.profile.sex eq '1'}">
							<td>男</td>
						</c:if>
						<c:if test="${b.profile.sex eq '0'}">
							<td>女</td>
						</c:if>
						<td>${b.profile.idno}</td>


						<td>${b.profile.mobile}</td>
						<td>${b.profile.company}</td>
						<td><fmt:formatDate value="${b.bdate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${b.edate}" pattern="yyyy-MM-dd" /></td>
						<td>${b.profile.remark}</td>
					</tr>
				</c:forEach>

			</tbody> 
		</table>
		
	<div class="pagination">${numpage}</div>
		<c:if test="${roleUpdateNo==1}">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" class="btn" value="批量更新档案编号" onclick="update()">
		</c:if>
		
		<input type="button" class="btn" value="租户导出" onclick="location.href='masterReport.do?estateId=${estateId}'">
		
	</form> 
</body>
</html>