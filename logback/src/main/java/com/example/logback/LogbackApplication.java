package com.example.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
public class LogbackApplication implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(LogbackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogbackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            int a = 2 / 0;
            System.out.println(a);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
