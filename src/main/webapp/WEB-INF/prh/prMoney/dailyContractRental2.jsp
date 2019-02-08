<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>用户管理</title>
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

	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		$(".chkAll").click(function(){		
			chk(); //全选全不选
		});
		$(".chkAll2").click(function(){		
			chk2(); //全选全不选
		});
		
	});
	
	function chk(){
		var chkAllFlag= $(".chkAll").attr("checked");
		
		var chks= $(".chk");
		if(chkAllFlag=="checked"){
			$(".chk").attr("checked",true);
		}else{
			$(".chk").attr("checked",false);
		}
		calculateMoney();
	}
	function chk2(){
		var chkAllFlag= $(".chkAll2").attr("checked");
		
		var chks= $(".chk2");
		if(chkAllFlag=="checked"){
			$(".chk2").attr("checked",true);
		}else{
			$(".chk2").attr("checked",false);
		}
	
	}
	
	//统计所有选中的产品金额
	function calculateMoney() {
	  var sum = 0;
	  var chkItems = document.getElementsByName("chk"); //获得复选框的值xia
	  
	  for (var i = 0; i < chkItems.length; i++) {
	    if (chkItems[i].checked) {
	    	var  money= $(chkItems[i]).parents("tr").find(".money").text();
	    	
	      sum += parseFloat(money);
	    }
	  }
	  var sumMoneyObj2 = document.getElementById("sumMoney2");
	
	  sumMoneyObj2.innerHTML = "付款:" + formatNumber(sum,'############.00');	
	}
	function iniEvent() {
		  var chkItems = document.getElementsByName("chk");
		  for (var i = 0; i < chkItems.length; i++) {
		    chkItems[i].onclick = calculateMoney;
		  }      
		}
	function payForm(){
		
		  var chks= $(".chk");
			for (var i = 0; i < chks.length; i++) {
				if(chks[i].checked){
					
					//提交 submit		
					$("#payf").submit();	
					return false;
				}
			}
			$.jBox.tip("请选择详细支付清单！");
			return false;
	}
	function opeForm(){
		
		 var submit = function (v, h, f) {
             if (v == 'ok'){
            	 var chks= $(".chk2");
     			for (var i = 0; i < chks.length; i++) {
     				if(chks[i].checked){
     					
     					//提交 submit		
     					$("#opeForm").submit();	
     					return false;
     				}
     			}
     			$.jBox.tip("请选择作废的详细账单！");
    			return false;
     			
             }   
               else if (v == 'cancel'){
            		$.jBox.tip("取消操作成功！");
             }
             return true; //close
           };
  
		$.jBox.confirm("确定要作废已保存的账务数据吗？", "敏感操作！", submit);
            
  	
	}
	
	</script>

