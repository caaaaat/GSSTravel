<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>罰則一覽表</title>
<style>
tr, td {
	border: 1px solid black;
	text-align: center;
}
.error {
	color: blue;
}
</style>
<script src="/GSStravel/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/LogOut.js"></script>
<script type="text/javascript" src="js/selectBar.js"></script>
</head>
<body>
	<div id="bar"></div>
	<h2>罰則明細</h2>
	<form action="<c:url value="/FineServlet" />" method="GET">
		<input type="submit" name="FineSetting" value="設定罰則" />
		<input type="submit" name="FineShow" value="查看罰則" /><br>
		<c:if test="${power==true}">
			<c:if test="${countI+1 ne 0 && countJ+1 ne 0}">
				<table id="resultTable">
					<tr>
						<td>行程 ＼ 罰則</td>
						<c:forEach var="i" varStatus="statusI" begin="0" end="${countI}">
							<c:if test="${statusI.count==1}">
								<td>旅遊前${fSelect[i].fine_Dates}天通知<br>扣款總費用 * ${fSelect[i].fine_Per}%</td>
							</c:if>
							<c:if test="${statusI.count>1}">
								<td>旅遊前${fSelect[i].fine_Dates} ~ ${fSelect[i-1].fine_Dates-1}天通知<br>扣款總費用 * ${fSelect[i].fine_Per}%</td>
							</c:if>
						</c:forEach>
						<td>旅遊開始日<br>扣款總費用 * 100%</td>
					</tr>
					<c:forEach var="i" begin="0" end="${countJ}">
						<tr>
							<td>${tSelect[i].tra_Name}<br>${totalDays[i][countI+1]}</td>
							<c:forEach var="j" varStatus="statusJ" begin="0" end="${countI}">
								<c:if test="${statusJ.count==1}">
									<td>報名截止日 ~ ${totalDays[i][j]}<br>${iSelect[i].item_Money} * ${fSelect[j].fine_Per}% = ${iSelect[i].item_Money*fSelect[j].fine_Per/100}</td>
								</c:if>
								<c:if test="${statusJ.count!=1}">
									<td>${afterDay[i][j-1]} ~ ${totalDays[i][j]}<br>${iSelect[i].item_Money} * ${fSelect[j].fine_Per}% = ${iSelect[i].item_Money*fSelect[j].fine_Per/100}</td>
								</c:if>
							</c:forEach>
							<td>${totalDays[i][countI+1]}<br>${iSelect[i].item_Money}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:if>
		<c:choose>
			<c:when test="${countI+1 eq 0 && countJ+1 eq 0}">
				<h2 class="error">目前尚無罰則＆行程資訊！</h2>
			</c:when>
			<c:when test="${countI+1 eq 0}">
				<h2 class="error">目前尚無罰則資訊！</h2>
			</c:when>
			<c:when test="${countJ+1 eq 0}">
				<h2 class="error">目前尚無行程資訊！</h2>
			</c:when>
		</c:choose>
	</form>
</body>
</html>