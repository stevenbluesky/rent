<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>

<head>

	<title>单位档案管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.jbox-title-panel{
			background:#dc6c6c !important;
			text-align:center;
		}
		.area-content{
			min-height: 84px;
			min-width: 475px;
		}
	</style>

	

	
	<script type="text/javascript">
		$(function(){
			
			checkForm();
		});
		function checkForm(){
			$("#myForm").submit(function(){
				var company= $(".company").val();
				var liason= $(".liason").val();
				var liason1= $(".liason1").val();
				var phone= $(".phone").val();
				var remark= $(".remark").val();
				if (company.length==0||liason.length==0||liason1.length==0||phone.length==0) {
					$.jBox.tip("信息填写不完整！");
					return false;
				}
				return  true;
			});
		}
	
	</script>

</head>
<body>
	
	
	<form action="${ctx}/../addCompany" id="myForm" method="post">
		<input type="hidden" value="${estateId}" name="estateId">
	<table >
	<tr>
		<td><span style="color: red;">*</span> 单位名称:</td><td><input class="company" name="company" type="text" /></td>
	</tr>
	<tr>
		<td><span style="color: red;">*</span>公司电话:</td><td><input  style="height:25px" name="phone"  type="text"  value="${profile.phone}" /></td>
	</tr>
	
		<tr>
		<td><span style="color: red;">*</span>联系人:</td><td><input  class="liason" name="liason" type="text"  /></td>
	</tr>
	
	<tr>
		<td><span style="color: red;">*</span>联系方式:</td><td><input class="liason1"  name="liason1" type="text"  /></td>
	</tr>
	
	<tr>
		<td>备注:</td><td><input class="remark" name="remark"  type="text"  /></td>
	</tr>
	
		
	</table>

	<div><input type="submit" value="添加单位" class="btn" /></div>
	<div><input type="button" value="退出返回" class="btn" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"/></div>
	
	</form>
</body>
</html>