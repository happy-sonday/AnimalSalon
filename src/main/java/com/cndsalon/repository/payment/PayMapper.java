package com.cndsalon.repository.payment;

import org.apache.ibatis.annotations.Mapper;

import com.cndsalon.web.dto.payment.PaymentDTO;

@Mapper
public interface PayMapper {
	void insertPayInfo(PaymentDTO paymentDTO) throws Exception;
	
	PaymentDTO selectPayInfo(String merchantUid) throws Exception;
	
	void updateStatus(PaymentDTO paymentDTO) throws Exception;
}
