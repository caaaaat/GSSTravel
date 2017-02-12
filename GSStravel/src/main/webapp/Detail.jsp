<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>報名明細</title>
</head>

<style>

</style>
<body>
<%@include file="SelectBar.jsp" %>
<h2>－報名明細－</h2>
<form action=<c:url value="/detail"/> method="post">
<p >活動代碼：${param.tra_no}</p>
<input type="hidden" name="tra_no" id="tra_no" value="${param.tra_no}">
	<table border="1" id="deailtable" width="140%">
		<tr>
			<th> </th>
			<th>員工編號</th>
			<th>身份</th>
			<th>姓名</th>
			<th>性別</th>
			<th>身份證字號</th>
			<th>生日</th>
			<th>用餐/車位</th>
			<th>特殊身份</th>
			<th>保險受益人</th>
			<th>與受益人關係</th>
			<th>緊急聯絡人</th>
			<th>緊急聯絡人電話</th>
			<th>報名時間</th>
			<th>取消日期</th>
			<th>備註</th>
			<th>取消原因</th>
		</tr>
		
		<c:if test="${not empty select}">
		<c:forEach var="row" items="${select}">
		<tr>
		    <td><c:if test="${empty row.det_CanDate}">
		    <button name="cancel" id="cancel" type="button" value="${row.det_No}" onclick="open_Can(this)">取消</button>
		    <button name="edit" class="detEdit" id="detEdit" type="button" value="${row.det_No}" >編輯</button>
		    <button name="prodaction" class="save" type="submit" value="save" hidden>儲存</button>
		    </c:if>
		    </td>
		    <td>${row.emp_No}</td>
		     <td><input type="text" name="trel" value="${row.rel}" style="display:none">
		     <c:if test="${row.rel == '員工'}">
		     ${row.rel}
		      <select style="display:none" class="fam_Rel" name="fam_Rel" disabled>
					<option>員工</option>
			 		<option>眷屬</option>
			        <option>親友</option>
			     </select>
		     </c:if>
		     <c:if test="${row.rel != '員工'}">
		     <select name="fam_Rel" disabled>
					<option>請選擇</option>
			 		<option>眷屬</option>
			        <option>親友</option>
			     </select>
			 </c:if></td>
		     <td><input type="text" class="name" name="name" value="${row.name}" disabled></td>
		     <td><input type="text" name="tsex" value="${row.sex}" style="display:none">
		     <select name="sex" disabled>
					<option>請選擇</option>
			 		<option>男</option>
			        <option>女</option>
			     </select></td>
		     <td><input type="text" name="ID" value="${row.ID}" disabled></td>
		     <td><input type="date" name="Bdate" value="${row.bdate}" disabled></td>
		     <td><input type="text" name="teat" value="${row.eat}" style="display:none">
		     <select name="eat" disabled>
					<option>葷</option>
			 		<option>素</option>
			        <option>不佔餐</option>
			     </select>
			     <c:if test="${row.rel != '員工'}">
				     <c:if test="${row.car == false}">
				        <input type="checkbox" name="car" Checked disabled>不佔車位
				        </c:if>
				          <c:if test="${row.car}">
				        <input type="checkbox" name="car" disabled>不佔車位
				        </c:if>
			     </c:if></td>
		     <td>
		     <c:if test="${row.rel != '員工'}">
		     <c:if test="${row.fam_Bady == true}">
		     	<p>幼童(0~3歲) </p>
		     </c:if>
		     <c:if test="${row.fam_kid == true}">
		     	<p>兒童(4~11歲) </p>
		     </c:if>
		      <c:if test="${row.fam_Dis == true}">
		     	<p>持身心障礙手冊 </p>
		     </c:if>
		      <c:if test="${row.fam_Mom == true}">
		     	<p>孕婦(媽媽手冊) </p>
		     </c:if>
		     <select class="multiselect" name ="spe" id="multiselect" multiple="multiple" data-placeholder="請選擇" style="width: 210px;">
		     <c:if test="${row.fam_Bady == false}">
		     <option>幼童(0~3歲)</option>
			 </c:if>
			 <c:if test="${row.fam_Bady}">
			 <option Selected>幼童(0~3歲)</option>
			 </c:if>
			 <c:if test="${row.fam_kid == false}">
		     <option>兒童(4~11歲)</option>
			 </c:if>
			 <c:if test="${row.fam_kid}">
			 <option Selected>兒童(4~11歲)</option>
			 </c:if>
		      <c:if test="${row.fam_Dis == false}">
		     <option>持身心障礙手冊</option>
		     </c:if>
		      <c:if test="${row.fam_Dis}">
		     <option Selected>持身心障礙手冊</option>
		     </c:if>
		     <c:if test="${row.fam_Mom == false}">
		     <option>孕婦(媽媽手冊)</option>
		      </c:if>
		      <c:if test="${row.fam_Mom}">
		     <option Selected>孕婦(媽媽手冊)</option>
		      </c:if>
		     </select>
		     </c:if>
		     </td>
		     <td><input type="text" name="ben" value="${row.ben}" disabled></td>
		     <td><input type="text" name="ben_Rel" value="${row.benRel}" disabled></td>
		     <td><input type="text" name="emg" value="${row.emg}" disabled></td>
		     <td><input type="text" name="emg_Phone" id="emg_Phone" value="${row.emgPhone}" disabled></td>
		     <td>${row.det_Date}</td>
		     <td>${row.det_CanDate}</td>
		     <td><input type="text" name="note" value="${row.note}" disabled></td>
		     <td>${row.det_canNote}</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<br />
		<button type="submit" name="prodaction" value="insert">新增</button>
		<input type="button" value="匯出Excel">
	</form>


    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.mobile.min.css" />
    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>
	
