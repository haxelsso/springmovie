package com.axelsson.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.axelsson.entities.*;
import com.axelsson.repositories.*;

// template processing
import org.springframework.ui.Model;
import java.util.*;

// The MoviesController program 
// author Helena Axelsson
// version 1.0



@Controller
public class MoviesController {

	@Autowired
	private MovieRepository repository;

	@GetMapping("/movies")
	public String index(Model model) {

		// template processing
		model.addAttribute("listofmovies", this.getMoviesFromDb());
	
		// template processing
		return "movies";
	}

	@GetMapping("/api/v1/movies/{id}")
	public @ResponseBody String showMovieById(@PathVariable("id") String movieId) {

		Movie movie = this.getMovieByIdFromDb(movieId);

		if (movie == null) {
			return "Movie not found";
		} else {		
			return movie.toString();
		}
	}

	@GetMapping("/api/v1/movies")
	public @ResponseBody Iterable index_json() {

		// JSON processing
		return this.getMoviesFromDb();
	}
	

	@PostMapping("/movies")
	public String moviesSubmit(@RequestParam(value="id", required=false, defaultValue="") String id,
				   @RequestParam(value="name", required=false, defaultValue="") String movieName,
				   @RequestParam(value="year", required=false, defaultValue="") String movieYear, Model model) {


		repository.save(new Movie(movieName, movieYear));

		// template processing
		model.addAttribute("listofmovies", this.getMoviesFromDb());
	
		// template processing
		return "movies";
		
	}

	@PostMapping("/api/v1/movies")
	public @ResponseBody Iterable moviesSubmit_json(@RequestParam(value="id", required=false, defaultValue="") String id,
				   @RequestParam(value="name", required=false, defaultValue="") String movieName,
				   @RequestParam(value="year", required=false, defaultValue="") String movieYear) {


		repository.save(new Movie(movieName, movieYear));

		// JSON processing
		return this.getMoviesFromDb();
		
	}
	
	private Iterable getMoviesFromDb() {
		
		return repository.findAll();
	}

	private Movie getMovieByIdFromDb(String movieId) {

		long movieIdChanged = Long.parseLong(movieId);

		return repository.findOne(movieIdChanged);
	}
}
