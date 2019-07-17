
var pageSize = 5;

var projectName = getRootPath();

function delLastStr(str){
	//s2=s2.substring(s2.lastIndexOf('/')+1, s2.lastIndexOf('.'));
	var nStr = str.substring(0,str.length-1);
	return nStr;
}

/** util get format data by date unit. */
function add0(m) {
	return m < 10 ? '0' + m : m;
}
function dateFormat(timestamp){
	var time = new Date(timestamp);
	var year = time.getFullYear();
	var month = time.getMonth() + 1;
	var date = time.getDate();
	
	var hours = time.getHours();
	var minutes = time.getMinutes();
	var seconds = time.getSeconds();
	
	return year + '-' + add0(month) + '-' + add0(date) + ' '
		+ add0(hours) + ':' + add0(minutes) + ":" + add0(seconds);
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

function getTodayFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + "00" + seperator2 + "00"
            + seperator2 + "00";
    return currentdate;
}

function getRootPath() { 
	var pathName = window.location.pathname.substring(1);   
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));   
	return window.location.protocol + '//' + window.location.host + '/'+ webName ;//+ '/';   
} 
