<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html >
<head>
<meta http-equiv="Content-Type" />
<title>公租房房源管理</title>
<style type="text/css">
.active>a{
    border-top: 2px solid #cc0000 !important;
	color: #cc0000 !important;
	font-weight: bold;
}

a:link {
	color: #444547;
	text-decoration: none;
}

a:visited {
	color: #444547;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}
<!--
body,div,ul,li {
	padding: 0;
}
body {
	font: 12px "宋体";
	text-align: left;
}


ul {
	list-style: none;
}
.main {
	clear: both;
	padding: 8px;
	text-align: left;
}
/*第一种形式*/
#tabs0 {
	height: 600px;
	width: 100%;
	border: 1px solid #red;
	background-color: #f2f6fb;
}

.menu0 {
	width: 400px;
}

.menu0 li {
	display: block;
	float: left;
	padding: 4px 0;
	width: 100px;
	text-align: center;
	cursor: pointer;
	background: orange;
}

.menu0 li.hover {
	background: #ff0;
}

#main0 .menu0 {
	display: none;
}

#main0 .menu0.block {
	display: block;
}
/*第二种形式*/
#tabs {
	text-align: left;
	width: 400px;
}

.menu1box {
	position: relative;
	overflow: hidden;
	height: 22px;
	width: 400px;
	text-align: left;
}

#menu1 {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1;
}

#menu1 li {
	float: left;
	display: block;
	cursor: pointer;
	width: 72px;
	text-align: center;
	line-height: 21px;
	height: 21px;
}

#menu1 li.hover {
	background: #fff;
	border-left: 1px solid #333;
	border-top: 1px solid #333;
	border-right: 1px solid #333;
}

.main1box {
	clear: both;
	margin-top: -1px;
	border: 1px solid #333;
	height: 181px;
	width: 400px;
}

#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}

-->
</style>

<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		$("#tabs0 li").click(function() {
			$(this).addClass("hover").siblings().removeClass("hover");
			var estateId= $(this).find("input").val();	  
			location.href="findPrHouseByEstatePaged.do?estateId="+estateId+"&currpage=1";
		});
		$(".chkAll").click(function(){
			chk(); //全选全不选
		});
		checkMoney();
	});
	function checkMoney(){
		var money= $(".money");
		
		money.each(function(i,e){
			
			if ($.trim($(e).text())=="0"||$.trim($(e).text())=="0.0"||$.trim($(e).text())=="0.00") {
				$(e).text("");
			}	
		});
		
	
		var rent1= $(".rent1");
		var rent1Money=0;
		rent1.each(function(i,e){
			if ($.trim($(e).text())  ) {
				rent1Money+=parseFloat($(e).text());	
			}
		});
		
		
		
		var rent2= $(".rent2");
		var rent2Money=0;
		rent2.each(function(i,e){
			if ($.trim($(e).text())  ) {
				rent2Money+=parseFloat($(e).text());	
			}
		});
		
		
		
		
		var rent3= $(".rent3");
		var rent3Money=0;
		rent3.each(function(i,e){
			if ($.trim($(e).text())  ) {
				rent3Money+=parseFloat($(e).text());	
			}
		});
		
		var desposit1= $(".desposit1");
		var desposit1Money=0;
		desposit1.each(function(i,e){
			if ($.trim($(e).text())  ) {
				desposit1Money+=parseFloat($(e).text());	
			}
		});
		
		var desposit2= $(".desposit2");
		var desposit2Money=0;
		desposit2.each(function(i,e){
			if ($.trim($(e).text())  ) {
				desposit2Money+=parseFloat($(e).text());	
			}
		});
		
		var desposit3= $(".desposit3");
		var desposit3Money=0;
		desposit3.each(function(i,e){
			if ($.trim($(e).text())  ) {
				desposit3Money+=parseFloat($(e).text());	
			}
		});
		
		var allMoney= $(".allMoney");
		var allTotal=0;
		allMoney.each(function(i,e){
			if ($.trim($(e).text())  ) {
				allTotal+=parseFloat($(e).text());	
			}
		});
	
		/* desposit2Total */
		
		
		$(".desposit1Total").text(desposit1Money);
		$(".desposit2Total").text(desposit2Money);
		$(".desposit3Total").text(desposit3Money);
		$(".allMoneyTotal").text(allTotal);
		
		
		$(".rent1Total").text(rent1Money);
		$(".rent2Total").text(rent2Money);
		$(".rent3Total").text(rent3Money);
		
		/* desposit2Total */
		
	var td= $("td");
		
		td.each(function(i,ee){
			if ($(ee).text().length==0) {
				$(ee).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			}	
			
			if ($(ee).text()=="0"||$(ee).text()=="0.0") {
				$(ee).text("");
			}
			
		});
	}
   
</script>

