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
		h3{
			margin-bottom: 10px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$("#isPrcode").click(function(){
				$("#deposit").show();	
			});
			$("#noPrcode").click(function(){
				$("#deposit").hide();
			});
			
		});
	</script>
	
	<script type="text/javascript">
		/* 加载  */
		$(function(){
			/*错误提示  */
			var tip= $(".tip").val();
			if (<%=request.getAttribute("tip")!=null%>) {
				
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			}
			/*检查label是否有数据  */
			checkLabel();
		});
		
		/*检查label是否有数据  */
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
<p id="test"> </p>
<div class="container-fluid" >
		
	<br/>
      <div class="addMaster">
		
				<input type="hidden" name="houseId" value="${house.id }">
				<input type="hidden" name="currMasterId" value="${currMaster.id }">
				
				<div class="span2 " style="width: 100%">
				
					<h3>房源信息</h3>
					小区	:  <span class="houseInfoLabel">${house.estate.name}&nbsp;&nbsp;&nbsp;</span>
					楼栋	:  <span class="houseInfoLabel">${house.buildingNo.buildingId}号楼&nbsp;&nbsp;${house.buildingNo.unitName}</span>&nbsp;&nbsp;&nbsp;
					房号	:  <span class="houseInfoLabel">${house.roomNo }</span>&nbsp;&nbsp;&nbsp;
					房间类型: <span class="houseInfoLabel">${house.roomType.name }</span>&nbsp;&nbsp;&nbsp;
					房间个数:<span class="houseInfoLabel">${house.roomNum }</span>&nbsp;&nbsp;&nbsp;
					面积:<span class="houseInfoLabel">${house.area}</span>&nbsp;&nbsp;&nbsp;
					地下室编号:<span class="houseInfoLabel">${house.backPrice }</span>&nbsp;&nbsp;&nbsp;
					月租金:<span class="houseInfoLabel">${house.monthPrice}</span>&nbsp;&nbsp;&nbsp;
					
					<br/><br/>
					</div>
				
				<div class="span2 " style="width: 100%">
					<h3>单号信息</h3>
					合同号:  <span class="houseInfoLabel">${currMaster.tag2}&nbsp;&nbsp;&nbsp;</span>
					准入编号:  <span class="houseInfoLabel">${currMaster.tag1}</span>&nbsp;&nbsp;&nbsp;
					
					交租方式:  <span class="houseInfoLabel">${currMaster.rentPayWay.name }</span>&nbsp;&nbsp;&nbsp;
					补贴标准: <span class="houseInfoLabel">${currMaster.subsidyType.name }</span>&nbsp;&nbsp;&nbsp;
					补贴金额: <span class="houseInfoLabel">${currMaster.setrate}</span>&nbsp;&nbsp;&nbsp;
					应缴租金: <span class="houseInfoLabel">${currMaster.rate}</span>&nbsp;&nbsp;&nbsp;
					入住人数: <span class="houseInfoLabel">${currMaster.numbs }</span>&nbsp;&nbsp;&nbsp;
					<br/><br/>   
					押金金额: <span class="houseInfoLabel">${currMaster.deposit }</span>&nbsp;&nbsp;&nbsp;
					扣款账号:<span class="houseInfoLabel">${currMaster.bankNo}</span>&nbsp;&nbsp;&nbsp;
					签约日期:<span class="houseInfoLabel"><fmt:formatDate value="${currMaster.dep1}" pattern="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;
					合同起:<span class="houseInfoLabel"><fmt:formatDate value="${currMaster.bdate}" pattern="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;
					合同止:<span class="houseInfoLabel"><fmt:formatDate value="${currMaster.edate}" pattern="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;
					缴租至<span class="houseInfoLabel"><fmt:formatDate value="${currMaster.rentDate}" pattern="yyyy-MM-dd" /></span>&nbsp;&nbsp;&nbsp;
					
					<br/><br/>
					联系人：<span class="houseInfoLabel">${currMaster.tag3}</span>&nbsp;&nbsp;&nbsp;
					联系方式：<span class="houseInfoLabel">${currMaster.tag4}</span>&nbsp;&nbsp;&nbsp;
					
					<br/><br/>
					</div>
					
					<div class="span2 " style="width: 100%">
				
				
					<h3>签约人信息</h3>
					姓名:  <span class="houseInfoLabel">${currMaster.profile.name}&nbsp;&nbsp;&nbsp;</span>
					性别:  <span class="houseInfoLabel">${currMaster.profile.sex==0?"女":"男"}</span>&nbsp;&nbsp;&nbsp;
						<c:set var="idclsName"  scope="request" value="${currMaster.profile.idcls}" ></c:set>
							
					证件	:  <span class="houseInfoLabel"><%=MyConvertUtil.getIdName(Integer.valueOf(request.getAttribute("idclsName").toString().trim())) %></span>&nbsp;&nbsp;&nbsp;
					证件号: <span class="houseInfoLabel">${currMaster.profile.idno }</span>&nbsp;&nbsp;&nbsp;
					籍贯:<span class="houseInfoLabel">${currMaster.profile.nation}</span>&nbsp;&nbsp;&nbsp;
					
					手机:<span class="houseInfoLabel">${currMaster.profile.mobile}</span>&nbsp;&nbsp;&nbsp;
					<br/><br/>
					地址:<span class="houseInfoLabel">${currMaster.profile.street}</span>&nbsp;&nbsp;&nbsp;
					email:<span class="houseInfoLabel">${currMaster.profile.email}</span>&nbsp;&nbsp;&nbsp;
					
					<div id="masterPhoto" style="border: 1px solid #ccc; width: 110px; height: 135px; float: right; margin-right: 200px; position: relative; left: -200px; top: -45px;padding: 0px;">
						
						
					<c:if test="${currMaster.picPhoto==null}">
						<img  src="${ctxStatic}/photo/default.jpg" style="" >
					</c:if>
					<c:if test="${currMaster.picPhoto!=null}">
						<img  src="${ctxStatic}/photo/${currMaster.picPhoto}" style="" >
					</c:if>
					
					</div>
					
					
					
					
					
					租户类别:  <span class="houseInfoLabel">${currMaster.profile.class_=='G'?'租户':'单位'}</span>&nbsp;&nbsp;&nbsp;
					签约单位:<span class="houseInfoLabel">${currMaster.profile.company }</span>&nbsp;&nbsp;&nbsp;
					<br/><br/>
					</div>
					<br/><br/>
			
					</div>
					<div class="span2 " style="width: 100%">
					<h3>同住人信息</h3>
					
        	<table id="contentTable"
					class="table  table-bordered table-condensed">
					<thead>
						<tr>
							<th>照片</th>
							<th>姓名</th>
							<th>性别</th>
							<th>证件</th>
							<th>证件号</th>
							<th>籍贯</th>
							<th width="20%">地址</th>
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
									
									<td >
										<c:if test="${l.photo!=null }">
											<img id="" src="${ctxStatic}/photo/${l.photo}" style="border: 1px solid #ccc;width:80px;height:100px;">	
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
					</div>
	
			<br/><br/><br/>
		
		<div class="span2 " style="width: 100%">
					<h3>维修记录</h3>
					
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
								<br/><br/><br/><br/>
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
			<br/><br/><br/><br/><br/><br/><br/><br/>
		</div>
	</div>

</body>
</html>