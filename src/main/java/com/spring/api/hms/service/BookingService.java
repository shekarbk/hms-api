package com.spring.api.hms.service;

import java.util.List;

import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;

public interface BookingService {

	List<BookingDetails> getBookingDetailsByIdAndDate(int doctorId, String bookingDate);

	int createNewBooking(BookingDetails bookingDetails);

	String deleteBooking(int bookingId) throws NoRecordFoundException;

	BookingDetails getBookingSummaryDetails(int bookingId);

	void updateBookingDetails(BookingDetails bookingDetails) throws NoRecordFoundException;

	List<BookingDetails> getAppointmentDetailsByDate(String bookingDate);
	
	List<BookingDetails> getAllBookingDetails();
}
