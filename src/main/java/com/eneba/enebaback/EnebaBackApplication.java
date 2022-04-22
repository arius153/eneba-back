package com.eneba.enebaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// TODO to work with DB remove 'exclude = DataSourceAutoConfiguration.class'
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EnebaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnebaBackApplication.class, args);
    }

}
