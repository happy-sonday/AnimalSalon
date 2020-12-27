
$(document).ready(function(){
	
	$("#all_agree").click(function(){
		if($("#all_agree").prop("checked")){
			$("input[name=chk]").prop("checked",true);
			$("#btn_payment").attr('disabled',false);
		} else {
			$("input[name=chk]").prop("checked", false);
			$("#btn_payment").attr('disabled',true);	
		}
	});
	
	$("#agree_third").click(function(){
		if($("#agree_third").prop("checked")){
			$("input[id=agree_third]").prop("checked",true);
			if($("#pay_agree").prop("checked")){
				$("#all_agree").prop("checked", true);
				$("#btn_payment").attr('disabled',false);
			}
		} else {
			$("input[name=agree_third]").prop("checked", false);
			$("#btn_payment").attr('disabled',true);
			$("#all_agree").prop("checked", false);	
		}
	});
	
	$("#pay_agree").click(function(){
		if($("#pay_agree").prop("checked")){
			$("input[id=pay_agree]").prop("checked",true);
			if($("#agree_third").prop("checked")){
				$("#all_agree").prop("checked", true);
				$("#btn_payment").attr('disabled',false);
			}
		} else {
			$("input[name=pay_agree]").prop("checked", false);
			$("#btn_payment").attr('disabled',true);
			$("#all_agree").prop("checked", false);
		}
	});
	
	$(function(){
		if(!$("input[name=chk]").prop("checked")){
			$("#btn_payment").attr('disabled',true);
		} else {
			$("#btn_payment").attr('disabled',false);
		}
	});
	
	$('#paym-01').click(function(){
		$('#paym-01').prop("checked", true);
		console.log($('#paym-01').prop("checked"));
		console.log($('#paym-02').prop("checked"));
		if($("#paym-01").prop("checked")){
			$(".pay-guide").empty();
			$(".pay-guide").html(
				'<p>KG이니시스 결제 모듈을 통해 신용카드로 결제합니다.' +
				'<br> 유의사항은 카드사마다 상이합니다.</p>'
			)
		}
	});
	
	$('#paym-02').click(function(){
		$('#paym-02').prop("checked", true);
		console.log($('#paym-01').prop("checked"));
		console.log($('#paym-02').prop("checked"));
		if($("#paym-02").prop("checked")){
			$(".pay-guide").empty();
			$(".pay-guide").html(
				'<p>카카오페이 안내 카카오페이는 카카오톡에서 카드를 등록, 간단하게 비밀번호만으로 결제할 수 있는 빠르고 편리한 모바일 결제 서비스입니다.' +
				'<br/>- 지원 카드 : 모든 카드 등록/결제 가능</p>'
			)
		}
	});

});

$(function(){
		if($("#paym-01").prop("checked")){
			$(".pay-guide").empty();
			$(".pay-guide").html(
				'<p>KG이니시스 결제 모듈을 통해 신용카드로 결제합니다.' +
				'<br> 유의사항은 카드사마다 상이합니다.</p>'
			)
		} else if($("#paym-02").prop("checked")){
			$(".pay-guide").empty();
			$(".pay-guide").html(
				'<p>카카오페이 안내 카카오페이는 카카오톡에서 카드를 등록, 간단하게 비밀번호만으로 결제할 수 있는 빠르고 편리한 모바일 결제 서비스입니다.' +
				'<br/>- 지원 카드 : 모든 카드 등록/결제 가능</p>'
			)
		}
	});
