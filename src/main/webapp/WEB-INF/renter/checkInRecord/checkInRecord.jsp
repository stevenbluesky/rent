<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title></title>
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
	#no1{
		width: 130px;
		font-size:20px;
		text-align: center;
	}	
	#no2{
		width: 130px;
		font-size:20px;
		
		text-align: center;
	}	
	</style>

<script type="text/javascript">
		

		$(document).ready(function() {
			/* 默认选项卡设置 */
			$(".addOthers").hide();
			chooseDiv(<%=request.getAttribute("tabNo")%>);
			
			 /* 操作后的提示语 */
			if (<%=request.getAttribute("tip")!=null%>) {
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			   }
			$(".chkAll").click(function(){
				chk(); //全选全不选
		 });
			
			/*删除非空验证  */
			$("#delReletApplyForm").submit(function(){
				 var op= $(".opHidden").val();
				var chk= $(".chk");
				var count=0;
				 var ids = new Array();
				 var canDoConfirm=true;
				 var canDoDel=true;
				//循环
				chk.each(function(e,q){
					var flag= $(q).is(":checked");
					if(flag){
						count++;
						ids[count-1]=$(q).val();
						//状态判断
						var state= $(q).parents("tr").find(".stateHidden").val();
						
						if (op==1&&state!=8) {
							canDoConfirm=false;
							return ;
							
						}
						if (op==2&&state!=7) {
							canDoDel=false;
							return;
						}
						
					}	
				});
				
				 if (op==1) {
					 if (count==0) {
						$.jBox.tip("请选择一条单号信息！");
						return false;	
					 }
					 if (!canDoConfirm) {
						 $.jBox.tip("租户尚未签约或已经入住，操作失败！");
						 return false;
					}
					 
				}else{
					
					if (count==0) {
						$.jBox.tip("请选择一条或多条单号信息！");
						return false;	
					}
					if(!confirm("确认删除吗？")){
						return false;
					}	
					if (!canDoDel) {
						$.jBox.tip("该条租户信息不能删除！");
						return false;
					}
				}
				
				return true;
				
			});
		});
		
		
		/*选择选项卡  */
		function chooseDiv(id){
			if (id==1) {
				$("#no2").removeClass("active");
				$("#no1").addClass("active");
				$(".applyDiv").show();
				$(".confirmDiv").hide();
			} else if (id==2) {
			
				$("#no1").removeClass("active");
				$("#no2").addClass("active");
				$(".applyDiv").hide();
				$(".confirmDiv").show();
			}
		}
		
		function chooseTab(tabNo){
			var estateId= $(".estateIdVal").val();
			
			if (tabNo==1) {
				var name1=$(".name1").val();
				var roomNo1=$(".roomNo1").val();
				if (name1==null) {
					name1="";
				}
				if (roomNo1==null) {
					roomNo2="";
				}
				location.href="findReletPaged.do?estateId="+estateId;
			}else{
				var name2=$(".name2").val();
				var roomNo2=$(".roomNo2").val();
				if (name2==null) {
					name2="";
				}
				if (roomNo2==null) {
					roomNo2="";
				}
				location.href="findReletPagedTab2.do?estateId="+estateId;
			}
			
		}
		
		/* 复选框  */
	    function chk(){
			var chkAllFlag= $(".chkAll").attr("checked");
			
			var chks= $(".chk");
			if(chkAllFlag=="checked"){
				$(".chk").attr("checked",true);
			}else{
				$(".chk").attr("checked",false);
			}
		}
	</script>

