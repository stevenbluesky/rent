<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
 	<h2>公租房代码</h2>
   <a href="findAllEstate.do">物业</a>&nbsp;&nbsp;
   <a href="findBuildingNoByEstatePaged.do">楼号</a>&nbsp;&nbsp;
   <a href="findBuildingByEstatePaged.do">楼幢</a>&nbsp;&nbsp;
   <a href="findAllBuildingFloorPaged.do">楼层</a>    &nbsp;&nbsp;
   <br/><br/><br/>
   <a href="findAllRoomTypePaged">房间类型</a>&nbsp;&nbsp;
   <a href="findAllRoomFeaturePaged.do">房间特征</a>&nbsp;&nbsp;
   
   <br/><br/><br/>
   <a href="findAllRepairReasonPaged.do">维修原因</a>&nbsp;&nbsp;
   <a href="findAllEquipmentPaged.do">配套设施类型</a>&nbsp;&nbsp;
   <a href="findAllElectricPaged.do">电器设置</a>&nbsp;&nbsp;
    <br/><br/><br/>
   <a href="findAllCostCodePaged.do">费用代码</a>&nbsp;&nbsp;
   <a href="findAllPaymentCodePaged.do">付款代码</a>&nbsp;&nbsp;
   <a href="findAllRentPayWayPaged.do">租金支付方式</a>&nbsp;&nbsp;
   <br/><br/><br/>
   <a href="findAllSubsidyTypePaged.do">补贴人类型</a>&nbsp;&nbsp;
   <a href="findSubsidyByEstatePaged.do">补贴</a>&nbsp;&nbsp;  
   
   <h2>房源管理</h2>
   <a href="findPrHouseByEstatePaged.do">房源管理</a>&nbsp;&nbsp;	
   <h3>df</h3>
  </body>
</html>
