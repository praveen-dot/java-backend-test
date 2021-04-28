package com.tui.proof.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService{
	
	
	private FlightRepository flightRepository;
	
	
	@Autowired
	public FlightServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
	
	public void deleteflight(long id) {
		 flightRepository.deleteById(id);
	}

	@Override
	public Long createflight(Flight flight) {
		// TODO Auto-generated method stub
		
		flightRepository.save(flight);
		return flight.getId();
	}
	
}
