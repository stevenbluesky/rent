<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>

<head>

	<title>单位档案管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.jbox-title-panel{
			background:#dc6c6c !important;
			text-align:center;
		}
		.area-content{
			min-height: 84px;
			min-width: 475px;
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
	</style>

	

	
	<script type="text/javascript">

	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		
	});
	
	
	
	function updateCom(){		
			var rent = document.querySelector('#contentTable input[type="radio"]:checked');
			if (!rent) {
				$.jBox.tip('请选择具体的单位档案！');
				return;
			}
			var rentID = rent.value;
			
			layer.ready(function(){ 
				var index =   layer.open({
				    type: 2,
				    title: '修改单位信息！',			    
				    maxmin: true,
				    area: ['600px', '300px'],
				    /* content: 'http://layer.layui.com/test/welcome.html', */
				    /* content: '${ctx}/syseckRepaire.jsp', */
				    content:'updateCom?guestno='+rentID+"&estateId=${estateId}",  		    
				    end: function(){		    	
				    	location.reload();		    
				    }
				  });
				
				});
			
		}

	function addCom(){
		
		layer.ready(function(){ 
			var index =   layer.open({
			    type: 2,
			    title: '新增单位信息！',			    
			    maxmin: true,
			    area: ['600px', '300px'],
			    
			    content:'addCom?estateId=${estateId}',  		    
			    end: function(){		    	
			    	location.reload();		    
			    }
			  });
			
			});
	}
	function delCom(){
		if (confirm("确认删除吗?")) {
			var rent = document.querySelector('#contentTable input[type="radio"]:checked');
			if (!rent) {
				$.jBox.tip('请选择具体的档案！');
				return;
			}
			var rentID = rent.value;
			window.location.href='${ctx}/../delCompany?guestno='+rentID;	
		}
	}
	</script>

</head>
<body>
	
	<div class="row-fluid">
	<div class="span4" style="width: 100%">
			<ul class="nav nav-tabs" >
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../findAllCompany?estateId=${e.id}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>
			
		</div>
	
		</div>
	<sys:message content="${message}"/>
	<form action="${ctx}/../prUtilFileDetail" id="myForm" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>		<th>选择 </th>
						<th>单位名称</th>										
						<th>电话号码</th>
						
						<th>联系人</th>
						<th>联系方式</th>
						<th>备注</th>
						
		<%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach var="b" items="${page.list}">
			<tr style="text-align: center;">
						<input id="utilfileno" type="hidden" value="${b.guestno}"></input>
						<td><input type="radio" value="${b.guestno}" class="rent-check"
						name="guestno"></td>
							<td>${b.company} </td>	
						<td>${b.phone} </td>
						<td>${b.liason} </td>
						<td>${b.liason1} </td>
						<td>${b.remark }</td>	 		
				</tr>
		</c:forEach>		
		</tbody>
	</table>
	<div class="pagination" >${numpage}</div>
	
	
	<div><input type="button" value="新增单位" class="btn" onclick="addCom()"/>
	<input type="button" value="修改单位" class="btn" onclick="updateCom()"/>
	<input type="button" value="删除单位" class="btn" onclick="delCom()"/></div>
	
	</form>
</body>
</html>