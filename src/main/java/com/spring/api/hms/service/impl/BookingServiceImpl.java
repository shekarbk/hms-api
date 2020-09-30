package com.spring.api.hms.service.impl;

import java.util.List;

import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.service.BookingService;

public class BookingServiceImpl implements BookingService {

	@Override
	public List<BookingDetails> getBookingDetails(String userId, String bookingDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewBooking(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub

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
