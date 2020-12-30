/**
 * booking에서 사용하는 스크립트 파일
 */


/** DatePicker Setting */
$(document).ready(function() {

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
		onSelect: function(dateText) {
			getDate = dateText;
			selected_day = getDate.substring(11, 12);
			setting_work_time(getDate, selected_day)
		}

		// 오늘날짜 세팅 및 예약은 2달 뒤까지만 가능
	}).datepicker('setDate', "0");


	// 즉시발생 함수
	setting_work_time();
	
});


/** 뒤로가기 */
function goBack() {
	window.history.back();
}

/** 금액 및 소요시간 설정 */
var change_sum_price = function(selected_option) {
	// 금액 및 소요시간 초기화
	$('#sumPrice').text($('#selected_price').val());
	$('#sumBeautyTime').text($('#selected_btime').val());

	var mo_index = selected_option.selectedIndex; // 인덱스
	var mo_value = selected_option.options[mo_index].value; // 해당 인덱스의 value
	var mo_beauty_time = mo_value.substring(0, mo_value.lastIndexOf(":")) * 1; // value의 앞 (beauty_time)
	var mo_price = mo_value.substring(mo_value.lastIndexOf(":") + 1) * 1; // value의 뒤 (price)

	var price = $('#defaultPrice').val()*1 + mo_price;
	var btime = $('#defaultBeautyTime').val()*1 + mo_beauty_time;

	$('#sumPrice').text(price + "원");
	$('#sumBeautyTime').text(btime + "분");
	$('#selected_price').val(price);
	$('#selected_btime').val(btime);
	
	$("input[name='time_radio']").prop("checked", false);
};


/** 디자이너 휴무 요일과 날짜의 요일 비교*/
(setting_work_time = function(getDate, selected_day) {

	if (getDate == null) {
		getDate = $.datepicker.formatDate('yy-mm-dd(DD)', $('#bookingDate').datepicker('getDate'));
	}
	if (selected_day == null) {
		selected_day = getDate.substring(11, 12);
	}
	
	var selected_d = getDate.substring(0, getDate.indexOf("("));
	$('#selected_date').val(selected_d);

	var d_length = $('input[name=dDayOff]').length;
	var d_day_off = new Array(d_length);
	var regular_holiday_tag = "<p>정기휴무</p>";
	var sTime = $('#sTime').val();
	var sCode = $('#sCode').val();
	
	// 시간 변경하는 경우. 사용하는 bCode (시간변경이 아닐 경우 0값으로 초기화)
	var bCode = $('#bCode').val();
	if(bCode == null){
		bCode = 0;
		console.log("ifbCodenull : " + bCode)
	}
	
	for (var i = 0; i < d_length; i++) {
		d_day_off[i] = $('input[name=dDayOff]')[i].value.trim();

		if (selected_day == d_day_off[i]) { // 정기휴무
			$('#bookable_time' + i).empty();
			$('#bookable_time' + i).append(regular_holiday_tag);

		} else { 						   // 예약시간
			$('#bookable_time' + i).empty();

			var dCode = $('#dCode' + i).val();

			$.ajax({

				contentType: 'application/json; charset=UTF-8',
				type: 'GET',
				url: '/cndsalon/booking/create-work-time',
				data: {
					'sTime': sTime,
					'getDate': getDate,
					'sCode': sCode,
					'dCode': dCode,
					'bCode': bCode
				},
				async: false,
				success: function(timeMap) {
					var w_list = timeMap.workTime;
					var n_list = timeMap.notWorkTime;
					var d_list = timeMap.degWorkTime;

					$.each(w_list, function(id, wlist) {
						$('#bookable_time' + i).append(

							"<input type='radio' value='" + wlist.workTime + "' name='time_radio' id='" + id+i
							+ "' onclick= 'check_time(this)'/><label for='" + id+i +"'>"
							+ wlist.workTime + "</label>");

					})
					if (n_list != null) {
						$.each(n_list, function(index, nlist) {
							$("input[value='" + nlist.notWorkTime + "']").attr('disabled', true);
						})
					}
					if (d_list != null) {
						for (var v = 0; v < d_list.length; v++) {
							$('#bookable_time' + i).
								find("input[value='" + d_list[v].designerWorkTime + "']")
								.attr('disabled', true);
						}
					}
				},
				error: function(jqXHR, textStatu) {
					alert("failed to communicate : " + textStatu);
				}
			});

		}
	}

});

