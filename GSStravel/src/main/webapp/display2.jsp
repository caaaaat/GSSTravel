<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="refresh" content="0;url=http://localhost:8080/GSStravel/AllTravel" />
<title>Insert title here</title>
</head>
<body>
	
</body>
<script type="text/javascript">
if(${bl1}){
	window.onload=drtail;
	function drtail(){
		alert("補助金轉移!!!!!"+"\n"+"從原本旅遊編碼:${decide[0]};旅遊名稱:${decide[1]}"+"\n"+"轉移至"+"\n"+"旅遊編碼:${decide[2]};旅遊名稱:${decide[3]}"); 
 	}
}
</script>
</html>