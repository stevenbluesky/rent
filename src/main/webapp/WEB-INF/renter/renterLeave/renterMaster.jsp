<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
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
		#stepTip{
			padding:10px 0px 10px;
			background-color:#F4F8FB;
		}
		
		#buildingNo{
			margin:10px 0px 10px;
			padding:10px 0px 10px;
			background-color:#F4F8FB;
		}
		#main{
			padding:10px 10px 10px;
			background-color: #E3E8EC; width: 100%;height: 550px;
		}
		
		#left{
			margin:5px 5px 0px;
			overflow:auto;
			width: 27%;
		}
		#right{
		margin :0px 5px 0px 0px;
		padding:5px 5px 10px;
		width: 69%;
		}
		.buildNoBtn{
			margin:10px 20px 10px;
			height:35px;
			
		}
		.searchTxt{
			width: 76px;
		}
		#houseTable tr td{
			
			text-align: center;
			vertical-align:middle;
			width:50px;
			height: 60px; 
			padding: 5px;
			
		}


	.roomPic{
			color :white;
			font-size:20px;
			font-weight:lighter;
			padding-top:12px;
			width:100%;
			height:100%;
			
			background-repeat: no-repeat;
			background-size:100%;
			padding-right: 0px;
		}

	#left{
		position: relative;
	}
	.buildingH2{
			font-size:16px;
			width: 58px;
			height: 30px;
		}
	.building1-5{
		position: absolute;left: 180px;top: 74px;	
		
	}
	.building2-6{
		position: absolute;left: 270px;top: 74px;	
		
	}
	.building6-10{
		position: absolute;left: 231px;top: 192px;	
		
	}		
	</style>

<script type="text/javascript">
		$(document).ready(function() {

			 /* 操作后的提示语 */
			if (<%=request.getAttribute("tip")!=null%>) {
				
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			}
		});
		
	</script>
	

