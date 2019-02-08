<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
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
		.nav-size {
			font-size:20px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#onWorking").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				var repaireID = repaire.value;
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaireID,{random:Math.random()},function(data){
					if(data.status != "已提交维修方") {
						$.jBox.tip('请选择已经状态为已提交维修方的保修条目');
						return;
					}
					var html = Mustache.render($("#onWorkingRepaireTemp").html(),data);
					$.jBox(html,{opacity:0,title:"保修条目修改",buttons:{"确认":"ok","关闭":"cancel"},width:380,height:300,submit:function(v,h,f){
						if(v=="ok") {
							var inputs = document.querySelectorAll(".jbox #repaireBox input");
							var obj = {};
							obj.id= data.id;
							_.forEach(inputs,function(key){
								obj[key.id] = key.value;
							});
							//obj['repairerTime'] = (new Date(obj['repairerTime'])).getTime();
							if(!obj['startTime']) {
								obj['startTime'] = (new Date()).getTime();
							}else{
								obj['startTime'] = (new Date(obj['startTime'])).getTime();
								console.log("repaire['startTime']");
							}
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/submitThird',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('修改完成');
									$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
									$("#searchForm2").submit();
									// location.replace(location.href);
									//location.reload();
								}
							});
						}
					}})
				});
			});
			$("#finished").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				var repaireID = repaire.value;
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaireID,function(data){
					if(data.status != "维修中") {
						$.jBox.tip('请选择已经状态为维修中的保修条目');
						return;
					}
					var html = Mustache.render($("#finishRepaireTemp").html(),data);
					console.log("get data from repaire:"+ data);
					$.jBox(html,{opacity:0,title:"保修条目修改",buttons:{"确认":"ok","关闭":"cancel"},width:380,height:300,submit:function(v,h,f){
						if(v=="ok") {
							var inputs = document.querySelectorAll(".jbox #repaireBox input");
							var obj = {};
							obj.id = data.id;
							_.forEach(inputs,function(key){
								obj[key.id] = key.value;
							});
							//obj['repairerTime'] = (new Date(obj['repairerTime'])).getTime();
							if(!obj['startTime']) {
								obj['startTime'] = (new Date()).getTime();
							}else{
								obj['startTime'] = (new Date(obj['startTime'])).getTime();
							}
							if(!obj['endTime']) {
								obj['endTime'] = (new Date()).getTime();
							}else{
								obj['endTime'] = (new Date(obj['endTime'])).getTime();
							}
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/submitThird',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('修改完成');
									$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
									$("#searchForm2").submit();
									// location.replace(location.href);
									//location.reload();
								}
							});
						}
					}})
				});
			})
		});
		function page(n,s){
			
	    	if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
			$("#searchForm2").submit();
	    	return false;
	    }
	</script>
	<script type="text/template" id="onWorkingRepaireTemp">
		<div id="repaireBox" class="container-fluid" style="width:500px;height:650px">
		<form id="updateRepaire">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span11">
    					维修状态:<input type="text" id="status" value="维修中" disabled>
    			</div>
				<div class="span1"></div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span11">
    					维修开始时间:<input type="text" placeholder="输入开始时间" id="startTime" value="{{startTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20">
    			</div>
				<div class="span1"></div>
			</div>
		</form>
	  </div>
	</script>
	<script type="text/template" id="finishRepaireTemp">
		<div id="repaireBox" class="container-fluid" style="width:500px;height:650px">
		<form id="updateRepaire">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span11">
    					维修状态:<input type="text" placeholder="输入编号" id="status" value="维修完成" disabled>
    			</div>
				<div class="span1"></div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span11">
    					维修开始时间:<input type="text" placeholder="输入开始时间" id="startTime" value="{{startTime}}" disabled>
    			</div>
				<div class="span1"></div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span11">
    					维修结束时间:<input type="text" placeholder="输入结束时间" id="endTime" value="{{endTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20">
    			</div>
				<div class="span1"></div>
			</div>
		</form>
	  </div>
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form:form id="searchForm" modelAttribute="repaire" action="${ctx}/sys/repaire/list" method="post" class="breadcrumb form-search ">
			
		</form:form>
	</div>
	<div class="container-fluid" style="margin-top:15px">
        <div class="row-fluid">
			
			<div class="row-fluid repaire-search">
			<form:form id="searchForm2" method="post" modelAttribute="repaire" action="${ctx}/sys/repaire/search">
				<div class="row-fluid">
					<div class="hide"><form:hidden path="id" value="submit" /></div>
					<div class="hide"><form:hidden path="tenement" value="${currentTen.id}" /></div>
					<div class="span2 search-input">楼号:<form:select path="floor" id="floor" style="width:100px;padding:0 0;">
								<option value="-1">全部</option>
								<c:forEach var="f" items="${buildings}">
									<c:if test="${repaire.floor==f.name }">
										<option selected="selected" value="${f.name}">${f.name}号楼</option>
									</c:if>

									<c:if test="${repaire.floor!=f.name }">
										<option value="${f.name }">${f.name }号楼</option>
									</c:if>
								</c:forEach>
					</form:select></div>
					<div class="span3 search-input">单元号:<form:select path="building" id="building" style="width:100px;padding:0 0;">
						<option value="-1">全部</option>
								<c:forEach var="f" items="${fns:getAllUnits()}">
									<c:if test="${repaire.building==f }">
										<option selected="selected" value="${f}">${f}单元</option>
									</c:if>

									<c:if test="${repaire.building!=f }">
										<option value="${f}">${f}单元</option>
									</c:if>
								</c:forEach>
					</form:select></div>
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div class="span2 search-input">房号:<form:input path="roomNum" type="text" id="roomNum" style="width:100px;padding:0 0;"></form:input></div>
					<!-- <div class="span2">单位<input type="text" id="company"></div>  -->
					<div class="span3 search-input">姓名:<form:input path="repairer" type="text" id="repairer" style="width:100px;padding:0 0;"></form:input></div>
					<div class="span1 search-input"><input id="search" class="btn" type="submit" value="条件搜索"></div>
				</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- <ul class="nav nav-tabs">
		<li ><a href="${ctx}/sys/rent/list">业务查询</a></li>
		<li class="active"><a href="${ctx}/sys/repaire/list">报修登记</a></li>
	</ul> -->
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>物业</th>
		<th>楼号</th>
		<th>单元号</th>
		<th>房号</th><th>房型</th><th>面积</th><th>报修人</th>
		<th>报修电话</th><th>报修时间</th><th>状态</th><th>开始时间</th><th>结束时间</th><th>维修类型</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>
					<input type="radio" value="${user.id}" class="rent-check" name="rentCheck">
				</td>
				<td>${user.tenement}</td>
				<td>${user.floor}号楼</td>
				<td>${fn:substring(user.building, fn:length(user.building)-3,fn:length(user.building) )}</td>
				<td>${user.roomNum}</td>
				<td>${user.roomType}</td>
				<td>${user.roomSize}</td>
				<td>${user.repairer}</td>
				<td>${user.repairerPhone}</td>
				<td>${fns:formatDate(user.repairerTime)}</td>
				<td>${user.status}</td>
				<td>${fns:formatDate(user.startTime)}</td>
				<td>${fns:formatDate(user.endTime)}</td>
				<td>${user.repaireType}</td>
				<td>${user.comments}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	<div>
		<c:set var="flag" value="0"></c:set>
		 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 <c:set var="flag" value="1"></c:set>
					<button id="onWorking" class="btn" style="margin-right:10px;" value="维修中">维修中</button>
						</c:if><c:if test="${role.id!=1&&flag==0 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==63}">
						
					<button id="onWorking" class="btn" style="margin-right:10px;" value="维修中">维修中</button>
		  </c:if></c:forEach></c:if></c:forEach>
		  <c:set var="flag" value="0"></c:set>
		   <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
				    <c:set var="flag" value="1"></c:set>
					<button id="finished" class="btn" style="margin-right:10px;" value="维修完成">维修完成</button>
						</c:if><c:if test="${role.id!=1&&flag==0 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==64}">
						
					<button id="finished" class="btn" style="margin-right:10px;" value="维修完成">维修完成</button>
			</c:if></c:forEach></c:if></c:forEach>
	</div>
	
	
	
	
	
	
	
	
</body>
</html>