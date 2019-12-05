/*无参数，获取多个category的信息*/
var getCategorys = function() {
	var categoryList = null;
	$.ajax({
		url : "/categorys",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			categoryList = result;
		}
	});
	return categoryList;
}

