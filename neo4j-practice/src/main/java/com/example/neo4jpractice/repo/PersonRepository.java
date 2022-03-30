package com.example.neo4jpractice.repo;

import com.example.neo4jpractice.entity.Person;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveNeo4jRepository<Person, Long> {

    Flux<Person> findAllByName(String name);

    Flux<Person> findAllByNameLike(String name);
}