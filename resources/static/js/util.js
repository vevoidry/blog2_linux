var timeFormat=function(time){
	var time1 = "" + time;
	var time2 = time1.slice(0, 19);
	var time3 = time2.replace("T", " ");
	return time3;
}