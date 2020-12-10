/**
 * 네이버 SENS 를 이용한 본인인증 문자메세지 스크립트
 */

var contextPath = 
 $('#contextPathHolder').attr('data-contextPath')?$('#contextPathHolder').attr('data-contextPath') : '';

$(function() {	
	
	var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

	
	//전화번호 유효성 테스트
	$('#phone').keyup(function() {


		if (isNaN($(this).val())) {
			$('#phoneErrorMsg').html("<b style='color: red'>숫자만 입력해주세요</b>");
		} else if ($(this).val().search(/\s/) != -1) {
			$('#phoneErrorMsg').html("<b style='color: red'>공백 없이 입력해주세요.</b>");
		} else if ($(this).val() == null || $(this).val() == "" || $(this).val() == null || $(this).val() == "") {
			$('#phoneErrorMsg').html("<b style='color: red'>필수 입력사항입니다:)</b>");			
		}else if($(this).val().length >11){
				$('#phoneErrorMsg').html("<b style='color: red'>번호를 확인해주세요.</b>");
		} else if(regExp.test( $(this).val() )){

			//중복검사

			//중복검사 통과 후, 제어
			var sendObj = {					
						phone : $(this).val()		
			}
			
			$.ajax({
				contentType: "application/json; charset=utf-8",
				type: "post",
				url: contextPath+"/check/phone",
				data: JSON.stringify(sendObj),
				success: function(result) {		
						$('#phoneErrorMsg').html(result);
				},
				error: function() {
					alert("failed to communicate");
				}
			});
			
		$('#phoneErrorMsg').text('');

		$("#getCrftNum").click(function() {


			$('#crftNumErrorMsg').text('');
			//var time = 180; //기준 시간 3분
			var time = 10; //10초 테스트용
			var min = "";
			var sec = "";


			//var x =setInterval(함수, 시간) 내부에서 clearInterval(x)호출하여 정지
			var x = setInterval(function() {
				min = parseInt(time / 60);
				sec = time % 60;

				document.getElementById("countdown").innerHTML = "0" + min + ":" + twoDigits(sec);
				time--;

				//타임아웃 시
				if (time < 0) {
					clearInterval(x);//setInterval() 실행 종료
					document.getElementById("countdown").innerHTML = "<b style='color: red'>00:00</b>";
					$('#crftNumErrorMsg').html("<b style='color: red'>인증 번호 입력시간이 초과했습니다</b>");
				}
			}, 1000);//var x = setInterval(function() END

		});//$("#getCrftNum").click(function(e)END


	}
	});//$('#phone').keyup(function()END





	//타이머 끝자리 0자리 출력 연산
	function twoDigits(sec) {
		if (sec == 0)
			return sec + "0";
	
		else if (sec <= 9)
			return "0" + sec;
		return sec;
	}
	
	//인증번호 영역
	//<input type="button" value="인증번호 받기" id="getCrftNum" th:onclick="|location.href='@{/getCrftNum}'|">
	$(".certification").append("<input type='button' value='인증번호 받기' id='getCrftNum' ><br />"
		+ "<input type='text' id='chk_crftNum' placeholder='인증번호 6자리 입력'>"
		+ "<span id='countdown'></span>"
		+ "<br /><div id='crftNumErrorMsg'></div>"
	);

	

});

