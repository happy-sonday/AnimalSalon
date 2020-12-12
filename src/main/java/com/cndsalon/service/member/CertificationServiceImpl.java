package com.cndsalon.service.member;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cndsalon.repository.member.CertificationRepository;
import com.cndsalon.web.dto.member.MessageRequestDto;
import com.cndsalon.web.dto.member.SendSmsResponseDto;
import com.cndsalon.web.dto.member.SmsRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Slf4j
public class CertificationServiceImpl  implements CertificationService{

	@Autowired
	private CertificationRepository repository;
	
	@Transactional
	@Override
	public int findById(String id) {		
		return repository.findById(id);
	}

	@Transactional
	@Override
	public int findByPhone(String phone) {	
		return repository.findByPhone(phone);
	}

	@Transactional
	@Override
	public int findByEmail(String email) {		
		return repository.findByEmail(email);
	}

	@Transactional
	@Override
	public int findByNickname(String nickname) {		
		return repository.findByEmail(nickname);
	}

	@Transactional
	@Override
	public String numberGen(int len, int dupCd) {	//자리수,  [중복허용1:중복제거2]
												
		
		Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수
        
        for(int i=0;i<len;i++) {
            
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            
            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        
        log.debug("생성 된 난수 :  {}" ,numStr);
        return numStr;    
	}
	
	

	@Transactional
	@Override
	public MimeMessage setSMTP(String NaverOrGoole, String senderEmail, String senderPassword) {
		MimeMessage message = null;
		String host="";
		int port=0;
				
		Properties prop = new Properties();
		if(NaverOrGoole.equalsIgnoreCase("naver")||NaverOrGoole.equals("네이버")) {
			host="smtp.naver.com";
			port=587;
			
		}else if(NaverOrGoole.equalsIgnoreCase("google")||NaverOrGoole.equals("구글")) {
			host="smtp.gmail.com";
			port=465;
			
			//추가 SSL 세팅 필요
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
		}
		
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.debug", "true");
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		MimeMessage message1 = null;
		try {
			message1 = new MimeMessage(session);
			message1.setFrom(new InternetAddress(senderEmail));
		} catch (MessagingException e ) {

			e.printStackTrace();
		} 	
		
		
		log.info("setSMTP 메서드 실행 {} SMTP 설정 완료",NaverOrGoole);
		return message1;
	}

	
	
	@Transactional
	@Override
	public void sendEmailWith(MimeMessage setSMTP, String receiver, String title, String content) {
		MimeMessage message = null;
		
		try {
			message = setSMTP;
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			message.setSubject(title);// 제목
			message.setText(content);//본문
			Transport.send(message);// 전송
			
			log.info("senEmailWith 메서드 실행 및 발송 완료");
		} catch (MessagingException e) {
			System.out.println("Message()ERR:" + e.getMessage());
		}		
	}

	
	
	@Transactional
	@Override
	public String makeSignature(Long time)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		
		String space = " "; // one space 
		String newLine = "\n"; // new line 
		String method = "POST"; // method 
		String url = "/sms/v2/services/ncp:sms:kr:262235515436:animalsalon/messages"; // url (include query string) 
		String timestamp = time.toString(); // current timestamp (epoch) 
		String accessKey = "zGFXENx98ufN822pwHGq";	 // access key id (from portal or Sub Account) 
		String secretKey = "Yj6cn5i2H1mfyE2KOLCD9wQeppHB7oqIRwdxg29m";
		String message = new StringBuilder()
		 .append(method)
		 .append(space)
		 .append(url)
		 .append(newLine)
		 .append(timestamp)
		 .append(newLine)
		 .append(accessKey)
		 .toString(); 

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256"); 
		Mac mac = Mac.getInstance("HmacSHA256"); 
		mac.init(signingKey); 

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8")); 
		String encodeBase64String = Base64.encodeBase64String(rawHmac); 
		
		
		log.debug("makeSignature() 구현");
		return encodeBase64String; 		
	}

	
	
	@Transactional
	@Override
	public SendSmsResponseDto sendSms(String recipientPhoneNumber, String content)
			throws JsonProcessingException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			RestClientException, URISyntaxException {
		
		
		Long time = new Date().getTime();
		//Long time = System.currentTimeMillis()/1000;//401 ERROR
		List<MessageRequestDto> messages = new ArrayList<>();

		// 보내는 사람에게 내용을 보냄. 
		messages.add(new MessageRequestDto(recipientPhoneNumber,content)); // content부분이 내용임 
		
		// 전체 json에 대해 메시지를 만든다. 
		SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", "01096558120", "AnimalSalon", messages);

 
		ObjectMapper objectMapper = new ObjectMapper(); 
		String jsonBody = objectMapper.writeValueAsString(smsRequestDto); 
		
		// 헤더에서 여러 설정값들을 잡아준다. 
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON); 
		headers.set("x-ncp-apigw-timestamp", time.toString()); 
		headers.set("x-ncp-iam-access-key", "zGFXENx98ufN822pwHGq"); // 제일 중요한 signature 서명하기. 
		String sig = makeSignature(time); 
		System.out.println("sig -> " + sig); 
		headers.set("x-ncp-apigw-signature-v2", sig);
		
		
		// 위에서 조립한 jsonBody와 헤더를 조립한다. 		
		HttpEntity<String> body = new HttpEntity<>(jsonBody, headers); 
		System.out.println(body.getBody()); 
		
		
		// restTemplate로 post 요청을 보낸다. 별 일 없으면 202 코드 반환된다. 
		RestTemplate restTemplate = new RestTemplate(); 
		SendSmsResponseDto sendSmsResponseDto = restTemplate.postForObject(
				new URI("https://sens.apigw.ntruss.com/sms/v2/services/ncp:sms:kr:262235515436:animalsalon/messages"),
				body, SendSmsResponseDto.class);
		
		
		log.debug("발송 상태: {}",sendSmsResponseDto.getStatusCode());		
		
		return sendSmsResponseDto;		
		
	}
	
	

}
