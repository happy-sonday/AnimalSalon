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

import org.hibernate.annotations.DynamicUpdate;
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
 * Lombok의 Setter를 사용할 시 Gson이 Json의 대문자를 인식하지 못하는 이슈 발생으로 setter 메서드 직접 생성
 */
@Entity(name = "BOOKING")
@Getter
@NoArgsConstructor
@DynamicUpdate
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
    
	

	public Long getbCode() {
		return bCode;
	}

	public void setbCode(Long bCode) {
		this.bCode = bCode;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public String getdCode() {
		return dCode;
	}

	public void setdCode(String dCode) {
		this.dCode = dCode;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public Integer getbBeautyTime() {
		return bBeautyTime;
	}

	public void setbBeautyTime(Integer bBeautyTime) {
		this.bBeautyTime = bBeautyTime;
	}

	public Integer getbPrice() {
		return bPrice;
	}

	public void setbPrice(Integer bPrice) {
		this.bPrice = bPrice;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	public void setbDate(String bDate) {
		this.bDate = LocalDate.parse(bDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void setbTime(String bTime) {
		this.bTime = LocalTime.parse(bTime, DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	@PrePersist
	public void defaultStatus() {
		if(bStatus== null) {
			this.bStatus = "0";
		}
	}
	
	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}
	
	public void setbCancelReason(String bCancelReason) {
		this.bCancelReason = bCancelReason;
	}
	
	@Override
	public String toString() {
		return "Booking [bCode=" + bCode + ", id=" + id + ", mCode=" + mCode + ", dCode=" + dCode + ", sCode=" + sCode
				+ ", bDate=" + bDate + ", bTime=" + bTime + ", bBeautyTime=" + bBeautyTime + ", bPrice=" + bPrice
				+ ", bStatus=" + bStatus + ", bCancelReason=" + bCancelReason + "]";
	}

}