<script type="text/javascript">

	$(function(){
		$(".form-search").click(function(){
			check();
		});
		$(".form-search").submit(function(){
			return check();
		});
		chk();
		
	});
	
	
	function renterInfo(){
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环
		var state=-1;
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				state= $(q).parents("tr").find(".stateHidden").val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择要查看的记录！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多条记录！");
			return;
		}
			layer.ready(function(){ 
				var index=  layer.open({
				    type: 2,
				    title: '租户信息',
				    maxmin: false,
				    area: ['1200px', '900px'],
				    content:'toRenterInfoByRenter.do?masterId='+id,
				    scrollbar: true,
				    end: function(){
				      location.reload();
				    }
				  });
				  
				  layer.full(index);   
			});
	}
	function toEdit(){
		
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环
		var state=-1;
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				state= $(q).parents("tr").find(".stateHidden").val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择要修改的记录！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多条记录！");
			return;
		}	
		var isLiveIn="";
		if (state!=7) {
			isLiveIn="1";
			
		} 
			var index=layer.open({
		    type: 2,
		    title: '入住登记修改',
		    maxmin: false,
		    area: ['1200px', '660px'],
		    content:'toAddRenter.do?currMasterId='+id+"&isLiveIn="+isLiveIn,
		    end: function(){
		    	location.reload();
		    }
		   
		  });
		layer.full(index);
	}
	
	
	
	
	function attachment(){
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环
		var state=-1;
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				state= $(q).parents("tr").find(".stateHidden").val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择一条业务记录！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多条业务记录！");
			return;
		}	
		
			layer.open({
		    type: 2,
		    title: '电子附件',
		    maxmin: false,
		    area: ['1000px', '560px'],
		    
		    content:'toAttachment.do?masterId='+id,
		    end: function(){
		    	location.reload();
		    }
		   
		  });
		
	}
	
	function toTempMan(){
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环
		var state=-1;
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				state= $(q).parents("tr").find(".stateHidden").val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择一条业务记录！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多条业务记录！");
			return;
		}	
		
			layer.open({
		    type: 2,
		    title: '临时居住人管理',
		    maxmin: false,
		    area: ['1000px', '560px'],
		    
		    content:'toTempMan.do?masterId='+id,
		    end: function(){
		    	location.reload();
		    }
		   
		  });
		
	}
	
	/*退租申请结果  */
	function applyResult(val){
			var masterId=$(".radioId:checked").val();
	    	if (val==1) {
	    	$(".radioId:checked").parent().next().find("input").val("2");
	      	$(".radioId:checked").parent().next().find("span").text("已申请");
	      	
	      	 
	    	
	      	$(".radioId:checked").parents("tr").find(".opBtn").html("<input class='btn' value='修改' type='button' onclick='toApplyEdit("+masterId+")' />"
	      	+"<input class='btn' value='删除' type='button' onclick='delApply("+masterId+")' />");
	      	$.jBox.tip("退租申请成功");	
		   }
	    
	}
	function toConfirmApply(){
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
			$.jBox.tip("请选择要确认的续租申请！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多条续租申请！");
			return;
		}		
		
		layer.open({
		    type: 2,
		    title: '续租确认',
		    
		    maxmin: false,
		    area: ['600px', '300px'],
		    
		    content:'toReletApplyConfirm.do?masterId='+id,
		    end: function(){
		    	location.reload();
		    }
		  });
		
		
	}
</script>


