var url = window.location.pathname;
$("li:has(ul)").each(function(i,e){
	$(this).prop("class","w");
});
$("li a").each(function(i, e){
	
	var thisUrl = $(this).prop("href");
	if(thisUrl.indexOf(url) != -1){
		$(this).addClass("active-menu");
		
		var li = $(this).parent().parent().parent();
		
		if(li.prop("class") == "w"){
			li.prop("class","active");
			li.children().eq(1).addClass("in");
			li.children().eq(1).height("80px");
		}
	}
	
	
});

/* 未处理信息查询 */
$.getJSON("/findUNInformation",{eventState:"1"},function(data){
	var value = $("#UNInformation").text(data);
});

$.getJSON("/getUserList",function(data){
	$.each(data,function(i,e){
		$("#username").text(e.userName);
		return false;
	});
});
