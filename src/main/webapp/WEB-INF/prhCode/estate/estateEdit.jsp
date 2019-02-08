<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改物业</title>
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
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}	
	});
	
		$(function(){
				
			$(".myForm").submit(function(){
				var name= $(".name").val();
				var phone=$(".phone").val();
				if (name.length==0) {
					$.jBox.tip("物业名不能为空");
					return false;
				}
				
				if (phone.length==0) {
					$.jBox.tip("物业电话不能为空");
					return false;
				}
			});
		});
	
	
	</script>
  </head>
  
	
  <body>
  <form class="myForm" action="editEstate.do" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
	   		   <td colspan="2">修改物业信息</td>
	   		</tr>
	   		<tr>
	   		   <td class="title" ><span style="color: red;">*</span> 物业名称:</td>
	   		   <td><input type="text" class="name" name="name" value="${estate.name }">
	   		   	<input type="hidden" name="id" value="${estate.id }">
	   		   </td>
	   		</tr>
	   		
	   		<tr>
	   			<td class="title">类别:</td>
	   			<td>
	   			    <select name="typeId" style="width: 100px;">
	   			    	<c:forEach var="t" items="${types}">
	   			    	    <c:if test="${t.id==estate.estateType.id }">
	   			    	       <option value="${t.id }" selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;${t.name}</option>
	   			    	    </c:if>
	   			    	    <c:if test="${t.id!=estate.estateType.id }">
	   							<option value="${t.id }">&nbsp;&nbsp;&nbsp;&nbsp;${t.name}</option>
	   						</c:if>
	   					</c:forEach>
	   			     </select>
	   	       </td>
	   		</tr>
	   		<tr>
	   			<td class="title" ><span style="color: red;">*</span>电话:</td>
	   			<td><input type="text" class="phone" name="phone" value="${estate.phone}"></td>
	   		</tr>
	   		
	   		
	   		<tr>
	   			<td class="title" >地址:</td>
	   			<td><input type="text"  name="address" value="${estate.address }"></td>
	   		</tr>
	   		<tr>
	   			<td class="title" >备注:</td>
	   			<td><input type="text"  name="remark" value="${estate.remark}"></td>
	   		</tr>
	   		<tr style="text-align: center;">
	   			<td colspan="2" style="text-align: center;"><input type="submit" class="btn" value="修改" style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		    	<input type="button" style="width: 70px;" class="btn" value="返回" onclick="location.href='findAllEstate.do'">
	   			</td>
	   			
	   		</tr>
	   </table>
   </form>
  </body>
</html>
