<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>旅遊報名</title>
</head>
<style>
*{
	font-size: 13px;
	
}
tr, input {
	text-align: center;
}

textarea {
	text-align: center;
	border:0;
 	resize:none;
	font-size: 20px;
	width:400px;
	overflow-y:visible;
	outline: none;
}

td {
	padding: 0px;
	text-align: center;
}
</style>

<body>
<!-- 	<script type="text/javascript" src='js/LogOut.js'></script> -->
<!--  	<input type="button" value='logout' onclick='logOut()'> -->
	<form action="<c:url value='/TotalAmountServlet' />" method="GET">
		<div><textarea name="tra_Name" readonly>${tra_Name}</textarea></div>
		<input type="hidden" name="tra_No" value="${tra_No}">
		<table border="1" id="table">
			<thead>
				<tr>
					<td>部門代碼</td>
					<td>員工(/眷屬親友)編號</td>
					<td>姓名(/隸屬於哪位員工)</td>
					<td>年度可補助金額<br /><span style="color:red; font-size:10px" >未滿一年以比例</span></td>
					<td>個人可補助金額</td>
					<td>個人團費</td>
					<td>其他增減費用明細說明</td>
					<td>其他增減費用總額</td>
					<td>應補團費</td>
				</tr>
			</thead>
			<c:forEach var="list" items="${list}">
				<tr>
					<td><input type="text" name="dept_No" value="${list.dept_No}" readonly></td>
					<td><c:if test="${list.fam_No==0}">
							<input type="text" name="emp_No" class="emp emp_fam" value="${list.emp_No}" readonly>
							<input type="hidden" name="empfam" value="${list.emp_No}">
						</c:if> 
						<c:if test="${list.fam_No!=0}">
							<input type="text" name="empfam" class="fam emp_fam" value="${list.emp_No}/${list.fam_No}" readonly>
<%-- 							<input type="hidden" name="empfam" value="${list.fam_No}"> --%>
						</c:if></td>
					<td><c:if test="${list.fam_Name==NULL}">
							<input type="text" value="${list.emp_Name}" readonly>
						</c:if> <c:if test="${list.fam_Name!=NULL}">
							<input type="text" value="${list.fam_Name} / ${list.emp_Name}" readonly>
						</c:if></td>
						
					<c:if test="${list.fam_Name==NULL}">
						<td><input type="text" class="years_money years" value=""></td>
						<td><input type="text" class="person_money" value=""></td>
						
					</c:if> 
					<c:if test="${list.fam_Name!=NULL}">
						<td><input type="text" class="years_money" value="0"></td>
						<td><input type="text" class="person_money" value="0"></td>					
					</c:if>	
					<td><input type="text" name="money" class="money" value="${list.det_money}"></td>
					<td><input type="text" name="det_note" class="det_note" value="${list.det_note}"></td>
					<td><input type="text" name="det_noteMoney" class="det_noteMoney" value="${list.det_noteMoney}"></td>
					<td><c:if test="${list.fam_No==0}">
						<input type="text" name="TA_money" class="TA_money" value="">
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<div>
			<input type="submit" id="submit" value="儲存" /> <input type="submit"
				id="excel" value="匯出Excel" />
		</div>
	</form>
</body>
<script src="js/jquery-1.12.3.min.js"></script>
<script>
 	var $money = $(".money");					//個人團費
 	var $emp = $(".emp");						//emp
 	var $fam = $(".fam");						//fam
 	var $TAmoney = $(".TA_money");				//應補團費
 	var $noteMoney= $(".det_noteMoney");		//減免費用
  	var $personmoney = $(".person_money");		//個人可補助金額
  	var $yearsmoney = $(".years_money");
 	var a=0;
 	var $empfam = $(".emp_fam");
 	$.each($empfam,function(val,empfam){
 		var overage;
 		if($yearsmoney[val].value >= $money[val].value){
 			overage=$money[val].value;
	 	}else{
	 		overage=$yearsmoney[val].value;
	 	}
 		$personmoney[val].value=overage;
 	});
	$.each($emp,function(keys,emp){
		var sum = Number($money[keys].value)-Number($noteMoney[a].value);
 		a=a+1;
		if(sum > 0){
	 		$.each($fam,function(key , fam){
	 			if ( $fam[key].value.substr(0, 3) == $emp[keys].value) {
	 				sum = sum + Number($money[key].value)-Number($noteMoney[a].value);
	 				a=a+1;
	 			}
	 		})
		}
		else{
			sum=0;
		}
 		$TAmoney[keys].value = sum;
	});
	var $years=$(".years");
	var a=0;
	$.each($years,function(key,value){
		var a=${years_money};
		$years[key].value=a[key];	
	})

</script>
</html>