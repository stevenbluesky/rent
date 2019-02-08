<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" >
</object>
<meta http-equiv="Content-Type" />
<title>单元管理</title>
<style type="text/css">
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
<!--
body, div, ul, li {
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: left;
}

a:link {
	color: #00F;
	text-decoration: none;
}

a:visited {
	color: #00F;
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

.smallTxt {
	width: 50px;
	height: 30px;
}

.middleTxt {
	width: 90px;
	height: 30px;
}

.longTxt {
	width: 170px;
}

.redFont {
	color: red;
	position: relative;
	top: 3px;
	left: 5px;
}


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
	
	function download() {
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
			$.jBox.tip("请选择要下载的文件！");

			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个文件！");
			return;
		}

		location.href = "downloadAttachment.do?id=" + id;

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
				$.jBox.tip("请选择要删除的附件!");
				$("#showForm").submit(function() {
					return false;
				});
				return;
			}
			if (!confirm("确定要删除吗?")) {
				$("#showForm").submit(function() {
					return false;
				});	
			}
	
		
	}
	
	$(function(){
		$("#myForm").submit(function(){
			
			if ($("#up").val().length==0) {
				$.jBox.tip("请选择文件！");
				return false;
			}
		});
	});
	//读身份证(同住人)
	function ReadIDCardOthers() {
		var strFolder = "c:\\test2";
		infoStr = JSobj.ReadIDBaseMsg(strFolder);
		
		 $(".infoStr").val(infoStr);
		 infoStr= $(".infoStr").val();
		 
		var info = eval('(' + infoStr + ')');

		var result = info.Result;
		
		
		if (result != 1) {
			$.jBox.tip("读取身份证失败！");
		} else {
			var item = info.Value;
			//获取身份证信息
			var name = item.Name;
			var sex = item.Gender;
			var code = item.Code;
			var address = item.Address;
			
			//身份证相片
			var photoPic=item.BMP;
			$('#linkPhotoImg').attr("src","data:image/jpg;base64,"+ photoPic);
			$(".linkPhotoPic").val(photoPic);
			
			
			if (sex=="男") {
				$(".linkSexDiv").html("<select id='linkSex' name='sex' class='input smallTxt linkSex'><option value='1' selected='selected' >男</option><option value='0' >女</option></select>");	
			}else{
				
				$(".linkSexDiv").html("<select id='linkSex' name='sex' class='input smallTxt linkSex'><option value='1' >男</option><option value='0'  selected='selected'>女</option></select>");
			}
	
			$(".linkName").val(name);
			$(".linkIdno").val(code);
			$(".linkAddress").val(address);
			proId = code.substring(0, 2);
			var url = "getProvince.do";
			var data = "proId=" + proId;
			$.post(url, data, function(proName) {
				$(".linkPro").val(proName);
			});

		}
	}
	
	
</script>
<script type="text/javascript">
	function WriteGuestOpenCard() {

		str1 = JSobj.WriteGuestOpenCard('1105', '9D1BCA695A4B895F8060C497',
				'1', '2017-03-27 14:12:00', '2017-09-29 14:12:00', '8', '3',
				'1', '0');
		alert(str1);
	}
</script>

</head>
<body>

