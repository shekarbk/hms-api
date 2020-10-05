package com.spring.api.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;

@Service
public interface BookingService {

	List<BookingDetails> getBookingDetails(String userId, String bookingDate);

	void createNewBooking(BookingDetails bookingDetails);

	String deleteBooking(int bookingId) throws NoRecordFoundException;

	BookingDetails getBookingSummaryDetails(int bookingId);

	void updateBookingDetails(BookingDetails bookingDetails) throws NoRecordFoundException;

	BookingDetails getAppointmentDetailsByDate(String bookingDate);

}
