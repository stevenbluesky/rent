
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主菜单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   #main{
	      width:100%; height:100%;
	      background-image: url("${ctxStatic}/image/loginBack.jpg");
	      background-repeat:round;
	      background-size:100%;
	   }
		
		.chooseTitle{
			font-size:20px;
			position: relative;left: -123px;top: 125px;
			color:#a3aac4;
			font-weight: bold; 
		}
		
		.chooseTitle a{
		  	color: #a3aac4;
		  	text-decoration: none;
		}
		#icoExit{
			/* position: relative;left: 750px;top: 450px; */
		}
		.ExitText{
			/* position: relative;left: -45px;top: 45px; */
			font-weight: bold; 
		}
		.ExitText a{
		  	color: #a3aac4;
		  	text-decoration: none;
		}
		#title
		{
			text-align: center;
		}
	</style>
  </head>
  
  <body >
    <div id="main"  >
    <div style="height:200px;"></div>
    	<div style="width:100%;">
    
    	<h1 style="font-size: 50px;text-align: center;">${fns:getConfig('productName')}</h1>
    	</div>
		<!-- 菜单是否存在  -->
		<c:set var="hasMenu1" value="0" ></c:set>
		<div id="menu" style="margin-top: 70px; text-align: center;">
		
		<c:forEach var="role" items="${user.rolesList}">
			
			<c:if test="${role.id==1 }">
				<div id="ico1" style="display:inline;" >
       				<a href="/rent/a/sys/rent/index"><img  src="${ctxStatic}/image/prhManage.png"></a> 
           			<span class="chooseTitle chooseTitle1" ><a href="/rent/a/sys/rent/index">公租房管理</a></span>
    	 		</div>
				
			</c:if>
			<c:if test="${role.id!=1 }">
				<c:if test="${role.isHouseManager!=null&&role.isHouseManager&&hasMenu1==0 }">
					<div id="ico1" style="display:inline;" >
			       		<a href="/rent/a/sys/rent/index"><img  src="${ctxStatic}/image/prhManage.png"></a> 
           				<span class="chooseTitle chooseTitle1" ><a href="/rent/a/sys/rent/index">公租房管理</a></span>
    	 			</div>
    	 			<c:set var="hasMenu1" value="1" ></c:set>
    	 			
				</c:if>
			</c:if>
		</c:forEach>


			<c:set var="flag" value="0"></c:set>
    	  <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">
    	  		<c:set var="flag" value="1"></c:set>
			 <div id="ico2" style="display:inline;">
	      		  <a href="/rent/a/sys/rent/thirdIndex"><img  src="${ctxStatic}/image/disanfang.png"></a> 
	     		  <span class="chooseTitle chooseTitle2 " ><a href="/rent/a/sys/rent/thirdIndex">第三方维修</a></span>    
    		 </div>
					
						</c:if><c:if test="${role.id!=1 &&flag==0}"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==62}">
						
					<div id="ico2" style="display:inline;">
	      		  <a href="/rent/a/sys/rent/thirdIndex"><img  src="${ctxStatic}/image/disanfang.png"></a> 
	     		  <span class="chooseTitle chooseTitle2 " ><a href="/rent/a/sys/rent/thirdIndex">第三方维修</a></span>    
    				 </div>
					
			 </c:if></c:forEach></c:if></c:forEach>



		<c:forEach var="role" items="${user.rolesList}">
			<c:if test="${role.id==1 }">

				<div id="ico3" style="display:inline;">
      
	       <a href="/rent/toParamIndex"> <img  src="${ctxStatic}/image/sysManagerIco.png"></a>
	     		 <span class="chooseTitle chooseTitle3" ><a href="/rent/toParamIndex">系统管理</a></span>
    	 		</div>
			</c:if>
		 </c:forEach>

		    </div>


    	<div id="icoExit" style="1px solid red; margin-top: 90px;text-align: center;position: relative;left: -30px;">
    	    	 <a href="toLogin" ><img src="${ctxStatic}/image/exitIco.png"></a>
    	</div>

  </body>
</html>
