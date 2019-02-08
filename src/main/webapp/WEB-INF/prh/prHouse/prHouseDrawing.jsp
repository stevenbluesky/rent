<%@page import="com.rent.entity.Building"%>
<%@page import="java.util.List"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<style type="text/css">
.tooltips{ border-width: 1px; border-style: solid; position: absolute; display: none; border-radius: 3px; opacity: 0; filter:alpha( opacity = 0) ; z-index: 999;}
.tooltips p.content{ padding: 5px; }
.tooltips .triangle-front,.tooltips .triangle-back{ width: 0; height: 0; overflow: hidden; border-width: 8px; border-style: solid; position: absolute; border-color: transparent ; top: 100%; left: 50%; margin-left: -8px;}
.tooltips .triangle-back{ margin-top: -1px;}

.yellow{ border-color: #c7c08d; background-color: #fffac3;}
.yellow .triangle-front{ border-top-color: #c7c08d;}
.yellow .triangle-back{ border-top-color: #fffac3;}

</style>

	<title>房间选择</title>
	<!--  权限判断     -->
<c:set var="canDo" value="0"></c:set>
<c:set var="canLook" value="0"></c:set>
<c:forEach var="role" items="${user.rolesList}">
	<c:if test="${role.id==1 }">
		<c:set var="canDo" value="1"></c:set>
		<c:set var="canLook" value="1"></c:set>

	</c:if>
	<c:if test="${role.id!=1 }">
		<c:forEach var="m" items="${role.moduleList }">
			<c:if test="${m.id==65}">
				<c:set var="canDo" value="1"></c:set>
			</c:if>
			<c:if test="${m.id==66}">
				<c:set var="canLook" value="1"></c:set>
			</c:if>

		</c:forEach>
	</c:if>
</c:forEach>


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
			padding:0px 0px 0px;
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
		
		
		#houseList tr td{
			
			padding-top: 10px;
			padding-bottom: 10px;
			
		}
		
	</style>

<script type="text/javascript">
		$(document).ready(function() {
			$("#choose").click(function(){
				
			})
		});
		function page(n,s) {
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/repaire/check");
			$("#searchForm").submit();
	    	return false;
	    }
</script>
	

<script type="text/javascript">
	function check(){
		var minPrice= $(".minPrice").val();
		var maxPrice=$(".maxPrice").val();
		var minArea=$(".minArea").val();
		var maxArea=$(".maxArea").val();
		
		if(isNaN(minPrice)||isNaN(maxPrice)){
			$.jBox.tip("租金必须为数字！");
			return false;
		}
		if(isNaN(minArea)||isNaN(maxArea)){
			$.jBox.tip("面积必须为数字！");
			return false;
		}
		parseInt(minPrice);
		return true;
	}
	
	
	/* 选择房间号很据状态选择操作 */
	function chooseOperation(id,state){
		/* 进入登记 */
		var canDo= <%=pageContext.getAttribute("canDo")%>;
		var canLook=<%=pageContext.getAttribute("canLook")%>;
		
		
		if (state==6) {
			if (canDo==0) {
				$.jBox.tip("权限不足！");
				return;
			}
			
			layer.ready(function(){ 
				  var currPage= layer.open({
				    type: 2,
				    title: '入住登记',
				    maxmin: false,
				    area: ['1200px', '638px'],
				    content:'toAddRenter?houseId='+id,
				    scrollbar: true,
				    end: function(){
				    	 location.reload();
				    }
				  });
				  layer.full(currPage);  
				  
				});
			/* 显示入住人信息 */
		}else if(state==1|| state==5||state==2||state==3||state==8){
			if (canLook==0) {
				$.jBox.tip("权限不足！");
				return;
			}
			layer.ready(function(){ 
				var index=  layer.open({
				    type: 2,
				    title: '租户信息',
				    maxmin: false,
				    area: ['1200px', '900px'],
				    content:'toRenterInfo?houseId='+id,
				    scrollbar: true,
				    end: function(){
				      location.reload();
				    }
				  });
				  layer.full(index);   
				});
		}else if(state==4){
			
			var url="getLockedInfo.do?houseId="+id;
			$.post(url,function(house){
				var info="             该房间已被锁定！\n\n             锁定原因：     "+house[0]+"\n\n             锁定人：     "+house[1]+"\n\n             锁定时间：     "+house[2];
				alert(info);
			});
			
		}else if(state==7){
			$.jBox.tip("房源正在维修！");
		}
		
	}
	$(function(){
		$(".form-search").click(function(){
			check();
		});
		$(".form-search").submit(function(){
			return check();
		});
		
		checkSearchForm();
	});
	function checkSearchForm(){
		
		$("#searchForm").submit(function(){
			var minArea= $(".minArea").val();
			var maxArea= $(".maxArea").val();
			var minPrice=$(".minPrice").val();
			var maxPrice=$(".maxPrice").val();

			if (isNaN(minArea)||isNaN(maxArea)||isNaN(minPrice)||isNaN(maxPrice)) {
				$.jBox.tip("面积或租金必须为数字！");
				return false;
			}
			if (minArea.length!=0&&maxArea.length==0||minArea.length==0&&maxArea.length!=0) {
				$.jBox.tip("面积区间输入不完整！");
				return false;
			}
			if (minPrice.length!=0&&maxPrice.length==0||minPrice.length==0&&maxPrice.length!=0) {
				$.jBox.tip("租金区间输入不完整！");
				return false;
			}
			if (parseFloat(minArea)>parseFloat(maxArea) ) {
				$.jBox.tip("面积区间不正确！");
				return false;
			}
			if (parseFloat(minPrice)>parseFloat(maxPrice) ) {
				$.jBox.tip("租金区间不正确！");
				return false;
			}
			
		});
		
	}
</script>
<style type="text/css">
	.roomPic{
			color :white;
			font-size:20px;
			font-weight:lighter;
			padding-top:12px;
			width:100%;
			height:100%;
			
			
			background-repeat:no-repeat;
			
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
	

</style>
</head>
<body >

<div class="BigDiv" style="width: 97%;height: 700px;">
        <div class="row-fluid" style="width: 100%;">
	        <div class="span4"  style="width: 100%">
				<ul class="nav nav-tabs">
					<c:forEach var="e" items="${estates}" varStatus="status">
						<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a href="prHouseDrawing.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
						</c:if>
						
						<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
								<li class="active"><a href="prHouseDrawing.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
							<c:if test="${estateId!=e.id  }">
								<li ><a href="prHouseDrawing.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
						</c:if>
						
				</c:forEach>
				</ul>
			</div>
		
		<div class="container-fluid row-fluid repaire-search" style="height: 28px;margin-bottom: 8px;" >
			<div class="row-fluid repaire-search" style="margin-bottom:0px;height: 25px;">
			<form method="post" id="searchForm" action="prHouseDrawing">
			  <input type="hidden" value="${buildingNoId}" name="buildingNoId">
			  <input type="hidden" value="${buildingId}" name="buildingId">
			  <input type="hidden" value="${estateId}" name="estateId">
			  
			    
				<div class="row-fluid" >
					<div class="span2 search-input" style="width: 100%">
						楼层:&nbsp;&nbsp;
							<select name="floor" class="floorId" style="width: 70px;">
								<option value="-1">全部</option>
							  	<c:forEach var="f" items="${floors }">
							  		<c:if test="${condition.floor==f.id }">
										<option selected="selected" value="${f.id }">${f.name}</option>
									</c:if>
									<c:if test="${condition.floor!=f.id }">
										<option value="${f.id }">${f.name }</option>
									</c:if>
								</c:forEach>	
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
						房类:
						&nbsp;&nbsp;
						     <select name="typeId" class="typeId" style="width: 100px;">
						     	<option value="-1">全部</option>
								<c:forEach var="t" items="${types }">
									<c:if test="${condition.typeId!=t.id}">
										<option value="${t.id }">${t.name }</option>
									</c:if>
									<c:if test="${condition.typeId==t.id}">
										<option selected="selected" value="${t.id }">${t.name }</option>
									</c:if>
								</c:forEach>		
							  </select>&nbsp;&nbsp;&nbsp;&nbsp;
						
						面积:&nbsp;&nbsp;<input name="minArea" value="${condition.minArea }" type="text" class="searchTxt minArea">&nbsp;&nbsp;到&nbsp;&nbsp;
							<input name="maxArea" value="${condition.maxArea }"  type="text" class="searchTxt maxArea">&nbsp;&nbsp;&nbsp;&nbsp;
							
						租金:&nbsp;&nbsp;<input name="minPrice" value="${condition.minPrice }" type="text" class="searchTxt minPrice">&nbsp;&nbsp;到&nbsp;&nbsp;
							<input name="maxPrice" value="${condition.maxPrice }" type="text" class="searchTxt maxPrice">
								&nbsp;&nbsp;&nbsp;&nbsp;
						
						
							<input type="submit" id="checkRepaire" class="btn" 
							 value="条件搜索" style="margin-right:10px;">
					</div>
					
				</div>
			
				</form>
			</div>

</div>
	</div>
	<div id="main" style="height: 100%;">
		<div id="left" style="width: 31%; overflow: hidden;">				 
			<div id="consle" style="height:100%; overflow: scroll;overflow-x: scroll;border:1px solid #F00;background-color:#eee;margin:0px;padding:0px;">
				<c:if test="${estate.mapPic!=null }">
				 	<img id="image" src="${ctxStatic}/image/building/${estate.mapPic}?timestemp='+new Date().getTime()" style="max-width:2000px !important;" >
				</c:if>
				<c:if test="${estate.mapPic==null }">
				 	<img id="image" src="${ctxStatic}/image/building/white.png" style="height: 100%;width: 100%"  >
				</c:if>
				
				
			</div>
		</div>
		<div id="right" style="width: 60%;">
				
			<div id="stepTip" style="height: 85px; width:890px;margin:10px 0px 10px;	position: absolute;">
			
				<span style="color: #BABCBB;font-size:35px;font-weight:bold;position: relative;top: 30px;left: -15px;"
				">&nbsp;&nbsp;&nbsp;房源统计
				</span>
				
			 	<table style="display: inline;color: #BABCBB;margin-top:2px;position: relative;top: 10px;">
						 	<tr><td>物业统计： </td>
						 		  <td>${eachCount.get(0)[0] }套<span style="color: red;">  可租，</span></td>
						 		  <td>${eachCount.get(0)[1] }套<span style="color: red;">  不可租，</span></td>
						 		  <td>${eachCount.get(0)[2] }套<span style="color: red;">  锁定，</span></td>
						 		  <td>${eachCount.get(0)[3] }套<span style="color: red;">  维修</span></td>
						 	</tr>
						 	<tr ><td>楼号统计： </td>
						 		  <td>${eachCount.get(1)[0] }套<span style="color: red;">  可租，</span></td>
						 		  <td>${eachCount.get(1)[1] }套<span style="color: red;">  不可租，</span></td>
						 		  <td>${eachCount.get(1)[2] }套<span style="color: red;">  锁定，</span></td>
						 		  <td>${eachCount.get(1)[3] }套<span style="color: red;">  维修</span></td>
						 	</tr>
						 	<tr ><td>单元统计： </td>
						 		  <td>${eachCount.get(2)[0] }套<span style="color: red;">  可租，</span></td>
						 		  <td>${eachCount.get(2)[1] }套<span style="color: red;">  不可租，</span></td>
						 		  <td>${eachCount.get(2)[2] }套<span style="color: red;">  锁定，</span></td>
						 		  <td>${eachCount.get(2)[3] }套<span style="color: red;">  维修</span></td>
						 	</tr>
						 	
						 </table> 
			<img src="${ctxStatic}/image/houseState.jpg" style="width: 280px;margin-right:0px;" >
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
			</div>
			
			<c:if test="${buildingNoId==null }">
				<c:set value="1" var="buildingNoId"></c:set>
			</c:if>
			<div id="buildingNo" style="margin-top: 80px;width:890px;">
				
				<c:forEach var="bNo" items="${fns:getAllUnits() }" varStatus="status">
					
					<c:if test="${buildingNoId==bNo }">
						<input type="button"value="&nbsp;&nbsp;${bNo }单元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="buildNoBtn btn" style=" color: red;font-size: 19px;width: 90px;height: 35px;text-align: center;"
						   onclick="location.href='prHouseDrawing.do?estateId=${estateId}&buildingId=${buildingId}&buildingNoId=${bNo}'" >
					</c:if>	
					<c:if test="${buildingNoId!=bNo }">
						<input   type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;${bNo }单元&nbsp;&nbsp;&nbsp;&nbsp;" class="buildNoBtn btn" 
							onclick="location.href='prHouseDrawing.do?estateId=${estateId}&buildingId=${buildingId}&buildingNoId=${bNo}'" style="width: 90px;height: 35px;text-align: center;">
					</c:if>
					<c:if test="${status.count%5==0 }">
						<br/>
					</c:if>
			
				</c:forEach>
			</div>	
		
		
		
			<div id="houseList" style="overflow: scroll;width: 890px;height: 540px;">
			
			<c:if test="${maxRoomNoId<=9}">
				<c:set var="tableWidth" value="${91*9 }" ></c:set>
			</c:if>
			
			<c:if test="${maxRoomNoId>9}">
				<c:set var="tableWidth" value="${91*(maxRoomNoId+1) }" ></c:set>
			</c:if>		
									      
			
					<table id="houseTable" 
						class="table table-striped table-bordered table-condensed"  style="width:${tableWidth}px; table-layout:fixed;word-break:break-all;">
						<tbody>
						
						<c:if test="${maxRoomNoId<=8 }">
							<c:set var="maxColNum" value="8"></c:set>
						</c:if>
						<c:if test="${maxRoomNoId>8 }">
							<c:set var="maxColNum" value="${maxRoomNoId }"></c:set>
						</c:if>
							<%-- <c:forEach begin="1" end="2" var="user">
								<tr>
								<c:forEach begin="1" end="${maxColNum+1 }">
									<td style="width: 90px;">&nbsp;&nbsp;</td>
								</c:forEach>
								</tr>
							</c:forEach> --%>
							
							<!--循环房屋  -->

							<tr>
								<c:set var="numOfRow" value="1"></c:set>
								<c:forEach var="h" items="${houses }">
									<!--楼层数改变，则换行  -->
									<c:if test="${floor!=null&&h.buildingFloorId!=floor }">
										<!-- 补全没有的表格线 -->
										<c:forEach begin="0" end="${maxColNum-numOfRow+1}">
											<td>&nbsp;&nbsp;</td>
										</c:forEach>
							</tr>
							<tr>
						
								<!--当前列号加1  -->
								<c:set var="numOfRow" value="${1 }"></c:set>
								</c:if>
								<!-- 房号和列号是否相同 -->
								<c:set var="isSame" value="${h.roomNoId==numOfRow?1:0 }"></c:set>
								<!--反复判断，当房号等于当前列号时，退出循环  -->
								<c:forEach begin="${numOfRow }" end="${h.roomNoId}">
									<!--房号和当前列数相同  -->
									<c:if test="${isSame==1}">
							<td >
						
	
	<c:set var="stateId" scope="request" value="${h.prhMaster.sta }"></c:set>
	<c:set var="state" scope="request" value="${h.state}"></c:set>					
									<div class="roomPic ahover${h.id}"  
									
									<c:if test="${h.masterId==null }">
									tooltips="
										<h3>房间信息：</h3>
										<p style=''>
										 	房间：${h.buildingNo.buildingId}号楼 ${h.buildingNo.unitName} ${h.roomNo }	&nbsp;&nbsp;&nbsp;&nbsp;   
										   	面积：${h.area }		&nbsp;&nbsp;&nbsp;&nbsp;
										   </p><p>
											月租：${h.monthPrice }	&nbsp;&nbsp;&nbsp;&nbsp;
										 	房型：${h.roomType.name }		&nbsp;&nbsp;&nbsp;&nbsp;
										</p>"
										
									</c:if>
									<c:if test="${h.masterId!=null }">
									tooltips="
										<h3>房间信息：</h3>
										<p style='border-bottom:1px solid green;padding-bottom:14px;'>
										 	房间：${h.buildingNo.buildingId}号楼 ${h.buildingNo.unitName} ${h.roomNo }	&nbsp;&nbsp;&nbsp;&nbsp;   
										   	面积：${h.area }		&nbsp;&nbsp;&nbsp;&nbsp;
											月租：${h.monthPrice }	&nbsp;&nbsp;&nbsp;&nbsp;
										 	房型：${h.roomType.name }		&nbsp;&nbsp;&nbsp;&nbsp;
										</p>
										
										<h3>租户信息：</h3>
										<p > <%=request.getAttribute("stateId")==null?MyConvertUtil.getHouseState(Integer.valueOf(request.getAttribute("state").toString())): MyConvertUtil.getMasterState(request.getAttribute("stateId").toString()) %>：
										${h.prhMaster.profile.name } 	&nbsp;&nbsp;&nbsp;&nbsp;   
										   	同住人：${h.prhMaster.linkManNames }
										   	
										   			&nbsp;&nbsp;&nbsp;&nbsp;
										</p></p>
											电话：${h.prhMaster.profile.mobile }	&nbsp;&nbsp;&nbsp;&nbsp;
										 	证件：${h.prhMaster.profile.idno }&nbsp;&nbsp;&nbsp;&nbsp;
										 	</p></p>
										 	合同：<fmt:formatDate value="${h.prhMaster.bdate }" pattern="yyyy-MM-dd" />&nbsp;&nbsp; -->&nbsp;&nbsp;<fmt:formatDate value="${h.prhMaster.edate }" pattern="yyyy-MM-dd" />
										 	</p></p>
										 	押金：${h.prhMaster.deposit }&nbsp;&nbsp;&nbsp;&nbsp;
										 	缴租至：<fmt:formatDate value="${h.prhMaster.rentDate}" pattern="yyyy-MM-dd" />
										</p>
									"  
									</c:if>
									onclick="chooseOperation(${h.id},${h.state})" style="background-image: url('${ctxStatic}/image/ico/${h.state}.jpg');">
										<script type="text/javascript">
											$(function(){
											$(".ahover${h.id}").hoverTips();
											});
										</script>
												
												<!-- 存作用域，用于小脚本 -->
												<c:set var="stateId" scope="request" value="${h.state }"></c:set>
											 	<span class="roomNumSpan">${h.roomNo }</span> 
											</div> 
										 </td>
										<!--当前列号加1  -->
										<c:set var="numOfRow" value="${numOfRow+1 }"></c:set>
									</c:if>
									<!--房号和当前列数不同  -->
									<c:if test="${isSame==0 }">
										<td>&nbsp;&nbsp;</td>
										<!--当前列号加1  -->
										<c:set var="numOfRow" value="${numOfRow+1 }"></c:set>

									</c:if>
									<!-- 房号和列号是否相同 -->
									<c:set var="isSame" value="${h.roomNoId==numOfRow?1:0 }"></c:set>
								</c:forEach>
								<!--获取当前层数  -->
								<c:set var="floor" value="${h.buildingFloorId }"></c:set>


								</c:forEach>

								<!-- 补全没有的表格线 -->
								<c:forEach begin="0" end="${maxColNum-numOfRow+1}">
									<td>&nbsp;&nbsp;</td>
								</c:forEach>
							<tr />

						</tbody>
					</table>
			</div>
		</div>
	</div>
	









	
</div>
	<sys:message content="${message}"/>
	<script type="text/javascript">
	
	
	
	$(function(){
		var count= "${fn:length(checkBuildings)}";
		if (count!=0) {
			var buildings="${checkBuildings}";
			<% 
				List<Building> buildings=(List<Building>)request.getAttribute("checkBuildings");
				
				for(Building b :buildings){
					System.out.println(b.getId());
			%>
			
				 var str="#buildingMap<%=b.getId()%>"; 
				 var bId="<%=b.getId()%>";
				 var bName="<%=b.getName()%>";
				 var x="<%=b.getPositionX() %>";
				 var y="<%=b.getPositionY() %>";
				 
				 
					var buildingMap = $(str);
				 
				 var pLeft= $("#image").position().left;	
				 var pTop= $("#image").position().top;
				
				 
				 var x= "<%=b.getPositionX()%>";
				 var y="<%=b.getPositionY()%>";
				 
				 
				 /* buildingMap.get(0).style.left = (parseInt(x)+pLeft-16);
				 buildingMap.get(0).style.top = ( parseInt(y)+pTop-7); */ 
				 pleft=16;
				 pTop=113;
				 var xx=(parseInt(x-25));
				 var yy=( parseInt(y-10));
				 
				 var estateId="${estateId}";
				 
				  
						
				$("#consle").append("<a href='prHouseDrawing.do?estateId="+estateId+" &buildingId="+bId+"'><span  style='color:red; height:30px;width:60px; position:absolute;left:"+xx+"px;top:"+yy+"px; ' id='buildingMap"+bId+"' class='buildingMap'>"
					+"&nbsp;&nbsp;&nbsp;"+  bName+"号楼"
						+"<input type='hidden' name='buildingIds' value='"+bId+"'>"
						+"<input type='hidden' name='positionXs' value='"+x+"'>"
						+"<input type='hidden' name='positionYs' value='"+y+"'>"
						+"</span></a>");
			
				 
			<%	
				}
			%>
			
		}
		
		
	});
	
	
$(function(){
		
		var oldxs=new Array();
		var oldys=new Array();
		var maps= $(".buildingMap");
		
		maps.each(function(i,m){
			oldxs[i]=m.style.left;
			oldys[i]=m.style.top;
			
		});
		
		$("#consle").scroll(function(){
			
			var x=$(this).scrollLeft();
			var y=$(this).scrollTop();
			var maps= $(".buildingMap");
			maps.each(function(i,m){
				
			 var oldTop= parseInt(oldys[i].substring(0,oldys[i].length-2));
			 var oldLeft= parseInt(oldxs[i].substring(0,oldxs[i].length-2));
			 m.style.top=(-y+oldTop) ;
			 m.style.left=(-x+oldLeft) ;
			 if (i==0) {
				$(".test").html("原坐标："+oldTop+"新坐标："+m.style.top+"移动了："+y);	
			 }
		   });
		});	
	});
	
	
	
	
	
	
	
	
	$('#image').mousemove(function(e) { 
		var pLeft= $("#image").position().top;
		var pTop= $("#image").position().top;
		var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
		var yy = e.originalEvent.y || e.originalEvent.layerY || 0;
		$(".position").html("x:<span class='xSpan'>"+(xx-16) + "</span>   "+"y:<span class='ySpan'>" +(yy-113)+"</span>"); 
		}); 

	</script>
</body>
</html>