var getComment_backups = function(size, page) {
	var comment_backupList = null;
	$.ajax({
		url : "/comment_backups",
		type : "GET",
		data : {
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			comment_backupList = result;
		}
	});
	return comment_backupList;
}

var getComment_backupsCount = function() {
	var count = 0;
	$.ajax({
		url : "/comment_backups/count",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}
