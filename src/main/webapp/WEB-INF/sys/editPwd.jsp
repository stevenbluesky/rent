<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>

<title></title>
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
<script type="text/javascript">
	function check(){
		var old= $(".oldPwd").val();
		var new1= $(".newPwd").val();
		var new2= $(".reNewPwd").val();
		if (old.length==0||new1.length==0||new2.length==0) {
			$.jBox.tip("请完整填写密码信息！");
			return false;
		}
		if (new1!=new2) {
			$.jBox.tip("两次密码输入不一致！");
			return false;
		}
		return true;
	}

	function add(){
		if (!check()) {
			return ;
		}
		$.ajax({
	        type: "POST",
	        dataType: "Text",
	        url: 'editPwd.do',
	        data: $('#myForm').serialize(),
	        success: function (result) {
	        	
	       	  if (result==1) {
	       		
	       		alert("修改成功");
	       		  var index = parent.layer.getFrameIndex(window.name);
	              parent.layer.close(index);
			  }else{
				  
					$.jBox.tip("密码错误！");
			  }
	            //加载最大可退金额
	        },
	        error: function(data) {
	            alert("error:"+data.responseText);
	         }
   	 });
}


</script>
</head>

<body>
	<form id="myForm"  method="post">

		<input type="hidden" name="estateId" class="estateId"
			value="<%=request.getParameter("estateId")%>">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
	  
		
			<tr>
				<td class="title">原密码:</td>
				<td><input type="password" name="oldPwd" class="oldPwd">
				</td>
			</tr>
			
			<tr>
				<td class="title">新密码:</td>
				<td><input type="password" name="newPwd" class="newPwd">
				</td>
			</tr>
			
			<tr>
				<td class="title">确认密码:</td>
				<td><input type="password" name="reNewPwd" class="reNewPwd">
				</td>
			</tr>
		
		
			


			<tr style="text-align: center;">
			<td colspan="2" style="padding-left:120px;">
			
			   <input class="btn"   type="button" onclick="add()" value="修改"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					class="btn"  type="button" style="width: 70px;" value="返回"
				 onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
				 
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>
