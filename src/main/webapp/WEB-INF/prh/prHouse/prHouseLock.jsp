<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html>
  <head>
    
    <title>锁定房间</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   .addFloor{
	   		border-collapse:collapse;
	   		width: 390px;
	   		margin-left: 40px;
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
	</style>
  </head>
  <script type="text/javascript">
  
  function apply(){
	  /* var reason= $(".reason").val();
	  if (reason.length==0) {
		  $.jBox.tip("信息未填写！");
		  return ;
	  }   */
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'lockHouse.do',
	        data: $('#myForm').serialize(),
	        success: function (result) {
	        	if (result==1) {
	        		alert("房间锁定成功！请点击确定刷新!");
				}else if(result==2){
					alert("房间解锁成功！请点击确定刷新!");
				}else if(result==3){
					alert("房间锁定失败！房间状态可能已被其他操作员操作!");
				}else if (result==4) {
					alert("房间解锁失败！房间状态可能已被其他操作员操作!");
				}
	        	parent.layer.close(parent.layer.getFrameIndex(window.name));
	       		parent.applyResult(1);
	        },
	        error: function(d) {
	            alert("error:"+d.responseText);
	         }
 		 });
  }
  </script>
  <body>
  <form id="myForm" method="post">
	   <table class="addFloor" border="0" bordercolor="#a0c6e5" >
	   		
	   		<tr><td colspan="2">
	   				<input type="hidden" name="isLock" value="${isLock}">
	   				<c:forEach var="houseId" items="${houseIds}">
						<input type="hidden" name="houseIds" value="${houseId}">			   				
	   				</c:forEach>
	   		<br/> </td></tr>
	   		<tr>
	   		    <td class="title" >
	   		    <c:if test="${isLock==1}">锁定</c:if><c:if test="${isLock==0}">解锁</c:if>理由:
	   		     </td>
	   		   <td><input type="text" name="remark"  class="reason"></td>
	   		</tr>
	   		
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2">
	   			<br/><br/>
	   			    <input class="btn submitBtn" type="button" value="<c:if test="${isLock==1}">锁定</c:if><c:if test="${isLock==0}">解锁</c:if>" style="width: 90px;" onclick="apply()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input class="btn" type="button" style="width: 70px;" value="返回" onclick="javascript:parent.layer.close(parent.layer.getFrameIndex(window.name));">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
