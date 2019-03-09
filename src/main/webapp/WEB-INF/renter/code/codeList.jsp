<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>门锁对码管理</title>
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
		// chk();
		
	});
	
	/*退租申请结果  */
	function applyResult(val){
		
	}
	function toCard(){
		var id= $('.radioId:checked').val();
		if(id!=undefined) {
            layer.open({
                type: 2,
                title: '门锁用户管理',
                maxmin: false,
                area: ['1000px', '500px'],

                content: 'toCardAdd.do?masterId=' + id,
                end: function () {
                    location.reload();
                }
            });
        }
	}
	
	function print(){
		var id= $('.radioId:checked').val();
		location.href="printCode.do?masterId="+id;
	}
	
</script>


</head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>
<body>
	<!--物业  -->
	<div class="container-fluid" style="margin: 0px;padding: 0px;">
		<div class="row-fluid">
			<div class="span4" style="width: 100%">
				<ul class="nav nav-tabs">
					<c:forEach var="e" items="${estates}" varStatus="status">
						<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a
								href="findCode.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
						</c:if>
						
						<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
								<li class="active"><a
								href="findCode.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
							<c:if test="${estateId!=e.id  }">
							<li><a href="findCode.do?estateId=${e.id}">${e.name}</a></li>
						</c:if>
						</c:if>
					
						
					</c:forEach>
				</ul>
			</div>
			<br/>
		</div>
			
		<div class="applyDiv" >
		
				<!--申请/确认选项卡  -->
				  <form method="post" id="searchForm"  
					  action="findCode.do">
					<input type="hidden" name="tabNo" value="${tabNo }">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					<div class="row-fluid">
							<div class="span2 search-input" style="width: 100%;height: 25px;" >
								
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
										class="searchTxt name1">&nbsp;&nbsp;&nbsp;&nbsp;

							        <input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" >
							 </div>
						</div>

				</form>
			
			
			<%-- <span style="font-size: 20px;"> 小区授权码：<span style="color: red;">${estate.authorCode }</span></span> --%>
			<br/>
			
			<form id="delReletApplyForm" action="delCheckInRecordForm.do" method="post">
					<!-- 隐藏域存条件 -->			
					<input type="hidden" name="estateId"      value="${estateId }" />
					<input type="hidden" name="roomNo" ${roomNo} >
					<input type="hidden" name="name" class="nameVal"  value="${name}">
					
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;text-align: center; margin: 0px;" >
					<tr>
						<th>
						选择
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
						<th>房间编号</th>
						
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
								<c:if test="${status.index==0 }">
									<input type="radio" checked="checked" name="masterId" class="radioId" value="${m.id }"/>
								</c:if>
								<c:if test="${status.index!=0 }">
									<input type="radio" name="masterId" class="radioId" value="${m.id }"/>
								</c:if>
								</td>
						<td >
						<input class="stateHidden" type="hidden" value="${m.sta}" >
						<span id="state${m.id}">
									<c:set value="${m.sta}" scope="request" var="sta"></c:set>
							  	   <%=MyConvertUtil.getMasterState(request.getAttribute("sta").toString()) %>
						
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
 						
 							<td>${m.prHouse.houseCode }</td>
							
							<td>${b.remark}</td>
						

						</tr>
					</c:forEach>

				</table>
			
		
				<div class="pagination" id="numpage">${numpage}</div>
				<br/>
				<input type="hidden" class="opHidden"   >
				
				
				
				
								
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn"  value="门锁用户管理" type="button" onclick="toCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==22}">
					<input class="btn"  value="门锁用户管理" type="button" onclick="toCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 				</c:if></c:forEach></c:if></c:forEach>
				
			</form>
	   </div> 
	
</div>
<script type="text/javascript">
function cardSearch(){
	var  Authorizedata='${estate.authorCode }';	
	returnstr=JSobj.ReadAllCard(Authorizedata);
	var info = eval('(' + returnstr + ')');
	var result= info.Result;
	
	if (result!=1) {
		if (result==5) {
			$.jBox.tip("读取失败，请尝试到其他物业查询！");		
		}else if(result==3){
			$.jBox.tip("读取失败,请正确放置卡片！");	
		}
		
	}else{
		var card= info.Value;
		var serial=card.SerialNumber;
		if (card.CardType==9) {
			var unit=card.Building;
			var floor=card.Floor;
			var room=card.Room;
			var roomNo;
			if ($(room).length==1) {
				roomNo=floor+"0"+room;
			}else {
				roomNo=floor+""+room;
			}
			var tip="房号卡\n\n流水号："+serial+"\n房源信息："+unit+"单元"+roomNo;
			alert(tip);
		}else if(card.CardType==15){
			var tip="锁号卡\n\n流水号："+serial;
			alert(tip);
		}else if(card.CardType==0){
			var begin=card.BeginTime;
			var end=card.EndTime;
			var unit=card.Building;
			var floor=card.Floor;
			var room=card.Room;
			var roomNo;
			if ($(room).length==1) {
				roomNo=floor+"0"+room;
			}else {
				roomNo=floor+""+room;
			}
			var tip="客人卡\n\n流水号："+serial;
			tip+="\n租期："+begin+"至"+end;
			tip+="\n房源信息："+unit+"单元"+roomNo;
			alert(tip);
		}else if(card.CardType==12){
			var tip="挂失卡\n\n流水号："+serial;
			alert(tip);
		}else if(card.CardType==7){
			var tip="授权卡\n\n流水号："+serial;
			alert(tip);
		}else if(card.CardType==8){
			var tip="时钟卡\n\n流水号："+serial;
			tip+="\n写入时间："+card.Clock;
			alert(tip);
		}
	}
}
</script>
	
</body>
</html>