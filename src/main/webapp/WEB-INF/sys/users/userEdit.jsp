<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>


<html>
<head>

<title>修改房源信息</title>
<meta http-equiv="Content-Type" />
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
		.addFloor {
		   width: 200px;
		   height: 27px;
		}
</style>
<script type="text/javascript">
	function add(){
		/*  非空验证  */
		 var name=$(".name").val();
		 var pwd=$(".pwd").val();
		 var roleId=$(".roleId:checked");
		 var phone=$(".phone").val();
		 var address=$(".address").val();
		 
		if (name.length==0||roleId.length==0) {
			$.jBox.tip("必填信息不能为空！");
			return;
		}
		if (phone.length!=0&&phone.length!=11) {
			$.jBox.tip("手机号码必须为11位！");
			return;
		}
		$.ajax({
			type : "POST",
			dataType : "Text",
			url : 'userEdit.do',
			data : $('#myForm').serialize(),
			success : function(result) {
				if (result=="1") {
					parent.layer.close(parent.layer.getFrameIndex(window.name));
					parent.editResult(result);	
				}
				

			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
}


</script>
</head>

<body>
	<form id="myForm"  method="post">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5"
		 style="width: 700px;"
		 >
		 
			<tr>
				<td class="title"><span style="color: red;">*&nbsp;&nbsp;</span>用户名:
				<input type="hidden" name="id" value="${u.id }">
				
				</td>
				<td><input type="text" name="name" class="name" value="${u.name }" style="height: 26px;"></td>
			</tr>

			<tr>
				<td class="title"><span style="color: red;">*&nbsp;&nbsp;</span>密码:</td>
				<td><input type="password" name="pwd"  class="pwd" style="height: 26px;">
					<span style="color: red;">&nbsp;&nbsp; （不填则不修改！）</span>
				</td>
			</tr>
			<tr>

				<td class="title"><span style="color: red;">*&nbsp;&nbsp;</span>物业:</td>
				<td><select name="estateId" style="width: 150px;">
						<option value="-1">所有物业</option>
						<c:forEach var="e" items="${estates }">
							
							<option value="${e.id }" <c:if test="${u.estateId==e.id }">selected="selected"</c:if>>${e.name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr class="roleListTr">
			
				<td class="title"><span style="color: red;">*&nbsp;&nbsp;</span>角色:</td>
				<td style="padding: 4px 10px 4px;">
					<c:forEach var="role" items="${roles }" varStatus="status">
						<input  
						 type="checkbox" <c:forEach var="r" items="${u.rolesList}">
						 					<c:if test="${role.id==r.id }">checked="checked"</c:if>
						 				</c:forEach>
						  
						 name="roleIds" value="${role.id}" class="roleId" id="roleId${role.id }"><label for="roleId${role.id }">${role.name }</label>&nbsp;&nbsp;
						<c:if test="${status.index%5==0&&status.index!=0 }">
							<br/>
						</c:if>
						
						
					</c:forEach>
					
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">*&nbsp;&nbsp;</span>性别:</td>
				<td>性别:<select id="linkSex" name="sex" class="input" >
						  	 
						<option <c:if test="${u.sex==1}">selected="selected"</c:if> value="1">男</option>
						<option <c:if test="${u.sex==0}">selected="selected"</c:if> value="0">女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="title">电话:</td>
				<td><input type="text" class="phone" value="${u.phone}" name="phone" style="height: 26px;"></td>
			</tr>
			<tr>
				<td class="title">住址:</td>
				<td><input type="text" class="address" value="${u.address}" name="address" style="height: 26px;"></td>
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
		<script type="text/javascript">
		/* function checkHtml() {
			
			var flag = $("#roleId8").is(":checked");
			
			if (flag) {
				var tempText = "<option value='1'>第一维修方</option>"
						+ "<option value='2'>第二维修方</option>"
						+ "<option value='3'>第三维修方</option>";
				tempText = $(".tempDiv1").html();
						$(".roleListTr").after("<tr class='repaire'><td style='text-align: right;'>维修方:</td><td><select class='repaireSelect input'>"
										+ tempText + " </select></td></tr>");
			}else{
				
				$(".roleListTr").next(".repaire").remove();
			}

		}

		$(function() {
			checkHtml();
		}); */
	</script>
	<div class="tempDiv1" style="display: none;">
			<option value='1' style="height: 50px;line-height: 40px;">第一维修方</option>
			<option value='2' style="height: 50px;" >第二维修方</option>
		
	</div>
</body>
</html>
