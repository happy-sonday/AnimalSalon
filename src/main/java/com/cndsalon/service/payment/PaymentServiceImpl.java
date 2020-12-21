package com.cndsalon.service.payment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndsalon.repository.payment.PayMapper;
import com.cndsalon.web.dto.payment.AccessToken;
import com.cndsalon.web.dto.payment.AuthData;
import com.cndsalon.web.dto.payment.CancelData;
import com.cndsalon.web.dto.payment.IamportResponse;
import com.cndsalon.web.dto.payment.PaymentDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러에서 요청받아 실질적으로 기능을 수행하는 서비스
 * 
 * @author CWLEE
 * @version 1.0
 * @see <a href="https://docs.iamport.kr/?lang=ko">아임포트 결제 연동 매뉴얼</a>
 * @see <a href="https://github.com/iamport/iamport-rest-client-java-hc">java용 아임포트 REST API 매뉴얼</a>
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	
	
	/**
	 * 아임포트 REST API 요청 최상위 주소
	 */
	private static final String API_URL = "https://api.iamport.kr";
	
	/**
	 * 액세스 토큰을 발급받기 위한 REST API 키
	 */
	private  String api_key = "1236237657129148";
	
	/**
	 * 액세스 토큰을 발급받기 위한 REST API Secret
	 */
	private  String api_secret = "xVzd5ADhR0X5lIISwR7rZAfVhE9C8PUFKtSP4w7Vw41SNJKjBSKAt0wKEGnMvhsfLk5PRdLoiMg5w1p8";
	
	private HttpClient client = HttpClientBuilder.create().build();
	
	@Autowired
	private Gson gson;
	
	/**
	 * 결제 관련 Mapper 객체 
	 * 
	 * @see com.cndsalon.repository.payment
	 */
	@Autowired
	private PayMapper payMapper;
	
	/**
	 * 결제시 해당 결제정보를 DB에 입력하는 메소드
	 * 
	 * @param paymentDTO 컨트롤러에서 전달받은 paymentDTO 데이터
	 */
	@Override
	public void insertPayInfo(PaymentDTO paymentDTO) throws Exception {
		payMapper.insertPayInfo(paymentDTO);
	}

	/**
	 * 고유주문번호를 통해 결제정보를 조회하는 메소드
	 * 
	 * @param merchantUid <br>조회할 고유주문번호 ( = 거래코드 )
	 */
	@Override
	public PaymentDTO selectPayInfo(String merchantUid) throws Exception {
		PaymentDTO payInfo = payMapper.selectPayInfo(merchantUid);
		return payInfo;
	}
	
	/**
	 * REST API KEY 와 REST API SECRET 를 통해서 아임포트 REST API 로부터 토큰을 발급받는다.
	 * 
	 * @return auth <br>아임포트 REST API로부터 받는 Response Data<br>
	 * Response Data : {"code": 0, 
	 * 							  "message":"string", 
	 * 							  "response":{
	 * 								  "access_token:"string",
	 * 								  "expired_at":0,
	 * 								  "now":0
	 * 							   }
	 * 							 }
	 */
	@Override
	public IamportResponse<AccessToken> getAuth() throws Exception {
		log.info("토큰발급시작");
		AuthData authData = new AuthData(api_key, api_secret);
		
		String authJsonData = gson.toJson(authData);
		
		try {
			StringEntity data = new StringEntity(authJsonData);
			
			HttpPost postRequest = new HttpPost(API_URL+"/users/getToken");
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Connection","keep-alive");
			postRequest.setHeader("Content-Type", "application/json");
			
			postRequest.setEntity(data);
			
			HttpResponse response = client.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			
			Type listType = new TypeToken<IamportResponse<AccessToken>>(){}.getType();
			IamportResponse<AccessToken> auth = gson.fromJson(body, listType);
			
			return auth;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * getAuth()로 전달받은 Response Data 중에서 "access_token"을 추출한다.
	 *   
	 * @see #getAuth()
	 * @return token <br>String 형식의 "access_token"
	 */
	@Override
	public String getToken() throws Exception {
		IamportResponse<AccessToken> auth = this.getAuth();
		
		if( auth != null) {
			String token = auth.getResponse().getToken();
			log.info("토큰발급완료");
			return token;
		}
		return null;		
	}
	
	/**
	 * 토큰 인증 후 요청주소에 따라 post 요청을 수행한다.
	 * 
	 * @param path <br> 요청주소
	 * @param token <br> 인증받은 토큰의 access_token 값
	 * @param postData <br> post방식의 Request Data
	 * @return body <br> 아임포트 REST API 가 응답하는 Response data를 body 에 넣어서 리턴한다.
	 */
	@Override
	public String postRequest(String path, String token, StringEntity postData) throws URISyntaxException {
		try {
			HttpPost postRequest = new HttpPost(API_URL+path);
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Connection","keep-alive");
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.addHeader("Authorization", token);

			postRequest.setEntity(postData);
					
			HttpResponse response = client.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			
			return body;
			
		} catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
				
		return null;
	}

	/**
	 * 토큰 인증 후 요청주소에 따라 get 요청을 수행한다.
	 * @param path <br> 요청주소
	 * @param token <br> 인증받은 토큰의 access_token 값
	 * @return body <br> 아임포트 REST API 가 응답하는 Response data를 body 에 넣어서 리턴한다.
	 */
	@Override
	public String getRequest(String path, String token) throws URISyntaxException {
		try {
			HttpGet getRequest = new HttpGet(API_URL+path);
			getRequest.addHeader("Accept", "application/json");
			getRequest.addHeader("Authorization", token);

			HttpResponse response = client.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);

			return body;
			
		  } catch (ClientProtocolException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();
		  }
		
		return null;
	}

	@Override
	public IamportResponse<PaymentDTO> paymentByImpUid(String impUid, String token) throws Exception {
		if(token != null) {
			String path = "/payments/"+impUid;
			String response = this.getRequest(path, token);
			
			Type listType = new TypeToken<IamportResponse<PaymentDTO>>(){}.getType();
			IamportResponse<PaymentDTO> payment = gson.fromJson(response, listType);
			
			return payment;
		}		
		return null;		
	}

	@Override
	public IamportResponse<PaymentDTO> paymentByMerchantUid(String merchantUid, String token) throws Exception {
		if(token != null){
			String path = "/payments/find/"+merchantUid;
			String response = this.getRequest(path, token);
			
			Type listType = new TypeToken<IamportResponse<PaymentDTO>>(){}.getType();
			IamportResponse<PaymentDTO> payment = gson.fromJson(response, listType);
			
			return payment;
		}
		
		return null;
	}

	/**
	 * 결제환불 요청시 수행하는 메소드
	 * @param cancelData <br> 결제환불 요청시 보내는 환불요청데이터
	 * @param token <br> 인증받은 토큰의 access_token 값
	 * @retrun payment <br> 결제환불 시 아임포트 REST API로부터 전달받는 ResponseData 중 "response" 값
	 * PaymentDTO 객체형식에 매핑
	 */
	@Override
	public IamportResponse<PaymentDTO> cancelPayment(CancelData cancelData, String token) throws Exception {
		if(token != null){
			String cancelJsonData = gson.toJson(cancelData);
			StringEntity data = new StringEntity(cancelJsonData);
			
			String response = this.postRequest("/payments/cancel", token, data);
						
			Type listType = new TypeToken<IamportResponse<PaymentDTO>>(){}.getType();
			IamportResponse<PaymentDTO> payment = gson.fromJson(response, listType);
			
			return payment;
		}		
		return null;
	}
}
