//package com.cndsalon.util.book;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//@Configuration
//public class DateFormatConfiguration2 {
//
//	@Bean
//	public SimpleModule jsonMapperJava8DateTimeModule() {
//		SimpleModule module = new SimpleModule();
//
//		module.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
//			@Override
//			public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
//					throws IOException {
//				return LocalDate.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//			}
//		});
//
//		module.addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
//
//			public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
//					throws IOException {
//				return LocalTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern("kk:mm:ss"));
//			}
//		});
//
//		module.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//
//			public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
//					throws IOException {
//				return LocalDateTime.parse(jsonParser.getValueAsString(),
//						DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));
//			}
//		});
//
//		return module;
//	}
//}