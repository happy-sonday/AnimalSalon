package com.cndsalon.aop.payment;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cndsalon.repository.payment.PayMapper;
import com.cndsalon.web.dto.payment.PaymentDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class CancelAdvice {
	@Autowired
	private PayMapper payMapper;
	
	// 결제 환불 성공 시 해당 DB Payment테이블의 status 속성을 'cancelled' 로 변경
	@AfterReturning(value="execution(* com.cndsalon.web.payment.PaymentController.doCancelPay(..))", returning="payInfo")
	public void afterCancel(JoinPoint jp, PaymentDTO payInfo) throws Exception {
		log.info("결제 환불 성공 후 DB status속성값 변경 시작");
		log.info(jp.getSignature().getName());
		log.info("전달받은 결제상태값: " + payInfo.getStatus());
		payMapper.updateStatus(payInfo);
	}
}
