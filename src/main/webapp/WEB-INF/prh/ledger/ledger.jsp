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
<body style="overflow: hidden;">
	<div class="row-fluid">
		<div class="span4" style="width: 100%">
	<ul class="nav nav-tabs" style="">
			<c:forEach items="${estates}" var="e">
				<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
				href="${ctx}/../ledger?estateId=${e.id}&sta=${sta}">${e.name }</a></li>
				<input name = "estateId"  id = "estateId" type="hidden" value="${estateId}"/>
			</c:forEach>	
						
		</ul>
		</div></div>
		
		
	<form  method="post"  action="ledger" >
					<ul class="nav nav-tabs" style="width: 100%">
					
					<li <c:if test="${sta eq '1'}">class="active"</c:if>><a
						href="${ctx}/../ledger?sta=1">欠费</a></li>
					<li <c:if test="${sta eq '2'}">class="active"</c:if>><a
						href="${ctx}/../ledger?sta=2">即将到期</a></li>
						<input name = "sta"  id = "sta" type="hidden" value="${sta}"/>			
					</ul>
					
					
				
					<ul class="nav nav-tabs">
					
					<li>
					
						<div class="row-fluid repaire-search" style="margin-bottom:0px;">							
												
												<div class="span2 search-input" style="width:150px;">
													
												<c:if test="${condition.name!=null}">
													姓        名:<input  name="name" type="text" id="master" style="width:100px;" value="${condition.name}"></input>
												</c:if>
												
												<c:if test="${condition.name ==null}">
													姓        名: <input  name="name" type="text" id="master" style="width:100px;"></input>
												</c:if>
												</div>
												<div class="span2 search-input" style="width:150px;">
												<c:if test="${condition.no!=null}">
													楼        号:<input  name="buildingId" type="text" id="master" style="width:100px;" value="${condition.no}"></input>
												</c:if>
												<c:if test="${condition.no ==null}">
													楼        号:<input  name="buildingId" type="text" id="master" style="width:100px;" ></input>
												</c:if>
													
												</div>
												<div class="span2 search-input" style="width:150px;">
													单        元:
												<select id="danyuanid" name="danyuanid" style="width:100px;">
														<option value="-1">请选择</option>
													<c:forEach var="n" items="${danyuan}" varStatus="1">					
														<option <c:if test="${danyuanId==n.id}">selected="selected"</c:if>value="${n.id}">${n.name}</option>					
													</c:forEach>
												</select>		
												</div>
												
												
												
												<div class="span2 search-input" >
													<input  type="submit" class="btn" value="条件搜索" id="master" style="width:150px;margin-left: 50px;"></input>
												</div>
																																	
						</div>
									
						</li>	
					</ul>
				</form>	
			<sys:message content="${message}"/>
			<form id="Jform" action="balance" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择
				 </th><th>编号</th><th>物业</th><th>楼号</th><th>单元</th><th>房号</th><th>入住人</th><th>电话</th><th>合同起始日期</th><th>合同截止日期</th><th>状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
				<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="12">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>
		
		<c:if test="${flag eq 'true' }">
		<c:forEach items="${page.list}" var="ma" >
			<tr>		
				<td>
					<input type="radio" name="chk" class="chk" value="${ma.id}"/>
				</td>			
				<td>${ma.id}</td>
					
				<td>${ma.prHouse.estate.name}</td>
				<td>${ma.prHouse.buildingNo.buildingId}号楼</td>
				<td>${ma.prHouse.buildingNo.unitName}</td>				
				<td>${ma.prHouse.roomNo}</td>
				<td>${ma.profile.name}</td>		
				<td>${ma.profile.mobile}</td>
				<td><fmt:formatDate value="${ma.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ma.edate}" pattern="yyyy-MM-dd" /></td>	
				
				
				<c:set var="sta" scope="request" value="${ma.prHouse.state}"></c:set>
				<td><%=MyConvertUtil.getHouseState(Integer.valueOf((request.getAttribute("sta").toString()))) %> </td>	
				<td>${ma.refer1 }</td>
				
			</tr>
			</c:forEach>
		
		
			</c:if>
				<c:if test="${flag eq 'other' }">
		<c:forEach items="${page.list}" var="ma" >
			<tr>		
				<td>
					<input type="radio" name="chk" class="chk" value="${ma.accnts.id}"/>
				</td>			
				<td>${ma.accnts.id}</td>
					
				<td>${ma.accnts.prHouse.estate.name}</td>
				<td>${ma.accnts.prHouse.buildingNo.buildingId}号楼</td>
				<td>${ma.accnts.prHouse.buildingNo.unitName}</td>				
				<td>${ma.accnts.prHouse.roomNo}</td>
				<td>${ma.accnts.profile.name}</td>		
				<td>${ma.accnts.profile.mobile}</td>
				<td><fmt:formatDate value="${ma.accnts.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ma.accnts.edate}" pattern="yyyy-MM-dd" /></td>	
				
				
				<c:set var="sta" scope="request" value="${ma.accnts.prHouse.state}"></c:set>
				<td><%=MyConvertUtil.getHouseState(Integer.valueOf((request.getAttribute("sta").toString()))) %> </td>	
				<td>${ma.accnts.refer1 }</td>
				
			</tr>
			</c:forEach>
		
		
		
		</c:if>
		</tbody>
	</table>
	<div class="pagination">${numpage }</div>
	
	</form>
</body>
</html>
