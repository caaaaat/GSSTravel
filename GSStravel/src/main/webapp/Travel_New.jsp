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
	<form action="<c:url value='/Travel_Edit'/>" method="GET">		
	<table class="t" >
		
		<tr>
			<td>活動代碼</td>
				<td><input type="text" name="edittraNO" value="${params.tra_NO}" style="width:320px"></td>
			<td></td>
		</tr>
		<tr>
			<td>*活動名稱</td>

			<td><input type="text" name="edittraName" value="${params.tra_Name}" style="width:320px"></td>
			<td></td>
		</tr>
				<tr>
			<td>*活動地點</td>
 			<td><SELECT name="edittraLoc"> 
 			<OPTION selected>${params.tra_Loc}</OPTION> 
			<!-- <option selected="selected" value="null">請選擇地點</option> --> 
 			<OPTION  value="北">北</OPTION> 
			<OPTION  value="中">中</OPTION> 
			<OPTION  value="南">南</OPTION> 
			<OPTION  value="東">東</OPTION> 
			<OPTION  value="外島">外島</OPTION>
		</SELECT></td> 
<!-- 
<select class="" name ="edittraName"  m data-placeholder="請選擇" style="width: 200px;">
			 <c:if test="${start.fam_Bady=='false'} ${start.fam_kid=='false'} ${start.fam_Dis=='false'} ${start.fam_Mom=='false'}" >
			 <option value="no" Selected>請選擇</option>
			 </c:if>
			 
		     <c:if test="${start.fam_Bady=='false'}">
		     <option value="baby">幼童(0~3歲)</option>
			 </c:if>
			 <c:if test="${start.fam_Bady}">
			 <option value="baby" Selected>幼童(0~3歲)</option>
			 </c:if>
			 
			 <c:if test="${start.fam_kid=='false'}">
		     <option value="kid">兒童(4~11歲)</option>
			 </c:if>
			 <c:if test="${start.fam_kid}">
			 <option value="kid" Selected>兒童(4~11歲)</option>
			 </c:if>
		      <c:if test="${start.fam_Dis=='false'}">
		     <option value="dis">持身心障礙手冊</option>
		     </c:if>
		      <c:if test="${start.fam_Dis}">
		     <option value="dis" Selected>持身心障礙手冊</option>
		     </c:if>
		     <c:if test="${start.fam_Mom=='false'}">
		     <option value="mom">孕婦(媽媽手冊)</option>
		      </c:if>
		      <c:if test="${start.fam_Mom}">
		     <option value="mom" Selected>孕婦(媽媽手冊)</option>
		      </c:if>
		     </select>

	-->		
			
			<!--<td><input type="text" name="edittraLoc" value="${params.tra_Loc}"></td>-->
			<td></td>
		</tr>
		<tr>
			<td>*活動日期</td>
		
			<td><input type="text" name="edittraOn" value="${params.tra_On}"><a> ~</a>
			<input type="text" name="edittraOff" value="${params.tra_Off}"></td>
		</tr>
		<tr>
			<td>*登記時間</td>
		<td><input type="text" name="edittraBeg" value="${params.tra_Beg}"><a> ~</a>
		<input type="text" name="edittraEnd" value="${params.tra_End}"></td>
				
			<td></td>
		</tr>
		<tr>
			<td>*活動總人數上限</td>

				<td><input type="text" name="edittraTotal" value="${params.tra_Total}"></td>
			<td></td>
		</tr>
		<tr>
			<td>*本團人數上限</td>

				<td><input type="text" name="edittraMax" value="${params.tra_Max}"></td>
			<td></td>
		</tr>
		<tr>
			<td >*活動說明</td>
				<td><textarea cols="50" rows="10" name="edittraIntr">${params.tra_Intr}</textarea></td>
			<td></td>
		</tr>
		<tr>
			<td>*活動內容</td>
				<td><textarea cols="50" rows="10" name="edittraCon">${params.tra_Con}</textarea></td>
			<td></td>
		</tr>
		<tr>
			<td>*注意事項</td>

				<td><input type="text" name="edittraAtter" value="${params.tra_Atter}"></td>
			<td></td>
		</tr>
		<tr>
			<td>附件</td>

				<td><input type="text" name="edittraFile" value="${params.tra_File}"></td>
			<td></td>
		</tr>
		<tr>
			<td>費用</td>
			<td class="t">項目</td>
			<td class="t">金額</td>

		</tr>
		
		<c:forEach var="row" items="${paramsi}">
		<tr>
		
<!-- 			<td></td> -->
<%-- 			<td><input type="text"  value="${row.item_Name}"></td> --%>
<%-- 			<td><input type="text"  value="${row.item_Money}"></td> --%>
<!-- 			<!--<td><input type="text"  value="${row.tra_No}"></td> -->
<!-- 			<td><input type="text"  value="${row.item_No}"></td>--> 
<%-- 			<td class="t">tra_No : ${row.tra_No}</td> --%>
<%-- 			<td class="t">item_No : ${row.item_No}</td> --%>
			
		</tr>
		</c:forEach>
		 
		<tr>
		<td>
			<input type="submit" name="inputerrors" value="Insert">
			<input type="submit" name="" value="取消">
		</td>
		</tr>
	</table>
	</form>
	<!-- 	<button><a href="<c:url value="/AllTravel"></c:url>" class="a">回上一頁</a></button><button><a href="<c:url value="/Login_Information?tra_No=${traveResult.tra_NO}&emp_No=${emp_No}" ></c:url>" class="a">報名</a></button>
	 -->
	
	
</body>
</html>