<script type="text/javascript">

	$(function(){
		$(".form-search").click(function(){
			check();
		});
		$(".form-search").submit(function(){
			return check();
		});
		
	});

	function repaireApply(){
		var id= $(".radioId:checked").val();
		
		var state= $(".radioId:checked").parent().next().find("input").val();
		
		if (state!=4) {//续租可以申请
			$.jBox.tip("该房间尚未退租！");
			return;
		}
		
		if (id==undefined) {
			$.jBox.tip("请选择一条租房信息！");
			return;
		} 
		
		layer.open({
		    type: 2,
		    title: '报修申请',
		    
		    maxmin: false,
		    area: ['600px', '590px'],
		    content:'toRepaireApply.do?masterId='+id,
		    end: function(){
		    	
		    }
		  });
	}
	
	function repaireApplyResult(){
		$.jBox.tip("报修申请成功！");
		
	}
	function toApply(){
		var id= $(".radioId:checked").val();
		var state= $(".radioId:checked").parent().next().find("input").val();
		
		if (state!=1&&state!=6) {//续租可以申请
			$.jBox.tip("该房间不能申请退租！");
			return;
		}
		if (id==undefined) {
			$.jBox.tip("请选择一条租房信息！");
			return;
		}
		layer.open({
		    type: 2,
		    title: '退租申请',
		    
		    maxmin: false,
		    area: ['600px', '400px'],
		    content:'toRenterLeave.do?masterId='+id,
		    end: function(){
		    	
		    }
		  });
	}
	
	/*打印单据  */
	function print(){
		var id= $(".radioId:checked").val();
		var state= $(".radioId:checked").parent().next().find("input").val();
		if (id==undefined) {
			$.jBox.tip("请选择一条租房信息！");
			return;
		}
		/* if (state!=3) {//续租可以申请
			$.jBox.tip("该房间尚未验房！");
			return;
		} */
		if (state!=4) {//续租可以申请
			$.jBox.tip("该房间尚未退租！");
			return;
		}
		location.href='leavePrint.do?id='+id+'&name=退租单&type=leave';
	}
	
	
	
	function  applyPrintResult(val){
		var masterId=$(".radioId:checked").val();
		if (val==1) {
	    	$(".radioId:checked").parent().parent().html("");
	      	
		}
	}
	
	
	
	function toConfirmLeave(){
		var id= $(".radioId:checked").val();
		var state= $(".radioId:checked").parent().next().find("input").val();
		if (id==undefined) {
			$.jBox.tip("请选择一条租房信息！");
			return;
		}
		if (state==4) {
			$.jBox.tip("该房间已退租！");
			return;
		} 
		if (state!=2) {
			$.jBox.tip("请先申请退租！");
			return;
		}
		if (!confirm("确认退租吗?")) {
			return;
		}
		
		var url="confirmLeave.do";
		var data="masterId="+id;
		
			$.post(url,data,function(result){
				
				if (result==1) {
					$.jBox.tip("退租确认成功！");	
					/* $(".radioId:checked").parents("tr").html(""); */
					$(".radioId:checked").parent().next().find("input").val("4");
			      	$(".radioId:checked").parent().next().find("span").html("已退租");
				}else if (result==0) {
					$.jBox.tip("账务尚未结清！");
				}else if(result==2){
					$.jBox.tip("其他操作员已操作！");
					$(".radioId:checked").parent().next().find("input").val("4");
			      	$(".radioId:checked").parent().next().find("span").html("已退租");
				}
		});
	}
	
	/*退租申请结果  */
	function applyResult(val){
			var masterId=$(".radioId:checked").val();
	    	if (val==1) {
	    	$(".radioId:checked").parent().next().find("input").val("2");
	      	$(".radioId:checked").parent().next().find("span").html("<span style='color:red;'>退租</span>申请中");
	      	
	      	 
	    	
	      	$(".radioId:checked").parents("tr").find(".opBtn").html("<input class='btn' value='修改' type='button' onclick='toApplyEdit("+masterId+",0)' />"
	      	+"<input class='btn' value='删除' type='button' onclick='delApply("+masterId+")' />");
	      	$.jBox.tip("退租申请成功");	
		   }
	    
	}
	
	function updateState(){
	     var state="";
		 var state1= $(".state1").is(':checked') ;
		 var state2= $(".state2").is(':checked') ;
		 if (state1&&!state2) {
			state=1;	
		 }
		 if (state2&&!state1) {
				state=2;	
		  }
		 if (state1&&state2) {
			state="";
		}
		
		 
		var estateId=  $(".estateIdVal").val();
		
		var buildingNoId= $(".buildingNoIdVal").val();
		var name=  $(".nameVal").val();
		var company=  $(".companyVal").val();
      	var leaveDate=  $(".leaveDateVal").val();
      	var url="findMasterByCondition.do?estateId="+estateId+"&buildingNoId="+buildingNoId+"&name="+name+"&company="+company+"&leaveDate="+leaveDate+"&sta="+state;
		location.href=url; 
	}
	/* 申请验房修改  */
	function toApplyEdit(master,src){
		
		if (src==1) {
			$.jBox.tip("租户账务已结清，不能修改");
			return;
		}
		layer.open({
		    type: 2,
		    title: '退租申请',
		    maxmin: false,
		    area: ['600px', '400px'],
		    content:'toRenterLeaveEdit.do?masterId='+master,
		    end: function(){
		    	
		    }
		  });
	}
	
	/*退租申请结果  */
	function applyResultEdit(val){
	     $.jBox.tip("退租申请修改成功");	
	}
	/* 删除 */
	function delApply(masterId,src){
	
		if (src==1) {
			$.jBox.tip("租户账务已结清，不能删除");
			return;
		}
		if(confirm("确认删除吗")){
			var url="delApply.do";
			var data="masterId="+masterId;
			$.post(url,data,function(data){
				
				var id=data.split('-')[0];
				var state=data.split('-')[1];
				
				var str="#state"+id;
				$(str).parent().find("input").val(1);
		      	$(str).text(state);
		      	$(str).parents("tr").find(".opBtn").html("");	
				$.jBox.tip("退租申请删除成功");
				
			});
		}
	}
	
</script>


