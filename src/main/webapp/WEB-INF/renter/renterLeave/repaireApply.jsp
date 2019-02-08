<%@page import="com.rent.common.utils.MyDateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html>
  <head>
    
    <title>退租申请修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   .addFloor{
	   		border-collapse:collapse;
	   		width: 390px;
	   		margin-left: 120px;
	   		margin-top:20px;
	   }
		.addFloor tr{
	        height: 40px;
	   }
	   .addFloor  td{
			padding: 0px 8px 0px;	        
	   }
		.addFloor .title{
			text-align: right;
		}
		.addFloor input{
		   width: 200px;
		   height: 30px;
		}
		.span6{
			width: 220px !important;
		}
	</style>
  </head>
  <script type="text/javascript">
  
  function apply(){
		var repairerPhone=$("#repairerPhone").val();
		var content=$(".area-content").val();
		
		if (repairerPhone.length==0) {
			$.jBox.tip("报修电话不能为空！");
			return;
		}
		if (repairerPhone.length!=11) {
			$.jBox.tip("报修电话必须为11位！");
			return;
		}
	  	if (content.length==0) {
	  		$.jBox.tip("报修明细不能为空！");
			return;
		}
		
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'repaireApply.do',
	        data: $('#insertRepaire').serialize(),
	        success: function (result) {
	        	if (result==1) {
	        		
		       	    parent.layer.close(parent.layer.getFrameIndex(window.name));
		       		parent.repaireApplyResult();	
				}else{
					$.jBox.tip("该房间已被其他租户入住,不能通过此方式报修！");
				}
	        },
	        error: function(data) {
	            alert("error:"+data.responseText);
	         }
 		 });
  }
  </script>
  <body>
  <div id="repaireBox" class="container-fluid" style="width:500px;height:500px">
		<form id="insertRepaire" method="post">
		<input type="hidden" name="masterID" value="${master.id}">
		<input type="hidden" name="houseID" value="${master.prHouse.id}">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					编号:<input type="text" placeholder="输入编号" id="id" disabled="disabled" value="${master.id}">
    			</div>
			
				<div class="span6" >
					报修电话:<input name="repairerPhone" type="text" placeholder="输入报修电话号码" id="repairerPhone" 
					<%-- value="${oldRepaire.repairerPhone }" --%>
					>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					物业:<input  type="text" placeholder="输入物业" id="tenement" disabled="disabled"  value="${master.prHouse.estate.name }">
    			</div>
				<div class="span6">
				   <%
						Date now = new Date();
						request.setAttribute("now", now);
					%>
					
					报修时间:<input name="repairerTime" type="text" placeholder="输入报修时间" id="repairerTime" 
					value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true});" maxlength="20">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					楼号:<input path="floor" type="text" placeholder="输入楼层" id="floor" disabled="disabled" value="${master.prHouse.buildingNo.buildingId}号楼">
    			</div>
			<div class="span6">报修人:<input name="repairer" type="text" placeholder="输入报修人姓名" id="repairer" value="${master.profile.name}" readonly="readonly">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					房号:<input type="text" placeholder="输入房号" value="${master.prHouse.roomNo}" disabled="disabled"/>
    			</div>
				<div class="span6">
					状态:<input type="text" id="status" value="${fns:getHouseState(master.prHouse.state)}" disabled></input>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					房型:<input type="text" placeholder="输入房屋型号" id="roomType" value="${master.prHouse.roomType.name }" disabled="disabled"></input>
    			</div>
				<div class="span6">
					维修类型:<select name="repaireType" id="repaireType" style="width: 150px;">
							
							<option value="正常维修"  <c:if test="${oldRepaire.repaireType=='正常维修' }">selected</c:if>>正常维修</option>
							<option value="维保期维修" <c:if test="${oldRepaire.repaireType=='维保期维修' }">selected</c:if>>维保期维修</option>
							<option value="公共区域" <c:if test="${oldRepaire.repaireType=='公共区域' }">selected</c:if>>公共区域</option>
					</select>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					面积:<input path="roomSize" type="text" placeholder="房间面积" id="roomSize" value="${master.prHouse.area}" disabled="disabled"></input>
    			</div>
				<div class="span6">
					
					房间类型:<input path="roomSize" type="text"  id="houseStatus" value="空置维修" disabled="disabled"></input>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
				<div class="span12">
    					报修内容:<textarea name="comments"  path="comments" class="area-content" type="text" placeholder="输入报修明细" id="comments"></textarea>
    			</div>
				<div class="span6">
				</div>
				
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
			<div class="span12" style="text-align: center;padding-right: 50px;">
			<input class="btn"   type="button" onclick="apply()" value="保存"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					class="btn"  type="button" style="width: 70px;" value="返回"
				 onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
			 </div>
		</form>
	  </div>
  </body>
</html>
