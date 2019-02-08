<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>新增单元信息</title>
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
  </head>
  
  <body>
  <form action="addBuildingNo.do" method="post">
	   <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">




		   <th colspan="2">新增单元信息</th>
	   		   
	   		</tr>
	   		<tr >
	   		   <td class="title">代码:</td>
	   		   <td><input type="text" name="id">&nbsp;&nbsp; 如:&nbsp;1-1</td>
	   		</tr>
	   		<tr>
	   		    <td class="title" >单元名称:</td>
	   		   <td><input type="text" name="name"></td>
	   		</tr>
	   		<tr>
	   			<td class="title">单元描述:</td>
	   			<td><input type="text"  name="address"></td>
	   		</tr>
	   		<tr>
	   			<td class="title" >所属物业:</td>
	   			<td>
	   				<input type="hidden" name="estateId" value="${estate.id}">
	   			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   			     ${estate.name }
	   	       </td>
	   		</tr>
	   		
	   		<tr>
	   			<td class="title" >备注:</td>
	   			<td><input type="text"  name="remark"></td>
	   		</tr>
	   		
	   		<tr style="text-align: center;">
	   			
	   			<td colspan="2" style="padding-left:120px;">
	   			<input  class="btn" type="submit" value="新增" style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input class="btn" type="button" style="width: 70px;" value="返回" onclick="location.href='findBuildingNoByEstatePaged.do'">
	   			</td>
	   			
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
