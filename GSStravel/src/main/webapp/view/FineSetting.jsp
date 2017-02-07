<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="" />
<title>罰則設定表</title>
<style>
tr, td {
	border: 1px solid black;
	text-align: center;
}
.error {
	color: red;
}
</style>
<script src="/GSStravel/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/LogOut.js"></script>
<script type="text/javascript" src="js/selectBar.js"></script>
<script>
	$(document).ready(function() {
		$("#add").click(function() {
      		$("#fineTable").append("<tr><td><input type='button' class='remove' value='－' /></td><td><input name='day' type='text' value='${row.fine_Dates}' autocomplete='off' /></td><td><input name='percent' type='text' value='${row.fine_Per}' autocomplete='off' /></td></tr>");
    	});
		
		$(document).on('click', '.remove', function(){
// 			var num = document.getElementById("fineTable").rows.length;
// 			if(num > 2){
				$(this).parents("tr").remove();
// 			}
// 			else{
// 				alert("The last row can't be deleted !!");
// 			}
		});
	});
</script>
</head>

<body>
	<div id='bar'></div><br>
	<form action="<c:url value="/FineServlet" />" method="GET">
		<input type="submit" name="FineSetting" value="罰則設定" />
		<input type="submit" name="FineShow" value="查看罰則" /><br>
		<em style="color: red">*</em>為必填欄位 
		<table id="fineTable">
			<tr>
				<td><input type="button" value="＋" id="add" /></td>
				<td><em style="color: red">*</em>取消日<br>(旅遊前 n 天通知)</td>
				<td><em style="color: red">*</em>罰款扣款比例 (%)</td>
			</tr>
			<c:forEach var="row" items="${select}">
				<tr>
					<td><input type="button" class="remove" value="－" /></td>
					<td><input name="day" type="text" value="${row.fine_Dates}" autocomplete="off" readonly /></td>
					<td><input name="percent" type="text" value="${row.fine_Per}" autocomplete="off" /></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" name="save" value="儲存" />
		<div class="error">${error.day}</div>
		<div class="error">${error.percent}</div>
		<div class="error">${error.pk}</div>
	</form>
</body>
</html>