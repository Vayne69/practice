package com.example.excledemo;

import com.example.excledemo.service.People;
import com.example.excledemo.service.PeopleFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcleDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExcleDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        People user1 = PeopleFactory.getByUserType("user1");
        System.out.println(user1);
    }
}
