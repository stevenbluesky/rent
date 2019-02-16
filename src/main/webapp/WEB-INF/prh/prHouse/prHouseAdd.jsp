<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>

<title>新增房源信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
 .addFloor{
	   		border-collapse:collapse;
	   		width: 490px;
	   }
		.addFloor tr{
	        height: 40px;
	   }
	   .addFloor  td{
			padding: 8px 8px 0px;	   
			  
	   }
		.addFloor .title{
			text-align: right;
		}
		.addFloor input{
		   width: 200px;
		   height: 27px;
		}
</style>
<script type="text/javascript">
	function add(){
		
		
		var floor= $("#buildingFloorId").val();
		var roomNo= $(".roomNo").val();
		var area=$(".area").val();
		var roomNum= $(".roomNum").val();
		var monthPrice=$(".monthPrice").val();
		var backPrice=$(".monthPrice").val();
		
		if (roomNo.length==0||area.length==0||roomNum.length==0||monthPrice.length==0||backPrice.length==0) {
			 $.jBox.tip("房源信息信息填写不完整！");
			 return;
		}
		
		//未完整输入房号
		if (floor==roomNo) {
			 $.jBox.tip("房号输入有误！");
			 return;
		}
		
		if (roomNo.substring(0,1)!=floor ) {
			 $.jBox.tip("房号与楼层不符！");
			 return;
		}
		
		$(".addBtn").attr("disabled","disabled");
		
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'prHouseAdd.do',
	        data: $('#myForm').serialize(),
	        success: function (result) {
	       	 	if (result==="0") {
					$>jBox.tip("房源信息重复！");
					$(".addBtn").removeAttr("disabled");
				}else{
					var index = parent.layer.getFrameIndex(window.name);
		            parent.layer.close(index);	
				}
	            //加载最大可退金额
	        },
	        error: function(data) {
	        	$(".addBtn").removeAttr("disabled");
	            $.jBox.tip("信息填写不正确！");
	         }
   	 });
}

	$(function(){
		changeRoomNum();  //楼层改变，房号随之改变
	});
	function changeRoomNum(){
		var floor= $("#buildingFloorId").val();
	
		$(".roomNo").val(floor);
		
	}
	function roomNumChange(){
		var floor= $("#buildingFloorId").val();
		var roomNo= $(".roomNo").val();
		if (roomNo.substring(0,1)!=floor ) {
			$(".roomNo").val(floor);	
			$.jBox.tip("房号开头必须与楼层一致！"); 
		}
	}

</script>
</head>

<body>
	<form id="myForm"  method="post">

		<input type="hidden" name="estateId" class="estateId"
			value="<%=request.getParameter("estateId")%>">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
	   	

			<tr>
				<td class="title">单元号:</td>
				<td><select name="buildingNoId" style="width: 100px;">
						<c:forEach var="b" items="${buildingNos }">
							<option value="${b.id }">${b.name }</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>

				<td class="title"><span style="color: red;">* </span> 楼层:</td>
				<td><select name="buildingFloorId" id="buildingFloorId" style="width: 100px;" onchange="changeRoomNum()">
						<c:forEach var="b" items="${buildingFloors }">
							<option value="${b.id }">${b.name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>房号:</td>
				<td><input type="text" class="roomNo" name="roomNo" onchange="roomNumChange()" onkeyup="value=value.replace(/[^\d.]/g,'')" style="width: 70px;"><span style="color: red;">&nbsp;&nbsp;注：房号开头必须与楼层一致！</span>
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>面积:</td>
				<td><input type="text" name="area" class="area">
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>房型:</td>
				<td><select name="roomTypeId" class="roomTypeId" style="width: 140px;">
						<c:forEach var="b" items="${roomTypes }">
							<option value="${b.id }">${b.name }</option>
						</c:forEach>
						
				</select></td>
			</tr>



			<tr>
				<td class="title"><span style="color: red;">* </span>房间:</td>
				<td><input type="text" class="roomNum" name="roomNum">
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>月租金:</td>
				<td><input type="text" class="monthPrice" name="monthPrice">
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>地下室编号:</td>
				<td><input type="text"  class="backPrice" name="backPrice">
				</td>
			</tr>
			
			<tr>
				<td class="title"><span style="color: red;">* </span>房源状态:</td>
				<td>
				
					<select name="state" style="width: 120px;">
						<%-- <option value="1"><%=MyConvertUtil.getHouseState(1) %></option>
						<option value="2"><%=MyConvertUtil.getHouseState(2) %></option>
						<option value="3"><%=MyConvertUtil.getHouseState(3) %></option> --%>
						<option value="4"><%=MyConvertUtil.getHouseState(4) %></option>
						<%-- <option value="5"><%=MyConvertUtil.getHouseState(5) %></option> --%>
						<option value="6"><%=MyConvertUtil.getHouseState(6) %></option>
						<option value="7"><%=MyConvertUtil.getHouseState(7) %></option>	
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="title">备注:</td>
				<td><input type="text" name="remark">
				</td>
			</tr>


			<tr style="text-align: center;">
			<td colspan="2" style="padding-left:120px;">
			
			   <input class="btn addBtn"   type="button"  onclick="add()" value="新增"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					class="btn"  type="button" style="width: 70px;" value="返回"
				 onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
				 
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>
