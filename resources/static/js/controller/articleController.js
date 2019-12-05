/*根据categoryId和size，page等参数获取相应的多个article信息*/
var getArticles = function(categoryId, size, page) {
	var articleList = null;
	$.ajax({
		url : "/articles",
		type : "GET",
		data : {
			"categoryId" : categoryId,
			"size" : size,
			"page" : page
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			articleList = result;
		}
	});
	return articleList;
}

var getArticlesCount = function(categoryId) {
	var count = 0;
	$.ajax({
		url : "/articles/count",
		type : "GET",
		data : {
			"categoryId" : categoryId,
		},
		dataType : "JSON",
		async : false,
		success : function(result) {
			count = result;
		}
	});
	return count;
}

var getArticle=function(articleId){
	var article=null
	$.ajax({
		url : "/articles/"+articleId,
		type : "GET",
		dataType : "JSON",
		async : false,
		success : function(result) {
			article = result;
		}
	});
	return article;
}
