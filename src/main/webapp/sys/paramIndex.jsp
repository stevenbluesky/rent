<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>系统管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://jeesite.com/" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<script src="/rent/static/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<!--崔少岩  -->
<script src="/rent/static/jquery/jquery-form.js" type="text/javascript"></script>
<script src="/rent/static/layer/layer.js" type="text/javascript"></script>

<link href="/rent/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<script src="/rent/static/bootstrap/2.3.1/js/bootstrap.min.js"
	type="text/javascript"></script>
<link href="/rent/static/bootstrap/2.3.1/awesome/font-awesome.min.css"
	type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="/rent/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="/rent/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="/rent/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="/rent/static/jquery-select2/3.4/select2.min.css"
	rel="stylesheet" />
<script src="/rent/static/jquery-select2/3.4/select2.min.js"
	type="text/javascript"></script>
<link
	href="/rent/static/jquery-validation/1.11.0/jquery.validate.min.css"
	type="text/css" rel="stylesheet" />
<script
	src="/rent/static/jquery-validation/1.11.0/jquery.validate.min.js"
	type="text/javascript"></script>
<link href="/rent/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />
<script src="/rent/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="/rent/static/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script src="/rent/static/common/mustache.min.js" type="text/javascript"></script>
<link href="/rent/static/common/jeesite.css" type="text/css"
	rel="stylesheet" />
<script src="/rent/static/common/jeesite.js" type="text/javascript"></script>
<script src="/rent/static/moment/moment.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '/rent/a', ctxStatic='/rent/static';</script>


<meta name="decorator" content="blank" />

<style type="text/css">
#main {
	padding: 0;
	margin: 0;
}

#main .container-fluid {
	padding: 0 4px 0 6px;
}

#header {
	margin: 0 0 8px;
	position: static;
}

#header li {
	font-size: 14px;
	_font-size: 12px;
}

#header .brand {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 26px;
	padding-left: 33px;
}

#footer {
	margin: 8px 0 0 0;
	padding: 3px 0 0 0;
	font-size: 11px;
	text-align: center;
	border-top: 2px solid #0663A2;
}

#footer, #footer a {
	color: #999;
}

#left {
	overflow-x: hidden;
	overflow-y: auto;
}

#left .collapse {
	position: static;
}

#userControl>li>a { /*color:#fff;*/
	text-shadow: none;
}

#userControl>li>a:hover, #user #userControl>li.open>a {
	background: transparent;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// 
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "/rent/a";
							return false;
						}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							// 
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
						$(menuId + " .accordion-body li:first li:first a:first i").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// 
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
		});
	</script>

