
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.jbox-title-panel {
	background: #dc6c6c !important;
	text-align: center;
}

.area-content {
	min-height: 84px;
	min-width: 475px;
}
</style>
<script type="text/javascript">
			
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>
	");
		}

	});
	$(function() {
		//获取class为caname的元素 
		$(".caname")
				.click(
						function() {
							var td = $(this);
							var txt = td.text();
							var input = $("<input onkeyup='value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')' name='caname' type='text' style='width:50px;height:25px;' value='"
									+ txt + "'/>");
							td.html(input);
							input.click(function() {
								return false;
							});
							//获取焦点 
							input.trigger("focus");
							//文本框失去焦点后提交内容，重新变为文本 
							input
									.blur(function() {
										var newtxt = $(this).val();
										//判断文本有没有修改 
										if (newtxt != txt) {
											var newstyletxt = $("<input onkeyup='value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')' name='caname' type='text' style='width:50px;height:25px;color:#F00' value='"
													+ newtxt + "'/>")
											td.html(newstyletxt);
											//如果修改了就把这一条数据加到一个集合中 ajax 异步到session中保存

											/* 
											 *不需要使用数据库的这段可以不需要 
											var caid = $.trim(td.prev().text()); 
											//ajax异步更改数据库,加参数date是解决缓存问题 
											var url = "../common/Handler2.ashx?caname=" + newtxt + "&caid=" + caid + "&date=" + new Date(); 
											//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");） 
											//数据库的修改就在一般处理程序中完成 
											$.get(url, function(data) { 
											if(data=="1") 
											{ 
											alert("该类别已存在！"); 
											td.html(txt); 
											return; 
											} 
											alert(data); 
											td.html(newtxt); 
											}); 
											 */
										} else {

											td.html(newtxt);
										}
									});
						});
	});
	$(function() {
		/* 操作后的提示语 */

		$(".chkAll").click(function() {

			chk(); //全选全不选
		});

	});

	function chk() {
		var chkAllFlag = $(".chkAll").attr("checked");

		var chks = $(".chk");
		if (chkAllFlag == "checked") {
			$(".chk").attr("checked", true);
		} else {
			$(".chk").attr("checked", false);
		}
	}
	function checkSel() {

		//判断  复选框是否选中了复选框
		var chks = $(".chk");
		for (var i = 0; i < chks.length; i++) {
			if (chks[i].checked) {
				//提交 submit		
				$("#upform").submit();
				return false;
			}
		}

		return false;
	}
	function batchAudit() {

		//判断是否有复选框   被选中 如果有 那就传 复选框 的c

		var estateId = $("#estateIdc").val();
		var roomtypeid = $("#roomtypeIdc").val();
		var danyuanid = $("#danyuanidc").val();
		var no = $("#noc").val();

		layer.ready(function() {
			var index = layer.open({
				type : 2,
				title : '租金调整配置！',
				maxmin : true,
				area : [ '600px', '300px' ],
				/* content: 'http://layer.layui.com/test/welcome.html', */
				/* content: '${ctx}/syseckRepaire.jsp', */
				content : 'batchAuditcenter?estateId=' + estateId
						+ '&roomtypeid=' + roomtypeid + '&danyuanid='
						+ danyuanid + '&no=' + no,
				end : function() {
					location.reload();
				}
			});

		});

	}
</script>

</head>
<body>
	<div class="row-fluid">
		<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs" style="width: 100%">
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../rentAdjustmentAudit?estateId=${e.id}">${e.name }</a></li>


				</c:forEach>

			</ul>
		</div>
	</div>

	<form action="rentAdjustmentAudit" method="post">
