<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<br>
<head>
<br>
<title>Welcome!</title>
<br>
<style type="text/css">
	span{
		color: red;
		text-decoration: underline;
	}
</style>
</head>
<br>
<body>
	
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退房告知单</h2>
	<p> 
	 尊敬的租户：<span>${profile.name}</span>
	</p>
	<p>
	您的住址为 ${prHouse.estate.name }小区 ${prHouse.estate.name }</span> 小区
	<span>${prHouse.buildingNo.buildingId }</span> 楼
	<span>${prHouse.buildingNo.name}</span>单元
	<span>${prHouse.roomNo}</span> 号。
	退房申请经租管中心审核完毕。按租赁合同约定，租期
	 自<span>${bdate?string("yyyy-MM-dd") }  </span>至 <span>${cotime?string("yyyy-MM-dd")} </span>为止，请您在接到本通知单时确保已经腾空该房屋（或已经打包整理好需要的物品），
	持本通知单至物业服务中心办理退房手续。
	</p>
	 
	 <p>退房流程如下：</p>
	<p>一、至物业前台交还钥匙，做退房登记。</p>
	<p>二、楼宇管家查验房屋内设施、电器和物品。</p>
	<p>三、至经租中心办理退房手续。</p>
	<br>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span> ${prHouse.estate.name }</span>小区</p>  
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<span>${.now?string("yyyy-MM-dd")}</span></p>
										     
</body>
</html>