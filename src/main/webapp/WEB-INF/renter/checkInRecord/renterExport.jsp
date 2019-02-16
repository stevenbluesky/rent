<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html >
<head>
<meta http-equiv="Content-Type" />
<title>公租房房源管理</title>
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
.searchTxt{
			width: 76px;
		}
a:visited {
	color: #444547;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}
<!--
body,div,ul,li {
	padding: 0;
}
body {
	font: 12px "宋体";
	text-align: left;
}


ul {
	list-style: none;
}
.main {
	clear: both;
	padding: 8px;
	text-align: left;
}
/*第一种形式*/
#tabs0 {
	height: 600px;
	width: 100%;
	border: 1px solid #red;
	background-color: #f2f6fb;
}

.menu0 {
	width: 400px;
}

.menu0 li {
	display: block;
	float: left;
	padding: 4px 0;
	width: 100px;
	text-align: center;
	cursor: pointer;
	background: orange;
}

.menu0 li.hover {
	background: #ff0;
}

#main0 .menu0 {
	display: none;
}

#main0 .menu0.block {
	display: block;
}
/*第二种形式*/
#tabs {
	text-align: left;
	width: 400px;
}

.menu1box {
	position: relative;
	overflow: hidden;
	height: 22px;
	width: 400px;
	text-align: left;
}

#menu1 {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1;
}

#menu1 li {
	float: left;
	display: block;
	cursor: pointer;
	width: 72px;
	text-align: center;
	line-height: 21px;
	height: 21px;
}

#menu1 li.hover {
	background: #fff;
	border-left: 1px solid #333;
	border-top: 1px solid #333;
	border-right: 1px solid #333;
}

.main1box {
	clear: both;
	margin-top: -1px;
	border: 1px solid #333;
	height: 181px;
	width: 400px;
}

