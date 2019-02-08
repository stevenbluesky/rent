<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>登录</title>
	<meta name="decorator" content="default"/>


	<style type="text/css">
	   #main{
	      width:100%; height:100%;
	      background-image: url("${ctxStatic}/image/loginBack.jpg");
	      background-size:100%;
	      background-repeat: no-repeat;
	   }
		#login{
		  position: relative;left: 450px;top: 220px;
		
		}
		.login{
			text-align: center;
		}
		.login tr{
			height: 60px;
		}
		.login tr td input{
			width:180px;
			height: 37px;
			background-color: #dfdfdf;
		}
		.login tr td img{
		    width: 39px;
		    
		}
		#buidingPic{
		    width:300px;
		    height:200px;
		    position: relative;left:829px; top:-15px;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			
			
			
			var tip= $(".tip").val();
			if (<%=request.getAttribute("tip")!=null%>) {
				
				$.jBox.tip("<%=request.getAttribute("tip")%>");	
			}
			$(".sumitBtn").click(function(){
				login();
				
			});
		});
	
	    function login(){
			var url="login";
			  $.ajax({
	                 type: "POST",
	                 dataType: "Text",
	                 url: url,
	                 data: $('#loginForm').serialize(),
	                 success: function (result) {
	                	 if (result==1) {
							location.href="toChooseManager";
						}else{
							$.jBox.tip("用户名或密码错误");	
						}
	                     //加载最大可退金额
	                 },
	                 error: function(data) {
	                     alert("error:"+data.responseText);
	                  }
	             });
		}
	    
		
	</script>
	
	
</head>
<body>
		
		 <div id="main" >
         <div  id="login">
         	
			<form id="loginForm" method="post">
				<table class="login" border="1"
					style="border-collapse: collapse; width: 380px; height: 200px;">
					<tr style="height: 50px;">
						<td
							style="background-color: #3D5263; color: white; font-weight: bold;">系统管理员登陆</td>
					</tr>


					<tr style="background-color: ededed; border: 0px;" valign="bottom">
						<td><img src="${ctxStatic}/image/userIco.png"
							style="position: relative; top: -5px; left:6px; height: 38px;" />
							<input name="name" type="text" style="height: 37px;"></td>
					</tr>
					
					<tr style="background-color: ededed; border: 0px;" valign="bottom">
						<td><img src="${ctxStatic}/image/pwdIco.png"
							style="position: relative; top: -5px; left:6px;" /> <input
							name="pwd" type="password"></td>
					</tr>
					<tr style="background-color: ededed; border: 0px;" valign="bottom">
						<td>
						    <img class="sumitBtn" src="${ctxStatic}/image/loginBtn.png" style="width: 215px;height:30px; position: relative; left: 4px;"> 
						</td>
					</tr>
					<tr style="background-color: ededed; border: 0px;" valign="bottom">
					
						<td></td>
					</tr>
				</table>
			</form>
		</div>
          <div id="buidingPic">
              	<img src="${ctxStatic}/image/building.png"></img>
          </div>
    </div>

	
</body>
</html>


	

  </head>
  
  <body>
   
  </body>
</html>

