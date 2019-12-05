var startFunction = function(userId) {
	setUser(userId);
}

var setUser = function(userId) {
	/* 获取用户信息 */
	var user = null;
	user = getUser(userId);
	/* 生成用户模板 */
	var startString = "<div class=\"panel panel-default\"><div class=\"panel-heading\">用户信息</div><div class=\"panel-body\">";
	var middleString = "";
	var endString = "</div></div>";
	middleString = "<h3>"
			+ "<a href=\"#\" class=\"btn btn-default btn-lg disabled\" role=\"button\">昵称</a>"
			+ "<span>"
			+ user.nickname
			+ "</span>"
			+ "</h3>"
			+ "<h3>"
			+ "<a href=\"#\" class=\"btn btn-default btn-lg disabled\" role=\"button\">头像</a>"
			+ "<img src=\"/image/"
			+ user.image
			+ "\" alt=\"image\""
			+ "class=\"img-rounded\" width=\"200px\" height=\"200px\">"
			+ "</h3>"
			+ "<h3>"
			+ "<a href=\"#\" class=\"btn btn-default btn-lg disabled\" role=\"button\">性别</a>"
			+ "<span>"
			+ user.gender
			+ "</span>"
			+ "</h3>"
			+ "<h3>"
			+ "<a href=\"#\" class=\"btn btn-default btn-lg disabled\" role=\"button\">生日</a>"
			+ "<span>"
			+ user.birthday
			+ "</span>"
			+ "</h3>"
			+ "<h3>"
			+ "<a href=\"#\" class=\"btn btn-default btn-lg disabled\" role=\"button\">简介</a>"
			+ "<span>" + user.profile + "</span>" + "</h3>";
	/* 绘制用户模板 */
	$("#userArea").html(startString + middleString + endString);
}
