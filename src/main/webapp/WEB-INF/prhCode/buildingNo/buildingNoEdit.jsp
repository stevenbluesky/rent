<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>修改单元信息</title>
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
			padding: 0px 8px 0px;	        
	   }
		.addFloor .title{
			text-align: right;
		}
		.addFloor input{
		   width: 200px;
		}
	</style>
  </head>
  
  <body>
  <form action="editBuildingNo.do" method="post">
  	<input type="hidden" name="id" value="${buildingNo.id }">
  	<input type="hidden" name="estateId" value="${buildingNo.estateId }">
	 <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">


	   		   <td colspan="2">修改单元信息</td>
	   		</tr>
	   		<tr>
	   		    <td class="title" >单元名称:</td>
	   		    
	   		   <td><input type="text" name="name" readonly="readonly" value="${buildingNo.name}"></td>
	   		</tr>
	   		<tr>
	   			<td class="title">单元描述:</td>
	   			<td><input type="text"  name="descript" value="${buildingNo.descript}"></td>
	   		</tr>
	   		
	   		
	   		<tr>
	   			<td class="title" >备注:</td>
	   			<td><input type="text"  name="remark" value="${buildingNo.remark}"></td>
	   		</tr>
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input class="btn" class="btn" type="submit" value="修改" style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input type="button" class="btn" style="width: 70px;" value="返回" onclick="location.href='findBuildingNoByEstatePaged.do'">
	   			</td>
	   		</tr>
	   </table>
   </form>
  </body>
</html>
