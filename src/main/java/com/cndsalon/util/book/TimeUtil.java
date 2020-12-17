package com.cndsalon.util.book;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cndsalon.web.dto.book.DateTimeDTO;

/**
 * <pre>
 * 개요: 예약 시간과 관련된 유틸 클래스
 * </pre>
 * @author <a href="mailto:hkj5455@gamil.com">김진혁</a><br>
 * @date 2020. 12. 15. 
 * @version 1.0
 * @since 1.0
 */
@Component
public class TimeUtil {
	
	public Map<String, List<DateTimeDTO>> createTimeList(String sTime, String getDate) {
		// 매장 운영시간 오픈, 마감 분리
		String startTimeStr = sTime.substring(0, sTime.indexOf("~"));
		String closeTimeStr = sTime.substring(sTime.indexOf("~")+1);
		
		// String -> Date 타입 파싱 + 마감 전까지의 예약 가능시간 설정
		LocalTime startTime = LocalTime.parse(startTimeStr);
		LocalTime closeTime = LocalTime.parse(closeTimeStr).minusMinutes(59);
		
		// 오늘 날짜 및 시간(+1분) 생성
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now().plusSeconds(1);
		
		// 선택한 날짜 값 핸들링
		getDate = getDate.substring(0, getDate.indexOf("("));
		LocalDate compareDate = LocalDate.parse(getDate);
		
		
		List<DateTimeDTO> workTimeList = this.createWorkTime(startTime, closeTime);
		List<DateTimeDTO> notWorkTimeList = null;
		if(nowDate.equals(compareDate) && nowTime.isAfter(startTime)) {
			notWorkTimeList = this.createNotWorkTime(startTime, nowTime, closeTime);
		}
		
		Map<String, List<DateTimeDTO>> timeMap = new HashMap<>();
		timeMap.put("workTime", workTimeList);
		timeMap.put("notWorkTime", notWorkTimeList);
		
		return timeMap;
	}
	
	/**
	  *
	  * <pre>
	  * 개요: 매장 시작시간 기준으로 30분 단위로 마감시간까지 맞춰서 목록 생성
	  * </pre>
	  * @method createWorkTime
	  * @param startTime [LocalTime] 매장 시작 시간 
	  * @param closeTime [LocalTime] 매장 마감 -1 시간
	  * @return 선택 가능한 시간 목록 전체 [List<DateTimeU]
	  *
	 */
	public List<DateTimeDTO> createWorkTime(LocalTime startTime, LocalTime closeTime) {
		List<DateTimeDTO> timeList = new ArrayList<>();
		DateTimeDTO time = null;
		
		while(startTime.isBefore(closeTime)) {
			time = new DateTimeDTO();
			time.setWorkTime(startTime);
			timeList.add(time);
			startTime = startTime.plusMinutes(30);
			time = null;
		}
		return timeList;
	}
	
	
	/**
	  *
	  * <pre>
	  * 개요: 매장 시작시간 기준으로 30분 단위로 현재시간까지 맞춰서 목록 생성
	  * </pre>
	  * @method createNotWorkTime
	  * @param startTime [LocalTime] 매장 시작 시간 
	  * @param nowTime [LocalTime] 현재 시간
	  * @return 현재시간에서 벗어나 선택 불가능한 시간 목록 [List<DateTimeU]
	  *
	 */
	public List<DateTimeDTO> createNotWorkTime(LocalTime startTime, LocalTime nowTime, LocalTime closeTime) {
		List<DateTimeDTO> timeList = new ArrayList<>();
		DateTimeDTO time = null;
		
		while(startTime.isBefore(nowTime)) {
			time = new DateTimeDTO();
			time.setNotWorkTime(startTime);
			timeList.add(time);
			startTime = startTime.plusMinutes(30);
			time = null;
			if(startTime.isAfter(closeTime))
				break;
		}
		return timeList;
	}
	
}
