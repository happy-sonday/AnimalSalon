/**
 *  bookingHome에서 사용하는 스크립트 파일
 */
$(document).ready(function(){

});

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
	var time_change_booking = function(bCode){
		window.open("/cndsalon/bookinghome/timeChangeView.do?bCode=" + bCode, "예약시간변경", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=700, height=600");
	}
	
	/** bookingHome.html 예약취소 클릭 시 **/
	var cancel_booking = function(bCode){
		window.open("/cndsalon/bookinghome/cancelView.do?bCode=" + bCode, "예약취소", "toolbar=no, menubar=no, " +
		"scrollbars=yes, resizable=no, width=700, height=600");
	}
	
	/** bookingCancel.html 예약/결재취소 클릭 시**/
	var go_cancel_booking = function(bCode){
		var reason = $('#reasonForCancel option:selected').val();
		console.log("제발좀 : " + reason);
		if (reason == "x") {
			
			alert('취소사유를 선택해주세요.');
		} else {
				
			var data = JSON.stringify({
					bStatus : "2",
					bCancelReason : reason
			});
			var token = $("meta[name='_csrf']").attr("content");
	 		var header = $("meta[name='_csrf_header']").attr("content");
		
			$.ajax({
				url : "/cndsalon/bookinghome/bookings/" + bCode,
				type : "PUT",
				contentType: "application/json; charset=utf-8",
				data : data,
				beforeSend : function(xhr){
				xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
				},
				success : function(){
					alert("예약이 취소되었습니다.");
					self.close();
				},
				error : function(){
					alert("취소 불가한 예약입니다.");
					self.close();	
				}
			});
		}
	}