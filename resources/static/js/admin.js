/*网页一开始即调用该函数*/
var startFunction = function() {
	setUsersCount();
}

// 生成正常用户分页
var setUsersCount = function() {
	var size = 10;
	var count = 0;
	count = getUsersCount();
	var pageSize = 0;
	pageSize = Math.floor((count - 1) / 10) + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setUsers(" + size + "," + index
				+ ")\">" + index + "</a></li>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#pageArea").html(startString + middleString + endString);
	setUsers(size, 1);
}

// 生成正常用户表格
var setUsers = function(size, page) {
	var userList = null;
	userList = getUsers(size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>username</th><th>nickname</th><th>image</th><th>gender</th><th>birthday</th><th>profile</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in userList) {
		console.log(index);
		middleString += "<tr><td>"
				+ userList[index].id
				+ "</td><td>"
				+ userList[index].username
				+ "</td><td>"
				+ userList[index].nickname
				+ "</td><td>"
				+ "<img src=\"/image/"
				+ userList[index].image
				+ "\" alt=\"Image\" class=\"img-rounded\" width=\"100px\" height=\"100px\">"
				+ "</td><td>"
				+ userList[index].gender
				+ "</td><td>"
				+ userList[index].birthday
				+ "</td><td>"
				+ userList[index].profile
				+ "</td><td><form action=\"/users/"
				+ userList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">冻结</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}

// 生成冻结用户分页
var setUser_backupsCount = function() {
	var size = 10;
	var count = 0;
	count = getUser_backupsCount();
	var pageSize = 0;
	pageSize = Math.floor((count - 1) / 10) + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setUser_backups(" + size + ","
				+ index + ")\">" + index + "</a></li>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#pageArea").html(startString + middleString + endString);
	setUser_backups(size, 1);
}

// 生成冻结用户表格
var setUser_backups = function(size, page) {
	var user_backupList = null;
	user_backupList = getUser_backups(size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>user_id</th><th>username</th><th>nickname</th><th>image</th><th>gender</th><th>birthday</th><th>profile</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in user_backupList) {
		console.log(index);
		middleString += "<tr><td>"
				+ user_backupList[index].id
				+ "</td><td>"
				+ user_backupList[index].user_id
				+ "</td><td>"
				+ user_backupList[index].username
				+ "</td><td>"
				+ user_backupList[index].nickname
				+ "</td><td>"
				+ "<img src=\"/image/"
				+ user_backupList[index].image
				+ "\" alt=\"Image\" class=\"img-rounded\" width=\"100px\" height=\"100px\">"
				+ "</td><td>"
				+ user_backupList[index].gender
				+ "</td><td>"
				+ user_backupList[index].birthday
				+ "</td><td>"
				+ user_backupList[index].profile
				+ "</td><td><form action=\"/user_backups/"
				+ user_backupList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">解冻</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}

// 生成正常目录表格
var setCategorys = function() {
	var categoryList = null;
	categoryList = getCategorys();
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>name</th><th>操作</th></tr>";
	var endString = "</table>"
			+ "<form action=\"/categorys\" method=\"post\"><input type=\"text\" name=\"categoryName\"><button type=\"submit\">新增目录</button></form>";
	for ( var index in categoryList) {
		middleString += "<tr><td>"
				+ categoryList[index].id
				+ "</td><td>"
				+ categoryList[index].name
				+ "</td><td><form action=\"/categorys/"
				+ categoryList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">冻结</button></form></td></tr>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#mainArea").html(startString + middleString + endString);
}

// 生成冻结目录表格
var setCategory_backups = function() {
	var category_backupList = null;
	category_backupList = getCategory_backups();
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>categoryId</th><th>name</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in category_backupList) {
		middleString += "<tr><td>"
				+ category_backupList[index].id
				+ "</td><td>"
				+ category_backupList[index].category_id
				+ "</td><td>"
				+ category_backupList[index].name
				+ "</td><td><form action=\"/category_backups/"
				+ category_backupList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">解冻</button></form></td></tr>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#mainArea").html(startString + middleString + endString);
}

// 生成正常帖子目录
var setArticlesCategory = function() {
	var categoryList = null;
	categoryList = getCategorys();
	var startString = "<ul class=\"nav nav-tabs\">";
	var middleString = "";
	var endString = "</ul>";
	for ( var index in categoryList) {
		middleString += "<li><a onclick=\"setArticlesCount("
				+ categoryList[index].id + ")\">" + categoryList[index].name
				+ "</a></li>";
	}
	$("#titleArea").html("");
	$("#pageArea").html("");
	$("#mainArea").html("");
	$("#titleArea").html(startString + middleString + endString);
	setArticlesCount(1);
}

// 生成正常帖子分页
var setArticlesCount = function(categoryId) {
	var size = 10;
	var count = 0;
	count = getArticlesCount(categoryId);
	var pageSize = 0;
	pageSize = (count - 1) / size + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	console.log(pageSize);
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setArticles(" + categoryId + ","
				+ size + "," + index + ")\">" + index + "</a></li>";
	}
	$("#pageArea").html(startString + middleString + endString);
	setArticles(categoryId, size, 1);
}

// 生成正常帖子缩略
var setArticles = function(categoryId, size, page) {
	var articleList = null;
	articleList = getArticles(categoryId, size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>categoryId</th><th>userId</th><th>userNickname</th><th>createTime</th><th>Title</th><th>contentSynopsis</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in articleList) {
		var time=timeFormat(articleList[index].create_time);
		console.log(index);
		middleString += "<tr><td>"
				+ articleList[index].id
				+ "</td><td>"
				+ articleList[index].category_id
				+ "</td><td>"
				+ articleList[index].user_id
				+ "</td><td>"
				+ articleList[index].user_nickname
				+ "</td><td>"
				+ time
				+ "</td><td>"
				+ articleList[index].title
				+ "</td><td>"
				+ articleList[index].content_synopsis
				+ "</td><td><form action=\"/articles/"
				+ articleList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">冻结</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}

// 生成冻结帖子目录
var setArticle_backupsCategory = function() {
	var categoryList = null;
	categoryList = getCategorys();
	var startString = "<ul class=\"nav nav-tabs\">";
	var middleString = "";
	var endString = "</ul>";
	for ( var index in categoryList) {
		middleString += "<li><a onclick=\"setArticle_backupsCount("
				+ categoryList[index].id + ")\">" + categoryList[index].name
				+ "</a></li>";
	}
	$("#titleArea").html("");
	$("#pageArea").html("");
	$("#mainArea").html("");
	$("#titleArea").html(startString + middleString + endString);
	setArticle_backupsCount(1);
}

// 生成冻结帖子分页
var setArticle_backupsCount = function(categoryId) {
	var size = 10;
	var count = 0;
	count = getArticle_backupsCount(categoryId);
	var pageSize = 0;
	pageSize = (count - 1) / size + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	console.log(pageSize);
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setArticle_backups(" + categoryId
				+ "," + size + "," + index + ")\">" + index + "</a></li>";
	}
	$("#pageArea").html(startString + middleString + endString);
	setArticle_backups(categoryId, size, 1);
}

// 生成冻结帖子缩略
var setArticle_backups = function(categoryId, size, page) {
	var article_backupList = null;
	article_backupList = getArticle_backups(categoryId, size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>articleId</th><th>categoryId</th><th>userId</th><th>userNickname</th><th>createTime</th><th>Title</th><th>contentSynopsis</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in article_backupList) {
		console.log(index);
		var time=timeFormat(article_backupList[index].create_time);
		middleString += "<tr><td>"
				+ article_backupList[index].id
				+ "</td><td>"
				+ article_backupList[index].article_id
				+ "</td><td>"
				+ article_backupList[index].category_id
				+ "</td><td>"
				+ article_backupList[index].user_id
				+ "</td><td>"
				+ article_backupList[index].user_nickname
				+ "</td><td>"
				+ time
				+ "</td><td>"
				+ article_backupList[index].title
				+ "</td><td>"
				+ article_backupList[index].content_synopsis
				+ "</td><td><form action=\"/article_backups/"
				+ article_backupList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">解冻</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}

// 生成正常评论分页
var setCommentsCount = function() {
	var size = 10;
	var count = 0;
	var articleId = null;
	count = getCommentsCount(articleId);
	var pageSize = 0;
	pageSize = Math.floor((count - 1) / 10) + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setComments(" + size + "," + index
				+ ")\">" + index + "</a></li>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#pageArea").html(startString + middleString + endString);
	setComments(size, 1);
}

// 根据正常评论表格
var setComments = function(size, page) {
	var commentList = null;
	var articleId = null;
	commentList = getComments(articleId, size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>userId</th><th>userNickname</th><th>articleId</th><th>createTime</th><th>commentRank</th><th>content</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in commentList) {
		var time=timeFormat(commentList[index].create_time);
		middleString += "<tr><td>"
				+ commentList[index].id
				+ "</td><td>"
				+ commentList[index].user_id
				+ "</td><td>"
				+ commentList[index].user_nickname
				+ "</td><td>"
				+ commentList[index].article_id
				+ "</td><td>"
				+ time
				+ "</td><td>"
				+ commentList[index].comment_rank
				+ "</td><td>"
				+ commentList[index].content
				+ "</td><td><form action=\"/comments/"
				+ commentList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">冻结</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}

// 生成冻结评论分页
var setComment_backupsCount = function() {
	var size = 10;
	var count = 0;
	count = getComment_backupsCount();
	var pageSize = 0;
	pageSize = Math.floor((count - 1) / 10) + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	console.log(pageSize);
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setComment_backups(" + size + ","
				+ index + ")\">" + index + "</a></li>";
	}
	$("#titleArea").html("");
	$("#mainArea").html("");
	$("#pageArea").html("");
	$("#pageArea").html(startString + middleString + endString);
	setComment_backups(size, 1);
}

// 根据分页生成评论表格
var setComment_backups = function(size, page) {
	var comment_backupsList = null;
	comment_backupsList = getComment_backups(size, page);
	var startString = "<table class=\"table\">";
	var middleString = "<tr><th>id</th><th>commentId</th><th>userId</th><th>userNickname</th><th>articleId</th><th>createTime</th><th>commentRank</th><th>content</th><th>操作</th></tr>";
	var endString = "</table>";
	for ( var index in comment_backupsList) {
		var time=timeFormat(comment_backupsList[index].create_time);
		middleString += "<tr><td>"
				+ comment_backupsList[index].id
				+ "</td><td>"
				+ comment_backupsList[index].comment_id
				+ "</td><td>"
				+ comment_backupsList[index].user_id
				+ "</td><td>"
				+ comment_backupsList[index].user_nickname
				+ "</td><td>"
				+ comment_backupsList[index].article_id
				+ "</td><td>"
				+ time
				+ "</td><td>"
				+ comment_backupsList[index].comment_rank
				+ "</td><td>"
				+ comment_backupsList[index].content
				+ "</td><td><form action=\"/comment_backups/"
				+ comment_backupsList[index].id
				+ "\" method=\"post\"><input type=\"hidden\" name=\"_method\" value=\"delete\"/><button type=\"submit\">解冻</button></form></td></tr>";
	}
	$("#mainArea").html(startString + middleString + endString);
}
