<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" />
<title>模块管理</title>
<style type="text/css">
<!--
body, div, ul, li {
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: left;
}
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
	height: 800px;
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

<script type="text/javascript">
	$(function() {
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		$("#tabs0 li").click(
				function() {
					$(this).addClass("hover").siblings().removeClass("hover");
					var estateId = $(this).find("input").val();
					location.href = "findBuildingNoByEstatePaged.do?estateId="
							+ estateId + "&currpage=1";
				});
		$(".chkAll").click(function() {

			chk(); //全选全不选
		});

	});

</script>


</head>
<body>
<input type="hidden" value="${menuLevel}" class="menuLevelHidden"/>

	<!--第一种形式-->
	<div>
		<ul class="nav nav-tabs">
		
			<li <c:if test="${tabNo==1}">class="active"</c:if> >
				<a href='toModuleList.do?tabNo=1'>菜单级别</a>
			</li>
						
			<li <c:if test="${tabNo==2}">class="active"</c:if> >
				<a href='toModuleBtn.do?tabNo=2'>按钮级别</a>
			</li>
			
		</ul>
		<script type="text/javascript">
		
		$(function(){
			var level1= $(".menuLevelHidden").val();
			
			changeLevel(level1);
		});
		
		function changeLevel(val){
			
			if (val==2) {
				$(".level1Class").show();
			}else{
				$(".level1Class").hide();
				$(".level1Dropdown").val("-1");
			}
		}
		
		</script>
		<div class="main" id="main0">
		
		<c:if test="${tabNo==1 }">
			<div class="row-fluid">
							<div class="span2 search-input" style="width: 100%;" >
								<br/>
								 <form method="post" id="searchForm" action="toModuleList.do">
								
								菜单等级:&nbsp;&nbsp;
								 <select name="menuLevel" class="floorId" onchange="changeLevel(this.value)" 
								style="width: 90px;">
								<option value="-1">全部</option>
								<option  value="1" <c:if test="${menuLevel==1}">selected="selected"</c:if>>一级菜单</option>
									<option  value="2" <c:if test="${menuLevel==2}">selected="selected"</c:if>>二级菜单</option>
								</select> &nbsp;&nbsp;&nbsp;&nbsp;
								
								
								<%-- <c:if test="${menuLevel==2 }"> --%>
								
								<span class="level1Class" >
								一级菜单:&nbsp;&nbsp; <select name="level1" class="level1Dropdown"
								style="width: 150px;">
								<option value="-1">全部</option>
								<c:forEach var="m" items="${level1Modules}">
									<c:if test="${level1==m.id }">
										<option value="${m.id }" selected="selected">${m.name }</option>
									</c:if>
									<c:if test="${level1!=m.id }">
										<option value="${m.id }">${m.name }</option>
									</c:if>
								</c:forEach>
								</select> &nbsp;&nbsp;&nbsp;&nbsp;
								</span>
						
							        <input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" />
								</form>
							 </div>
						</div>
			
			
			  <form id="myForm" action="delBuildingNo.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
						<th>id</th>
						<th>菜单名</th>
						<th>菜单等级</th>
						<th>上一级菜单</th>
					</tr>
					<c:forEach var="m" items="${modules }">
						<tr style="text-align: center;">
							<td>${m.id }</td>
							<td>${m.name }</td>
							<td>
							<c:if test="${m.levels==0 }"></c:if>
							<c:if test="${m.levels==1 }">一级菜单</c:if>
							<c:if test="${m.levels==2 }">二级菜单</c:if>
							</td>
							
							<td>${m.parentModule.name }</td>
							
						</tr>
					</c:forEach>
				</table>

				
			</form>
		</div>
	</div>
	<br />
	<br />
</c:if>
<c:if test="${tabNo==2 }">
			<div class="row-fluid">
							<div class="span2 search-input" style="width: 100%;" >
								<br/>
								 <form method="post" id="searchForm" action="toModuleBtn.do">
		
			<script type="text/javascript">
				
				/*获取二级菜单的值  */
				function getLevel2(value){
					
					var level2=$(".level2Btn").val();
					var url="toModuleBtn.do?level1="+value+"&level2="+level2+"&op=dropdown";
					
					location.href=url;
				}
			
			</script>
			<input type="hidden" class="level1Btn" value="${level1 }"/>
			<input type="hidden" class="level2Btn" value="${level2 }"/>
								
								<span class="" >
								一级菜单:&nbsp; <select name="level1" class="" onchange="getLevel2(this.value)" 
								style="width: 150px;">
								
								<c:forEach var="m" items="${level1Modules}">
									<c:if test="${level1==m.id }">
										<option value="${m.id }" selected="selected">${m.name }</option>
									</c:if>
									<c:if test="${level1!=m.id }">
										<option value="${m.id }">${m.name }</option>
									</c:if>
								</c:forEach>
								</select> &nbsp;&nbsp;&nbsp;&nbsp;
								</span>
						
								<span class="" >
								二级菜单:&nbsp; <select name="level2" class=""  
								style="width: 150px;">
								<c:if test="${fn:length(level2Modules)==0}">
									<option value="-1" selected="selected">无</option>
								</c:if>
								 
								<c:forEach var="m" items="${level2Modules}">
									<c:if test="${level1==m.id }">
										<option value="${m.id }" selected="selected">${m.name}</option>
									</c:if>
									<c:if test="${level1!=m.id }">
										<option value="${m.id }">${m.name }</option>
									</c:if>
								</c:forEach>
								</select> &nbsp;&nbsp;&nbsp;&nbsp;
								</span>
									<input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" />
								</form>
							 </div>
							 
						</div>
			
			
			  <form id="myForm" action="delBuildingNo.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
						<th>id</th>
						<th>菜单名</th>
						<th>菜单等级</th>
						<th>上一级菜单</th>
					</tr>
					<c:forEach var="m" items="${modules }">
						<tr style="text-align: center;">
							<td>${m.id}</td>
							<td>${m.name }</td>
							<td>
							<c:if test="${m.levels==0 }"></c:if>
							<c:if test="${m.levels==1 }">一级菜单</c:if>
							<c:if test="${m.levels==2 }">二级菜单</c:if>
							</td>
							
							<td>${m.parentModule.name }</td>
							
						</tr>
					</c:forEach>
				</table>

				
			</form>
		</div>
	</div>
	<br />
	<br />
</c:if>

</body>
</html>