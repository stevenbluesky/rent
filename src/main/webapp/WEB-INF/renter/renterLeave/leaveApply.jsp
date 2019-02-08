<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html>
  <head>
    
    <title>退租申请</title>
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
	</style>
  </head>
  <script type="text/javascript">
  
  function apply(){
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'renterLeave.do',
	        data: $('#myForm').serialize(),
	        success: function () {
	        	$.jBox.tip("退租申请成功");
	       	   parent.layer.close(parent.layer.getFrameIndex(window.name));
	       		parent.applyResult(1);
	            
	        },
	        error: function(data) {
	            alert("error:"+data.responseText);
	         }
 		 });
  }
  </script>
  <body>
  <form id="myForm" method="post">
	   <table class="addFloor" border="0" bordercolor="#a0c6e5" >
	   		
	   		<tr><td colspan="2">
	   			<input type="hidden" name="id" value="${masterId}">
	   		<br/> </td></tr>
	   		<tr>
	   		    <td class="title" >退租理由:</td>
	   		   <td><input type="text" name="refer2" ></td>
	   		</tr>
	   		<tr>
	   		    <td class="title" >退租时间:</td>
	   		   <td>
	   		    	<%Date now= new Date(); request.setAttribute("now", now); %>
					           <input		
								name="cotime_" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />" 
								       type="date">
	   		   
	   		   </td>
	   		</tr>
	   		
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2">
	   			<br/><br/>
	   			<input class="btn" type="button" value="申请退租" style="width: 90px;" onclick="apply()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input class="btn" type="button" style="width: 70px;" value="返回" onclick="javascript:parent.layer.close(parent.layer.getFrameIndex(window.name));">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
