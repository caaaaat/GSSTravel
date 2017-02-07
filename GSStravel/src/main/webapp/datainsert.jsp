<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
	<script src="js/jquery-3.1.1.min.js"></script>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<div id="bar"></div><!-- 記得改 -->

<form  action=<c:url value="/Servlet"/>  method="post">
<table>
<span>員工編號</span>${empno}<br>
<span>姓名</span>${empname}<br>
<tr><span>手機</span><input type="text" name ="empphone" id="empphone"  value="${empphone}">  <div id=empphoneerror>${error.empphone}</div><br>
<span>保險受益人</span><input type="text" name ="empben" id="empben" value = "${empben}"><div id=empbenerror>${error.empben}</div><br>
<span>與受益人關係</span><input type="text" name ="empbenrel" id="empbenrel" value = "${empbenrel}"><div id=empbenrelerror>${error.empbenrel}</div><br>
<span>緊急聯絡人</span><input type="text" name ="empemg" id="empemg" value = "${empemg}" ><div id=empemgerror>${error.empemg}</div><br>
<tr><span>緊急聯絡人電話</span><input type="text" name ="empemgphone" id="empemgphone" value="${empemgphone}"><div id=empemgphoneerror>${error.empemgphone}</div><br>

<span>用餐</span><select name ="empeat" value="${empeat}">
<option  value="0">葷</option>
<option  value="1">素</option>
</select><span>(如有特別要求請填寫於備註)</span><br>
<span>備註</span><input type="text" name ="empnote" id="empnote" value="${empnote}" ><div id=empnoteerror></div><br>
</table>

<div>
－眷屬親友資訊－<br>
*眷屬包含直系及旁系二等親，納入補助計算<br>
*親友為旁系三等親以上及朋友，不納入補助計算<br>
</div>


<table id=familytable>
<tr >
	<th></th>
	<th>*眷屬/親友</th>
	<th>*姓名</th>
	<th>*性別</th>
	<th>*身份證字號</th>
	<th>*生日</th>
	<th>用餐/車位</th>
	<th>幼童(0~3歲)</th>
	<th>兒童(4~11歲)</th>
	<th>持身心障礙手冊</th>
	<th>孕婦</th>
	<th>*保險受益人</th>
	<th>*保險受益人關係</th>
	<th>*緊急聯絡人</th>
	<th>*緊急聯絡人電話</th>
	<th>備註</th>
</tr>
			<!-- option裡面有空白??????? --><!--沒有抓到錯誤訊息-->		
	<c:if test="${famstartsize>0}">
	<c:forEach var="start" items="${famstart}"  >
	  <tr >
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
		<td><input type="text" name ="famname" id="famname" value="${start.fam_Name}" ><div id="famnameerror" name="famnameerror">${error.famneme}</div></td>
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
		
		<td><input type="text" name ="famid" id="famid" value="${start.fam_Id}"><div id="famiderror">${error.famid}</div></td><!-- getfamid()會抓到value值 -->
		<td><input type="text" id="fambdate" name="fambdate" value="${start.fam_Bdate}"><div id="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td> 
		
		<td><select name ="fameat" value="${start.fam_Eat}">
			<c:if test="${start.fam_Eat=='葷'}">
				<option value="葷" selected>葷</option>
				<option value="素">素</option>
			</c:if>
			<c:if test="${start.fam_Eat=='素'}">
				<option value="葷" >葷</option>
				<option value="素" selected>素</option>
			</c:if>
			</select>
			
			<c:if test="${start.fam_Car=='true'}">
			<input type="checkbox" id="famcar" name="famcar" checked>不占車位</td>
			</c:if>
			<c:if test="${start.fam_Car=='false'}">
			<input type="checkbox" id="famcar" name="famcar" >不占車位</td>
			</c:if>
			
		<c:if test="${start.fam_Bady=='true'}">	
		<td><input type="checkbox" id ="fambaby" name= "fambaby" checked>是</td>
		</c:if>
		<c:if test="${start.fam_Bady=='false'}">	
		<td><input type="checkbox" id ="fambaby" name= "fambaby" >是</td>
		</c:if>
		
		<c:if test="${start.fam_kid=='true'}">
		<td><input type="checkbox" id ="famkid" name= "famkid" checked>是</td>
		</c:if>
		<c:if test="${start.fam_kid=='false'}">
		<td><input type="checkbox" id ="famkid" name= "famkid" >是</td>
		</c:if>
		
		<c:if test="${start.fam_Dis=='true'}">
		<td><input type="checkbox" id ="famdis" name= "famdis" checked>是</td>
		</c:if>
		<c:if test="${start.fam_Dis=='false'}">
		<td><input type="checkbox" id ="famdis" name= "famdis" >是</td>
		</c:if>
		
		<c:if test="${start.fam_Mom=='true'}">
		<td><input type="checkbox" id ="fammom" name= "fammom" checked>是</td>
		</c:if>
		<c:if test="${start.fam_Mom=='false'}">
		<<td><input type="checkbox" id ="fammom" name= "fammom" >是</td>
		</c:if>
		
		
		<td><input type="text" name ="famben" id="famben" value="${start.fam_Ben}"><div id="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel" value="${start.fam_BenRel}" ><div id="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg" value="${start.fam_Emg}"><div id="famemgerror">${error.famemg}</div ></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone" value="${start.fam_EmgPhone}"><div id="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famnote" id="famnote" value="${start.fam_Note}"><div id="famnoteerror"></div></td>
	</tr> 
	
	
	</c:forEach>
	</c:if>

