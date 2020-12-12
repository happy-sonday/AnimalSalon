/**
 * 
 */
 

 $(function() {
 
 
  //heade에서 contextPath정보를 읽어옴
 var contextPath = 
 $('#contextPathHolder').attr('data-contextPath')?$('#contextPathHolder').attr('data-contextPath') : '';
 
  //뒤로가기
  $("#goBack").click(function(){
	window.history.back();
	});

  
 
  // 전체 선택 체크박스_약관
  $( "#chk_all" ).click(function() {  
  	//alert( "Handler for .click() called." );
  	if($("#chk_all").is(":checked")){
  		$(".chk").prop("checked", true);
  	}else{
  		$(".chk").prop("checked",false);
  	}  	  	  
  });
  
  
  $(".chk").click(function() {  
	if($("input[name='chk']:checked").length==$("input:checkbox[name='chk']").length){
	//checked된 갯수와 전체 체크박스 갯수가 일치할때	
		$("#chk_all").prop("checked",true)
	}else{
	$("#chk_all").prop("checked",false);
	}			
  });
  

  

  
  //아이디 유효성 검사
  $('#id').keyup(function() {

        //영소문으로 시작, 최소 5자리이상 20자리 제한 영소문자 숫자로만구성
   		var idJ = /^[a-z]+[a-z0-9]{5,19}$/g;;


   		if($("input:text").val()==null||$("input:text").val()==""){
   			$('#idErrorMsg').html("<b style='color: red'>필수입력사항입니다:)</b>");
   
		//아이디 유효성 테스트 통과시 중복검사
   		}else	 if(idJ.test( $(this).val() )){					
					//$('#idErrorMsg').text('');//클리어
					
					var sendObj = {										
					id : $("#id").val()					
					}			
										
					$.ajax({
					contentType: "application/json; charset=utf-8",
					type: "post",
					url: contextPath+"/check/id",
					data: JSON.stringify(sendObj),
					success: function(result) {		
							$('#idErrorMsg').html(result);
						},
							error: function(jqXHR, textStatu) {
							console.log("failed to communicate:"+textStatu);
						}
					});
		}else{
				$('#idErrorMsg').html("<b style='color: red'>최소 5자리 이상 영어 소문자와 숫자로 만들어주세요.</b>");	
		}
	});
	
	//패스워드 유효성 검사
	//1차 비밀번호 입력 검증
	$('#pwd').keyup(function() {
     	if($("input:password").val()==null||$("input:password").val()==""){
 			$('#pwdErrorMsg').html("<b style='color: red'>필수입력사항입니다:)</b>");
 		}else{
 			 			
 			var pwd = $(this).val();
 			 var num = pwd.search(/[0-9]/g);
 			 var eng = pwd.search(/[a-z]/ig);
 			 var spe = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
 			 var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

 			 if(pwd.length < 5){
 				 $('#pwdErrorMsg').html("<b style='color: red'>보안 : 취약</b>");
 				 
 			}else if(pwd.search(/\s/) != -1){
 				$('#pwdErrorMsg').html("<b style='color: red'>공백 없이 입력해주세요.</b>");
 				
 			}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
 				 $('#pwdErrorMsg').html("<b style='color: orange'>보안 : 보통</b>");
 				  
 			}else if(reg.test(pwd)){
 				 $('#pwdErrorMsg').html("<b style='color: blue'>보안 : 최고 등급</b>");
 			} 		    			
 		}
 		
	});
	
	//2차 비밀번호 입력 검증
	$('#2ndPwd').keyup(function() {
 		if($("#2ndPwd").val()==null||$("#2ndPwd").val()==""){
 			$('#2ndPwdErrorMsg').html("<b style='color: red'>입력한 비밀번호를 확인해주세요:)</b>"); 	
 		}else if($('#pwd').val()!=$('#2ndPwd').val()){
			$('#2ndPwdErrorMsg').text('');//클리어
			$('#2ndPwdErrorMsg').html("<b style='color: red'>암호가 틀립니다.</b>");		
		}else{				
			$('#2ndPwdErrorMsg').text('');//클리어
			$('#2ndPwdErrorMsg').html("<b style='color: blue'>암호가 맞습니다</b>");					
		}
	});
	
	
	//이름 유효성 검증
	$('#name').keyup(function() {			 	
					
		var nameJ = /^[가-힣]{2,6}$/;//한글테스트		
		
	       if($(this).val()==null||$(this).val()==""){
	       	$('#nameErrorMsg').html("<b style='color: red'>필수입력사항입니다:)</b>");        		
	    }
	    else{	    	
	    	if(nameJ.test( $(this).val() )){
	    		$('#nameErrorMsg').text('');
	    	}else{
	    		$('#nameErrorMsg').html("<b style='color: red'>이름을 확인해주세요</b>");	
	    	}  		    	
	    }  						          
    			    			
	});
	
	//도로명 주소창 열기
	 $("#findZip, #zip, #roadAddress").click(function() {
			
			
			 new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#zip").val(data.zonecode) ;
				$("#roadAddress").val(roadAddr) ;                          
          
               
            }
        }).open();
		
		$("#detailAddress").focus();	
	});
	
	

	//상세 주소 유효성 검증
	$("#detailAddress").keyup(function(){
		
	 	var addrRgx = /^[ㄱ-ㅎ가-힣a-zA-Z0-9\s@\-_~*()/,.:]+$/; //한글+영문+공백+일부 특수문자만 허용
		
		if($(this).val()==null||$(this).val()==""){
	       	$('#detailAddressErrorMsg').html("<b style='color: red'>필수입력사항입니다:)</b>");        		
	    }else if(addrRgx.test( $(this).val() )){
	    		$('#detailAddressErrorMsg').text("");
	    }else{
	    		$('#detailAddressErrorMsg').html("<b style='color: red'>유효하지 않은 주소입니다.</b>");	
	    }  		    	
	});				          
		
	
	//휴대번호나는 sens.js로 통합
	//도메인 선택박스에 따른 자동완성	
	
	$('#domain').change(function() {			
		
		var selectedText = $("#domain option:selected").val();
		var selectedIndex= $('#domain option').index($('#domain option:selected'));		 

		
		if(selectedIndex==0){
			$('#txtDomain').val("").prop("readonly",false).focus();														
		}else{
			$('#txtDomain').val(selectedText).prop("readonly",true);			
			$('#email_id').focus();	
		}						
	});
	
	
	
 		 
 			 
 			
	//이메일 검증 			 
 	$( "#email_id" ).blur(function() {			

		var emailPattern =/^[a-zA-Z0-9._-]+@[a-zA-z0-9.-]+\.[a-zA-Z]{2,4}$/;//정규식 조건 
		var email_id= $('#email_id').val();
		var txtDomain=$('#txtDomain').val();
		var email=email_id+txtDomain;	
		
		if(email_id==null||email_id==""||txtDomain==null||txtDomain==""){
			$('#emailErrorMsg').html("<b style='color: red'>필수 입력 사항입니다</b>");			
		}else if(!email.match(emailPattern)){
			$('#emailErrorMsg').html("<b style='color: red'>유효하지 않은 이메일 주소입니다.</b>");							
		}else{
	
			$('#emailErrorMsg').text('');//클리어
					
					var sendObj = {										
					email : email							
					}			
										
					$.ajax({
					contentType: "application/json; charset=utf-8",
					type: "post",
					url: contextPath+"/check/email",
					data: JSON.stringify(sendObj),
					success: function(result) {		
							$('#emailErrorMsg').html(result);
						},
							error: function(jqXHR, textStatu) {
							console.log("failed to communicate:"+textStatu);
						}
					});
			
		}						
	});
		
	
	//별명유효성 검사
  $( "#actNickname" ).click(function() {  

  	if($(this).is(":checked")){

			$("#nickname").focus();
			$("#nickname").keyup(function(){
				
				var nicknameRgx = /^[가-힣a-zA-Z0-9]{2,10}$/; //2자이상 한글+영문+숫자
					$('#nicknameErrorMsg').text("");
					if(nicknameRgx.test( $(nickname).val() )){//test가 검증되면 별명 중복여부 확인
					
					var sendObj = {					
					
					nickname : $(this).val()			
					
					}			
						
					$.ajax({
						
					contentType: "application/json; charset=utf-8",
					type: "post",
					url: contextPath+"/check/nickname",
					data: JSON.stringify(sendObj),
					success: function(result) {		
							$('#nicknameErrorMsg').html(result);
						},
							error: function(jqXHR, textStatu) {
							console.log("failed to communicate:"+textStatu);
						}
					});
					
											
					}else if($("#nickname").val().search(/\s/) != -1){//공백이 있을시 alert
						$('#nicknameErrorMsg').html("<b style='color: red'>공백은 사용이 불가능 합니다.</b>");
					}else{
						$('#nicknameErrorMsg').html("<b style='color: red'>유효하지 않은 별명입니다.</b>");
					}
			});


	 }else{//체크해제시 초기화
			$('#nicknameErrorMsg').text("");
			$('#nickname').val("");			
	 }  	  	  
  });
  

		
	
	
	
    
    
		//전체 유효성 검증
		 $("#mamber_save").click(function() {
						
					//등록
					//alert($("#roadAddress").val()+" " + $("#detailAddress").val());
			    	//alert($("#profile").val()==null? "noImg": $("#profile").val());

				var data={
			    			id : $("#id").val(),			
							pwd : $("#pwd").val(),
							name :  $("#name").val(),
							zip : $("#zip").val(),
							address : $("#roadAddress").val()+" " + $("#detailAddress").val(),
							phone : $("#phone").val(),
							email :  $("#email_id").val()+$("#txtDomain").val(),
							//profile : $("profile").val()==null? "noImg": $("#profile").val(),//따로 저장하자
							//role : "CLIENT"//데이터베이스 저장할때 저장하자	    		
			    	}


				   $.ajax({
						
					contentType: "application/json; charset=utf-8",
					type: "post",
					url: contextPath+"/member/join",
					data: JSON.stringify(data),
					success: function(result) {		
							alert("회원가입이 완료되었습니다.");							
						
							window.location.href=contextPath+"/member/welcome";
						},
							error: function(jqXHR, textStatu) {
							alert("failed to communicate:"+textStatu);
						}
					});
		});
			    	


				
				
				//profile 이미지
  				$("#profile").change(function(e){                   
	
				
				var ext=$(this).val().split(".").pop().toLowerCase();
				var fileSize=this.files[0].size;
				var maxSize= 1*1024*1024;
				
				//확장자 체크
				if($.inArray(ext,["jpg","jpeg","png", "bmp"])==-1){
					$('#profileErrorMsg').html("<b style='color: red'>jpg, jpeg, png, bmp 확장자만 업로드 가능합니다.</b>");
					$(this).val('');//업로드한 파일 clear
					return;
				}else if(fileSize>maxSize){//파일 크기 확인					
					$('#profileErrorMsg').html("<b style='color: red'>업로드 제한 용량 1MB를 초과했습니다.</b>");
					$(this).val('');//업로드한 파일 clear
					return;
				} 
					
					//위 필터 통과하면 업로드 시작			
					$('#profileErrorMsg, #preview').empty();//없으면 에러 표시와 함께 누적됨
					
						
					
				 	var files = e.target.files;
				    var arr =Array.prototype.slice.call(files);
			      	arr.forEach(function(f){       
			    	     
			                 
			        var str = '<div style="display: inline-flex; padding: 5px;">';
			              
			        
			        if(f.type.match('image.*')){
			            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
			            reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
			              //str += '<button type="button" class="delBtn" value="'+f.name+'" style="background: red">x</button><br>';
			              str += '<img src="'+e.target.result+'" width=150 height=150 />';
			              str += '</div>';					
			              $(str).appendTo('#preview');
			            } 
			            reader.readAsDataURL(f);
			        }else{
			        	//이미지 없음 넣기
			            str += '<img src="/resources/img/fileImg.png" width=150 height=150 />';
			            $(str).appendTo('#preview');
			        }
			     });//arr.forEach      
      
   			 });//file change END



					
			
