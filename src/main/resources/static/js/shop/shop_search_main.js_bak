var mapContainer = document.getElementById('map');
	var mapOption = {
		center: new kakao.maps.LatLng(37.507586813960295, 127.02682191743735),
		level: 3
	};
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
		    {
		        content: '<div>논현애완미용</div>', 
		        latlng: new kakao.maps.LatLng(37.507586813960295, 127.02682191743735)
		    },
		    {
		        content: '<div>커비애완미용</div>', 
		        latlng: new kakao.maps.LatLng(37.50887075345535, 127.02678844593346)
		    },
		    {
		        content: '<div>포이애완미용</div>', 
		        latlng: new kakao.maps.LatLng(37.47887431342842, 127.04727510827242)
		    },
		    {
		        content: '<div>나은애완미용</div>',
		        latlng: new kakao.maps.LatLng(37.47913485668283, 127.04911246750096)
		    }
		];

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

/*
 init : function () {
var _this = this;//함수의 변수화    

$('#btn-save').click(function () {
           
           _this.save();//변수 선언
           
          var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
          };
    });//$('#btn-save').click(function ()END

 save : function () {  	

        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
           
            //url: 'http://localhost:8181/cndsalon/api/v1/posts',
            url: contextPath+'/api/v1/posts',
            
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
            
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = contextPath;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }//save:function()END
};//init:function()END
*/