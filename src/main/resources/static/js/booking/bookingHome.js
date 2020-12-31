/**
 *  bookingHome에서 사용하는 스크립트 파일
 */

	/** bookingHome.html 내역삭제 클릭 시**/
	var delete_booking_view = function(bCode){
		
		var data = JSON.stringify({
					bStatus : "3"
		});
		
		$.ajax({
			contentType : 'application/json; charset=utf-8',
			type : 'PUT',
			url : '/cndsalon/bookinghome/bookings/' + bCode,
			dataType : 'json',
			data : data,
			beforeSend : function(xhr){
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
			},
			success : function(){
				alert('내역이 삭제되었습니다.');
				location.reload();
			},
			error : function(){
			alert('삭제 불가한 내역입니다.');
			}
		});
		
	}
	
	/** bookingHome.html 시간변경 클릭 시 **/
	var time_change_booking = function(bCode, sCode, bBeautyTime){
		window.open("/cndsalon/bookinghome/timeChangeView.do?bCode=" + bCode + "&sCode=" + sCode + "&bBeautyTime=" + bBeautyTime, "예약시간변경", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=620, height=620");
	}
	
	
	var go_time_change_booking = function(){
		var b_time = $('input[name="time_radio"]:checked').val();
		
		if(!b_time){
			alert('시간을 선택해주세요');
			return;
		} else {
			$('#selected_time').val(b_time);
			
			document.formm.submit();
			alert("시간이 변경되었습니다.");
			// 데이터가 전송된 후에 페이지를 닫기 위한 window.open
			window.open('about:blank', '_self');
			opener.location.reload();
			//self.close();
		}
	}
	
	/** bookingHome.html 예약취소 클릭 시 **/
	var cancel_booking = function(bCode){
		
		window.open("/cndsalon/bookinghome/cancelView.do?bCode=" + bCode, "예약취소", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=700, height=600");
	}
	
