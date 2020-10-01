package com.spring.api.hms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table (name="BOOKING_DETAILS") 
public class BookingDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private String bookedDate;
	private String bookedTime;
	private String doctorName;
	private String treatmentType;
	private String purpose;
	private int profileId;
	private boolean isTreatmentCompleted;
	private String prescription;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private UserDetailsEntity userDetails;
	
	public BookingDetailsEntity(String bookedDate, String bookedTime, String doctorName,
			String treatmentType, String purpose, int profileId, boolean isTreatmentCompleted,
			String prescription, UserDetailsEntity userDetails) {
		super();
		this.bookedDate = bookedDate;
		this.bookedTime = bookedTime;
		this.doctorName = doctorName;
		this.treatmentType = treatmentType;
		this.purpose = purpose;
		this.profileId = profileId;
		this.isTreatmentCompleted = isTreatmentCompleted;
		this.prescription = prescription;
		this.userDetails = userDetails;
	}
	
	
	
}
