/*网页一开始即调用该函数*/
var startFunction = function(articleId) {
	setArticle(articleId);
	setCommentsCount(articleId);
}

/* 生成article */
var setArticle = function(articleId) {
	var article = null;
	article = getArticle(articleId);
	var startString = "";
	var middleString = "";
	var endString = "";
	var time=timeFormat(article.create_time);
	middleString = "<div class=\"panel panel-success\">"
			+ "<div class=\"panel-heading\"><h3>" + article.title
			+ "</h3></div>"
			+ "<div class=\"panel-body\"><div><a href=\"/user?userId="
			+ article.user_id + "\">发帖者：<span>" + article.user_nickname
			+ "</span></a>&nbsp;&nbsp;&nbsp;&nbsp;发帖时间：<span>"
			+ time + "</span></div></div>"
			+ "<div class=\"panel-footer\"><p>" + article.content_complete
			+ "</p></div>" + "</div>";
	/* 绘制文章模板 */
	$("#articleArea").html(startString + middleString + endString);

}

// 生成评论分页
var setCommentsCount = function(articleId) {
	var count = 0;
	count = getCommentsCount(articleId);
	var size = 10;
	var pageSize = 0;
	pageSize = (count - 1) / size + 1;
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setComments(" + articleId + ","
				+ size + "," + index + ")\">" + index + "</a></li>";
	}
	$("#pageArea").html(startString + middleString + endString);
	setComments(articleId, size, 1);
}
// 生成评论
var setComments = function(articleId, size, page) {
	var commentList = null;
	commentList = getComments(articleId, size, page);
	var startString = "";
	var middleString = "";
	var endString = "";
	for ( var index in commentList) {
		var time1 = "" + commentList[index].create_time;
		var time2 = time1.slice(0, 19);
		var time3 = time2.replace("T", " ");
		middleString += "<div class=\"panel panel-success\"><div class=\"panel-body\">第"
				+ commentList[index].comment_rank
				+ "楼&nbsp;&nbsp;&nbsp;&nbsp;"
				+ time3
				+ "&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<a href=\"/user?userId="
				+ commentList[index].user_id
				+ "\">"
				+ commentList[index].user_nickname
				+ "</a></div>"
				+ "	<div class=\"panel-footer\" >"
				+ commentList[index].content
				+ "</div></div>";
	}
	$("#commentArea").html(startString + middleString + endString);
}
