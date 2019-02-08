<%@page import="java.util.Date"%>
<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<html>
<head>
<title>退租申请</title>

<%-- <link rel="stylesheet" href="${ctxStatic}/jquery/jquery.treeview.css" /> --%>
<style type="text/css">
.treeview, .treeview ul { 
	padding: 0;
	margin: 0;
	list-style: none;
}

.treeview ul {
	background-color: white;
	margin-top: 4px;
}

.treeview .hitarea {
	background: url(${ctxStatic}/jquery/images/treeview-default.gif) -64px -23px no-repeat;
	height: 16px;
	width: 16px;
	margin-left: -16px;
	float: left;
	cursor: pointer;
}
/* fix for IE6 */
* html .hitarea {
	display: inline;
	float:none;
}

.treeview li { 
	margin: 0;
	padding: 3px 0pt 3px 16px;
}

.treeview a.selected {
	background-color: #eee;
}

#treecontrol { margin: 1em 0; display: none; }

.treeview .hover { color: red; cursor: pointer; }

.treeview li { background: url(${ctxStatic}jquery/images/treeview-default-line.gif) 0 0 no-repeat; }
.treeview li.collapsable, .treeview li.expandable { background-position: 0 -180px; }

.treeview .expandable-hitarea { background-position: -80px -1px; }

.treeview li.last { background-position: 0 -1766px }
.treeview li.lastCollapsable, .treeview li.lastExpandable { 
	background-image: url(${ctxStatic}/jquery/images/treeview-default.gif);

 }  
.treeview li.lastCollapsable { background-position: 0 -111px }
.treeview li.lastExpandable { background-position: -32px -67px }

.treeview div.lastCollapsable-hitarea, .treeview div.lastExpandable-hitarea { background-position: 0; }

.treeview-black li { background-image: url(${ctxStatic}/jquery/images/treeview-black-line.gif); }
.treeview-black .hitarea, .treeview-black li.lastCollapsable, .treeview-black li.lastExpandable { background-image: url(${ctxStatic}/jquery/images/treeview-black.gif); }  


.treeview-famfamfam li { background-image: url(${ctxStatic}/jquery/images/treeview-famfamfam-line.gif); }
.treeview-famfamfam .hitarea, .treeview-famfamfam li.lastCollapsable, .treeview-famfamfam li.lastExpandable { background-image: url(${ctxStatic}/jquery/images/treeview-famfamfam.gif); } 

.treeview .placeholder {
	background: url("${ctxStatic}/jquery/images/ajax-loader.gif") 0 0 no-repeat;
	height: 16px;
	width: 16px;
	display: block;
}

.filetree li { padding: 3px 0 2px 16px; }
.filetree span.folder, .filetree span.file { padding: 14px 0 1px 5px; }


/* .filetree span.file { background: url(${ctxStatic}/jquery/images/file.gif) 0 0 no-repeat; } */



</style>	
<link rel="stylesheet" href="screen.css" />
<script src="${ctxStatic}/jquery/jquery.cookie.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery.treeview.js"
	type="text/javascript"></script>
<style type="text/css">
ul {list-style: none;};
.addFloor {
	border: 1px solid red;
	border-collapse: collapse;
	width: 100%;
	margin-top: 10px;
}

.addFloor tr {
	height: 40px;
}

.addFloor  td {
	padding: 0px 8px 0px;
}

.addFloor .title {
	width: 100px;
	text-align: right;
}

.addFloor input.text {
	width: 200px;
	height: 30px;
}

.chkLevel1 {
	margin-right: 100px;
}
</style>

