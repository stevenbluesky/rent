<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.rent.entity.PrhMaster"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.input{
			margin-left:7px;
			margin-right: 20px;
				
		}
		.houseInfoLabel{
			margin-left:7px;
			margin-right: 20px;
			color: red;
		}
		
		.smallTxt{
			width: 50px;
			height: 30px;
		}
		.middleTxt{
			width: 90px;
			height: 30px;
		}
		.longTxt{
			width: 170px;
			
		}
		.redFont{
			color: red;
			position: relative;top: 3px;left:5px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			/* alert("<fmt:formatDate value='${newMasterBDate}' pattern='yyyy-MM-dd' />"); */
			$("#isPrcode").click(function(){
				$("#deposit").show();	
			});
			$("#noPrcode").click(function(){
				$("#deposit").hide();
			});
			$(".chkAll").click(function(){
				
				chk(); //全选全不选
			});
			checkLabel();
		});
	</script>
	
	<script type="text/javascript">
		/* 加载  */
		$(function(){
			/* 默认选项卡设置 */
			$(".addOthers").hide();
			chooseDiv(<%=request.getAttribute("tabNo")%>);
			
			/*错误提示  */
			var tip= $(".tip").val();
			if (<%=request.getAttribute("tip")!=null%>) {
				
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			}
		
			chkRentAdd();/*主单表单验证  */
			checkLinkManForm();   /*同住人表单验证  */
			chk();  /* 复选框  */
			changeDateList();
		});
		
		
		 function del(){
			 
			 if (<%=request.getAttribute("currMaster")==null%>) {
				 	$.jBox.tip("请先申请续租！");	
					 $("#linkDelForm").submit(function(){
							return false;
					}); 
			   }else{
			 
				   var count=0;
				   var chk= $(".chk");
				//循环
				chk.each(function(e,q){
					var flag= $(q).is(":checked");
					if(flag){
						count++;
					}	
				});
				 if(count==0){
				    	$.jBox.tip("请选择要删除的同住人！");	
						$("#linkDelForm").submit(function(){
							return false;
						});
	
				 }else{
					 if (!confirm("确认删除吗？")) {
						 $("#linkDelForm").submit(function(){
								return false;
						}); 
					 }
				 }
		  } 
			     
	   }
		
		
		/* 复选框  */
	    function chk(){
			var chkAllFlag= $(".chkAll").attr("checked");
			
			var chks= $(".chk");
			if(chkAllFlag=="checked"){
				$(".chk").attr("checked",true);
			}else{
				$(".chk").attr("checked",false);
			}
		}
		/*选择选项卡  */
		function chooseDiv(id){
		
			
			if (id==1) {
				$("#no2").removeClass("active");
				$("#no3").removeClass("active");
				$("#no1").addClass("active");
				$(".addMaster").show();
				$(".addOthers").hide();
				$(".repaireList").hide();
			} else if (id==2) {
				$("#no1").removeClass("active");
				$("#no3").removeClass("active");
				$("#no2").addClass("active");
				$(".addMaster").hide();
				$(".repaireList").hide();
				$(".addOthers").show();
			}else if(id==3){
				
				$("#no2").removeClass("active");
				$("#no1").removeClass("active");
				$("#no3").addClass("active");
				$(".repaireList").show();
				$(".addOthers").hide();
				$(".addMaster").hide();
			}
		}
		/* 同住人非空验证 */
		
		function checkLinkManForm(){
			$("#linkManForm").submit(function(){
				if (<%=request.getAttribute("currMaster")==null%>) {
					$.jBox.tip("请先申请续租！");
					return false;
				}
				
				/*尚未添加签约人  */
				/*非空  */
				var name= $(".linkName").val();
			    var mobile= $(".linkMobile").val();
			    var idno= $(".linkIdno").val();
			    
				if (name.length==0||mobile.length==0||idno.length==0) {
					 $.jBox.tip("必填信息不能为空！");
					 return false;
				}
				 if (idno.length!=18&&idno.length!=15) {
					 $.jBox.tip("身份证位数不正确！");
					 return false;
				  }
				 
				 var oldIdno="${link.profile.idno }";
					if (oldIdno!=idno) {
						$.ajax({
							url : "isGuestInLive.do?idno="+idno+"&date="+new Date().getTime(),
							type : "get",
							async : false,
							dataType : "text",
							success : function(result) {
								if (result=="1") {
									$.jBox.tip("该租户已经登记或入住其他房间,不能再次入住！");
									$(".haslinkInlive").val("1");
								
								}else{
									$(".haslinkInlive").val("0");
								}
							}
						});
						if ($(".haslinkInlive").val()=="1") {
							return false;
						}	
					
					}
					
				 
				 if (mobile.substring(0,1)!=1|| mobile.length!=11) {
					 $.jBox.tip("手机号格式不正确！");
					 return false;
				  }
				return true;
			});
			
		}
		function chkRentAdd(){
			$("#addRenter").submit(function(){
				var tag2= $(".masterTag2").val();
				 var tag1= $(".masterTag1").val();
				 var name= $(".masterName").val();
				 var mobile= $(".masterMobile").val();
				 var idno= $(".masterIdNo").val();
				 var tag4=$(".tag4Txt").val();
				 var bankNo= $(".bankNoMaster").val();
				  if (tag4.length!=0&&tag4.length!=11) {
						
					 $.jBox.tip("联系方式不正确！");
					 return false;
				} 
				 if (tag2.length==0||tag1.length==0||name.length==0||mobile.length==0||idno.length==0) {
					 $.jBox.tip("必填信息不能为空！");
					return false;						
				  }
				
				 return true;	
			});
			 
		}
		
		function checkLabel(){
			var labels= $(".houseInfoLabel");
			 
			$(labels).each(function() {
	               var val= $(this).text();
	               if (val.length==0) {
	            	   $(this).text("未填写");
					}
	        });

		  			  
		}
	</script>
	
	

