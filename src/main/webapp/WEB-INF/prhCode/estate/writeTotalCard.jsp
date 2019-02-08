<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 390px;
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
}

.contentTable tr {
	height: 50px;
}
</style>
<script type="text/javascript">

	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		var name = $.trim(parent.$(".radioId:checked").parents("tr").find(
				".estateName").text());
		var authorCode = $.trim(parent.$(".radioId:checked").parents("tr")
				.find(".authorCode").text());
		$(".name").text(name);
		$(".authorCode").val(authorCode);

	});
</script>
</head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj"
	width="1" height="1"></object>
<body>
	<br />
	<form class="myForm" action="addEstate.do" method="post">
		<input type="hidden" class="authorCode">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed addFloor"
			border="1" bordercolor="#a0c6e5">

			<tr>
				<td class="title"><span style="color: red;">*</span>物业名称:</td>
				<td style="text-align: center;"><label class="name"> </label></td>
			</tr>


			<tr>
				<td class="title"><span style="color: red;">* </span>开始时间:</td>


				<td>
					<%
						Date date = new Date();
						request.setAttribute("now", date);
					%> <input type="text" class="begin" class="" style="height: 25px;"
					value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" />"
					onclick="WdatePicker({isShowClear:false,readOnly:true})">

				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>结束时间:</td>
				<td>
				<input type="text" class="end"
					style="height: 25px;"
					value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" />"
					onclick="WdatePicker({isShowClear:false,readOnly:true})">
					
					</td>
			</tr>

			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input
					type="button" class="btn" value="开总卡" style="width: 70px;"
					onclick="createCard()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" class="btn" style="width: 70px;" value="返回"
					onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
				</td>

			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function createCard() {
		
		$.ajax({
			url : "getCardSeq.do?name=" + new Date(),
			success : function(id) {

				var begin= $(".begin").val();
				var end=$(".end").val();
				var author=$(".authorCode").val();
				str1 = JSobj.WriteTotalControlCard(id,author, '1', begin,end);
				var info = eval('(' + str1 + ')');
				var result = info.Result;
				
				if (result == 1) {
					$.jBox.tip("总卡写入成功！");
				} else {
					$.jBox.tip("总卡写入失败！");
				}
			}
		});

	}
</script>
</html>
