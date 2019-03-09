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
<%--<link rel="stylesheet" href="css/page.css" type="text/css"></link>--%>
<%--<script type="text/javascript" src="js/jquery-1.8.3.js"></script>--%>
<%--<script type="text/javascript" src="js/page.js"></script>--%>
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
			$.jBox.tip("请选择要修改的公租房房源！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个公租房房源！");
			return;
		}
		layer.ready(function(){ 
			  layer.open({
			    type: 2,
			    title: '修改房源信息',
			    
			    maxmin: false,
			    area: ['510px', '580px'],
			   
			    content:'toPrHouseEdit.do?id='+id,
			    
			    end: function(){
			    	location.reload();
			        	
			    	
			    }
			  });
			});
	}
	function toAutoAdd(){
		 layer.open({
			    type: 2,
			    title: '批量导入房源',
			    maxmin: false,
			    area: ['534px', '576px'],
			    content:'toPrHouseAutoAdd.do',
			    end: function(){
			    	location.reload();
			    }
			  });
	}
	
	function toAdd(){
		layer.ready(function(){
            var currPage= layer.open({
			    type: 2,
			    title: '新增房源信息',
			    maxmin: false,
			    area: ['510px', '580px'],
			    content:'toPrHouseAdd.do?estateId=${estateId}',
			    end: function(){
			    	 location.reload();
			    }
			  });
            // layer.full(currPage);
        });
		
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
			$.jBox.tip("请选择要删除的房源！");
			$("#myForm").submit(function(){
				return false;
			});
			return;
			
     	}
	    if (!confirm("确认删除吗？")) {
	    	$("#myForm").submit(function(){
				return false;
			});
	    	return;
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
					  action="findPrHouseByEstatePaged.do">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					<div class="row-fluid">
						<div class="span2 search-input" style="width: 100%;height: 25px;margin-bottom: 10px;" >
								&nbsp;&nbsp;&nbsp;
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
							</select> &nbsp;&nbsp;&nbsp;
									
									
									
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
								
								
							</select> &nbsp;&nbsp;&nbsp;
							
						
									
							楼层:&nbsp;&nbsp; <select name="floorId" class="floorId" 
								style="width: 90px;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${floors}">
									<c:if test="${condition.floor==f.id }">
										<option selected="selected" value="${f.id}">${f.name}</option>
									</c:if>

									<c:if test="${condition.floor!=f.id }">
										<option value="${f.id }">${f.name }</option>
									</c:if>
								</c:forEach>
							</select> &nbsp;&nbsp;&nbsp;
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
							</select> &nbsp;&nbsp;&nbsp;
							
							
							房源类型 :&nbsp;&nbsp; <select name="houseNature" class="houseNature" 
								style="width: 90px;">
								<option value="-1">全部</option>
								
								<option value="1" <c:if test="${houseNature==1}">selected="selected"</c:if>>新房源</option>
								<option value="2" <c:if test="${houseNature==2}">selected="selected"</c:if>>退租房源</option>
								
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
			<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
				<tr >	
					<th style="width: 40px;padding-top: 9px;">
					  <input type="checkbox"  class="chkAll" id="chkAll" /><label for="chkAll" style="font-weight: bold;font-size: 17px;">选择</label>
				   </th>
						<th>楼号</th>
						<th>单元</th>
						<th>楼层</th>
						<th>房号</th>
						<th>房型</th>
						<th>面积</th>
						<th>地下室编号</th>
						<th>房间</th>
						<th>月租金</th>
						<th>房源状态</th>
                        <th>门锁网关ID</th>
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
				
				
				<c:forEach var="b" items="${page.list }">
					<tr style="text-align: center;">
						<td><input type="checkbox" name="chk" class="chk" value="${b.id}"/> </td>
						
						<td>${b.buildingNo.buildingId}号楼</td>
						<td>${b.buildingNo.name} </td>
						<td>${b.buildingFloor.name}</td>
						<td>${b.roomNo }</td>
						<td>${b.roomType.name}</td>
						<td>${b.area}</td>
						<td>${b.backPrice}</td>
						<td>${b.roomNum}</td>
						<td>${b.monthPrice}</td>
						<c:set var="stateId" scope="request" value="${b.state }"></c:set>
						<td ><input class="stateHidden" type="hidden" value="${stateId}"/>
						<%=request.getAttribute("stateId")==null?null: MyConvertUtil.getHouseState(Integer.valueOf(request.getAttribute("stateId").toString())) %> 
						 </td>
                        <td>${b.associatedlock}</td>
						<td>${b.remark}</td>
						
					</tr>
				</c:forEach>
				
			</table>
			
		<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>
			
			
			 
			 				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="新 增" type="button" onclick="toAdd()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==100}">
						
					<input class="btn" value="新 增" type="button" onclick="toAdd()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
		    
			 

				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="修 改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==101}">
						
					<input class="btn" value="修 改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
		    
	
			 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="删除" type="submit" onclick="del()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==102}">
						
					<input class="btn" value="删除" type="submit" onclick="del()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 	
	
			 
			 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="批量导入" type="button" onclick="toAutoAdd()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==103}">
						
					<input class="btn" value="批量导入" type="button" onclick="toAutoAdd()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 <script type="text/javascript">
				function lockHouse(isLock){
					var chk= $(".chk");
					var count=0;
					 var ids = new Array();
					 var canDo=true;
					//循环
					chk.each(function(e,q){
						var flag= $(q).is(":checked");
						
						if(flag){
							count++;
							ids[count-1]=$(q).val();
							//状态判断
							var state= $(q).parents("tr").find(".stateHidden").val();
							if (isLock==1&&state!=6) {
								canDo=false;
								return ;
							}
							if (isLock==0&&state!=4	) {
								canDo=false;
								return;
							}
							
						}	
					});
					if(count==0&&isLock==1){
						$.jBox.tip("请选择要锁定的公租房房源！");
						return;
					}
					if(count==0&&isLock==0){
						$.jBox.tip("请选择要解锁的公租房房源！");
						return;
					}
					
					if (canDo==false&&isLock==1) {
						$.jBox.tip("只有可租状态的房源可以加锁！");
						return;
					}
					if (canDo==false&&isLock==0) {
						$.jBox.tip("只有锁定状态的房源可以解锁！");
						return;
					}
					
					
					layer.ready(function(){ 
						  layer.open({
						    type: 2,
						    title: '房源锁定',
						    maxmin: false,
						    area: ['494px', '256px'],
						    content:'toLockHouse.do?houseIds='+ids+"&isLock="+isLock,
						    end: function(){
						    	location.reload();
						    }
						  });
					});
				}
			 	
			</script>			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="锁定" type="button" onclick="lockHouse(1)" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==104}">
						
					<input class="btn" value="锁定" type="button" onclick="lockHouse(1)" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			  
			  
			  
			  
			  
			 
			 
			 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<input class="btn" value="解锁" type="button" onclick="lockHouse(0)" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==105}">
						
					<input class="btn" value="解锁" type="button" onclick="lockHouse(0)" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 </script>

			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" type="button" value="房源导出" onClick="exportHouse();">&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==73}">
					<input class="btn" type="button" value="房源导出" onClick="exportHouse();">&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>	 
			 
			</form>	  	
		</div>
	
	<script type="text/javascript">
	
	function exportHouse(){
		location.href="houseReport.do?estateId=${estateId}&buildingId=${condition.buildingId}&buildingNoId=${condition.buildingNoId}&floor=${condition.floor}&typeId=${condition.typeId}&houseNature=${houseNature}";
	}
	
	function WriteSetRoomNumberCard()
	{
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
			$.jBox.tip("请选择要一个的公租房房源！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个公租房房源！");
			return;
		}
		  $.ajax({
				url : "getRoomNumberInfo.do?houseId="+id+"&name="+new Date(),
				success : function(results) {
					
					str1 = JSobj.WriteSetRoomNumberCard(results[0],results[1],results[2],results[3],results[4],'0');	
					var info = eval('(' + str1+ ')');
					var result = info.Result;
					if (result != 1) {
						$.jBox.tip("房号卡写入失败！");
					}else{
						$.jBox.tip("房号卡写入成功！");
					}
				}
		  });
		
	}
	
	
	function WriteSetRoomIdCard()
	{
		
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
			$.jBox.tip("请选择要一个的公租房房源！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个公租房房源！");
			return;
		}
	
		  $.ajax({
				url : "getRoomNumberInfo.do?houseId="+id+"&name="+new Date(),
				success : function(results) {
				
					str1 = JSobj.WriteSetRoomIdCard(results[0],results[1],results[5]);
				
					var info = eval('(' + str1+ ')');
					var result = info.Result;
					if (result != 1) {
						$.jBox.tip("锁号卡写入失败！");
					}else{
						$.jBox.tip("锁号卡写入成功！");
					}
				},error:function(r){
					alert("error");
				}
		  });
	}
	
	</script> 	 
</body>
</html>