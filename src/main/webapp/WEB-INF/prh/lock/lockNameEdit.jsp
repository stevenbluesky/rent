<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<title>修改门锁名称</title>
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
		$(function(){
		});
		function edit(){
			
			$.ajax({
                type: "POST",
                dataType: "Text",
                url: 'lockNameEdit.do',
                data: $('#myForm').serialize(),
                success: function (result) {
                
               	 if (result!=null) {
               		 if (result=="success") {
               		     alert("修改成功！");
               			parent.layer.close(parent.layer.getFrameIndex(window.name));	
					 }else{
						$.jBox.tip("修改失败！");
					 }
                     
				 }
                },
                error: function(data) {
                    alert("error:"+data.responseText);
                 }
            });
		}
		

</script>
</head>

<body>
	<form id="myForm" method="post">
		<input type="hidden" name="deviceid" value="${deviceid}">
		<input type="hidden" name="houseid" class="houseid" value="${houseid}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >

			<tr>
				<td class="title">名称:</td>
				<td><input type="text" name="lockName" class="area" value="${lockName}">
				</td>
			</tr>

			<tr style="text-align: center;">
			
		<td colspan="2" style="padding-left:120px;" >
			<input class="btn"  type="button" value="修改" onclick="edit()"  style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" style="width: 70px;" value="返回" class="btn" 
					onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"></td>
			</tr>
		</table>
	</form>
</body>
</html>
