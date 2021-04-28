package com.tui.proof.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.SequenceGenerator;
import com.tui.proof.ws.repository.BookingRepository;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class BookingServiceImpl implements BookingService{
	
	
	private BookingRepository bookingrepository;
	
	
	private MongoOperations mongoOperations;
	
	
	private KafkaTemplate<String, String> kafkatemplate;
	
	
	private JavaMailSender mailsender;
	
	private ObjectMapper objectmapper=new ObjectMapper();
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingrepository, MongoOperations mongoOperations,
			KafkaTemplate<String, String> kafkatemplate, JavaMailSender mailsender) {
		this.bookingrepository = bookingrepository;
		this.mongoOperations = mongoOperations;
		this.kafkatemplate = kafkatemplate;
		this.mailsender = mailsender;
	}

	@Override
	public void deleteBooking(long id) {
		this.bookingrepository.deleteById(id);
	}
	
	public long sequenceGenerator(){
		
		SequenceGenerator counter = mongoOperations.findAndModify(
                query(where("_sequenceName").is("booking")),
                new Update().inc("sequenceId",1),
                options().returnNew(true).upsert(true),
                SequenceGenerator.class);

        return counter.getSequenceId();
	}

	@Override
	public Long createBooking(Booking booking) {
		// TODO Auto-generated method stub
		bookingrepository.save(booking);
		try {
			kafkatemplate.send("BookingDetails", objectmapper.writeValueAsString(booking));
			//consumeMessage(objectmapper.writeValueAsString(booking));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return booking.getId();
	}
	
	@KafkaListener(topics = "BookingDetails", groupId = "kafka_test_group")
	public void consumeMessage(String msg) {
		try {
			Thread.sleep(30000);
			Booking booking= objectmapper.readValue(msg, Booking.class);
			String email=booking.getEmail();
			SimpleMailMessage message= new SimpleMailMessage();
			message.setFrom("rgp.vicky@gmail.com");
			message.setTo(email);
			String mailSubject = "Springboot Mail";
			String mailcontent ="Hi  "+booking.getName()+ "\n\n"; 
			mailcontent += "Booking Successful" +"\n";
			mailcontent +="Thanks"+"\n"+"Praveen";
			message.setSubject(mailSubject);
			message.setText(mailcontent);
			mailsender.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
		
