<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<title>修改楼号信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 790px;
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
	
}
</style>


<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
	
	});
	
	
	
	
	function createCard() {
		$.ajax({
			url : "getCardSeq.do?name=" + new Date(),
			success : function(id) {
				
				var begin= $(".begin").val();
				var end=$(".end").val();
				var unit=$(".unit:checked").val();
				var author=$(".authorCode").val();
				str1 = JSobj.WriteBuildingCard(id,author, '1',begin,end, unit);
				var info = eval('(' + str1 + ')');
				var result = info.Result;
				if (result == 1) {
					$.jBox.tip("单元卡卡写入成功！");
				} else {
					$.jBox.tip("单元卡卡写入失败！");
				}
				
			}
		});

	}
	
	
	function WriteBuildingCard()
	{
	//SerialNumber：流水号
	//Authorizedata:授权信息  12字节
	//Flag: 1; //不开反锁，不挂失其他卡 2; //开反锁，不挂失其他卡   3; //不开反锁，挂失其他卡  4; //开反锁，挂失其他卡
	// BeginTime, EndTime: 
	// Building: 楼号
		str1 = JSobj.WriteBuildingCard('1','9D1BCA695A4B895F8060C497','1','2017-03-27 14:12:00','2017-03-29 14:12:00','8');	
		alert(str1);
	}
</script>

</head>

<body>
	    <input class="authorCode" value="${estate.authorCode}" type="hidden">
	<form id="myForm" action="buildingEdit.do" method="post">
	
		<input type="hidden" name="estateId" class="estateId" value="${estateId }">
		<input type="hidden" name="id" class="buildingId" value="${building.id }">
		<input type="hidden" name="checkedNos" class="checkedNos">
			<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">&nbsp;&nbsp;开单元卡</td>
			</tr>
		
			<tr>
				<td>单元列表</td>
				<td>
					<br/>
						<c:forEach items="${buildingNos }" var="n" varStatus="status">
							
							 	<input type="radio" name="unit" 
							 	
							 	value="${fn:substring(n.name, 0, fn:length(n.name)-2 )}" 
							 	
							 	id="unit${n.id}"  class="unit"
							 		<c:if test="${status.count==1 }">checked="checked"</c:if>
							 	><label for="unit${n.id}">${n.unitName }
							 	 
							 	
							 	</label>
							 	&nbsp;&nbsp;&nbsp; 
							 	<c:if test="${status.count%4==0 }">
							 		<br/><br/>
							 	</c:if>
						</c:forEach>
				  	<br/>
				</td>
			</tr>
			<tr>
				<td>开始时间:</td>
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
				<td>结束时间:</td>
				<td>
					<input type="text" class="end"
					style="height: 25px;"
					value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" />"
					onclick="WdatePicker({isShowClear:false,readOnly:true})">
					
				</td>
			</tr>		
			<!-- 
			
			<tr>
				<td class="title" style="vertical-align: top;">楼号:</td>
				<td>
					<table class="chkNos">
						<tr>
							
						</tr>
					</table>
				</td>
			</tr> -->
			
			
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;">
				
				<input type="button" value="开单元卡" class="btn" style="width: 110px;height: 40px;" onclick="createCard()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					
					<input type="button" class="btn" style="width: 110px;height: 40px;" value="返     回" onclick="location.href='findBuildingByEstatePaged.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
