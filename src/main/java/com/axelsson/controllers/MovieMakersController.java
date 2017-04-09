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
import com.axelsson.entities.*;
import com.axelsson.repositories.*;

// template processing
import org.springframework.ui.Model;
import java.util.*;

// The MovieMakersController program 
// author Helena Axelsson
// version 1.0



@Controller
public class MovieMakersController {

	@Autowired
	private MovieMakerRepository repository;

	@GetMapping("/moviemakers")
	public String index(Model model) {

		// template processing
		model.addAttribute("listofmoviemakers", this.getMovieMakersFromDb());
	
		// template processing
		return "moviemakers";
	}

	@GetMapping("/api/v1/moviemakers")
	public @ResponseBody Iterable index_json() {

		// JSON processing
		return this.getMovieMakersFromDb();
	}

	
	private Iterable getMovieMakersFromDb() {
	
		return repository.findAll();
	}
}
