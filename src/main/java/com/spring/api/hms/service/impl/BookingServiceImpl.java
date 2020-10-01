package com.spring.api.hms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.hms.entity.BookingDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.repository.BookingDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;
import com.spring.api.hms.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	BookingDetailsRepository bookingDtlsRepository;

	@Override
	public List<BookingDetails> getBookingDetails(String userId, String bookingDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewBooking(BookingDetails bookingDetails) {
		Optional<UserDetailsEntity> userDetails = userDetailsRepository.findById(bookingDetails.getProfileId());
		if (userDetails.isPresent()) {
			BookingDetailsEntity bookingEnty = new BookingDetailsEntity(bookingDetails.getBookedDate(),
					bookingDetails.getBookedTime(), bookingDetails.getDoctorName(), bookingDetails.getTreatmentType(),
					bookingDetails.getPurpose(), bookingDetails.getProfileId(),
					bookingDetails.isTreatmentCompleted(), bookingDetails.getPrescription(), userDetails.get());
			bookingDtlsRepository.save(bookingEnty);
		}

	}

	@Override
	public String deleteBooking(int bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingDetails getBookingSummaryDetails(int bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBookingDetails(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public BookingDetails getAppointmentDetailsByDate(String bookingDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
