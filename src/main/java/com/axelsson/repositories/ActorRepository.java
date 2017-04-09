package com.axelsson.repositories;

import java.util.List;
import com.axelsson.entities.*;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {

	List<Actor> findByLastName(String lastName);
}
