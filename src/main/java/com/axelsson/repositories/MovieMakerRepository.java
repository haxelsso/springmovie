package com.axelsson.repositories;

import java.util.List;
import com.axelsson.entities.*;

import org.springframework.data.repository.CrudRepository;

public interface MovieMakerRepository extends CrudRepository<MovieMaker, Long> {

	List<MovieMaker> findByLastName(String lastName);
}
