package com.cndsalon.util.book;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

	public String TodaysDay() {
		DayOfWeek todaysDay = DayOfWeek.from(LocalDate.now());

		switch (todaysDay) {
		case MONDAY:return "MON";
		case TUESDAY:return "TUE";
		case WEDNESDAY:return "WED";
		case THURSDAY:return "THR";
		case FRIDAY:return "FRI";
		case SATURDAY:return "SAT";
		case SUNDAY:return "SUN";
		}
		return null;
	}
}