</head>
<body>

	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner"
				style="display: flex; border-bottom: 2px solid #04498c;">
				<div
					style="width: 140px; height: 71px; display: inline-flex; font-size: xx-large; text-align: center; line-height: 71px; padding-left: 50px;">Logo</div>
				<div class="brand"
					style="padding: 0 0; height: 71px; line-height: 71px; border-left: 2px solid #1a99e2;">
					<span id="productName">系统管理</span>
				</div>
				<ul id="userControl" class="nav pull-right" style="left:50%;margin-left: 100px;" >
					<li id="themeSwitch" class="dropdown" style="border-left: 2px solid #1a99e2;margin-left:10px;">
						
						<a class="dropdown-toggle"  href="/rent/toChooseManager" >
							<div style="display:block;text-align:center">
								<i class="icon-th-large"></i>
								<div>主菜单</div>
							</div>
						</a>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li>
					<li id="systemManager" class="dropdown" style="border-right: 2px solid #1a99e2;margin-left:10px;">
						<a class="dropdown-toggle"  href="/rent/a/sys/rent/index" >
							<div style="display:block;text-align:center">
								<i class="icon-gear"></i>
								<div>公租房管理</div>
							</div>
						</a>
					</li>
					<li>
					<a class="dropdown-toggle"  href="/rent/toLogin" >
							<div style="display:block;text-align:center">
								<i class="icon-signout"></i>
								<div>退出登录</div>
							</div>
						</a>
					
					</li>
					<li>
						<div style="height:71px;line-height:71px;text-align:center;font-size:25px;margin-left:10px;">
						 <% Date date=new Date(); request.setAttribute("now", date); %>
							  <fmt:formatDate value="${now }" pattern="yyyy-MM-dd" />
						</div>
					</li>
				</ul>
				<div class="accordion" id="menu-1"
					style="position: absolute; top: 80px; width: 160px; left: 0;">

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#menu-1" data-href="#collapse-1" href="#collapse-1"
								title=""><i class="icon-chevron-down"></i>&nbsp;用户管理</a>
						</div>
						<div id="collapse-1" class="accordion-body collapse in">
							<div class="accordion-inner">
								<ul class="nav nav-list">

									<!-- <li><a data-href=".menu3-11" href="/rent/a/sys/rent/list/"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;报修登记</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">
										</ul></li> -->
								</ul>
							</div>
						</div>
					</div>



					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#menu-1" data-href="#collapse-10"
								href="#collapse-10" title=""><i class="icon-chevron-right"></i>&nbsp;系统参数</a>
						</div>
						<div id="collapse-10" class="accordion-body collapse ">
							<div class="accordion-inner">
								<ul class="nav nav-list">

									<li><a data-href=".menu3-19"
										href="/rent/a/../findAllEstate.do" target="mainFrame"
										style="color: blue"><i class="icon-circle-arrow-right"></i>&nbsp;物业管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">




										</ul></li>
									<li><a data-href=".menu3-20"
										href="/rent/a/../findBuildingByEstatePaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;楼号管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">



										</ul></li>
									<li><a data-href=".menu3-21"
										href="/rent/a/../findBuildingNoByEstatePaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;单元管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">



										</ul></li>
									<li><a data-href=".menu3-22"
										href="/rent/a/../findAllBuildingFloorPaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;楼层管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">


										</ul></li>
									<li><a data-href=".menu3-23"
										href="/rent/a/../findAllRoomTypePaged.do" target="mainFrame"
										style="color: blue"><i class="icon-circle-arrow-right"></i>&nbsp;房间类型管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
									<li><a data-href=".menu3-24"
										href="/rent/a/../findAllRoomFeaturePaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;房间特征管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">


										</ul></li>
									<li><a data-href=".menu3-25"
										href="/rent/a/../findAllRepairReasonPaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;维修原因管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
									<li><a data-href=".menu3-26"
										href="/rent/a/../findAllEquipmentPaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;配套设施类型</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
									<li><a data-href=".menu3-27"
										href="/rent/a/../findAllElectricPaged.do" target="mainFrame"
										style="color: blue"><i class="icon-circle-arrow-right"></i>&nbsp;电器管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
									
									<li><a data-href=".menu3-29"
										href="/rent/a/../findAllRentPayWayPaged.do" target="mainFrame"
										style="color: blue"><i class="icon-circle-arrow-right"></i>&nbsp;租金支付方式</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
									<li><a data-href=".menu3-30"
										href="/rent/a/../findAllSubsidyTypePaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;补贴标准管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">


										</ul></li>
									<li><a data-href=".menu3-31"
										href="/rent/a/../findSubsidyByEstatePaged.do"
										target="mainFrame" style="color: blue"><i
											class="icon-circle-arrow-right"></i>&nbsp;补贴管理</a>
										<ul class="nav nav-list hide"
											style="margin: 0; padding-right: 0;">

										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left"></div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src=""
						style="overflow: visible;" scrolling="yes" frameborder="no"
						width="100%" height="650"></iframe>
				</div>
			</div>
			<div id="footer" class="row-fluid">
				Copyright &copy; 2016-2020 iSurpass公租房管理系统 - Powered By <a
					href="http://jeesite.com" target="_blank"></a>
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// 
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}	
	</script>
	
	<script src="/rent/static/common/wsize.min.js" type="text/javascript"></script>


</body>
</html>