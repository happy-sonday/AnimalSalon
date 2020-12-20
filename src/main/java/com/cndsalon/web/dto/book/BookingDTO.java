package com.cndsalon.web.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingDTO {
	private String id;
	private String mCode;
	private String dCode;
	private String sCode;
	private String bDate;
	private String bTime;
	private int bBeautyTime;
	private int bPrice;
}