</table>

<!--新增、儲存 -->
<input type="button" value="insert" id="insert" name ="button"><br>
<input type="submit" value ="save" id="save" name="button"><br>

<%-- <tr><td>${error.errormsg}</td></tr> --%>

</form>


	<table>
	<!-- 空白欄位 -->
	<tr id=repeat>
		<td><input type="button" name ="delete" id="delete" value="刪除"></td>
		<td>
			<select name ="famrel" >		
					<option value="眷屬" >眷屬</option>
					<option value="親友" >親友<option>
			</select>
			</td>
		<td><input type="text" name ="famname" id="famname"  ><div id="famnameerror">${error.famneme}</div></td>
		<td><select name ="famsex">  <!--  servlet抓name db抓值會抓進value值進去-->
			<option value="女" >女</option>
			<option value="男" >男<option>
		</select></td>
		<td><input type="text" name ="famid" id="famid" ><div id="famiderror">${error.famid}</div></td><!-- getfamid()會抓到value值 -->
		<td><input type="text" id="fambdate" name="fambdate" ><div id="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td> 
		<td><select name ="fameat" value="${start.fam_Eat}">
				<option value="葷" >葷</option>
				<option value="素" >素</option>
			</select>
			<input type="checkbox" id="famcar" name="famcar" >不占車位</td>
		<td><input type="checkbox" id ="fambaby" name= "fambaby" >是</td>
		<td><input type="checkbox" id ="famkid" name= "famkid" >是</td>
		<td><input type="checkbox" id ="famdis" name= "famdis" >是</td>
		<<td><input type="checkbox" id ="fammom" name= "fammom" >是</td>
		
		<td><input type="text" name ="famben" id="famben" ><div id="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel"><div id="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg"><div id="famemgerror">${error.famemg}</div ></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone"><div id="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famnote" id="famnote"><div id="famnoteerror"></div></td>
	</tr>
	</table>


<script>
$(function(){
	$("#repeat").hide();
	$("#familytable").attr("width","1200px").attr("border","3px").attr("border-collapse","collapse");
	$("#insert").click(
		function(){
			$("#familytable").append('<tr id=repeat name=repeat>'+ $("#repeat").html()+'<tr>');
			}
	);	

	$("#familytable").on("click","input[name*='delete']",function(){
		$("input[name*='delete']").parents("tr:last").remove();
		
	});
	
	
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
	
	var empben=/^[^\s].*[^\s]/;
	$("#empben").blur(function(){
		if(empben.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenerror").text("");
		}else{
			$("#empbenerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var empbenrel=/^[^\s].*\s*[^\s]/;
	$("#empbenrel").blur(function(){
		if(empbenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenrelerror").text("");
		}else{
			$("#empbenrelerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var empemg=/^[^\s].*\s*[^\s]/;
	$("#empemg").blur(function(){
		if(empemg.test($(this).val())){
			$(this).css("border-color","green")
			$("#empemgerror").text("");
		}else{
			$("#empemgerror").text("需要為中文");
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
	
	var empnote=/.*\s/;
	$("#empnote").blur(function(){
		if(empnote.test($(this).val())){
			$(this).css("border-color","green")
			$("#empnoteerror").text("");
		}else{
			$("#empnoteerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famname=/^[^\s].*\s*[^\s]/;
	var x= function (){
		if(famname.test($(this).val())){
			$(this).css("border-color","green")
			$("#famnameerror").text("");
		}else{
// 			$("#famnameerror").on("text",function(){$(this).text("需要為中文")});
			$(this).css("border-color","red");
		}
	};
	$("input[name*='famname']").on("blur",x);
// 	$("tr[name='repeat']>input[name*='famname]").on("blur",x);
	
	
	var famid=/^[A-Za-z][1-2]\d{8}$/;
	$("input[name*='famid']").on("blur",function(){
		if(famid.test($(this).val())){
			$(this).css("border-color","green")
			$("#famiderror").text("");
		}else{
// 			$("#famiderror").text("需要為台灣身份證規格");
			$(this).css("border-color","red");
		}
	});
	
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
	
	var famben=/^[^\s].*\s*[^\s]/;
	$("input[name*='famben']").on("blur",function(){
		if(famben.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenerror").text("");
		}else{
// 			$("#fambenerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var fambenrel=/^[^\s].*\s*[^\s]/;
	$("input[name*='fambenrel']").on("blur",function(){
		if(fambenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenrelerror").text("");
		}else{
// 			$("#fambenrelerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famemg=/^[^\s].*\s*[^\s]/;
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
	
	var famnote=/.*\s/;
	$("input[name*='famnote']").on("blur",function(){
		if(famnote.test($(this).val())){
			$(this).css("border-color","green")
			$("#famnoteerror").text("");
		}else{
// 			$("#famnoteerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
});

	
</script>


</body>
<script type="text/javascript" src="/GSStravel/js/selectBar.js"></script>
</html>