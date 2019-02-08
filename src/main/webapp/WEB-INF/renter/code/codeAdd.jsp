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
							<th>白卡状态</th>
							<th>身份证对码</th>
						</tr>
					</thead>
					<tbody>
					
					
						<tr>
							<td><input type="hidden" class="cardId" name="cardId" /> <input
								type="hidden" class="idenPwd" name="idenPwd" /> <input
								type="radio" name="idStr" class="radioId" checked="checked"
								value="${master.id}" /> <input type="hidden" class="oldCardId"
								name="oldCardId" /></td>
							<td class="type">登记人</td>
							<td class="linkNameTxt">${master.profile.name}</td>
							<td class="linkSexTxt">${master.profile.sex==0?'女':'男'}</td>
							<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
							<td class="linkidTypeTxt">身份证</td>
							<td class="linkIdnoTxt">${master.profile.idno}</td>
							
							<td class="linkMobileTxt">${master.profile.mobile}</td>
							
							<td class="linkLinkTxt"></td>

							<td class="cardState"><c:if test="${master.card!=null}">已开卡</c:if>
								<c:if test="${master.card==null}">未开卡</c:if></td>
							<td class="identityCard">
							
							<c:if
									test="${master.identityCard!=null }">
								${master.identityCard.idenPwd}
							</c:if> <c:if test="${master.identityCard==null }">
								未设置
							</c:if></td>
							
						</tr>

						<c:forEach var="l" items="${linkMans}">
							<c:if test="${l.del!=1 }">
							<tr>
								<td><input type="radio" name="idStr" class="radioId"
									value="${master.id}-${l.id}" /></td>
								<td class="type">同住人</td>
								<td class="linkNameTxt">${l.profile.name}</td>
								<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
								<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
								<td class="linkidTypeTxt">身份证</td>
								
								<td class="linkIdnoTxt">${l.profile.idno}</td>
								<td class="linkMobileTxt">${l.profile.mobile}</td>
								
								<td class="linkLinkTxt">${l.link}</td>
								<td class="cardState"><c:if test="${l.card!=null}">已开卡</c:if>
									<c:if test="${l.card==null}">未开卡</c:if></td>

								<td class="identityCard"><c:if
										test="${l.identityCard!=null }">
								${l.identityCard.idenPwd }
							</c:if> <c:if test="${l.identityCard==null }">
								未设置
							</c:if></td>
							
							</tr>
							</c:if>
						</c:forEach>
						
						<c:forEach var="l" items="${tempMans}">
							<c:if test="${l.del!=1 }">
							<tr>
								<td><input type="radio" name="idStr" class="radioId"
									value="${master.id}-${l.id}" /></td>
								<td class="type">临时居住人</td>
								<td class="linkNameTxt">${l.profile.name}</td>
								<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
								<%-- <c:set var="idclsName"  scope="request" value="${l.profile.idcls}" ></c:set> --%>
								<td class="linkidTypeTxt">身份证</td>
								
								<td class="linkIdnoTxt">${l.profile.idno}</td>
								<td class="linkMobileTxt">${l.profile.mobile}</td>
								
								<td class="linkLinkTxt"></td>
								<td class="cardState">
								
								</td>
								<td class="identityCard">
								
									<c:if test="${l.idenPwd!=null }">
										${l.idenPwd }
									</c:if>
									
									 <c:if test="${l.idenPwd==null }">
									未设置
									</c:if>
							</td>
							
							</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="hidden" name="op" class="op" />
				
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="开卡" type="button" onClick="createCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==23}">
					<input class="btn" value="开卡" type="button" onClick="createCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="挂失卡" type="button" onclick="lossCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==25}">
					<input class="btn" value="挂失卡" type="button" onclick="lossCard()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="生成身份证对码" type="button" onclick="createIdentityPwd()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==24}">
					<input class="btn" value="生成身份证对码" type="button" onclick="createIdentityPwd()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
			 	
			 	
			 	<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="身份证挂失确认" type="button" onclick="identityLoss()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==74}">
					<input class="btn" value="身份证挂失确认" type="button" onclick="identityLoss()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
			 	
			 		<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn" value="历史身份证对码" type="button" onclick="historyIdenPwd()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==75}">
					<input class="btn" value="历史身份证对码" type="button" onclick="historyIdenPwd()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
				
				
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
					<input class="btn"  value="打印对码" type="button" onclick="print()" />&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==108}">
					<input class="btn"  value="打印对码" type="button" onclick="print()" />&nbsp;&nbsp;&nbsp;&nbsp;
			 	</c:if></c:forEach></c:if></c:forEach>
			 	
			 	
			 	
		<script type="text/javascript">
		
		
		function historyIdenPwd(){
			var id = $('.radioId:checked').val();
			
			 layer.open({
				    type: 2,
				    title: '历史身份证对码',
				    maxmin: false,
				    area: ['700px', '376px'],
				    content:'toHistoryIdenPwd.do?idStr='+id,
				    end: function(){
				    	location.reload();
				    }
				  });
			
		}
		
		
		
		function identityLoss(){
			var id = $('.radioId:checked').val();
			
			
		 var idenPwd= $.trim($('.radioId:checked').parents("tr").find(".identityCard").text());
			if (idenPwd=="未设置") {
				$.jBox.tip("租户尚未生成对码，不能挂失，请确认！");
				return;	
			}
		 
			if (!confirm("请确认身份证已经挂失!是否继续？")) {
				return false;
			}
			
			$.ajax({
				url : 'lossIdentity.do?idStr='+ id+"&name="+new Date(),
				success : function(result) {
					
				 	if (result==0) {
						$.jBox.tip("未找到记录！");		
					}else{
						$('.radioId:checked').parents("tr").find(".identityCard").text("未设置");
						$.jBox.tip("挂失确认成功！");
					} 
				}
			});
		}
		
		function lossCard() {
			$(".cardId").val("");
			$(".oldCardId").val("");
			$(".idenPwd").val("");
			$(".op").val("挂失卡");
			
			if ($('.radioId:checked').parents("tr").find(".cardState").text()=="未开卡") {
				$.jBox.tip("租户尚未开卡，不能挂失，请确认！");
				return;
			}
			
			 if (!confirm("确认挂失吗？")) {
				return false;
				}
			var id = $('.radioId:checked').val();
			 
			/*通过ajax获取信息  */
			$.ajax({
				url : 'lossCard.do?idStr='+ id+"&name="+new Date(),
				success : function(results) {
					
					if (results==null||results.length==0) {
						$.jBox.tip("租户尚未开卡，不能挂失，请确认！");
					}
					var cardId = results[0];
					var authorCode=results[1];
					var oldCardId=results[2];
					
					$(".cardId").val(cardId);
					$(".oldCardId").val(oldCardId);
					
					lossResult= JSobj.WriteLossCard(cardId,authorCode,'0',oldCardId,'0');	
					var resultStr = eval('(' + lossResult + ')');
					var result = resultStr.Result;
					
					if (result==1) {
						//提交表单
						$.ajax({
							type : "POST",
							dataType : "Text",
							url : 'writeCard.do',
							data : $('#myForm').serialize(),
							success : function(result) {
								if (result == 1) {
									$('.radioId:checked').parents("tr").find(".cardState").text("已挂失");
									$.jBox.tip("挂失卡写入成功！");

								} else if (result == 3) {
									$.jBox.tip("该流水号已被占用！");
								}

							},
							error : function(data) {
							}
						});

						
						
					}else{
						$.jBox.tip("操作失败，请重试！");
					}
					
				},
				error : function(data) {
					alert("error:" + data.responseText);
				}
			});

		}
	</script>
				<br />
			</form>
		</div>


	</div>
	<div style="display: none;">
		<input type="text" class="infoStr">
	</div>
	<br />
	<script type="text/javascript">
		function createIdentityPwd() {
			var type= $(".radioId:checked").parents("tr").find(".type").text();
			
			$(".cardId").val("");
			$(".oldCardId").val("");
			$(".idenPwd").val("");
			$(".op").val("生成身份证对码");
			var id = $('.radioId:checked').val();
			/*通过ajax获取信息  */
			$.ajax({
				url : 'getInfoForIdentity.do?idStr=' + id+"&name="+new Date(),
				success : function(results) {
					var cardId = results[0];
					$(".cardId").val(cardId);
					
					var bDate = results[1];
					var eDate = results[2];
					var roomNoStr = results[3];
					
					
					//读取身份证卡号
					var strFolder = "c:\\test";
					infoStr = JSobj.ReadIDBaseMsg(strFolder);
					
					 $(".infoStr").val(infoStr);
					 infoStr= $(".infoStr").val();
					
					 var info = eval('(' + infoStr + ')');
					
					 
					
					var result= info.Result;
					var identityStr;
					if (result!=1) {
						 $.jBox.tip("读取身份证失败！");
					}else{
						var item=info.Value;
						var code=item.Code;
						var realCode= $('.radioId:checked').parents("tr").find(".linkIdnoTxt").text();
						if (realCode!=code) {
							 $.jBox.tip("身份证与租户信息不符合！");
							 return;
						}else{
							 identityStr = item.CardNo; //身份证物理卡号
							
						}
						
						//获取密码
						
						str1 = JSobj.GetPassword(identityStr, bDate, eDate,
								roomNoStr, "0000000000");
					
						var q = eval('(' + str1 + ')');
						var result = q.Result;
						
						if (result != 1) {
							$.jBox.tip("获取密码失败！");
						} else {
							
							$(".idenPwd").val(q.Value);
							
							//保存数据库
							var urlData="";
							if (type=="临时居住人") {
								urlData="writeTempCard.do";
							}else{
								urlData="writeCard.do";
							}
							
							//提交表单
							$.ajax({
								type : "POST",
								dataType : "Text",
								url : urlData,
								data : $('#myForm').serialize(),
								success : function(result) {
									if (result == 1) {
										/* var type= $('.radioId:checked').parents("td").next().text(); */
										
										$('.radioId:checked').parents("tr")
										.find(".identityCard").text(
												q.Value);
										
										$.jBox.tip("身份证对码生成成功！");

									} else if (result == 2) {
										$.jBox.tip("该租户已经已经生成过密码，请查看！");
									} else if (result == 3) {
										$.jBox.tip("该流水号已被占用！");
									}

								},
								error : function(data) {
								}
							});
						}
					}
					
				},
				error : function(data) {
					alert("error:" + data.responseText);
				}
			});

		}
	</script>


	<script type="text/javascript">
		function createCard() {
			
			
			$(".op").val("开卡");
			$(".cardId").val("");
			$(".oldCardId").val("");
			$(".idenPwd").val("");
			var id = $('.radioId:checked').val();
			/*通过ajax获取信息  */
			$.ajax({
				url : "getInfoForCard.do?idStr=" + id+"&name="+new Date(),
				success : function(results) {

					var cardId = results[0];
					$(".cardId").val(cardId);
					
					var authorCode = results[1];
	
					var bDate = results[2];
					var eDate = results[3];
					var unit = results[4];
					var floor = results[5];
					var roomId = results[6];
									
					var infoStr = JSobj.WriteGuestOpenCard(cardId, authorCode, '1',
							bDate, eDate, unit, floor, roomId, '0');
					
					var info = eval('(' + infoStr + ')');

					var result = info.Result;
					if (result != 1) {
						$.jBox.tip("开卡失败！");
					} else {
						//提交表单
						$.ajax({
							type : "POST",
							dataType : "Text",
							url : 'writeCard.do',
							data : $('#myForm').serialize(),
							success : function(result) {
								if (result == 1) {
									$('.radioId:checked').parents("tr").find(
											".cardState").text("已开卡");
									$.jBox.tip("开卡成功！");

								} else if (result == 2) {
									$.jBox.tip("该租户已经开过卡！");
								} else if (result == 3) {
									$.jBox.tip("该流水号已被占用！");
								}

							},
							error : function(data) {
							}
						});

					}

				},
				error : function(data) {
					alert("error:" + data.responseText);
				}
			});
		}
	</script>
</body>
</html>