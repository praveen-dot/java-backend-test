package com.tui.proof.ws.model;


import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value="booking")
public class Booking {
	@Id
	@EqualsAndHashCode.Include
	private long id;
	private String name;
	private String lastName;
	private long phoneNumber;
	private String email;
	private String address;
	private String origin;
	private String destination;
	private LocalDate dateFrom;
	private LocalDate dateTo;

}
