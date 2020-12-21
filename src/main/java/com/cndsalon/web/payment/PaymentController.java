package com.cndsalon.web.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cndsalon.service.payment.PaymentService;
import com.cndsalon.web.dto.payment.CancelData;
import com.cndsalon.web.dto.payment.IamportResponse;
import com.cndsalon.web.dto.payment.PaymentDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * 결제관련 기능의 컨트롤러
 * 
 * @author CWLEE
 * @version 1.0
 * @see com.cndsalon.service.payment.PaymentServiceImpl
 */
@Controller
@Slf4j
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	/**
	 * 결제 페이지로 이동하는 메소드
	 * 
	 * @return View PageName & Model 데이터
	 * @throws Exception
	 */
	@GetMapping("/payments")
	public ModelAndView movePaymentPage() throws Exception{
		ModelAndView mv = new ModelAndView("/payment/paymentForm");
		log.info("결제페이지 입장완료");
		return mv;
	}
	
	/**
	 * 결제성공 페이지로 이동하는 메소드
	 * 
	 * @return View PageName
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/moveSuccess", method=RequestMethod.GET)
	public String moveSuccessPage() throws Exception{
		log.info("결제성공페이지 입장완료");
		return "/payment/afterPaySuccess";
	}
		
	/**
	 * 결제실패페이지로 이동하는 메소드
	 * 
	 * @return View PageName
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/moveFail", method=RequestMethod.GET)
	public String moveFailPage() throws Exception{
		log.info("결제실패페이지 입장완료");
	return "/payment/afterPayFailed";
	}
				
	/**
	 * 결제성공 시 서버 DB에 결제정보데이터를 저장
	 * 
	 * @param paymentDTO ajax로 요청받은 결제데이터
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/success", method = RequestMethod.POST)
	public void insertPaymentInfo(@RequestBody PaymentDTO paymentDTO) throws Exception {
		// 확인용 로그코드
		log.info("=== Insert PaymentInfo start ===");
		log.info("아임포트 거래 고유번호 : " + paymentDTO.getImpUid());
		log.info("고유 주문번호 : " + paymentDTO.getMerchantUid());
		log.info("PG사 : " + paymentDTO.getPgProvider());
		log.info("결제수단 : " + paymentDTO.getPayMethod());
		log.info("결제 금액 : " + paymentDTO.getPaidAmount());
		log.info("결제 상태 : " + paymentDTO.getStatus());
		log.info("결제승인시각 : " + paymentDTO.getPaidAt());
		log.info("주문명 : " + paymentDTO.getName());
		log.info("주문자이름 : " + paymentDTO.getBuyerName());
		log.info("주문자Email : " + paymentDTO.getBuyerEmail());
		log.info("주문자연락처 : " + paymentDTO.getBuyerTel());
		log.info("주문자주소 : " + paymentDTO.getBuyerAddr());
		log.info("주문자우편번호 : " + paymentDTO.getBuyerPostcode());
		
		paymentService.insertPayInfo(paymentDTO);
	}
	
	/**
	 * 결제환불테스트페이지로 이동하는 메소드
	 * 
	 * @return View PageName
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/moveCancel", method=RequestMethod.GET)
	public String moveRefundForm() throws Exception{
		log.info("결제환불테스트페이지 입장완료");
	return "/payment/refundTest";
	}
	
	/**
	 * 결제 환불을 요청하는 메소드
	 * 
	 * @param cancelData ajax로 요청받는 데이터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/cancel", method=RequestMethod.POST)
	@ResponseBody
	public PaymentDTO doCancelPay(@RequestBody CancelData cancelData) throws Exception {
		String token = paymentService.getToken();
		log.info("획득한 토큰 값 : " + token);
		log.info("전달받은 cancelData : " + cancelData.getMerchant_uid());
		log.info("토큰 획득 후 결제환불 요청 시작");
		IamportResponse<PaymentDTO> refundResult = paymentService.cancelPayment(cancelData, token);
		PaymentDTO payInfo = refundResult.getResponse();
		log.info("환불로직 후 결과의 merchantUid : " + payInfo.getMerchantUid());
		log.info("환불로직 후 결과의 status : " + payInfo.getStatus());
		log.info("결제환불 완료");
		return payInfo;
	}
	
	/**
	 * 환불성공시 이동할 페이지
	 * 
	 * @return View PageName
	 * @throws Exception
	 */
	@RequestMapping(value="/payments/cancelSuccess", method=RequestMethod.GET)
	 public String moveAfterCancel() throws Exception{
		return "/payment/afterRefund";
	 }
}