<script type="text/javascript">
  $(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}

		$(".chkAll").click(function() {
			chk(); //全选全不选
		});
		
		var texts= $(".level1CountSpan");
		
		texts.each(function(e, text) {
			
			if ($(text).text().indexOf("()")!=-1) {
				$(text).text("");
			}
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
			$.jBox.tip("请选择要删除的角色！");
			$("#myForm").submit(function() {
				return false;
			});
			return;
		}
	}

	function apply() {
		
		var  modulesIds=$(".modulesIds").val();
		
		var name=$(".name").val();
		
		if (name.length==0) {
			$.jBox.tip("角色名不能为空！");
			
			return;
		}
		
		if (modulesIds.length==0) {
			$.jBox.tip("请选择角色权限！");
			return;
		}
		
		$.ajax({
			type : "POST",
			dataType : "Text",
			url : 'addRole.do',
			data : $('#myForm').serialize(),
			success : function(result) {
				if (result==1) {
					parent.layer.close(parent.layer.getFrameIndex(window.name));
					parent.addResult(result);	
				}else{
					$.jBox.tip("角色名重复");
				}
				

			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	function chooseLevel(value){
		var  modulesIds=$(".modulesIds").val();
		var  moduleNum=$(".moduleNumHidden").val();
		if (moduleNum==null) {
			moduleNum=0;
		}
		var name=$(".name").val();
		var level1CountHidden=$(".level1CountHidden");
		var array=new Array();
		level1CountHidden.each(function(i,n){
			array[i]=n.value;
		});
		
		var url=" toRoleAdd.do?level1="+value+"&name="+name+"&modulesIds="+modulesIds+"&moduleNum="+moduleNum+"&level1Counts="+array;
		location.href=url;
	}

	function choose(chk1){
		child(chk1);//关联选中
		
		var val="";
		var chks= $(".mIds");	//复选框
		
		//文本框
		var  modulesId=$(".modulesIds").val();
		var ids= modulesId.split("-");
		
		//复选框
		for (var i = 0; i < chks.length; i++) {
			val= chks[i].value;
			//选中的(文本框没有则添加)
			if($(chks[i]).is(":checked")){
				if ($.inArray(val, ids)==-1){
					ids.push(val);
				}
			//没选中的(文本框有，则删除)
			}else{
				var exitIndex= $.inArray(val, ids);
			
				if (exitIndex!=-1){
					ids.splice(exitIndex,1);	
				}
			}
		}
		
		//循环结束，处理数组
		var info="";
		for (var i = 0; i < ids.length; i++) {
			
			if (i==0) {
				info=info+ids[i];
				
			}else{
				info=info+"-"+ids[i];
			}
		}
		
		$(".modulesIds").val(info);
		if ($(".modulesIds").val().substring(0,1)=="-") {
			var ss= $(".modulesIds").val().substring(1,$(".modulesIds").val().length);
			$(".modulesIds").val(ss);
		}
		
		/* 统计数量 */
		var count=ids.length;
		
		if (ids.length!=0&&ids[0].length==0) {
			count=count-1;
		}
		
		/* if (count==0) {
			$(".moduleNum").html("尚未选择");	
		}else{
			$(".moduleNum").html("已选择"+count+"个权限&nbsp;&nbsp;<a>点击查看</a><input type='hidden' name='moduleNum' class='moduleNumHidden' value='"+count+"'>");
		}
		
		if ($(".modulesIds").val().length==0) {
			$(".moduleNum").html("尚未选择<input type='hidden' name='moduleNum' class='moduleNumHidden' value='0'>");	
		} */
		$(".leve1Radio").val();
		/* $("#level1Count:checked").val(); */
		var radio= $(".leve1Radio:checked");
		var len= $(".mIds:checked").length;
		if (len==0) {
			radio.parent().find(".level1CountSpan").text("");
			$(".leve1Radio:checked").next().val("");
		}else{
			radio.parent().find(".level1CountSpan").text("("+len+")");
			$(".leve1Radio:checked").next().val(len);
			/* if (len==$(".mIds").length) {
				radio.parent().find("#level1Count").text("(全部)");	
			} */
			
		}
	
		
		
	}
		
		
	
</script>
</head>



<body style="overflow-y: scroll;overflow-x: hidden;">

	<form id="myForm" method="post">
		
		<table class="addFloor" border="0" bordercolor="#a0c6e5">
			<tr>
				<td colspan="2"><input type="hidden" name="id"
					value="${masterId}"> <br /></td>
			</tr>
			<tr>
				<td class="title">角色名:</td>
				<td><input type="text" style="height: 30px;" class="name" name="name" value="${name }"></td>
			</tr>
			<tr>
				<td class="title">
				<!-- 已选择权限: -->
				</td>
				<td>
				<%-- <span class="moduleNum">
					<c:if test="${moduleNum!=0 }">
						已选择${moduleNum}个权限&nbsp;&nbsp;<a>点击查看</a>
						<input type="hidden" name="moduleNum" class="moduleNumHidden" value="${moduleNum}">
					</c:if>
					<c:if test="${moduleNum==0 }">尚未选择</c:if>
				</span> --%>
				<input type="hidden" name="modulesIds" value="${modulesIds}" class="modulesIds">
				</td>
			</tr>
			<tr>
				<td class="title">选择权限:</td>
				<td
					style="border: 1px solid orange; padding-top: 0px; line-height: 40px; vertical-align: top;width: 93%;">
					<div id="left" ><br /> 一级菜单：<br />
					<table>
						<tr>
						<c:forEach var="m" items="${level1Modules}" varStatus="status">
								<td><input onchange="chooseLevel(this.value)" name="level1" id="level1Radio${m.id}" type="radio"
										class="leve1Radio" value="${m.id }" 
											<c:if test="${m.id==level1}">checked='checked'</c:if>
									>
									<input type="hidden" value="${level1Counts!=null?level1Counts[status.index]:0 }" class="level1CountHidden">
									<label for="level1Radio${m.id}" style="font-size: 20px;">${m.name }</label>
									<span class="level1CountSpan" style="color: red;">
 										<c:if test="${level1Counts!=null&&level1Counts[status.index]!=0}">
 										 
										(${level1Counts[status.index]})
										</c:if>
										<c:if test="${level1Counts==null}"></c:if>
										&nbsp;&nbsp;&nbsp;
									</span>	
									
								</td>
								<!-- 换行  -->
						    	<c:if test="${status.count%3==0&&status.count!=0 }"></tr><tr></c:if>
						</c:forEach>
						</tr>
					</table>
					</div>
				<div id="right">
						二级菜单(按钮)：
				</div>
					
  <div id="right" style="margin-left: 50px;">
   <ul class='filetree browser'>
   	  <c:forEach var="m" items="${level2Modules}" varStatus="status">
			<li><span class='folder'>
			
			
			<input  <c:forEach var="selectedId" items="${modulesIdList}"><c:if test="${selectedId==m.id }">checked='checked'</c:if></c:forEach>
			type="checkbox" class="mIds" onchange="choose(this)"  name="ids" id="t${m.id }" pId="t0" value="${m.id}"/>${m.name }</span>
			<c:forEach var="b" items="${m.btnModules}">
				<ul>
				<li><span class='file'>
				
				<input <c:forEach var="selectedId" items="${modulesIdList}"><c:if test="${selectedId==m.id }">checked='checked'</c:if></c:forEach> 
				 type="checkbox" class="mIds" name="ids" id='t${b.id }' pId="t${m.id }" value="${b.id}" onchange="choose(this)"/>${b.name}</span></li>
				</ul>
			</c:forEach>
			</li>	
			<!-- 换列 -->
			<c:if test="${status.index==1 }">
			</ul>
			</div>
			<div id="right" style="margin-left: 50px;">
				<ul  class='filetree browser'>
			</c:if>
		</c:forEach>
		</ul>
</div>


</td>
</tr>
			<tr style="text-align: center;">
				<td colspan="2"><br />
				<br /> <input class="btn" type="button" value="新增"
					style="width: 90px;" onclick="apply()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="btn" type="button"
					style="width: 70px;" value="返回"
					onclick="javascript:parent.layer.close(parent.layer.getFrameIndex(window.name));">
				</td>

			</tr>
		</table>
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".browser").treeview({});

			$("#btn1").click(function() {
				var c = $("input[name='ids']:checked");

				alert(c.length);
			})

			$("#btn2").click(function() {
				var h = $("input[name='ids']:indeterminate");
				alert(h.length);
			})

		});

		function findParent(p, checked) {
			$("input[id='" + p + "']").attr("indeterminate", checked);
		}
		
		
		function child(e) {
			var pId = e.id;
			var checked = e.checked;
			var p = $("#" + pId + "").attr("pId");

			if (checked) {
				findParent(p, checked);
				var children = $("input[pId='" + pId + "']");
				for (var i = 0; i < children.length; i++) {
					$(children[i]).attr("checked", checked);
				}

				/* var checkLevelLengh = $("input[pId='" + p + "']:checked").length; */
				
				
				var levelLengh = $("input[pId='" + p + "']").length;
				/* if (checkLevelLengh == levelLengh) { */
					$("#" + p + "").removeAttr("indeterminate");
					$("#" + p + "").attr("checked", checked);
				/* } */
			} else {
				var children = $("input[pId='" + pId + "']");
				for (var i = 0; i < children.length; i++) {
					$(children[i]).attr("checked", checked);
				}

				var checkLevelLengh = $("input[pId='" + p + "']:checked").length;
				
			}
		}
	</script>

</body>
</html>
