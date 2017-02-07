var xhinjs=new XMLHttpRequest();
var pathName = document.location.pathname;
var index = pathName.substr(1).indexOf("/");
var result = pathName.substr(0, index + 1);
function logOut(){

	xhinjs.open("GET",result+"/LogOut.do")
	xhinjs.send();
}

xhinjs.onload=function(){

	window.location.href=result+"/login.jsp";
}