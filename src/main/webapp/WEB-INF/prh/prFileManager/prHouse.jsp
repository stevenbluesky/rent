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

.jbox-title-panel {
	background: #dc6c6c !important;
	text-align: center;
}

.area-content {
	min-height: 84px;
	a min-width: 475px;
}
</style>
<script type="text/javascript">

		$(function(){
			/* 操作后的提示语 */
			if (<%=request.getAttribute("tip") != null%>) {
				$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
	});
	function selectSubmit() {
		var rent = document
				.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的房源！');
			return;
		}
		var rentID = rent.value;
		window.location.href = '${ctx}/../prhRmdev?houseid=' + rentID;
	}

	function updateSubmit() {
		var rent = document
				.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的房源！');
			return;
		}
		var rentID = rent.value;
		window.location.href = '${ctx}/../updateHouse?houseid=' + rentID;

	}
	function ControllerSubmit() {
		var rent = document
				.querySelector('#contentTable input[type="radio"]:checked');

		window.location.href = '${ctx}/../addRmdevbTemp';

	}
	function moreAdd() {

		window.location.href = '${ctx}/../moreAddRmdev?estateId=${estateId}';
	}
	function historyMaster() {
		var rent = document
				.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的房源！');
			return;
		}
		var rentID = rent.value;
		window.location.href = '${ctx}/../historyMaster?houseid=' + rentID;
	}
</script>

</head>
<body>

	<div class="row-fluid">
		<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs">
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../prHouseFileByCondition?estateId=${e.id}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>
		</div>
		<div class="row-fluid repaire-search" style="margin-bottom: 10px;">
			<form method="post"
				action="${ctx}/../prHouseFileByCondition?estateId=${estateId}">


				<div class="span2 search-input" style="width: 150px;">
					<c:if test="${condition.no!=null}">
													楼        号:<input name="no" type="text" id="master"
							style="width: 100px;" value="${condition.no}"></input>
					</c:if>
					<c:if test="${condition.no ==null}">
													楼        号:<input name="no" type="text" id="master"
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
					房 型: <select id="roomtypeid" name="roomtypeid" style="width: 100px">
						<option value="-1">请选择</option>
						<c:forEach var="n" items="${roomtype}">
							<option
								<c:if test="${roomtypeid == n.id}">selected="selected"</c:if>
								value="${n.id}">${n.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="span2 search-input">
					房源状态: <select id="state" name="state" style="width: 100px">
						<option <c:if test="${state == -1}">selected="selected"</c:if>
							value="-1">请选择</option>
						<option <c:if test="${state ==1}">selected="selected"</c:if>
							value="1">签约</option>
						<option <c:if test="${state == 2}">selected="selected"</c:if>
							value="2">登记</option>
						<option <c:if test="${state == 3}">selected="selected"</c:if>
							value="3">欠费</option>
						<option <c:if test="${state == 4}">selected="selected"</c:if>
							value="4">锁定</option>
						<option <c:if test="${state == 5}">selected="selected"</c:if>
							value="5">在租</option>
						<option <c:if test="${state == 6}">selected="selected"</c:if>
							value="6">可租</option>
						<option <c:if test="${state == 7}">selected="selected"</c:if>
							value="7">空置维修</option>
						<option <c:if test="${state ==8}">selected="selected"</c:if>
							value="8">在租维修</option>

					</select>
				</div>
				<div class="span2 search-input">
					<input id="search" class="btn" type="submit" value="条件搜索">
				</div>
			</form>
		</div>
	</div>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>编号</th>
				<th>租户</th>
				<th>物业</th>
				<th>楼号</th>
				<th>单元</th>
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

			<c:forEach var="b" items="${page.list}">
				<tr style="text-align: center;">
					<td><input type="radio" value="${b.id}" class="rent-check"
						name="rentCheck"></td>
					<td>${b.no}</td>

					<c:forEach var="n" items="${names}">
						<c:if test="${b.id==n.id}">
							<td>${n.name}</td>
						</c:if>
					</c:forEach>
					<td>${b.estate.name}</td>
					<td>${b.buildingNo.buildingId}号楼</td>
					<td>${b.buildingNo.unitName}</td>
					<td>${b.buildingFloor.name}</td>
					<td>${b.roomNo }</td>
					<td>${b.roomType.name}</td>
					<td>${b.area}</td>
					<td>${b.roomNum}</td>
					<td>${b.monthPrice}</td>

					<c:if test="${b.state!=null}">
						<c:set var="type" scope="request" value="${b.state}"></c:set>
						<td><%=MyConvertUtil.getHouseState(Integer.valueOf((request.getAttribute("type").toString())))%>
						</td>
					</c:if>
					<c:if test="${b.state==null}">
						<td></td>
					</c:if>

					<td>${b.remark}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${numpage}</div>
	<div>





		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">

				<button class="btn" style="margin-right: 10px;"
					onclick="historyMaster()">查看房间历史租户</button>
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:forEach var="m" items="${role.moduleList }">
					<c:if test="${m.id==42}">

						<button class="btn" style="margin-right: 10px;"
							onclick="historyMaster()">查看房间历史租户</button>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>

		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">

				<button class="btn" style="margin-right: 10px;"
					onclick="selectSubmit()">查看配套设备</button>
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:forEach var="m" items="${role.moduleList }">
					<c:if test="${m.id==43}">

						<button class="btn" style="margin-right: 10px;"
							onclick="selectSubmit()">查看配套设备</button>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>

		<%-- 
	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<button  class="btn" style="margin-right:10px;"  onclick="ControllerSubmit()" >新增设备模板</button>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==44}">
						
					<button  class="btn" style="margin-right:10px;"  onclick="ControllerSubmit()" >新增设备模板</button>
			 </c:if></c:forEach></c:if></c:forEach>
			  --%>
		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">

				<button class="btn" style="margin-right: 10px;" onclick="moreAdd()">批量添加设备(模板)</button>
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:forEach var="m" items="${role.moduleList }">
					<c:if test="${m.id==45}">

						<button class="btn" style="margin-right: 10px;"
							onclick="moreAdd()">批量添加设备(模板)</button>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>

	</div>
</body>
</html>
