package com.spring.api.hms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import com.spring.api.hms.entity.BookingDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.repository.BookingDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class BookingServiceImplTest {

	@Mock
	UserDetailsRepository userDetailsRepository;

	@Mock
	BookingDetailsRepository bookingDetailsRepository;

	@InjectMocks
	BookingServiceImpl BookingServiceImpl;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetBookingDetailsByIdAndDate() {
		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();
		UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
		userDtlsObj1.setUserId(2);
		bookingDetailsEntity.setDoctorId(userDtlsObj1);

		UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
		userDtlsObj2.setUserId(1);
		bookingDetailsEntity.setPatientId(userDtlsObj2);

		List<BookingDetailsEntity> bookingEntyList = new ArrayList<BookingDetailsEntity>();
		bookingEntyList.add(bookingDetailsEntity);

		Mockito.when(
				bookingDetailsRepository.findByIdAndDate(Mockito.any(UserDetailsEntity.class), Mockito.anyString()))
				.thenReturn(bookingEntyList);
		List<BookingDetails> bookingVOList = BookingServiceImpl.getBookingDetailsByIdAndDate(2, "10-10-2020");

		Mockito.verify(bookingDetailsRepository).findByIdAndDate(Mockito.any(UserDetailsEntity.class),
				Mockito.anyString());
		assertEquals(1, bookingVOList.size());
	}

	@Test
	public void testCreateNewBooking() {
		BookingDetails bookingDetails = new BookingDetails();
		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();

		Mockito.when(userDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDtlsObj));
		Mockito.when(bookingDetailsRepository.save(Mockito.any(BookingDetailsEntity.class)))
				.thenReturn(bookingDetailsEntity);
		BookingServiceImpl.createNewBooking(bookingDetails);

		Mockito.verify(bookingDetailsRepository).save(Mockito.any(BookingDetailsEntity.class));
	}

	@Test
	public void testDeleteBooking() throws NoRecordFoundException {
		Mockito.doNothing().when(bookingDetailsRepository).deleteById(Mockito.anyInt());
		BookingServiceImpl.deleteBooking(1);
		Mockito.verify(bookingDetailsRepository).deleteById(Mockito.anyInt());
	}

	@Test
	public void testDeleteBookingWithExpectedException() throws NoRecordFoundException {
		Mockito.doNothing().when(bookingDetailsRepository).deleteById(Mockito.anyInt());
		Exception exception = Assertions.assertThrows(NoRecordFoundException.class,
				() -> BookingServiceImpl.deleteBooking(0));
		Assertions.assertTrue(exception.getClass().equals(NoRecordFoundException.class));
	}

	@Test
	public void testDeleteBookingWithEmptyResultExcpetion() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(bookingDetailsRepository)
				.deleteById(Mockito.anyInt());
		Exception exception = Assertions.assertThrows(NoRecordFoundException.class,
				() -> BookingServiceImpl.deleteBooking(1));
		Assertions.assertTrue(exception.getClass().equals(NoRecordFoundException.class));
		Mockito.verify(bookingDetailsRepository).deleteById(Mockito.anyInt());

	}

	@Test
	public void testGetBookingSummaryDetails() {
		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();

		UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
		userDtlsObj1.setUserId(2);
		bookingDetailsEntity.setDoctorId(userDtlsObj1);

		UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
		userDtlsObj2.setUserId(1);
		bookingDetailsEntity.setPatientId(userDtlsObj2);

		Mockito.when(bookingDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bookingDetailsEntity));
		BookingServiceImpl.getBookingSummaryDetails(1);

		Mockito.verify(bookingDetailsRepository).findById(Mockito.anyInt());
	}

	@Test
	public void testUpdateBookingDetails() throws NoRecordFoundException {
		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		Mockito.when(userDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDtlsObj));

		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();

		UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
		userDtlsObj1.setUserId(2);
		bookingDetailsEntity.setDoctorId(userDtlsObj1);

		UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
		userDtlsObj2.setUserId(1);
		bookingDetailsEntity.setPatientId(userDtlsObj2);

		Mockito.when(bookingDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bookingDetailsEntity));

		Mockito.when(bookingDetailsRepository.save(Mockito.any(BookingDetailsEntity.class)))
				.thenReturn(bookingDetailsEntity);

		BookingDetails bookingDetails = new BookingDetails();
		BookingServiceImpl.updateBookingDetails(bookingDetails);

		Mockito.verify(bookingDetailsRepository).save(Mockito.any(BookingDetailsEntity.class));

	}

	@Test
	public void testUpdateBookingDetailsWithExpectedException() {
		Exception exception = Assertions.assertThrows(NoRecordFoundException.class, () -> {
			Mockito.when(userDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

			BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();

			UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
			userDtlsObj1.setUserId(2);
			bookingDetailsEntity.setDoctorId(userDtlsObj1);

			UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
			userDtlsObj2.setUserId(1);
			bookingDetailsEntity.setPatientId(userDtlsObj2);

			Mockito.when(bookingDetailsRepository.findById(Mockito.anyInt()))
					.thenReturn(Optional.of(bookingDetailsEntity));

			Mockito.when(bookingDetailsRepository.save(Mockito.any(BookingDetailsEntity.class)))
					.thenReturn(bookingDetailsEntity);

			BookingDetails bookingDetails = new BookingDetails();
			BookingServiceImpl.updateBookingDetails(bookingDetails);

		});
		Assertions.assertTrue(exception.getClass().equals(NoRecordFoundException.class));
	}

	@Test
	public void testGetAppointmentDetailsByDate() {
		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();
		UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
		userDtlsObj1.setUserId(2);
		bookingDetailsEntity.setDoctorId(userDtlsObj1);

		UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
		userDtlsObj2.setUserId(1);
		bookingDetailsEntity.setPatientId(userDtlsObj2);

		List<BookingDetailsEntity> bookingEntyList = new ArrayList<BookingDetailsEntity>();
		bookingEntyList.add(bookingDetailsEntity);

		Mockito.when(bookingDetailsRepository.findBybookedDate(Mockito.anyString())).thenReturn(bookingEntyList);

		List<BookingDetails> bookingVOList = new ArrayList<BookingDetails>();
		BookingDetails bookingDetails = new BookingDetails();
		bookingVOList.add(bookingDetails);

		BookingServiceImpl.getAppointmentDetailsByDate("10-10-2010");
		Mockito.verify(bookingDetailsRepository).findBybookedDate(Mockito.anyString());

		assertEquals(1, bookingVOList.size());
	}

	@Test
	public void testGetAllBookingDetails() {
		BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();
		UserDetailsEntity userDtlsObj1 = new UserDetailsEntity();
		userDtlsObj1.setUserId(2);
		bookingDetailsEntity.setDoctorId(userDtlsObj1);

		UserDetailsEntity userDtlsObj2 = new UserDetailsEntity();
		userDtlsObj2.setUserId(1);
		bookingDetailsEntity.setPatientId(userDtlsObj2);

		List<BookingDetailsEntity> bookingEntyList = new ArrayList<BookingDetailsEntity>();
		bookingEntyList.add(bookingDetailsEntity);

		Mockito.when(bookingDetailsRepository.findAll()).thenReturn(bookingEntyList);
		BookingServiceImpl.getAllBookingDetails();
		Mockito.verify(bookingDetailsRepository).findAll();
		assertEquals(1, bookingEntyList.size());
	}
}
