package com.cndsalon.util.book;


public class TestLocalTime {

	public static void main(String[] args) {
		
////		 소요시간 제어 Test
//		LocalDateTime origin = LocalDateTime.now();
//		LocalDateTime plus = origin.plusMinutes(0);
//		System.out.println(plus);
		
		TimeUtil test = new TimeUtil();
		
		String sTime = "09:00~18:00";
		String getDate = "2020-12-15(화)";
		test.createTimeList(sTime, getDate);
		

	}

}
