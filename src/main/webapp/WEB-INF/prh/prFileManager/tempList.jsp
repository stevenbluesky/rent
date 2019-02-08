
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

$(function() {
	 /* 操作后的提示语 */
	

	$(".chkAll").click(function(){
		
		chk(); //全选全不选
	});
	
});

	function chk(){
		var chkAllFlag= $(".chkAll").attr("checked");
		
		var chks= $(".chk");
		if(chkAllFlag=="checked"){
			$(".chk").attr("checked",true);
		}else{
			$(".chk").attr("checked",false);
		}
	}

	function checkSel(){
		
		
		//判断  复选框是否选中了复选框
	    var chks= $(".chk");
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked){
				//提交 submit		
				$("#myForm").submit();
				return false;
			}
		}
		alert("请选择具设备！");
		return false;
	}


</script>

</head>
<body>






	<div class="row-fluid">
		
		
	</div>
	<sys:message content="创建房间类型*${roomname}*成功" />
	
	<form id="myForm" action="checkTempList.do" method="post">
	<input type="hidden"  value="${roomname}" name="roomname" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				
				<th>选择<input type="checkbox" 
				 class="chkAll" style="position: relative;top: 2px;"/></th>
				<th>类型</th>
				<th>项目</th>
				
				<th>数量</th>
				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${equ}" var="e" >	
			<tr>
				
				<th><input type="checkbox" name="chk" class="chk" value="${e.id}"/></th>
				<th><select class="a" id="b" name="cla${e.id}">						
								<option value="1">1.钥匙</option>	
								<option value="2">2.室内家具</option>	
								<option value="3">3.室内电器</option>	
					</select></th>
				<th><input type="hidden"  value="${e.id}" name="eqid" />
				${e.name}</th>
				<th><select class="devname" id="state" name="num${e.id}">
							<c:forEach var="s"  begin="1" end="10">
								<option value="${s}">${s}</option>	
							</c:forEach>
					</select></th>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
	<sys:message content="${message}"/>
	<div class="pagination">${numpage}</div>
	<div>		
		<input type="button" class="btn" style="margin-right:10px;"  onclick="checkSel()" value="选择完成"></input>			
	</div>
	</form>
	
</body>
</html>
