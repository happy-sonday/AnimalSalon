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

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	private static final String API_URL = "https://api.iamport.kr";
	private  String api_key = "1236237657129148";
	private  String api_secret = "xVzd5ADhR0X5lIISwR7rZAfVhE9C8PUFKtSP4w7Vw41SNJKjBSKAt0wKEGnMvhsfLk5PRdLoiMg5w1p8";
	
	private HttpClient client = HttpClientBuilder.create().build();
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private PayMapper payMapper;
	
	@Override
	public void insertPayInfo(PaymentDTO paymentDTO) throws Exception {
		payMapper.insertPayInfo(paymentDTO);
		
	}

	@Override
	public PaymentDTO selectPayInfo(String merchantUid) throws Exception {
		PaymentDTO payInfo = payMapper.selectPayInfo(merchantUid);
		return payInfo;
	}
	
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
