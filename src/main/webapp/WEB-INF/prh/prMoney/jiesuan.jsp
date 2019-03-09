<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<%System.out.println("进入结算页面了"); %>
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
	$(function() {
		 /* 操作后的提示语 */
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
		
		$("#payf").submit(function(){
			var money= $(".collectMoney").val();
			if (money.length==0) {
				$.jBox.tip("请输入应付金额！");
				return false;
			}
			if (isNaN(money)) {
				$.jBox.tip("应付金额格式不正确！");
				return false;
			}
		});
		
	});
	function realSysTime(clock){ 
		var now=new Date(); //创建Date对象 
		var year=now.getFullYear(); //获取年份 
		var month=now.getMonth(); //获取月份 
		var date=now.getDate(); //获取日期 
		var day=now.getDay(); //获取星期 
		var hour=now.getHours(); //获取小时 
		var minu=now.getMinutes(); //获取分钟 
		var sec=now.getSeconds(); //获取秒钟 
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day]; //获取中文的星期 
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
		clock.innerHTML=time; //显示系统时间 
		} 
		window.onload=function(){ 
		window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
		} 
	
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
	  var sumMoneyObj = document.getElementById("sumMoney");
	  sumMoneyObj.innerHTML = "总金额:" + sum;
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
     			$.jBox.tip("请选择废除的详细账单！");
    			return false;
     			
             }   
               else if (v == 'cancel'){
            		$.jBox.tip("取消操作成功！");
             }
             return true; //close
           };
  
		$.jBox.confirm("确定要废除已保存的账务数据吗？", "敏感操作！", submit);
            
  	
	}
	
	</script>

</head>
<body onload="iniEvent()">
	
	
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
						
				

	
	<form id="payf" action="jiesuanForm" method="post">
		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>物业</th><th>房号</th><th>租金金额</th><th>租金开始日期</th>
		<th>租金结束日期</th><th>财务状态</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:if test="${rensize==0}">
			<tr>
				<td colspan="12"><h4>暂无数据！</h4><td>
			
			</tr>
		</c:if>
		<c:if test="${rensize!=0}">
			<c:forEach items="${rentals}" var="ren">
			<input type="hidden" name="accntid" value="${ren.accnt }"/>
			<tr>
				
				
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
	
	<div>
		<table >		
									<tr>			
											<td >
												<div style="margin-right: 40px;margin-bottom: 20px"  >
												合同起始时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${prhMaster.bdate}" pattern="yyyy-MM-dd" />
												</div>
											</td>
											<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												合同截止时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${prhMaster.edate }" pattern="yyyy-MM-dd" />
												</div>
											</td>
											<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												申请日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${prhMaster.depApply}" pattern="yyyy-MM-dd" />
												</div>
											</td>
											<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												合同金额:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${hetongmoney}" pattern="#0.00" />
												</div>
											</td>
											
									</tr>
									<tr>
									<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												已付清:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${yifuqing }" pattern="#0.00" />
												</div>
											</td>
											
											<td>
												<div  style="margin-right:40px;margin-bottom: 20px" >
												未付清:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${weifuqing}" pattern="#0.00" />
												</div>
											</td>
											
											<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												发票号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ino">
												</div>
											</td>
											
											<td>
												应收款:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="collectMoney" class="collectMoney">
											</td>
											
									</tr>
									
								<%-- 	<tr>
									
									<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												应退金额:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${tuiqian}" pattern="#0.00" />
												</div>
											</td>
											<td>
												<div  style="margin-right:40px;margin-bottom: 20px" >
												实退金额:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="text" name="tuiMoney" 
													value='<fmt:formatNumber value="${tuiqian}" pattern="#0.00" />' 
													onkeyup="value=value.replace(/[^\d.]/g,'')">
												</div>
											</td>
											<td>
												<div  style="margin-right: 40px;margin-bottom: 20px" >
												应退押金:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${desposit}" pattern="#0.00" />
												</div>
											
											</td>
											<td>
												<div  style="margin-right:40px;margin-bottom: 20px" >
												实退押金:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="desposit" value='<fmt:formatNumber value="${desposit}" pattern="#0.00" />' onkeyup="value=value.replace(/[^\d.]/g,'')"> 
												</div>
											</td>
									
									</tr> --%>
									</table>
	</div>
	<input type = "hidden" name="masterid" value="${prhMaster.id}"/>
	
	<input  <c:if test="${prhMaster.sta eq '4' or prhMaster.src eq '1'}">disabled="disabled" </c:if> type="submit" id="repaireApply" class="btn" style="margin-right:10px;"   value="退租清账"/>
	
	</form>				
</body>
</html>
<%System.out.println("进入结算页面了2"); %>