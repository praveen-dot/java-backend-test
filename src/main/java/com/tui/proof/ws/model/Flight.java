package com.tui.proof.ws.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value ="flight")
public class Flight {
		
		@Id
		@EqualsAndHashCode.Include
		private long id;
		private String company;
		private LocalTime time;
		private LocalDate date;
		private Double price;
}
