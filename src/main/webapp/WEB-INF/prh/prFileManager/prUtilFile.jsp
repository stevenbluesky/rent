<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>

<head>

	<title>单位档案管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
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

	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
	});
	
	function selectSubmit(){		
			var rent = document.querySelector('#contentTable input[type="radio"]:checked');
			if (!rent) {
				$.jBox.tip('请选择具体的单位！');
				return;
			}
			var rentID = rent.value;
			
			window.location.href='${ctx}/../prUtilFileDetail?guestno='+rentID;
		}

	</script>

</head>
<body>
	<div class="row-fluid">
	<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs" >
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../prUtilFile?estateId=${e.id}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>
			
		</div>
	
		</div>
		
		
			<form  method="post"  action="${ctx}/../prUtilFile?estateId=${estateId}">
		
			<div class=" search-input">
			&nbsp;&nbsp;&nbsp;
			公司名称:&nbsp;&nbsp;&nbsp;
			<input  name="name" type="text" id="master" value="${name }" style="width: 100px"></input>
			&nbsp;&nbsp;&nbsp;
			<input id="search" class="btn" type="submit" value="条件搜索">
			</div>
			
			
			
			</form>
			
		
	<sys:message content="${message}"/>
	<form action="${ctx}/../prUtilFileDetail" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>		<th>选择 </th>
						<th>系统编号 </th>
						<th>单位名称</th>										
						<th>电话号码</th>
						<th>企业法人</th>
						<th>销售员</th>
						<th>联系人</th>
						<th>备注</th>
						
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach var="b" items="${page.list}">
			<tr style="text-align: center;">
						<input id="utilfileno" type="hidden" value="${b.guestno}"></input>
						<td><input type="radio" value="${b.guestno}" class="rent-check"
						name="guestno"></td>
						<td>${b.guestno} </td>					
						<td>${b.company} </td>	
						<td>${b.phone} </td>
						<td>${b.idno} </td>
						<td>${b.saleid} </td>
						<td>${b.liason} </td>
						<td>${b.refer}</td>				
				</tr>
		</c:forEach>		
		</tbody>
	</table>
	<div class="pagination" >${numpage}</div>
	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<div><input type="button" value="单位租户明细" class="btn" onclick="selectSubmit()"/></div>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==41}">
						
					<div><input type="button" value="单位租户明细" class="btn" onclick="selectSubmit()"/></div>
			 </c:if></c:forEach></c:if></c:forEach>
	
	
	
	</form>
</body>
</html>