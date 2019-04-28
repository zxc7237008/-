var url = window.location.href;
$("li:has(ul)").each(function(i,e){
	$(this).prop("class","w");
});
$("li a").each(function(i, e){
	
	var thisUrl = $(this).prop("href");
	if(thisUrl == url){
		$(this).addClass("active-menu");
		
		var li = $(this).parent().parent().parent();
		
		if(li.prop("class") == "w"){
			li.prop("class","active");
		}
	}
	
	
});

/* 未处理信息查询 */
$.getJSON("/findUNInformation",{eventState:"1"},function(data){
	var value = $("#UNInformation").text(data);
});