package com.axelsson.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Movie {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//variables
	private Long id;
	public String name;
	public String year;

	protected Movie() {}
	
	public Movie(String name, String year) {
		this.name = name;
		this.year = year;
	}
	
	@Override
	public String toString() {
		return String.format("Movie[id=%d, name='%s', year='%s']", id, name, year);

	}
	

}
