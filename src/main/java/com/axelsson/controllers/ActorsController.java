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

// The ActorsController program 
// author Helena Axelsson
// version 1.0



@Controller
public class ActorsController {

	@Autowired
	private ActorRepository repository;

	@GetMapping("/actors")
	public String index(Model model) {

		// template processing
		model.addAttribute("listofactors", this.getActorsFromDb());
	
		// template processing
		return "actors";
	}

	@GetMapping("/api/v1/actors")
	public @ResponseBody Iterable index_json() {

		// JSON processing
		return this.getActorsFromDb();
	}

	
	private Iterable getActorsFromDb() {
		
		return repository.findAll();
	}
}
