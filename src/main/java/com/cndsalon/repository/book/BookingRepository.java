package com.cndsalon.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cndsalon.domain.book.Booking;

public interface BookingRepository extends JpaRepository<Booking, String>{

}
