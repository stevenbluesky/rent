  <%@page import="java.util.Date"%>
<%@page import="com.rent.entity.Users"%>

  <%
/* 判断session是否过期 */
	if(session.getAttribute(Global.USER)==null&&!"1".equals(request.getAttribute("isLogin"))){
%>

<script type="text/javascript">top.location.href='/rent/toLogin?state=1';</script>	
<%
	}
%>    
 
 
<%@page import="com.rent.common.config.Global"%>
<%@ page contentType="text/html;charset=UTF-8"%><meta
	http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://jeesite.com/" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  

<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<!--崔少岩  -->
<script src="${ctxStatic}/jquery/jquery-form.js" type="text/javascript"></script>
<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>

<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->

<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
<script src="${ctxStatic}/moment/moment.js" type="text/javascript"></script>
<script src="${ctxStatic}/lodash/lodash.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/float/zzsc.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
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

#contentTable {
	font-size: medium;
    font-style: normal;
}


</style>
<script type="text/javascript">
$(function(){
	var maxWidth=0;
	var lis= $($(".nav-tabs").get(0)).find("li"); 
	lis.each(function(i,li){
		maxWidth+=($($(li).get(0)).width());
	});
	maxWidth+=20;
	 $(".nav-tabs").attr("style","width: "+maxWidth+"px");
	
	 var attr= $(".nav-tabs").parent().attr("style");
	 if (attr!=null) {
		
		 $($(".nav-tabs").get(0)).parent().attr("style",attr+";overflow-x:auto;width:100%;");
	}else{
		
		$($(".nav-tabs").get(0)).attr("style","overflow-x:auto;width:100%;");
		 
	}
	 
	 var w= $($(".nav-tabs").get(0)).width();
	var wparent=$($(".nav-tabs").get(0)).parent().width();
	if (w<=wparent) {
		 $(".nav-tabs").attr("style","width: 100%");
	}
	
	 
});

</script>
 