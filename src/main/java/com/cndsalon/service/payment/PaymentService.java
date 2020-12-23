package com.cndsalon.service.payment;

import java.net.URISyntaxException;

import org.apache.http.entity.StringEntity;

import com.cndsalon.web.dto.payment.AccessToken;
import com.cndsalon.web.dto.payment.CancelData;
import com.cndsalon.web.dto.payment.IamportResponse;
import com.cndsalon.web.dto.payment.PaymentDTO;

public interface PaymentService {
	public void insertPayInfo(PaymentDTO paymentDTO) throws Exception;
	
	public PaymentDTO selectPayInfo(String merchantUid) throws Exception;
	
	public IamportResponse<AccessToken> getAuth() throws Exception;
	
	public String getToken() throws Exception;
	
	public String postRequest(String path, String token, StringEntity postData) throws URISyntaxException;
	
	public String getRequest(String path, String token) throws URISyntaxException;
	
	public IamportResponse<PaymentDTO> paymentByImpUid(String impUid, String token) throws Exception;
	
	public IamportResponse<PaymentDTO> paymentByMerchantUid(String merchantUid, String token) throws Exception;
	
	public IamportResponse<PaymentDTO> cancelPayment(CancelData cancelData, String token) throws Exception;
}
