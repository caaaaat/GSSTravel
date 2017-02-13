<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
	
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.mobile.min.css" />
	<script src="js/jquery-3.1.1.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>


<%@include file="SelectBar.jsp" %>
<form  action=<c:url value="/FamilyServlet"/>  method="post">

<table>
	
		<tr>  <td>員工編號</td>  <td>${empno}</td></tr>
		<tr>  <td>姓名</td>  <td>${empname}</td></tr>
		<tr>  <td>手機</td>  <td><input type="text" name ="empphone" id="empphone"  value="${empphone}"> <div id=empphoneerror>${error.empphone}</div> </td></tr>
		<tr>  <td>保險受益人</td> <td><input type="text" name ="empben" id="empben" value ="${empben}"><div id=empbenerror>${error.empben}</div> </td></tr>
		<tr>  <td>與受益人關係</td> <td><input type="text" name ="empbenrel" id="empbenrel" value = "${empbenrel}"><div id=empbenrelerror>${error.empbenrel}</div> </td></tr>
		<tr>  <td>緊急聯絡人</td> <td><input type="text" name ="empemg" id="empemg" value = "${empemg}" ><div id=empemgerror>${error.empemg}</div> </td></tr>
		<tr>  <td>緊急聯絡人電話</td> <td><input type="text" name ="empemgphone" id="empemgphone" value="${empemgphone}"><div id=empemgphoneerror>${error.empemgphone}</div> </td></tr>
		<tr>  <td>用餐</td>
			<td><select name ="empeat">
				<c:if test="${empeat=='葷'}">
					<option  value="葷" selected>葷</option>
					<option  value="素">素</option>
				</c:if>
				<c:if test="${empeat=='素'}">
					<option  value="葷" >葷</option>
					<option  value="素" selected>素</option>
				</c:if>
				</select>
				(如有特別要求請填寫於備註)
			</td>
		</tr>
		
		<tr>  <td>備註</td>  <td><input type="text" name ="empnote" id="empnote" value="${empnote}" ><div id=empnoteerror></div></td></tr>
	
</table>

<div>
－眷屬親友資訊－<br>
*眷屬包含直系及旁系二等親，納入補助計算<br>
*親友為旁系三等親以上及朋友，不納入補助計算<br>
</div>


<table id="familytable">
<tr >
	<th></th>
	<th>*眷屬/親友</th>
	<th>*姓名</th>
	<th>*性別</th>
	<th>*身份證字號</th>
	<th>*生日</th>
	<th>*手機</th>
	<th>用餐/車位</th>
	<th>特殊身份</th>
	<th>*保險受益人</th>
	<th>*保險受益人關係</th>
	<th>*緊急聯絡人</th>
	<th>*緊急聯絡人電話</th>
	<th>*緊急聯絡人關係</th>
	<th>備註</th>