/** 예약되어있는 시간과 예약하려는 시간의 겹치는지 확인 유무 */
var check_time = function(selected_time) {
	selected_timeV = selected_time.value;
	
	if ($('#optionList option:selected').val() == "x") {
		$("input[name='time_radio']").prop("checked", false);
		alert('옵션을 선택해주세요.');
	} else {
		var selectedDesigner = selected_time.parentElement.parentElement.childNodes[1].childNodes[1].value;
		$('#selected_designer').val(selectedDesigner);
		var shop_name = $('#sName').val();
		var shop_phone = $('#sPhone').val();
		var sum_b = $('#sumBeautyTime').text();
		sum_b = sum_b.substring(0, sum_b.indexOf("분")) * 1;
		
		// bookingTimeChange.html에서 sum_b 초기화 하는 부분
		if($('#sumBeautyTime').text()===""){
			sum_b = $('#sumBeautyTime').val();
		}
		
		var timeRadio = selected_time.parentElement.getElementsByTagName('input');
		var disabled_test = new Array();
		var xTimeList = new Array();

		for (var i = 0; i < timeRadio.length; i++) {
			
			disabled_test.push(timeRadio[i].getAttribute('disabled'));
			if (disabled_test[i] != null) {
				xTimeList.push(timeRadio[i].value);
			}
		}
		if (!xTimeList.length) { // 비활성화 버튼이 없을 경우 함수 탈출
			return;
		}

		$.ajax({
			contentType: 'application/json',
			type: 'GET',
			url: '/cndsalon/booking/check-available-time',
			data: {
				'sumB': sum_b,
				'selectedTime': selected_timeV,
				'xTimeList': xTimeList
			},
			success: function(status) {
				if (status == false) { // 겹쳤을 시 샵 정보 및 버튼 초기화.
					$("input[name='time_radio']").prop("checked", false);
					alert("예약하시려는 시간의 예상 소요시간과 이미 예약되어 있는 시간이 겹쳐 있으므로 " +
						"다른 시간을 선택하여 주시거나, \n" +
						"선택하신 시간의 예약 가능 유무는 업체로 연락하시기 바랍니다. \n\n" +
						"업체명 : " + shop_name + " / 연락처 : " + shop_phone);
				} else { // 겹치지 않을 시 예약 가능

				}
			},
			error: function(jqXHR, textStatu) {
				alert("failed to communicate : " + textStatu);
			}
		});
	}
}

/** bookingDetail.html에서 결제화면으로 넘어가기 위한 유효성 검사 */
var move_payments = function() {

	
	var b_time = $('input[name="time_radio"]:checked').val();

	if (!b_time) {
		if ($('#optionList option:selected').val() == "x") {
			alert('옵션을 선택해주세요.');
			return;
		} else {
			alert('시간을 선택해주세요.')
			return;
		}
	} else {
		if ($('#mType').val() == "고양이") {
			$('#selected_price').val() == $('#defaultPrice').val();
			$('#selected_btime').val() == $('#defaultBeautyTime').val();
		}
		document.formm.action="/cndsalon/payments";
		document.formm.submit();	
	}

}

/** bookingMenu.html 메뉴타입 선택 시 해당 메뉴 조회 **/
var choice_type = function(selected_type){
	var menu_type = selected_type.value;
	location.href = "/cndsalon/booking/shop/" + $('#sCode').val() + "/type/" + menu_type;
}

