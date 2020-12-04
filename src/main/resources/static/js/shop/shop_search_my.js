
// 접속시 위치 정보를 허용 하면 hidden 값에 위치 정보를 저장 한다
$(function () {        
    // Geolocation API에 액세스할 수 있는지를 확인
    if (navigator.geolocation) {
        //위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
            //$('#latitude').html(pos.coords.latitude);     // 위도
            $('#latitude_my_x').val(pos.coords.latitude);
            //$('#longitude').html(pos.coords.longitude); // 경도
            $('#latitude_my_y').val(pos.coords.longitude);
        });
    } else {// 거부 시 	
	    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
});
// 버튼 클릭 시 내위치 정보를 넘겨 준다
function shop_search(){
	var url = "/cndsalon/getAll?";
	var latitude_my_x = $('#latitude_my_x').val();
	var latitude_my_y = $('#latitude_my_y').val();
	
		location.href= url+"userLocalX"+"="+latitude_my_x+
		"&"+"userLocalY"+"="+latitude_my_y;		
	
	
}
