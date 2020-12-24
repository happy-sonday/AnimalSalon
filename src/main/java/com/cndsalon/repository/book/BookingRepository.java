package com.cndsalon.repository.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cndsalon.domain.book.Booking;

public interface BookingRepository extends JpaRepository<Booking, String>{

	@Query(value = "SELECT * FROM booking b WHERE b.d_code= ?1 AND b.s_code= ?2 AND b.b_date= ?3 AND b.b_status = '0'",nativeQuery = true)
	List<Booking> findBydCodeAndsCodeAndbDate(@Param("dCode") String dCode, @Param("sCode") String sCode, @Param("compareDate") LocalDate compareDate);

}
