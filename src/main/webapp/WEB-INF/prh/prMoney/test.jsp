
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery表格可编辑修改表格里面的数值</title>
<meta name="description" content="jquery表格特效制作jquery表格可编辑任意修改里面的数值" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function(){
  $('table td').click(function(){
    if(!$(this).is('.input')){
      $(this).addClass('input').html('<input type="text" value="'+ $(this).text() +'" />').find('input').focus().blur(function(){
        $(this).parent().removeClass('input').html($(this).val() || 0);
      });
    }
  }).hover(function(){
    $(this).addClass('hover');
  },function(){
    $(this).removeClass('hover');
  });
});
</script>
<style type="text/css">
/* page styles */
body{font-family:"Segoe UI", Frutiger,Tahoma,Helvetica,"Helvetica Neue", Arial, sans-serif;font-size:62.5%;}
table{border-collapse:collapse;}
td, th{text-align:center;border:1px solid #ddd;padding:2px 5px;}
caption{margin:0 0 .5em;font-weight:bold;}
/*demo styles*/
table{width:500px;height:200px;margin-left:30px;}
td, th{font-size:1.2em;padding:2px;width:13%;}
th{background-color:#f4f4f4;}
caption{font-size:1.5em;}
table{float:left;margin:40px 40px 0 0;}
.demo{width:500px;margin:0 auto;}
/* input */
td input{border:1px solid orange;background:yellow;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;position:absolute;padding:8px 0;text-align:center;width:60px;margin:-17px 0 0 4px;font-size:1.4em;}
td.input{ padding:0;position:relative;}
td.hover{background:#eee;}
</style>
</head>
<body>
<div class="demo">
  <table>
    <caption>2009年员工产品销售走势图</caption>
    <thead>
      <tr>
        <td></td>
        <th scope="col">food</th>
        <th scope="col">auto</th>
        <th scope="col">household</th>
        <th scope="col">furniture</th>
        <th scope="col">kitchen</th>
        <th scope="col">bath</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th scope="row">Mary</th>
        <td>190</td>
        <td>160</td>
        <td>40</td>
        <td>120</td>
        <td>30</td>
        <td>70</td>
      </tr>
      <tr>
        <th scope="row">Tom</th>
        <td>3</td>
        <td>40</td>
        <td>30</td>
        <td>45</td>
        <td>35</td>
        <td>49</td>
      </tr>
      <tr>
        <th scope="row">Brad</th>
        <td>10</td>
        <td>180</td>
        <td>10</td>
        <td>85</td>
        <td>25</td>
        <td>79</td>
      </tr>
      <tr>
        <th scope="row">Kate</th>
        <td>40</td>
        <td>80</td>
        <td>90</td>
        <td>25</td>
        <td>15</td>
        <td>119</td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>