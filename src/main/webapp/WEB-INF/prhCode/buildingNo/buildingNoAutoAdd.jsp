<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			padding: 0px 8px 0px;	        
	   }
		.addFloor .title{
			text-align: right;
		}
		.addFloor input{
		   width: 50px;
		}
	</style>
	
	
  <script type="text/javascript">
  	$(function(){
  		$(".myForm").submit(function(){
  	  		var begin= $(".begin").val();
  	  		var end= $(".end").val();
  	  		if (begin.length==0||end.length==0||begin>end) {
  	  			$.jBox.tip("请输入正确的区间");
  				return false;
  			}
  	  	});	
  		
  	});
  	</script>
  </head>
  
  <body>
  <form class="myForm" action="autoAddBuildingNo.do" method="post">
  		
    	<input type="hidden" name="estateId" value="${estateId }">                 			             
  		
  <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
	   		   <td colspan="2">批量新增单元</td>
	   		</tr>

	   		<tr>
	   		    <td class="title" >单元区间:</td>   
	   		   <td><input type="text" class="begin" name="begin"> &nbsp;&nbsp;到&nbsp;&nbsp;
	   		   	   <input type="text" class="end" name="end">&nbsp;&nbsp;&nbsp;（如：1-10）</td>
	   		</tr>
	   		
	   	
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input class="btn" type="submit" value="一 键 生 成" style="width: 110px;height: 40px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input class="btn" type="button" style="width: 110px;height: 40px;" value="返     回" onclick="location.href='findBuildingNoByEstatePaged.do'">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
