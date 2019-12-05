var getUser_backups = function(size, page) {
	var user_backupsList = null;
	$.ajax({
		url : "/user_backups",
		type : "GET",
		data : {
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			user_backupsList = result;
		}
	});
	return user_backupsList;
}

var getUser_backupsCount = function() {
	var count = 0;
	$.ajax({
		url : "/user_backups/count",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}
