<%@page import="com.rent.common.utils.MyConvertUtil"%>
<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<object classid="clsid:54881A9B-E923-4159-B407-0A358830FABC" id="JSobj" width="1" height="1" >
</object>
<meta http-equiv="Content-Type" />
<title>单元管理</title>
<style type="text/css">
.active>a{
    border-top: 2px solid #cc0000 !important;
	color: #cc0000 !important;
	font-weight: bold;
}

a:link {
	color: #444547;
	text-decoration: none;
}

a:visited {
	color: #444547;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}
<!--
body, div, ul, li {
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: left;
}

a:link {
	color: #00F;
	text-decoration: none;
}

a:visited {
	color: #00F;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}

ul {
	list-style: none;
}

.main {
	clear: both;
	padding: 8px;
	text-align: left;
}
/*第一种形式*/
#tabs0 {
	height: 800px;
	width: 100%;
	border: 1px solid #red;
	background-color: #f2f6fb;
}

.menu0 {
	width: 400px;
}

.menu0 li {
	display: block;
	float: left;
	padding: 4px 0;
	width: 100px;
	text-align: center;
	cursor: pointer;
	background: orange;
}

.menu0 li.hover {
	background: #ff0;
}

#main0 .menu0 {
	display: none;
}

#main0 .menu0.block {
	display: block;
}
/*第二种形式*/
#tabs {
	text-align: left;
	width: 400px;
}

.menu1box {
	position: relative;
	overflow: hidden;
	height: 22px;
	width: 400px;
	text-align: left;
}

#menu1 {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1;
}

#menu1 li {
	float: left;
	display: block;
	cursor: pointer;
	width: 72px;
	text-align: center;
	line-height: 21px;
	height: 21px;
}

#menu1 li.hover {
	background: #fff;
	border-left: 1px solid #333;
	border-top: 1px solid #333;
	border-right: 1px solid #333;
}

.main1box {
	clear: both;
	margin-top: -1px;
	border: 1px solid #333;
	height: 181px;
	width: 400px;
}

#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}
-->
allInput{border:none !important; border:1px solid #CCC !important;vertical-align:middle !important; }
.input  {height:24px !important; line-height:24px !important; border-right:none !important; width:200px !important;}
.liulan {width:100px !important;height:26px !important;background:url(/jscss/demoimg/201206/bgimg.jpg) no-repeat !important; cursor:pointer !important;
	margin-bottom: 10px;
			}

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
.upload ul li{
	height: 50px;
}
body{
overflow: hidden;
}
.right1{
	float: left;
}
.left1{
  float:left;
}

</style>

<script type="text/javascript">
	$(function() {
		if (<%=request.getAttribute("tip") != null%>) {
			
			$.jBox.tip("<%=request.getAttribute("tip")%>");
		}
		$("#tabs0 li").click(
				function() {
					$(this).addClass("hover").siblings().removeClass("hover");
					var estateId = $(this).find("input").val();
					location.href = "findBuildingNoByEstatePaged.do?estateId="
							+ estateId + "&currpage=1";
				});
		$(".chkAll").click(function() {

			chk(); //全选全不选
		});

	});

	function chk() {
		var chkAllFlag = $(".chkAll").attr("checked");

		var chks = $(".chk");
		if (chkAllFlag == "checked") {
			$(".chk").attr("checked", true);
		} else {
			$(".chk").attr("checked", false);
		}
	}

	function toEdit() {
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环

		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择要修改的单元！");

			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个单元！");
			return;
		}

		location.href = "toEditBuildingNo.do?id=" + id;

	}
	
	function download() {
		var chk = $(".chk");
		var count = 0;
		var id = null;
		//循环

		chk.each(function(e, q) {
			var flag = $(q).is(":checked");
			if (flag) {
				id = $(q).val();
				count++;
			}
		});
		if (count == 0) {
			$.jBox.tip("请选择要下载的文件！");

			return;
		} else if (count > 1) {
			$.jBox.tip("请勿选择多个文件！");
			return;
		}

		location.href = "downloadAttachment.do?id=" + id;

	}
	
	function del() {
		
			var chk = $(".chk");
			var ids = new Array();
			var count = 0;
			//循环
			chk.each(function(e, q) {
				var flag = $(q).is(":checked");
				if (flag) {
					id = $(q).val();
					ids.push(id);
					count++;
				}
			});

			if (count == 0) {
				$.jBox.tip("请选择要删除的附件!");
				$("#showForm").submit(function() {
					return false;
				});
				return;
			}
			if (!confirm("确定要删除吗?")) {
				$("#showForm").submit(function() {
					return false;
				});	
			}
	
		
	}
	
	$(function(){
		$("#myForm").submit(function(){
			
			if ($("#up").val().length==0) {
				$.jBox.tip("请选择文件！");
				return false;
			}
		});
		
		
	});
