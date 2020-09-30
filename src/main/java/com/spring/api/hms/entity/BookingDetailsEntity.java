package com.spring.api.hms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name="BOOKING_DETAILS") 
public class BookingDetailsEntity {
	@Id
	@GeneratedValue
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private UserDetailsEntity userDetails;
}
