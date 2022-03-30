package com.example.neo4jpractice.service;

import com.example.neo4jpractice.entity.Person;
import com.example.neo4jpractice.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public Flux<Person> doWork() {

        Person emil = new Person("Emil");
        Person gerrit = new Person("Gerrit");
        Person michael = new Person("Michael");

        // Persist entities and relationships to graph database
        return repository.saveAll(Flux.just(emil, gerrit, michael));
    }
}