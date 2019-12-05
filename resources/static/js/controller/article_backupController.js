var getArticle_backups = function(categoryId, size, page) {
	var article_backupList = null;
	$.ajax({
		url : "/article_backups",
		type : "GET",
		data : {
			"categoryId" : categoryId,
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			article_backupList = result;
		}
	});
	return article_backupList;
}

var getArticle_backupsCount = function(categoryId) {
	var count = 0;
	$.ajax({
		url : "/article_backups/count",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}
