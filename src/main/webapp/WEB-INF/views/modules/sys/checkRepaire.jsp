<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">	
		.repaire-search {
			margin-left:5px !important;
			margin-top: 8px;
		}
		.repaire-search input{
			width:100px;
		}
		.repaire-search .search-input{
			height: 20px;
			line-height:20px;
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
	function changeURL(event){
		var printSubmitReport = document.querySelector("#printReport");
		if(event.checked) {
			printSubmitReport.href = "${ctx}/exportDoc/export?id="+event.value+"&name=交办单&type=accept";
		}else{
			printSubmitReport.href = "javascript:void(0)";
		}
	};
	
	function sum() {
		var allCounts = document.querySelectorAll(".jbox .pay-items");
		var count = 0;
		var b = _.chain(allCounts).map(function(e){
			count += e.querySelector("#count").value * e.querySelector("#price").value;
			return e;
		}).value();
		document.querySelector(".jbox #sum").innerHTML = count;
	};
	
	function deleteItem(e) {
		e.parentElement.parentElement.remove();
		//parentItem.remove();
		sum();
	};
		$(document).ready(function() {
			$("#checkRepaire").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				var repaireID = repaire.value;
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaireID,function(data){
					if(!data.status || data.status == '已登记' || data.status=='完成检查') {
						
					}else{
						$.jBox.tip('请选择状态已登记或者没有状态的保修条目');
						return;
					}
					var repaire = {};
					var states = {};
					states.state1 ={
						content:Mustache.render($("#repaireBox").html(),data),
						buttons:{"下一步":"ok","取消":"cancel"},
						title: "现场查验",
						opacity:0,
						submit: function(v,h,f) {
							if(v=="ok") {
								var inputs = document.querySelectorAll(".jbox #checkRepaire input");
								var comments = $(".jbox #checkRepaire textarea").val();
								_.forEach(inputs,function(key){
									repaire[key.id] = key.value;
								});
								repaire.comments = comments;
								if(!repaire['repairerTime']) {
									repaire['repairerTime'] = (new Date()).getTime();
								}else{
									repaire['repairerTime'] = (new Date(obj['repairerTime'])).getTime();
								}
								$.ajax({
									data:JSON.stringify(repaire),
									type:"POST",
									url:'${ctx}/sys/repaire/repaireCheck',
									contentType:'application/json;charset=utf-8',
									success:function(data){
										$.jBox.nextState();
										// location.reload();
									}
								});
								// $.jBox.nextState();
							} else {
								return true;
							}
							return false;
						}
					}
					states.state2 = {
						content:Mustache.render($("#checkRepaireTemp").html(),data),
						buttons:{"下一步":"ok","取消":"cancel","增加条目":"add"},
						//title:"提交审批",
						opacity:0,
						submit: function(v,h,f) {
							if(v == 'ok') {
								var inputs = document.querySelectorAll(".jbox #submitRepaireBox .pay-items");
								var sum = 0;
								repaire.payments = _.chain(inputs).map(function(item){
									var detail = item.querySelectorAll("input"),obj={};
									_.forEach(detail,function(e){
										obj[e.id] = e.value;
									});
									obj.repaireID = data.id;
									sum += !obj.count || !obj.price ? 0 : obj.count*obj.price;
									return obj;
								}).value();
								if(!data.payment){
									_.forEach(document.querySelectorAll("#payment"),function(payment){
										payment.value = sum;
									});
								}
								$.ajax({
									data:JSON.stringify(repaire),
									type:"POST",
									url:'${ctx}/sys/repaire/repaireCheck',
									contentType:'application/json;charset=utf-8',
									success:function(data){
										$.jBox.nextState();
										// location.reload();
									}
								});
							} else if(v=='add'){
								var emptyItems ={
									project:'',
									count:'',
									price:'',
									repaireID: data.id
								}
								if(!data.payments) {
									data.payments = [];
								}
								var inputs = document.querySelectorAll(".jbox #submitRepaireBox .pay-items");
								var count = 0;
								data.payments = _.chain(inputs).map(function(item){
									var detail = item.querySelectorAll("input"),obj={};
									_.forEach(detail,function(e){
										obj[e.id] = e.value;
									});
									obj.repaireID = data.id;
									count += !obj.count || !obj.price ? 0 : obj.count*obj.price;
									return obj;
								}).value();
								data.payments.push(emptyItems);
								html = Mustache.render($("#checkRepaireTemp").html(),data);
								$.jBox.setContent(html);
								document.querySelector(".jbox #sum").innerHTML = count;
							}else {
								$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
								$("#searchForm2").submit();
								//location.reload();
								return true;
							}
							return false;
						}
					}
					states.state3 = {
						content:Mustache.render($("#lastStateBox").html(),data),
						buttons:{'审批':"1",'无需审批':"2"},//,'关闭':"3"},
						title:"完成查验",
						opacity:0,
						submit: function(v,h,f) {
							repaire.payments = null;
							if(v == "1") {
								var inputs = document.querySelectorAll(".jbox #approver input");
								var obj = {id:data.id};
								_.forEach(inputs,function(key){
									obj[key.id] = key.value;
								});
								$.ajax({
									data:JSON.stringify(obj),
									type:"POST",
									url:'${ctx}/sys/repaire/repaireCheck',
									contentType:'application/json;charset=utf-8',
									success:function(data){
										$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
										$("#searchForm2").submit();
										//location.reload();
									}
								});
							}else if(v == "2"){
								var obj1 = {id:data.id};
								obj1.payment = '-1';
								obj1.checker = '-1';
								$.ajax({
									data:JSON.stringify(obj1),
									type:"POST",
									url:'${ctx}/sys/repaire/repaireCheck',
									contentType:'application/json;charset=utf-8',
									success:function(data){
										$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
										$("#searchForm2").submit();
									//	location.reload();
									}
								})
							}else{
								$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
								$("#searchForm2").submit();
								//location.reload();
								return false;
							}
							// return false;
						}
					}
					$.jBox.open(states,'现场查验',750,'auto');
				});
			})
		});
		function page(n,s) {
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			//$("#searchForm").attr("action","${ctx}/sys/repaire/check");
			//$("#searchForm").submit();
			$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
			$("#searchForm2").submit();
	    	return false;
	    }
	</script>
	<script type="text/template" id="checkRepaireTemp">
		<div id="submitRepaireBox" class="container-fluid" style="width:600px;height:450px">
		<!--<div class="row-fluid">
			<button class="btn" onclick="addItem()">增加条目</button>
			<button class="btn" id="addItem">增加条目</button>
		</div>-->
		<div clas="item-container">
		<div class="row-fluid">
			<div class="span3" style="text-align:center;height:34px;line-height:34px;">项目</div>
			<div class="span3" style="text-align:center;height:34px;line-height:34px;">数量</div>
			<div class="span3" style="text-align:center;height:34px;line-height:34px;">单价</div>
			<div class="span3" style="text-align:center;height:34px;line-height:34px;">操作</div>
		</div>
		{{#payments}}
		<div class="row-fluid pay-items">
            <div class="hidde"><input type="hidden" id="paymentID" value="{{paymentID}}"></div>
			<div class="hidde"><input type="hidden" id="repaireID" value="{{repaireID}}"></div>
			<div class="span3"><input type="text" id="project" value="{{project}}" style="width:100px" required></div>
			<div class="span3"><input type="text" required id="count" value="{{count}}" onchange="sum()" style="width:100px"></div>
			<div class="span3"><input type="text" required id="price" value="{{price}}" onchange="sum()" style="width:100px"></div>
			<div class="span2"><button class="btn" id="deleteItem" onclick="deleteItem(this)" style="width:100px">清除</button></div>
		</div>
        {{/payments}}
		</div>
		<div style="float:right;">
			合计:<span id="sum">{{sum}}</span>
		</div>
		</div>
	</script>
</head>
<body>
	<div id="lastStateBox" class="container-fluid hide" style="width:400px;height:250px">
		<div id="approver" class="row-fluid" style="padding-left:10px;margin-top:10px">
			<div class="span12">
				维修金:<input type="text" placeholder="输入金额" id="payment" value="{{payment}}">
			</div>
			
			<div class="span12" style="margin-left: 0px;">
				提交审批人:<input type="text" placeholder="请输入审批人" id="checker" value="{{checker}}">
			</div>
			
			
		</div>
	</div>
	<div id="repaireBox" class="container-fluid hide" style="width:750px;height:650px">
		<form id="checkRepaire">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span4">
    					维修编号:<input type="text" placeholder="输入编号" id="id" value="{{id}}" disabled>
    			</div>
				<div class="span4">
					房型:<input type="text" placeholder="输入房型" id="roomType" value="{{roomType}}">
				</div>
				<div class="span4">
					报修时间:<input type="text" placeholder="输入报修时间" id="repairerTime" value="{{repairerTime}}" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" maxlength="20">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span4">
    					楼号:<input type="text" placeholder="输入房号" id="floor" value="{{floor}}">
    			</div>
				<div class="span4">
					面积:<input type="text" placeholder="输入面积" id="roomSize" value="{{roomSize}}">
				</div>
				<div class="span4">
					状态:<input type="text" placeholder="输入报修状态" id="status" value="{{status}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span4">
    					物业:<input type="text" id="tenement" value="{{tenement}}">
    			</div>
				<div class="span4">
					房号:<input type="text" placeholder="输入房号" id="roomNum" value="{{roomNum}}">
				</div>
				<div class="span4">
					工程量:<input type="text" placeholder="预估工程量" id="work" value="{{work}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span4">
    				单元号:<input type="text" placeholder="输入单元号" id="building" value="{{building}}">
    			</div>
				<div class="span4">
					报修设备:<input type="text" placeholder="输入报修设备" id="equipment" value="{{equipment}}">
				</div>
				<div class="span4">
					验收人:<input type="text" placeholder="输入验收人名字" id="checker" value="{{checker}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
				<div class="span12">
    					报修内容:<textarea path="comments" id="comments" class="area-content" type="text" placeholder="输入报修明细" id="comments" value="{{comments}}">{{comments}}</textarea>
    			</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
				<div class="controls">
					<input id="nameImage" name="photo" htmlEscape="false" maxlength="255" class="input-xlarge hide" hidden value="{{nameImage}}"/>
					<sys:ckfinder input="nameImage" pre="jbox" type="images" uploadPath="/photo" masterID="{{masterID}}" selectMultiple="true" maxWidth="100" maxHeight="100"/>
				</div>
			</div>
		</form>
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
							<li class="active"><a href="${ctx}/sys/repaire/listWithTenment/${ten.id}">${ten.name}</a></li>
						</c:if>
						<c:if test="${currentTen.name == null || ten.name != currentTen.name}">
							<li><a href="${ctx}/sys/repaire/listWithTenment/${ten.id}">${ten.name}</a></li>
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
					<div class="hide"><form:hidden path="id" value="check" /></div>
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
					<div class="span2 search-input">房号:<form:input path="roomNum" type="text" id="roomNum" style="width:100px;padding:0 0;"></form:input></div>
					<!-- <div class="span2">单位<input type="text" id="company"></div>  -->
					<div class="span2 search-input">姓名:<form:input path="repairer" type="text" id="repairer" style="padding:0 0;"></form:input></div>
					<div class="span2 search-input"><input id="search" class="btn" type="submit" value="条件搜索"></div>
				</div>
				</form:form>
			</div>
			
		</div>
		
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>物业</th>
		<th>楼号</th>
		<th>单元号</th>
		<th>房号</th><th>房型</th><th>面积</th><th>报修人</th>
		<th>报修电话</th><th>报修时间</th><th>状态</th><th>维修类型</th><th>备注</th>
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>
					<input type="radio" value="${user.id}" class="rent-check" name="rentCheck" onchange="changeURL(this)">
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
		 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
			 
					<button id="checkRepaire" class="btn" style="margin-right:10px;" value="现场查验">现场查验</button>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==105}">
						
					<button id="checkRepaire" class="btn" style="margin-right:10px;" value="现场查验">现场查验</button>
			 </c:if></c:forEach></c:if></c:forEach>
	
		
		 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<a href="javascript:void(0)" id="printReport" class="btn" style="margin-right:10px;" value="打印单据">打印单据</a>
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==54}">
						
					<a href="javascript:void(0)" id="printReport" class="btn" style="margin-right:10px;" value="打印单据">打印单据</a>
			 </c:if></c:forEach></c:if></c:forEach>
	</div>
	
</body>
</html>