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

        	
   		var idJ = /^[a-z0-9]{5,12}$/;


   		if($("input:text").val()==null||$("input:text").val()==""){
   			$('#idErrorMsg').html("<b style='color: red'>필수입력사항입니다:)</b>");
   
   		}else{
   			/*
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
 		*/
 		$('#idError').html("<b style='color: red'>영어 소문자 및 숫자로 최소 5자리 이상으로 만들어주세요.</b>");					
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
 	$( "#domain" ).blur(function() {	
		

		var emailPattern =/^[a-zA-Z0-9._-]+@[a-zA-z0-9.-]+\.[a-zA-Z]{2,4}$/;//정규식 조건 
		var email_id= $('#email_id').val();
		var txtDomain=$('#txtDomain').val();
		var email=email+txtDomain;
		
		if(email_id==null||email_id==""||txtDomain==null||txtDomain==""){
			$('#emailErrorMsg').html("<b style='color: red'>필수 입력 사항입니다</b>");			
			
		}else if(!email.match(emailPattern)){
			$('#emailErrorMsg').val("");
			$('#emailErrorMsg').html("<b style='color: red'>유효하지 않은 이메일 주소입니다.</b>");							
		}else{
			/*
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
			*/						
		}						
	});
 			 
	/* 			 
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
 	*/
	
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
	
	$("#searchZip,#zip,#address1").click(function() {
	
		//다음우편번호주소찾기로 ㄱㄱ
		window.open(contextPath+'/searchZip', '우편번호찾기', 'width=700px,height=800px,scrollbars=yes');
	});
	
	//전화번호 유효성 테스트
	 $('#phone').keyup(function() {
						
			    
		 if(isNaN($(this).val())){
				$('#phoneErrorMsg').html("<b style='color: red'>숫자만 입력해주세요</b>");
		 }else if($(this).val().search(/\s/) != -1){
				$('#phoneErrorMsg').html("<b style='color: red'>공백 없이 입력해주세요.</b>");
		}else if($(this).val()==null||$(this).val()==""||$(this).val()==null||$(this).val()==""){
				$('#phoneErrorMsg').html("<b style='color: red'>필수 입력사항입니다:)</b>");
		}else{
				$('#phoneErrorMsg').text('');
				//중복검사 ajax , 완료되면 인증번호 받기 실행
		}
		
	});
	
	//별명중복 검증
	$('#nickname').keyup(function() {
						
		$('#nicknameErrorMsg').text('');  	    
		if($(this).val()==null||$(this).val()==""){
			$('#nicknameErrorMsg').html("<b style='color: red'>별명을 확인해주세요.</b>");
		}else{
			
		}
	});
	
	//전체 유효성 검증
	
    
    
    //생년월일 년월 출력   
    	/*
    	// 년 뿌려주기
        var dt = new Date();
        var year = "";
        var com_year = dt.getFullYear();        
        $("#YEAR").append("<option value=''>년도</option>");
        // 올해 기준으로 -1년부터 +5년을 보여준다.
        for(var y = (com_year-1); y <= (com_year+5); y++){
            $("#YEAR").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
        }
        */
        // 월 뿌려주기(1월부터 12월)
        var month;
        $("#MONTH").append("<option value=''>월</option>");
        for(var i = 1; i <= 12; i++){
            $("#MONTH").append("<option value='"+ i +"'>"+ i + " 월" +"</option>");
        }
        //일 뿌려주기(1일부터 31일)
        var day;
        $("#DAY").append("<option value=''>일</option>");
        for(var i = 1; i <= 31; i++){
            $("#DAY").append("<option value='"+ i +"'>"+ i + " 일" +"</option>");
        }
        
        
        $("#DAY").append("<option value=''>일</option>");
        for(var i = 1; i <= 31; i++){
            $("#DAY").append("<option value='"+ i +"'>"+ i + " 일" +"</option>");
        }
        
        //인증번호 받기
        /*
        $("#getCrftNum").click(function() {
	
		//
		window.open(contextPath+'/getCrftNum', '인증번호받기', 'width=700px,height=800px,scrollbars=yes');
		});
		*/	
		
		//인증번호 영역
		//<input type="button" value="인증번호 받기" id="getCrftNum" th:onclick="|location.href='@{/getCrftNum}'|">
		$(".certification").append("<input type='button' value='인증번호 받기' id='getCrftNum'><br />"
		+"<input type='text' id='chk_crftNum' placeholder='인증번호 6자리 입력'>"
		+"<span id='countdown'></span>"
		+"<br /><div id='crftNumErrorMsg'></div>"
		);
        
        
   
        $("#getCrftNum").click(function(e) {
        	
   	        	
		 	$('#crftNumErrorMsg').text('');
		   //var time = 180; //기준 시간 3분
		   var time = 10; //10초 테스트용
		   var min = "";
		   var sec = "";
	   
   
   			//var x =setInterval(함수, 시간) 내부에서 clearInterval(x)호출하여 정지
		    var x = setInterval(function(){
		   	min = parseInt(time/60);
		   	sec = time%60;
		   	
		   	document.getElementById("countdown").innerHTML="0"+min+":"+twoDigits(sec);
		   	time--;
		  
		   	//타임아웃 시
		   	if(time < 0){
		   		clearInterval(x);//setInterval() 실행 종료
		   		document.getElementById("countdown").innerHTML="<b style='color: red'>00:00</b>";
		   		$('#crftNumErrorMsg').html("<b style='color: red'>인증 번호 입력시간이 초과했습니다</b>");
		   	}
	   	},1000);
	   	
	   	//끝자리 수 계산
	   	function twoDigits(sec){	   	
	   		if(sec==0)
	   			return sec+"0";
	   		else if(sec<=9)
	   			return "0"+sec;	   			
	   			return sec;	   	
	   	}
	   	
	}); 
	
	
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

