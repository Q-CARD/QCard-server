package com.qcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.qcard"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.qcard"})
public class QCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(QCardApplication.class, args);
    }
}
