<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<html >
<head>

<meta http-equiv="Content-Type" />
<title>单元管理</title>
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


.main {
	clear: both;
	padding: 8px;
	text-align: left;
}

-->
</style>

<script type="text/javascript">
	$(function() {
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		$("#tabs0 li").click(
				function() {
					$(this).addClass("hover").siblings().removeClass("hover");
					var estateId = $(this).find("input").val();
					location.href = "findBuildingNoByEstatePaged.do?estateId="
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
			$.jBox.tip("请选择要修改的单元！");

			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个单元！");
			return;
		}
		
		location.href = "toEditBuildingNo.do?id=" + id;

	}

	function del() {
		
		
		var chk = $(".chk");
		var ids = new Array();
		var count = 0;

		//循环
		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				ids.push(id);
				count++;
			}

		});

		if (count == 0) {
			$.jBox.tip("请选择要删除的单元!");

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
	}
</script>


</head>
<body>

<div class="row-fluid">
	<div class="span4" style="width: 100%">
		<ul class="nav nav-tabs">
			<c:forEach var="e" items="${estates}" varStatus="status">
				<c:if test="${estateId==e.id }">
					<li class="active"><a
						href='moneyReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
				<c:if test="${estateId!=e.id  }">
					<li><a href='moneyReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
		
</div>

	<!--第一种形式-->
	<div>
		


		<div class="main" id="main0">

			<form id="myForm" action="delBuildingNo.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
						<th><input type="checkbox" class="chkAll"
							style="position: relative; top: 2px;" />选择</th>
						<th>单元名称</th>
						<th>单元描述</th>
						<th>所属楼号</th>
						<th>备注</th>
					</tr>

					<c:forEach var="b" items="${page.list }">
						<tr style="text-align: center;">
							<td><input type="checkbox" name="chk" class="chk"
								value="${b.id}" /></td>
							<td>${b.name }</td>
							<td>${b.descript }</td>
							<td>
							<c:if test="${b.buildingId!=null}">${b.buildingId} 号楼</c:if>
							
							</td>
							
							<td>${b.remark }</td>
						</tr>
					</c:forEach>
				</table>

				<br />
				<div class="pagination" id="numpage">${numpage}</div>
				<br /> 
				<input class="btn" value="新增" type="button"
					onclick="location.href='toAutoAddBuildingNo.do?estateId=${estateId}';" />
				
				
				<input class="btn" value="修 改" type="button"
					onclick="toEdit()" />
					
					 <input class="btn" value="删 除" type="submit"
					onclick="del()" />
					
				<input class="btn" value="开d" type="button"
					onclick="toEdit()" /> 

			</form>
		</div>
	</div>
	<br />
	<br />



</body>
</html>