// review title 클릭시 해당 review image불러오기 및 content show
function review_detail($rcode,$ridx){
		var rCode=$rcode;
		var rIdx=$ridx;
		//console.log(rCode+"::::"+rIdx+":::"+$("#reviewstatus"+rIdx).val());
		
		if ($("#testreview_content"+rIdx).css("display")=="none" && $("#reviewstatus"+rIdx).val()==0){
		$.ajax({
	   	type : "GET",
	   	url : "/cndsalon/shop/getReviewDetail",
	   	contentType : "application/json; charset=UTF-8",
		
		async : true,
	    //data : JSON.stringify({'rCode':rCode}),
		data : {'rCode':rCode},
	    success : function(data) {
			
	       	$.each(data,function(index,list){
					
					$("#testreview_image"+rIdx).append(
						"<td><img src=\"/cndsalon/shop/upload_image/"+list.rPhotopath+list.rPhotoname+"\" width=100 height=100 /></td>"									
					)
					$("#reviewstatus"+rIdx).val('2')
					$("#testreview_image"+rIdx).css({display:'inline'})
					$("#testreview_content"+rIdx).css({display:'inline'})
					//console.log(list.rPhotopath+list.rPhotoname)
					
	        	})
			
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}
						
		})
		}else if($("#testreview_content"+rIdx).css("display")=="inline" && $("#reviewstatus"+rIdx).val()==2){
			$("#testreview_image"+rIdx).css({display:'none'})
			$("#testreview_content"+rIdx).css({display:'none'})
			$("#reviewstatus"+rIdx).val('1')
		}else if($("#testreview_content"+rIdx).css("display")=="none" && $("#reviewstatus"+rIdx).val()==1){
			$("#reviewstatus"+rIdx).val('2')
			$("#testreview_image"+rIdx).css({display:'inline'})
			$("#testreview_content"+rIdx).css({display:'inline'})
		}
		;
		
			
}
