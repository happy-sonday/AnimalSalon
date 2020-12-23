package com.cndsalon.web.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingDTO {
	private String id;
	private String mcode;
	private String dcode;
	private String scode;
	private String bdate;
	private String btime;
	private int beautytime;
	private int price;
}
