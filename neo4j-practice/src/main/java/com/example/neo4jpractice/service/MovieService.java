package com.example.neo4jpractice.service;

import com.example.neo4jpractice.entity.MovieEntity;
import com.example.neo4jpractice.entity.PersonEntity;
import com.example.neo4jpractice.repo.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/1/28 14:00
 */
@Service
@Slf4j
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public int saveMovie() {
        PersonEntity personEntity = new PersonEntity(1,"zhangsan");
        PersonEntity personEntity1 = new PersonEntity(2,"lisi");

        Set<PersonEntity> set = new HashSet<>();
        set.add(personEntity);

        Set<PersonEntity> set1 = new HashSet<>();
        set1.add(personEntity1);

        MovieEntity movieEntity = new MovieEntity("wqwqwq","zzzz");
        movieEntity.setDirectors(set);
        movieEntity.setActors(null);
        MovieEntity save = movieRepository.save(movieEntity);
        log.info(save.toString());
        return 1;
    }
}
