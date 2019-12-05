var getUsers = function(size, page) {
	var userList = null;
	$.ajax({
		url : "/users",
		type : "GET",
		data : {
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			userList = result;
		}
	});
	return userList;
}

var getUsersCount = function() {
	var count = 0;
	$.ajax({
		url : "/users/count",
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}

var getUser = function(userId) {
	var user = null;
	$.ajax({
		url : "/users/" + userId,
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			user = result;
		}
	});
	return user;
}
