package com.tui.proof.ws.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tui.proof.ws.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, Long>{


}
