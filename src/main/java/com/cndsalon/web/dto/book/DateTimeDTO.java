package com.cndsalon.web.dto.book;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 개요: Java에서 생성한 LocalTime 타입의 변수들을 Javascript에서 사용하기 위해
 * 역직렬화를 해주는 DTO 클래스
 * </pre>
 * @author <a href="mailto:hkj5455@gamil.com">김진혁</a><br>
 * @date 2020. 12. 22. 
 * @version 1.0 
 * @since 
 */
@ToString
@Getter @Setter
@RequiredArgsConstructor
public class DateTimeDTO {

	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonFormat(pattern = "HH:mm")
	private LocalTime workTime;
	
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonFormat(pattern = "HH:mm")
	private LocalTime notWorkTime;
	
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonFormat(pattern = "HH:mm")
	private LocalTime designerWorkTime;
}
