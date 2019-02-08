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
<body >
	
		<h4>已付清</h4>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>物业</th><th>房号</th><th>付款人</th><th>租金开始日期</th><th>租金结算日期</th><th>结算日期</th>
		<th>收费总金额</th><th>发票号</th><th>现金支付金额</th><th>刷卡支付金额</th><th>转账支付金额</th><th>财务状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:if test="${rentalsTsize==0}">
			<tr>
				<td colspan="12"><h4>暂无数据！</h4><td>
				
			</tr>
		</c:if>
		<c:if test="${rentalsTsize!=0}">
			<c:forEach items="${rentalsT}" var="ren">
		
			<tr>
				
				<td>${ren.rentals.inumber}</td>
				<td>${ren.rentals.accnts.prHouse.estate.name}</td>
				<td>${ren.rentals.accnts.prHouse.no}</td>
				<td>${ren.rentals.accnts.profile.name}</td>
				<td><fmt:formatDate value="${ren.rentals.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ren.rentals.edate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ren.rentals.ilogdate}" pattern="yyyy-MM-dd" /></td>				
				<td>${ren.rentals.iamount}</td>		
				<td>${ren.rentals.ino}</td>
				<td><c:if test="${ren.paycode eq '1'}">
					${ren.credit}
					</c:if></td>
				<td><c:if test="${ren.paycode eq '2'}">
					${ren.credit}
					</c:if>	 </td>
				<td><c:if test="${ren.paycode eq '3'}">
					${ren.credit}
					</c:if></td>
				<td><c:if test="${ren.rentals.sta eq 'T'}">
							已付清
					</c:if>
					<c:if test="${ren.rentals.sta eq 'F'}">
							未付清
					</c:if>
				 </td>
				<td><c:if test="${ren.rentals.inumber ==0}">
							押金
					</c:if>
					<c:if test="${ren.rentals.inumber !=0}">
							月租金
					</c:if></td>
				
				
			</tr>
		</c:forEach>
		</c:if>
		
		</tbody>
	</table>
	
</body>
</html>