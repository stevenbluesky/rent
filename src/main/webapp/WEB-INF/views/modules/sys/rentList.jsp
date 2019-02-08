<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
		.search-input {
			height:20px;
			line-height:20px;
		}
		.nav-size {
			font-size:20px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#repaireApply").click(function(){
				
				var rent = document.querySelector('#contentTable input[type="radio"]:checked');
				if (!rent) {
					$.jBox.tip('请选择具体的业务');
					return;
				}
				var stateName=$.trim($("#contentTable input[type='radio']:checked").parents("tr").find(".state").text());
				if (stateName=="欠费") {
					$.jBox.tip('该房间已欠费，不能报修！');
					return;
				}
				
				
				var rentID = rent.value;
				$.getJSON("${ctx}/sys/rent/getRent/" + rentID,function(data){
					var html = Mustache.render($("#insertRepaireTemp").html(),data);
					$.jBox(html,{closeOnMouseleave:false,opacity:0,
							title:"报修申请",buttons:{"保存":"save","关闭":"cancel"},
							width:580,height:450,loaded:function(e){
								if(data.statusName && (data.statusName.indexOf('可租')>=0||data.statusName.indexOf('锁定')>=0||data.statusName.indexOf('空置维修')>=0)){
									$('.jbox #repaireBox #houseStatus').val('7');
									
								}else{
									$('.jbox #repaireBox #houseStatus').val('8');
								}
							},submit:function(v,h,f){
						var inputs = document.querySelectorAll(".jbox #repaireBox input");
						var comments = $(".jbox #repaireBox textarea").val();
						var select = document.querySelectorAll(".jbox #repaireBox select");
						var repaireType = $('.jbox #repaireBox #repaireType').val();
						var houseStatus =$('.jbox #repaireBox #houseStatus').val();
						var originHouseStatus ="";
						if(data.statusName&&data.statusName.indexOf("维修")<0)
							{
							  originHouseStatus =data.status;
							}
						var repairerPhone = $('.jbox #repaireBox #repairerPhone').val();
						if(v=="ok"){
							var obj = {};
							_.forEach(inputs,function(key){
								obj[key.id] = key.value;
							});
							_.forEach(select,function(select){
								obj[select.id]=select.value;
							});
							obj[comments.id] = comments.value;
							obj[select.id] = select.value;
							obj['masterID'] = data.masterID;
							obj['houseID'] = data.houseID;
							obj['repairerTime'] = moment(obj['repairerTime']).format('YYYY-MM-DD');
							if(obj['repairerTime'].indexOf('Invalid') >=0 ) {
								obj['repairerTime'] = moment().format('YYYY-MM-DD');
							}
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/add',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('报修完成');
									//location.href = location.href;
									$("#searchForm2").attr("action","${ctx}/sys/rent/rentSearch");
									$("#searchForm2").submit();
									// location.replace(location.href)
									//location.reload(true);
								}
							});
							
						}else if(v=="save"){
							//var comments = document.querySelector("#repaireBox textarea");
							console.log('we are heer');
							var reg = /^1[3|4|5|7|8][0-9]{9}$/;
							if(!repairerPhone || repairerPhone.trim() == ''){
								top.$.jBox.tip('请输入手机号码');
								return false;
							}
							if(repairerPhone && repairerPhone.trim() != '' && !reg.test(repairerPhone)){
								top.$.jBox.tip('请输入正确的手机号码');
								return false;
							}
							if(!comments || _.trim(comments) == '') {
								$.jBox.tip('请输入保修内容');
								return false;
							}else{
										//var inputs = document.querySelectorAll("#repaireBox input");
										// var comments = document.querySelector("#repaireBox textarea");
										//var select = document.querySelector("#repaireBox select")
										var obj1 = {};
										_.forEach(inputs,function(key){
											obj1[key.id] = key.value;
										});

										obj1.repaireType = repaireType;
										obj1.houseStatus = houseStatus;
										obj1.comments = comments;
										obj1.originHouseStatus=originHouseStatus;
										//obj1[select.id] = select.value;
										obj1['masterID'] = data.masterID;
										obj1['houseID'] = data.houseID;
										obj1['repairerTime'] = moment(obj1['repairerTime']).format('YYYY-MM-DD');
										if(obj1['repairerTime'].indexOf('Invalid') >=0 ) {
											obj1['repairerTime'] = moment().format('YYYY-MM-DD');
										}
										console.log('check the new obj1:'+ data.houseID);
										$.ajax({
											data:JSON.stringify(obj1),
											type:"POST",
											url:'${ctx}/sys/repaire/add',
											contentType:'application/json;charset=utf-8',
											success:function(data){
												top.$.jBox.tip('报修完成');
											//	location.href = location.href;
											$("#searchForm2").attr("action","${ctx}/sys/rent/rentSearch");
											$("#searchForm2").submit();
										//	 location.replace(location.href)
												//location.reload(true);
											}
										});
							}
						}
					}});
				});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm2").attr("action","${ctx}/sys/rent/rentSearch");
			$("#searchForm2").submit();
			//$("#search").submit();
	    	return false;
	    }
	</script>
	<script type="text/template" id="insertRepaireTemp">
		<div id="repaireBox" class="container-fluid" style="width:500px;height:500px">
		<form id="insertRepaire">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					编号:<input type="text" placeholder="输入编号" id="id" value="{{id}}" disabled>
    			</div>
				<div class="span6">
					报修电话:<input type="text" placeholder="输入报修电话号码" id="repairerPhone" value="{{phone}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					物业:<input type="text" placeholder="输入物业" id="tenement" value="{{tenement}}" disabled>
    			</div>
				<div class="span6">
					报修时间:<input type="text" placeholder="输入报修时间" id="repairerTime" value="{{repairerTime}}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					楼号:<input path="floor" type="text" placeholder="输入楼层" id="floor" value="{{floor}}" disabled>
    			</div>
				<div class="span6">报修人:<input type="text" placeholder="输入报修人姓名" id="repairer" value="{{name}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					房号:<input type="text" placeholder="输入房号" id="roomNum" value="{{roomNum}}" disabled></input>
    			</div>
				<div class="span6">
					状态:<input type="text" id="status" value="{{statusName}}" disabled></input>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					房型:<input type="text" placeholder="输入房屋型号" id="roomType" value="{{roomType}}" disabled></input>
    			</div>
				<div class="span6">
					维修类型:<select id="repaireType">
							<option value="正常维修" selected>正常维修</option>
							<option value="维保期维修">维保期维修</option>
							<option value="公共区域">公共区域</option>
					</select>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					面积:<input path="roomSize" type="text" placeholder="房间面积" id="roomSize" value="{{roomSize}}" disabled></input>
    			</div>
				<div class="span6">
					房间状态:<select id="houseStatus" disabled>
							<c:forEach var="statusItem" items="${houseStatus}">
								<option value="${statusItem}">${fns:getHouseState(statusItem)}</option>
							</c:forEach>
					</select>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
				<div class="span12">
    					报修内容:<textarea path="comments" class="area-content" type="text" placeholder="输入报修明细" id="comments"></textarea>
    			</div>
				<div class="span6">
				</div>
			</div>
		</form>
	  </div>
	</script>
