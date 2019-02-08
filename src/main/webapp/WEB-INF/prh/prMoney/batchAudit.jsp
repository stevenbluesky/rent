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
	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		
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
	function Balance(){		
		
		//获取要结算的列的id单选按钮
		
		var rent = document.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的合同主单！');
			return;
		}else{
			$("#Jform").submit();
		}
	  
			
		
		
	
	}
	
	</script>
	
</head>
<body>
	
	<form  method="post"  action="dailyContractRental" >
	<input type="hidden" name="estateId" value="${estateId }">
	<ul class="nav nav-tabs">
					<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../dailyContractRental?estateId=${e.id}">${e.name }</a></li>
						<input name = "estateId"  id = "estateId" type="hidden" value="${estateId}"/>
					</c:forEach>	
					<li>
					
						<div class="row-fluid repaire-search" style="margin-bottom:0px;">							
												
												<div class="span2 search-input" style="width:100px;">
												<c:if test="${condition.name!=null}">
													姓        名: <input  name="name" type="text" id="master" style="width:100px;" value="${condition.name}"></input>
												</c:if>
												
												<c:if test="${condition.name ==null}">
													姓        名: <input  name="name" type="text" id="master" style="width:100px;"></input>
												</c:if>
												</div>
												<div class="span2 search-input" style="width:100px;">
												<c:if test="${condition.no!=null}">
													房        号:<input  name="no" type="text" id="master" style="width:100px;" value="${condition.no}"></input>
												</c:if>
												<c:if test="${condition.no ==null}">
													房        号:<input  name="no" type="text" id="master" style="width:100px;" ></input>
												</c:if>
													
												</div>
												
												
												<div class="span2 search-input" style="padding-top: 10px;">
													<input  type="submit" class="btn" value="条件搜索" id="master" style="width:150px;margin-left: 50px;"></input>
												</div>
																																	
						</div>
									
						</li>			
					</ul>
					<ul class="nav nav-tabs">
					<li>
						<div class="row-fluid repaire-search" style="margin-bottom:20px;">
												<c:if test="${condition.type ==null}">
												缴费类型:<input  name="type" type="radio" id="master" value="2">首次缴费</input>
													
												
													<input  name="type" type="radio" id="master"  value="1">续        租</input>
												</c:if>
												<c:if test="${condition.type !=null}">
												缴费类型:<input <c:if test="${condition.type==2}">checked="checked"</c:if> name="type" type="radio" id="master" value="2">首次缴费</input>
													
												
													<input <c:if test="${condition.type==1}">checked="checked"</c:if> name="type" type="radio" id="master"  value="1">续        租</input>
												</c:if>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<!-- 状态:<input  name="src" type="radio" id="master" value="2">已结算</input>
													
								
													<input  name="src" type="radio" id="master" value="1">未结算</input> -->	
											
						</div>
					</li>
					</ul>
				</form>	
			<sys:message content="${message}"/>
			<form id="Jform" action="balance" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择
				 </th><th>编号</th><th>物业</th><th>楼号</th><th>房号</th><th>入住人</th><th>合同起始日期</th><th>合同截止日期</th><th>缴费类型</th><th>状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="ma" >
			<tr>		
				<td>
					<input type="radio" name="chk" class="chk" value="${ma.id}"/>
				</td>			
				<td>${ma.id}</td>
					
				<td>${ma.prHouse.estate.name}</td>
				<td>单元</td>	
				<td>${ma.prHouse.no}</td>
				<td>${ma.profile.name}</td>			
				<td><fmt:formatDate value="${ma.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ma.edate}" pattern="yyyy-MM-dd" /></td>	
				<c:if test="${ma.type!=null}">
				<c:set var="type" scope="request" value="${ma.type}"></c:set>
				<td><%=MyConvertUtil.getMasterType((request.getAttribute("type").toString())) %> </td>
				</c:if>
				<c:if test="${ma.type!=null}">
					<td></td>
				</c:if>
				<td><c:if test="${ma.src eq '1'}">未结算</c:if><c:if test="${ma.src eq '2'}">已结算</c:if></td>			
				<td>${ma.refer1 }</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${numpage }</div>
	
	<div>
		<input type="button"  id="repaireApply" class="btn"  value="结算" onclick="Balance()"/>		
	</div>
	</form>
</body>
</html>
