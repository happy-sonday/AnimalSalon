/**
 * booking에서 사용하는 스크립트 파일
 */


  // date picker 기본 설정
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
		// 	        showButtonPanel: true,	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션
		// 	        closeText: '닫기', // 닫기 버튼 텍스트 변경
		// 	        currentText: '오늘', // 오늘 텍스트 변경
	});

	$('#booking_date').datepicker({
		maxDate: '+2m',
		minDate: '0'	// 예약가능 날짜는 당일부터 2달 뒤 까지
	});

	$('#booking_date').datepicker("setDate", "today"); // 기본값 세팅

});

  // 뒤로가기
function goBack() {
	window.history.back();
}




