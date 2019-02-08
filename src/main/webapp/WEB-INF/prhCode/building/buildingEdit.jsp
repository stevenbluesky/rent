<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>

<title>修改楼号信息</title>
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
		$("#myForm").submit(function(){
		
			 var beginNo=$(".beginNo").val();
			 var endNo=$(".endNo").val();
			 var buildNo=$(".buildNo:checked");
			 var count=0;
			 buildNo.each(function(e,q){
					var flag= $(q).is(":checked");
					if(flag){
						count++;
					}	
				});
			 
			
			/* if (buildNo.length==0) {
				$.jBox.tip("请选择单元！");
				return false;
			} */
			return true;
		});
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
			alert("df");
			chk(); //全选全不选
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
		var begin =0;
		var end = 100000000;
		var buildingId= $(".name").val();
		
		if (true) {
			
		$(".tip1").html("").css("color","green");
			var url = "getBuildNoByRange.do";
			var data = "estateId=" + estateId + "&begin=" + begin + "&end="
					+ end+"&buildingId="+buildingId +"&date="+new Date().getTime() ;
			
					$.ajax({
						url : url,
						data : data,
						scriptCharset : 'utf-8',
						success : function(nos) {
							
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
								    
								    if (no.remark=="#!%2") {
								    	str += "<td> <input type='checkbox' checked='checked' name='buildNo' class='buildNo buildingNoChecked' value='"+id+"' id='buildNo"+id+"'><label for='buildNo"+id+" '>"
										+ id + "单元	</label> </td>";
										
										var val= $(".checkedNos").val();
										if (val.length!=0) {
											$(".checkedNos").val(val+"-"+id);	
										}else{
											$(".checkedNos").val(val+id);	
										}
										
										
										
									}else{
										str += "<td> <input type='checkbox' name='buildNo' class='buildNo' value='"+id+"' id='buildNo"+id+"'><label for='buildNo"+id+" '>"
										+ id + "单元	</label> </td>";
									}
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
	    
	<form id="myForm" action="buildingEdit.do" method="post">
	
		<input type="hidden" name="estateId" class="estateId" value="${estateId }">
		<input type="hidden" name="id" class="buildingId" value="${building.id }">
		<input type="hidden" name="checkedNos" class="checkedNos">
			<table id="contentTable" class="table table-striped table-bordered table-condensed addFloor"  border="1" bordercolor="#a0c6e5" >
			<tr style="background-color: #008080;color: black;font-weight: bold;">
			
				<td colspan="2">修改楼号信息</td>
			</tr>
			
		
			<tr>
				<td class="title">楼号名称:</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input readonly="readonly"  type="text"  value="${name}" style="width: 50px;" >&nbsp;&nbsp;号
					<input type="hidden" class="name" name="name" value="${name}">
				
				</td></tr>
				
			<!-- 
			
			<tr>
				<td class="title" style="vertical-align: top;">楼号:</td>
				<td>
					<table class="chkNos">
						<tr>
							
						</tr>
					</table>
				</td>
			</tr> -->
			
			
			<tr style="text-align: center;">
				<td colspan="2" style="text-align: center;"><input type="submit" value="修  改" class="btn"
					style="width: 110px;height: 40px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="btn"
					style="width: 110px;height: 40px;" value="返     回"
					onclick="location.href='findBuildingByEstatePaged.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
