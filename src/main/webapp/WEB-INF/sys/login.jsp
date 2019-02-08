<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<title>登录</title>

<style type="text/css">
body{
	 background-color: #3388be; 
}
#all {
/* 	overflow:hidden; */
	
	margin:auto;
	width: 1200px;
	height: 700px;
	background-image: url("${ctxStatic}/image/loginBack.png");  
	background-size:100%;
	background-repeat:no-repeat;
}
#all tr td{
	width: 210px;
	

}
#login {
	text-align: center;
	margin: 0px auto;
	width: 640px;
	/* position: fixed;
	top: 240px;
	left: 480px; */
	
}

.login {
	
	text-align: center;
	vertical-align: middle;
}

.login tr {
	height: 60px;
}

.login tr td input {
	width: 140px;
	height: 37px;
	background-color: #dfdfdf;
}

.login tr td img {
	width: 39px;
}

#buidingPic {
	width: 320px;
	height: 290px;
}
</style>
<script type="text/javascript">
		$(function(){
			var tip= $(".tip").val();
			if (<%=request.getAttribute("tip") != null%>) {			
				$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		$(".sumitBtn").click(function() {
			login();
		});
		//当前页是iframe
		if (self.location!=top.location){
			top.location.href='toLogin';
			
		}
		//当前页不是iframe
		if (self.location==top.location){
				
		} 
	});
	function login() {
		var url = "login";
		$.ajax({
			type : "POST",
			dataType : "Text",
			url : url,
			data : $('#loginForm').serialize(),
			success : function(result) {
				if (result == 1) {
					location.href = "toChooseManager";
				} else {
					$.jBox.tip("用户名或密码错误");
				}

			},
			error : function(data) {
				
			}
		});
	}
</script>
</head>
<html>
<body>

	
	
	<table id="all" style="" >
		<tr><td  style="height: 270px !important ;">&nbsp;
	
		</td><td>&nbsp;</td><td>&nbsp;</td></tr>
		<tr>
		<td></td>
		<!--登录  -->
		<td align="center">
		<form id="loginForm" method="post">
				<table class="login">
					<tr>
						<td>
							<table class="login" border="0"
								style="border-collapse: collapse; width: 100%; height: 100%;">
								
								<tr><td height="10px"></td></tr>
								<tr style="border: 0px;"
									valign="bottom">
									<td>
									<span style="color: white;">用户名：</span><input name="name" type="text"  style="height: 37px;"
											
										>
									</td>
								</tr>
								<tr
									valign="bottom">
									<td style="vertical-align: middle;">
									<span style="color: white;">
									密&nbsp;&nbsp;&nbsp;码：
									</span>
									<input
										name="pwd" type="password"
										
										
										 ></td>
								</tr>
								<tr style=""
									valign="bottom">
									<td style="padding-bottom: 20px;padding-left: 30px;">
								
									<img type="button" class="sumitBtn" src="${ctxStatic}/image/loginBtn.png"
									
									  style="width: 90px;" />
									
									</td>
								</tr>
								
							</table>
						</td>
						
					</tr>
				</table>
				<p style="padding-left:215px; color: red;">注：建议使用IE 11浏览器！</p>
				<p style="padding-left:270px; color: red;">
					<input type="button" class="btn" value="组件下载" onclick="location.href='downloadActiveDll.do'">
					
					
				</p>
				
			</form> 
		
		
		</td>
		
		<td></td></tr>
		
		<tr><td style="height: 180px;"></td><td></td><td></td></tr>
	
	</table>
	
		<%-- <div style="height: 240px"></div>
		<div id="login"  >
			<form id="loginForm" method="post">
				<table>
					<tr>
						<td>
							<table class="login" border="0"
								style="border-collapse: collapse; width: 320px; height: 200px;">
								<tr style="height: 50px;">
									<td style="background-color: #3D5263; color: white; font-weight: bold;">
										</td>
								</tr>
								<tr style="background-color: ededed; border: 0px;"
									valign="bottom">
									<td><img src="${ctxStatic}/image/userIco.png"
										style="position: relative; top: -5px; left: 6px; height: 38px;" />
										<input name="name" type="text"  style="height: 37px;"
											value="admin"
										>
									</td>
								</tr>

								<tr style="background-color: ededed; border: 0px;"
									valign="bottom">
									<td><img src="${ctxStatic}/image/pwdIco.png"
										style="position: relative; top: -5px; left: 6px;" /> <input
										name="pwd" type="password"
										value="123"
										 ></td>
								</tr>
								<tr style="background-color: ededed; border: 0px;"
									valign="bottom">
									<td><img class="sumitBtn"
										src="${ctxStatic}/image/loginBtn.png"
										style="width: 215px; height: 30px; position: relative; left: 4px;">
									</td>
								</tr>
								<tr style="background-color: ededed; border: 0px;"
									valign="bottom">
									<td></td>
								</tr>
							</table>
						</td>
						<td>
							<img id="buidingPic" src="${ctxStatic}/image/building.png"></img>
						
						</td>
					</tr>
				</table>
				<p style="padding-left:465px; color: red;">注：建议使用IE 11浏览器！</p>
				<p style="padding-left:520px; color: red;">
					<input type="button" class="btn" value="组件下载" onclick="location.href='downloadActiveDll.do'">
					
					
				</p>
				
			</form> 
		</div> --%>
	
</body>
</html>