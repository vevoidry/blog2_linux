/*网页一开始即调用该函数*/
var startFunction = function() {
	setCategorys();
	setArticlesPage(1);
}
/* 目录函数：作用是生成目录模板 */
var setCategorys = function() {
	/* 获取目录信息 */
	var categoryList = null;
	categoryList = getCategorys();
	/* 生成目录模板 */
	var startString = "<ul class=\"nav nav-pills\">";
	var middleString = "";
	var endString = "</ul>";
	for ( var index in categoryList) {
		middleString += "<li class=\"category\" id=\"category"+categoryList[index].id+"\"><a onclick=\"setArticlesPage("
				+ categoryList[index].id + ")\">" + categoryList[index].name
				+ "</a></li>";
	}
	/* 绘制目录模板 */
	$("#categoryArea").html(startString + middleString + endString);
}
/* 点击目录时调用该函数：作用是生成该目录的分页模板，并调用生成帖子模板的函数 */
var setArticlesPage = function(categoryId) {
	var size = 10;
	/* 获取文章数量 */
	var articleCount = 0;
	articleCount = getArticlesCount(categoryId);
	/* 计算分页数量 */
	var pageSize = 0;
	pageSize = (articleCount - 1) / size + 1;
	/* 生成分页模板 */
	var startString = "<nav aria-label=\"Page navigation\"><ul class=\"pagination\">";
	var middleString = "";
	var endString = "</ul></nav>";
	for (index = 1; index <= pageSize; index++) {
		middleString += "<li><a onclick=\"setArticles(" + categoryId + ","
				+ size + "," + index + ")\">" + index + "</a></li>";
	}
	/* 绘制分页模板 */
	$("#pageArea").html(startString + middleString + endString);
	$(".category").removeClass("active");
	$("#category"+categoryId).addClass("active");
	/* 其他 */
	setArticles(categoryId, size, 1);
}
/* 点击分页时调用该函数：作用是生成帖子模板 */
var setArticles = function(categoryId, size, page) {
	/* 获取文章信息 */
	var articleList = null;
	articleList = getArticles(categoryId, size, page);
	/* 生成文章模板 */
	var startString = "<div class=\"panel panel-info\">";
	var middleString = "";
	var endString = "</div>";
	for ( var index in articleList) {
		var time=timeFormat(articleList[index].create_time);
		middleString += "<div class=\"panel-heading\">"
				+ "<a class=\"panel-title\" href=\"/article?articleId="
				+ articleList[index].id
				+ "\"><h4>"
				+ articleList[index].title
				+ "</h4></a>"
				+ "</div>"
				+ "<div class=\"panel-body\"><p class=\"panel-title\">"
				+ articleList[index].content_synopsis
				+ "</p></div>"
				+ "<div class=\"panel-footer\"><a class=\"panel-title\" href=\"/user?userId="
				+ articleList[index].user_id + "\">发帖者："
				+ articleList[index].user_nickname
				+ "</a>&nbsp;&nbsp;&nbsp;&nbsp;发帖时间：" +time+ "</div>";
	}
	/* 绘制文章模板 */
	$("#articleArea").html(startString + middleString + endString);
}
