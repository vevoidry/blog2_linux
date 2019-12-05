var startFunction = function() {
	login();
}

var login = function() {
	/*让所有元素的active状态失效*/
	$("#loginLi").removeClass("active");
	$("#registerLi").removeClass("active");
	/*让某个元素的active状态生效*/
	$("#loginLi").addClass("active");
	/*让某些元素隐藏，某些元素取消隐藏*/
	$("#login").show();
	$("#register").hide();
}
var register = function() {
	/*让所有元素的active状态失效*/
	$("#loginLi").removeClass("active");
	$("#registerLi").removeClass("active");
	/*让某个元素的active状态生效*/
	$("#registerLi").addClass("active");
	/*让某些元素隐藏，某些元素取消隐藏*/
	$("#login").hide();
	$("#register").show();
}

