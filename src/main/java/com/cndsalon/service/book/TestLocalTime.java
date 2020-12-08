package com.cndsalon.service.book;

import java.time.LocalDateTime;

public class TestLocalTime {

	public static void main(String[] args) {
		
		// 소요시간 제어 Test
		LocalDateTime origin = LocalDateTime.now();
		LocalDateTime plus = origin.plusMinutes(0);
		System.out.println(plus);
		
	}

}
