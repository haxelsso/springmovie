package com.axelsson.repositories;

import java.util.List;
import com.axelsson.entities.*;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	List<Movie> findByName(String name);
}