</head>
<body onload="iniEvent()">
	
	<form  method="post"  action="historyRental" >
	<ul class="nav nav-tabs" style="width: 100%">
					
					<c:forEach items="${estates}" var="e" >
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
					disabled="true"	href="${ctx}/../balance?estateId=${e.id}">${e.name }</a></li>
					</c:forEach>	
					<li>
					
											
						</li>			
					</ul>
					<ul class="nav nav-tabs">
					<li>
						<div class="row-fluid repaire-search" style="margin-bottom:0px;">		
								<table >		
									<tr >			
											<td >
												<div style="margin-right: 100px;margin-bottom: 20px"  >
												编号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.id}
												</div>
											</td>
											<td>
												<div  style="margin-right: 100px;margin-bottom: 20px" >
												性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${prhMaster.profile.sex==0}">女</c:if><c:if test="${prhMaster.profile.sex==1}">男</c:if>
												</div>
											</td>
											<td>
											
											<div  style="margin-right: 100px;margin-bottom: 20px" >
												物业:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												${prhMaster.prHouse.estate.name }
												</div>
										
											</td>
											<td>
											
												<div  style="margin-right: 100px;margin-bottom: 20px" >
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input id="masterId" name = "masterId" type="hidden" value="${prhMaster.id}"/>
												<input type="submit" value="历史账务记录" id="repaireApply" class="btn" style="margin-right:10px;" />
												</div>
												
										
											</td>
											
									<tr>
									<tr>
										<td>
											<div style="margin-right: 100px;margin-bottom: 20px">
													类别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:if test="${prhMaster.profile.class_ eq 'C'}">
															单位租户
													</c:if>
													<c:if test="${prhMaster.profile.class_ eq 'G'}">
															个人租户
													</c:if>
												</div>
											</td>
											<td>
												<div style="margin-right: 100px;margin-bottom: 20px" >
												证件:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.profile.idno }
												</div>
											
										</td>
										<td>
													<div style="margin-right: 100px;margin-bottom: 20px" >
													楼号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.prHouse.buildingNo.buildingId}号楼
												</div>
												
											</td>
									</tr>
									<tr>
										<td>
											<div style="margin-right: 100px;margin-bottom: 20px">
												状态:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:set var="state" scope="request" value="${prhMaster.sta}"></c:set>
												<%=MyConvertUtil.getMasterState(request.getAttribute("state").toString()) %>  
												</div>
											</td>
											<td>
												
											<div  style="margin-right: 100px;margin-bottom: 20px" >
												单位:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.profile.company}
												</div>
										</td>
										<td>
												<div  style="margin-right: 100px;margin-bottom: 20px" >
													单元:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.prHouse.buildingNo.unitName}
												</div>
											</td>
									</tr>
									<tr>
										<td>
											<div style="margin-right: 100px;margin-bottom: 20px">
												姓名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.profile.name}
												</div>
											</td>
											<td>
												<div style="margin-right: 100px;margin-bottom: 20px" >
												电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.profile.mobile }
												</div>
												<td>
												<div  style="margin-right: 100px;margin-bottom: 20px" >
												房号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.prHouse.roomNo }
												</div>
											</td>
											
										
									</tr>
									<tr>
										<td>
											<div style="margin-right: 100px;margin-bottom: 20px">
												交租方式:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.rentPayWay.name}
												</div>
											</td>
											<td>
												<div style="margin-right: 100px;margin-bottom: 20px" >
												补贴标准:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.subsidyType.name }
												</div>
												<td>
												<div  style="margin-right: 100px;margin-bottom: 20px" >
												面积:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${prhMaster.prHouse.area }
												</div>
											</td>
											
										
									</tr>
								
										
							</table>																										
						</div>
					</li>
					</ul>
						
				</form>	
	<sys:message content="${message}"/>
	<form id="opeForm" action="updateRentalstaAndupdatePaymentSta" method="post">
		<h4>已付清</h4>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择<input type="checkbox" 
				 class="chkAll2" style="position: relative;top: 2px;"/></th><th>编号</th><th>物业</th><th>房号</th><th>租金开始日期</th><th>租金结束日期</th><th>付款人</th><th>结算日期</th>
		<th>收费总金额</th><th>发票号</th><th>现金支付金额</th><th>刷卡支付金额</th><th>转账支付金额</th><th>财务状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:if test="${rentalsTsize==0}">
			<tr>
				<td colspan="12"><h4>暂无数据！</h4><td>
				
			</tr>
		</c:if>
		<c:if test="${rentalsTsize!=0}">
			<c:forEach items="${rentalsT}" var="ren">
		
			<tr>
				<td>
					<input type="checkbox" name="chk2" class="chk2" value="${ren.prtno}" >
					<input type="hidden" name="accntid2" value="${ren.rentals.accnt }"/>
				</td>
				<td>${ren.rentals.inumber}</td>
				<td>${ren.rentals.accnts.prHouse.estate.name}</td>
				<td>${ren.rentals.accnts.prHouse.no}</td>
				
				<td><fmt:formatDate value="${ren.rentals.bdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ren.rentals.edate}" pattern="yyyy-MM-dd" /></td>
				
				<td>${ren.rentals.accnts.profile.name}</td>
				<td><fmt:formatDate value="${ren.rentals.ilogdate}" pattern="yyyy-MM-dd" /></td>				
				<td>${ren.rentals.iamount}</td>		
				<td>${ren.rentals.ino}</td>
				<td><c:if test="${ren.paycode eq '1'}">
					${ren.credit}
					</c:if></td>
				<td><c:if test="${ren.paycode eq '2'}">
					${ren.credit}
					</c:if>	 </td>
				<td><c:if test="${ren.paycode eq '3'}">
					${ren.credit}
					</c:if></td>
				<td><c:if test="${ren.rentals.sta eq 'T'}">
							已付清
					</c:if>
					<c:if test="${ren.rentals.sta eq 'F'}">
							未付清
					</c:if>
				 </td>
				<td><c:if test="${ren.rentals.inumber ==0}">
							押金
					</c:if>
					<c:if test="${ren.rentals.inumber !=0}">
							月租金
					</c:if></td>
				
				
			</tr>
		</c:forEach>
		</c:if>
		
		</tbody>
	</table>
	
	
	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<div><input type="button" id="oper" class="btn" style="margin-right:10px;" value="作废" onclick="opeForm()"></input></div>					
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==107}">
					<div><input type="button" id="oper" class="btn" style="margin-right:10px;" value="作废" onclick="opeForm()"></input></div>
	</c:if></c:forEach></c:if></c:forEach>
		
	
	</form>
	
	<form id="payf" action="payForm" method="post">
		<h4>未付清</h4>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择<input type="checkbox" 
				 class="chkAll" style="position: relative;top: 2px;"/></th><th>编号</th><th>物业</th><th>房号</th><th>租金金额</th><th>租金开始日期</th>
		<th>租金结束日期</th><th>财务状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:if test="${rentalsFsize==0}">
			<tr>
				<td colspan="12"><h4>暂无数据！</h4><td>
			
			</tr>
		</c:if>
		<c:if test="${rentalsFsize!=0}">
			<c:forEach items="${rentalsF}" var="ren">
			<input type="hidden" name="accntid" value="${ren.accnt }"/>
			<tr>
				<td>
					<input type="checkbox" name="chk" class="chk" value="${ren.id}"  >
				</td>
				
				<td>${ren.inumber}</td>
				<td >${ren['accnts']['prHouse']['estate']['name']}</td>
				<td >${ren['accnts']['prHouse']['no']}</td>
				<td name="money" class="money">${ren['rate']}</td>

				<td><fmt:formatDate value="${ren['bdate']}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ren['edate']}" pattern="yyyy-MM-dd" /></td>		
				<c:set var="sta" scope="request" value="${ren['sta']}"></c:set>
				<td><%=MyConvertUtil.getRenSta((request.getAttribute("sta").toString())) %> </td>
				
					<c:if test="${ren.inumber==0}">
						<td>押金</td>
					</c:if>
						<c:if test="${ren.inumber!=0}">
						<td>租金</td>
					</c:if>
							
				
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	

	<ul class="nav nav-tabs">
						<li>
							<div class="row-fluid repaire-search" style="margin-bottom:0px;">	
							<table >		
									<tr>			
											<td >
												<div  style="margin-right: 100px;margin-bottom: 25px;"  >
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p id="sumMoney2">付款:</p>
												</div>
											</td>
											<td>
												<div  style="margin-right: 100px;" >
													<select id="roomtypeid" name="payType" > 
														<option value="-1">请选择</option>
													<c:forEach var="n" items="${pcode}" varStatus="1">					
														<option value="${n.id}">${n.name}</option>					
													</c:forEach>
													</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*
												</div>
											</td>
						
											<td>												
												<div  style="margin-right: 100px;" >
												批次:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="batch" type="text" id="batch" style="width:100px;margin-top: 7px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</input>
												</div>
											</td>
											
											
												<td>
												<div  style="margin-right: 100px;" >
												发票号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="ino" type="text" id="ino" style="width:100px;margin-top: 7px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</input>
												</div>
											</td>
											<td>
												<div  style="margin-right: 100px;margin-bottom: 25px" >
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p id="">总金额:<fmt:formatNumber value="${zong}" pattern="#0.00" /></p>
												</div>
											</td>
											
											</tr>
											<tr>
											<td>
												<div style="margin-right: 100px;" ></div>
											</td>
					
											</tr>
									
									</table>
							</div>
						</li>
					
						 </ul>
						
		<input type="button" id="repaireApply" class="btn" style="margin-right:10px;"  onclick="payForm()" value="结算"/>
		
		
	</form>				
</body>
</html>