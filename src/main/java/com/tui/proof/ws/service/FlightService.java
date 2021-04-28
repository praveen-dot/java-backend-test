package com.tui.proof.ws.service;

import java.util.List;

import com.tui.proof.ws.model.Flight;

public interface FlightService {
	List<Flight> getAllFlights();
	void deleteflight(long id);
	Long createflight(Flight flight);
}
