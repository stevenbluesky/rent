
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>单位档案管理</title>
<meta name="decorator" content="default" />
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
.jbox-title-panel {
	background: #dc6c6c !important;
	text-align: center;
}

.area-content {
	min-height: 84px;
	min-width: 475px;
}
</style>
<script type="text/javascript">
$(function(){
	/* 操作后的提示语 */
	if (<%=request.getAttribute("tip")!=null%>) {
		$.jBox.tip("<%=request.getAttribute("tip")%>");	
	}
});

function selectSubmit(){	
	
   
	layer.ready(function(){ 
		var index =  layer.open({
		    type: 2,
		    title: '请点击右上角关闭！',			    
		    maxmin: true,
		    area: ['1200px', '700px'],
		    /* content: 'http://layer.layui.com/test/welcome.html', */
		    /* content: '${ctx}/syseckRepaire.jsp', */
		    content:'prhRmdevMoBan?houseid=${houseid}',  		    
		    end: function(){		    	
		    	location.reload();		    
		    }
		  });
		layer.full(index);
		});
	
	
}	
	function addSubmit(){		
		layer.ready(function(){ 
			var index=  layer.open({
			    type: 2,
			    title: '新增配套设备',			    
			    maxmin: true,
			    area: ['1200px', '400px'],
			    /* content: 'http://layer.layui.com/test/welcome.html', */
			    /* content: '${ctx}/syseckRepaire.jsp', */
			    content:'prhRmdevCenter?flag=1&houseid=${houseid}',   		
			   
			    
			    end: function(){
			    	location.reload();		
			    }
			  });
			layer.full(index);
			});
		
		
	}
	function deleteSubmit(){		
		var rent = document.querySelector('#contentTable input[type="radio"]:checked');
		if (!rent) {
			$.jBox.tip('请选择具体的设备！');
			return;
		}
		if (confirm("你真的打算删除这条设备信息吗?")) {  
			var rentID = rent.value;
			window.location.href='${ctx}/../deleteRmdev?id='+rentID+'&housid='+${houseid};
        }  
        else {  
           return false;
        }  
		
		
	}
	function back(){	
		<%request.removeAttribute("tip");%>
		window.history.back();
		
	}
	
	
</script>

</head>
<body>






	<div class="row-fluid">
		<div class="span4">
			<ul class="nav nav-tabs" style="width: 100%">
				<c:forEach items="${estates}" var="e">
					<li <c:if test="${e.id==estateId}">class="active"</c:if>><a
						href="${ctx}/../prHouseFile?estateId=${e.id}">${e.name }</a></li>
				</c:forEach>
				<!--  <li><a href="${ctx}/sys/repaire/list">报修登记</a></li>-->
			</ul>
		</div>
		<div class="span8 repaire-search">
			<!-- <form method="post" action="${ctx}/../House">
				<div class="row-fluid">
					<div class="hide">
						<input type="hidden" value="" />
					</div>
					<div class="span1 search-input">
						有效<input name="sta" type="radio" id="sta1" value="1"></input> 停用<input
							name="sta" type="radio" id="sta2" value="2"></input> 删除<input
							name="sta" type="radio" id="sta3" value="3"></input>
					</div>
					<div class="span1 search-input">
						姓名<input name="name" type="text" id="name"></input> <input
							id="search" class="btn" type="submit" value="条件搜索">
					</div>
				</div>
			</form>
			 -->
		</div>
	</div>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>编号</th>
				<th>类别</th>
				<th>项目</th>
				<th>状态</th>
				<th>数量</th>
				

				<%--<th>角色</th> --%>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="b" items="${page.list}">
				<tr style="text-align: center;">
					<td><input type="radio" value="${b.id}" class="rent-check"
						name="rentCheck"></td>
					<td>${b.id}</td>				
					<c:if test="${b.devclass eq '1'}">
						<td>1.钥匙</td>					
					</c:if>
					<c:if test="${b.devclass eq '2'}">
						<td>2.房内设施</td>					
					</c:if>
					<c:if test="${b.devclass eq '3'}">
						<td>3.房内电器</td>					
					</c:if>
				
					<td>${b.devname}</td>
					<c:if test="${b.sta=='1'}">
						<td>正常</td>					
					</c:if>
					<c:if test="${b.sta!='1'}">
						<td>异常</td>					
					</c:if>
					<td>${b.devnumb}</td>
		
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<sys:message content="${message}"/>
	<div class="pagination">${numpage}</div>
	<div>		
		<button class="btn" style="margin-right:10px;"  onclick="addSubmit()">新增设备</button>		
		
		 <button class="btn" style="margin-right:10px;" onclick="selectSubmit()">选择模板</button>
	
		<button id="del" class="btn" style="margin-right:10px;"  onclick="deleteSubmit()">删除设备</button>
		
		<button id="del" class="btn" style="margin-right:10px;"  onclick="back()">返回</button>
	</div>
	
	
</body>
</html>
