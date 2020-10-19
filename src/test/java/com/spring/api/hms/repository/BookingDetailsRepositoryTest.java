package com.spring.api.hms.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.api.hms.entity.BookingDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingDetailsRepositoryTest {

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	@BeforeEach
	protected void setUp() throws Exception {

	}

	@Test
	public void testFindByIdAndDate() {
		UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
		userDetailsEntity.setUserId(1);
		String bookingDate = "10-10-2020";
		List<BookingDetailsEntity> BookingDetailsEntityList = bookingDetailsRepository
				.findByIdAndDate(userDetailsEntity, bookingDate);
		Assert.assertNotNull(BookingDetailsEntityList);
	}
	
	@Test
	public void testFindBybookedDate() {
		String bookingDate = "10-10-2020";
		List<BookingDetailsEntity> BookingDetailsEntityList = bookingDetailsRepository.findBybookedDate(bookingDate);
		Assert.assertNotNull(BookingDetailsEntityList);
	}
	
}
