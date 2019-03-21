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

</script>
<script type="text/javascript">
	function WriteGuestOpenCard() {

		str1 = JSobj.WriteGuestOpenCard('1105', '9D1BCA695A4B895F8060C497',
				'1', '2017-03-27 14:12:00', '2017-09-29 14:12:00', '8', '3',
				'1', '0');
		alert(str1);
	}
	
	
	function print(){
		location.href="printCode.do?masterId=${master.id}";
	}
	
	
	
</script>

</head>
<body>


	<!--第一种形式-->
	<div>
		<ul class="nav nav-tabs">

			<c:forEach var="e" items="${estates}" varStatus="status">
				<c:if test="${estateId==e.id }">
					<li class="active"><a
						href='findBuildingNoByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
				<c:if test="${estateId!=e.id  }">
					<li><a
						href='findBuildingNoByEstatePaged.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
			</c:forEach>
		</ul>


		<div class="main" id="main0">

			<form id="myForm" method="post" >
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed" style="width: 980px;">
					<thead>
						<tr>
							<th style="width: 30px;">选择</th>
							<th width="30" >租户类别</th>
							<th>姓名</th>
							<th width="20">性别</th>
							<th width="30">证件</th>
							<th width="10">证件号</th>
							<th>手机</th>
							<th>关系</th>
						</tr>
					</thead>
					<tbody>
					
					
						<tr>
							<td><input type="hidden" class="cardId" name="cardId" /> <input
								type="hidden" class="idenPwd" name="idenPwd" /> <input
								type="radio" name="idStr" class="radioId" checked="checked"
								value="${master.id}@1" /> <input type="hidden" class="oldCardId"
								name="oldCardId" /></td>
							<td class="type">登记人</td>
							<td class="linkNameTxt">${master.profile.name}</td>
							<td class="linkSexTxt">${master.profile.sex==0?'女':'男'}</td>
							<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
							<td class="linkidTypeTxt">身份证</td>
							<td class="linkIdnoTxt">${master.profile.idno}</td>
							
							<td class="linkMobileTxt">${master.profile.mobile}</td>
							
							<td class="linkLinkTxt"></td>
							
						</tr>

						<c:forEach var="l" items="${linkMans}">
							<c:if test="${l.del!=1 }">
							<tr>
								<td><input type="radio" name="idStr" class="radioId"
									value="${master.id}-${l.id}@2" /></td>
								<td class="type">同住人</td>
								<td class="linkNameTxt">${l.profile.name}</td>
								<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
								<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
								<td class="linkidTypeTxt">身份证</td>
								
								<td class="linkIdnoTxt">${l.profile.idno}</td>
								<td class="linkMobileTxt">${l.profile.mobile}</td>
								
								<td class="linkLinkTxt">${l.link}</td>
							
							</tr>
							</c:if>
						</c:forEach>
						
						<c:forEach var="l" items="${tempMans}">
							<c:if test="${l.del!=1 }">
							<tr>
								<td><input type="radio" name="idStr" class="radioId"
									value="${master.id}-${l.id}@3" /></td>
								<td class="type">临时居住人</td>
								<td class="linkNameTxt">${l.profile.name}</td>
								<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
								<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
								<td class="linkidTypeTxt">身份证</td>
								
								<td class="linkIdnoTxt">${l.profile.idno}</td>
								<td class="linkMobileTxt">${l.profile.mobile}</td>
								
								<td class="linkLinkTxt"></td>
							
							</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="hidden" name="op" class="op" />


				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="增加门锁用户" type="button" onClick="toAddLockUserPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==23}">
					<input class="btn" value="增加门锁用户" type="button" onClick="toAddLockUserPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if></c:forEach></c:if></c:forEach>
				<%--<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="下发密码" type="button" onClick="toAddPasswordPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==23}">
					<input class="btn" value="下发密码" type="button" onClick="toAddPasswordPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="下发MF卡" type="button" onclick="toAddMFCardPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==25}">
					<input class="btn" value="下发MF卡" type="button" onclick="toAddMFCardPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="下发身份证" type="button" onclick="toAddIDCardPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==24}">
					<input class="btn" value="下发身份证" type="button" onclick="toAddIDCardPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>--%>

                <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
                    <input class="btn" value="查看门锁用户" type="button" onclick="toLockUserPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
                </c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==24}">
                    <input class="btn" value="查看门锁用户" type="button" onclick="toLockUserPage()" />&nbsp;&nbsp;&nbsp;&nbsp;
                </c:if></c:forEach></c:if></c:forEach>
				<br />
			</form>
		</div>
	</div>
	<div style="display: none;">
		<input type="text" class="infoStr">
	</div>
	<br />
	<script type="text/javascript">

        function toAddLockUserPage() {
            var chk= $(".radioId");
            var count=0;
            var id=null;
            chk.each(function(e,q){
                var flag= $(q).is(":checked");
                if(flag){
                    id=$(q).val();
                    count++;
                }
            });
            if(count==0){
                $.jBox.tip("请选择要下发的用户！");
                return;
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: '增加门锁用户',
                    maxmin: false,
                    area: ['500px', '400px'],
                    content:'toAddLockuserPage.do?idStr='+id,
                    end: function(){
                        location.reload();
                    }
                });
            });
        }
		function toAddPasswordPage() {
            var chk= $(".radioId");
            var count=0;
            var id=null;
            chk.each(function(e,q){
                var flag= $(q).is(":checked");
                if(flag){
                    id=$(q).val();
                    count++;
                }
            });
            if(count==0){
                $.jBox.tip("请选择要下发的用户！");
                return;
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: '下发密码用户',
                    maxmin: false,
                    area: ['400px', '240px'],
                    content:'toAddLockUserPage.do?usertype=1&idStr='+id,
                    end: function(){
                        location.reload();
                    }
                });
            });
        }
        function toAddMFCardPage() {
            var chk= $(".radioId");
            var count=0;
            var id=null;
            chk.each(function(e,q){
                var flag= $(q).is(":checked");
                if(flag){
                    id=$(q).val();
                    count++;
                }
            });
            if(count==0){
                $.jBox.tip("请选择要下发的用户！");
                return;
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: '下发MF卡用户',
                    maxmin: false,
                    area: ['400px', '240px'],
                    content:'toAddLockUserPage.do?idStr='+id+'&usertype=3',
                    end: function(){
                        location.reload();
                    }
                });
            });
        }
        function toAddIDCardPage() {
            var chk= $(".radioId");
            var count=0;
            var id=null;
            chk.each(function(e,q){
                var flag= $(q).is(":checked");
                if(flag){
                    id=$(q).val();
                    count++;
                }
            });
            if(count==0){
                $.jBox.tip("请选择要下发的用户！");
                return;
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: '下发身份证用户',
                    maxmin: false,
                    area: ['400px', '240px'],
                    content:'toAddLockUserPage.do?usertype=4&idStr='+id,
                    end: function(){
                        location.reload();
                    }
                });
            });
        }
        function toLockUserPage() {
            var chk= $(".radioId");
            var count=0;
            var id=null;
            chk.each(function(e,q){
                var flag= $(q).is(":checked");
                if(flag){
                    id=$(q).val();
                    count++;
                }
            });
            if(count==0){
                $.jBox.tip("请选择要查看的用户！");
                return;
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: '查看门锁用户',
                    maxmin: false,
                    area: ['1000px', '400px'],
                    content:'toLockUserPage.do?idStr='+id,
                    end: function(){
                        location.reload();
                    }
                });
            });
        }
	</script>
</body>
</html>