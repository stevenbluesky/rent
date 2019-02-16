<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" />
<title>房间特征管理</title>
<style type="text/css">
<!--
body,div,ul,li {
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
<%--<link rel="stylesheet" href="css/page.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/page.js"></script>--%>

<script type="text/javascript">
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		$("#tabs0 li")
				.click(
						function() {
							$(this).addClass("hover").siblings().removeClass(
									"hover");
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
			$.jBox.tip("请选择要修改的楼层！");
			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个楼层！");
			return;
		}

		location.href = "toRoomFeatureEdit.do?id=" + id;

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
			$.jBox.tip("请选择要删除的楼层！");
			$("#myForm").submit(function() {
				return false;
			});
			return;
		}
	}
</script>


</head>
<body>

	<!--第一种形式-->
	<div id="tabs0">
		
		<div class="main" id="main0">

			<form id="myForm" action="roomFeatureDel.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
			<tr >	<th ><input type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/>选择 </th>
						
						<th>编号</th>
						<th>房屋特征名</th>
						
						<th>备注</th>
						
					</tr>
					<c:forEach var="b" items="${page.list}" varStatus="status">
						<tr
							style="text-align: center;<c:if test='${status.count%2==0 }'>background-color:ffffde </c:if>">
							<td><input type="checkbox" name="chk" class="chk" value="${b.id}"/> </td>
							<td>${b.no }</td>
							<td>${b.name }</td>
							<td>${b.remark }</td>
							
						</tr>
					</c:forEach>
				</table>
			<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>
				<input class="btn" value="新 增" type="button"
					onclick="location.href='toRoomFeatureAdd'" />


				<input class="btn" value="修 改" type="button" onclick="toEdit()" /> 
				<input
				class="btn"	value="删 除" type="submit" onclick="del()" /> 

			</form>
		</div>
	</div>
	<br />
	<br />
	

</body>
</html>