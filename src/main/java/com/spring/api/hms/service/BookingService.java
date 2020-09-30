package com.spring.api.hms.service;

import java.util.List;

import com.spring.api.hms.model.BookingDetails;

public interface BookingService {

	List<BookingDetails> getBookingDetails(String userId, String bookingDate);

	void createNewBooking(BookingDetails bookingDetails);

	String deleteBooking(int bookingId);

	BookingDetails getBookingSummaryDetails(int bookingId);

	void updateBookingDetails(BookingDetails bookingDetails);

	BookingDetails getAppointmentDetailsByDate(String bookingDate);

}
