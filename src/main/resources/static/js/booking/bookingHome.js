/**
 *  bookingHome에서 사용하는 스크립트 파일
 */

	/** 내역삭제 클릭 시**/
	var delete_booking_view = function(bCode){
		console.log("클릭한 예약번호 : " + bCode)
	}
	
	/** 시간변경 클릭 시 **/
	var time_change_booking = function(){
		window.open("/cndsalon/timeChangeView.do", "예약시간변경", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=700, height=600");
	}
	
	/** 예약취소 클릭 시 **/
	var cancel_booking = function(){
		window.open("/cndsalon/cancelView.do", "예약취소", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=700, height=600");
	}
	
	/** 리뷰쓰기 클릭 시**/
	var write_review = function(){
		
	}
	
	/** 재예약 클릭 시**/
	var re_booking = function(){
		
	}