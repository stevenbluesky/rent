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
	function add(){
		
		var index= layer.open({
		    type: 2,
		    title: '新增角色',
		    maxmin: false,
		    scrollbar: true,
		    area: ['1400px', '500px'],
		    content:'toRoleAdd.do',
		    end: function(){
		    	location.reload();
		    }
		  });
		layer.full(index);
	}
	
	function addResult(result){
		if (result==1) {
			alert("新增成功");
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
			$.jBox.tip("请选择要删除的角色！");
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
	 function edit(){
			var chk= $(".chk");
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
				$.jBox.tip("请选择要修改的角色！");
				return;
			}else if(count>1){
				$.jBox.tip("请勿选择多个角色！");
				return;
			}
			
			var index= layer.open({
			    type: 2,
			    title: '修改角色',
			    maxmin: false,
			    scrollbar: true,
			    area: ['1400px', '500px'],
			    content:'toRoleEdit.do?roleId='+id+"&isFirst=1",
			    end: function(){
			    	location.reload();
			    }
			  });
			layer.full(index);
		}
		
		function addResult(result){
			if (result==1) {
				alert("新增成功");
			}
		}
		function editResult(result){
			if (result==1) {
				alert("修改成功");
			}
		}
</script>


</head>
<body>
<input type="hidden" value="${menuLevel}" class="menuLevelHidden"/>

	<!--第一种形式-->
	<div>
	
		<div class="main" id="main0">
		 <form id="myForm" action="delRole.do" method="post">
				<table border="1" bordercolor="#a0c6e5" id="contentTable"
					class="table table-striped table-bordered table-condensed"
					style="border-collapse: collapse;">
					<tr>
					<th><input  type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/>选择</th>
					 	<th>角色名</th>
						
					</tr>
					<c:forEach var="r" items="${roles }">
						<tr style="text-align: center;">
						<td style="width: 5%;">
						<c:if test="${r.id!=1&&r.id!=8 }">
							<input  type="checkbox" name="chk" class="chk" value="${r.id}"/> 
						</c:if>
						
						</td>
							<td>${r.name }</td>
							
							
						</tr>
					</c:forEach>
				</table>

				
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="hidden" name="op" class="op" />
				 <input class="btn" value="新增" type="button" onClick="add()" />&nbsp;&nbsp;&nbsp;&nbsp; 
				 <input class="btn" value="修改" type="button" onclick="edit()" />&nbsp;&nbsp;&nbsp;&nbsp;
 				 <input class="btn" value="删除" type="submit" onclick="del()" />
			</form>
		</div>
	</div>
	<br />
	<br />


</body>
</html>