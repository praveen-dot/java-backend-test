package test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tui.proof.MainApplication;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.repository.FlightRepository;
import com.tui.proof.ws.service.BookingServiceImpl;
import com.tui.proof.ws.service.FlightServiceImpl;

@SpringBootTest(classes = MainApplication.class)
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingTest {
	
	@Autowired
	private BookingRepository bookingrepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private FlightRepository flightrepository;
	
	@Mock
	private KafkaTemplate<String, String> kafkatemplate;
	
	@Mock
	private JavaMailSender mailsender;
	
	
	private BookingServiceImpl bookingserviceimpl;
	private FlightServiceImpl flightserviceimpl;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		bookingserviceimpl=new BookingServiceImpl(bookingrepository, mongoOperations, kafkatemplate, mailsender);
		flightserviceimpl = new FlightServiceImpl(flightrepository);
	}
	
	@Test
	public void testBooking() {
		
		Booking booking =new Booking();
		booking.setId(1);
		booking.setName("rg");
		booking.setEmail("aaa@gmail.com");
		Long bookingid=bookingserviceimpl.createBooking(booking);
		
		Booking bk=bookingrepository.findById(bookingid).get();
		Assert.assertEquals(booking.getName(), bk.getName());
	}
	
	@Test
	public void testCreateFlight() {
		
		Flight flight = new Flight();
		flight.setId(34);
		flight.setCompany("JetAirways");
		Long flightid= flightserviceimpl.createflight(flight);
		Flight ft=flightrepository.findById(flightid).get(); 
		Assert.assertEquals(flight.getCompany(), ft.getCompany());
	}
	
	
}
