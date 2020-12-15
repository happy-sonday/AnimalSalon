//package com.cndsalon.util.book;
//
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
//
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.format.DateTimeFormatter;
//import java.util.TimeZone;
//
//@Configuration
//public class DateFormatConfiguration {
//	private static final String timeFormat = "HH:mm";
//	private static final String dateFormat = "yyyy-MM-dd";
//	private static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
//
//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//		return jacksonObjectMapperBuilder -> {
//			jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("UTC"));
//			jacksonObjectMapperBuilder.simpleDateFormat(datetimeFormat);
//			jacksonObjectMapperBuilder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
//			jacksonObjectMapperBuilder
//					.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(datetimeFormat)));
//			jacksonObjectMapperBuilder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));
//		};
//	}
//}
