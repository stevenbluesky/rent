<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>用户管理</title>
<style type="text/css">
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
			location.href="findBuildingByEstatePaged.do?estateId="+estateId+"&currpage=1";
		});
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
	
	function toEdit(){
		var chk= $(".chk");
		var count=0;
		var id=null;
		//循环
		
		chk.each(function(e,q){
			var flag= $(q).is(":checked");
			if(flag){
				id=$(q).val();
				count++;
			}	
		});
		if(count==0){
			$.jBox.tip("请选择要修改的楼号！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个楼号！");
			return;
		}
	 
	 	location.href="toEditBuilding.do?id="+id;
		
	}

 
	function openCard() {
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环

		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				count++;
			}
		});
		
		if (count == 0) {
			$.jBox.tip("请选择要开卡的楼号！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个楼号！");
			return;
		}

		location.href = "toOpenBuildingNoCard.do?id=" + id+"&estateId=${estateId}";
	}


	
	
	function del(){
		var count=0;
		   var chk= $(".chk");
		//循环
		chk.each(function(e,q){
			var flag= $(q).is(":checked");
			if(flag){
				
				count++;
			}	
			
		});
	    if(count==0){
			$.jBox.tip("请选择要删除的楼号！");
			$("#myForm").submit(function(){
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
</script>
</head>
<body>

	<!--第一种形式-->
	
<div class="row-fluid">
	<div class="span4" style="width: 100%">
		
		<ul class="nav nav-tabs" style=""  >
					
					<c:forEach var="e" items="${estates}" varStatus="status">
						<c:if test="${estateId==e.id }" >
							<li class="active"><a href='findBuildingByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
						<c:if test="${estateId!=e.id  }">
							<li ><a href='findBuildingByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
						<%-- <c:if test="${status.count%3==0 }">
							</ul>
								<ul class="nav nav-tabs" >
						</c:if>  --%>
						
				</c:forEach>
				
		</ul>
		</div>
		</div>
		
		<div id="tabs0" >
		<div class="main" id="main0" >
			
			<form id="myForm" action="delBuilding.do" method="post">
			<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
			<tr >
					<th ><input type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/>选择 </th>
						<th>楼号名称</th>
						<th>单元列表</th>
						<th>所属物业</th>

						<th>备注</th>
				</tr>							 
				
				<c:forEach var="b" items="${page.list }">
					<tr style="text-align: center;">
						<td><input type="checkbox" name="chk" class="chk" value="${b.id}"/> </td>
						<td>${b.name }号楼</td>
						<td>${b.buildingnocode }</td>
						<td>${b.estate.name }</td>
						
						<td>${b.remark }</td>
						
					</tr>
				</c:forEach>
				
			</table>
			
			<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>
			
			
			
			
			
			 <input class="btn" value="新 增" type="button"
				onclick="location.href='toBuildingAdd?estateId=${estateId}';" />
				
		      <input class="btn" value="修 改" type="button"
				onclick="toEdit()" />
		     <input class="btn" value="删除" type="submit"
				onclick="del()" />
			
			<input class="btn" value="开单元卡" type="button" onclick="openCard()" />
			 </form>	  	
		</div>
	</div>
	<br />
	<br />
			 
</body>
</html>