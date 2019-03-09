<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	
	<script type="text/javascript">
	
	$(function(){
		/* 操作后的提示语 */
		if (<%=request.getAttribute("tip")!=null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}	
	});
	
	function toEdit(){
		 var estateId= $(".radioId:checked").val();
		 location.href="toEditEstate.do?id="+estateId;
		 
	}
	function del(){
		 var estateId= $(".radioId:checked").val();
		 if (confirm("确认删除吗？")) {
			 location.href="delEstate.do?id="+estateId;
		}
	}
		</script>
  </head>
 <object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>

  <body>
  
  <table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
<tr >
 	 <th>选择</th>
   		   <th>物业名称</th>
   		   <th>类别</th>
   		   <th>物业电话</th>
   		   <th>物业地址</th>
			<th>授权码</th>
   		   <th>备注</th>
   		   <th>操作</th>
   		   
   		</tr>
   		
   		<c:forEach items="${estates}" var="e" varStatus="status">
	         <tr style="text-align: center;">
	          <td>
	          <c:if test="${status.index==0 }">
	          	<input type="radio" name="idStr" class="radioId" value="${e.id}" checked="checked"/>
	          </c:if>
	          <c:if test="${status.index!=0 }">
	          	<input type="radio" name="idStr" class="radioId" value="${e.id}" />
	          </c:if>
	          </td>
	          <td class="estateName">${e.name }</td>
	          <td>${e.estateType.name }</td>
	          <td>${e.phone }</td>
	          <td>${e.address }</td>
	           <td class="authorCode">${e.authorCode }</td>
	          <td>${e.remark }</td>
	          <td>
	       	       
	       	 </td>
	       </tr>
       </c:forEach>
     
   </table>
  <br/>
  <input class="btn" value="新  增"  type="button" onclick="location.href='toAddEstate.do';"/>&nbsp;&nbsp;
  
  <input class="btn" value="修  改"  type="button" onclick="toEdit()"/>&nbsp;&nbsp; 
  
  <input class="btn" value="删   除"  type="button" onclick="del()"/>&nbsp;&nbsp;
  
<%--  <input class="btn" value="写授权卡"  type="button" onclick="WriteAuthorizeCard()"/>&nbsp;&nbsp;
  
  <input class="btn" value="写时钟卡"  type="button" onclick="WriteSetClockCard()"/>&nbsp;&nbsp;
  
  <input class="btn"  value="写总卡" type="button" onClick="WriteTotalControlCard()">--%>
  
  </body>
  
  <!--  -->
<script type="text/javascript">

function WriteTotalControlCard(){
	var radio= $(".radioId:checked");
	   if (radio.val()==null) {
		return;
		}
	 layer.open({
		    type: 2,
		    title: '写总卡',
		    maxmin: false,
		    area: ['534px', '576px'],
		    content:'toWriteTotalCard.do',
		    end: function(){
		    	location.reload();
		    }
		  });
	
}

  function WriteAuthorizeCard(){
	  var estateId= $(".radioId:checked").val();
	   if (estateId==null) {
		return;
	  }
	  $.ajax({
			url : "getAuthorSeq.do?estateId="+estateId+"&name="+new Date(),
			success : function(result) {
				str1 = JSobj.WriteAuthorizeCard(result[0],result[1]);
				var info = eval('(' + str1+ ')');
				var result = info.Result;
				if (result != 1) {
					$.jBox.tip("授权卡写入失败！");
				}else{
					$.jBox.tip("授权卡写入成功！");
				}
			}
	  });
  }
 
  

	function WriteSetClockCard() {
		
		var nowDate= timeFormart();
		var estateId = $(".radioId:checked").val();
		var estateId= $(".radioId:checked").val();
		   if (estateId==null) {
			return;
		  }
		$.ajax({
			url : "getAuthorSeq.do?estateId=" + estateId + "&name="
					+ new Date(),
			success : function(result) {
				str1 = JSobj.WriteSetClockCard(result[0],result[1],nowDate);
				var info = eval('(' + str1 + ')');
				var result = info.Result;
				if (result != 1) {
					$.jBox.tip("时钟卡写入失败！");
				} else {
					$.jBox.tip("时钟卡写入成功！");
				}
			}
		});
	}
	
	
	
	
</script>







<script type="text/javascript">
/*    function WriteAuthorizeCard()
  {
  // SerialNumber:流水号
  // Authorizedata: 授权信息
  //
  	str1 = JSobj.WriteAuthorizeCard('1222','9D1BCA695A4B895F8060C497');	
  	alert(str1);
  }
 */
  </script>

<script type="text/javascript">
function timeFormart() {
	var d = new Date();
	var year = d.getFullYear() + "";
	var month = d.getMonth() + 1;
	var date = d.getDate();
	var day = d.getDay();
	var Hours = d.getHours(); //获取当前小时数(0-23)
	var Minutes = d.getMinutes(); //获取当前分钟数(0-59)
	var Seconds = d.getSeconds(); //获取当前秒数(0-59)
	var curDateTime = year+"-";
	
	if (month > 9) {
		curDateTime = curDateTime + month+"-";

	} else {
		curDateTime = curDateTime + "0" + month+"-";
	}
	if (date > 9)
		curDateTime = curDateTime + date+" ";
	else
		curDateTime = curDateTime + "0" + date+" ";
	if (Hours > 9)
		curDateTime = curDateTime + Hours+":";
	else
		curDateTime = curDateTime + "0" + Hours+":";
	if (Minutes > 9)
		curDateTime = curDateTime + Minutes+":";
	else
		curDateTime = curDateTime + "0" + Minutes+":";
	if (Seconds > 9)
		curDateTime = curDateTime + Seconds;
	else
		curDateTime = curDateTime + "0" + Seconds;
	//document.getElementByIdx_x("NumberNo").value=curDateTime;
	return curDateTime;
}



	//生成当前时间数字
	function curDateTime() {
		var d = new Date();
		var year = d.getFullYear() + "";
		var month = d.getMonth() + 1;
		var date = d.getDate();
		var day = d.getDay();
		var Hours = d.getHours(); //获取当前小时数(0-23)
		var Minutes = d.getMinutes(); //获取当前分钟数(0-59)
		var Seconds = d.getSeconds(); //获取当前秒数(0-59)
		var curDateTime = year;
		if (month > 9) {
			curDateTime = curDateTime + month;

		} else {
			curDateTime = curDateTime + "0" + month;
		}
		if (date > 9)
			curDateTime = curDateTime + date;
		else
			curDateTime = curDateTime + "0" + date;
		if (Hours > 9)
			curDateTime = curDateTime + Hours;
		else
			curDateTime = curDateTime + "0" + Hours;
		if (Minutes > 9)
			curDateTime = curDateTime + Minutes;
		else
			curDateTime = curDateTime + "0" + Minutes;
		if (Seconds > 9)
			curDateTime = curDateTime + Seconds;
		else
			curDateTime = curDateTime + "0" + Seconds;
		//document.getElementByIdx_x("NumberNo").value=curDateTime;
		return curDateTime;
	}
	//生成指定位数随机数
	function RndNum(n){
			var rnd="";
		 for(var i=0;i<n;i++){
		 	rnd+=Math.floor(Math.random()*10);
		 }
		
		 return rnd;
	}
	
	 function getTimeAndRandom(){
		 	alert(curDateTime()+RndNum(8));
		  return curDateTime()+RndNum(8);
	 }
</script>
</html>
