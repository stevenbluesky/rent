<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<title>新增楼号信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.addFloor {
	border-collapse: collapse;
	width: 790px;
}

.addFloor tr {
	height: 40px;
}

.addFloor  td {
	padding: 0px 8px 0px;
}

.addFloor .title {
	text-align: right;
}

.addFloor input {
	width: 50px;
}
</style>


<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
		
		createChkBox();
		
		$(".beginNo").blur(function() {
			createChkBox();
		});
		$(".endNo").blur(function() {
			createChkBox();
		});

		$("#myForm").submit(function() {
			return true;
		});
		$(".buildNoAll").click(function(){
			chk(); //全选全不选
		});
		$("#myForm").submit(function(){
			 var name= $(".name").val();
			 var begin=$(".beginNo").val();
			 var end=$(".endNo").val();
			 var buildNo=$(".buildNo:checked");
			 var count=0;
			 buildNo.each(function(e,q){
					var flag= $(q).is(":checked");
					if(flag){
						count++;
					}	
				});
			 
			 
			if (name.length==0) {
				$.jBox.tip("请输入楼号名！");
				return false;
			}
			
			if (isNaN(name)) {
				$.jBox.tip("楼号名必须为数字！");
				return false;
			}
						
			/* if (count==0) {
				
				$.jBox.tip("请选择单元！");
				return false;
			} */
			
			return true;
		});
	});
	
	 function chk(){
		var chkAllFlag= $(".buildNoAll").attr("checked");
		
		var chks= $(".buildNo");
		if(chkAllFlag=="checked"){
			$(".buildNo").attr("checked",true);
		}else{
			$(".buildNo").attr("checked",false);
		}
	}
	/*生成checkBox  */
	function createChkBox() {
		var estateId = $(".estateId").val();
		var begin = 0;
		var end = 10000;
		
		if (true) {
			
		$(".tip1").html("").css("color","green");
			var url = "getBuildNoByRange.do";
			var data = "estateId=" + estateId + "&begin=" + begin + "&end="
					+ end +"&date="+new Date().getTime() ;
			
			 
			
					$.ajax({
						url : url,
						data : data,
						scriptCharset : 'utf-8',
						success : function(nos) {
							if ($(nos).length==0) {
								$(".chkNos").html("<span style='color:red;'>&nbsp;&nbsp;&nbsp;暂无可绑定单元</span>");
								return;
							}
							
							var content = "&nbsp;&nbsp;<input type='checkbox' name='buildNoAll' class='buildNoAll' id='buildNo0' onclick='chk()'><label for='buildNo0 '>全选</label>";
							$(nos).each(
								function(i, no) {
								
								   var id = no.name.substr(0,no.name.length - 2); 
									
								    var str="";
								    if(i==0){
								    	str+="<tr>";
								    }else if(i!=0&&(i)%5==0){
								    	str+="</tr><tr>";
								    }
									str += "<td> <input type='checkbox' name='buildNo' class='buildNo' value='"+id+"' id='buildNo"+id+"'><label for='buildNo"+id+" '>"
											+ id + "单元</label> </td>";
									content = content + str;
									$(".chkNos").html(content);

								});
						}
					});
		   }else{
		   		$(".chkNos").html("该区间没有楼号");
		   } 
	} 

	function checkBeginEnd() {
		var begin = $(".beginNo").val();
		var end = $(".endNo").val();
		if(begin.length==0||end.length==0){
		$(".tip1").html("请输入正确的区间").css("color","red");
			return false;
		}
		
		if (isNaN(begin)||isNaN(end)) {
			$(".tip1").html("请输入正确的区间").css("color","red");
			return false;
		}else{
				if(parseInt(begin) >parseInt(end)){
					$(".tip1").html("请输入正确的区间").css("color","red");
					return false;
				}
				
				return true;
		}
		return true;
	}
	
	
</script>


</head>

<body>
	<form id="myForm" action="buildingAdd.do" method="post">
		<input type="hidden" name="estateId" class="estateId"
			value="<%=request.getParameter("estateId")%>">


		<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
				<td colspan="2">新增楼号信息</td>
			</tr>
			
			
			<tr>
				<td class="title">楼号名称:</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" class="name" style="width: 50px;" >&nbsp;&nbsp;号</td></tr>

			

			

			<!-- <tr>
				<td class="title" style="vertical-align: middle;">绑定单元:</td>
				<td>
					<table class="chkNos" border="0"  >
											
					</table>
				</td>
			</tr> -->
			
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input class="btn" type="submit" value="新增"
					style="width: 110px;height: 40px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="btn"
					style="width: 110px;height: 40px;" value="返     回"
					onclick="location.href='findBuildingByEstatePaged.do'"></td>

			</tr>
		</table>
	</form>
	<script type="text/javascript">
	
	
	</script>
</body>
</html>
