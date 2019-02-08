<%@page import="com.rent.common.utils.MyDateUtil"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.rent.entity.PrhMaster"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj"
	width="1" height="1"> </object>

<title>用户管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.input {
	margin-left: 7px;
	margin-right: 20px;
}

.houseInfoLabel {
	margin-left: 7px;
	margin-right: 20px;
	color: red;
}

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
		$(function(){
			
			if (<%=request.getAttribute("tip") != null%>) {
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			}
			$("#isPrcode").click(function(){
				$("#deposit").show();	
			});
			$("#noPrcode").click(function(){
				$("#deposit").hide();
			});
			
			$(".chkAll").click(function(){
				chk(); //全选全不选
			});
			
			changeDateList();
		});
	</script>

<script type="text/javascript">
		/* 加载  */
		$(function(){
			/* 默认选项卡设置 */
			$(".addOthers").hide();
			chooseDiv(<%=request.getAttribute("tabNo")%>);
			
			/*错误提示  */
			var tip= $(".tip").val();
			if (<%=request.getAttribute("tip") != null%>) {
				
				$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
			
		/* 单位是否显示 */
			/* 单位是否显示 */
	
		if ("${currMaster.profile.class_}"=="C") {
			$(".company").show();
		} else {
			$(".company").hide();
		}
		chkRentAdd();/*主单表单验证  */
		checkLinkManForm(); /*同住人表单验证  */
		chk(); /* 复选框  */
	});

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
			$.jBox.tip("请选择要删除的同住人！");

			$("#linkDelForm").submit(function() {
				return false;
			});
			return true;
		}

	}

	/* 复选框  */
	function chk() {
		var chkAllFlag = $(".chkAll").attr("checked");

		var chks = $(".chk");
		if (chkAllFlag == "checked") {
			$(".chk").attr("checked", true);
		} else {
			$(".chk").attr("checked", false);
		}
	}
	/*选择选项卡  */
	function chooseDiv(id) {

		if (id == 1) {
			$("#no2").removeClass("active");
			$("#no3").removeClass("active");
			$("#no1").addClass("active");
			$(".addMaster").show();
			$(".addOthers").hide();
			$(".repaireList").hide();
		} else if (id == 2) {
			$("#no1").removeClass("active");
			$("#no3").removeClass("active");
			$("#no2").addClass("active");
			$(".addMaster").hide();
			$(".addOthers").show();
		} else if (id == 3) {

			$("#no2").removeClass("active");
			$("#no1").removeClass("active");
			$("#no3").addClass("active");
			$(".repaireList").show();
			$(".addOthers").hide();
			$(".addMaster").hide();
		}
	}
	/* 同住人非空验证 */
	$(function(){
		$("#linkManForm").submit(function() {
			/*尚未添加签约人  */
	
			if (<%=request.getAttribute("currMaster") == null%>) {
				$.jBox.tip("请先添加签约人信息！");
				return false;
			}
			/*非空  */
			var name = $(".linkName").val();
			var mobile = $(".linkMobile").val();
			var idno = $(".linkIdno").val();
			
			
			var linkAddress = $(".linkAddress").val();
			if (name.length == 0 || mobile.length == 0 || idno.length == 0||linkAddress.length==0) {
				
				$.jBox.tip("必填信息不能为空！");
				return false;
			}
			if (idno.length != 18 && idno.length != 15) {
				$.jBox.tip("身份证位数不正确！！");
				return false;
			}
 			
			var oldIdno="${link.profile.idno }";
			if (oldIdno!=idno) {
				$.ajax({
					url : "isGuestInLive.do?idno="+idno+"&date="+new Date().getTime(),
					type : "get",
					async : false,
					dataType : "text",
					success : function(result) {
						if (result=="1") {
							$.jBox.tip("该租户已经登记或入住其他房间,不能再次入住！");
							$(".haslinkInlive").val("1");
						
						}else{
							$(".haslinkInlive").val("0");
						}
					}
				});
				if ($(".haslinkInlive").val()=="1") {
					return false;
				}	
			
			}
			
		
			/* if (oldIdno!=idno) {
				var url="isGuestInLive.do";
				var data="idno="+idno;
				$.post(url, data, function(result) {
					if (result=="1") {
						$.jBox.tip("该租户已经登记或入住其他房间,不能再次入住！");
						$(".haslinkInlive").val("1");
					
					}else{
						$(".haslinkInlive").val("0");
					}
				});
				
				if ($(".haslinkInlive").val()=="1") {
					return false;
				}	
			} */
			
			if (mobile.substring(0, 1) != 1 || mobile.length != 11) {
				$.jBox.tip("手机号格式不正确！");
				return false;
			}

			return true;
		});

	});
	function checkLinkManForm() {
		
	}
	/*登记非空  */
	function chkRentAdd() {
		$("#addRenter").submit(
				function() {
					var tag2 = $(".masterTag2").val();
					var tag1 = $(".masterTag1").val();
					var name = $(".masterName").val();
					var mobile = $(".masterMobile").val();
					var idno = $(".masterIdNo").val();
					var deposit = $(".depositTxt").val();
					var tag4 = $(".tag4Txt").val();
					var bankNo = $(".bankNoMaster").val();
					var address= $(".masterAddress").val();
					
					var guestType= $("#guestTypeId").val();
					var company= $("#company").val();
					
					
					if ( name.length == 0 || mobile.length == 0
							|| idno.length == 0||address.length==0) {
						$.jBox.tip("必填信息不能为空！");
						return false;
					}
					if (idno.length != 18 && idno.length != 15) {
						$.jBox.tip("身份证位数不正确！");
						return false;
					}

					if (mobile.substring(0, 1) != 1 || mobile.length != 11) {
						$.jBox.tip("手机号格式不正确！");
						return false;
					}
					if (guestType=="C"&&company==null) {
						$.jBox.tip("请选择单位！");
						return false;
					}
					if (deposit.length != 0 && isNaN(deposit)) {
						$.jBox.tip("押金格式不正确！");
						return false;
					}
					
					if (tag4.length != 0 && tag4.length != 11) {
						$.jBox.tip("联系方式不正确！");
						return false;
					}
					if (bankNo.length != 0 && bankNo.length > 19) {
						$.jBox.tip("扣款账号不能大于19位！");
						return false;
					}
					return true;
				});

	}
	function guestType(val) {
		if (val == 'G') {
			$(".company").hide();
		} else {
			$(".company").show();
		}
	}

	//读身份证(主单)
	function ReadIDCard() {
		var strFolder = "c:\\test";
		var infoStr =JSobj.ReadIDBaseMsg(strFolder);
		
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
			$('#masterPhotoImg').attr("src","data:image/jpg;base64,"+ photoPic);
			$(".photoPic").val(photoPic);
			
			$(".masterName").val(name);
			
			
			
			
			if (sex=="男") {
				$(".sexDiv").html("<select name='sex' class='input smallTxt masterSex'><option value='1' selected='selected'>男</option><option value='0'>女</option></select>");	
			}else{
				$(".sexDiv").html("<select name='sex' class='input smallTxt masterSex'><option value='1'>男</option><option selected='selected' value='0'>女</option></select>");
			}
			
			
			$(".masterIdno").val(code);
			$(".masterAddress").val(address);

			proId = code.substring(0, 2);
			var url = "getProvince.do";
			var data = "proId=" + proId;
			$.post(url, data, function(proName) {
				$(".masterPro").val(proName);
			});

		}

		var url = "getProvince.do";
		var data = "proId=" + proId;
		$.post(url, data, function(proName) {
			$(".masterPro").val(proName);
		});

		
	}
	
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
	function updateLink() {

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
			$.jBox.tip("请选择要修改的同住人！");
			return;
		}
		if (count > 1) {
			$.jBox.tip("请勿选择多个同住人！");
			return;
		}
		var id = $(".chk:checked").val();
		location.href = "toEditLinkMan.do?linkId=" + id;
	}

	function changeDateList() {

		var rentCodeId = $("#rentCodeList").val();
		var contractBeginDate = $(".contractBeginDate").val();
		var url = "changeDateList.do";
		var data = "rentCodeId=" + rentCodeId + "&contractBeginDate="
				+ contractBeginDate;
		$.post(url, data, function(results) {
			
			var endDate = results[0];
			var toDate = results[1];
			$(".contractEndDate").val(endDate);
			$(".moneyToDate").val(endDate);
		});
	}
	
