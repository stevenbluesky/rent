<%@page import="com.rent.common.utils.MyConvertUtil"%>
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
	min-height: 84px;a
	min-width: 475px;
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

	<div class="row-fluid">
		
	
	</div>
	<sys:message content="${message}" />
		<h3 style="margin: 10px 10px 10px;">单位租户明细</h3>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			
			<tr>
				
				<th>编号</th>
				<th>租户</th>
				<th>单位</th>
				<th>物业</th>
				
				<th>楼层</th>
				<th>房号</th>
				<th>房型</th>
				<th>面积</th>
				<th>房间</th>
				<th>月租金</th>
				<th>房源状态</th>
				<th>备注</th>

				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(masters)==0 }">
				<tr>
					<td colspan="12"><h2 style="text-align: center;">暂无数据！</h2></td>
				</tr>
			</c:if>
			<c:if test="${masters!=null}">
			<c:forEach var="b" items="${masters}">
				<tr style="text-align: center;">
					
					<td>${b.id}</td>

					<td>${b.profile.name}</td>
					<td>${b.profile.company}</td>
					<td>${b.prHouse.estate.name}</td>
					<td>${b.prHouse.buildingFloor.name}</td>
					<td>${b.prHouse.roomNo }</td>
					<td>${b.prHouse.roomType.name}</td>
					<td>${b.prHouse.area}</td>
					<td>${b.prHouse.roomNum}</td>
					<td>${b.prHouse.monthPrice}</td>
					<c:set var="sta" scope="request" value="${b.sta}"></c:set>
					<td><%=MyConvertUtil.getMasterState((request.getAttribute("sta").toString())) %> </td>	
					<td>${b.refer1 }</td>

				</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<input type="button" class="btn" onclick="location.href='prUtilFile?currpage=${currpage}'" value="返  回">
	
</body>
</html>