</head>
<body>
	<!--物业  -->
	<div class="container-fluid" style="padding: 0px;">
		<div class="row-fluid">
			<div class="span4" style="width: 100%">
				<ul class="nav nav-tabs">
					<c:forEach var="e" items="${estates}" varStatus="status">
						<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a
									href="findCheckInRecord.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
						</c:if>
						
						<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
								<li class="active"><a
									href="findCheckInRecord.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
							<c:if test="${estateId!=e.id  }">
								<li><a href="findCheckInRecord.do?estateId=${e.id}">${e.name}</a></li>
							</c:if>
						</c:if>
					
					
						
					</c:forEach>
				</ul>
			</div>
			<br/>
	
		<div class="row-fluid repaire-search" style="height: 25px;">
				<!--申请/确认选项卡  -->
				  <form method="post" id="searchForm"  
					  action="findCheckInRecord.do">
					<input type="hidden" name="tabNo" value="${tabNo }">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					<div class="row-fluid">
							<div class="span2 search-input" style="width: 100%;" >
								
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
							
							             房号:&nbsp;&nbsp;<input
										name="roomNo" value="${condition.roomNo }" type="text"
										class="searchTxt roomNo1">&nbsp;&nbsp;&nbsp;&nbsp;
							
							 姓名:&nbsp;&nbsp;<input name="name"
										value="${condition.name }" type="text"
										class="searchTxt name1">&nbsp;&nbsp;    
							        <input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" >
							
							 </div>
						</div>

				</form>
	
		</div>
			
			
			<form id="delReletApplyForm" action="delCheckInRecordForm.do" method="post">
					<!-- 隐藏域存条件 -->			
					<input type="hidden" name="estateId"      value="${estateId }" />
					<input type="hidden" name="roomNo" ${roomNo} >
					<input type="hidden" name="name" class="nameVal"  value="${name}">
					
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;text-align: center;" >
					<tr>
						<th>
						<input type="checkbox"  class="chkAll" style="position: relative;top: 2px;" id="chkAll"/><label for="chkAll" style="font-weight: bold;font-size: 17px;">选择</label>
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
						<th>合同起始时间</th>
						<th>合同结束时间</th>
						<th>备注</th>
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
								<input type="checkbox" name="chk" class="chk" value="${m.id }"/>
								<input type="hidden" value="${m.houseId }" class="houseIdHidden">
								</td>
						<td >
						<input class="stateHidden" type="hidden" value="${m.sta}" >
						<span id="state${m.id}">
									<c:set value="${m.sta}" scope="request" var="sta"></c:set>
							  	   <%=MyConvertUtil.getMasterState(request.getAttribute("sta").toString()) %>
							<%-- <c:set value="${m.prHouse.state}" scope="request" var="state"></c:set>
							
							<c:if test="${m.sta==1 }">
									<%=MyConvertUtil.getHouseState(Integer.parseInt(request.getAttribute("state").toString())) %>								
							</c:if>
							  <c:if test="${m.sta!=1 }">  
							  <c:set value="${m.sta}" scope="request" var="sta"></c:set>
							  	   <%=MyConvertUtil.getMasterState(request.getAttribute("sta").toString()) %>
							  </c:if>  --%>
						  </span>
						  </td>
						  
						  
						  	<td>${m.profile.name}</td>
							<td>
							${m.profile.sex==0?'女':'男'}</td>
							
							<td>${m.profile.idno}</td>
							<td>${m.profile.mobile}</td>
							<td>${m.profile.company}</td>
							
							
							<td>${m.prHouse.buildingNo.buildingId}号楼</td>
							<td>${m.prHouse.buildingNo.unitName}</td>
							<td> ${m.prHouse.roomNo}</td>
 						
 							<td><fmt:formatDate value="${m.bdate }" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${m.edate }" pattern="yyyy-MM-dd" /></td>
							<td>${b.remark}</td>
						

						</tr>
					</c:forEach>

				</table>
				
				
				<script type="text/javascript">
   function print(){
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
			$.jBox.tip("请选择要打印的单号信息！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个单号信息！");
			return;
		}
		 
	   var chk= $(".chk");
		var count=0;
		//循环
		chk.each(function(e,q){
			var flag= $(q).is(":checked");
			if(flag){
				count++;
				//状态判断
				var state= $(q).parents("tr").find(".stateHidden").val();
				if (state!=7&&state!=5) {
					$.jBox.tip("该租户不能打印合同！");
					return false;
				} 
			
				location.href='createContract.do?id='+id+'&name=合同&type=contract';
				
			}	
		});
		return true;
	}
	     function confirmIn(val){
	    	 $(".opHidden").val(val);
	     }
	     
		</script>			
			
		
				<div class="pagination" id="numpage">${numpage}</div>
				<br/>
				<input type="hidden" class="opHidden"   >
				
				
				
				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn"  value="打印合同" type="button" onclick="print()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==14}">
						
					<input class="btn"  value="打印合同" type="button" onclick="print()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			  
<%-- 			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn"  name="op" value="入住确认" type="submit" onclick="confirmIn(1)" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==15}">
						
					<input class="btn"  name="op" value="入住确认" type="submit" onclick="confirmIn(1)" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach> --%>
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn"  value="修改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==16}">
						
					<input class="btn"  value="修改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
			
				
				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn"  name="op" value="删除" type="submit"  onclick="confirmIn(2)" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==17}">
						
					<input class="btn"  name="op" value="删除" type="submit"  onclick="confirmIn(2)" />&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
				

				 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="电子附件" type="button"  onclick="attachment()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==67}">
						
					<input class="btn" value="电子附件" type="button"  onclick="attachment()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
								
						 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="查看租户信息" type="button"  onclick="renterInfo()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==81}">
						
					<input class="btn" value="查看租户信息" type="button"  onclick="renterInfo()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 	
			 	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="临时居住人管理" type="button"  onclick="toTempMan()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==110}">
					<input class="btn" value="临时居住人管理" type="button"  onclick="toTempMan()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
						
						
							
				
				
				
				<!-- &nbsp;&nbsp;&nbsp;&nbsp;
				<input class="btn"  name="op" value="测试打印" type="button" onclick="location.href='printHetong.do'"  /> -->
				</form>
	   </div> 
	
	
</div>
	
	
	


</body>
</html>