<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>新增楼层</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   .addFloor{
	   		border-collapse:collapse;
	   		width: 600px;
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
		   width: 80px;
		}
	</style>
 	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
 	<script type="text/javascript">
 		function check(){
					var begin= $(".begin").val();
					var end= $(".end").val();
					if(begin.length==0||end.length==0||isNaN(begin)||isNaN(end)||parseInt(begin)>parseInt(end)){
						$(".tip").html("请输入正确的区间").css("color","red");
						return false;
					}
					
					$(".tip").html("").css("color","green");
					return true;
			
			}
			
		$(function(){
			$(".begin").blur(function(){
				check();
			});
			$(".end").blur(function(){
				check();
			});
			
			$("#myForm").submit(function(){
				 return	check();
			});
			
		});
</script>
 
  </head>
  
  <body>
  
  <form id="myForm" action="addBuildingFloor.do" method="post">
	    <table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
	   		   <td colspan="2">批量新增楼层</td>
	   		</tr>
	   
	   
	   		
	   		<tr>
	   		    <td class="title" >楼层区间:</td>   
	   		   <td><input type="text" name="begin" class="begin"> &nbsp;&nbsp;到&nbsp;&nbsp;
	   		   	   <input type="text" name="end" class="end">&nbsp;&nbsp;&nbsp;（如：1-10号 ）<span class="tip"></span></td>
	   		</tr>
	   		
	   		
	   		
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input class="btn" type="submit" value="一 键 生 成" style="width: 110px;height: 40px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input type="button" class="btn" style="width: 110px;height: 40px;" value="返     回" onclick="location.href='findAllBuildingFloorPaged.do'">
	   			</td>
	   		</tr>
	   </table>
   </form>
  </body>
</html>
