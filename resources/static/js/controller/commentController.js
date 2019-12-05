var getComments = function(articleId, size, page) {
	var commentList = null;
	$.ajax({
		url : "/comments",
		type : "GET",
		data : {
			"articleId" : articleId,
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			commentList = result;
		}
	});
	return commentList;
}

var getCommentsCount = function(articleId) {
	var count = 0;
	$.ajax({
		url : "/comments/count",
		type : "GET",
		data : {
			"articleId" : articleId
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}
