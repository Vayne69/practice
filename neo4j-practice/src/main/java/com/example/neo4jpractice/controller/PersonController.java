package com.example.neo4jpractice.controller;

import com.example.neo4jpractice.entity.MovieEntity;
import com.example.neo4jpractice.entity.Person;
import com.example.neo4jpractice.repo.MovieRepository;
import com.example.neo4jpractice.service.MovieService;
import com.example.neo4jpractice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/neo4j")
@Slf4j
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    MovieService moveService;

    @Resource
    private MovieRepository movieRepository;

    @PostMapping("/person")
    public Map<String, String> createPerson() {
        Flux<Person> personFlux = personService.doWork();
        log.info(personFlux.toString());
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "success");
        return map;
    }

    @GetMapping("/movies")
    List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/movies/save")
    Map<String, String> save() {
        moveService.saveMovie();
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "success");
        return map;
    }

    @GetMapping("movies/{title}")
    MovieEntity findOneByTitle(@PathVariable String title) {
        return movieRepository.findOneByTitle(title);
    }
}
