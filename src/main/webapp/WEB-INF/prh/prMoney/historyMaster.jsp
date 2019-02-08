<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.jbox-title-panel{
			background:#dc6c6c !important;
			text-align:center;
		}
		.area-content{
			min-height: 84px;
			min-width: 475px;
		}
	</style>
	<script type="text/javascript">
	
	
	</script>
	
</head>
<body>

			
			<form id="Jform" action="balance" method="post">
			<h3 style="margin: 10px 10px 10px;">房间历史租户</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择
				 </th><th>编号</th><th>物业</th><th>楼号</th><th>单元</th><th>房号</th><th>入住人</th><th>合同起始日期</th><th>合同截止日期</th><th>状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:if test="${size==0}">
			<tr>
				<td colspan="12"><h3 style="text-align: center;">暂无历史租户！</h3></td>
			</tr>
		</c:if>
		<c:if test="${size!=0}">
		<c:forEach items="${masters}" var="ma" >
			<tr>		
				<td>
					<input type="radio" name="chk" class="chk" value="${ma.id}"/>
				</td>			
				<td>${ma.id}</td>
					
				<td>${ma.prHouse.estate.name}</td>
				<td>${ma.prHouse.buildingNo.buildingId}号楼</td>
				<td>${ma.prHouse.buildingNo.unitName}</td>				
				<td>${ma.prHouse.roomNo}</td>
				<td>${ma.profile.name}</td>			
				<td><fmt:formatDate value="${ma.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ma.edate}" pattern="yyyy-MM-dd" /></td>	
				
				
				<c:set var="sta" scope="request" value="${ma.sta}"></c:set>
				<td><%=MyConvertUtil.getMasterState((request.getAttribute("sta").toString())) %> </td>	
				<td>${ma.remark }</td>
				
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	<input type="button" class="btn" onclick="location.href='prHouseFileByCondition?currpage=${currpage}'" value="返  回">
	
	
	</form>
</body>
</html>
