package com.spring.api.hms.model;

import com.spring.api.hms.enums.StatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookingDetails {
	private int bookingId;
	private String bookedDate;
	private String bookedTime;
	private int doctorId;
	private String doctorName;
	private String treatmentType;
	private String purpose;
	private int patientId;
	private String patientName;
	private StatusEnum isTreatmentCompleted;
	private String prescription;

}
