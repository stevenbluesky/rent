<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" />
<title>模块管理</title>

<script type="text/javascript">
	$(function() {
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		
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
	
	
	function toAdd(){
		layer.open({
		    type: 2,
		    title: '新增用户',
		    maxmin: false,
		    scrollbar: true,
		    area: ['700px', '500px'],
		    content:'toUserAdd.do',
		    end: function(){
		    	
		    }
		  });
		
	}
	
	 function chk(){
			var chkAllFlag= $(".chkAll").attr("checked");
			
			var chks= $(".chk");
			if(chkAllFlag=="checked"){
				$(".chk").attr("checked",true);
			}else{
				$(".chk").attr("checked",false);
			}
		}
	function addResult(result){
		if (result==1) {
			alert("新增成功");
			location.reload();
		}
	}
	
	function editResult(result){
		if (result==1) {
			alert("修改成功");
			location.reload();
		}
	}
	
	/* 删除 */
	 function del(){
 	
		var count=0;
		   var chk= $(".chk");
		//循环
		chk.each(function(e,q){
			var flag= $(q).is(":checked");
			if(flag){
				
				count++;
			}	
			
		});
	    
	    if(count==0){
			$.jBox.tip("请选择要删除的用户！");
			$("#myForm").submit(function(){
				return false;
			});
			return;
			
     	}
	    if (!confirm("确认删除吗？")) {
	    	$("#myForm").submit(function(){
				return false;
			});
	    	return;
		}
	    return true;
	}
	
		function toEdit(){
			var chk= $(".chk:checked");
			var count=0;
			var id=null;
			//循环
			
			chk.each(function(e,q){
				var flag= $(q).is(":checked");
				if(flag){
					id=$(q).val();
					count++;
				}	
			});
			if(count==0){
				$.jBox.tip("请选择要修改的用户！");
				return;
			}else if(count>1){
				$.jBox.tip("请勿选择多个用户！");
				return;
			}
			
			layer.open({
			    type: 2,
			    title: '修改用户',
			    maxmin: false,
			    scrollbar: true,
			    area: ['700px', '500px'],
			    content:'toUserEdit.do?userId='+chk.val(),
			    end: function(){
			    	
			    }
			  });
		}
</script>


</head>
<body>
<input type="hidden" value="${menuLevel}" class="menuLevelHidden"/>

	<!--第一种形式-->
	<div>
		
		<div class="main" id="main0">
		 <form id="myForm" action="delUser.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
					<th><input  type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/>选择</th>
					 	<th>用户名</th>
					 	<th>物业</th>
					 	<th>角色</th>
						<th>性别</th>
						<th>电话</th>
						<th>地址</th>
					</tr>
					<c:forEach var="u" items="${users }">
						<tr style="text-align: center;">
						<td style="width: 5%;">
						<c:if test="${user.id!=u.id}">
							<input  type="checkbox" name="chk" class="chk" value="${u.id}"/> 
						</c:if>
						</td>
							
							<td>${u.name }</td>
							<td>
							<c:if test="${u.estate.name==null }">所有物业</c:if>
							<c:if test="${u.estate.name!=null }">${u.estate.name }</c:if>
							</td>
							<td>
							<c:forEach var="r" items="${u.rolesList }" varStatus="status">
								${r.name}
								<c:if test="${status.index!=fn:length(u.rolesList)-1 }">,</c:if>
							</c:forEach> 
							
							
							</td>
							<td>${u.sex==0?"女":"男" }</td>
							
							<td>${u.phone }</td>
							<td>${u.address }</td>
							
						</tr>
					</c:forEach>
				</table>

				
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="hidden" name="op" class="op" />
				 <input class="btn" value="新增" type="button" onClick="toAdd()" />&nbsp;&nbsp;&nbsp;&nbsp; 
 				 <input class="btn" value="修改" type="button" onclick="toEdit()" />&nbsp;&nbsp;&nbsp;&nbsp;
 				 <input class="btn" value="删除" type="submit" onclick="del()" />
			</form>
		</div>
	</div>
	<br />
	<br />


</body>
</html>