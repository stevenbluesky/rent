<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改电器设置</title>
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
					$.jBox.tip("电器不能为空!");
					return false;
				}
				
			});
		});
		
	
	</script>
  </head>
  
  <body>
  <form class="myForm" action="electricEdit.do" method="post">
  	   <input type="hidden" name="id" value="${electric.id}">
	   <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">


	   		   <td colspan="2">修改电器设置</td>
	   		   
	   		</tr>
	   		<tr>
	   		    <td class="title" >名称:</td>
	   		   <td><input type="text" class="name" name="name" value="${electric.name}"></td>
	   		</tr>
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input type="submit" class="btn" value="修改" style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input type="button" style="width: 70px;" class="btn" value="返回" onclick="location.href='findAllElectricPaged.do'">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
