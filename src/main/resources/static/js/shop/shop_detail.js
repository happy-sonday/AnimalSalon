// review title 클릭시 해당 review image불러오기 및 content show
function review_detail($rcode){
		var rCode=$rcode;
		console.log(rCode);
		
		$.ajax({
	   	type : "GET",
	   	url : "/cndsalon/getReviewDetail",
	   	contentType : "application/json",
		async : true,
	    data :  {"rCode":rCode} ,
	    success : function(data) {

	       	$.each(data,function(index,list){
					$("#testreview_image").append(
						"<td><img src=\"/cndsalon/upload_image/"+list.rphotopath+list.rphotoname+"\" width=100 height=100 /></td>"
									
					)
					$("#testreview_image").css({display:'inline'})
					$("#testreview_content").css({display:'inline'})
					console.log(list.rphotopath+list.rphotoname)
	        	})
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}			
		});	
			
}
