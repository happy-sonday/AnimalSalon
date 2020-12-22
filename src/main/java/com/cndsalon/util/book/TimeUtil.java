package com.cndsalon.util.book;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cndsalon.domain.book.Booking;
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
	
	/**
	  *
	  * <pre>
	  * 개요 : createWorkTime / createNotWorkTime / createDesignerWorkTime 메서드들을 사용하는 메서드
	  * </pre>
	  * @method createTimeList
	  * @param sTime[String]
	  * @return 시간목록들을 put한 Map 객체 반환 [Map<String, List<DateTimeDTO>>]
	  *
	 */
	public Map<String, List<DateTimeDTO>> createTimeList(String sTime, LocalDate compareDate, List<Booking> designerWorkTimeList) {
		// 매장 운영시간 오픈, 마감 분리
		String startTimeStr = sTime.substring(0, sTime.indexOf("~"));
		String closeTimeStr = sTime.substring(sTime.indexOf("~")+1);
		
		// String -> Date 타입 파싱 + 마감 전까지의 예약 가능시간 설정
		LocalTime startTime = LocalTime.parse(startTimeStr);
		LocalTime closeTime = LocalTime.parse(closeTimeStr).minusMinutes(59);
		
		// 오늘 날짜 및 시간(+1분) 생성
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now().plusSeconds(1);	
		
		List<DateTimeDTO> workTimeList = this.createWorkTime(startTime, closeTime);
		List<DateTimeDTO> notWorkTimeList = null;
		List<DateTimeDTO> degWorkTimeList = null;
		if(nowDate.equals(compareDate) && nowTime.isAfter(startTime)) {
			notWorkTimeList = this.createNotWorkTime(startTime, nowTime, closeTime);
		}
		
		if(designerWorkTimeList.size() != 0) {
			degWorkTimeList = this.createDesignerWorkTimeList(designerWorkTimeList);
		}
		
		Map<String, List<DateTimeDTO>> timeMap = new HashMap<>();
		timeMap.put("workTime", workTimeList);
		timeMap.put("notWorkTime", notWorkTimeList);
		timeMap.put("degWorkTime", degWorkTimeList);
		
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
	
	/**
	  *
	  * <pre>
	  * 개요: 해당 샵의 디자이너의 예약내역을 조회하여 소요시간까지 시간 목록 생성
	  * </pre>
	  * @method createDesignerWorkTimeList
	  * @param designerWorkTimeList[List<Booking>] 예약시간과 소요시간을 가지고 있는 booking List
	  * @return 디자이너 작업시간인 예약불가 시간 목록 반환 [TimeUtil]
	  *
	 */
	public List<DateTimeDTO> createDesignerWorkTimeList(List<Booking> designerWorkTimeList){
		List<DateTimeDTO> timeList = new ArrayList<>();
		DateTimeDTO time = null;
		
		for(int i=0; i< designerWorkTimeList.size(); i++) {
			LocalTime dTime = designerWorkTimeList.get(i).getBTime(); // 예약시간
			int beautyTime = designerWorkTimeList.get(i).getbBeautyTime(); // 소요시간

			if(beautyTime==0) {
				time = new DateTimeDTO();
				time.setDesignerWorkTime(dTime);
				timeList.add(time);
			} else {
				int tempTime = 0;
				while(tempTime < beautyTime + 1) {
					time = new DateTimeDTO();
					time.setDesignerWorkTime(dTime.plusMinutes(tempTime));
					timeList.add(time);
					tempTime = tempTime+30;
					time = null;
				}			
			}
		}
		return timeList;
	}

	public Boolean checkAvailableTime(int sumB, String selectedTime, List<String> xTimeList) {
		LocalTime selectedS = LocalTime.parse(selectedTime);
		LocalTime selectedE = selectedS.plusMinutes(sumB);
		
		Boolean check = null;

		for(int i=0; i<xTimeList.size(); i++) {
			LocalTime checkTime = LocalTime.parse(xTimeList.get(i));
			
			if(selectedS.isBefore(checkTime) && selectedE.isAfter(checkTime)) {
				check = false;
				return check;
			} else {
				check = true;
			}
		}
		return check;
	}
	
}
