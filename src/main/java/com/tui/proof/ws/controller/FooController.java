package com.tui.proof.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.repository.FlightRepository;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.service.FlightService;


@RestController
public class FooController {
	
	@Autowired
	private BookingRepository bookingrepository;
	@Autowired
	private FlightRepository flightrepository;
	@Autowired
	private FlightService flightservice;
	@Autowired
	private BookingService bookingservice;
	
	
	@PostMapping("/createbooking")
	public Long createBooking(@RequestBody Booking booking ){
		return bookingservice.createBooking(booking);
				
	}
	
	@GetMapping("/showbookingdetails/{id}")
	public Booking showBookingDetails(@PathVariable long id) {
		return bookingrepository.findById(id).get();
	}
	
	@GetMapping("/deleteBooking/{id}")
	public void deleteBooking(@PathVariable (value="id") long id) {
		
		//call the delete employee method 
		bookingservice.deleteBooking(id);
	}
	
	@GetMapping("/showFlights")
	public List<Flight> getFlights(){
		// getting all the available flights
		return flightservice.getAllFlights();
	}
	
	@PostMapping("/createflights")
	public Long createFlights(@RequestBody Flight flight) {
		return flightservice.createflight(flight);
	}
	
	@DeleteMapping("/deleteFlights/{id}")
	public void deleteflights(@PathVariable long id) {
		//deleting the flight by id;
		flightservice.deleteflight(id);
	}
	
	
  
}