<%
Date now=new Date();
request.setAttribute("now", now);
%>
</head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>
<body >

	<!--第一种形式-->
	<div class="row-fluid" style=" margin: 0px;padding: 0px;" >
		<div class="span4" style="width: 100%">
		<ul class="nav nav-tabs">
					
					<c:forEach var="e" items="${estates}" varStatus="status">
					
					<c:if test="${user.estateId!=null }">
							<c:if test="${user.estateId==e.id }">
								<li class="active"><a href='dayReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
							</c:if>
					</c:if>
						
					<c:if test="${user.estateId==null }">
							<c:if test="${estateId==e.id }">
							<li class="active"><a href='dayReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
						<c:if test="${estateId!=e.id  }">
							<li><a href='dayReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
						</c:if>
					</c:if>
						
				</c:forEach>
		</ul>
		</div>
		<div class="row-fluid repaire-search" style="height: 28px; margin: 0px;padding: 0px;" >
			 <form method="post" id="searchForm" action="monthReport.do">
					<input type="hidden" value="${estateId}" class="estateIdVal" name="estateId">
					
					<div class="row-fluid">
						<div class="span2 search-input" style="width: 100%;height: 25px;margin-bottom: 10px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
								<!--当前年、月  -->
							<c:if test="${nowYear==null }">
								<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear" />
							</c:if>
						
 							<c:set var="monthNum" value="12"></c:set>
 							
 							 年:
							<select name="year" class="year" style="width: 70px;">
								<c:forEach var="year" begin="2017" end="${nowYear}">
									<option value="${year }"<c:if test="${nowYear==year }">selected="selected" </c:if> >${year }</option>
								</c:forEach>
							</select> &nbsp;&nbsp;&nbsp;&nbsp;
									
									
							
							        <input
										type="submit" id="checkRepaire" class="btn"
										style="margin-right: 10px;margin-bottom: 0px;" value="查  询" >
							 </div>
						</div>

				</form>
	
		</div>
		
	</div>	
		<div class="main" id="main0" style=" width:90%;height:75%; margin: 0px;padding-right: 130px;">
			
			<form id="myForm" action="prHouseDel.do" method="post" >
			<input  type="hidden" name="estateId" value="${estateId }"/>
			<table border="1" bordercolor="#a0c6e5" id="contentTable"  
			class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;width:80% !important;">	
				<tr >	
					<td rowspan="3" colspan="2" style="padding-right:70px;padding-left:70px; width: 190px !important;text-align: center;">项目</td>
					<td rowspan="3" colspan="2">年累计</td>
					<td  colspan="${monthNum +3}" style="text-align: left;font-weight: bold;padding-left: 400px;">${title}</td>
						
				</tr>	
				<tr>
						<td colspan="3" style="height: 20px;!important;text-align: center;">第一季度</td>
						<td colspan="3" style="height: 20px;!important;text-align: center;">第二季度</td>
						<td colspan="3" style="height: 20px;!important;text-align: center;">第三季度</td>
						<td colspan="3" style="height: 20px;!important;text-align: center;">第四季度</td>
				</tr>
										 
				<tr>
					
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td style="text-align: center;" >${day }</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td >1.</td>
					<td style="width: 80px !important;">房租统计</td>
					<td colspan="2" class="rent1Total rentTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money rent1">
					
					<fmt:formatNumber type="number" value="${monthRent.get(day)-monthExitRent.get(day) }" pattern="0.00" maxFractionDigits="2"/>
					
					</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td></td>
					<td >收房租</td>
					<td colspan="2" class="rent2Total rentTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money rent2">${monthRent.get(day) }</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td></td>
					<td>退房租</td>
					<td colspan="2" class="rent3Total rentTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money rent3">${monthExitRent.get(day) }</td>
					</c:forEach>
				</tr>
				<tr>
					<td>2.</td>
					<td>押金统计</td>
					<td colspan="2" class="desposit1Total despositTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money desposit1">
					<fmt:formatNumber type="number" value="${monthDesposit.get(day)-monthExitDesposit.get(day) }" pattern="0.00" maxFractionDigits="2"/>
					</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td></td>
					<td>收押金</td>
					<td colspan="2"  class="desposit2Total despositTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money desposit2">${monthDesposit.get(day) }</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td></td>
					<td>退押金</td>
					<td colspan="2"  class="desposit3Total despositTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money desposit3">${monthExitDesposit.get(day)}</td>
					</c:forEach>
				</tr>
				
				<tr>
					<td>3.</td>
					<td>总计</td>
					<td colspan="2" class="allMoneyTotal"></td>
					<c:forEach var="day" begin="1" end="${monthNum }" >
					<td class="money allMoney">
					
					<fmt:formatNumber type="number" value="${(monthRent.get(day)-monthExitRent.get(day))+(monthDesposit.get(day)-monthExitDesposit.get(day) ) }"
					 pattern="0.00" maxFractionDigits="2"/>
					
					</td>
					</c:forEach>
				</tr>
			</table>
			
			<br/>
		 	<input  onclick="location.href='monthReportExport.do?estateId=${estateId}&year=${nowYear}'" value="导出报表" type="button" class="btn">
			 
			</form>	  	
		</div>
	

</body>
</html>