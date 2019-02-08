<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>用户管理</title>
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
	</style>
	<script type="text/javascript">
			
	$(function() {
		 /* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
	
		
	});

	$(function() {
		 /* 操作后的提示语 */
		

		$(".chkAll").click(function(){
			
			chk(); //全选全不选
		});
		
	});
			function batchAudit(){
				 var submit = function (v, h, f) {
		             if (v == 'ok'){
		            	 var chks= $(".chk2");
		     			
		     					//提交 submit		
		     			$("#planform").submit();	
		     			return false;
		     			
		     			
		     			
		             }   
		               else if (v == 'cancel'){
		            		$.jBox.tip("取消操作成功！");
		             }
		             return true; //close
		           };
		  
				$.jBox.confirm("确定要调整这些房源的价格吗？", "敏感操作！", submit);
			}
	</script>
	
</head>
<body>
	
	<form  id="planform" action="plan"  method="post" >
		<table style="margin-left: 50px;">
		<h3>查到${count}间房源!</h3>
		<tr>
			
				<td>请输入调整后的金额:</td>
				<td><input type="text" name="rentMod" /></td>
			
		</tr>
		<tr>
			
				<td>请输入调整的理由:</td>
				<td><input type="text" name="reason" /></td>
			
		</tr>
		<tr>
		
				<td>调整人:</td>
				<td><input type="text" name="decMan" /></td>
		
		</tr>
		<tr>
			
				<td>调价时间:</td>
				<td>
				<input id="startTime" name="decDate" type="text" readonly="readonly" maxlength="20" class="Wdate required"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:true});"/>
				</td>
			
		</tr>
		
		<tr>	
			<td colspan="2">
				<input type="hidden" name="roomtypeId" value="${condition.roomtypeId}"/>
				<input type="hidden" name="estateId" value="${condition.estateId}"/>
				<input type="hidden" name="no" value="${condition.no}"/>
				<input type="hidden" name="danyuanid" value="${condition.danyuanid}"/>
				<input type="button" id="repaireApply" class="btn" style="margin-right:10px;" value="批量调整" onclick="batchAudit();"/>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>