</script>

</head>
<body>
	<div style="display: none;">
		<input type="text" class="infoStr">
		
	</div>
	
	<div id="test"></div>
	<div class="container-fluid">
		<div class="">
			<ul class="nav nav-tabs">
				<li onclick="chooseDiv(1)"
					<c:if test="${tabNo==1 }">class="active" </c:if> id="no1"><a>入住人</a></li>
				<li onclick="chooseDiv(2)"
					<c:if test="${tabNo==2 }">class="active" </c:if> id="no2"><a>增加同住人</a></li>
				<%-- <li onclick="chooseDiv(3)" <c:if test="${tabNo==3 }">class="active" </c:if>  id="no3" ><a >维修记录</a></li> --%>
			</ul>
		</div>
		<br />
		<div class="addMaster">
			<form method="post" id="addRenter" action="addRenter">
				
				<input type="hidden" name="numbs" class="numbs" value="${currMaster.numbs }">
				<!--图片隐藏域  -->
				<input type="hidden" name="photoPic" class="photoPic" value="">
				<input type="hidden" name="idenPic" class="idenPic"> <input
					type="hidden" name="houseId" value="${house.id }"> <input
					type="hidden" name="currMasterId" value="${currMaster.id }">
				<input type="hidden" name="picIdentity" class="picIdentityHidden"
					value=""> <input type="hidden" name="picPhoto"
					class="picPhotoHidden" value="${currMaster.picPhoto }">
				
				<div class="span2 search-input"
					style="width: 100%; overflow: hidden;">
					小区 : <span class="houseInfoLabel">
						${house.estate.name}&nbsp;&nbsp;&nbsp;</span> 楼栋 : <span
						class="houseInfoLabel">${house.buildingNo.buildingId}号楼&nbsp;&nbsp;${house.buildingNo.unitName}</span>&nbsp;&nbsp;&nbsp;
					房号 : <span class="houseInfoLabel">${house.roomNo }</span>&nbsp;&nbsp;&nbsp;
					房间类型: <span class="houseInfoLabel">${house.roomType.name }</span>&nbsp;&nbsp;&nbsp;
					房间个数:<span class="houseInfoLabel">${house.roomNum }</span>&nbsp;&nbsp;&nbsp;
					面积:<span class="houseInfoLabel">${house.area}</span>&nbsp;&nbsp;&nbsp;
					地下室编号:<span class="houseInfoLabel">${house.backPrice }</span>&nbsp;&nbsp;&nbsp;
					月租金:<span class="houseInfoLabel">${house.monthPrice}</span>&nbsp;&nbsp;&nbsp;

					<br /><br />
					

					<!-- 租户账户:<input name="guestno"  type="text" class="input longTxt" style="height: 25px;">
					档案:<input name="" type="text" class="input longTxt" style="height: 25px;"> -->
					
					<table >
						<tr>
							<td>合同号:<input name="tag2" type="text" class="masterTag2 input longTxt" style="height: 25px;" value="${currMaster.tag2}"></td>
							<td>准入编号:<input name="tag1" type="text" class="masterTag1 input longTxt" style="height: 25px;" value="${currMaster.tag1}">
							&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn" style="margin-right: 10px;"value="读身份证" onclick="ReadIDCard();">
							</td>
							<td></td>
							<td rowspan="2">
								<input type="hidden" class="masterPhotoSrc"  value="${ctxStatic}/photo/"> 
								
								<c:if test="${currMaster.picPhoto!=null }">
									<img id="masterPhotoImg" src="${ctxStatic}/photo/${currMaster.picPhoto}" style="border: 1px solid #ccc;width:80px;height:100px;">	
								</c:if>
							
								<c:if test="${currMaster.picPhoto==null }">
									<img id="masterPhotoImg" src="${ctxStatic}/photo/default.jpg" style="border: 1px solid #ccc;width:80px;height:100px;" >	
								</c:if>
								
								
							</td>
						</tr>
						<tr>
							<td>  	姓名:<input name="name"
						type="text" class="masterName input middleTxt"
						style="height: 25px;" value="${currMaster.profile.name}">
							
						性别:
						<span class="sexDiv">
						<select name="sex" class="input smallTxt masterSex">
								<option value="1" <c:if test="${currMaster.profile.sex=='1'}">selected="selected"</c:if> >男</option>
								<option value="0" <c:if test="${currMaster.profile.sex=='0'}">selected="selected"</c:if> >女</option>
						</select>
						</span>
						
						</td>
						
					<td colspan="2">
								
					<span class="redFont">*&nbsp;</span> 
				
				
				 证件: <select name="idcls" class="input middleTxt " >
						<option value="1">身份证</option>
					</select>
					
				证件号:<input name="idno"
						type="text" class="masterIdno input longTxt" style="height: 25px;" 
						value="${currMaster.profile.idno }">
						
				 籍贯:<input
						name="nation" type="text" class="masterPro input middleTxt"
						style="height: 25px;" value="${currMaster.profile.nation }"> 
							</td>
						
						</tr>
					</table>
				
					
				
							 <!-- data:image/jpg;base64,xxxx -->
						
					<br/>
					 <span class="redFont">*&nbsp;</span> 地址:<input name="street"
						type="text" class="masterAddress input longTxt"
						style="height: 25px; width: 350px;"
						value="${currMaster.profile.street }"> <span
						class="redFont">*&nbsp;</span> 手机:<input name="mobile" type="text"
						class="masterMobile input" style="height: 25px; width: 120px;"
						value="${currMaster.profile.mobile }"> email:<input
						name="email" type="text" class="input longTxt"
						style="height: 25px; width: 140px;"
						value="${currMaster.profile.email }"> <br />
					<br /> 
					
					
										
					<c:if test="${isLiveIn==1 }">
						<input name="class_" value="${currMaster.profile.class_ }" type="hidden">
					</c:if>
					
					 租户类别: <select name="class_"  class="input middleTxt guestType" id="guestTypeId"
					 <c:if test="${isLiveIn==1 }">disabled="disabled"</c:if>
						onchange="guestType(this.value)">
						<c:if test="${currMaster.profile.class_ != 'C' }">
							<option value="G" selected="selected">租户</option>
							<option value="C">单位</option>
						</c:if>
						<c:if test="${currMaster.profile.class_ == 'C' }">
							<option value="G">租户</option>
							<option value="C" selected="selected">单位</option>
						</c:if>

					</select>
					
					
					<c:if test="${isLiveIn==1 }">
						<input name="currMaster.guestNo " value="${currMaster.guestNo }" type="hidden">
					</c:if>
					
					 <span class="company"> 签约单位: <select id="company" name="guestNo" <c:if test="${isLiveIn==1 }">disabled="disabled"</c:if>
						class="input longTxt">
							<c:forEach var="c" items="${companys}">
								<c:if test="${currMaster.guestNo == c.guestno }">
									<option value="${c.guestno}" selected="selected">${c.company }</option>
								</c:if>
								<c:if test="${currMaster.guestNo != c.guestno }">
									<option value="${c.guestno}">${c.company}</option>
								</c:if>
							</c:forEach>
					</select>
					</span>
					
					<c:if test="${isLiveIn==1 }">
						<input name="subsidyTypeId" value="${currMaster.subsidyTypeId }" type="hidden">
					</c:if>
					
					
					补贴标准:<select name="subsidyTypeId" class="input middleTxt" <c:if test="${isLiveIn==1 }">disabled="disabled"</c:if>>

						<option value="-1" selected="selected">无</option>
						<c:forEach var="s" items="${subsidyTypes}">
							<c:if test="${currMaster.subsidyTypeId == s.id }">
								<option value="${s.id }" selected="selected">${s.name }</option>
							</c:if>
							<c:if test="${currMaster.subsidyTypeId != s.id }">
								<option value="${s.id }">${s.name }</option>
							</c:if>
						</c:forEach>
					</select>
					
					&nbsp;&nbsp;补贴金额: 
					<c:if test="${currMaster.setrate==null }">
						<span class="houseInfoLabel">（保存完成后显示）</span>
					</c:if>
					<c:if test="${currMaster.setrate!=null }">
						<span class="houseInfoLabel"><fmt:formatNumber value="${currMaster.setrate}" pattern="#0.00" /></span>&nbsp;&nbsp;&nbsp;
					
					</c:if>
					
					 	&nbsp;&nbsp;应缴租金: 
					<c:if test="${currMaster.rate==null }">
						<span class="houseInfoLabel">（保存完成后显示）</span>
					</c:if>
					<c:if test="${currMaster.rate!=null }">
						<span class="houseInfoLabel"><fmt:formatNumber value="${currMaster.rate}" pattern="#0.00" /></span>&nbsp;&nbsp;&nbsp;
					
					</c:if>
					 <br /><br />
					
					<c:if test="${isLiveIn==1 }">
						<input name="rentCode" value="${currMaster.rentCode }" type="hidden">
					</c:if>
					
					
					
					交租方式:<select name=rentCode id="rentCodeList" <c:if test="${isLiveIn==1 }">disabled="disabled"</c:if>
						class="input middleTxt rentCode" onchange="changeDateList()">
						<c:forEach var="r" items="${rentPayWays}">
							<c:if test="${currMaster.rentCode == r.id }">
								<option value="${r.id }" selected="selected">${r.name }</option>
							</c:if>
							<c:if test="${currMaster.rentCode != r.id }">
								<option value="${r.id }">${r.name }</option>
							</c:if>
						</c:forEach>
					</select>
					  
						扣款账号:<input name="bankNo" type="text" class="bankNoMaster input longTxt"
						style="height: 25px;" value="${currMaster.bankNo}"> <br /> <br />
					<%
						Date now = new Date();
						request.setAttribute("now", now);
					%>
					<%
						Date yearAfter = MyDateUtil.addYear(new Date(), 1);
						request.setAttribute("yearAfter", yearAfter);
					%>
					
					
					签约日期: <input name="dep1_" type="date" class="input longTxt" <c:if test="${isLiveIn==1 }">readOnly="readOnly"</c:if>
						style="height: 25px;"
						value="<fmt:formatDate value="${currMaster.dep1 !=null ? currMaster.dep1:now }" pattern="yyyy-MM-dd" />"
						<c:if test="${isLiveIn!=1 }">onFocus="WdatePicker({isShowClear:false,readOnly:true,onpicking:changeDateList()})"</c:if>
						
						>

					合同起:<input onpropertychange="changeDateList()" name="bdate_" <c:if test="${isLiveIn==1 }">readOnly="readOnly"</c:if>
						type="date" class="input contractBeginDate longTxt"
						style="height: 25px;"
						value="<fmt:formatDate value="${currMaster.bdate !=null ? currMaster.bdate:now }" pattern="yyyy-MM-dd" />"
						<c:if test="${isLiveIn!=1 }">onFocus="WdatePicker({isShowClear:false,readOnly:true,onpicking:changeDateList()})"</c:if>  >
					
					合同止:<input name="edate_" type="text" <c:if test="${isLiveIn==1 }">readOnly="readOnly"</c:if>
						class="input longTxt contractEndDate" style="height: 25px;"
						value="<fmt:formatDate value="${currMaster.edate !=null ? currMaster.edate:yearAfter }" pattern="yyyy-MM-dd" />"
						<c:if test="${isLiveIn!=1 }"> onFocus="WdatePicker({isShowClear:false,readOnly:true})" </c:if> >

					<br />
					<br /> 缴租至:<input name="rentdate_" type="date" <c:if test="${isLiveIn==1 }">readOnly="readOnly"</c:if>
						class="input longTxt moneyToDate" style="height: 25px;"
						value="<fmt:formatDate value="${currMaster.rentDate !=null ? currMaster.rentDate:now }" pattern="yyyy-MM-dd" />"
						<c:if test="${isLiveIn!=1 }">onFocus="WdatePicker({isShowClear:false,readOnly:true})"</c:if>  >

					联系人:<input name="tag3" type="text" class="input longTxt"
						style="height: 25px;" value="${currMaster.tag3}"> 联系方式:<input
						name="tag4" type="text" class="input longTxt tag4Txt"
						style="height: 25px;" value="${currMaster.tag4}"> <br />
					<br />
					
					
					
					<c:if test="${isLiveIn==1 }">
						<input name="isLiveIn" value="${isLiveIn }" type="hidden">
					</c:if>
					
					
					<c:if test="${currMaster.id==null }">
						<input type="submit" id="addMasterBtn" class="btn"
							style="margin-right: 10px;" value="登 记">&nbsp;&nbsp;&nbsp;
				</c:if>
					<c:if test="${currMaster.id!=null }">
						<input type="submit" id="addMasterBtn" class="btn"
							style="margin-right: 10px;" value="修 改">&nbsp;&nbsp;&nbsp;
				</c:if>

				</div>
			</form>




		</div>
		<div class="addOthers">
			<input type="hidden" class="currMasterId" value="${currMaster.id}">
			<input type="hidden" class="currHouseId"
				value="${currMaster.prHouse.id}"> <br />

			<form id="linkDelForm" action="delLinkMan" method="post">
				<c:if test="${isLiveIn==1 }">
						<input name="isLiveIn" value="${isLiveIn }" type="hidden">
					</c:if>
				<table id="contentTable"
					class="table  table-bordered table-condensed">
					<thead>
						<tr>
							<th style="width: 30px;">选择 <input type="checkbox"
								class="chkAll" style="position: relative; top: 2px;" /></th>
							
							<th>照片</th>
							
							<th>姓名</th>
							<th>性别</th>
							<th>证件</th>
							<th>证件号</th>
							<th>籍贯</th>
							<th width="20%">地址</th>
							<th>手机</th>
							<th>email</th>
							<th>关系</th>
							<th>添加时间</th>	
							<th>状态</th>
							<th>删除时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(linkMans)==0}">
							<tr>
								<td colspan="10">
									<div style="height: 70px;">
										<h2 style="margin: 30px 260px 30px;">尚未添加同住人！</h2>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${fn:length(linkMans)!=0}">
							<c:forEach var="l" items="${linkMans}">
								<tr <c:if test="${l.del==1}">style="background-color: #CCC;"</c:if>>
									
									<td>
										<c:if test="${l.del!=1}">
											<input type="checkbox" name="chk" class="chk" value="${l.id}" />
										</c:if>
										
									</td>
									
										<td >
											<c:if test="${l.photo!=null }">
												<img id="" src="${ctxStatic}/photo/${l.photo}" style="border: 1px solid #ccc;width:80px;height:100px;">	
											</c:if>
										</td>
										
									
									<td class="linkNameTxt">${l.profile.name}</td>
									<td class="linkSexTxt">${l.profile.sex==0?'女':'男'}</td>
									
									<td class="linkidTypeTxt">身份证</td>
									<td class="linkIdnoTxt">${l.profile.idno}</td>
									<td class="linkNationTxt">${l.profile.nation}</td>
									<td class="linkStreetTxt">${l.profile.street}</td>
									<td class="linkMobileTxt">${l.profile.mobile}</td>
									<td class="linkEmailTxt">${l.profile.email}</td>
									<td class="linkLinkTxt">${l.link}</td>
									<td>
										<c:if test="${l.addtime!=null}">
											<fmt:formatDate value="${l.addtime}" pattern="yyyy-MM-dd" />
										</c:if>
									</td>
									<td>${l.del==1?'已删除':''}</td>
									<td>
										<c:if test="${l.deltime!=null}">
											<fmt:formatDate value="${l.deltime}" pattern="yyyy-MM-dd" />
										</c:if>
									</td>
									
									
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<br /> 
				<input value="修改" class="btn" type="button" onclick="updateLink()" /> &nbsp;&nbsp;
				
				<c:if test="${currMaster.sta==7||isLiveIn!=1  }">
					<input value="删除" class="btn" type="submit" onclick="del()" />
				</c:if>
			</form>
			<div>

				<form action="addLinkMan" id="linkManForm" method="post">
				
					<c:if test="${isLiveIn==1 }">
						<input name="isLiveIn" value="${isLiveIn }" type="hidden">
					</c:if>
					
				<input type="hidden" name="linkPhotoPic" class="linkPhotoPic" value="${linkPhotoCode }">
				
				<input type="hidden" class="haslinkInlive" value="0">
					<input name="masterId" type="hidden" value="${currMaster.id}">
					<input name="id" type="hidden" value="${link.id }"> <input
						type="button" class="btn" style="margin-right: 10px;" value="读身份证"
						onclick="ReadIDCardOthers();"> <br />
					<br /> <span class="redFont">*&nbsp;</span> 姓名:<input name="name"
						value="${link.profile.name }" type="text"
						class="linkName input middleTxt linkName" style="height: 25px;">

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
						
						
						 
								
						<c:if test="${link.photo!=null }">
							<img id="linkPhotoImg" src="${ctxStatic}/photo/${link.photo}" style="border: 1px solid #ccc;width:80px;height:100px;">	
						</c:if>
							
						<c:if test="${link.photo==null }">
							<img id="linkPhotoImg" src="${ctxStatic}/photo/default.jpg" style="border: 1px solid #ccc;width:80px;height:100px;" >	
						</c:if>
						
						
						
						
						
						
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
					<%-- 	关系:<input
						name="link" type="text" value="${link.link }"
						class="input middleTxt linkLink" style="height: 25px;"> --%>
						 
						 关系:<select name="link" class="input middleTxt linkLink">
							<option value="">请选择</option>
							
							<option value="夫妻" <c:if test="${link.link=='夫妻'}">selected="selected"</c:if> >夫妻</option>
							<option value="子女" <c:if test="${link.link=='子女'}">selected="selected"</c:if>>子女</option>
							<option value="其他" <c:if test="${link.link=='其他'}">selected="selected"</c:if>>其他</option>
					
					</select>
					
					
					 补贴标准:<select name="subsidyTypeId" class="input middleTxt">
						<option value="-1">无</option>
						<c:forEach var="s" items="${subsidyTypes}">
							<c:if test="${s.id==link.subsidyTypeId }">
								<option value="${s.id }">${s.name }</option>
							</c:if>
							<c:if test="${s.id!=link.subsidyTypeId }">
								<option value="${s.id }">${s.name }</option>
							</c:if>
						</c:forEach>	
					</select>
					
					<br />
					<br />
					<br /> 
					
					<c:if test="${currMaster.sta==7||isLiveIn!=1  }">
						<input type="submit" id="checkRepaire" class="btn" 
							   style="margin-right: 10px;" value="保存">		&nbsp;&nbsp;&nbsp;
					</c:if>
				</form>
			</div>

		</div>

		
	</div>
</body>
</html>