<script type="text/javascript">

$(function(){ 	
	 //多選下拉式選單
	 
	 $(".multiselect").hide();
    //性別選取
	 for(var i=0; i<document.getElementsByName("tsex").length; i++){
		if(document.getElementsByName("tsex")[i].value =="男"){
			document.getElementsByName("sex")[i].selectedIndex = 1; 
		}else{
			document.getElementsByName("sex")[i].selectedIndex = 2; 
		}
	 }
	 
	//身分選取
	 for(var i=0; i<document.getElementsByName("trel").length; i++){
			if(document.getElementsByName("trel")[i].value =="眷屬"){
				document.getElementsByName("fam_Rel")[i].selectedIndex = 1; 
			}else if(document.getElementsByName("trel")[i].value =="親友"){
				document.getElementsByName("fam_Rel")[i].selectedIndex = 2;
			}
			else{
				document.getElementsByName("fam_Rel")[i].selectedIndex = 0;
			}
		 }
	 
	//用餐選取
	 for(var i=0; i<document.getElementsByName("teat").length; i++){
			if(document.getElementsByName("teat")[i].value == "葷"){
				document.getElementsByName("eat")[i].selectedIndex = 0; 
			}else if(document.getElementsByName("teat")[i].value =="素"){
				document.getElementsByName("eat")[i].selectedIndex = 1; 
			}else{
				document.getElementsByName("eat")[i].selectedIndex = 2;
			}
		 }
	
	 $(".detEdit").click(function () {
		 $(this).parents("tr").find("input").removeAttr("disabled");
		 $(this).parents("tr").find("select").removeAttr("disabled");
		 $(this).parents("tr").find("radio").removeAttr("disabled");
		 $(this).parents("tr").find(".save").show();
		 $(this).parents("tr").find(".multiselect").show();
		 $(this).parents("tr").find(".multiselect").kendoMultiSelect({autoClose: false});
		 $(this).parents("tr").find("p").hide();
		 $(this).hide();
		 $(".detEdit").prop("disabled",true);
     });
	 
});


function open_Can(obj) {
    var CanUrl = '/GSStravel/Detail_Cancel.jsp?can_detNo=' + obj.value + "&can_traNo=" + document.getElementById("tra_no").value;
    window.open(CanUrl, 'Detail_Cancel', 'width=300,height=250,top=100,left=400');
};
</script>
</body>
</html>

