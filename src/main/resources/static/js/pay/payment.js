/**
 * 
 */

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
});
