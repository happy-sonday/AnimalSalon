package com.cndsalon.domain.book;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 개요: 예약내역 Entity
 * </pre>
 * @author <a href="mailto:hkj5455@gmail.com">김진혁</a><br>
 * @date 2020. 12. 8. 
 * @version 1.0
 * @since 
 * LocalDateTime을 매핑하는 어노테이션이 구체적으로 나와있는 정보를 찾지 못했다.
 * 일단, 찾아본 레퍼런스 중에서 지정해놨다.
 * 추가로, booking테이블은 view테이블을 사용할 가능성이 있음.(예약 외에 다른 파트는 큰 문제 없을 것으로 보이니 신경쓰지 않아도 괜찮을듯.)
 */
@Entity(name = "BOOKING")
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "BOOKING_SEQ_GENERATOR", sequenceName = "BOOKING_SEQ", initialValue = 1, allocationSize = 1)
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ_GENERATOR")
    private Long bCode;    // 예약코드
	
    private String id;        // 아이디

	private String mCode;    // 메뉴코드
	
    private String dCode;    // 디자이너코드
	
    private String sCode;    // 매장코드
	
	@Column(name = "B_DATE", columnDefinition = "DATE")
    private LocalDate bDate; // 예약일정(년월일,요일)
	
	@Column(name = "B_TIME", columnDefinition = "TIME")
    private LocalTime bTime; // 예약일정(시간)
	
    private Integer bBeautyTime; // 시술 소요시간
	
    private Integer bPrice;  // 예약 금액
	
    private String bStatus;    // 예약 상태 / 0: 예약진행중, 1: 완료, 2: 취소(환불), 3: 삭제 / default 0
	
    private String bCancelReason; // 예약 취소 사유
    
    @Builder
    public Booking(String id, String mCode, String dCode, String sCode, int bBeautyTime, int bPrice) {
    	this.id = id;
    	this.mCode = mCode;
    	this.dCode = dCode;
    	this.sCode = sCode;
    	this.bBeautyTime = bBeautyTime;
    	this.bPrice = bPrice;
    }

	public void setBDate(String bDate) {
		this.bDate = LocalDate.parse(bDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void setBTime(String bTime) {
		this.bTime = LocalTime.parse(bTime, DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	@PrePersist
	public void defaultStatus() {
		if(bStatus== null) {
			this.bStatus = "0";
		}
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", mCode=" + mCode + ", dCode=" + dCode + ", sCode=" + sCode + ", bDate=" + bDate
				+ ", bTime=" + bTime + ", bBeautyTime=" + bBeautyTime + ", bPrice=" + bPrice + "]";
	}
    
	
}