</tr>
		
	<c:if test="${famstartsize>0}">

	<c:forEach var="start" items="${famstart}">
	  <tr>
		<td><input type="button" name ="delete" id="delete" value="刪除"></td>
		<td>
			<select name ="famrel" >	
				<c:if test="${start.fam_Rel=='親友'}">				
					<option value="眷屬" >眷屬</option>
					<option value="親友" selected>親友<option>
				</c:if>
				<c:if test="${start.fam_Rel=='眷屬'}">				
					<option value="眷屬" selected>眷屬</option>
					<option value="親友" >親友<option>
				</c:if>
			</select>
			</td>
		<td><input type="text" name ="famname" id="famname" value="${start.fam_Name}" ><div name="famnameerror" name="famnameerror">${error.famneme}</div></td>
		<td><select name ="famsex">  <!--  servlet抓name db抓值會抓進value值進去-->
		<c:if test="${start.fam_Sex=='男'}">
			<option value="女" >女</option>
			<option value="男" selected>男<option>
		</c:if>
		<c:if test="${start.fam_Sex=='女'}">
			<option value="女" selected>女</option>
			<option value="男" >男<option>
		</c:if>
		</select></td>
		
		<td><input type="text" name ="famid" id="famid" value="${start.fam_Id}"><div class="famiderror">${error.famid}</div></td><!-- getfamid()會抓到value值 -->
		<td><input type="date" id="fambdate" name="fambdate" class="fambdate" value="${start.fam_Bdate}" /><div class="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td>
		<td><input type="text" name ="famphone" id="famphone"  value="${start.fam_Phone}"><div class=famphoneerror>${error.famphone}</div></td>
		<td ><select name ="fameat" >  <!-- 今天的日期 減去 他的生日 < 三歲  (剩幾天?) (看年底還是年初)  看年?  -->
			<c:if test="${start.fam_Eat=='葷'}">
				<option value="葷" selected>葷食</option>
				<option value="素">素食</option>
			</c:if>
			<c:if test="${start.fam_Eat=='素'}">
				<option value="葷" >葷</option>
				<option value="素" selected>素</option>
			</c:if>
			</select>
 			
	 			<c:if test="${start.fam_Car=='true'}">
					<input name="check1" type="checkbox" value="true" checked><div>占車位</div>
				</c:if>
				<c:if test="${start.fam_Car=='false'}">
					<input name="check1" type="checkbox" value="true" ><div>占車位</div>
				</c:if>
 			
			</td>
		
		<td><select class="multiselect aaa" name ="famspa"  multiple="multiple" data-placeholder="請選擇" style="width: 200px;">
		    
		     <c:if test="${start.fam_Bady=='false'}">
		     <option value="baby">幼童(0~3歲)</option>
			 </c:if>
			 <c:if test="${start.fam_Bady}">
			 <option id="${start.fam_No}_span_1" value="baby" Selected>幼童(0~3歲)</option>
			 </c:if>
			 <script>
			 	console.log('${start.fam_No}_span_1');
			 </script>
			 
			 <c:if test="${start.fam_kid=='false'}">
		     <option value="kid">兒童(4~11歲)</option>
			 </c:if>
			 <c:if test="${start.fam_kid}">	 
			 <option value="kid" Selected>兒童(4~11歲)</option>
			 </c:if>
			 		 
		      <c:if test="${start.fam_Dis=='false'}">
		     <option  value="dis">持身心障礙手冊</option>
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
		</td>
		
		<td><input type="text" name ="famben" id="famben" value="${start.fam_Ben}"><div class="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel" value="${start.fam_BenRel}" ><div class="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg" value="${start.fam_Emg}"><div class="famemgerror">${error.famemg}</div></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone" value="${start.fam_EmgPhone}"><div class="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famemgrel" id="famemgrel" value="${start.fam_EmgRel}"><div nameclass"famemgrelerror">${error.famemgrel}</div ></td>
		<td><input type="text" name ="famnote" id="famnote" value="${start.fam_Note}"><div class="famnoteerror"></div></td>
	</tr> 
	</c:forEach>
	
	</c:if>

</table>

<!--新增、儲存 -->
<input type="button" value="insert" id="insert" name ="button"><br>
<input type="submit" value ="save" id="save" name="button"><br>


</form>

	<table>
	<!-- 空白欄位 -->
	<tr name="repeat">
		<td><input type="button" name ="delete" id="delete" value="刪除"></td>
		<td>
			<select name ="famrel" >		
					<option value="眷屬" >眷屬</option>
					<option value="親友" >親友<option>
			</select>
			</td>
		<td><input type="text" name ="famname" id="famname"  ><div class="famnameerror">${error.famneme}</div></td>
		<td><select name ="famsex">  <!--  servlet抓name db抓值會抓進value值進去-->
			<option value="女" >女</option>
			<option value="男" >男<option>
		</select></td>
		<td><input type="text" name ="famid" id="famid" ><div class="famiderror">${error.famid}</div></td>
		<td><input type="date" id="fambdate" name="fambdate" class="fambdate" /><div class="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td>
		<td><input type="text" name ="famphone" id="famphone"  >  <div class=famphoneerror>${error.famphone}</div></td> 
		<td><select name ="fameat">
				<option value="葷" >葷</option>
				<option value="素" >素</option>
			</select>
			<input name="check1" type="checkbox" value="true" ><div>占車位</div>
 		</td>
		<td>
			<select  name ="famspa"  id="multiselect"  multiple="multiple" data-placeholder="請選擇" style="width: 200px;">
		     <option >幼童(0~3歲)</option>
		     <option>兒童(4~11歲)</option>
		     <option>持身心障礙手冊</option>
		     <option>孕婦(媽媽手冊)</option>
		     </select>
		</td>
