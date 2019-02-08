<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>用户管理</title>
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
	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}

		$(".chkAll").click(function() {
			chk(); //全选全不选
		});
	});

	function chk() {
		var chkAllFlag = $(".chkAll").attr("checked");

		var chks = $(".chk");
		if (chkAllFlag == "checked") {
			$(".chk").attr("checked", true);
		} else {
			$(".chk").attr("checked", false);
		}
	}
	function Balance() {

		//获取要结算的列的id单选按钮

		var rent = document
				.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的合同主单！');
			return;
		} else {
			$("#Jform").submit();
		}

	}
</script>

</head>
<body>

	<div class="row-fluid">
		<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs" style="width: 100%">
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../dailyContractRental?estateId=${e.id}">${e.name }</a></li>
					<input name="estateId" id="estateId" type="hidden"
						value="${estateId}" />
				</c:forEach>

			</ul>

		</div>
	</div>
	<form method="post" action="dailyContractRental">
		<input type="hidden" name="estateId" value="${estateId }">
		<ul class="nav nav-tabs1">
			<li style="float: left;">
				<div class="row-fluid repaire-search">
					<c:if test="${condition.sta==null}"> 
												缴费类型: <input name="sta" type="radio" id="master" value="1">登           记</input>

						<input name="sta" type="radio" id="master" value="2">签            约</input>

						<input name="sta" type="radio" id="master" value="3">在           租</input>
					</c:if>
					<c:if test="${condition.sta !=null}">
												缴费类型:<input
							<c:if test="${condition.sta==1}">checked="checked"</c:if>
							name="sta" type="radio" id="master" value="1">登         记</input>
						<input <c:if test="${condition.sta==2}">checked="checked"</c:if>
							name="sta" type="radio" id="master" value="2">签        约</input>
						<input <c:if test="${condition.sta==3}">checked="checked"</c:if>
							name="sta" type="radio" id="master" value="3">在        租</input>
					</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 状态:<input  name="src" type="radio" id="master" value="2">已结算</input>
													
								
													<input  name="src" type="radio" id="master" value="1">未结算</input> -->

				</div>

			</li>

			<li style="float: left;">

				<div class="row-fluid repaire-search" style="margin-bottom: 0px;">

					<div class="span2 search-input" style="width: 150px;">

						<c:if test="${condition.name!=null}">
													姓        名:<input name="name" type="text" id="master"
								style="width: 100px;" value="${condition.name}"></input>
						</c:if>

						<c:if test="${condition.name ==null}">
													姓        名: <input name="name" type="text" id="master"
								style="width: 100px;"></input>
						</c:if>
					</div>
					<div class="span2 search-input" style="width: 150px;">
						<c:if test="${condition.no!=null}">
													楼        号:<input name="buildingId" type="text" id="master"
								style="width: 100px;" value="${condition.no}"></input>
						</c:if>
						<c:if test="${condition.no ==null}">
													楼        号:<input name="buildingId" type="text" id="master"
								style="width: 100px;"></input>
						</c:if>

					</div>
					<div class="span2 search-input" style="width: 150px;">
						单 元: <select id="danyuanid" name="danyuanid" style="width: 100px;">
							<option value="-1">请选择</option>
							<c:forEach var="f" items="${fns:getAllUnits()}">
								<c:if test="${danyuanId==f }">
									<option selected="selected" value="${f}">${f}单元</option>
								</c:if>

								<c:if test="${danyuanId!=f }">
									<option value="${f}">${f}单元</option>
								</c:if>
							</c:forEach>
						</select>

					</div>

					<div class="span2 search-input">
						<input type="submit" class="btn" value="条件搜索" id="master"
							style="width: 150px; margin-left: 50px;"></input>
					</div>

				</div>

			</li>
		</ul>
	</form>
	<sys:message content="${message}" />
	<form id="Jform" action="balance" method="post">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>选择</th>
					<th>编号</th>
					<th>物业</th>
					<th>楼号</th>
					<th>单元</th>
					<th>房号</th>
					<th>入住人</th>
					<th>合同起始日期</th>
					<th>合同截止日期</th>
					<th>状态</th>
					<th>备注</th>
					<%--<th>角色</th> --%>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="11">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>
				<c:forEach items="${page.list}" var="ma">
					<tr>
						<td><input type="radio" name="chk" class="chk"
							value="${ma.id}" /></td>
						<td>${ma.id}</td>

						<td>${ma.prHouse.estate.name}</td>
						<td>${ma.prHouse.buildingNo.buildingId}号楼</td>
						<td>${ma.prHouse.buildingNo.unitName}</td>
						<td>${ma.prHouse.roomNo}</td>
						<td>${ma.profile.name}</td>
						<td><fmt:formatDate value="${ma.bdate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${ma.edate}" pattern="yyyy-MM-dd" /></td>


						<c:set var="sta" scope="request" value="${ma.sta}"></c:set>
						<td><%=MyConvertUtil.getMasterState((request.getAttribute("sta").toString()))%>
						</td>
						<td>${ma.refer1}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${numpage }</div>


		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">

				<div>
					<input type="button" id="repaireApply" class="btn" value="结算"
						onclick="Balance()" />
				</div>
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:forEach var="m" items="${role.moduleList }">
					<c:if test="${m.id==46}">

						<div>
							<input type="button" id="repaireApply" class="btn" value="结算"
								onclick="Balance()" />
						</div>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>


	</form>
</body>
</html>
