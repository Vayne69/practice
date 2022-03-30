package com.example.neo4jpractice.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author yangj
 */
@Node(value = "people")
@Data
public class Person {
    @Id @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