</script>
<script type="text/javascript">
	function WriteGuestOpenCard() {

		str1 = JSobj.WriteGuestOpenCard('1105', '9D1BCA695A4B895F8060C497',
				'1', '2017-03-27 14:12:00', '2017-09-29 14:12:00', '8', '3',
				'1', '0');
		alert(str1);
	}
</script>

</head>
<body>


	<!--第一种形式-->
	<div class="left1" style="width: 590px;">
		<form action="delAttachment.do" id="showForm" method="post">
			<input type="hidden" value="${masterId}" name="masterId"/>
		  
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed" style="width: 590px !important;">
					<thead>
						<tr>
							<th style="width: 30px;">选择<input type="checkbox"  class="chkAll" style="position: relative;top: 2px;"/></th>
							<th>文件名</th>
							<th >附件类型</th>
							<th>上传时间</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="att" items="${attachments}">
						
						<tr>
							<td><input type="checkbox" name="chk" class="chk" value="${att.id}"/>  </td>
								
								<td>
								
								
								
								
								${att.name}</td>
							
							<td>
								 <c:set var="typeId" scope="request" value="${att.type}"></c:set>
								 
								 
								<%=MyConvertUtil.getAttachmentType(Integer.parseInt((request.getAttribute("typeId").toString())))%> 
							</td>
							
							<td>${att.uploadTime }</td>
							<td>${att.remark }</td>
							
						
						</tr>
					</c:forEach>
						
					</tbody>
				</table>
				&nbsp;&nbsp;&nbsp;&nbsp;
				
								 <c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="下载" type="button" onclick="download()" />&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==68}">
						
					<input class="btn" value="下载" type="button" onclick="download()" />&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
				
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" value="删除" type="submit" onclick="del()" />
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==69}">
						
					<input class="btn" value="删除" type="submit" onclick="del()" />
			 </c:if></c:forEach></c:if></c:forEach>
				
				
				
			</form>
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				
				
				
				
			
		</div>
		
		<div class="right1 upload" style="margin-left:24px; padding-left: 20px;width:360px;border-left: 1px solid #CCC;" >
		
		<form id="myForm" action="uploadAttachment.do"  method="post" enctype ="multipart/form-data">
		<h3>附件上传</h3>
		
		<br/>
		
		<input name="masterId" type="hidden" value="${masterId }"/>
		
		<ul >
			<li>文件类型：&nbsp;
			 <select name="type"> 
				<option value="1">合同</option>
				<option value="2">身份证</option>
				<option value="3">其他</option>
			</select> </li>
			<li>选择文件：&nbsp;
			<input type="file"  id="up" onchange="txt.value=this.value" name="file" style="height:26px;" class="allInput"  size="1" ></li>
			
			<li>&nbsp;&nbsp;备注：&nbsp;<input name="remark" /></li>
			<li style="padding-left: 30px;">&nbsp;&nbsp;&nbsp;
			
			
			
				<c:forEach var="role" items="${user.rolesList}"><c:if test="${role.id==1 }">

					<input class="btn" type="submit" value="上传"/>&nbsp;&nbsp;&nbsp;
						</c:if><c:if test="${role.id!=1 }"><c:forEach var="m" items="${role.moduleList }"><c:if test="${m.id==70}">
						
					<input class="btn" type="submit" value="上传"/>&nbsp;&nbsp;&nbsp;
			 </c:if></c:forEach></c:if></c:forEach>
				
			
			<input type="button" class="btn" value="返回"  onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"/>
			</li>
		</ul>	
		</form>
		</div>	
	
	
	<br />

<script type="text/javascript">
jQuery.fn.extend({
    uploadPreview: function (opts) {
    	
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "image",
            Width: 100,
            Height: 100,
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


</body>

</html>