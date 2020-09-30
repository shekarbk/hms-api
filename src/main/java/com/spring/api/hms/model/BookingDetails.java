package com.spring.api.hms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookingDetails {
	private int bookingId;
	private String bookedDate;
	private String bookedTime;
	private String doctorName;
	private String treatmentType;
	private String purpose;
	private String profileId;
	private String patientName;
	private boolean isTreatmentCompleted;
	private String prescription;

}
