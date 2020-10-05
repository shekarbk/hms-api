package com.spring.api.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.BookingService;

@RestController
@RequestMapping("/v1/hms/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping
	public String CreateNewBooking(@RequestBody BookingDetails bookingDtls) {
		bookingService.createNewBooking(bookingDtls);
		return HmsConstants.SUCCESS_MESSAGE;
	}

	@GetMapping("/{bookingId}")
	public Response<BookingDetails> getBookingSummaryDetails(@PathVariable int bookingId) {
		BookingDetails bookingDetails = bookingService.getBookingSummaryDetails(bookingId);
		return new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.UPDATE_MESSAGE, bookingDetails);
	}

	@PutMapping
	public Response<BookingDetails> updateBookingDetails(@RequestBody BookingDetails bookingDtls) {
		try {
			bookingService.updateBookingDetails(bookingDtls);
		} catch (NoRecordFoundException e) {
			return new Response<BookingDetails>(HmsConstants.STATUS_FAILED, e.getMessage(), null);
		}
		return new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.UPDATE_MESSAGE, null);
	}

	@DeleteMapping("/{bookingId}")
	public Response<BookingDetails> deleteBooking(@PathVariable int bookingId) {
		try {
			bookingService.deleteBooking(bookingId);
		} catch (NoRecordFoundException e) {
			return new Response<BookingDetails>(HmsConstants.STATUS_FAILED, e.getMessage(), null);
		}
		return new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.DELETE_MESSAGE, null);
	}

}
