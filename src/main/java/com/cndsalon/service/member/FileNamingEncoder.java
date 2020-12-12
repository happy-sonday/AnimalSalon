package com.cndsalon.service.member;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service("fileNamingEncoder")
@Log
public class FileNamingEncoder {

	/**
	 * 실제 저장 파일명 암호화 처리<br> 
	 * 효과) 업로드 파일들의 중복 방지
	 *  
	 * @param fileName 원본 파일명 ex) abcd.pdf 
	 * @return 업로드할 파일명 ex) abcd_암호화코드.pdf
	 */
	public final String enFilename(String fileName) {

		log.info("파일명 접미어 처리 메서드 ");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String []fileNames = fileName.split("\\.");
		String pureFileName = "";
		
		// 확장자를 제외한 나머지 이름들 모두 연결(병합)
		for (int i=0; i<fileNames.length-1; i++) {
			pureFileName += fileNames[i];
			// 마지막에 토큰은 "." 미부착 !
			if (i<fileNames.length-2)
				pureFileName += ".";
		}
		
		int len = fileName.split("\\.").length;
		String extName = fileNames[len-1];
		
		String uniqueFileName = pureFileName + "_" 
							  + sdf.format(new Date(System.currentTimeMillis()))
							  // + Integer.toHexString(new Object().hashCode())
							  + this.makeRandomNumberPostfix(10)
							  +"." + extName;
							  // 16진수 해쉬코드(최대 8자리)
		return uniqueFileName;
	} //
	
	public final String decodeFilename(String encodedFilename) {
		
		String originalFilename = ""; 
		int lastIndex = encodedFilename.lastIndexOf("_"); 
		String filename = encodedFilename.substring(0, lastIndex);
		
		log.info("추출된 파일명 : " + filename);
		
		int dotLastIndex = encodedFilename.lastIndexOf(".");
		String ext = encodedFilename.substring(dotLastIndex+1);
		
		log.info("추출된 확장자 : " + ext);

		originalFilename = filename + "." + ext;
		
		return originalFilename;
	} //
	
	/**
	 * 인코딩 파일명 접미사용 난수 발생 함수 : n자리 고정 생성
	 * 
	 * @return 접미사용 난수
	 */
	public final String makeRandomNumberPostfix(int limit) {
		
		log.info("난수 발생 함수 ");
		
		long randomNum = new Random().nextLong();
		
		log.info("난수 : "+randomNum);
		String num = String.valueOf((randomNum+"").replaceAll("-", ""));
		
		if (num.length() < limit) {
			for (int i=0; i<limit-num.length(); i++) {
				num += "0";
			}
		} else {
			num = num.substring(0,10);
		}
		return num;
	}

}