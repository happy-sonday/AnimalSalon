package com.cndsalon.service.member;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cndsalon.domain.member.DemoMember;
import com.cndsalon.repository.member.MemberDemoRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberCRUDServiceImpl implements MemberCRUDService{

	@Autowired
	private MemberDemoRepository repository;
	

	@Autowired
	@Qualifier("fileNamingEncoder")
	private FileNamingEncoder fileNamingEncoder;
	
	@Transactional
	@Override
	public void insert(DemoMember reqDto) {
		
			log.debug("MemberCRUDServiceImpl  insert(): {}", reqDto);
			repository.save(reqDto);		
	}

	@Transactional
	@Override
	public void update(DemoMember reqDto) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public String delete(DemoMember reqDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<DemoMember> allList(DemoMember reqDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public void updateProfile(MultipartFile pFile) {
		
		
	log.debug("updateProfile {}", pFile.getOriginalFilename());
		
		//파일 정보
		String originFileName = pFile.getOriginalFilename();
		String fileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1, originFileName.length());
		log.info("파일명 - 확장자 :  " + originFileName + "-" + fileExt);
		
		
		
		FileOutputStream fos = null;
		
		//파일명 인코딩(암호화)
		String enFileName=fileNamingEncoder.enFilename(originFileName);
		
		
		//파일저장소			 
		String uploadPath= "E:/upload/cndsalon/";		
				
		
		try {
			
			byte[] bytes = pFile.getBytes();
			Files.write(Paths.get(uploadPath+enFileName), bytes);
			
			//방법2
			/*
			File outFileName = new File(uploadPath + fileName);
			fos = new FileOutputStream(outFileName);
			fos.write(bytes);
			*/
		} catch ( IOException e) {
			log.error("FileUploadController save File writing error ! ");
			e.printStackTrace();
		}finally {
			
			try {
				if (fos != null) fos.close();
			} catch (IOException e) {
				log.error("FileUploadController save IOE : ");
				e.printStackTrace();
			}
			log.info("File upload success! ");
			
		}
		
		//디코딩(복호화된) 파일명
		//fileNamingEncoder.decodeFilename(fileName);
		
		repository.updateProfile(enFileName);
	

}//fileUpload END
		
		
		
		

	
}
	


