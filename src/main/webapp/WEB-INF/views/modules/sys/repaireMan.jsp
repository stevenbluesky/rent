<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">	
		.repaire-search {
			margin-left:5px !important;
			margin-top: 8px;
		}
		.repaire-search input{
			width:100px;
			padding: 0 0 !important;
		}
		.repaire-search .search-input{
			height: 20px;
			line-height:20px;
		}
		.area-content{
			min-height: 84px;
			min-width: 475px;
		}
		.padding-left {
			padding-left: 10px;
		}
	</style>
	<script type="text/javascript">
		function changeURL(event){
			var printSubmitReport = document.querySelector("#printSubmitReport");
			var printAcceptReport = document.querySelector("#printAcceptReport");
			if(event.checked) {
				printSubmitReport.href = "${ctx}/exportDoc/export?id="+event.value+"&name=rent&type=approve";
				printAcceptReport.href = "${ctx}/exportDoc/export?id="+event.value+"&name=rent&type=accept";
			}else{
				printSubmitReport.href = "javascript:void(0)";
				printAcceptReport.href = "javascript:void(0)";
			}
		};
		
		
		$(document).ready(function() {
			$("#status").select2('disable');
			$.getJSON("${ctx}/sys/repaire/getThird",function(data){
				window.thirdUsers = _.chain(data).map(function(usr){
					return {
						text:usr.name,
						id:usr.id
					}
				}).value();
			});
			$("#submitRepaire").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaire.value,function(data){
					if(data.status.indexOf("完成检查") < 0) {
						$.jBox.tip('请选择状态为完成检查的保修条目');
						return;
					}
					var html = Mustache.render($("#submitThird").html(),data);
					$.jBox(html,{opacity:0,
								title:"提交维修方",buttons:{"确定":"ok","取消":"cancel"},
								loaded:function(e){
						console.log('check the new loaded function');
						$(".jbox #thirdSubmit #thirdCom").select2({data:window.thirdUsers});
						$(".jbox #thirdSubmit #thirdCom").select2('val',data.thirdCom);
						$(".jbox #thirdSubmit #thirdStatus").select2({data:[{text:'加速',id:'加速'},{text:'不加速',id:'不加速'}]});
						$(".jbox #thirdSubmit #thirdStatus").select2('val',data.thirdStatus);
						var startTime = document.querySelector(".jbox #thirdSubmit #startTime");
						startTime.readOnly = true;
						if(!data.startTime) {
							startTime.value = moment().format("YYYY-MM-DD");
						}
					},submit:function(v,h,f){
						if (v==='ok'){
							var inputs = document.querySelectorAll(".jbox #thirdSubmit input");
							var thirdCom = $(".jbox #thirdSubmit #thirdCom").select2("val");
							var thirdStatus = $(".jbox #thirdSubmit #thirdStatus").select2("val");
							var obj = {}
							obj.id = data.id;
							if(!thirdCom || thirdCom.trim() == ''){
								top.$.jBox.tip('请选择第三方！');
								return false;
							}
							if(thirdCom && thirdCom.length > 0) {
								obj.thirdCom = thirdCom;
							}
							if(thirdStatus && thirdStatus.length > 0){
								obj.approvalStatus = thirdCom;
							}
							_.forEach(inputs,function(key){
								obj[key.id] = key.value;
							});
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/submitThird',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('提交完成');
									$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
									$("#searchForm2").submit();
									//location.reload();
								}
							});
						}
					}})
				});
			});
			$("#acceptance").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaire.value,function(data){
					if(!(data.status == "维修完成" || data.status == "不合格")) {
						$.jBox.tip('请选择状态为维修完成或者不合格的保修条目');
						return;
					}
					var html = Mustache.render($("#validate_update").html(),data);
					$.jBox(html,{opacity:0,title:"维修检验",buttons:{"确定":"ok","取消":"cancel"},loaded:function(e){
						console.log('check the new loaded function for status');
						$(".jbox #updateValidate #thirdCom").select2({data:window.thirdUsers});
						$(".jbox #updateValidate #thirdCom").select2('val',data.thirdCom);
						$(".jbox #updateValidate #status").select2({data:[{text:'合格',id:'合格'},{text:'不合格',id:'不合格'}]});
						var validateTime = document.querySelector(".jbox #updateValidate #validateTime");
						if(!data.validateTime) {
							validateTime.value = moment().format("YYYY-MM-DD");
						}
					},submit:function(v,h,f){
						if (v==='ok'){
							var status = $(".jbox #updateValidate #status").select2("val");
							console.log($(".jbox #updateValidate #status")[0].value);
							var inputs = document.querySelector('.jbox #updateValidate #validateTime');
							if(!status || status.trim() == ''){
								top.$.jBox.tip("请输入验收状态！");
								return false;
							}
							var obj = {
								id:data.id,
								status: status,
								validateTime: inputs.value,
								houseID:data.houseID,
								houseStatus:data.houseStatus,
								originHouseStatus:data.originHouseStatus
							}
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/validate',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('提交完成');
									$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
									$("#searchForm2").submit();
									//location.reload();
								}
							});
						}
					}});
					$(".jbox #updateValidate #status").select2();
				});
			});
		});
		function page(n,s) {
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
			$("#searchForm2").submit();
	    	return false;
	    	
	    }
	</script>
