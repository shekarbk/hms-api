package com.spring.api.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.service.BookingService;

@RestController
@RequestMapping("/v1/hms")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/booking")
	public String CreateNewBooking(@RequestBody BookingDetails bookingDtls) {
		bookingService.createNewBooking(bookingDtls);
		return HmsConstants.SUCCESS_MESSAGE;
	}

	@GetMapping("/booking/{bookingId}")
	public BookingDetails getBookingSummaryDetails(@PathVariable int bookingId) {
		BookingDetails bookingDetails = bookingService.getBookingSummaryDetails(bookingId);
		return bookingDetails;
	}
}
