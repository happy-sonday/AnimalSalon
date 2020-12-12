package com.cndsalon.repository.member;

public interface CertificationRepository {

	public int findById(String id);
	
	public int findByPhone(String phone);
	
	public int findByEmail(String email);
	
	public int findByNickname(String email);
	
}
