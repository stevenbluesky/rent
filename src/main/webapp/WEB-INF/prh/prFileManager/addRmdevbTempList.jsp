
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
function checkRoomType(){
	
	var typeId=$("#roomTypeId").val();
	
	
	
	layer.ready(function(){ 
		var index =   layer.open({
		    type: 2,
		    title: '请点击右上角关闭！',			    
		    maxmin: true,
		    area: ['1200px', '600px'],
		    /* content: 'http://layer.layui.com/test/welcome.html', */
		    /* content: '${ctx}/syseckRepaire.jsp', */
		    content:'checkRoomtype?typeId='+typeId,  		    
		    end: function(){		    	
		    	location.reload();		    
		    }
		  });
		layer.full(index);
		});
	}

	function GiveUp(){
		
		window.location.href='${ctx}/../prhRmdevCenter?flag='+2;
	}


</script>

</head>
<body>






	<div class="row-fluid">
		
		<div class="span8 repaire-search">
		 <form id="myform" method="post" >
		 	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th colspan="2">新增设备模板</th>
			</tr>
			<tr>
						
				<c:if test="${roomname==''}">
					<th>
					
					<div class="span1 search-input" style="padding-left: 90px;">
					房间类型：<select name="typeId" class="typeId" style="width: 150px;" id="roomTypeId">
						<c:forEach var="type" items="${roomtypes}">
							<option value="${type.id}" >${type.name }</option>
						</c:forEach>
						
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="button" class="btn" style="margin-right:10px;" value="编    辑" onclick="checkRoomType()"> 
					<!-- 请输入模板名称：<input  name="typename" type="text" id="name"></input><input  type="button" class="btn" style="margin-right:10px;" value="点击添加" onclick="checkRoomType()"> -->
					</div></th>		
				</c:if>
				
				
				<c:if test="${roomname!=''}">
					
					<th>
					
					
					<div class="span1 search-input" style="padding-left: 90px;">
					房间类型：<select name="typeId" class="typeId" style="width: 150px;" id="roomTypeId">
						<c:forEach var="type" items="${roomtypes}">
							<option value="${type.id}" >${type.name }</option>
						</c:forEach>
						
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="button" class="btn" style="margin-right:10px;" value="编    辑" onclick="checkRoomType()"> 
					<!-- 请输入模板名称：<input  name="typename" type="text" id="name"></input><input  type="button" class="btn" style="margin-right:10px;" value="点击添加" onclick="checkRoomType()"> -->
					</div></th>
							
				</c:if>
				
			</tr>	
		</thead>
	</table>
		 </form>
		</div>
	</div>
	<sys:message content="${message}"/>
		 <form id="SecForm" method="post" action="OverAddTemp" >
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
					
				<th>类别</th>
				<th>项目</th>				
				<th>数量</th>
				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${temps}">
				<tr style="text-align: center;">
								
					<c:if test="${b.cla eq '1'}">
						<td>1.钥匙</td>					
					</c:if>
					<c:if test="${b.cla eq '2'}">
						<td>2.房内设施</td>					
					</c:if>
					<c:if test="${b.cla eq '3'}">
						<td>3.房内电器</td>					
					</c:if>
					<c:forEach var="a" items="${equipment}">
						<c:if test="${b.eqid==a.id}">
							<td>${a.name}</td>
						</c:if>		
					</c:forEach>					
					<td>${b.num}</td>		
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	<sys:message content="${message}"/>
	<div class="pagination">${numpage}</div>
	<div>		
		<c:if test="${roomname==''}">
			<button class="btn" style="margin-right:10px;" disabled="disabled">新增完成</button>
		</c:if>		
		<c:if test="${roomname!=''}">
			<button class="btn" style="margin-right:10px;" >新增完成</button>
		</c:if>	
		
	</div>
	
	</form>
	<button class="btn" style="margin-right:10px;" onclick="GiveUp()">放弃返回</button>	
</body>
</html>
