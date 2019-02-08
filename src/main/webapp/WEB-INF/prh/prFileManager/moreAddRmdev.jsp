
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
	
</script>

</head>
<body>






	<div class="row-fluid">
		<div class="span4"  style="width: 100%">
			<ul class="nav nav-tabs"  style="width: 100%">
				<c:forEach items="${roomtypes}" var="e">
					<li <c:if test="${e.id==roid}">class="active"</c:if>><a
						href="${ctx}/../moreAddRmdev?roid=${e.id}&estateId=${estate.id }">${e.name }</a></li>
				</c:forEach>
				
			</ul>
		</div>
		<div class="row-fluid repaire-search" style="margin-bottom: 10px;">
			<form id="form2" method="post" action="">
			<input id ="roid" type="hidden" value="${roid}" name="roid"/>
			
				<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>类别</th>
				<th>项目</th>
				
				<th>价格</th>
				
				<th>数量</th>	
				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="b" items="${page.list}" varStatus="status">
				<tr style="text-align: center;">					
						<td>${status.count }</td>
						<td><c:set var="cla"  value="${b.cla }"></c:set> 
			 			<%=MyConvertUtil.getEquipType( pageContext.getAttribute("cla").toString().trim() ) %></td>
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
				<div>
					<div class="span2 search-input">物        业:
						<input id="estateId" name="estateId" type="hidden" value="${estate.id }">
						<input type="text" value="${estate.name }" style="width: 100px;" disabled="disabled">
					</div>
				</div>
	
				<div>
					<div class="span2 search-input">房        型:
						<select id="roomtypeid" name="roomtypeid" style="width: 120px;">
								
							<c:forEach var="n" items="${roomtypes}" varStatus="1">					
								<option value="${n.id}">${n.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
					<div>
					<div class="span2 search-input" >单        元:
						<select id="danyuanid" name="danyuanid" style="width: 120px;">
								
							<c:forEach var="n" items="${danyuan}" varStatus="1">					
								<option value="${n.id}">${n.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
			
				<div class="span2 search-input">
					<input id="search" class="btn" type="button" value="添加" onclick="moreAdd()">&nbsp;&nbsp;
					<input type="button" class="btn" onclick="location.href='prHouseFileByCondition?estateId=${estate.id}'" value="返回">  
				</div>			
			</form>
		</div>
	</div>
	<sys:message content="${message}" />

	
	<div></div>


</body>
</html>