</head>
<body>

<div class="container-fluid" >
		<div class="" >
				<ul class="nav nav-tabs">
					<li onclick="chooseDiv(1)" <c:if test="${tabNo==1 }">class="active" </c:if> id="no1"><a>入住人</a></li>
					<li onclick="chooseDiv(2)" <c:if test="${tabNo==2 }">class="active" </c:if>  id="no2" ><a >增加同住人</a></li>
					<li onclick="chooseDiv(3)" <c:if test="${tabNo==3 }">class="active" </c:if>  id="no3" ><a >维修记录</a></li>
					
				</ul>
			</div>
	<br/>
        <div class="addMaster">
			<form method="post" id="addRenter" action="reletApplyAdd.do">
				<input type="hidden" name="houseId" value="${house.id }">
				<input type="hidden" name="currMasterId" value="${currMaster.id }">
				<input type="hidden" name="oldMasterId" value="${oldMaster.id }">
				
				<div class="span2 search-input" style="width: 100%">
					姓名:  <span class="houseInfoLabel">${oldMaster.profile.name}&nbsp;&nbsp;&nbsp;</span>
					性别:  <span class="houseInfoLabel">${oldMaster.profile.sex==0?"女":"男"}</span>&nbsp;&nbsp;&nbsp;
					<c:set var="idclsName"  scope="request" value="${oldMaster.profile.idcls}" ></c:set>
					证件	:  <span class="houseInfoLabel">
					
					<%=MyConvertUtil.getIdName(Integer.valueOf(request.getAttribute("idclsName").toString().trim())) %>
					
					</span>&nbsp;&nbsp;&nbsp;
					证件号: <span class="houseInfoLabel">${oldMaster.profile.idno }</span>&nbsp;&nbsp;&nbsp;
					籍贯:<span class="houseInfoLabel">${oldMaster.profile.nation}</span>&nbsp;&nbsp;&nbsp;
					手机:<span class="houseInfoLabel">${oldMaster.profile.mobile}</span>&nbsp;&nbsp;&nbsp;
					<br/><br/>
					地址:<span class="houseInfoLabel">${oldMaster.profile.street}</span>&nbsp;&nbsp;&nbsp;
					email:<span class="houseInfoLabel">${oldMaster.profile.email}</span>&nbsp;&nbsp;&nbsp;
				
					租户类别:  <span class="houseInfoLabel">${oldMaster.profile.class_=='G'?'租户':'单位'}</span>&nbsp;&nbsp;&nbsp;
					签约单位:<span class="houseInfoLabel">${oldMaster.profile.company }</span>&nbsp;&nbsp;&nbsp;
					调整后月租金：<span class="houseInfoLabel">${oldMaster.prHouse.monthPrice}</span>&nbsp;&nbsp;&nbsp;
					<br/><br/>
					<script type="text/javascript">
						function changeDateList(){
					 		
					 		var rentCodeId= $("#rentCodeList").val();
							var contractBeginDate=$(".contractBeginDate").val();
							var url="changeDateList.do";
							var data="rentCodeId="+rentCodeId+"&contractBeginDate="+contractBeginDate;
							$.post(url,data,function(results){
								var endDate= results[0];
								var toDate=results[1];
								$(".contractEndDate").val(endDate);
								$(".moneyToDate").val(toDate);
							});
						}
					
					
					</script>
					交租方式:<select name=rentCode id="rentCodeList" class="input longTxt rentCode" onchange="changeDateList()">
							   <c:forEach var="r" items="${rentPayWays}">
							   <c:if test="${currMaster==null }">
								   	<c:if test="${oldMaster.rentCode == r.id }">
								   		<option value="${r.id }" selected="selected">${r.name }</option>
								   </c:if>
						   		   <c:if test="${oldMaster.rentCode != r.id }">
								   		<option value="${r.id }" >${r.name }</option>
								   </c:if>
							   </c:if>
							   <c:if test="${currMaster!=null }">
								   	<c:if test="${currMaster.rentCode == r.id }">
								   		<option value="${r.id }" selected="selected">${r.name }</option>
								   </c:if>
						   		   <c:if test="${currMaster.rentCode != r.id }">
								   		<option value="${r.id }" >${r.name }</option>
								   </c:if>
							   </c:if>
							   
					   			</c:forEach>
					       </select>
					补贴标准:<select name="subsidyTypeId" class="input longTxt">
								<option value="-1">无</option>
							<c:forEach var="s" items="${subsidyTypes}">
							
							  <c:if test="${currMaster==null }">
								     <c:if test="${oldMaster.subsidyTypeId == s.id }">
								     	<option value="${s.id }" selected="selected">${s.name }</option>
								     </c:if>
						   			<c:if test="${oldMaster.subsidyTypeId != s.id }">
								     	<option value="${s.id }" >${s.name }</option>
								     </c:if>
							   </c:if>
							   <c:if test="${currMaster!=null }">
								     <c:if test="${currMaster.subsidyTypeId == s.id }">
								     	<option value="${s.id }" selected="selected">${s.name }</option>
								     </c:if>
						   			<c:if test="${currMaster.subsidyTypeId != s.id }">
								     	<option value="${s.id }" >${s.name }</option>
								     </c:if>
							   </c:if>	
					   			</c:forEach>					
					</select>
				
						&nbsp;&nbsp;补贴金额: 
					<c:if test="${currMaster.setrate==null }">
						<span class="houseInfoLabel">（保存完成后显示）</span>
					</c:if>
					<c:if test="${currMaster.setrate!=null }">
						<span class="houseInfoLabel">${currMaster.setrate}</span>&nbsp;&nbsp;&nbsp;
					
					</c:if>
					
					
						&nbsp;&nbsp;应缴租金: 
					<c:if test="${currMaster.rate==null }">
						<span class="houseInfoLabel">（保存完成后显示）</span>
					</c:if>
					<c:if test="${currMaster.rate!=null }">
						<span class="houseInfoLabel">${currMaster.rate}</span>&nbsp;&nbsp;&nbsp;
					
					</c:if>
						<br/><br/>
						
						
						 
						
						
					<%Date now= new Date(); request.setAttribute("now", now); %>
					 签约日期: 
					<input name="dep1_" type="date" class="input longTxt" style="height: 25px;width: 140px;" value="<fmt:formatDate value="${prhMaster.dep1 !=null ? prhMaster.dep1:now }" pattern="yyyy-MM-dd" />" 
					onFocus="WdatePicker({isShowClear:false,readOnly:true,onpicking:changeDateList()})">
					
					合同起:<input onchange="changeDateList()" name="bdate_" type="date" class="input longTxt contractBeginDate" style="height: 25px;width: 140px;" value="<fmt:formatDate value="${prhMaster.bdate !=null ? prhMaster.bdate:newMasterBDate}" pattern="yyyy-MM-dd" />"
					onFocus="WdatePicker({isShowClear:false,readOnly:true,onpicking:changeDateList()})">
					
					合同止:<input   name="edate_" type="date" class="input longTxt contractEndDate" style="height: 25px;width: 140px;" value="<fmt:formatDate value="${prhMaster.edate !=null ? prhMaster.edate:newMasterEDate }" pattern="yyyy-MM-dd" />"
					onFocus="WdatePicker({isShowClear:false,readOnly:true})">
					
					缴租至:<input name="rentdate_" type="date" class="input longTxt moneyToDate" style="height: 25px;width: 140px;" value="<fmt:formatDate value="${prhMaster.rentDate !=null ? prhMaster.rentDate:toMasterDate }" pattern="yyyy-MM-dd" />"
					onFocus="WdatePicker({isShowClear:false,readOnly:true})">
		
					<br/><br/>
					
					<!-- 押金支付:
						<input type="radio" name="prcode" value="1" id="isPrcode" checked="checked"> <label for="isPrcode">是</label> 
						<input type="radio" name="prcode" value="0" id="noPrcode" > <label for="noPrcode">否</label> &nbsp;&nbsp;&nbsp;&nbsp; -->
						
					<!-- <span id="deposit">押金金额: -->
					<input name="deposit" type="hidden" class="input middleTxt"  style="height: 25px;" value="0">
					
					</span>
					扣款账号:<input name="bankNo" type="text" class="input bankNoMaster longTxt" style="height: 25px;" value="${prhMaster.bankNo}">
					
						联系人:<input name="tag3" type="text" class="input longTxt" style="height: 25px;" value="${prhMaster.tag3}">
					联系方式:<input name="tag4" type="text" class="input longTxt tag4Txt" style="height: 25px;" value="${prhMaster.tag4}">
				
				<br/><br/>
				
				<c:if test="${currMaster==null }">
				  <input type="submit" id="addMasterBtn" class="btn" style="margin-right:10px;" value="申请">&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${currMaster!=null }">
				  <input type="submit" id="addMasterBtn" class="btn" style="margin-right:10px;" value="修改">&nbsp;&nbsp;&nbsp;
				</c:if>
				
				</div>
				</form>
	
		
	</div>
	<div class="addOthers">
					<input type="hidden" class="currMasterId" value="${currMaster.id}" >
					<input type="hidden" class="currHouseId" value="${currMaster.prHouse.id}" >
				
				 <br/>
  
   <form id="linkDelForm" action="delLinkManRelet" method="post">
     
     
     
     
     
     <table id="contentTable"
					class="table  table-bordered table-condensed">
					<thead>
						<tr>
							<th style="width: 30px;">选择 <input type="checkbox"
								class="chkAll" style="position: relative; top: 2px;" /></th>
							<th>姓名</th>
							<th>性别</th>
							<th>证件</th>
							<th>证件号</th>
							<th>籍贯</th>
							<th>地址</th>
							<th>手机</th>
							<th>email</th>
							<th>关系</th>
							<th>添加时间</th>	
							<th>状态</th>
							<th>删除时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(linkMans)==0}">
							<tr>
								<td colspan="10">
									<div style="height: 70px;">
										<h2 style="margin: 30px 260px 30px;">尚未添加同住人！</h2>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${fn:length(linkMans)!=0}">
							<c:forEach var="l" items="${linkMans}">
								<tr <c:if test="${l.del==1}">style="background-color: #CCC;"</c:if>>
									
									<td>
										<c:if test="${l.del!=1}">
											<input type="checkbox" name="chk" class="chk" value="${l.id}" />
										</c:if>
										</td>
									<td class="linkNameTxt">${l.profile.name}</td>
									<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
									<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
									<td class="linkidTypeTxt">身份证</td>
									<td class="linkIdnoTxt">${l.profile.idno}</td>
									<td class="linkNationTxt">${l.profile.nation}</td>
									<td class="linkStreetTxt">${l.profile.street}</td>
									<td class="linkMobileTxt">${l.profile.mobile}</td>
									<td class="linkEmailTxt">${l.profile.email}</td>
									<td class="linkLinkTxt">${l.link}</td>
									<td>
										<c:if test="${l.addtime!=null}">
											<fmt:formatDate value="${l.addtime}" pattern="yyyy-MM-dd" />
											
										</c:if>
									</td>
									<td>${l.del==1?'已删除':''}</td>
									<td>
										<c:if test="${l.deltime!=null}">
											<fmt:formatDate value="${l.deltime}" pattern="yyyy-MM-dd" />
										</c:if>
									</td>
									
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			
			
			
			
			
			
			
			
			 <br/>
			 
			 	<script type="text/javascript">
					function updateLink(){
						if (<%=request.getAttribute("currMaster")==null%>) {
							$.jBox.tip("请先申请续租！");
							return false;
						}
				 		var count=0;
						   var chk= $(".chk");
						//循环
						chk.each(function(e,q){
							var flag= $(q).is(":checked");
							if(flag){
								count++;
							}	
						});
					    if(count==0){
					    	$.jBox.tip("请选择要修改的同住人！");
					    	return ;
					    }
					    if (count>1) {
					    	$.jBox.tip("请勿选择多个同住人！");
					    	return;
						}
					    var id= $(".chk:checked").val();
					    
					    location.href="toReletApplyAdd.do?linkId="+id+"&masterId=${currMaster.id}";
				}
			 	</script>
			 
			 	  <input value="修改" class="btn"  type="button"
			      onclick="updateLink()" /> &nbsp;&nbsp;
			 
			 <input value="删除" class="btn"  type="submit"
			   onclick="del()" />
			
		</form>
			<div>
			<br/>
			<form action="addLinkManRelet.do" id="linkManForm" method="post">
			<%-- <input type="hidden" name="oldMasterId" value="${oldMaster.id }"> --%>
			<input type="hidden" class="haslinkInlive" value="0">
			<input name="masterId" type="hidden" value="${currMaster.id}"	>
			<input name="id" type="hidden" value="${link.id}">
			
			<span class="redFont">*&nbsp;</span>
	    	姓名:<input name="name" type="text"  value="${link.profile.name }" class="linkName input middleTxt" style="height: 25px;" >
			
			性别:<select name="sex" class="input smallTxt" >
						 <c:if test="${link.profile.sex!=0}">
								<option value="1">男</option>
								<option value="0">女</option>
							</c:if>
							<c:if test="${link.profile.sex==0}">
								<option value="1" >男</option>
								<option value="0" selected="selected">女</option>
							</c:if>
				 </select>
			证件:
				<select  name="idcls" class="input middleTxt">
						<option value="1">身份证</option>
				</select>
				<span class="redFont">*&nbsp;</span>
			证件号:<input name="idno" type="text" value="${link.profile.idno }" class="linkIdno input longTxt" style="height: 25px;" >
			
			籍贯:<input name="nation" type="text" class="input middleTxt" style="height: 25px;" value="${link.profile.nation }">
			<br/><br/>
	
	
			<span class="redFont">*&nbsp;</span>
			地址:<input name="street"  type="text" class="input longTxt" value="${link.profile.street }" style="height: 25px;" >
			
			<span class="redFont">*&nbsp;</span>
			手机:<input name="mobile" type="text" value="${link.profile.mobile}"  class="linkMobile input middleTxt" style="height: 25px;width: 130px;" >
			email:<input name="email" type="text" value="${link.profile.email }" class="input longTxt" style="height: 25px;" >

					
			<%-- 关系:<input name="link" type="text"  value="${link.link }" class="input middleTxt" style="height: 25px;" > --%>
			 关系:<select name="link" class="input middleTxt linkLink">
							<option value="">请选择</option>
							
							<option value="夫妻" <c:if test="${link.link=='夫妻'}">selected="selected"</c:if> >夫妻</option>
							<option value="子女" <c:if test="${link.link=='子女'}">selected="selected"</c:if>>子女</option>
							<option value="其他" <c:if test="${link.link=='其他'}">selected="selected"</c:if>>其他</option>
					
					</select>
			
			
				<br/><br/><br/>
			
			补贴标准:<select name="subsidyTypeId" class="input longTxt">
							<option value="-1">无</option>
							<c:forEach var="s" items="${subsidyTypes}">
									<c:if test="${s.id==link.subsidyTypeId }">
					   					<option value="${s.id }">${s.name }</option>
					   				</c:if>
					   				<c:if test="${s.id!=link.subsidyTypeId }">
					   					<option value="${s.id }">${s.name }</option>
					   				</c:if>
					   			</c:forEach>				
					</select>
					
			
			<br/><br/><br/>
			<input type="submit" id="checkRepaire" class="btn" style="margin-right:10px;" value="保存">&nbsp;&nbsp;&nbsp;
			</form>	
		 </div>
		
	</div>

	<div class="repaireList">
		 <table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>报修人</th>
						<th>报修电话</th>
						<th>报修时间</th>
						<th>状态</th>
						<th>维修类型</th>
						<th>备注</th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(repaires)==0}">
						<tr><td colspan="9">
							<div style="height: 70px;">
								<h2 style="margin:30px 400px 30px;">尚无维修记录！</h2>
							</div>
						</td></tr>
					</c:if>
					<c:if test="${fn:length(repaires)!=0}">
						<c:forEach var="user" items="${repaires}" varStatus="status">
							<tr>
								<td>${status.count }</td>  
				<td>${user.repairer}</td>
				<td>${user.repairerPhone}</td>
				<td>${fns:formatDate(user.repairerTime)}</td>
				<td>${user.status}</td>
				<td>${user.repaireType}</td>
				<td>${user.comments}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
	
	</div>

</div>
</body>
</html>