#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}
-->
</style>
<%--<link rel="stylesheet" href="css/page.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/page.js"></script>--%>
<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		$("#tabs0 li").click(function() {
			$(this).addClass("hover").siblings().removeClass("hover");
			var estateId= $(this).find("input").val();	  
			location.href="findPrHouseByEstatePaged.do?estateId="+estateId+"&currpage=1";
		});
		$(".chkAll").click(function(){
			
			chk(); //全选全不选
		});
		$("#searchForm").submit(function(){
			return check();
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
	
    function check(){
		var minArea=$(".minArea").val();
		var maxArea=$(".maxArea").val();
		
		if(isNaN(minArea)||isNaN(maxArea)){
			$.jBox.tip("面积必须为数字！");
			return false;
		}
		return true;
	}
    
    
    
</script>


</head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>
<body>

	<!--第一种形式-->
	
<div class="row-fluid">
	<div class="span4" style="width: 100%">
		<ul class="nav nav-tabs" >
					
					<c:forEach var="e" items="${estates}" varStatus="status">
					
					<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a href='findPrHouseByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
							</c:if>
					</c:if>
						
					<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
							<li class="active"><a href='findPrHouseByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
						<c:if test="${estateId!=e.id  }">
							<li><a href='findPrHouseByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
					</c:if>
						
				</c:forEach>
		</ul>
		</div>
		<div class="row-fluid repaire-search" style="height: 28px; margin: 0px;padding: 0px;" >
			 <form method="post" id="searchForm"  
					  action="renterExport.do">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					<div class="row-fluid">
						<div class="span2 search-input" style="width: 100%;height: 25px;margin-bottom: 10px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								楼号:&nbsp;&nbsp; <select name="buildingId" class="floorId" 
								style="width: 90px;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${buildings}">
									<c:if test="${condition.buildingId==f.name }">
										<option selected="selected" value="${f.name}">${f.name}号楼</option>
									</c:if>

									<c:if test="${condition.buildingId!=f.name }">
										<option value="${f.name }">${f.name }号楼</option>
									</c:if>
								</c:forEach>
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
									
									
									
							  单元:&nbsp;&nbsp; <select name="buildingNoId" class="floorId" 
								style="width: 90px;">
								<option value="-1">全部</option>
								
								
								<c:forEach var="f" items="${fns:getAllUnits()}">
									<c:if test="${condition.buildingNoId==f }">
										<option selected="selected" value="${f}">${f}单元</option>
									</c:if>

									<c:if test="${condition.buildingNoId!=f }">
										<option value="${f}">${f}单元</option>
									</c:if>
								</c:forEach>
								
								
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
							
						
						
						
							房型 :&nbsp;&nbsp; <select name="typeId" class="floorId" 
								style="width: 90px;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${roomTypes}">
									<c:if test="${condition.typeId==f.id }">
										<option selected="selected" value="${f.id}">${f.name}</option>
									</c:if>
											
									<c:if test="${condition.typeId!=f.id }">
										<option value="${f.id }">${f.name }</option>
									</c:if>
								</c:forEach>
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
							
							收入等级:&nbsp;&nbsp; <select name=subsidyTypeId class="floorId" 
								style="width: 90px;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${subsidyTypes}">
									<c:if test="${condition.subsidyTypeId==f.id }">
										<option selected="selected" value="${f.id}">${f.name}</option>
									</c:if>
											
									<c:if test="${condition.subsidyTypeId!=f.id }">
										<option value="${f.id }">${f.name }</option>
									</c:if>
								</c:forEach>
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
							
							
							面积:&nbsp;&nbsp;<input name="minArea" value="${condition.minArea }" type="text" class="searchTxt minArea">&nbsp;&nbsp;到&nbsp;&nbsp;
							<input name="maxArea" value="${condition.maxArea }"  type="text" class="searchTxt maxArea">&nbsp;&nbsp;&nbsp;&nbsp;
							
							
							租户状态:&nbsp;&nbsp; <select name="masterType" class="masterType" style="width: 90px;">
								<option value="-1">全部</option>
								<option value="1" <c:if test="${condition.masterType==1 }">selected="selected"</c:if>>在租</option>
								<option value="7" <c:if test="${condition.masterType==7 }">selected="selected"</c:if>>登记</option>
								<option value="8" <c:if test="${condition.masterType==8 }">selected="selected"</c:if>>签约</option>
								<option value="4" <c:if test="${condition.masterType==4 }">selected="selected"</c:if>>退租</option>
								
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
							
							
							        <input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" >
							 </div>
						</div>

				</form>
	
		</div>
		
	</div>	
		<div class="main" id="main0" style=" margin: 0px;padding: 0px;">
			
			<form id="myForm" action="prHouseDel.do" method="post">
			<input  type="hidden" name="estateId" value="${estateId }"/>
			<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;text-align: center;" >
					<tr>
						<th>姓名</th>
						<th>身份证</th>
						<th>收入等级</th>
						<th>人口</th>
						<th>小区名称</th>
						<th>房间号</th>
						
 						<th>面积</th>
						<th>地下室编号</th>
						
						<!-- <th>保障内面积</th>
						<th>保障外面积</th> -->
						
						
						<th>应缴月租金</th>
						<th>应缴年租金</th>
						<th>补贴</th>
						<th>月实缴</th>
						<th>年实缴</th>
						<th>电话号码</th>
						<th>合同止日期</th>
						<th>办理日期</th>
						
					</tr>
						<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="18">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>
					<c:forEach var="m" items="${page.list }" varStatus="status">
						<tr style="text-align: center;">
						
						  	<td>${m.profile.name}</td>
							<td>${m.profile.idno}</td>
							<td>${m.subsidyType.name }</td>
							<td>${m.numbs}</td>
							<td>${m.prHouse.estate.name}</td>
							<td>${m.prHouse.buildingNo.buildingId}-${fn:substring(m.prHouse.buildingNo.unitName, 0, 1)}-${m.prHouse.roomNo}</td>
							<td>${m.prHouse.area}</td>
							<td width="5"> ${m.prHouse.backPrice}</td>
 							<!-- <td></td>
 							<td></td> -->
 							<td>${m.prHouse.monthPrice }</td>
 							<td>${m.prHouse.monthPrice*12 }</td>
 							<td>${m.setrate}</td>
 							<td>${m.rate }</td>
 							<td>${m.rate*12 }</td>
 							<td>${m.profile.mobile}</td>
 							<td><fmt:formatDate value="${m.edate }" pattern="yyyy-MM-dd" /></td>
 							<td><fmt:formatDate value="${m.bdate}" pattern="yyyy-MM-dd" /></td>
						

						</tr>
					</c:forEach>

				</table>
			
		<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>
			  &nbsp;&nbsp;&nbsp;&nbsp;
			 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" type="button" value="租户导出" onClick="exportHouse();">&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==73}">
					<input class="btn" type="button" value="租户导出" onClick="exportHouse();">&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>	 
			</form>	  	
		</div>
	
	<script type="text/javascript">
	function exportHouse(){
		 location.href="rentersReport.do?estateId=${estateId}&buildingId=${condition.buildingId}&buildingNoId=${condition.buildingNoId}&subsidyTypeId=${subsidyTypeId}&typeId=${condition.typeId}&houseNature=${houseNature}&minArea=${condition.minArea}&maxArea=${condition.maxArea}&masterType=${condition.masterType}"; 
	}
	
	</script> 	 
</body>
</html>