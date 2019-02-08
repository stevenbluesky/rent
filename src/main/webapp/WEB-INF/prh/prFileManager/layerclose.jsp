
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
$(function() {
	 /* 操作后的提示语 */
	if (<%=request.getAttribute("tip")!=null%>) {
		
		$.jBox.tip("<%=request.getAttribute("tip")%>");	
	}

	
});
function layerClose(){
	alert ("关闭layer");
	window.close();
}
</script>
	
</head>
<body>


	<sys:message content="${message}" />			
	
	
</body>
</html>
