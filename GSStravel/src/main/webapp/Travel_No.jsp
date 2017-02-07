<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<style type="text/css">
.t{
	border:1px solid black;
}
.a{
	text-decoration:none; 
	color:black;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h1>行程內容</h1>
	<button><a href="<c:url value="/FeeTravel?tra_No=${traveResult.tra_NO}"/>" class="a">行程</a></button><button><a href="<c:url value="/FineShowOneServlet?tra_No=${traveResult.tra_NO}"/>" class="a">罰則</a></button>
														                                                                                                                           
	<table class="t" >	
		<tr>
			<td>活動代碼</td>
			<td>${traveResult.tra_NO}</td>
		</tr>
		<tr>
			<td>活動名稱</td>
			<td>${traveResult.tra_Name}</td>
		</tr>
		<tr>
			<td>活動日期</td>
			<td>${traveResult.tra_On}~${traveResult.tra_Off}</td>
		</tr>
		<tr>
			<td>登記時間</td>
			<td>${traveResult.tra_Beg}~${traveResult.tra_End}</td>
		</tr>
		<tr>
			<td>每人報名上限</td>
			<td>${traveResult.tra_Total}人</td>
		</tr>
		<tr>
			<td>本團人數上限</td>
			<td>${traveResult.tra_Max}人</td>
		</tr>
		<tr>
			<td>是否住宿</td>
			<td><c:if test="${traveResult.tra_On==traveResult.tra_Off}">否</c:if><c:if test="${traveResult.tra_On!=traveResult.tra_Off}">是</c:if></td>
		</tr>
		<tr>
			<td >活動說明</td>
			<td>${traveResult.tra_Intr}</td>
		</tr>
		<tr>
			<td>活動內容</td>
			<td style="word-break: break-all;width:500px">${traveResult.tra_Con}</td>
		</tr>
		<tr>
			<td>注意事項</td>
			<td>${traveResult.tra_Atter}</td>
		</tr>
		<tr>
			<td>附件</td>
			<td>${traveResult.tra_File}</td>
		</tr>
		<tr>
			<td>費用</td>
			<td class="t">項目</td>
			<td class="t">金額</td>
		</tr>
		<c:forEach var="row" items="${itemResult}">
		<tr>
			<td></td>
			<td class="t">${row.item_Name}</td>
			<td class="t">${row.item_Money}</td>
		</tr>
		</c:forEach>
	</table>
	<button><a href="<c:url value="/AllTravel"></c:url>" class="a">回到報名/查詢</a></button><button><a href="<c:url value="/Login_Information?tra_No=${traveResult.tra_NO}&emp_No=${emp_No}" ></c:url>" class="a">報名</a></button>

	
</body>
</html>