</head>
<body>

<div class="row-fluid">
	<div class="span4" style="width: 100%">
				<ul class="nav nav-tabs">
	
		<c:forEach items="${tenements}" var="ten">
			<c:if test="${currentTen != null && ten.name == currentTen.name}">
				<li class="active"><a href="${ctx}/sys/rent/listRentWithTenment/${ten.id}">${ten.name}</a></li>
			</c:if>
			<c:if test="${currentTen.name == null || ten.name != currentTen.name}">
				<li><a href="${ctx}/sys/rent/listRentWithTenment/${ten.id}">${ten.name}</a></li>
			</c:if>
		</c:forEach>
	</ul>
</div>
</div>
		
			
		<div style="overflow: hidden;">
		<ul class="nav nav-tabs">
				<li class="active nav-size"><a href="${ctx}/sys/rent/list">业务查询</a></li>
				<li><a class="nav-size" href="${ctx}/sys/repaire/list">报修登记</a></li>
			</ul>
		</div>
	<div id="importBox" class="hide">
		<form:form id="searchForm" modelAttribute="rent" action="${ctx}/sys/rent/list" method="post" class="breadcrumb form-search ">
		
		</form:form>
	</div>
	<div class="container-fluid">
        <div class="row-fluid">
	        
			
			<div class="row-fluid repaire-search">
			<form:form id="searchForm2" method="post" modelAttribute="rent" action="${ctx}/sys/rent/rentSearch">
				<div class="row-fluid">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div class="hide"><form:hidden path="id" value="listRent" /></div>
					<div class="hide"><form:hidden path="tenement" value="${currentTen.id}" /></div>
					<div class="span2 search-input">楼号:<form:select path="floor" id="floor" style="width:100px;padding:0 0;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${buildings}">
									<c:if test="${rent.floor==f.name }">
										<option selected="selected" value="${f.name}">${f.name}号楼</option>
									</c:if>

									<c:if test="${rent.floor!=f.name }">
										<option value="${f.name }">${f.name }号楼</option>
									</c:if>
								</c:forEach>
					</form:select></div>
					<div class="span3 search-input"> 单元号
					
					<form:select path="building" id="building" style="width:100px;padding:0 0;">
						<option value="-1">全部</option>
									<c:forEach var="f" items="${fns:getAllUnits()}">
									<c:if test="${rent.building==f }">
										<option selected="selected" value="${f}">${f}单元</option>
									</c:if>

									<c:if test="${rent.building!=f }">
										<option value="${f}">${f}单元</option>
									</c:if>
								</c:forEach>
					</form:select></div>
					<div class="span2 search-input">房号:<form:input path="roomNum" class="padding-no" type="text" id="roomNum" style="width:100px;padding:0 0;"></form:input></div>
					<!-- <div class="span2">单位<input type="text" id="company"></div>  -->
					<div class="span3 search-input">姓名:<form:input path="name" type="text" id="name" style="width:180px;padding:0 0;"></form:input></div>
					<div class="span1 search-input"><input id="search" class="btn" type="submit" value="条件搜索"></div>
				</div>
				</form:form>
			</div>
		</div>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>编号</th><th>状态</th><th>姓名</th><th>性别</th><th>电话号码</th><th>单位</th><th>物业</th><th>楼号</th><th>单元</th><th>房号</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>
					<input type="radio" value="${user.id}" class="rent-check" name="rentCheck">
				</td>
				<td>${user.id}</td>
				<td class="state">${fns:getHouseState(user.status)}</td>
				<td>${user.name}</td>
				<td>${fns:getGender(user.gender)}</td>
				<td>${user.phone}</td>
				<td>${user.company}</td>
				<td>${user.tenement}</td>
				<td>${user.floor}号楼</td>
				<td>
					${fn:substring(user.building, fn:length(user.building)-3,fn:length(user.building) )}
				
				</td>
				<td>${user.roomNum}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }"> 
			 
					<div>
		<button id="repaireApply" class="btn" style="margin-right:10px;" value="报修申请">报修申请</button>
	</div>
						</c:if> <c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==54}">
						
					<div>
		<button id="repaireApply" class="btn" style="margin-right:10px;" value="报修申请">报修申请</button>
	</div>
			 </c:if></c:forEach></c:if></c:forEach>
</body>
</html>