var getCategory_backups = function() {
	var article_backupList = null;
	$.ajax({
		url : "/category_backups",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			article_backupList = result;
		}
	});
	return article_backupList;
}