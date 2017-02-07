var xhinlogin=new XMLHttpRequest();
window.onload=function(){
	call();
}

function call(){
	xhinlogin.open("GET",resultjs+"/LogOut.do?role=1")
	xhinlogin.send();
}

xhinlogin.onload=function(){
	var bodyjs = document.getElementById("bar");
	var rolejs = 1;
	var divjs = document.createElement("div");
	var btnjs = document.createElement("input");
	
	btnjs.setAttribute("value","報名/查詢");
	btnjs.setAttribute("type","button");
	btnjs.setAttribute("onclick","search_signup()");
	divjs.appendChild(btnjs);
	
	btnjs = document.createElement("input");
	btnjs.setAttribute("value","資料輸入");
	btnjs.setAttribute("type","button");
	btnjs.setAttribute("onclick","datainsert()");
	divjs.appendChild(btnjs);
	
	rolejs=this.responseText;	
	
	if(rolejs=='true'){
		btnjs = document.createElement("input");
		btnjs.setAttribute("value","行程維護");
		btnjs.setAttribute("type","button");
		btnjs.setAttribute("onclick","travel_maintain()");
		divjs.appendChild(btnjs);
		
		btnjs = document.createElement("input");
		btnjs.setAttribute("value","報名維護");
		btnjs.setAttribute("type","button");
		btnjs.setAttribute("onclick","people_maintain()");
		divjs.appendChild(btnjs);
		
		btnjs = document.createElement("input");
		btnjs.setAttribute("value","罰則維護");
		btnjs.setAttribute("type","button");
		btnjs.setAttribute("onclick","fine()");
		divjs.appendChild(btnjs);
		
		btnjs = document.createElement("input");
		btnjs.setAttribute("value","旅費統計");
		btnjs.setAttribute("type","button");
		btnjs.setAttribute("onclick","detail_money()");
		divjs.appendChild(btnjs);
	}
	bodyjs.appendChild(divjs);	
}
var pathNamejs = document.location.pathname;
var indexjs = pathNamejs.substr(1).indexOf("/");
var resultjs = pathNamejs.substr(0, indexjs + 1);
	
function search_signup(){
	window.location.href=resultjs+"/AllTravel";
}

function datainsert(){
	window.location.href=resultjs+"/register";
}

function travel_maintain(){
	window.location.href=resultjs+"/search.jsp";
}

function people_maintain(){
	window.location.href=resultjs+"/search.jsp";
}

function fine(){
	window.location.href=resultjs+"/FineServlet?FineSetting=罰則設定";
}
	
function detail_money(){
	window.location.href=resultjs+"/Detail_Money.jsp";
}