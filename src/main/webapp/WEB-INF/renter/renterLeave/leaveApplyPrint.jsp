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
  
  function print(){
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'renterLeavePrint.do',
	        data: $('#myForm').serialize(),
	        success: function () {
	        	$.jBox.tip("打印成功,该房屋已改为可租！");
	       	   parent.layer.close(parent.layer.getFrameIndex(window.name));
	       		parent.applyPrintResult(1);
	            
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
	   			<input type="hidden" name="masterId" value="${masterId}">
	   		<br/> </td></tr>
	   		<tr>
	   		    <td class="title" >打印内容:</td>
	   		   <td>
	   		   		<img src="" width="200px" height="300px;">
	   		   
	   		   </td>
	   		</tr>
	   		<tr>
	   		    <td class="title" ></td>
	   		   <td>
	   		   
	   		   </td>
	   		</tr>
	   		
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2">
	   			<br/><br/>
	   			<input class="btn" type="button" value="打印" style="width: 90px;" onclick="print()"> 
	   		    	<input class="btn" type="button" style="width: 70px;" value="返回" onclick="javascript:parent.layer.close(parent.layer.getFrameIndex(window.name));">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