</head>
<body>
	<div id="submitThird" class="hide">
		<div class="container-fluid" id="thirdSubmit">
			<input type="hidden" id="status" value="已提交维修方"/>
			<div class="row-fluid">
				维修方：<input type="hidden" id="thirdCom" style="width:200px;margin-left:10px;margin-top:10px"/>
				<!-- <select id="thirdCom" name="thirdCom">
						<c:forEach var="user" items="${thirdUsers}">
						<option value="${user.id }">${user.name }</option>
						</c:forEach>
				</select> -->
			</div>
			<div class="row-fluid">
				提交人：<input id="repairer" type="text" value="{{repairer}}" style="margin-left:10px;margin-top:10px"/>
			</div>
			<div class="row-fluid">
				提交时间：<input id="startTime" type="text" value="{{startTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20"/>
			</div>
			<div class="row-fluid">
				类型：<input type="hidden" id="thirdStatus" style="width:200px;margin-left:20px;margin-bottom:10px"/>
				<!-- <select id="thirdStatus">
						<option value="1">加速</option>
						<option value="2">不加速</option>
				</select> -->
			</div>
		</div>
	</div>
	<div id="validate_update" class="hide">
		<div class="container-fluid" id="updateValidate">
			<div class="row-fluid padding-left">
				维修方：<input id="thirdCom" type="hidden" disabled style="margin-top:10px;margin-left:40px">
			</div>
			<div class="row-fluid padding-left">
				维修开始时间：<input id="startTime" type="date" value="{{startTime}}" disabled style="margin-left:5px"/>
			</div>
			<div class="row-fluid padding-left">
				维修结束时间：<input id="endTime" type="date" value="{{endTime}}" disabled style="margin-left:5px"/>
			</div>
			<div class="row-fluid padding-left">
				验收状态：<input type="hidden" id="status" style="width:200px;margin-left:30px;"/>
				<!-- <select id="status">
						<option value="1">验收合格</option>
						<option value="2">验收不合格</option>
				</select> -->
			</div>
			<div class="row-fluid padding-left">
				验收时间：<input id="validateTime" type="text" value="{{validateTime}}" style="margin-top:10px;margin-left:30px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20"/>
			</div>
		</div>
	</div>
	<div id="importBox" class="hide">
		<form:form id="searchForm" modelAttribute="repaire" action="${ctx}/sys/repaire/list" method="post" class="breadcrumb form-search ">
		
		</form:form>
	</div>
  
 <div >
 
 <div class="row-fluid" style="">
				<ul class="nav nav-tabs">
					<c:forEach items="${tenements}" var="ten">
						<c:if test="${currentTen != null && ten.name == currentTen.name}">
							<li class="active"><a href="${ctx}/sys/repaire/manListWithTenment/${ten.id}">${ten.name}</a></li>
						</c:if>
						<c:if test="${currentTen.name == null || ten.name != currentTen.name}">
							<li><a href="${ctx}/sys/repaire/manListWithTenment/${ten.id}">${ten.name}</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
   
   	</div>
    <div class="container-fluid">
        <div class="row-fluid">
	        
			
			<div class="row-fluid repaire-search">
			<form:form id="searchForm2" method="post" modelAttribute="repaire" action="${ctx}/sys/repaire/search">
				<div class="row-fluid">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div class="hide"><form:hidden path="id" value="manage" /></div>
					<div class="hide"><form:hidden path="tenement" value="${currentTen.id}" /></div>
					<div class="span3 search-input" style="margin-left:0">楼号:<form:select path="floor" id="floor" style="width:100px;padding:0 0;">
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
					<div class="span3 search-input" style="margin-left:0">单元号:<form:select path="building" id="building" style="width:100px;padding:0 0;">
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
					<div class="span3 search-input" style="margin-left:0">房&nbsp;&nbsp;号&nbsp;&nbsp;:<form:input path="roomNum" type="text" id="roomNum"></form:input></div>
					<!-- <div class="span2">单位<input type="text" id="company"></div>  -->
					<div class="span2 search-input" style="margin-left:0">姓名:<form:input path="repairer" type="text" id="repairer"></form:input></div>
				</div>
				<div class="row-fluid" style="margin-top:5px;">
					<div class="span3 search-input" style="margin-left:0">起始时间:<form:input path="startTime" type="text" id="startTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20"></form:input></div>
					<div class="span3 search-input" style="margin-left:0">截止时间:<form:input path="endTime" type="text" id="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20"></form:input></div>
					<div class="span3 search-input" style="margin-left:0">状态:<form:select path="status" id="status" items="${statusList}" itemValue="name" itemLabel="name" style="width:180px"></form:select></div>
					<div class="span2 search-input"><input id="search" class="btn" type="submit" value="条件搜索"></div>
				</div>
			</div>
		</div>
			</form:form>
		</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>物业</th>
		<th>楼号</th>
		<th>单元号</th>
		<th>房号</th>
		<th>房型</th><th>面积</th><th>报修人</th>
		<th>报修电话</th><th>报修时间</th><th>状态</th><th>维修类型</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>
					<input type="radio" value="${user.id}" class="rent-check" name="rentCheck" onclick="changeURL(this)" />
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
				<td>${user.repaireType}</td>
				<td>${user.comments}</td>
			</tr> 
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div>
		
		<!-- <button id="acceptance" class="btn" style="margin-right:10px;" value="验收">验收</button> -->
		
		
		
		 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<button id="submitRepaire" class="btn" style="margin-right:10px;" value="提交维修方">提交维修方</button>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==57}">
						
					<button id="submitRepaire" class="btn" style="margin-right:10px;" value="提交维修方">提交维修方</button>
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<a href="javascript:void(0)" id="printSubmitReport" class="btn" style="margin-right:10px;" value="打印交办单">打印交办单</a>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==58}">
						
					<a href="javascript:void(0)" id="printSubmitReport" class="btn" style="margin-right:10px;" value="打印交办单">打印交办单</a>
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<button id="acceptance" class="btn" style="margin-right:10px;" value="验收">验收</button>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==59}">
						
					<button id="acceptance" class="btn" style="margin-right:10px;" value="验收">验收</button>
			 </c:if></c:forEach></c:if></c:forEach>
			 
			 
			  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<a href="javascript:void(0)" id="printAcceptReport" class="btn" style="margin-right:10px;" value="打印验收单">打印单据</a>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==60}">
						
					<a href="javascript:void(0)" id="printAcceptReport" class="btn" style="margin-right:10px;" value="打印验收单">打印单据</a>
			 </c:if></c:forEach></c:if></c:forEach>
	</div>
	
</body>
</html>