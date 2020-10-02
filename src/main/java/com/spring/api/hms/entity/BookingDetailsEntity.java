package com.spring.api.hms.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.api.hms.enums.StatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table(name = "BOOKING_DETAILS")
public class BookingDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private String bookedDate;
	private String bookedTime;
	private String treatmentType;
	private String purpose;
	private StatusEnum isTreatmentCompleted;
	private String prescription;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "patientId", nullable = false) //patientId is the column name present in the table
	private UserDetailsEntity patientId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "doctorId", nullable = false) //doctorId is the column name present in the table
	private UserDetailsEntity doctorId;

	public BookingDetailsEntity(String bookedDate, String bookedTime,  String treatmentType,
			String purpose, StatusEnum isTreatmentCompleted, String prescription,
			UserDetailsEntity patentId, UserDetailsEntity doctorId) {
		super();
		this.bookedDate = bookedDate;
		this.bookedTime = bookedTime;
		this.treatmentType = treatmentType;
		this.purpose = purpose;
		this.isTreatmentCompleted = isTreatmentCompleted;
		this.prescription = prescription;
		this.patientId = patentId;
		this.doctorId = doctorId;
	}

}