$('#selectProfile').click(function (e) {
    //document.profile.target_url.value = document.getElementById( 'target_img' ).src;
    //e.preventDefault();
    $('#profile').click();
});        


function changeValue(obj){
   document.profile.submit();
}




 
 /*
<div>
    <img id = "target_img" src="target_img_name.png">
</div>
 
 
<form name="signform" method="POST" ENCTYPE="multipart/form-data" action="./design_update.htm">
    <input type="file" id="file" name="file" style="display:none;" onchange="changeValue(this)">
    <input type="hidden" name = "target_url">
</form>
*/



   		
		
		
        
        
   
        
	
	
	//heade에서 contextPath정보를 읽어옴
	//js에서 contextpath 정보 읽음 var contextPath = $('#contextPathHolder').attr('data-contextPath')?$('#contextPathHolder').attr('data-contextPath') : '';
	//ajax 문법 예제1)
	/*
var main = {
	
init : function () {
var _this = this;//함수의 변수화    

$('#btn-save').click(function () {
           
           _this.save();//변수 선언
           
          var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
          };
    });//$('#btn-save').click(function ()END

 save : function () {  	

        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
           
            //url: 'http://localhost:8181/cndsalon/api/v1/posts',
            url: contextPath+'/api/v1/posts',
            
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
            
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = contextPath;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }//save:function()END
};//init:function()END

main.init();
	
	
	       
	*/
	//ajax 문법 예제2)
	
	/*
	
 $(document).ready(function() {//페이지가 준비되었을때
	 $( "#email_id,#txtDomain" ).keyup(function() {	
 				
   				
 				var emailPattern =/^[a-zA-Z0-9._-]+@[a-zA-z0-9.-]+\.[a-zA-Z]{2,4}$/;//정규식 조건 
 				var email_id= $('#email_id').val();
 				var txtDomain=$('#txtDomain').val();
 				var email_cs=email_id+"@"+txtDomain;
 				
 				if(email_id==null||email_id==""||txtDomain==null||txtDomain==""){
 					$('#emailError').html("<b style='color: red'>필수 입력 사항입니다</b>");			
 					
 				}else if(!email_cs.match(emailPattern)){
 					$('#emailError').html("<b style='color: red'>유효하지 않은 이메일 입니다.</b>");							
 				}else{
 					
 					
 					var request=$.ajax({
    					url:"LB?command=emailConfirm", method:"GET", data: {email_cs : email_cs}, dataType: "json"	
    					//호출될 url,           메서드 type:전송방식,   전송되는 파라미터,   응답타입
    					});
    				
    				request.done(function(data) {					
    					$('#emailError').html(data.msg);	
    				});
    				
    				request.fail(function(jqXHR, textStatus) {				
    					alert("Request failed:"+textStatus);					
 					});						
 				}
 				
   			 });


		$('#idConfirm').keyup(function() {

        	
        		var idJ = /^[a-z0-9]{5,12}$/;



        		if($("input:text").val()==null||$("input:text").val()==""){
        			$('#idError').html("<b style='color: red'>필수입력사항입니다:)</b>");
        
        		}else{
        			
        			if(idJ.test( $(this).val() )){
	    				
	    				
	    				var request=$.ajax({
	    					url:"LB?command=idConfirm", method:"GET", data: {idConfirm : $('#idConfirm').val()}, dataType: "json"	
	    					//호출될 url,           메서드 type:전송방식,   전송되는 파라미터,   응답타입
	    					});
	    				
	    				request.done(function(data) {					
	    					$('#idError').html(data.msg);	
	    				});
	    				
	    				request.fail(function(jqXHR, textStatus) {				
	    					alert("Request failed:"+textStatus);
	    				});
	    			}else{
	    				$('#idError').html("<b style='color: red'>영어 소문자 및 숫자로 최소 5자리 이상으로 만들어주세요.</b>");	
	    			}					
        	} 	
		});
   });

	
	*/
    
  
});

