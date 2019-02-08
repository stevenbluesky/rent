<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<title>修改房源信息</title>
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
		$(function(){
		});
		function edit(){
			
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
			
			$.ajax({
                type: "POST",
                dataType: "Text",
                url: 'prHouseEdit.do',
                data: $('#myForm').serialize(),
                success: function (result) {
                
               	 if (result!=null) {
               		 if (result=="success") {
               			 alert("修改成功");
               			parent.layer.close(parent.layer.getFrameIndex(window.name));	
					 }else{
						$.jBox.tip("房源信息重复！");	
					 }
                     
				 }
                },
                error: function(data) {
                    alert("error:"+data.responseText);
                 }
            });
		}
		
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
	<form id="myForm" method="post">
		<input type="hidden" name="id" value="${prHouse.id}">
		<input type="hidden" name="estateId" class="estateId"
			value="${estateId }">
	<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
	   		
			<tr>
				<td class="title">单元号:</td>
				<td> 
				<select name="buildingNoId" style="width: 100px;">
						<c:forEach var="b" items="${buildingNos }">
							<c:if test="${b.id==prHouse.buildingNoId }">
								<option value="${b.id }" selected="selected">${b.name  }</option>
							</c:if>
							<c:if test="${b.id!=prHouse.buildingNoId }">
								<option value="${b.id }" >${b.name }</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>

			<tr>

				<td class="title">楼层:</td>
				<td><select name="buildingFloorId" id="buildingFloorId" class="buildingFloorId" style="width: 100px;" onchange="changeRoomNum()" >
						<c:forEach var="b" items="${buildingFloors }">
							<c:if test="${b.id==prHouse.buildingFloorId }">
								<option value="${b.id }" selected="selected">${b.name}</option>
							</c:if>
							<c:if test="${b.id!=prHouse.buildingFloorId }">
								<option value="${b.id }" >${b.name}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="title">房号:</td>
				<td><input type="text" name="roomNo" class="roomNo" value="${prHouse.roomNo}" onchange="roomNumChange()" onkeyup="value=value.replace(/[^\d.]/g,'')" style="width: 70px;"><span style="color: red;">&nbsp;&nbsp;注：房号开头必须与楼层一致！</span>
				</td>
			</tr>
			<tr>
				<td class="title">面积:</td>
				<td><input type="text" name="area" class="area" value="${prHouse.area}">
				</td>
			</tr>
			<tr>
				<td class="title">房型:</td>
				<td><select name="roomTypeId" style="width: 150px;">
						<c:forEach var="b" items="${roomTypes }">
							<c:if test="${b.id==prHouse.roomTypeId }">
								<option value="${b.id }" selected="selected">${b.name }</option>
							</c:if>
							<c:if test="${b.id!=prHouse.roomTypeId }">
								<option value="${b.id }">${b.name }</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>



			<tr>
				<td class="title">房间:</td>
				<td><input type="text" name="roomNum" class="roomNum" value="${prHouse.roomNum}">
				</td>
			</tr>
			<tr>
				<td class="title">月租金:</td>
				<td><input type="text" name="monthPrice" class="monthPrice" value="${prHouse.monthPrice}">
				</td>
			</tr>
			<tr>
				<td class="title">地下室编号:</td>
				<td><input type="text" name="backPrice" class="backPrice" value="${prHouse.backPrice}">
				</td>
			</tr>
			<tr>
				<td class="title">房源状态:</td>
				<td><select name="state" style="width: 100px;" disabled="disabled">
				 
				 <c:if test="${prHouse.state==1}"><option value="${prHouse.state}" selected="selected"><%=MyConvertUtil.getHouseState(1) %></option></c:if>
				 <c:if test="${prHouse.state==2}"><option value="${prHouse.state}" selected="selected"><%=MyConvertUtil.getHouseState(2) %></option></c:if>
				 <c:if test="${prHouse.state==3}"><option value="${prHouse.state}" selected="selected"><%=MyConvertUtil.getHouseState(3) %></option></c:if>
				 <c:if test="${prHouse.state==5}"><option value="${prHouse.state}" selected="selected"><%=MyConvertUtil.getHouseState(5) %></option></c:if>
				 
						<%-- <option value="1" <c:if test="${prHouse.state==1}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(1) %></option>
						<option value="2" <c:if test="${prHouse.state==2}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(2) %></option>
						<option value="3" <c:if test="${prHouse.state==3}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(3) %></option> --%>
						<option value="4" <c:if test="${prHouse.state==4}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(4) %></option>
						<%-- <option value="5" <c:if test="${prHouse.state==5}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(5) %></option> --%>
						
						<option value="6" <c:if test="${prHouse.state==6}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(6) %></option>
						<option value="7" <c:if test="${prHouse.state==7}">selected="selected"</c:if>><%=MyConvertUtil.getHouseState(7) %></option>	
					</select>
				</td>
			</tr>
			<tr>
				<td class="title">备注:</td>
				<td><input type="text" name="remark" value="${prHouse.remark}">
				</td>
			</tr>


			<tr style="text-align: center;">
			
		<td colspan="2" style="padding-left:120px;" >
			<input class="btn"  type="button" value="修改" onclick="edit()"  style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" style="width: 70px;" value="返回" class="btn" 
					onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"></td>
			</tr>
		</table>
	</form>
</body>
</html>
