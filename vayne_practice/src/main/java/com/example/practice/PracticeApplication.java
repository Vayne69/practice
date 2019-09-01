package com.example.practice;

import com.example.practice.aopAspect.TargetClass;
import com.example.practice.config.ComponentProperties;
import com.example.practice.test.PropertiesTest;
import com.example.practice.test.ValueTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dell
 */
@SpringBootApplication
@EnableEurekaClient
public class PracticeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PracticeApplication.class, args);
        // System.out.println(context.getBean(ComponentProperties.class));
        // context.close();
    }

    @Autowired
    ValueTest valueTest;
    @Autowired
    PropertiesTest propertiesTest;
    @Autowired
    TargetClass targetClass;

    @Override
    public void run(String... args) throws Exception {
        // String s = valueTest.toString();
        // System.out.println(s);
        // System.out.println(propertiesTest.toString());
        String result = targetClass.joint("asdad", "asdda");
        System.out.println("result:" + result);
    }
}
