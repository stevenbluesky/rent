
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
		$("#form1").submit(function(){
			  alert("Submitted");
		});
		
			function add(){
				
				//取到 三个值判断是否为空
				var cla =document.getElementById("devclass");
				var name=document.getElementById("name");
				var num=document.getElementById("num");
				
				if($('select[name="devclass"]').val() == -1){
					
					alert("请选择设备类型!");
					return false;
				}
				if($('select[name="eqid"]').val() == -1){
						
						alert("请选择具体设备!");
						return false;
					}
				
					if($('select[name="num"]').val() == -1){
						
						alert("请选择数量!");
						return false;
					}
				$('#form1').submit();
				
			}
		
</script>
	
</head>
<body>






	<div class="row-fluid">
		
		<div class="row-fluid repaire-search" style="margin-bottom:10px;">
			<form id="form1" method="post"  action="${ctx}/../prhAddRmdev">
			<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<input type="hidden" name="roomno" value="${houseid}"/>
				<th>代码：<input type="text" name="id" value="${id}" readonly="readonly"/ ></th>
				<th>类别:<select class="devclass" id="state" name="devclass">
						<option value="-1">请选择</option>
						<option value="1">1.钥匙</option>
						<option value="2">2.房内设施</option>
						<option value="3">3.房内电器</option>
						</select></th>
				
				<th>名称:<select class="devname" id="state" name="eqid">
							<option value="-1">请选择</option>
							<c:forEach  var="e" items="${equipment}" >							
								<option value="${e.id}">${e.name}</option>							
							</c:forEach>
					</select></th>
				<th>数量:<select class="devname" id="state" name="num">
							
							
							<c:forEach var="s"  begin="1" end="10">
								<option value="1">${s}</option>	
							</c:forEach>
					</select></th>
				
			</tr>
			<tr>
				<th>品牌:<input type="text" readonly="readonly"/></th>
				<th>型号：<input type="text" readonly="readonly"/></th>
				<th>规格：<input type="text" readonly="readonly"/></th>
				<th>颜色：<input type="text" readonly="readonly"/></th>
				
			</tr>
			<tr>
				<th>单价：<input type="text" readonly="readonly"/></th>
				<th>购买日期：<input type="text" readonly="readonly"/></th>
				<th>保修日期：<input type="text" readonly="readonly"/></th>
				<th>折旧率：<input type="text" readonly="readonly"/></th>		
			</tr>
			<tr>
				<th colspan="4">备注：<input type="textarea"/></th>
					
			</tr>
			<tr>
				<th>操作员：<input type="text" readonly="readonly"/></th>
				<th>设备状态：<input type="date" readonly="readonly"/></th>
				<th>操作时间：<input type="text" readonly="readonly"/></th>
				<th>成本价：<input type="text" readonly="readonly"/></th>		
			</tr>
		</thead>
		<tbody>
			
			
		
		</tbody>
	</table>
			<div class="span2 search-input"><input id="search" class="btn" type="button" onclick="add()" value="添加"></div>
			
			<div class="span2 search-input"><input id="search" class="btn" type="button" value="返回" onclick="close()"></div>
			</form>
		</div>
	</div>
	<sys:message content="${message}" />
	
	<div class="pagination">${numpage}</div>
	<div>		
	</div>
	
</body>
</html>
