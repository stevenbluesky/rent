<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>选择管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   #main{
	      width:100%; height:100%;
	      background-image: url("${ctxStatic}/image/loginBack.jpg");
	     background-repeat: no-repeat;
	       background-size: 100%;
	      
	   }
		#ico1{
		  	position: relative;left: 320px;top:300px;
		}
		#ico2{
		 	position: relative;left: 420px;top:300px;
		}
		#ico3{
			position: relative;left: 510px;top:300px;
		}
		.chooseTitle{
			font-size:20px;
			position: relative;left: -123px;top: 125px;
			color:#a3aac4;
			font-weight: bold; 
		}
		.chooseTitle1{
			position: relative;left: -144px;top: 125px;
		}
		.chooseTitle2{
			position: relative;left: -142px;top: 125px;
		}
		
		.chooseTitle a{
		  	color: #a3aac4;
		  	text-decoration: none;
		}
		#icoExit{
			position: relative;left: 725px;top: 450px;
		}
		.ExitText{
			position: relative;left: -76px;top: 45px;
			font-weight: bold; 
		}
		.ExitText a{
		  	color: #a3aac4;
		  	text-decoration: none;
		}
	</style>
  </head>
  
  <body >
    <div id="main" >
    <div  style="position:relative;left: 320px;top: 180px; font-size: 70px;">
    	<h1 style="font-size: 70px;">公租房管理系统软件V1.0</h1>
    </div>
       <div id="ico1" style="display:inline;">
       
           <a href="/rent/a/sys/rent/index"><img  src="${ctxStatic}/image/prhManage.png"></a> 
           					
	                   <span class="chooseTitle chooseTitle1" ><a href="/rent/a/sys/rent/index">公租房管理</a></span>
    	
    	 </div>
    	  <div id="ico2" style="display:inline;">
       				  
	       <a href="/rent/a/sys/rent/thirdIndex"><img  src="${ctxStatic}/image/disanfang.png"></a> 
	     		  <span class="chooseTitle chooseTitle2 " ><a href="/rent/a/sys/rent/thirdIndex">第三方维修</a></span>    
    	
    	 </div>
    	 
    	  <div id="ico3" style="display:inline;">
      
	       <a href="/rent/toParamIndex"> <img  src="${ctxStatic}/image/prhManage.png"></a> 
	     		 <span class="chooseTitle chooseTitle3" ><a href="/rent/toParamIndex">系统管理</a></span>
    	 </div>
    	
    	<div id="icoExit">
    	    	 <a href="toLogin" ><img src="${ctxStatic}/image/exitIco.png"></a>
    	    	 <span class="ExitText" ><a href="toLogin">退出</a></span> 
    	</div>
    </div>
  </body>
</html>
