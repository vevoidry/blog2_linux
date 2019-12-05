var startFunction = function() {
	setForm();
}

var setForm = function() {
	var categoryList = null;
	categoryList = getCategorys();
	var startString = "<form action=\"/articles\" method=\"post\"><div><select name=\"categoryId\">";
	var middleString = "";
	var endString = "</select></div><div>标题<input type=\"text\" name=\"title\"></div><div>"
			+ "内容<textarea class=\"form-control\" rows=\"3\" name=\"content\"></textarea>"
			+ "</div>" + "<button type=\"submit\">发帖</button>" + "</form>";
	for ( var index in categoryList) {
		middleString += "<option value=\"" + categoryList[index].id + "\">"
				+ categoryList[index].name + "</option>";
	}
	$("#formArea").html(startString + middleString + endString);
}
