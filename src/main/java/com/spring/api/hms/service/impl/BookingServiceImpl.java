package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.entity.BookingDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.repository.BookingDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;
import com.spring.api.hms.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	BookingDetailsRepository bookingDtlsRepository;

	@Override
	public List<BookingDetails> getBookingDetailsByIdAndDate(int doctorId, String bookingDate) {
		
		UserDetailsEntity userEntity = new UserDetailsEntity();
		userEntity.setUserId(doctorId);
		List<BookingDetailsEntity> bookingEntyList = bookingDtlsRepository.findByIdAndDate(userEntity, bookingDate);
		List<BookingDetails> bookingVOList = new ArrayList<BookingDetails>();
		
		for (BookingDetailsEntity entity : bookingEntyList) {
			BookingDetails bookingVO = new BookingDetails();
			UserDetailsEntity patientEntity = entity.getPatientId();
			UserDetailsEntity doctorEntity = entity.getDoctorId();

			bookingVO.setBookingId(entity.getBookingId());
			bookingVO.setBookedDate(entity.getBookedDate());
			bookingVO.setBookedTime(entity.getBookedTime());
			bookingVO.setDoctorId(doctorEntity.getUserId());
			bookingVO.setDoctorName(entity.getDoctorId().getFirstName() + " " + entity.getDoctorId().getLastName());
			bookingVO.setTreatmentType(entity.getTreatmentType());
			bookingVO.setPurpose(entity.getPurpose());
			bookingVO.setPatientId(patientEntity.getUserId());
			bookingVO.setPatientName(entity.getPatientId().getFirstName() + " " + entity.getPatientId().getLastName());
			bookingVO.setIsTreatmentCompleted(entity.getIsTreatmentCompleted());
			bookingVO.setPrescription(entity.getPrescription());
			bookingVOList.add(bookingVO);
		}
		return bookingVOList;
	}

	@Override
	public void createNewBooking(BookingDetails bookingDetails) {

		Optional<UserDetailsEntity> patientObj = userDetailsRepository.findById(bookingDetails.getPatientId());
		Optional<UserDetailsEntity> doctorObj = userDetailsRepository.findById(bookingDetails.getDoctorId());

		BookingDetailsEntity bookingEnty = new BookingDetailsEntity(bookingDetails.getBookedDate(),
				bookingDetails.getBookedTime(), bookingDetails.getTreatmentType(), bookingDetails.getPurpose(),
				bookingDetails.getIsTreatmentCompleted(), bookingDetails.getPrescription(), patientObj.get(),
				doctorObj.get());
		bookingDtlsRepository.save(bookingEnty);
	}

	@Override
	public String deleteBooking(int bookingId) throws NoRecordFoundException {
		if (bookingId != 0) {
			try {
				bookingDtlsRepository.deleteById(bookingId);
			} catch (EmptyResultDataAccessException e) {
				throw new NoRecordFoundException("booking id not found !!!");
			}
		} else {
			throw new NoRecordFoundException("booking id not found !!!");
		}
		return HmsConstants.DELETE_MESSAGE;
	}

	@Override
	public BookingDetails getBookingSummaryDetails(int bookingId) {
		Optional<BookingDetailsEntity> bookingEnty = bookingDtlsRepository.findById(bookingId);
		BookingDetails bookingVO = new BookingDetails();
		if (bookingEnty.isPresent()) {
			BookingDetailsEntity entity = bookingEnty.get();
			UserDetailsEntity patientEntity = entity.getPatientId();
			UserDetailsEntity doctorEntity = entity.getDoctorId();

			bookingVO.setBookingId(entity.getBookingId());
			bookingVO.setBookedDate(entity.getBookedDate());
			bookingVO.setBookedTime(entity.getBookedTime());
			bookingVO.setDoctorId(doctorEntity.getUserId());
			bookingVO.setDoctorName(entity.getDoctorId().getFirstName() + " " + entity.getDoctorId().getLastName());
			bookingVO.setTreatmentType(entity.getTreatmentType());
			bookingVO.setPurpose(entity.getPurpose());
			bookingVO.setPatientId(patientEntity.getUserId());
			bookingVO.setPatientName(entity.getPatientId().getFirstName() + " " + entity.getPatientId().getLastName());
			bookingVO.setIsTreatmentCompleted(entity.getIsTreatmentCompleted());
			bookingVO.setPrescription(entity.getPrescription());
		}
		return bookingVO;
	}

	@Override
	public void updateBookingDetails(BookingDetails bookingDetails) throws NoRecordFoundException {
		Optional<UserDetailsEntity> patientObj = userDetailsRepository.findById(bookingDetails.getPatientId());
		Optional<UserDetailsEntity> doctorObj = userDetailsRepository.findById(bookingDetails.getDoctorId());

		if (!patientObj.isPresent() || !doctorObj.isPresent()) {
			throw new NoRecordFoundException("either patient id or doctor id not found !!!");
		}

		Optional<BookingDetailsEntity> bookingEnty = bookingDtlsRepository.findById(bookingDetails.getBookingId());
		if (bookingEnty.isPresent()) {
			BookingDetailsEntity entity = bookingEnty.get();
			entity.setBookedDate(bookingDetails.getBookedDate());
			entity.setBookedTime(bookingDetails.getBookedTime());
			entity.setDoctorId(doctorObj.get());
			entity.setTreatmentType(bookingDetails.getTreatmentType());
			entity.setPurpose(bookingDetails.getPurpose());
			entity.setPatientId(patientObj.get());
			entity.setIsTreatmentCompleted(bookingDetails.getIsTreatmentCompleted());
			entity.setPrescription(bookingDetails.getPrescription());

			bookingDtlsRepository.save(entity);
		}

	}

	@Override
	public List<BookingDetails> getAppointmentDetailsByDate(String bookingDate) {
		
		List<BookingDetailsEntity> bookingEntyList = bookingDtlsRepository.findBybookedDate(bookingDate);
		List<BookingDetails> bookingVOList = new ArrayList<BookingDetails>();
		for (BookingDetailsEntity entity : bookingEntyList) {
			BookingDetails bookingVO = new BookingDetails();
			UserDetailsEntity patientEntity = entity.getPatientId();
			UserDetailsEntity doctorEntity = entity.getDoctorId();

			bookingVO.setBookingId(entity.getBookingId());
			bookingVO.setBookedDate(entity.getBookedDate());
			bookingVO.setBookedTime(entity.getBookedTime());
			bookingVO.setDoctorId(doctorEntity.getUserId());
			bookingVO.setDoctorName(entity.getDoctorId().getFirstName() + " " + entity.getDoctorId().getLastName());
			bookingVO.setTreatmentType(entity.getTreatmentType());
			bookingVO.setPurpose(entity.getPurpose());
			bookingVO.setPatientId(patientEntity.getUserId());
			bookingVO.setPatientName(entity.getPatientId().getFirstName() + " " + entity.getPatientId().getLastName());
			bookingVO.setIsTreatmentCompleted(entity.getIsTreatmentCompleted());
			bookingVO.setPrescription(entity.getPrescription());
			bookingVOList.add(bookingVO);
		}
		return bookingVOList;
	}

}
