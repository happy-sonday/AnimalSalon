
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
		if($(window).scrollTop()==$(document).height()-$(window).height()&& pageNum<=pageMax && searchKind=="default"){
			search_ajax_list();
		}else if($(window).scrollTop()==$(document).height()-$(window).height()&& pageNum<=pageMax && searchKind=="filter"){			
			search_filter_ajax_list();									
		}
    });      
	var userLocalX;
	var userLocalY;
	var makerX=[];
	var makerY=[];
	var makerName=[];
	var filter ;
	var positions=[];
	var marker = new kakao.maps.Marker();
    
	// geolocation api 를 실행한다.
	navigator.geolocation.getCurrentPosition (success,error);
	// 접속시 위치 정보를 허용 위치 정보를 저장 한다
	function success(pos){
			userLocalX=(pos.coords.latitude);
			userLocalY=(pos.coords.longitude);
			$('#userLocalX').val(pos.coords.latitude);
			$('#userLocalY').val(pos.coords.longitude);
			get_page_num();
			search_ajax_list();
			searchAddrFromCoords();	
	}
	// 접속시 위치 정보 거부 할시 기본 정보로 저장 한다.
    function error(pos) {
		userLocalX=37.566826;
		userLocalY=126.9786567;
		$('#userLocalX').val(37.566826);
		$('#userLocalY').val(126.9786567);
		get_page_num();
		search_ajax_list();
		searchAddrFromCoords();	
    }			
	//도로명 주소창 열기
	$('#location_change').click(function () {	
	 new daum.Postcode({
        oncomplete: function(data) {
        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var roadAddr = data.roadAddress; // 도로명 주소 변수
        var extraRoadAddr = ''; // 참고 항목 변수

        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            extraRoadAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraRoadAddr !== ''){
            extraRoadAddr = ' (' + extraRoadAddr + ')';
        }
        // 우편번호와 주소 정보를 해당 필드에 넣는다.
		$('.location__select').text(roadAddr);   
		searchCoordsFromAddr(); 
        }
        }).open();		
	});
	// 주소로 좌표를 얻어내기
	function searchCoordsFromAddr(){
			
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch($('.location__select').text(), function(result, status) {
    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {
	console.log(result[0].y+"::::"+result[0].x)
			userLocalX=(result[0].y);
			userLocalY=(result[0].x);
			$('#userLocalX').val(result[0].y);
			$('#userLocalY').val(result[0].x);
			maker_remove();
			get_page_num();
			pageNum=1;
			$("#shops *").remove();
			search_ajax_list();	
	}
	});
	}	 
	// 현재 좌표를 통한 내주소 얻기
	function searchAddrFromCoords() {
	var geocoder = new kakao.maps.services.Geocoder();
	var coord = new kakao.maps.LatLng($('#userLocalX').val(), $('#userLocalY').val());
	var callback = function(result, status) {
    if (status == kakao.maps.services.Status.OK) {
        $('.location__select').text(result[0].address.address_name);
    }
	};
	geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);       
	}
	//필터 검색 기능 사용시 내위치 정보를 얻기
	function search_ajax_filter_location(){
    
        navigator.geolocation.getCurrentPosition (function(pos) {
			$('#userLocalX').val(pos.coords.latitude);
			$('#userLocalY').val(pos.coords.longitude);
			});

	}
	// 기본 검색 MAX page 얻기
	function get_page_num(){
		
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/shop/getPage",
			async : true,       	        
	        success : function(data) {
				pageMax=data+1;	        	
	        },
			error : function(data, status){
				//console.log("Error:"+data+":"+status);
			}
		});	
	}
	// filter 검색 MAX page 얻기
	function get_filter_page_num(){
		filter = $('#searchFilter').serialize();
		$("#shops *").remove();
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/shop/getFilterPage",
			async : true,       	 
	   		contentType : "application/json",
	    	data :  filter ,       
	        success : function(data) {
				pageMax=data+1;	
				searchKind="filter";
				pageNum=1;
				search_filter_ajax_list();       	
	        },
			error : function(data, status){
				//console.log("Error:"+data+":"+status);
			}
		});	
	}
	// 기본검색 기능
	function search_ajax_list(){
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/shop/shopmain_list",
	    	contentType : "application/json",
			async : true,
	        data : {'userLocalX' : userLocalX , 'userLocalY' : userLocalY,'pageNum' : pageNum},
			       	        
	        success : function(data) {
				var idx=(pageNum-1)*10-1;
	        	$.each(data,function(index,list){
					$("#shops").append(					
						"<div class='shop_info_overview'>"
						+"<a href='/cndsalon/shop/shopdetail?sCode="+list.scode+"' >"
						+"<div class='shop_info_overview_image'><img src=\"/cndsalon/shop/upload_image/"+list.sphotopath+list.sphotoname+"\" width=100 height=100 /></div>"
						+"<div class='shop_info_overview_name'><p>"+list.sname+"</p><p>별점 : "
						+list.savgScore+"</p><p>거리 : "+list.slocale+"Km</p><p>운영시간 : "
						+list.stime+"<p id='shop_option"+idx+"'></p></div></a></div>"			
					)
					list.sparking==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>주차가능 </p>") : $('#shop_option'+idx).append("")
					list.swifi==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>Wifi </p>") : $('#shop_option'+idx).append("")
					list.ssubway==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>지하철역근처 </p>") : $('#shop_option'+idx).append("")
					list.scharge==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>추가요금없음 </p>") : $('#shop_option'+idx).append("")
					list.spickup==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>픽업가능 </p>") : $('#shop_option'+idx).append("")
					list.sbigdog==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>대형견가능 </p>") : $('#shop_option'+idx).append("")
					makerX.push(list.sgpsX);
					makerY.push(list.sgpsY);
					makerName.push(list.sname);		
					idx++
	        	})
				map_load(makerX,makerY,makerName);
				pageNum++;
	        },
			error : function(data, status){
				//console.log("Error:"+data+":"+status);
			}
			
		});	
	}
	
	// form의 button 클릭 시 수행
	$('#searchButton').click(function (){
		// 기존 마커를 지우는 기능 수행
		maker_remove();
	var form_result = 0;
		$('.filter_value_sum').each(function(){
			form_result+=Number($(this).val());
		});

	// 검색 조건 미선택 시 alert 발생
	if(form_result>0){
		get_filter_page_num();
		$('html, body').animate({scrollTop : 0},'slow');
	}else{
		alert("검색조건이 선택 되지 않았습니다.")
	}
	
	});
	//필터 검색 기능
	function search_filter_ajax_list(){
	
	filter = $('#searchFilter').serialize()+"&pageNum="+pageNum;
	console.log(pageNum+"페이지 번호를 찍는다")
	$.ajax({
	   	type : "GET",
	   	url : "/cndsalon/shop/shopmain_search",
	   	contentType : "application/json",
		async : true,
	    data :  filter ,
	    success : function(data) {
			search_ajax_filter_location();
			var idx=(pageNum-1)*10-1;
	       	$.each(data,function(index,list){
					$("#shops").append(
						"<div class='shop_info_overview'>"
						+"<a href='/cndsalon/shop/shopdetail?sCode="+list.scode+"' >"
						+"<div class='shop_info_overview_image'><img src=\"/cndsalon/shop/upload_image/"+list.sphotopath+list.sphotoname+"\" width=100 height=100 /></div>"
						+"<div class='shop_info_overview_name'><p>"
						+list.sname+"</p><p>별점 : "
						+list.savgScore+"</p><p>거리 : "
						+list.slocale+"Km</p><p>운영시간 : "
						+list.stime+"<p id='shop_option"+idx+"'></p></div></a></div>"			
					
					)
					list.sparking==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>주차가능 </p>") : $('#shop_option'+idx).append("")
					list.swifi==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>Wifi </p>") : $('#shop_option'+idx).append("")
					list.ssubway==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>지하철역근처 </p>") : $('#shop_option'+idx).append("")
					list.scharge==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>추가요금없음 </p>") : $('#shop_option'+idx).append("")
					list.spickup==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>픽업가능 </p>") : $('#shop_option'+idx).append("")
					list.sbigdog==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_option_style'>대형견가능 </p>") : $('#shop_option'+idx).append("")
					list.pcat==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>고양이 </p>") : $('#shop_option'+idx).append("")
					list.pshort==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>단모 </p>") : $('#shop_option'+idx).append("")
					list.plong==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>장모 </p>") : $('#shop_option'+idx).append("")
					list.pdog==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>강아지 </p>") : $('#shop_option'+idx).append("")
					list.psmall==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>소형견 </p>") : $('#shop_option'+idx).append("")
					list.pmedium==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>중형견 </p>") : $('#shop_option'+idx).append("")
					list.plarge==true ? $('#shop_option'+idx).append("<p class='shop_info_overview_product_style'>대형견 </p>") : $('#shop_option'+idx).append("")
					
				makerX.push(list.sgpsX);
				makerY.push(list.sgpsY);
				makerName.push(list.sname);	
				idx++				
	        	})
				
				map_load(makerX,makerY,makerName);
				pageNum++
	        },
			error : function(data, status){
				//console.log("Error:"+data+":"+status);
			}			
		});		
	}
	// 기존 마커를 비운다	
	function maker_remove(){
		var mapContainer = document.getElementById('map');
		positions=[];
		marker ="";
		makerName=[];
		makerX=[];
		makerY=[];
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
		    marker = new kakao.maps.Marker({map: map, // 마커를 표시할 지도
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
	// 지도에 마커를 생성한다.
	function map_load(makerX,makerY,makerName){
		var mapContainer = document.getElementById('map');
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
		    marker = new kakao.maps.Marker({map: map, // 마커를 표시할 지도
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
