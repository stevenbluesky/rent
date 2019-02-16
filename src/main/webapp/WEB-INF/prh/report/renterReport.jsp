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
th,td{
	text-align: center !important;
}

-->
</style>
<%--<link rel="stylesheet" href="css/page.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/page.js"></script>--%>
<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		for(var i=1;i<=10; i++){
			calTotal(i);	
		}
		
	});
	/*计算总钱数  */
	function calTotal(no){
		var total=0.0;
		$((".data"+no)).each(function(i,e){
			 var text= $(e).text();
			total+=parseFloat(text);
		});
		if (no!=1&&no!=3) {
			total= formatNumber(total,'############.00');	
		}
		if (no==2) {
			total=formatNumber(total,'############.00')+'%';
		}
		
		$((".total"+no)).text(total);
		  
	}
	function formatNumber(number,pattern){
	    var str            = number.toString();
	    var strInt;
	    var strFloat;
	    var formatInt;
	    var formatFloat;
	    if(/\./g.test(pattern)){
	        formatInt        = pattern.split('.')[0];
	        formatFloat        = pattern.split('.')[1];
	    }else{
	        formatInt        = pattern;
	        formatFloat        = null;
	    }

	    if(/\./g.test(str)){
	        if(formatFloat!=null){
	            var tempFloat    = Math.round(parseFloat('0.'+str.split('.')[1])*Math.pow(10,formatFloat.length))/Math.pow(10,formatFloat.length);
	            strInt        = (Math.floor(number)+Math.floor(tempFloat)).toString();                
	            strFloat    = /\./g.test(tempFloat.toString())?tempFloat.toString().split('.')[1]:'0';            
	        }else{
	            strInt        = Math.round(number).toString();
	            strFloat    = '0';
	        }
	    }else{
	        strInt        = str;
	        strFloat    = '0';
	    }
	    if(formatInt!=null){
	        var outputInt    = '';
	        var zero        = formatInt.match(/0*$/)[0].length;
	        var comma        = null;
	        if(/,/g.test(formatInt)){
	            comma        = formatInt.match(/,[^,]*/)[0].length-1;
	        }
	        var newReg        = new RegExp('(\\d{'+comma+'})','g');

	        if(strInt.length<zero){
	            outputInt        = new Array(zero+1).join('0')+strInt;
	            outputInt        = outputInt.substr(outputInt.length-zero,zero)
	        }else{
	            outputInt        = strInt;
	        }

	        var 
	        outputInt            = outputInt.substr(0,outputInt.length%comma)+outputInt.substring(outputInt.length%comma).replace(newReg,(comma!=null?',':'')+'$1')
	        outputInt            = outputInt.replace(/^,/,'');

	        strInt    = outputInt;
	    }

	    if(formatFloat!=null){
	        var outputFloat    = '';
	        var zero        = formatFloat.match(/^0*/)[0].length;

	        if(strFloat.length<zero){
	            outputFloat        = strFloat+new Array(zero+1).join('0');
	            //outputFloat        = outputFloat.substring(0,formatFloat.length);
	            var outputFloat1    = outputFloat.substring(0,zero);
	            var outputFloat2    = outputFloat.substring(zero,formatFloat.length);
	            outputFloat        = outputFloat1+outputFloat2.replace(/0*$/,'');
	        }else{
	            outputFloat        = strFloat.substring(0,formatFloat.length);
	        }

	        strFloat    = outputFloat;
	    }else{
	        if(pattern!='' || (pattern=='' && strFloat=='0')){
	            strFloat    = '';
	        }
	    }

	    return strInt+(strFloat==''?'':'.'+strFloat);
	}

	 
</script>


</head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>
<body>

	<!--第一种形式-->
	
		<div class="main" id="main0" style=" margin: 0px;padding: 0px;">
			
			<form id="myForm" action="prHouseDel.do" method="post">
			<input  type="hidden" name="estateId" value="${estateId }"/>
			<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;margin-top: 30px;">
				<tr >
					<th colspan="12" style="text-align: center;font-size: 25px;padding-right: 100px;">${title}</th>
				</tr>	
				<tr >	
				
						<th colspan="2" rowspan="2">物业</th>
						<th colspan="5" >基本信息</th>
						<th colspan="5">财务统计</th>
				</tr>							 
				
				<tr>
					<th colspan="2">房数</th>
					<th>人数</th>
					<th>面积</th>
					<th>应收租金</th>
					<th>收押金</th>
					<th>已收租金</th>
					<th>退押金</th>
					<th>退租金</th>
					<th>合计</th>
					
				</tr>
			
				<c:forEach var="r" items="${reports}" varStatus="status">
				
					<tr>
					<td>${status.count }.</td>
					<td>${r.key }</td>
					
					<td class="data1"><fmt:formatNumber value=" ${r.value[1]}" pattern="#0" /></td>
					<td class="data2"><fmt:formatNumber value="${(r.value[0]==0||r.value[1]==null)?0:r.value[1]/r.value[0] }"  type="percent" pattern="#0.0000%"/></td>
					<td class="data3"><fmt:formatNumber value="${r.value[2]==null?0:r.value[2] }" pattern="#0" /></td>
					<td class="data4"><fmt:formatNumber value="${r.value[3]==null?0:r.value[3]}" pattern="#0.00" /></td>
					<td class="data5"><fmt:formatNumber value="${r.value[6]==null?0:r.value[6]}" pattern="#0.00" /></td>
					<td class="data6"><fmt:formatNumber value="${r.value[5]==null?0:r.value[5]}" pattern="#0.00" /></td>
					<td class="data7"><fmt:formatNumber value="${r.value[4]==null?0:r.value[4]}" pattern="#0.00" /></td>
					
					<td class="data9"><fmt:formatNumber value="${r.value[8]==null?0:r.value[8]}" pattern="#0.00" /></td>
					<td class="data10"><fmt:formatNumber value="${r.value[7]==null?0:r.value[7]}" pattern="#0.00" /></td>
					
					
					<td class="data8"><fmt:formatNumber value="${	(r.value[4]==null?0:r.value[4])+(r.value[5]==null?0:r.value[5])-((r.value[7]==null?0:r.value[7])+(r.value[8]==null?0:r.value[8]))    }" pattern="#0.00" /></td>  
				</tr>
				
				</c:forEach>
				
				<tr>
					
					<td colspan="2">合计</td>
					<td class="total1"></td>
					<td class="total2"></td>
					<td class="total3"></td>
					<td class="total4"></td>
					<td class="total5"></td>
					<td class="total6"></td>
					<td class="total7"></td>
					<td class="total9"></td>
					<td class="total10"></td>
					<td class="total8"></td>
				</tr>
				
			</table>
			
			<br/>
		 	<input  onclick="location.href='renterReportExport.do?estateId=${estateId}'" value="导出报表" type="button" class="btn">
		
</body>
</html>