<!-- 		class="multiselect"   id="multiselect"-->
		<td><input type="text" name ="famben" id="famben" ><div class="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel"><div class="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg"><div class="famemgerror">${error.famemg}</div ></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone"><div class="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famemgrel" id="famemgrel"><div class="famemgrelerror">${error.famemgrel}</div ></td>
		<td><input type="text" name ="famnote" id="famnote"><div class="famnoteerror"></div></td>
	</tr>
	</table>


<script>
$(function(){
	$(".multiselect").kendoMultiSelect({autoClose: false});
	$("tr[name='repeat']").hide();
	$("#familytable").attr("width","1200px").attr("border","3px").attr("border-collapse","collapse");
	$("#insert").click(
		function(){
			$("#familytable").append('<tr class=repeat >'+ $("tr[name='repeat']").html()+'</tr>');
			$(".repeat:last #multiselect").kendoMultiSelect({autoClose: false});
			
			}
	);	
	$("#familytable").on("click","input[name*='delete']",function(){
// 		$("input[name*='delete']").parents("tr:last").remove();
		$(this).parents("tr").remove();
		
	});	
	  
// 		  for(i=0;i<=;i++){
// 		  var fambdate =$(".repeat input[name*='fambdate']").
// 		  }
// 		  console.log(fambdate);
// 		  $.post("Register",{})
	  
// 	var xh = new XMLHttpRequest();
// 	search();
	
// 	function search() {
// 		if (xh != null) {
	
// 		var selectedValues = $('select[name="famspa"]').serialize() ;
		
// 		var pathName = document.location.pathname;
// 		var index = pathName.substr(1).indexOf("/");
// 		var result = pathName.substr(0, index + 1);
// 		var url = result + "/controller/Datainsert.do?";
		
// 		if (selectedValues!= undefined) {
// 			var selectvalues =JSON.stringify(selectedValues);
// 		}
	
// 		//轉json 格式? 字串證列  console.log() 輸出   h
				
// 		xh.addEventListener("readystatechange", ajaxReturn);
// 		xh.open("POST","FamilyServlet");
// 		xh.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// 		xh.send(selectvalues);
// 		console.log(selectedValues);
// 		}else {
// 			alert("Your browser doesn't support JSON!");
// 		}
// 	}
// 	function ajaxReturn() {
// 		if (xh.readyState == 4){
// 			if (xh.status == 200) {
// 			 	alert(selectedValues);
			 	
// 			}
// 		}
// 	}
	
	var empphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("#empphone").blur(function(){
		if(empphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#empphoneerror").text("");
		}else{
			$("#empphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
	var empben=/^.*\s*[^\s]/;
	$("#empben").blur(function(){
		if(empben.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenerror").text("");
		}else{
			$("#empbenerror").text("不能為空值");
			$(this).css("border-color","red");
		}
	});
	
	var empbenrel=/^.*\s*[^\s]/;
	$("#empbenrel").blur(function(){
		if(empbenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenrelerror").text("");
		}else{
			$("#empbenrelerror").text("不能為空值");
			$(this).css("border-color","red");
		}
	});
	
	var empemg=/^.*\s*[^\s]/;
	$("#empemg").blur(function(){
		if(empemg.test($(this).val())){
			$(this).css("border-color","green")
			$("#empemgerror").text("");
		}else{
			$("#empemgerror").text("不能為空值");
			$(this).css("border-color","red");
		}
	});
	
	var empemgphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("#empemgphone").blur(function(){
		if(empemgphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#empemgphoneerror").text("");
		}else{
			$("#empemgphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
	
	var famname=/^.*\s*[^\s]/;
	$("input[name*='famname']").on("blur",function(){
		if(famname.test($(this).val())){
			$(this).css("border-color","green")
			$("#famnameerror").text("");
		}else{
			//後面用append加一個h4 或什麼 來顯示錯誤訊息  新增的抓不到正規劃  正規劃錯誤後要不能新增進去
			$(this).css("border-color","red");
		}
	});
	$(".repeat td").on("blur","input[name='famname']",function(){
		if(famname.test($(this).val())){
			$(this).css("border-color","green")
		}else{
			$(this).css("border-color","red");
		}
	});
// 	$("tr[name='repeat']>input[name*='famname]").on("blur",x);
// 	$("input[name*='famname']").css("border-color","red");
// 	$("input[name*='famname']").css("border-color","green")
	
	$("input[name*='famid']").on("blur",
			function (){
		  // 依照字母的編號排列，存入陣列備用。
		  var letters = new Array('A', 'B', 'C', 'D', 
		      'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 
		      'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
		      'X', 'Y', 'W', 'Z', 'I', 'O');
		  // 儲存各個乘數
		  var multiply = new Array(1, 9, 8, 7, 6, 5, 
		                           4, 3, 2, 1);
		  var nums = new Array(2);
		  var firstChar;
		  var firstNum;
		  var lastNum;
		  var total = 0;
		  // 撰寫「正規表達式」。第一個字為英文字母，
		  // 第二個字為1或2，後面跟著8個數字，不分大小寫。
		  var regExpID=/^[a-z](1|2)\d{8}$/i; 
		  // 使用「正規表達式」檢驗格式
		  if (regExpID.test($("input[name*='famid']").val())){
		    
			// 取出第一個字元和最後一個數字。
				firstChar = $(this).val().charAt(0).toUpperCase();
				lastNum = $(this).val().charAt(9);
		   
		  } else {
			
			$(this).css("border-color","red");
			$("#famiderror").text("身分證格式錯誤");
// 			return false;
		  }
		  // 找出第一個字母對應的數字，並轉換成兩位數數字。
		  for (var i=0; i<26; i++) {
			if (firstChar == letters[i]) {
			  firstNum = i + 10;
			  nums[0] = Math.floor(firstNum / 10);
			  nums[1] = firstNum - (nums[0] * 10);
			  break;
			} 
		  }
		  // 執行加總計算
		  for(var i=0; i<multiply.length; i++){
		    if (i<2) {
		      total += nums[i] * multiply[i];
		    } else {
		      total += parseInt( $(this).val().charAt(i-1)) * 
		               multiply[i];
		    }
		  }
		  // 和最後一個數字比對
		  if ((10 - (total % 10))!= lastNum) {
			  $(this).css("border-color","red");
			  $("#famiderror").text("身分證格式錯誤");
// 			return false;
		  }else{
		  $(this).css("border-color","green")
// 		  return true;
		}});

	
	var fambdate=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	$("input[name*='fambdate']").on("blur",function(){
		if(fambdate.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambdateerror").text("");
		}else{
// 			$("#fambdateerror").text("需要為年-月-日的規格");
			$(this).css("border-color","red");
		}
	});
	
	var famphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("input[name*='famphone']").on("blur",function(){
		if(famphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#famnameerrror").text("");
		}else{
// 			$("#famnameerror").text("不符和手機規則");
			$(this).css("border-color","red");
		}
	});
	
	var famben=/^.*\s*[^\s]/;
	$("input[name*='famben']").on("blur",function(){
		if(famben.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenerror").text("");
		}else{
// 			$("#fambenerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var fambenrel=/^.*\s*[^\s]/;
	$("input[name*='fambenrel']").on("blur",function(){
		if(fambenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenrelerror").text("");
		}else{
// 			$("#fambenrelerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famemg=/^.*\s*[^\s]/;
	$("input[name*='famemg']").on("blur",function(){
		if(famemg.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgerror").text("");
		}else{
// 			$("#famemgerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famemgphpone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("input[name*='famemgphpone']").on("blur",function(){
		if(famemgphpone.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgphoneerror").text("");
		}else{
// 			$("#famemgphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
	var famemgrel=/^.*\s*[^\s]/;
	$("input[name*='famemgrel']").on("blur",function(){
		if(famemgrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgrel").text("");
		}else{
// 			$("#famemgrel").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
});

	
</script>


</body>
</html>