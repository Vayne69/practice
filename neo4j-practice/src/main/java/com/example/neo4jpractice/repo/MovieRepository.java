package com.example.neo4jpractice.repo;


import com.example.neo4jpractice.entity.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MovieRepository extends Neo4jRepository<MovieEntity, String> {
    MovieEntity findOneByTitle(String title);
}