<div style="display: none;">
		<input type="text" class="infoStr">
		
	</div>
	<div class="addOthers">
			<input type="hidden" class="currMasterId" value="${currMaster.id}">
			<input type="hidden" class="currHouseId"
				value="${currMaster.prHouse.id}"> <br />

			<form id="linkDelForm" action="delTempMan" method="post">
				<c:if test="${isLiveIn==1 }">
						<input name="isLiveIn" value="${isLiveIn }" type="hidden">
					</c:if>
				<table id="contentTable"
					class="table  table-bordered table-condensed">
					<thead>
						<tr>
							<th style="width: 30px;">选择 <input type="checkbox"
								class="chkAll" style="position: relative; top: 2px;" /></th>
							
							
							<th>姓名</th>
							<th>性别</th>
							<th>证件</th>
							<th>证件号</th>
							<th>籍贯</th>
							<th width="20%">地址</th>
							<th>手机</th>
							<th>email</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(mans)==0}">
							<tr>
								<td colspan="10">
									<div style="height: 70px;">
										<h2 style="margin: 30px 260px 30px;">尚未添加临时居住人！</h2>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${fn:length(mans)!=0}">
							<c:forEach var="l" items="${mans}">
								<tr>
									
									<td>
									
										<input type="checkbox" name="chk" class="chk" value="${l.id}" />
										
										
									</td>
									
									<td class="linkNameTxt">${l.profile.name}</td>
									<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
									
									<td class="linkidTypeTxt">身份证</td>
									<td class="linkIdnoTxt">${l.profile.idno}</td>
									<td class="linkNationTxt">${l.profile.nation}</td>
									<td class="linkStreetTxt">${l.profile.street}</td>
									<td class="linkMobileTxt">${l.profile.mobile}</td>
									<td class="linkEmailTxt">${l.profile.email}</td>
									
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<br /> 
			
			<!-- 	&nbsp;&nbsp;&nbsp;&nbsp;
				<input value="修改" class="btn" type="button" onclick="updateLink()" /> &nbsp;&nbsp; -->
				
				
				
				<c:if test="${currMaster.sta==7||isLiveIn!=1  }">
					<input value="删除" class="btn" type="submit" onclick="del()" />
				</c:if>
			</form>
			<div>
				<form action="addTempMan" id="linkManForm" method="post">
					<%-- <c:if test="${isLiveIn==1 }"><input name="isLiveIn" value="${isLiveIn }" type="hidden"></c:if> --%>
				
				<input type="hidden" name="linkPhotoPic" class="linkPhotoPic" value="${linkPhotoCode }">
				<input type="hidden" class="haslinkInlive" value="0">
				
				<input name="masterId" type="hidden" value="${currMaster.id}">
					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="id" type="hidden" value="${link.id }"> 
					<input type="button" class="btn" style="margin-right: 10px;" value="读身份证" onclick="ReadIDCardOthers();">
					
					 <br /><br /> 
				
				<span class="redFont">*&nbsp;</span>
				 
					姓名:<input name="name" value="${link.profile.name }" type="text" class="linkName input middleTxt linkName" style="height: 25px;">

					性别:
					<span class="linkSexDiv">
					<select id="linkSex" name="sex" class="input smallTxt linkSex">
						<option value="1" <c:if test="${link.profile.sex=='1'}">selected="selected" </c:if> >男</option>
						<option value="0" <c:if test="${link.profile.sex=='0'}">selected="selected" </c:if> >女</option>
					</select> 
					
					
					</span>
					证件: <select name="idcls" class="input middleTxt linkIdType">
						<option value="1">身份证</option>
					</select> <span class="redFont">*&nbsp;</span> 证件号:<input name="idno"
						value="${link.profile.idno }" type="text"
						class="linkIdno input longTxt" style="height: 25px;"> 籍贯:<input
						name="nation" value="${link.profile.nation }" type="text"
						class="input middleTxt linkPro" style="height: 25px;">
						
						 <br />
					<br /> <span class="redFont">*&nbsp;</span>
					
					 地址:<input name="street"
						value="${link.profile.street }" type="text"
						class="input longTxt linkAddress" style="height: 25px;width: 350px;">
					<span class="redFont">*&nbsp;</span> 手机:<input name="mobile"
						type="text" value="${link.profile.mobile }"
						class="linkMobile input middleTxt"
						style="height: 25px; width: 140px;"> email:<input
						name="email" value="${link.profile.email }" type="text"
						class="input longTxt" style="height: 25px;"> 
						
					<br />	<br />
					
					
					 
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" id="checkRepaire" class="btn" style="margin-right: 10px;" value="保存">	
						&nbsp;&nbsp;&nbsp;
					
				</form>
			</div>

		</div>




</body>

</html>