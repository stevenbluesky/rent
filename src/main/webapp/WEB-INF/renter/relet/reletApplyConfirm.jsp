<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<html>
<head>

<title>退租申请</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 390px;
	margin-left: 120px;
	margin-top: 20px;
}

.addFloor tr {
	height: 40px;
}

.addFloor  td {
	padding: 0px 8px 0px;
}

.addFloor .title {
	text-align: right;
}

.addFloor input {
	width: 200px;
	height: 30px;
}
</style>
</head>
<script type="text/javascript">
	function checkInput(){
		var hetong= $(".hetong").val();
		var inNo=$(".inNo").val();
		if (hetong.length==0||inNo.length==0) {
			$.jBox.tip("信息尚未填写!");	
			return false;
		}
		return true;
	}


	function apply() {
			$.ajax({
				type : "POST",
				dataType : "Text",
				url : 'reletApplyConfirm.do',
				data : $('#myForm').serialize(),
				success : function(result) {
					if (result==1) {
						alert("租户续租确认成功！");
						parent.layer.close(parent.layer.getFrameIndex(window.name));
						parent.confirmResult(1);	
					}else if (result==0) {
						$.jBox.tip("续租确认失败,账务尚未结算!");
					}else if (result==2) {
						$.jBox.tip("合同编号重复!");
					}else if (result==3) {
						$.jBox.tip("准入编号重复!");
					}else if(result==4) {
						$.jBox.tip("该租户已经确认续租,请勿重复操作!");
					}
				},
				error : function(data) {
				}
			});
		
		
		
	}
</script>
<body>
	<form id="myForm" method="post">
		<table class="addFloor" border="0" bordercolor="#a0c6e5">
			<tr>
				<td colspan="2"><input type="hidden"  name="masterId"
					value="${masterId}"> <br /></td>
			</tr>
			<tr>
				<td class="title">合同编号:</td>
				<td><input type="text" name="hetong" class="hetong"></td>
			</tr>
			<tr>
				<td class="title">入住编号:</td>
				<td>
				  <input name="inId"
					    type="text" class="inNo"/>
					    
				</td>
			</tr>


			<tr style="text-align: center;">
				<td colspan="2"><br />
				<br /> <input class="btn" type="button" value="续租确认"
					style="width: 90px;" onclick="apply()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						 
					<input class="btn" type="button"
					style="width: 70px;" value="返回"
					onclick="javascript:parent.layer.close(parent.layer.getFrameIndex(window.name));">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
