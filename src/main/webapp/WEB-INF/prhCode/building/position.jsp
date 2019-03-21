<%@page import="java.util.List"%>
<%@page import="com.rent.entity.Building"%>
<%@ page  pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<title>JQuery 拖拽效果</title>

<style>
body {
	margin: 0px;
	padding: 0px;
	overflow: hidden;
}

.left {
	float: left;
}

.right {
	margin-left:30px;
	border: 5px solid #F00;
	width: 600px; height : 400px; border : 5px solid #F00;
	background-color : #eee; margin : 0px; padding : 0px; overflow : scroll;
	float: left;
	height: 400px;
	border: 5px solid #F00;
	background-color: #eee;
	margin: 0px;
	padding: 0px;
	overflow: scroll;
}
.xTxt{
	width: 50px;
}
.yTxt{
	width: 50px;
}
.rightTable{
	margin-left: 20px;
}

.rightTable tr td{
	padding: 10px 0px 10px;
}
.buildingMap{
	
}
.fileTd{
	position: absolute;
}
.allInput{border:none !important; border:1px solid #CCC !important;vertical-align:middle !important; }
.input  {height:24px !important; line-height:24px !important; border-right:none !important; width:200px !important;}


.files{	position:absolute !important; left:202px !important; top:41px !important; heigth:29px !important;cursor:pointer !important;
		 filter: Alpha(opacity=0);    
 			 -moz-opacity:0;    
 			 opacity:0;  
		}
.redTip{
	color: red;
	font-weight: bold;
	line-height: 30px;
} 
</style>
<script type="text/javascript">
	$(function(){
		if (<%=request.getAttribute("tip")!=null%>) {
			$.jBox.tip("<%=request.getAttribute("tip")%>");	
		}
	});


	function check(){
		var radio= $(".buildingRadio:checked");
		var xTxt=parseInt($(".xTxt").val());
		var yTxt=parseInt($(".yTxt").val());
		
		if (radio.length==0) {
			alert("请选择要定位的楼号！");
			return;
		}
		
		if (xTxt.length==0||yTxt.length==0) {
			alert("请输入要定位的坐标！");
			return;
		}
		if (isNaN(xTxt)||isNaN(yTxt)) {
			alert("坐标格式错误！");
			return;
		}
		if (xTxt.length>4||yTxt.length>4) {
			alert("坐标过大！");
			return;
		}

	}
	function confirm1(){
		check();  /* 非空  */	
		var radio= $(".buildingRadio:checked");
		var buildingNo=radio.next().text();
		var bId=radio.val();
		var xTxt=$(".xTxt").val();
		var yTxt=$(".yTxt").val();
		var str="#buildingMap"+bId;
		
		
		$(str).remove();
		
		var lastInfo= $("#consle").html();
		$("#consle").append("<span style='text-align:center; color:red;width:65px;height:30px; border:1px dashed red;' id='buildingMap"+bId+"' class='buildingMap'>"+buildingNo
			+"<input type='hidden' name='buildingIds' value='"+bId+"'>"
			+"<input type='hidden' name='positionXs' value='"+xTxt+"'>"
			+"<input type='hidden' name='positionYs' value='"+yTxt+"'>"
		+"</span>");
		 
		 var buildingMap = $(str);
		
		 var pLeftImg= $("#image").position().left;	
		 var pTopImg= $("#image").position().top;
			
		 buildingMap.get(0).style.position = "absolute";
		 buildingMap.get(0).style.left = (parseInt(xTxt)+pLeftImg-32);
		 buildingMap.get(0).style.top = (parseInt(yTxt)+pTopImg-7);
		 
		 /* 重置滚动时间 */
		 var pLeft= $("#consle").position().left;
		 var pTop= $("#consle").position().top;
		 
		 var differTop= pTop-(pTopImg-11);
		 var differLeft= pLeft-(pLeftImg-11);
		 
		var oldxs=new Array();
		var oldys=new Array();
		var maps= $(".buildingMap");
		
		maps.each(function(i,m){
			oldxs[i]=m.style.left;
			oldys[i]=m.style.top;
			
		});
		/*  接触绑定事件  */
		$('#consle').unbind("scroll");
		
		/* 滚动事件   */
		$("#consle").scroll(function(){
			var x=$(this).scrollLeft();
			var y=$(this).scrollTop();
			var maps= $(".buildingMap");
			maps.each(function(i,m){
			
				
			 var oldTop= parseInt(oldys[i].substring(0,oldys[i].length-2));
			 var oldLeft= parseInt(oldxs[i].substring(0,oldxs[i].length-2));
			 m.style.top=(-y+oldTop+differTop) ;
			 m.style.left=(-x+oldLeft+differLeft) ;
			
			 
			  var top2=m.style.top.substring(0,m.style.top.length-2);
			  var left2=m.style.left.substring(0,m.style.left.length-2);
			 
			  if (top2<=pTop||left2<=pLeft||top2>500||left2>500) {
				  $(m).hide();
				}else if(top2>pTop&&left2>pLeft){
				  $(m).show();
				}
		   });
		});	
		
	}
</script>
</head>

<body>
<form action="positionBuilding.do" method="post" enctype ="multipart/form-data">
	
<div class="row-fluid" style="margin: 0px;padding: 0px;">
			<div class="span4" style="width: 100%;">
				<ul class="nav nav-tabs">
					<c:forEach var="e" items="${estates}" varStatus="status">
						
							<c:if test="${estateId==e.id }">
								<li class="active"><a
									href="toPosition.do?estateId=${e.id}">${e.name }</a></li>
							</c:if>
							<c:if test="${estateId!=e.id  }">
								<li><a href="toPosition.do?estateId=${e.id}">${e.name}</a></li>
							</c:if>
					</c:forEach>
				</ul>
			</div>
<input type="hidden" name="estateId" value="${estateId }">
<div class="left">
	<div id="consle" style="width:500px;height:510px;border:5px solid #F00;background-color:#eee;margin:0px;padding:0px;overflow-x: scroll;">
		<c:if test="${estate.mapPic!=null}">
		
			<img id="image" src="${ctxStatic}/image/building/${estate.mapPic}?timestemp='+new Date().getTime()" style="max-width:2000px !important;" >
		</c:if>
		<c:if test="${estate.mapPic==null}">
			<img id="image"  style="max-width:2000px !important;" src="${ctxStatic}/image/building/white.png" width="485" height="485">
		</c:if>
	</div>
<div class="position"></div>
</div>
<div class="right" style="height: 510px;">
<h2>操作：</h2>
<table class="rightTable">
	<tr>
		<td>选择图片：</td>
		<td class="fileTd">
			<input type="text" id="txt" readonly="readonly" name="txt" class="input allInput"><input type="button" onmousemove="file.style.pixelLeft=event.x-60;file.style.pixelTop=this.offsetTop;" value="浏览 ..." size="30" onclick="file.click()" class="liulan allInput">
			<input type="file"  id="up" onchange="txt.value=this.value" name="file" style="height:26px;" class="files allInput"  size="1" hidefocus>		
		</td>
	</tr>
	<tr>
		<td>选择楼号：</td>
		<td>
			<table>
				<tr>
					<c:forEach var="b" items="${buildings}" varStatus="status">
						<td><input name="buildingId" value="${b.id }" type="radio" class="buildingRadio" id="building${b.id }"> <label class="buildingLabel" for="building${b.id }">${b.name} 号楼 &nbsp;&nbsp;&nbsp;</label> </td>
						<c:if test="${status.count%5==0 }">
							</tr><tr>
						</c:if>	
					</c:forEach>
					
				</tr>
			</table>
		
		</td>
	</tr>
	<tr>
		<td>选择坐标：</td>
		<td>x:<input name="x" class="xTxt" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		y:<input name="yTxt" class="yTxt" ></td>
	</tr>
	<tr>
		<td></td>
		<td  style="text-align: left;">						   
			<input type="button" class="btn" onclick="confirm1()" value="定位"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" class="btn" value="更新">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn" onclick="clear1()" value="清空定位信息">  
			
		</td>
	</tr>
	<tr>
		<td style="text-align: right;color: red;vertical-align: top;line-height: 20px;"><br/>提示：</td>
		<td style="padding-top: 10px;"><br/>
			1.<span class="redTip">选择图片</span>， 选择图片之后，即可进行定位操作。<br>
			2.<span class="redTip">选择楼号</span>，选择要定位的楼号。<br>
			3.<span class="redTip">选择坐标</span>，可以点击要图片上要定位的位置，快速选择坐标。<br>
			4.<span class="redTip">定位</span>，选择坐标和楼号之后，即可定位。<br>
			5.<span class="redTip">更新</span>，点击更新提交本次定位情况（以及图片）。<br>
			6.<span class="redTip">清空定位信息</span>，点击清空该小区的定位信息。
		</td>
	</tr>
</table>
<div class="test"></div>	
</div>

<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery.drag.js"></script>
<script type="text/javascript">
	function clear1(){
		$(".buildingMap").remove();
		var estateId="${estateId}";
		location.href="clearBuildingInDraw.do?estateId="+estateId;
	}
</script>
<script type="text/javascript">
$(function(){
	
	
	var count= "${fn:length(checkBuildings)}";
	if (count!=0) {
		var buildings="${checkBuildings}";
		<% 
			List<Building> buildings=(List<Building>)request.getAttribute("checkBuildings");
			
			for(Building b :buildings){
				System.out.println(b.getId());
		%>
		
			 var str="#buildingMap<%=b.getId()%>"; 
			 var bId="<%=b.getId()%>";
			 var bName="<%=b.getName()%>";
			 var x="<%=b.getPositionX() %>";
			 var y="<%=b.getPositionY() %>";
			 
			 
				var buildingMap = $(str);
			 
			 var pLeft= $("#image").position().left;	
			 var pTop= $("#image").position().top;
			
			 
			 var x="<%=b.getPositionX()%>";
			 var y="<%=b.getPositionY()%>"; 
			 /* buildingMap.get(0).style.left = (parseInt(x)+pLeft-16);
			 buildingMap.get(0).style.top = ( parseInt(y)+pTop-7); */ 
			 var xx=(parseInt(x)+pLeft-16);
			 var yy=( parseInt(y)+pTop-7);
			
					
			$("#consle").append("<span style='text-align: center; height:30px;color:red; width:65px;position:absolute;left:"+xx+"px;top:"+yy+"px; border:1px solid red;' id='buildingMap"+bId+"' class='buildingMap'>"+bName+"号楼"
					+"<input type='hidden' name='buildingIds' value='"+bId+"'>"
					+"<input type='hidden' name='positionXs' value='"+x+"'>"
					+"<input type='hidden' name='positionYs' value='"+y+"'>"
					+"</span>");
		<%	
			}
		%>
		
	}
});

$('#image').mousemove(function(e) { 
	var pLeft= $("#image").position().left;	
	var pTop= $("#image").position().top;
	
	var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
	var yy = e.originalEvent.y || e.originalEvent.layerY || 0;
	
	$(".position").html("x:<span class='xSpan'>"+parseInt(xx-pLeft) + "</span>   "+"y:<span class='ySpan'>" +parseInt(yy-pTop)+"</span>"); 
	}); 

	
	$('#image').mouseout(function(){
		$(".position").html("x:<span class='xSpan'>"+(0) + "</span>   "+"y:<span class='ySpan'>" +(0)+"</span>");			
	});
	
$('#image').click(function(){
	var x= $(".xSpan").text();
	var y=$(".ySpan").text();
	$(".xTxt").val(x);
	$(".yTxt").val(y);
	
});
$(function(){
	
	 var pLeft= $("#image").position().left;
	 var pTop= $("#image").position().top;	
	 
	var oldxs=new Array();
	var oldys=new Array();
	var maps= $(".buildingMap");
	
	maps.each(function(i,m){
		oldxs[i]=m.style.left;
		oldys[i]=m.style.top;
		var top2=m.style.top.substring(0,m.style.top.length-2);
		var left2=m.style.left.substring(0,m.style.left.length-2);
		if (top2<=pTop||left2<=pLeft||top2>500||left2>500) {
		  $(m).hide();
		}else if(top2>pTop&&left2>pLeft){
		  $(m).show();
		}
		
	});
	
	$("#consle").scroll(function(){
		
		var x=$(this).scrollLeft();
		var y=$(this).scrollTop();
		var maps= $(".buildingMap");
		maps.each(function(i,m){
		
			
		 var oldTop= parseInt(oldys[i].substring(0,oldys[i].length-2));
		 var oldLeft= parseInt(oldxs[i].substring(0,oldxs[i].length-2));
		 m.style.top=(-y+oldTop) ;
		 m.style.left=(-x+oldLeft) ;
		
		 
		  var top2=m.style.top.substring(0,m.style.top.length-2);
		  var left2=m.style.left.substring(0,m.style.left.length-2);
		 
		  if (top2<=pTop||left2<=pLeft||top2>500||left2>500) {
			  $(m).hide();
			}else if(top2>pTop&&left2>pLeft){
			  $(m).show();
			}
		  
		
	
	   });
	});	
});

</script>
<script type="text/javascript">
jQuery.fn.extend({
    uploadPreview: function (opts) {
    	
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "image",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
        	
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if ($.browser.msie) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.Callback();
            }
        })
    }
});




$("#up").uploadPreview({ Img: "image"});
</script>

</form>
</body>
</html>
