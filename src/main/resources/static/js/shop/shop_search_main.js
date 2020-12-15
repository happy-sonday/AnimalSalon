
//매장 부가옵션 button action
function filterClick($inSearch, inSearchVar){
		var inSearch = $inSearch;
		var inSearchVar = inSearchVar;
	if (inSearch == 1){
	$("#"+inSearchVar).val('0')
	$("."+inSearchVar).css({background : '#fff2d8', color : '#000000'});	
	}else{
	$("#"+inSearchVar).val('1').css('background-color','green');	
	$("."+inSearchVar).css({background : '#2EFE9A', color : '#000000'});
	}
}

//cat button action(중분류 비활성화기능)
function filterClick_cat_menu($inSearch, inSearchVar){
		var inSearch = $inSearch;
		var inSearchVar = inSearchVar;
	if (inSearch == 1 && inSearchVar=="pCat"){
	$("#"+inSearchVar).val('0')
	$("."+inSearchVar).css({background : '#fff2d8', color : '#000000'});
	$('.pShort').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pShort').val('0');
	$('.pLong').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pLong').val('0');
	}else if(inSearch == 0 && inSearchVar=="pCat"){
	$("#"+inSearchVar).val('1').css('background-color','green');	
	$("."+inSearchVar).css({background : '#2EFE9A', color : '#000000'});
	$('.pShort').show();
	$('.pLong').show();
	$('.pDog').css({background : '#fff2d8', color : '#000000'});
	$('#pDog').val('0');
	$('.pSmall').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pSmall').val('0');
	$('.pMedium').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pMedium').val('0');
	$('.pLarge').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pLarge').val('0');
	}
}
//dog button action(중분류 비활성화기능)
function filterClick_dog_menu($inSearch, inSearchVar){
		var inSearch = $inSearch;
		var inSearchVar = inSearchVar;
	if (inSearch == 1 && inSearchVar=="pDog"){
	$("#"+inSearchVar).val('0')
	$("."+inSearchVar).css({background : '#fff2d8', color : '#000000'});
	$('.pSmall').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pSmall').val('0');
	$('.pMedium').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pMedium').val('0');
	$('.pLarge').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pLarge').val('0');
	}else if(inSearch == 0 && inSearchVar=="pDog"){
	$("#"+inSearchVar).val('1').css('background-color','green');	
	$("."+inSearchVar).css({background : '#2EFE9A', color : '#000000'});
	$('.pCat').css({background : '#fff2d8', color : '#000000'});
	$('#pCat').val('0');
	$('.pShort').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pShort').val('0');
	$('.pLong').hide().css({background : '#fff2d8', color : '#000000'});
	$('#pLong').val('0');
	$('.pSmall').show();
	$('.pMedium').show();
	$('.pLarge').show();
	}
}
//scroll 위치가 마지막을 때 비동기식 리스트 불러오기
$(function () {
	$('.pShort').hide();
	$('.pLong').hide();
	$('.pSmall').hide();
	$('.pMedium').hide();
	$('.pLarge').hide();
	var pageNum = 1;
	var pageMax ;
	var searchKind="default";
	$(window).scroll(function() {
 		var scrollTop = $(this).scrollTop();
        var outerHeight = $(this).outerHeight();
        var scrollHeight = $(this).prop('scrollHeight');
		
    	//if(scrollTop+outerHeight==scrollHeight && pageNum<=pageMax && searchKind=="default"){
		if($(window).scrollTop()==$(document).height()-$(window).height()&& pageNum<=pageMax && searchKind=="default"){
			console.log("default Document end")
			console.log(pageNum+"::::"+pageMax)
			search_ajax_list();
		}else if($(window).scrollTop()==$(document).height()-$(window).height()&& pageNum<=pageMax && searchKind=="filter"){			
			console.log("filter Document end")
			console.log(pageNum+"::::"+pageMax)
			search_filter_ajax_list();		
							
		}
        
    });      
	
	var userLocalX;
	var userLocalY;
	var makerX=[];
	var makerY=[];
	var makerName=[];
	var filter ;
    // Geolocation API에 액세스할 수 있는지를 확인
	// 접속시 위치 정보를 허용 위치 정보를 저장 한다
    if (navigator.geolocation) {
        //위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
			userLocalX=(pos.coords.latitude);
			userLocalY=(pos.coords.longitude);
			$('#userLocalX').val(pos.coords.latitude);
			$('#userLocalY').val(pos.coords.longitude);
			get_page_num();
			search_ajax_list();
			});
    } else {// 지원 불가   	

	    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
	//필터 검색 기능 사용시 내위치 정보를 얻기
	function search_ajax_filter_location(){
    // Geolocation API에 액세스할 수 있는지를 확인
    if (navigator.geolocation) {
        //위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
			$('#userLocalX').val(pos.coords.latitude);
			$('#userLocalY').val(pos.coords.longitude);
			});
    } else {// 지원 불가   	

	    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
	}
	// 기본 검색 MAX page 얻기
	function get_page_num(){
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/getPage",
			async : true,       	        
	        success : function(data) {
				pageMax=data+1;	
				console.log(pageMax);        	
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}
		});	
	}
	// filter 검색 MAX page 얻기
	function get_filter_page_num(){
		filter = $('#searchFilter').serialize();
		$("#shops *").remove();
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/getFilterPage",
			async : true,       	 
	   		contentType : "application/json",
	    	data :  filter ,       
	        success : function(data) {
				pageMax=data+1;	
				console.log(pageMax); 
				searchKind="filter";
				console.log(searchKind+"검색버튼 내부 search page num"+pageNum)
				pageNum=1;
				console.log(pageNum)
				search_filter_ajax_list();       	
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}
		});	
	}
	// 기본검색 기능
	function search_ajax_list(){
		console.log("search_ajax_list 시작"+pageNum);
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/shopmain_list",
	    	contentType : "application/json",
			async : true,
	        data : {'userLocalX' : userLocalX , 'userLocalY' : userLocalY,'pageNum' : pageNum},
			       	        
	        success : function(data) {
		
	        	$.each(data,function(index,list){
					$("#shops").append(
					
						"<div class='testshop_content' width='400px' height='100px'>"
						+"<a href='/cndsalon/shopdetail?sCode="+list.scode+"' >"
						+"<div width='200px' height='100px'><img src=\"/cndsalon/upload_image/"+list.sphotopath+list.sphotoname+"\" width=100 height=100 /></div>"
						+"<div width='200px' height='100px'><p>"+list.sname+"</p><p>별점 : "+list.savgScore+"</p><p>거리 : "+list.slocale+"M</p><p>운영시간 : "+list.stime+"<p id='tttt"+index+"'></p></div></a></div>"			
					
					)
					list.sparking==true ? $('#tttt'+index).append("주차가능") : $('#tttt').append("")
					list.swifi==true ? $('#tttt'+index).append("Wifi") : $('#tttt').append("")
					list.ssubway==true ? $('#tttt'+index).append("지하철역근처") : $('#tttt').append("")
					list.scharge==true ? $('#tttt'+index).append("추가요금없음") : $('#tttt').append("")
					list.spickup==true ? $('#tttt'+index).append("픽업가능") : $('#tttt').append("")
					list.sbigdog==true ? $('#tttt'+index).append("대형견가능") : $('#tttt').append("")
					makerX.push(list.sgpsX);
					makerY.push(list.sgpsY);
					makerName.push(list.sname);		
	        	})

				map_load(makerX,makerY,makerName);
				pageNum++;
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}
			
		});	
	}
	
	
	$('#searchButton').click(function (){
	console.log("검색버튼 시작")	
	get_filter_page_num();
	$('html, body').animate({scrollTop : 0},'slow');
	
	});
	//필터 검색 기능
	function search_filter_ajax_list(){
	filter = $('#searchFilter').serialize()+"&pageNum="+pageNum;
	console.log(filter)
	//filter.push({name:"pageNum",value:pageNum});
	$.ajax({
	   	type : "GET",
	   	url : "/cndsalon/shopmain_search",
	   	contentType : "application/json",
		async : true,
	    data :  filter ,
	    success : function(data) {
 
			search_ajax_filter_location();
	       	$.each(data,function(index,list){
					$("#shops").append(
					
						"<div class='testshop_content' width='400px' height='100px'>"
						+"<a href='/cndsalon/shopdetail?sCode="+list.scode+"' >"
						+"<div width='200px' height='100px'><img src=\"/cndsalon/upload_image/"+list.sphotopath+list.sphotoname+"\" width=100 height=100 /></div>"
						+"<div width='200px' height='100px'><p>"+list.sname+"</p><p>별점 : "+list.savgScore+"</p><p>거리 : "+list.slocale+"M</p><p>운영시간 : "+list.stime+"<p id='tttt"+index+"'></p></div></a></div>"			
					
					)
					list.sparking==true ? $('#tttt'+index).append("주차가능") : $('#tttt').append("")
					list.swifi==true ? $('#tttt'+index).append("Wifi") : $('#tttt').append("")
					list.ssubway==true ? $('#tttt'+index).append("지하철역근처") : $('#tttt').append("")
					list.scharge==true ? $('#tttt'+index).append("추가요금없음") : $('#tttt').append("")
					list.spickup==true ? $('#tttt'+index).append("픽업가능") : $('#tttt').append("")
					list.sbigdog==true ? $('#tttt'+index).append("대형견가능") : $('#tttt').append("")
					list.pcat==true ? $('#tttt'+index).append("고양이") : $('#tttt').append("")
					list.pshort==true ? $('#tttt'+index).append("단모") : $('#tttt').append("")
					list.plong==true ? $('#tttt'+index).append("장모") : $('#tttt').append("")
					list.pdog==true ? $('#tttt'+index).append("강아지") : $('#tttt').append("")
					list.psmall==true ? $('#tttt'+index).append("소형견") : $('#tttt').append("")
					list.pmedium==true ? $('#tttt'+index).append("중형견") : $('#tttt').append("")
					list.plarge==true ? $('#tttt'+index).append("대형견") : $('#tttt').append("")
					
				makerX.push(list.sgpsX);
				makerY.push(list.sgpsY);
				makerName.push(list.sname);					
	        	})
				pageNum++
				map_load(makerX,makerY,makerName);
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}			
		});		
	}
	
	function map_load(makerX,makerY,makerName){
		 
		var mapContainer = document.getElementById('map');
		//console.log("map_load -- start"+ makerName);
		var positions=[];
		for (a=0;a<makerName.length;a++){
			positions.push({content:'<div>'+makerName[a]+'</div>',
			 latlng: new kakao.maps.LatLng(makerX[a],makerY[a])})
		}
			positions.push({content:'<div>여기에 계신가요?</div>',
			 latlng: new kakao.maps.LatLng(userLocalX,userLocalY)})
		var mapOption = {
			center: new kakao.maps.LatLng(userLocalX, userLocalY),
			level: 6
		};
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			
		for (var i = 0; i < positions.length; i ++) {			
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({map: map, // 마커를 표시할 지도
			position: positions[i].latlng // 마커의 위치
			   });
			
		    // 마커에 표시할 인포윈도우를 생성합니다 
		    var infowindow = new kakao.maps.InfoWindow({
		        content: positions[i].content // 인포윈도우에 표시할 내용
		    });
			
		    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));			
		}
		// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
		function makeOverListener(map, marker, infowindow) {
		    return function() {
		        infowindow.open(map, marker);
		    };
		}
		// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
		function makeOutListener(infowindow) {
		    return function() {
		        infowindow.close();
		    };
		}
		
	}

});
