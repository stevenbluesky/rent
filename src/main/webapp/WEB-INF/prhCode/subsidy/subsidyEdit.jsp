<%@page import="java.util.Date"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>修改补贴</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 490px;
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
	width: 180px;
}
</style>
<script type="text/javascript">
		$(function(){
			$(".myForm").submit(function(){
				var perOneArea= $(".perOneArea").val();
				var minSafeArea= $(".minSafeArea").val();
				var maxSafeArea= $(".maxSafeArea").val();
				var maxArea= $(".maxArea").val();
				var price= $(".price").val();
				if (perOneArea.length==0||minSafeArea.length==0||maxSafeArea.length==0||maxArea.length==0||price.length==0) {
					$.jBox.tip("数据填写不完整!");
					return false;
				}
				
				var count=0;
				var countf=0;
				var ins= $(".ins");
				ins.each(function(i,e){
					var i= $(e).val();
					if (i.length==0) {
						count++;
						
					}
					if (isNaN(i)) {
						countf++;
					}
				});
				if (count>0) {
					$.jBox.tip("数据填写不完整!");
					return false;
				}
				count=0;
				var countf2=0;
				var outs= $(".outs");
				outs.each(function(i,e){
					var i= $(e).val();
					if (i.length==0) {
						count++;
						
					}
					if (isNaN(i)) {
						countf2++;
					}
				});
				if (count>0) {
					$.jBox.tip("数据填写不完整!");
					return false;
				}
				if (countf>0||countf2>0) {
					$.jBox.tip("数据格式不正确!");
					return false;
				}
				
				if (isNaN(perOneArea)||isNaN(minSafeArea)||isNaN(maxSafeArea)||isNaN(maxArea)||isNaN(price)) {
					$.jBox.tip("数据格式不正确!");
					return false;
				}
				
			});
		});
	
	</script
</head>

<body>
	<form class="myForm" action="subsidyEdit.do" method="post">
		
		<input type="hidden" name="sId" value="<%=request.getParameter("id") %>">
		<input type="hidden" name="estateId" value="${estateId }">
		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">修改补贴</td>
			</tr>
			<%  Date date=new Date();
					request.setAttribute("now", date);
			%>
			<tr>
				<td class="title"><span style="color: red;">* </span>开始时间:</td>
					
				<td>
					<input  type="text" name="begin" 
					  value="<fmt:formatDate value="${subsidy.beginDate!=null?subsidy.beginDate:now}" pattern="yyyy-MM-dd" />"  
					onclick="WdatePicker({isShowClear:false,readOnly:true})">	  
				
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>结束时间:</td>
				<td>
				
				<input    type="text" name="end" 
					  value="<fmt:formatDate value="${subsidy.endDate!=null?subsidy.endDate:now}" pattern="yyyy-MM-dd" />"  
					onclick="WdatePicker({isShowClear:false,readOnly:true})">	  
				
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>每人保障面积:</td>
				<td><input type="text" name="perOneArea" value="${subsidy.perOneArea }" class="perOneArea"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>最低保障面积:</td>
				<td><input type="text" name="minSafeArea" value="${subsidy.minSafeArea }" class="minSafeArea"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>最高保障面积:</td>
				<td><input type="text" name="maxSafeArea" value="${subsidy.maxSafeArea }" class="maxSafeArea"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>最大面积:</td>
				<td><input type="text" name="maxArea" value="${subsidy.maxArea }" class="maxArea"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>单价:</td>
				<td><input type="text" name="price" value="${subsidy.price }" class="price"></td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>保障内补贴</td>
				<td>  <br/>
					  
					<table > 
					<c:forEach var="type" items="${subsidyTypes }" varStatus="status" >
					
						 <c:forEach var="in" items="${subsidy.in}">
						 	<c:if test="${in.key==type.id}">
						 	
						 		<tr style="vertical-align: middle;">
						 			<td style="text-align:right; ">${type.name }:<br/><br/></td>
						 			<td valign="bottom"><span style="color: red;">* </span><input type="text" name="ins" class="ins" 
						 			 value="<fmt:formatNumber value="${ in.value!=null?in.value*100.0:in.value }" type="currency" pattern="#0.00"/> "style="width: 80px;">&nbsp;&nbsp; %<br/><br/></td>
						 			 
						 			 
						 			 
						 		</tr>
						 	</c:if>
					  </c:forEach>	 
						
					</c:forEach>
					 </table>
					
				</td>
			</tr>
			<tr>
				<td class="title"><span style="color: red;">* </span>保障外补贴</td>
				<td>									<br/>
					<table > 
					<c:forEach var="type" items="${subsidyTypes }" varStatus="status" >
					
						 <c:forEach var="out" items="${subsidy.out}">
						 	<c:if test="${out.key==type.id}">
						 	
						 		<tr style="vertical-align: middle;">
						 			<td style="text-align:right; ">${type.name }:<br/><br/></td>
						 			<td valign="bottom"><span style="color: red;">* </span><input type="text" name="outs" class="outs" 
						 			 value="<fmt:formatNumber value="${ out.value!=null?out.value*100.0:out.value }" type="currency" pattern="#0.00"/>" style="width: 80px;">&nbsp; %<br/><br/></td>
						 		</tr>
						 		
						 	</c:if>
					  </c:forEach>	 
						
					</c:forEach>
					 </table>
				</td>
			</tr>
						
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input type="submit" value="修改" class="btn"
					style="width: 70px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="btn"
					type="button" style="width: 70px;" value="返回"
					onclick="location.href='findSubsidyByEstatePaged.do'">
				</td>

			</tr>
		</table>
	</form>
</body>
</html>