<input type="hidden" name="estateId" value="${estateId }">
		<ul class="nav nav-tabs1" style="width: 100%">
			<div class="row-fluid repaire-search" style="margin-bottom: 0px;">

				<div class="span2 search-input" style="width: 150px;">

					单 元: <select id="danyuanid" name="danyuanid" style="width: 100px;">
						<option value="-1">请选择</option>

						<c:forEach var="f" items="${fns:getAllUnits()}">
							<c:if test="${danyuanid==f }">
								<option selected="selected" value="${f}">${f}单元</option>
							</c:if>

							<c:if test="${danyuanid!=f }">
								<option value="${f}">${f}单元</option>
							</c:if>
						</c:forEach>

					</select>


				</div>
				<div class="span2 search-input" style="width: 150px;">
					<c:if test="${condition.no!=null}">
													楼        号:<input name="no" type="text" id="master"
							style="width: 100px;" value="${condition.no}"></input>
					</c:if>
					<c:if test="${condition.no ==null}">
													楼        号:<input name="no" type="text" id="master"
							style="width: 100px;"></input>
					</c:if>

				</div>
				<div class="span2 search-input" style="width: 150px;">
					房 型: <select id="roomtypeId" name="roomtypeid"
						style="width: 100px;">
						<option value="-1">请选择</option>
						<c:forEach var="n" items="${roomtype}" varStatus="1">
							<option
								<c:if test="${roomtypeid eq n.id}">selected="selected"</c:if>
								value="${n.id}">${n.name}</option>
						</c:forEach>
					</select>


				</div>


				<input type="hidden" id="condition" value="${condition}" /> <input
					type="hidden" id="estateIdc" value="${condition.estateId}" /> <input
					type="hidden" id="roomtypeIdc" value="${condition.roomtypeId}" /> <input
					type="hidden" id="noc" value="${condition.no}" /> <input
					type="hidden" id="danyuanidc" value="${condition.danyuanid}" />
				<div class="span2 search-input">
					<input type="submit" class="btn" value="条件搜索" id="master"
						style="width: 150px; margin-left: 50px;"></input>
				</div>

			</div>
		</ul>

	</form>
	<sys:message content="${message}" />
	<form id="upform" action="updaterentAdjustmentAudit" method="post">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>选择<input type="checkbox" class="chkAll"
						style="position: relative; top: 2px;" /></th>
					<th>物业</th>
					<th>楼号</th>
					<th>单元</th>
					<th>房号</th>
					<th>房型</th>
					<th>租金</th>
					<th>新租金</th>
					<th>调价日期</th>
					<th>调价说明</th>
					<th>备注</th>
					<%--<th>角色</th> --%>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="11">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>

				<input type="hidden" name="chk" value="0" />
				<input type="hidden" name="caname" value="" />
				<c:forEach var="b" items="${page.list}" varStatus="status" begin="0"
					step="1">
					<tr style="text-align: center;">
						<td><input type="checkbox" name="chk" class="chk"
							value="${b.id}" /></td>
						<td>${b.estate.name}</td>
						<td>${b.buildingNo.buildingId}号楼</td>
						<td>${b.buildingNo.unitName}</td>


						<td>${b.roomNo }</td>
						<td>${b.roomType.name}</td>
						<td class="caname">${b.monthPrice}</td>
						<td>${b.rentMod}</td>
						<td><fmt:formatDate value="${b.decDate}" pattern="yyyy-MM-dd" /></td>
						<td>${b.reason}</td>
						<td>${b.remark}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination">${numpage}</div>

		<input type="hidden" name="estateId" value="${estateId}"> </input>

		<ul class="nav nav-tabs1">
			<li>
				<div>
					调价理由:<input name="reason" type="text" id="repaireApply"
						style="margin-right: 10px; width: 120px; margin-top: 5px" "/>
					调价日期:<input id="startTime" name="decDate" type="text"
						readonly="readonly" maxlength="20" class="Wdate required"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />





					<c:forEach var="role" items="${user.rolesList}">
						<c:if test="${role.id==1 }">

							<input type="button" id="repaireApply" class="btn"
								style="margin-right: 10px;" value="复选调价" onclick="checkSel();" />
						</c:if>
						<c:if test="${role.id!=1 }">
							<c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==49}">

									<input type="button" id="repaireApply" class="btn"
										style="margin-right: 10px;" value="复选调价" onclick="checkSel();" />
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>



				</div>
			</li>
		</ul>





		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">
				<ul class="nav nav-tabs1">
					<li><input type="button" id="repaireApply" class="btn"
						style="margin-right: 10px;" value="批量调价" onclick="batchAudit();" />
					</li>
				</ul>
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:forEach var="m" items="${role.moduleList }">
					<c:if test="${m.id==50}">

						<ul class="nav nav-tabs1">
							<li><input type="button" id="repaireApply" class="btn"
								style="margin-right: 10px;" value="批量调价" onclick="batchAudit();" />
							</li>
						</ul>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>

	</form>
</body>
</html>