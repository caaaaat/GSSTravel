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
      		$("#fineTable").append("<tr><td><input type='button' class='remove' value='－' /></td><td><input type='text' id='day' name='day' value='${row.fine_Dates}' autocomplete='off' required='required' /></td><td><input type='text' id='percent' name='percent' value='${row.fine_Per}' autocomplete='off' required='required' /></td></tr>");
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
<script>
	window.onload = function() {
		document.getElementById("day").onblur = chkDay;
		document.getElementById("percent").onblur = chkPercent;
	}

	function chkDay() {
		var day = document.getElementById("day").value;
		if (day == "") {
			alert("請輸入取消日！");
		} else if (day < 1) {
			alert("取消日必須為正整數！");
		} else {
			alert("取消日必須為正整數！");
		}
	}

	function chkPercent() {
		var percent = document.getElementById("percent").value;
		if (percent == "") {
			alert("請輸入取消日！");
		} else if (percent < 1 || percent > 100) {
			alert("扣款比例必須為小於100的正數！");
		} else {
			alert("扣款比例必須為小於100的正數！");
		}
	}
</script>
</head>

<body>
	<div id="bar"></div>
	<h2>罰則設定</h2>
	<form action="<c:url value="/FineServlet" />" method="GET">
		<input type="submit" name="FineSetting" value="設定罰則" />
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
					<td><input type="text" id="day" name="day" value="${row.fine_Dates}" autocomplete="off" required="required" /></td>
					<td><input type="text" id="percent" name="percent" value="${row.fine_Per}" autocomplete="off" required="required" /></td>
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