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
	function moneyReport(){
		var id= $(".radioId:checked").val();
		location.href="moneyReport.do?id="+id+"&estateId=${estateId}";
	}
	
		</script>
  </head>
 <object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" ></object>

  <body>
  
  <div class="row-fluid">
	<div class="span4" style="width: 100%">
		<ul class="nav nav-tabs">
			<c:forEach var="e" items="${estates}" varStatus="status">
				<c:if test="${estateId==e.id }">
					<li class="active"><a
						href='moneyReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
				<c:if test="${estateId!=e.id  }">
					<li><a
						href='moneyReport.do?estateId=${e.id}&currpage=1'>${e.name }</a></li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
</div>
  
  
  <table border="1" bordercolor="#a0c6e5" id="contentTable" class="table table-striped table-bordered table-condensed" style="border-collapse:collapse;">	
<tr >
 	 <th>选择</th>
   		   <th>批次</th>
   		</tr>
   		
   		<c:forEach items="${batchs}" var="e" varStatus="status">
	         <tr style="text-align: center;">
	          <td>
	          <c:if test="${status.index==0 }">
	          	<input type="radio" name="idStr" class="radioId" value="${e }" checked="checked"/>
	          </c:if>
	          <c:if test="${status.index!=0 }">
	          	<input type="radio" name="idStr" class="radioId" value="${e}" />
	          </c:if>
	          </td>
	          <td class="estateName"> 
	          	${e }
	          </td>
	          
	       </tr>
       </c:forEach>
     
   </table>
  <br/>
  <input class="btn" value="导出财务报表"  type="button" onclick="moneyReport()"/>&nbsp;&nbsp;
  
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
