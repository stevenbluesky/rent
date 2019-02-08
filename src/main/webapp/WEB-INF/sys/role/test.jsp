<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<html	>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>测试</title>
<script type="text/javascript">
  function test(){
	  var content = "";
	  
	  var fso = new ActiveXObject("Scripting.FileSystemObject");  
      var reader = fso.openTextFile("c:\myjstest.txt", 1);
      alert("");
      while(!reader.AtEndofStream) {
          content += reader.readline();
          content += "\n";
      } 
      // close the reader
      reader.close();
      alert(content);
  }
</script>
</head>
<body>
	<input type="file"
		onchange="document.getElementById('img').src=getFullPath(this);" />
	<img id="img" />
	<input type="button" onclick="test()" value="测试">
</body>
</html>
