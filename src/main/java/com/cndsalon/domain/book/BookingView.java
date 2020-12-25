package com.cndsalon.domain.book;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "BOOKING_VIEW")
@Getter
@NoArgsConstructor
public class BookingView {
	@Id
	private Long bCode;
	private String id;
	private int bPrice;
	private String bStatus;
	private String mCode;
	private String mName;
	@Column(name = "M_P_PATH")
	private String mpPath;
	@Column(name = "M_P_SYSNAME")
	private String mpSysName;
	@Column(name = "M_P_ORGNAME")
	private String mpOrgName;
	private String sCode;
	private String sName;
	private String dCode;
	private String dName;

	@Column(name = "B_DATE", columnDefinition = "DATE")
	private LocalDate bDate;
	@Column(name = "B_TIME", columnDefinition = "TIME")
	private LocalTime bTime;

}
