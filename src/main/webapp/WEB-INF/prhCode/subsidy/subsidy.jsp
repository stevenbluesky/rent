<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" />
<title>补贴管理</title>
<style type="text/css">
<!--
body, div, ul, li {
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: left;
}
.active>a{
    border-top: 2px solid #cc0000 !important;
	color: #cc0000 !important;
	font-weight: bold;
}

a:link {
	color: #444547;
	text-decoration: none;
}

a:visited {
	color: #444547;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}

ul {
	list-style: none;
}

.main {
	clear: both;
	padding: 8px;
	text-align: left;
}
/*第一种形式*/
#tabs0 {
	height: 800px;
	width: 100%;
	border: 1px solid #red;
	background-color: #f2f6fb;
}

.menu0 {
	width: 400px;
}

.menu0 li {
	display: block;
	float: left;
	padding: 4px 0;
	width: 100px;
	text-align: center;
	cursor: pointer;
	background: orange;
}

.menu0 li.hover {
	background: #ff0;
}

#main0 .menu0 {
	display: none;
}

#main0 .menu0.block {
	display: block;
}
/*第二种形式*/
#tabs {
	text-align: left;
	width: 400px;
}

.menu1box {
	position: relative;
	overflow: hidden;
	height: 22px;
	width: 400px;
	text-align: left;
}

#menu1 {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1;
}

#menu1 li {
	float: left;
	display: block;
	cursor: pointer;
	width: 72px;
	text-align: center;
	line-height: 21px;
	height: 21px;
}

#menu1 li.hover {
	background: #fff;
	border-left: 1px solid #333;
	border-top: 1px solid #333;
	border-right: 1px solid #333;
}

.main1box {
	clear: both;
	margin-top: -1px;
	border: 1px solid #333;
	height: 181px;
	width: 400px;
}

#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}
-->
</style>
<link rel="stylesheet" href="css/page.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/page.js"></script>

<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		$("#tabs0 li").click(
				function() {
					$(this).addClass("hover").siblings().removeClass("hover");
					var estateId = $(this).find("input").val();
					location.href = "findSubsidyByEstatePaged.do?estateId="
							+ estateId + "&currpage=1";
				});
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

	function toEdit() {
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环

		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择要修改的补贴！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个补贴！");
			return;
		}
		location.href = "toSubsidyEdit.do?id=" + id;
	}

	function del() {

		var count = 0;
		var chk = $(".chk");
		//循环
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				count++;
			}

		});

		if (count == 0) {
			$.jBox.tip("请选择要删除的补贴！");
			$("#myForm").submit(function() {
				return false;
			});
			return;
		}
		if (!confirm("确认删除吗?")) {
			$("#myForm").submit(function() {
				return false;
			});
		}
		return true;
	}
</script>


</head>
<body>

	<!--第一种形式-->
	<div id="tabs0">
		<ul class="nav nav-tabs">

			<c:forEach var="e" items="${estates}" varStatus="status">
				<c:if test="${estateId==e.id }">
					<li class="active"><a
						href='findSubsidyByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
				<c:if test="${estateId!=e.id  }">
					<li><a
						href='findSubsidyByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
			</c:forEach>
		</ul>

		<div class="main" id="main0">

			<form id="myForm" action="subsidyDel.do" method="post">
				<input type="hidden" name="estateId" value="${estateId}" />

				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
						<th rowspan="3"><input type="checkbox" class="chkAll"
							style="position: relative; top: 2px;" />选择</th>
						<th rowspan="3">开始时间</th>
						<th rowspan="3">结束时间</th>
						<th rowspan="3">每人保障面积</th>
						<th rowspan="3">最低保障面积</th>
						<th rowspan="3">最高保障面积</th>
						<th rowspan="3">最大面积</th>
						<th rowspan="3">单价</th>
						<th colspan="${fn:length(subsidyTypes)*2}" style="text-align: center;">租金补贴比例</th>


					</tr>


					<tr
						style="background-color: #008080; text-align: center; vertical-align: middle;">

						<th colspan="${fn:length(subsidyTypes)}">保障面积内</th>
						<th colspan="${fn:length(subsidyTypes)}">保障面积外</th>

					</tr>

					<tr
						style="background-color: #008080; text-align: center; vertical-align: middle;">
						
						<c:forEach var="sType" items="${subsidyTypes}">
							<th>${sType.name }</th>
						</c:forEach>
						<c:forEach var="sType" items="${subsidyTypes}">
							<th>${sType.name }</th>
						</c:forEach>
					</tr>

					<c:forEach var="b" items="${page.list }">
						<tr style="text-align: center;">
							<td><input type="checkbox" name="chk" class="chk"
								value="${b.id}" /></td>
							<%-- <td>${b.beginDate }</td>
						<td>${b.endDate }</td> --%>
							<td><fmt:formatDate value="${b.beginDate}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:if test="${empty b.endDate}">至今</c:if> <c:if
									test="${not empty b.endDate}">
									<fmt:formatDate value="${b.endDate}" pattern="yyyy-MM-dd" />
								</c:if></td>

							<td>${b.perOneArea }</td>
							<td>${b.minSafeArea }</td>
							<td>${b.maxSafeArea }</td>
							<td>${b.maxArea }</td>
							<td>${b.price }</td>
							<c:forEach var="in" items="${b.in}">
								<td><fmt:formatNumber value="${in.value }"   type="percent"   pattern="#0.00%" />
								</td>
							</c:forEach>

							<c:forEach var="out" items="${b.out}">
								<td><fmt:formatNumber value="${out.value }"  type="percent" pattern="#0.00%"/>
								</td>
							</c:forEach>

						</tr>
					</c:forEach>

				</table>
				<br />
				<div class="pagination" id="numpage">${numpage}</div>
				<br /> <input class="btn" value="新 增" type="button"
					onclick="location.href='toSubsidyAdd.do?estateId=${estateId}';" />

				<input class="btn" value="修 改" type="button" onclick="toEdit()" />
				<input class="btn" value="删除" type="submit" onclick="del()" />

			</form>
		</div>
	</div>
	<br />
	<br />


</body>
</html>