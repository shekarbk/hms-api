package com.spring.api.hms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.enums.StatusEnum;
import com.spring.api.hms.enums.TreatmentTypeEnum;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.BookingService;

@WebMvcTest(controllers = BookingController.class)
@ActiveProfiles("test")
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BookingService bookingServiceMock;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public void testCreateNewBooking() throws JsonProcessingException, Exception {
		Mockito.when(bookingServiceMock.createNewBooking(Mockito.any(BookingDetails.class)))
				.thenReturn(Mockito.anyInt());
		BookingDetails bookingDtls = new BookingDetails();
		bookingDtls.setBookedDate("05-10-2020");
		bookingDtls.setBookedTime("11:00");
		bookingDtls.setDoctorId(1);
		bookingDtls.setDoctorName("");
		bookingDtls.setTreatmentType("checkup");
		bookingDtls.setPurpose("cold&fever");
		bookingDtls.setPatientId(1);
		bookingDtls.setPatientName("");
		bookingDtls.setIsTreatmentCompleted(StatusEnum.NOT_COMPLETED);
		bookingDtls.setPrescription("");

		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, null, null);

		this.mockMvc
				.perform(post("/v1/hms/booking").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bookingDtls)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testgetBookingSummaryDetails() throws Exception {
		Mockito.when(bookingServiceMock.getBookingSummaryDetails(Mockito.anyInt()))
				.thenReturn(Mockito.any(BookingDetails.class));
		int bookingId = 1;
		this.mockMvc.perform(get("/v1/hms/booking/{bookingId}", bookingId)).andExpect(status().isOk());

	}

	@Test
	public void testUpdateBookingDetails() throws Exception {
		BookingDetails bookingDtls = new BookingDetails();
		Mockito.doNothing().when(bookingServiceMock).updateBookingDetails(Mockito.any(BookingDetails.class));
		this.mockMvc.perform(put("/v1/hms/booking").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bookingDtls))).andExpect(status().is(200));
	}

	@Test
	public void testUpdateBookingDetailsWithExpectedException() throws Exception {
		BookingDetails bookingDtls = new BookingDetails();
		Mockito.doThrow(NoRecordFoundException.class).when(bookingServiceMock)
				.updateBookingDetails(Mockito.any(BookingDetails.class));
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_FAILED, null, null);
		this.mockMvc
				.perform(put("/v1/hms/booking").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bookingDtls)))
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testDeleteBooking() throws Exception {
		int bookingId = 1;
		Mockito.when(bookingServiceMock.deleteBooking(Mockito.anyInt())).thenReturn(Mockito.anyString());
		this.mockMvc.perform(delete("/v1/hms/booking/{bookingId}", bookingId)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteBookingWithExpectedException() throws Exception {
		int bookingId = 0;
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_FAILED, null, null);
		Mockito.doThrow(NoRecordFoundException.class).when(bookingServiceMock).deleteBooking(Mockito.anyInt());
		this.mockMvc.perform(delete("/v1/hms/booking/{bookingId}", bookingId))
				.andExpect(jsonPath("$.status", is(response.getStatus())));

	}

//	TODO: need to fix below test case mock issue
	@Test
	public void testGetBookingDetailsByDoctorIdAndDate() throws Exception {
		String bookingDate = "10-10-2020";
		int doctorId = 2;
		List<BookingDetails> bookingDtlsList = new ArrayList<BookingDetails>();
		BookingDetails bookingDetails = new BookingDetails();
		bookingDtlsList.add(bookingDetails);
		Response<List<BookingDetails>> response = new Response<List<BookingDetails>>(HmsConstants.STATUS_SUCCESS, null,
				bookingDtlsList);

//		Mockito.when(bookingServiceMock.getBookingDetailsByIdAndDate(Mockito.anyInt(),Mockito.anyString()))
//				.thenReturn(Mockito.<BookingDetails>anyList());

		Mockito.when(bookingServiceMock.getBookingDetailsByIdAndDate(Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(bookingDtlsList);
		this.mockMvc.perform(get("/v1/hms/booking/date/{bookingDate}/doctorId/{doctorId}", bookingDate, doctorId))
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testGetBookingDetailsByDoctorIdAndDateWithException() throws Exception {
		String bookingDate = "10-10-2021";
		int doctorId = 1;
		List<BookingDetails> bookingDtlsList = new ArrayList<BookingDetails>();
		Response<List<BookingDetails>> response = new Response<List<BookingDetails>>(HmsConstants.STATUS_FAILED, null,
				bookingDtlsList);

		Mockito.when(bookingServiceMock.getBookingDetailsByIdAndDate(Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(bookingDtlsList);
		this.mockMvc.perform(get("/v1/hms/booking/date/{bookingDate}/doctorId/{doctorId}", bookingDate, doctorId))
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testGetAppointmentDetailsByDateWithExpectedException() throws Exception {
		String bookingDate = "10-10-2020";
		Response<List<BookingDetails>> response = new Response<List<BookingDetails>>(HmsConstants.STATUS_FAILED,
				HmsConstants.NO_RECORDS_FOUND, null);
		Mockito.when((bookingServiceMock.getAppointmentDetailsByDate(Mockito.anyString())))
				.thenReturn(Mockito.anyList());
		this.mockMvc.perform(get("/v1/hms/booking/date/{bookingDate}", bookingDate)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testGetAppointmentDetailsByDate() throws Exception {
		String bookingDate = "10-10-2020";
		List<BookingDetails> bookingDtls = new ArrayList<BookingDetails>();
		BookingDetails bookingDetails = new BookingDetails();
		bookingDtls.add(bookingDetails);
		Response<List<BookingDetails>> response = new Response<List<BookingDetails>>(HmsConstants.STATUS_SUCCESS, null,
				bookingDtls);
		Mockito.when((bookingServiceMock.getAppointmentDetailsByDate(Mockito.anyString()))).thenReturn(bookingDtls);
		this.mockMvc.perform(get("/v1/hms/booking/date/{bookingDate}", bookingDate)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testGetAllBookingDetails() throws Exception {
		List<BookingDetails> bookingDtls = new ArrayList<BookingDetails>();
		BookingDetails bookingDetails = new BookingDetails();
		bookingDtls.add(bookingDetails);
		Response<List<BookingDetails>> response = new Response<List<BookingDetails>>(HmsConstants.STATUS_SUCCESS, null,
				bookingDtls);
		Mockito.when(bookingServiceMock.getAllBookingDetails()).thenReturn(bookingDtls);
		this.mockMvc.perform(get("/v1/hms/booking/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

}
