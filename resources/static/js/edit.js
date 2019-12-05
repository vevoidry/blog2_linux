var startFunction = function(userId) {
	setUserImage(userId);
	setUser(userId);
}

var setUserImage = function(userId) {
	var user = null;
	user = getUser(userId);
	console.log(user);
	var startString = "";
	var middleString = "";
	var endString = "";
	middleString="头像：<img src=\"/image/"+user.image+"\" alt=\"Image\" class=\"img-rounded\" width=\"200px\" height=\"200px\">"+
	"<form action=\"/users/"+user.id+"/image\" method=\"post\" enctype=\"multipart/form-data\">"+
	"<input type=\"file\" name=\"filename\" class=\"form-control\"/>"+
	"<button type=\"submit\" class=\"btn btn-default\">保存</button>"+
	"</form>";
	$("#imageArea").html(startString + middleString + endString);
}

var setUser = function(userId) {
	var user = null;
	user = getUser(userId);
	var startString = "";
	var middleString = "";
	var endString = "";
	var genderString="";
	if(user.gender=="男"){
		genderString="男<input type=\"radio\" name=\"gender\" value=\"男\" checked=\"checked\"/>女<input type=\"radio\" name=\"gender\" value=\"女\"/>";
	}else{
		genderString="男<input type=\"radio\" name=\"gender\" value=\"男\"/>女<input type=\"radio\" name=\"gender\" value=\"女\" checked=\"checked\"/>";
	}
	middleString=
		"<form action=\"/users/"+user.id+"\" method=\"post\">"+
		"<input type=\"hidden\" name=\"_method\" value=\"put\"/>"+
		"<input type=\"text\" name=\"id\" value=\""+user.id+"\" hidden/>"+
		"<input type=\"text\" name=\"username\" value=\""+user.username+"\" hidden/>"+
		"<div class=\"panel panel-default\"><div class=\"panel-body\">昵称：<input type=\"text\" name=\"nickname\" value=\""+user.nickname+"\"/>（昵称长度不可超过12个字符）</div></div>"+
		"<div class=\"panel panel-default\"><div class=\"panel-body\">"+
		"<div>"+"性别："+genderString+"</div>"+
		"</div></div>"+
		"<div class=\"panel panel-default\"><div class=\"panel-body\"><div>生日：<input type=\"text\" name=\"birthday\" value=\""+user.birthday+"\"/></div></div></div>"+
		"<div class=\"panel panel-default\"><div class=\"panel-body\"><div>简介<input type=\"text\" name=\"profile\" value=\""+user.profile+"\"/></div></div></div>"+
		"<div class=\"panel panel-default\"><div class=\"panel-body\"><div><button type=\"submit\" class=\"btn btn-default\">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/\"><button type=\"button\" class=\"btn btn-default\">取消</button></a></div></div></div>"+
		"</form>";
	$("#userArea").html(startString + middleString + endString);
}
