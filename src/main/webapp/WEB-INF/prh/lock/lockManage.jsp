<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html >
<head>
<meta http-equiv="Content-Type" />
<title>公租房门锁管理</title>
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
body,div,ul,li {
	padding: 0;
}
body {
	font: 12px "宋体";
	text-align: left;
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
	height: 600px;
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
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		$("#tabs0 li").click(function() {
			$(this).addClass("hover").siblings().removeClass("hover");
			var estateId= $(this).find("input").val();	  
			location.href="findPrHouseByEstatePaged.do?estateId="+estateId+"&currpage=1";
		});
		$(".chkAll").click(function(){
			chk(); //全选全不选
		});
	});
	
    function chk(){
		var chkAllFlag= $(".chkAll").attr("checked");
		
		var chks= $(".chk");
		if(chkAllFlag=="checked"){
			$(".chk").attr("checked",true);
		}else{
			$(".chk").attr("checked",false);
		}
	}
	
	function toEdit(){
		var chk= $(".chk");
		var count=0;
		var baseinfo = null;
		//循环
		
		chk.each(function(e,q){
			var flag= $(q).is(":checked");
			if(flag){
				baseinfo=$(q).val();
				count++;
			}	
		});
		if(count==0){
			$.jBox.tip("请选择要修改的门锁！");
			return;
		}else if(count>1){
			$.jBox.tip("请勿选择多个门锁！");
			return;
		}
		layer.ready(function(){
			  layer.open({
			    type: 2,
			    title: '修改门锁名称',
			    maxmin: false,
			    area: ['494px', '200px'],
			    content:'toLockNameEdit.do?baseinfo='+baseinfo,
			    end: function(){
			    	location.reload();
			    }
			  });
			});
	}

</script>
</head>
<body>

	<!--第一种形式-->
	
<div class="row-fluid">
    <div class="row-fluid repaire-search" style="height: 28px; margin: 0px;padding: 0px;" >
         <form method="post" id="searchForm" action="lockManage.do">
                <div class="row-fluid">
                    <div class="span2 search-input" style="width: 100%;height: 25px;margin-bottom: 10px;" >
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        名称:<input name="lockName" value="${lockName}" type="text" class="searchTxt roomNo1">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        网关ID：<input name="deviceid" value="${deviceid}" type="text" class="searchTxt roomNo1">
                        &nbsp;&nbsp;&nbsp;&nbsp;
						是否绑定房间:&nbsp;&nbsp;
						全部<input type="radio" name="ifBind" value="0" <c:if test="${ifBind!='1' || ifBind!='2'}">checked="checked"</c:if>>&nbsp;
						是<input type="radio" name="ifBind" value="1" <c:if test="${ifBind=='1'}">checked="checked"</c:if>>&nbsp;
						<%--否<input type="radio" name="ifBind" value="2">--%>

						&nbsp;&nbsp;
                        <input type="submit" id="checkRepaire" class="btn"
                                    style="margin-right: 10px;margin-bottom: 13px;" value="条件搜索" >
                         </div>
                    </div>
            </form>
    </div>
</div>
		<div class="main" id="main0" style=" margin: 0px;padding: 0px;">
			<form id="myForm" action="prHouseDel.do" method="post">
			<table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">
				<tr >	
					<th style="width: 40px;padding-top: 9px;">
					  <input type="checkbox"  class="chkAll" id="chkAll" /><label for="chkAll" style="font-weight: bold;font-size: 17px;">选择</label>
				   </th>
						<td>名称</td>
						<th>网关ID</th>
						<th>绑定房间</th>
				</tr>							 
				<c:if test="${fn:length(page.list)==0}">
					<tr>
						<td colspan="12">
							<div style="height: 70px; padding-top: 20px;">
								<h2 style="text-align: center;">尚无数据！</h2>
							</div>
						</td>
					</tr>
				</c:if>

				<c:forEach var="b" items="${page.list }">
					<tr style="text-align: center;">
						<td><input type="checkbox" name="chk" class="chk" value="${b._deviceid}@${b.name}@${b.houseid}"/> </td>
						<td>${b.name}</td>
						<td>${b._deviceid}</td>
						<td>${b.housedesc} </td>
					</tr>
				</c:forEach>
				
			</table>
			
		<br/><div  class="pagination" id="numpage" > ${numpage}</div><br/>

				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="修 改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }">
								<c:if test="${m.id==101}">
						
					<input class="btn" value="修 改" type="button" onclick="toEdit()" />&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>

			</form>	  	
		</div>
</body>
</html>