/**
 * booking에서 사용하는 스크립트 파일
 */


/** DatePicker Setting */
$(document).ready(function() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1; // 1~12가 아니라 0~11로 반환되므로 +1
	var day = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var today = year + "-" + month + "-" + day;

	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],	//한글 캘린더 요일 표시 부분
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	//한글 요일 표시 부분
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	// 한글 요일 표시 부분
		showMonthAfterYear: true,	// true : 년 월  false : 월 년 순으로 보여줌
		yearSuffix: '년',
	});

	$('#bookingDate').datepicker({
		dateFormat: 'yy-mm-dd(DD)',
		maxDate: '+2m',
		minDate: '0',
		
		/** 날짜 선택 시 발생 이벤트 */
		onSelect: function(dateText){
		 getDate = dateText;
		 selected_day = getDate.substring(11, 12);
		 setting_work_time(getDate, selected_day)
		}
		
	// 오늘날짜 세팅 및 예약은 2달 뒤까지만 가능
	}).datepicker('setDate', "0"); 
	
	
	// 즉시발생 함수
	setting_work_time();
	
	/**
	* document ready
	**/
});


/** 뒤로가기 */
function goBack() {
	window.history.back();
}


/** 금액 및 소요시간 설정 */
var change_sum_price = function(selected_option) {
	// 금액 및 소요시간 초기화
	$('#sumPrice').text($('#defaultPrice').val());
	$('#sumBeautyTime').text($('#defaultBeautyTime').val());

	var mo_index = selected_option.selectedIndex; // 인덱스
	var mo_text = selected_option.options[mo_index].text; // 해당 인덱스의 text
	var mo_value = selected_option.options[mo_index].value; // 해당 인덱스의 value
	var mo_beauty_time = mo_value.substring(0, mo_value.lastIndexOf(":"))*1; // value의 앞 (beauty_time)
	var mo_price = mo_value.substring(mo_value.lastIndexOf(":") + 1)*1; // value의 뒤 (price)

	let sum_price = $('#sumPrice').text() * 1;
	let sum_beauty_time = $('#sumBeautyTime').text() * 1;

	var price = sum_price + mo_price;
	var btime = sum_beauty_time + mo_beauty_time;

	$('#sumPrice').text(price+"원");
	$('#sumBeautyTime').text(btime+"분");
};


/** 디자이너 휴무 요일과 날짜의 요일 비교*/
	(setting_work_time = function(getDate, selected_day) {
	
	if(getDate == null){
		getDate = $.datepicker.formatDate('yy-mm-dd(DD)', $('#bookingDate').datepicker('getDate'));
	}
	if(selected_day == null) {
		selected_day = getDate.substring(11, 12);
	} 
	
	var d_length = $('input[name=dDayOff]').length;
	var d_day_off = new Array(d_length);
	var regular_holiday_tag = "<p>정기휴무</p>";
	var sTime = $('#sTime').val();
	
	for(var i=0; i<d_length; i++){
		d_day_off[i] = $('input[name=dDayOff]')[i].value.trim();
		
		if(selected_day == d_day_off[i]) { // 정기휴무
			$('#designer_work_time'+i).empty();
			$('#designer_work_time'+i).append(regular_holiday_tag);
			
		} else { 						   // 예약시간
			$('#designer_work_time'+i).empty();
			
			$.ajax({
			
				contentType : 'application/json; charset=UTF-8',
				type : 'GET',
				url : '/cndsalon/booking/create-work-time',
				data : {'sTime':sTime,
						'getDate':getDate},
				async : false,
				success : function(timeMap) {
					var w_list = timeMap.workTime;
					var n_list = timeMap.notWorkTime;
					
					$.each(w_list, function(index, wlist){
						$('#designer_work_time'+i).append(
							"<label><input type='radio' value='" + wlist.workTime + "' name='time_radio' />"
							+ wlist.workTime + "</label>");
						if(n_list != null){
							$.each(n_list, function(index, nlist){
								$("input[value='"+ nlist.notWorkTime + "']").attr('disabled', true);
							})	
						}
						
					})
				},
				error: function(jqXHR, textStatu) {
					alert("failed to communicate : " + textStatu);
				}
			});
			
		}
	} 
	
});




/** bookingDetail.html 예약을 위한 유효성 검사 */



