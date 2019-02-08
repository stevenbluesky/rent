<%@page import="com.rent.entity.Users"%>
<%@page import="com.rent.common.config.Global"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
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
	width: 190px;
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

.menu-li {
	display: block;
	width: 100%;
}

.accordion-group {
	width:179px;
	border: 0px;
	margin-bottom: 0px;
}

.accordion-heading {
	height: 40px;
	background-image: url(${ctxStatic}/images/select.jpg);
	background-repeat: repeat;
}
.accordion{

background-image: url(${ctxStatic}/images/expt.jpg);
	height:647px;
	width:179px;
	
}
.accordion-group a {
	color: #9B9EA5 !important;
}

.accordion-group a:hover {
	color: #ffffff !important;
}

.nav-list {
	padding-left: 22px;
}

.menu-li a:hover {
	background-color: #ff0000;
}

.accordion-toggle {
	font-size: 18px !important;
	color: #9A9EA7;
	height: 30px;
	line-height: 30px;
}

.accordion-body {
	background-image: url(${ctxStatic}/images/expt.jpg);
}

.accordion-inner {
	padding: 0px;
	border: 0px;
}



.menu-li-active
{
	color: blue;
}

.menu-li-active
{
	font-size: 16px !important;
	font-weight: bold;
}

.menu-li-active a{
	color:#ffffff  !important;
}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// <c:if test="${tabmode eq '1'}"> 初始化页签
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight },
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// <c:if test="${tabmode eq '1'}"> 隐藏页签
					$(".jericho_tab").hide();
					$("#mainFrame").show();//</c:if>
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
							top.location = "${ctx}";
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
							// <c:if test="${tabmode eq '1'}"> 打开显示页签
							return addTab($(this)); // </c:if>
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
			// <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
			$("#userInfo .dropdown-menu a").mouseup(function(){
				return addTab($(this), true);
			});// </c:if>
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
		});
		
		 $(function() {
		$(".menu-li").click(function(){
			$(".menu-li").removeClass("menu-li-active");
			//$(this).siblings().removeClass("menu-li-active");
			$(this).addClass("menu-li-active");

		})
	}) 
	</script>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top"
			style="background-image: url(${ctxStatic}/images/top_bg.jpg);margin:0px;">
			<div class=""
				style="display: flex; border-bottom: 2px solid #04498c;">
				<div
					style="width: 140px; height: 71px; display: inline-flex; font-size: xx-large; text-align: center; line-height: 71px; padding-left: 50px;">

					<img src="${ctxStatic}/images/logo.png">
				</div>
				<div class="brand"
					style="padding: 0 0; height: 71px; line-height: 71px; border-left: 2px solid #ccc; width: 640px;">
					<span id="productName" style="margin-left: 30px; color: #cc0000;">公租房管理系统</span>&nbsp;&nbsp;
					
				</div>

				<ul id="userControl" class="nav pull-right" style="margin-left: 70px;" >
				<li>
						<div
							style="height: 71px; line-height: 71px; text-align: center; font-size: 15px; margin-left: 10px;">
							<span style="color: #5B636E;"> 欢迎您！${user.name }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%
 	Date date = new Date();
 	request.setAttribute("now", date);
 %> <fmt:formatDate value="${now }" pattern="yyyy-MM-dd" />
					</span>
						</div>
					</li>
					<li id="themeSwitch" class="dropdown"
						style="border-left: 2px solid #DADBE0; margin-left: 10px;"><a
						class="dropdown-toggle" href="/rent/toChooseManager">
							<div style="text-align: center; color: #5B636E">
								<i class="icon-th-large"></i>
								<div>主菜单</div>
							</div>
					</a> <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li>


					<c:forEach var="role" items="${user.rolesList}">
						<c:if test="${role.id==1 }">
							<li id="systemManager" class="dropdown"
						style=" margin-left: 10px;">
						<a class="dropdown-toggle" href="/rent/toParamIndex">
							<div style="text-align: center; color: #5B636E">
								<i class="icon-gear"></i>
								<div>系统管理</div>
							</div>
					</a>
					</li>
						</c:if>
						
					</c:forEach>


					
					<li><a class="dropdown-toggle" href="/rent/toLogin?state=2">
							<div style="text-align: center; color: #5B636E">
								<i class="icon-signout"></i>
								<div>退出登录</div>
							</div>
					</a></li>
						<li id="themeSwitch" class="dropdown"
						style="margin-left: 10px;"><a
						class="dropdown-toggle" onclick="editPwd()">
							<div style="text-align: center; color: #5B636E">
								<i class="icon-lock"></i>
								<div>修改密码</div>
							</div>
					</a> 
					</li>
					<script type="text/javascript">
							function editPwd(){
								
								layer.ready(function(){ 
									  layer.open({
									    type: 2,
									    title: '修改密码',
									    
									    maxmin: false,
									    area: ['490px', '253px'],
									    content:'/rent/toEditPwd.do',
									    end: function(){
									    	
									    }
									  });
									});
							 }
						</script>
				</ul>
			
			
			<div class="accordion" id="menu-1" 
					style="position: absolute; top: 71px; left: 0;">
					<c:set var="menuList" value="${fns:getMenuList()}" />
					<c:set var="firstMenu" value="true" />
					<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
						
						<c:if test="${menu.parent.id eq '333' && menu.isShow eq '1'}">
						
						
			 
								<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse"
										data-parent="#menu-${parentId}"
										data-href="#collapse-${menu.id}" href="#collapse-${menu.id}"
										title="${menu.remarks}"><i
										class="icon-chevron-${not empty firstMenu && firstMenu ? 'down' : 'right'}"></i>&nbsp;${menu.name}</a>
								</div>
								<div id="collapse-${menu.id}"
									class="accordion-body collapse ${not empty firstMenu && firstMenu ? 'in' : ''}">
									<div class="accordion-inner">
										<ul class="nav nav-list">
											<c:forEach items="${menuList}" var="menu2">
											
												<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
													
												
			 
																<li class='menu-li'><a data-href=".menu3-${menu2.id}"
														href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}"
														target="${not empty menu2.target ? menu2.target : 'mainFrame'}"
														style="color: blue"><i
															class="icon-${not empty menu2.icon ? menu2.icon : 'circle-arrow-right'}"></i>&nbsp;${menu2.name}</a>
														<ul class="nav nav-list hide"
															style="margin: 0; padding-right: 0;">
															<c:forEach items="${menuList}" var="menu3">
																<c:if
																	test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
																	<li class="menu3-${menu2.id} hide"><a
																		href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}"
																		target="${not empty menu3.target ? menu3.target : 'mainFrame'}"><i
																			class="icon-${not empty menu3.icon ? menu3.icon : 'circle-arrow-right'}"></i>&nbsp;${menu3.name}</a></li>
																</c:if>
															</c:forEach>
														</ul></li>
													<c:set var="firstMenu" value="false" />
					
						
												
												</c:if>
											</c:forEach>
											<!--二级菜单结束  -->
												
											
											
											
										</ul>
									</div>
								</div>
							</div>
								
								
						
					
						
							
						</c:if>
						
					</c:forEach>
					
				</div>
		
		
			</div>
			
			</div>
		</div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left"><%-- 
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
				</div>
				<!-- <div id="openClose" class="close">&nbsp;</div> -->
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="/rent/toWelcome.do" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	             ${fns:getConfig('productName')} 
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
			$("#openClose").height($("#openClose").height() - 5);// <c:if test="${tabmode eq '1'}"> 
			$(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}	// <c:if test="${tabmode eq '1'}"> 
		function openCloseClickCallBack(b){
			$.fn.jerichoTab.resize();
			document.querySelector('#menu-0').style="display:none";
		} // </c:if>
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
	
</body>
</html>