</head>
<body>

	<ul  class="menu0" id="menu0">
	</ul>
	<div class="container-fluid" style="padding: 0px;">
		<div class="row-fluid">
			<div class="span4" style="width: 100%">
				<ul class="nav nav-tabs">
					<c:forEach var="e" items="${estates}" varStatus="status">
						<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a
								href="findMasterByCondition.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
						</c:if>
						
						<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
								<li class="active"><a
								href="findMasterByCondition.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
							<c:if test="${estateId!=e.id  }">
								<li><a href="findMasterByCondition.do?estateId=${e.id}">${e.name}</a></li>
							</c:if>
						</c:if>
						
						
					</c:forEach>
				</ul>
			</div>
			
		</div>
		
		<div class="repaire-search">

				<form method="post" id="searchForm"  
					action="findMasterByCondition.do">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					
					<div class="row-fluid repaire-search" style="margin-bottom:0px;height: 25px;">
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
							
							  房号:&nbsp;&nbsp;<input name="roomNo"
								value="${condition.roomNo }" type="text"
								class="searchTxt minPrice">&nbsp;&nbsp;&nbsp;&nbsp;
							
							
							
						 姓名:&nbsp;&nbsp;<input name="name"
								value="${condition.name }" type="text"
								class="searchTxt minPrice">&nbsp;&nbsp;&nbsp;&nbsp;
					       
					   
					
			&nbsp;&nbsp;&nbsp;&nbsp;  
			
		   		        <input
								type="submit" id="checkRepaire" class="btn"
								style="margin-right: 10px;" value="条件搜索">
								
			&nbsp;&nbsp;&nbsp;&nbsp;  状态：  
			  <input  type="checkbox" value="1"  onclick="updateState()"   class="state1"  id="zhengchang"
			       <c:if test="${sta==1||sta==null}">checked='checked'</c:if> ><label for="zhengchang">正常</label>
			  <input type="checkbox" value="2"  onclick="updateState()" id="tuizu" 
				  <c:if test="${sta==2||sta==null}">checked='checked'</c:if>  class="state2"
				     
				     ><label for="tuizu">退租/申请</label>&nbsp;&nbsp;&nbsp;
						</div>

				</form>
			</div>
		
		
	
		<div class="main" id="main0" style="margin: 0px;">
			<form id="myForm" action="prHouseDel.do" method="post">
					<!-- 隐藏域存条件 -->			
					<input type="hidden" name="estateId"      value="${estateId }" />
					<input type="hidden" name="buildingNoId" class="buildingNoIdVal"  value="${buildingNoId}">
					<input type="hidden" name="name" class="nameVal"  value="${name}">
					<input type="hidden" name="company"  class="companyVal" value="${company}">
					<input type="hidden" name="leaveDate" class="leaveDateVal"  value="${leaveDate}">
		
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;text-align: center;" >
					<tr>
						<th>
							
						</th>
						<th>状态</th>
						<th>姓名</th>
						<th>性别</th>
						<th>证件号码</th>
						<th>电话号码</th>
						<th>单位</th>
						<th>楼号</th>
						<th>单元</th>
						<th>房号</th>
						<th>备注</th>
						<th>操作（已申请）</th>
					</tr>
						<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="12">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>
					<c:forEach var="m" items="${page.list }" varStatus="status">
						<tr style="text-align: center;">
							<td>
								<input type="radio" name="id" class="radioId"
								   <c:if test="${status.index==0 }"> checked="checked"</c:if> 
										value="${m.id}" />
								
								</td>
						<td>
						<input type="hidden" value="${m.sta}" >
						<span id="state${m.id}">
							<c:set value="${m.prHouse.state}" scope="request" var="state"></c:set>
							
							<%-- <c:if test="${m.sta==1 }">
									<%=MyConvertUtil.getHouseState(Integer.parseInt(request.getAttribute("state").toString())) %>								
							</c:if> --%>
							  <%-- <c:if test="${m.sta!=1 }"> --%>
							  <%-- </c:if> --%>
							  <!--判断是否有续租  -->
							  <c:if test="${m.type!=null&&m.type=='1'}">
							  	   <%=MyConvertUtil.getMasterState("5") %>
							  </c:if>
							  <c:if test="${m.type==null}">
							  		<c:set value="${m.sta}" scope="request" var="sta"></c:set>
							  	   <%=MyConvertUtil.getMasterState(request.getAttribute("sta").toString()) %>
							  </c:if>  
							  	
						  </span>
						  </td>
						  
						  
						  	<td>${m.profile.name}</td>
							<td>
							${m.profile.sex==0?'女':'男'}</td>
							
							<td>${m.profile.idno}</td>
							<td>${m.profile.mobile}</td>
							<td>${m.profile.company}</td>
							<td>${m.prHouse.buildingNo.buildingId} 号楼</td>
							<td>${m.prHouse.buildingNo.unitName}</td>
							<td> ${m.prHouse.roomNo}</td>
							
					     	<td>${b.remark}</td>
							<td class="opBtn">
								<c:if test="${m.sta==2}">
								
				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="修改" type="button" onclick="toApplyEdit(${m.id},${m.src!=null?m.src:0})" />
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="mo" items="${role.moduleList }"><c:if test="${mo.id==35}">
						
					<input class="btn" value="修改" type="button" onclick="toApplyEdit(${m.id},${m.src!=null?m.src:0})" />
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="删除" type="button" onclick="delApply(${m.id},${m.src!=null?m.src:0})" />
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="mo" items="${role.moduleList }"><c:if test="${mo.id==35}">
						
					<input class="btn" value="删除" type="button" onclick="delApply(${m.id},${m.src!=null?m.src:0})" />
			 </c:if></c:forEach></c:if></c:forEach>
								
							    </c:if>	
							</td>

						</tr>
					</c:forEach>

				</table>

				<br />
				<div class="pagination" id="numpage">${numpage}</div>
				
				<br /> 
				
				
				
				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn"  value="申请退租" type="button" onclick="toApply()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="mo" items="${role.moduleList }"><c:if test="${mo.id==35}">
						
					<input class="btn"  value="申请退租" type="button" onclick="toApply()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="确认退租" type="button" onclick="toConfirmLeave()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="mo" items="${role.moduleList }"><c:if test="${mo.id==36}">
						
					<input class="btn" value="确认退租" type="button" onclick="toConfirmLeave()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="打印单据" type="button" onclick="print()" /> &nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="mo" items="${role.moduleList }"><c:if test="${mo.id==37}">
						
					<input class="btn" value="打印单据" type="button" onclick="print()" /> &nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="报修申请" type="button" onclick="repaireApply()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==38}">
						
					<input class="btn" value="报修申请" type="button" onclick="repaireApply()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
			</form>
		</div>
	</div>



</body>
</html>