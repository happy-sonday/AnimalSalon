
// 접속시 위치 정보를 허용 하면 hidden 값에 위치 정보를 저장 한다
$(function () {        
	var userLocalX;
	var userLocalY;
    // Geolocation API에 액세스할 수 있는지를 확인
    if (navigator.geolocation) {
        //위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
            //$('#latitude_my_x').val(pos.coords.latitude);// 위도
            //$('#latitude_my_y').val(pos.coords.longitude);// 경도
			userLocalX=(pos.coords.latitude);
			userLocalY=(pos.coords.longitude);
			search_ajax_list();
			});
    } else {// 지원 불가   	

	    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
			//userLocalX=("37.62843");
			//userLocalY=("127.07184");
			//search_ajax_list();
	function search_ajax_list(){
		 //console.log(userLocalX+":::"+userLocalY);
	
		 $.ajax({
	    	type : "GET",
	    	url : "/cndsalon/getAll_ajax_list",
	    	contentType : "application/json",
			async : true,
	        data : {'userLocalX' : userLocalX , 'userLocalY' : userLocalY},
			       	        
	        success : function(data) {
				//console.log("data : "+data);
			var makerX=[];
			var makerY=[];
			var makerName=[];
	        	$.each(data,function(index,list){
	        		$('#resultlist').append("<tr><td>"+list.scode+"</td>"
					+"<td>"+list.sname+"</td>"
					//+"<td>"+list.saddr+"</td>"
					+"<td>"+list.stime+"</td>"
					+"<td>"+list.sparking+"</td>"
					+"<td>"+"<img src='"+list.sphotopath+list.sphotoname+"'/>"+"</td>"
					+"<td>"+list.savgScore+"</td>"
					+"<td>"+list.slocale+"</td></tr>"
					
					);
					
					makerX.push(list.sgpsX);
					makerY.push(list.sgpsY);
					makerName.push(list.sname);
					
	        	})
				map_load(makerX,makerY,makerName);
	        },
			error : function(data, status){
				console.log("Error:"+data+":"+status);
			}
			
		});	
	}
	function map_load(makerX,makerY,makerName){
		var mapContainer = document.getElementById('map');
		console.log("map_load -- start"+ makerName);
		var positions=[];
		for (a=0;a<makerName.length;a++){
			console.log(a+":::"+makerX[a]+"::"+makerY[a]+":::"+makerName[a]+":::"+makerName.length)
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
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
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
