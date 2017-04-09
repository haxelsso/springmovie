package com.axelsson.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieMaker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//variables
	private Long id;
	public String firstName;
	public String lastName;

	protected MovieMaker() {}
	
	public MovieMaker(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return String.format("MovieMaker[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);

	}
}
