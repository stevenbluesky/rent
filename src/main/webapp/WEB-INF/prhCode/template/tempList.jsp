
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
function moreAdd(){
	//取下拉框里面的值
	  var danyuanid= $("#danyuanid").val();
	  var  estateId= $("#estateId").val();
	  var  roomtypeid= $("#roomtypeid").val();
	  var roid=$("#roid").val();
	  if(danyuanid==-1&&estateId==-1&&roomtypeid==-1){
		  $.jBox.tip("请选择要批量添加的区域！");	
	  }else{
		  window.location.href='${ctx}/../moreAddRmdevList?danyuanid='+danyuanid+'&estateId='+estateId+'&roomtypeid='+roomtypeid+'&roid='+roid;
	  }
}
	
function toAdd(){
	 layer.open({
		    type: 2,
		    title: '编辑设备模板',
		    maxmin: false,
		    area: ['834px', '576px'],
		    content:'toTemplateItem.do?typeId=${roid}',
		    end: function(){
		    	location.reload();
		    }
		  });
}

</script>

</head>
<body>


	<div class="row-fluid">
		<div class="span4"  style="width: 100%">
			<ul class="nav nav-tabs"  style="width: 100%">
				<c:forEach items="${roomtypes}" var="e">
					<li <c:if test="${e.id==roid}">class="active"</c:if>><a
						href="${ctx}/../templateList.do?roid=${e.id}">${e.name }</a></li>
				</c:forEach>
				
			</ul>
		</div>
		<div class="row-fluid repaire-search" style="margin-bottom: 10px;">
			<form id="myForm" method="post" action="delTemplateItem.do">
			<input id ="roid" type="hidden" value="${roid}" name="roid"/>
			
				<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/>选择</th>
				<th>类别</th>
				<th>项目</th>
				
				<th>价格</th>
				
				<th>数量</th>	
				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="b" items="${page.list}">
				<tr style="text-align: center;">					
						<td><input type="checkbox" name="chk" class="chk" value="${b.id}"/></td>
						<td>
						<c:set var="cla"  value="${b.cla }"></c:set> 
			 			<%=MyConvertUtil.getEquipType( pageContext.getAttribute("cla").toString().trim() ) %>
						</td>
						<c:forEach var="n" items="${equipment}">
						<c:if test="${b.eqid==n.id}">
							<td>${n.name}</td>
							<td>${n.price}</td>
						</c:if>
						</c:forEach>
					<td>${b.num}</td>
					
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
				

	<div class="pagination">${numpage}</div>
	 &nbsp;&nbsp; &nbsp;&nbsp;
		<input class="btn" value="编辑模板" type="button"
					onclick="toAdd()"
					
					/>  &nbsp;&nbsp;&nbsp;&nbsp;
			
				<input
				class="btn"	value="删 除" type="submit" onclick="del()" /> 
	<div></div>


				<script type="text/javascript">
	function chk(){
		var chkAllFlag= $(".chkAll").attr("checked");
		
		var chks= $(".chk");
		if(chkAllFlag=="checked"){
			$(".chk").attr("checked",true);
		}else{
			$(".chk").attr("checked",false);
		}
	}
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}

		$(".chkAll").click(function() {

			chk(); //全选全不选
		});

	});
	function del() {
		var chk = $(".chk");
		var ids = new Array();
		var count = 0;

		//循环
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				ids.push(id);
				count++;
			}

		});

			if (count == 0) {
			$.jBox.tip("请选择要删除的配套设施类型！");
			$("#myForm").submit(function() {
				return false;
			});
			return;
		}
		if (!confirm("确认删除吗?")) {
			$("#myForm").submit(function() {
				return false;
			});
		}
	}			
</script></body>
</html>

