package com.spring.api.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.api.hms.entity.BookingDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetailsEntity, Integer> {
	
	@Query("select r from BookingDetailsEntity r where r.doctorId = ?1 and r.bookedDate = ?2")
	List<BookingDetailsEntity> findByIdAndDate(UserDetailsEntity doctorId, String bookingDate);

	List<BookingDetailsEntity> findBybookedDate(String bookingDate);
	
}
