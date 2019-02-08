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
	function changeURL(event){
		var printSubmitReport = document.querySelector("#printReport");
		if(event.checked) {
			printSubmitReport.href = "${ctx}/exportDoc/export?id="+event.value+"&name=rent&type=register";
		}else{
			printSubmitReport.href = "javascript:void(0)";
		}
	};
		$(document).ready(function() {
			var hs = $(".jbox #repaireBox #houseStatus");
			if(hs){
				hs.prop('disabled', false);
			}
			$("#delete").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				var repaireID = repaire.value;
				$.jBox.confirm("确认要删除该保修条目吗?","系统提示",function(v,h,f){
					if(v=="ok"){
						$.getJSON("${ctx}/sys/repaire/delete/" + repaireID,function(data){
							$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
							$("#searchForm2").submit();
							//location.reload();
						})
					}
				})
			});
			$("#update").click(function(){
				var repaire = document.querySelector('#contentTable input[type="radio"]:checked');
				if(!repaire) {
					$.jBox.tip('请选择具体的保修条目');
					return;
				}
				var repaireID = repaire.value;
				$.getJSON("${ctx}/sys/repaire/getRepaire/"+repaireID,function(data){
					console.log(data.repaireTime);
					// data.repairerTime = moment(data.repairerTime).format('YYYY-MM-DD');
					var html = Mustache.render($("#updateRepaireTemp").html(),data);
					$.jBox(html,{opacity:0,title:"保修条目修改",buttons:{"确认":"ok","关闭":"cancel"},width:580,height:450,loaded:function(e){
						$(".jbox #repaireBox #houseStatus").prop('disabled', false);
						$(".jbox #repaireBox #houseStatus").val(data.houseStatus);
						$(".jbox #repaireBox #repaireType").val(data.repaireType);
						$(".jbox #repaireBox #houseStatus").prop('disabled', 'disabled');
					},submit:function(v,h,f){
						if(v=="ok") {
							var inputs = document.querySelectorAll(".jbox #repaireBox input");
							var comments = $(".jbox #repaireBox textarea").val();
							// var select = document.querySelectorAll(".jbox #repaireBox select");
							var repaireType = $('.jbox #repaireBox #repaireType').val();
							var houseStatus = $('.jbox #repaireBox #houseStatus').val();
							var repairerPhone = $('.jbox #repaireBox #repairerPhone').val();
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
							}
							var obj = {};
							_.forEach(inputs,function(key){
								obj[key.id] = key.value;
							});
							/*_.forEach(select,function(key){
								obj[key.id] = key.value;
							});*/
							obj.comments = comments;
							obj.repaireType = repaireType;
							obj.houseStatus = houseStatus;
							obj.houseID = data.houseID;
							obj.masterID = data.masterID;
							//obj[select.id] = select.value;
							//obj['repairerTime'] = (new Date(obj['repairerTime'])).getTime();
							if(!obj['repairerTime']) {
								obj['repairerTime'] = (new Date()).getTime();
							}else{
								obj['repairerTime'] = (new Date(obj['repairerTime'])).getTime();
							}
							$.ajax({
								data:JSON.stringify(obj),
								type:"POST",
								url:'${ctx}/sys/repaire/update',
								contentType:'application/json;charset=utf-8',
								success:function(data){
									top.$.jBox.tip('修改完成');
									$("#searchForm2").attr("action","${ctx}/sys/repaire/search");
									$("#searchForm2").submit();
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
	<script type="text/template" id="updateRepaireTemp">
		<div id="repaireBox" class="container-fluid" style="width:500px;height:500px">
		<form id="updateRepaire">
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					编号:<input type="text" placeholder="输入编号" id="id" value="{{id}}" disabled>
    			</div>
				<div class="span6">
					报修电话:<input type="text" placeholder="输入报修电话号码" id="repairerPhone" value="{{repairerPhone}}">
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
    					楼层:<input path="floor" type="text" placeholder="输入楼层" id="floor" value="{{floor}}" disabled>
    			</div>
				<div class="span6">报修人:<input type="text" placeholder="输入报修人姓名" id="repairer" value="{{repairer}}">
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px">
				<div class="span6">
    					房号:<input type="text" placeholder="输入房号" id="roomNum" value="{{roomNum}}" disabled></input>
    			</div>
				<div class="span6">
					状态:<sapn>{{status}}</sapn>
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
					房间状态:<select id="houseStatus">
							<c:forEach var="statusItem" items="${houseStatus}">
								<option value="${statusItem}">${fns:getHouseState(statusItem)}</option>
							</c:forEach>
					</select>
				</div>
			</div>
			<div class="row-fluid" style="padding-left:10px;margin-top:10px;">
				<div class="span12">
    					报修内容:<textarea path="comments" class="area-content" type="text" placeholder="输入报修明细" id="comments" value="{{comments}}">{{comments}}</textarea>
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
							<li class="active"><a href="${ctx}/sys/repaire/listRepairesWithTenment/${ten.id}">${ten.name}</a></li>
						</c:if>
						<c:if test="${currentTen.name == null || ten.name != currentTen.name}">
							<li><a href="${ctx}/sys/repaire/listRepairesWithTenment/${ten.id}">${ten.name}</a></li>
						</c:if>
					</c:forEach>
				</ul>
</div></div>
	<div id="importBox" class="hide">
		<form:form id="searchForm" modelAttribute="repaire" action="${ctx}/sys/repaire/list" method="post" class="breadcrumb form-search ">
			
		</form:form>
	</div>
	<div class="container-fluid" style="overflow: hidden;">
        <div class="row-fluid">
	     
			
			<ul class="nav nav-tabs">
				<li ><a class="nav-size" href="${ctx}/sys/rent/list">业务查询</a></li>
				<li class="active nav-size"><a href="${ctx}/sys/repaire/list">报修登记</a></li>
			</ul>
			
			<div class="row-fluid repaire-search">
			<form:form id="searchForm2" method="post" modelAttribute="repaire" action="${ctx}/sys/repaire/search">
				<div class="row-fluid">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div class="hide"><form:hidden path="id" value="listRepaire" /></div>
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
					<div class="span3 search-input">姓名:<form:input path="repairer" type="text" id="repairer" style="width:100px;padding:0 0;"></form:input></div>
					<div class="span1 search-input"><input id="search" class="btn" type="submit" value="条件搜索"></div>
				</div>
				</form:form>
			</div>
		</div>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>物业</th><th>楼号</th>
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
		<button id="printReport" class="btn" style="margin-right:10px;" value="打印单据">打印单据</button>
		<button id="delete" class="btn" style="margin-right:10px;" value="删除">删除</button>
		<button id="update" class="btn" style="margin-right:10px;" value="修改">修改</button>
	</div>
	
</body>
</html>