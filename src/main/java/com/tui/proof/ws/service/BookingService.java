package com.tui.proof.ws.service;

import com.tui.proof.ws.model.Booking;

public interface BookingService {

	void deleteBooking(long id);
	Long createBooking(Booking booking);
}
