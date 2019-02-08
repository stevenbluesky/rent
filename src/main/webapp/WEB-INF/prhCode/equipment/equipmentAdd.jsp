<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>新增配套设施类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   .addFloor{
	   		border-collapse:collapse;
	   		width: 390px;
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
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$(".myForm").submit(function(){
				var name= $(".name").val();
				if (name.length==0) {
					$.jBox.tip("配套设施类型不能为空!");
					return false;
				}
				
			});
		});
		
	
	</script>
  </head>
  
  <body>
  <form class="myForm" action="equipmentAdd.do" method="post">
	   <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">





	   		   <td colspan="2">新增配套设施类型</td>
	   		   
	   		</tr>
	   		<tr>
	   		    <td class="title" >名称:</td>
	   		   <td><input type="text" class="name" name="name"></td>
	   		</tr>
	   		
	   		<tr>
	   		    <td class="title" >类别:</td>
	   		   <td>
	   		   <select class="type" id="type" name="type" style="width: 100px;">
						<option value="1">钥匙</option>
						<option value="2">房内设施</option>
						<option value="3">房内电器</option>
					</select>
	   		   </td>
	   		</tr>
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input type="submit" value="新增" class="btn" style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input type="button" style="width: 70px;" class="btn" value="返回" onclick="location.href='findAllEquipmentPaged.do'">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
